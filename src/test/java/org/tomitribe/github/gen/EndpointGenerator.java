/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.tomitribe.github.gen;

import lombok.Data;
import org.tomitribe.github.gen.code.endpoint.Endpoint;
import org.tomitribe.github.gen.code.endpoint.EndpointMethod;
import org.tomitribe.github.gen.code.model.ArrayClazz;
import org.tomitribe.github.gen.code.model.Clazz;
import org.tomitribe.github.gen.code.model.ClazzReference;
import org.tomitribe.github.gen.code.model.Example;
import org.tomitribe.github.gen.code.model.Field;
import org.tomitribe.github.gen.code.model.Name;
import org.tomitribe.github.gen.code.model.VoidClazz;
import org.tomitribe.github.gen.openapi.Content;
import org.tomitribe.github.gen.openapi.Github;
import org.tomitribe.github.gen.openapi.Method;
import org.tomitribe.github.gen.openapi.OpenApi;
import org.tomitribe.github.gen.openapi.Parameter;
import org.tomitribe.github.gen.openapi.Preview;
import org.tomitribe.github.gen.openapi.Response;
import org.tomitribe.github.gen.openapi.Schema;
import org.tomitribe.github.gen.util.Words;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;

@Data
public class EndpointGenerator {


    private final ModelGenerator modelGenerator;
    private final String modelPackage;
    private ComponentIndex componentIndex;
    private final String endpointPackage;
    private ResolveReferences resolver;

    public EndpointGenerator() {
        this.modelPackage = "org.tomitribe.github.model";
        this.endpointPackage = "org.tomitribe.github.client";
        this.modelGenerator = new ModelGenerator(modelPackage);
    }

    public List<Endpoint> build(final OpenApi openApi) {
        this.componentIndex = new ComponentIndex(modelGenerator, openApi);
        this.resolver = new ResolveReferences(componentIndex);

        modelGenerator.getClasses().forEach(resolver::resolve);

        final Map<String, List<EndpointMethod>> categories = openApi.getMethods()
                .filter(this::isSupported)
                .map(this::createMethod)
                .collect(Collectors.groupingBy(EndpointMethod::getCategory));

        final List<Endpoint> endpoints = new ArrayList<>();

        for (final Map.Entry<String, List<EndpointMethod>> entry : categories.entrySet()) {
            final String category = entry.getKey();
            final List<EndpointMethod> methods = entry.getValue();

            final String typeName = Words.getTypeName(category + "-client");

            endpoints.add(Endpoint.builder()
                    .className(typeName)
                    .methods(methods)
                    .build());
        }

        final List<Clazz> classes = modelGenerator.getClasses();
        classes.forEach(resolver::resolve);

        // We no longer need any of the ClazzReference instances
        // Remove them so they aren't rendered
        classes.removeIf(clazz -> clazz instanceof ClazzReference);

        return endpoints;
    }

    private boolean isSupported(final Method method) {
        /*
         * We use the summary to create the method name
         */
        if (method.getSummary() == null) return false;

        /*
         * If we don't have response information, we can't do anything
         *
         * Does not appear to skip any endpoints
         */
        if (method.getResponses() == null) return false;

        /*
         * If there are no 200 range responses, we don't currently support it
         * There are some that legitimately return 302 redirects, which we
         * should add support for in future versions.
         *
         * Skips
         * migrations/download-archive-for-org
         * actions/download-artifact
         * actions/download-job-logs-for-workflow-run
         * actions/download-workflow-run-logs
         * repos/download-tarball-archive
         * repos/download-zipball-archive
         * migrations/get-archive-for-authenticated-user
         */
        if (method.getResponses().keySet().stream().noneMatch(s -> s.startsWith("2"))) return false;

        /*
         * If it returns void or returns json, we're good.
         * If it returns neither of those things we currently can't support it.
         *
         * There are API calls that return binary data like zips, which we should
         * add support for in future versions.
         *
         * Skips:
         * markdown/render
         * markdown/render-raw
         * activity/mark-thread-as-read
         * meta/get-octocat
         * actions/cancel-workflow-run
         * actions/re-run-workflow
         * repos/get-pull-request-review-protection
         * checks/rerequest-suite
         * activity/mark-repo-notifications-as-read
         * pulls/remove-requested-reviewers
         * scim/list-provisioned-identities
         * scim/provision-and-invite-user
         * scim/get-provisioning-information-for-user
         * scim/update-attribute-for-user
         * scim/set-information-for-provisioned-user
         * meta/get-zen
         */
        if (!returnsVoid(method) && !returnsApplicationJson(method)) return false;

        /*
         * If the request body is not application/json we can't support it yet.
         *
         * Skips:
         * repos/upload-release-asset
         */
        if (method.getRequestBody() != null && !acceptsApplicationJson(method)) return false;

        return true;
    }

    private boolean acceptsApplicationJson(final Method method) {
        return method.getRequestBody().getContent().keySet().stream()
                .anyMatch(s -> s.equals("application/json"));
    }

    private boolean returnsApplicationJson(final Method method) {
        return method.getResponses().values().stream()
                .filter(response -> response.getName().startsWith("2"))
                .map(Response::getContent)
                .filter(Objects::nonNull)
                .map(Map::keySet)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .anyMatch(s -> s.equals("application/json"));
    }

    private boolean returnsVoid(final Method method) {
        return method.getResponses().keySet().stream().anyMatch(s -> s.equals("204"));
    }

    private EndpointMethod createMethod(final Method method) {
        final String methodName = asMethodName(method.getSummary());
        final String requestClassName = asRequestName(method.getSummary());

        final Clazz requestClass = generateRequestClass(requestClassName, method);
        final Clazz responseClass = generateResponseClass(method);

        final EndpointMethod.Builder builder = EndpointMethod.builder()
                .method(method.getName())
                .path(method.getPath().getName())
                .javaMethod(methodName)
                .request(requestClass)
                .response(responseClass)
                .summary(method.getSummary())
                .operationId(method.getOperationId());

        if (method.getExternalDocs() != null) {
            builder.docs(method.getExternalDocs().getUrl());
        }

        final Github github = method.getGithub();
        if (github != null) {
            builder.category(github.getCategory())
                    .subcategory(github.getSubcategory())
                    .removalDate(github.getRemovalDate())
                    .deprecationDate(github.getDeprecationDate())
                    .enabledForGitHubApps(TRUE.equals(github.getEnabledForGitHubApps()))
                    .githubCloudOnly(TRUE.equals(github.getGithubCloudOnly()));

            if (github.getPreviews() != null) {
                for (final Preview preview : github.getPreviews()) {
                    builder.preview(preview.getName());
                }
            }
        }

        final EndpointMethod endpointMethod = builder.build();
        resolver.resolveMethod(endpointMethod);

        final Clazz clazz = endpointMethod.getResponse();
        if (clazz.isPaged()) {
            paged(clazz);
        }
        if (shouldHaveName(clazz) && clazz.getName() == null) {
            final String name = Words.getTypeName(method.getSummary()) + "Response";
            clazz.setName(new Name(modelPackage, name));
        }

        return endpointMethod;
    }

    private Clazz generateResponseClass(final Method method) {
        if (method.getResponses() == null) throw new IllegalStateException("Method has no responses: " + method);
        if (method.getResponses().values() == null) throw new IllegalStateException("Method has no responses: " + method);

        final Response ok = method.getResponses().values().stream()
                .filter(response -> response.getName().startsWith("2"))
                .min(Comparator.comparing(Response::getName))
                .orElseThrow(() -> new IllegalStateException("No 200 range responses found"));

        if (ok.getContent() == null) {
            return new VoidClazz();
        }

        final Content jsonResponse = ok.getContent().get("application/json");
        if (jsonResponse == null) {
            if (method.getResponses().values().stream().anyMatch(response -> response.getName().equals("204"))) {
                return new VoidClazz();
            } else {
                throw new IllegalStateException("Expected 'application/json' content");
            }
        }

        final Clazz clazz = modelGenerator.build(jsonResponse.getSchema());

        processExamples(jsonResponse, clazz);

        if (isPaged(ok)) {
            clazz.setPaged(true);
        }

        return clazz;
    }

    private static void processExamples(final Content jsonResponse, final Clazz clazz) {
        if (jsonResponse.getExamples() != null) {
            jsonResponse.getExamples().values().stream()
                    .map(Map.class::cast)
                    .map(map -> map.get("$ref"))
                    .filter(Objects::nonNull)
                    .map(String.class::cast)
                    .map(s -> s.replaceAll("^#/components/examples/", ""))
                    .map(s -> Example.builder().name(s).build())
                    .forEach(clazz.getExamples()::add);
        }

        if (jsonResponse.getExample() != null) {
            clazz.getExamples().add(Example.builder().content(jsonResponse.getExample()).build());
        }
    }

    private void paged(final Clazz clazz) {
        if (clazz instanceof ArrayClazz) {
            final ArrayClazz arrayClazz = (ArrayClazz) clazz;
            arrayClazz.setPaged(true);
        } else {
            final Field items = getPagedItem(clazz);

            final String pageName = plural(items.getType().getSimpleName()) + "Page";

            clazz.setName(new Name(modelPackage, pageName));
            clazz.setPaged(true);
        }
    }

    private String plural(final String name) {
        if (name.endsWith("y")) {
            return name.replaceAll("y$", "ies");
        }
        if (name.endsWith("s")) {
            return name + "es";
        }
        return name + "s";
    }

    public static Field getPagedItem(final Clazz clazz) {
        final List<Field> arrays = clazz.getFields().stream()
                .filter(Field::isCollection)
                .collect(Collectors.toList());

        if (arrays.size() != 1) {
            throw new IllegalStateException("Expected exactly 1 array field, found " + arrays.size());
        }

        return arrays.get(0);
    }

    private boolean isPaged(final Response response) {
        /*
         * Our preferred way to detect if the response is paged is to have a `Link` header
         * However, the openapi definition for github leaves it out in a few places.
         */
        if (response.getHeaders() != null && response.getHeaders().get("Link") != null) return true;

        /*
         * Our backup detection is if the class as a field called 'items' that is an array,
         * another field called `total_count` which is an int.
         */
        if (response.getContent() == null) return false;

        final Content content = response.getContent().get("application/json");
        if (content == null) return false;
        if (content.getSchema() == null) return false;
        if (content.getSchema().getProperties() == null) return false;
        if (content.getSchema().getProperties().get("items") == null) return false;
        if (content.getSchema().getProperties().get("total_count") == null) return false;

        final Schema page = content.getSchema();
        final Schema items = page.getProperties().get("items");
        final Schema totalCount = page.getProperties().get("total_count");

        if (!items.getType().equals("array")) return false;
        if (!totalCount.getType().equals("integer")) return false;
        return true;
    }

    private boolean shouldHaveName(final Clazz clazz) {
        if (clazz instanceof ArrayClazz) return false;
        if (clazz instanceof VoidClazz) return false;
        return true;
    }

    private Clazz generateRequestClass(final String requestClassName, final Method method) {
        // It has both parameters and a request body
        if (method.getRequestBody() != null && method.getParameters() != null && method.getParameters().size() > 0) {
            final Clazz clazz;
            {
                final Map<String, Content> content = method.getRequestBody().getContent();
                final Content applicationJson = content.get("application/json");
                final Schema schema = applicationJson.getSchema();
                clazz = modelGenerator.build(schema);
                if (clazz.getName() == null) {
                    clazz.setName(new Name(modelPackage, requestClassName));
                }
            }

            final Clazz.Builder builder = Clazz.builder();
            for (final Parameter parameter : method.getParameters()) {
                final Schema schema = getSchema(parameter);

                if (isPagingParameter(schema)) continue;

                final String name = Optional.ofNullable(parameter.getName())
                        .orElse(schema.getName());
                final Field field = modelGenerator.getField(builder, name, schema);

                if (parameter.getIn() != null) {
                    final Field.In in = Field.In.valueOf(parameter.getIn().toUpperCase());
                    field.setIn(in);
                }
                clazz.getFields().add(field);
            }

            clazz.getInnerClasses().addAll(builder.getInnerClasses());

            return clazz;
        }

        if (method.getRequestBody() != null) {
            final Map<String, Content> content = method.getRequestBody().getContent();
            final Content applicationJson = content.get("application/json");
            final Schema schema = applicationJson.getSchema();
            final Clazz clazz = modelGenerator.build(schema);
            if (clazz.getName() == null) {
                clazz.setName(new Name(modelPackage, requestClassName));
            }
            return clazz;
        }

        if (method.getParameters() != null) {

            final Clazz.Builder clazz = Clazz.builder().name(modelPackage + "." + requestClassName);
            for (final Parameter parameter : method.getParameters()) {
                final Schema schema = getSchema(parameter);

                if (isPagingParameter(schema)) continue;

                final String name = Optional.ofNullable(parameter.getName())
                        .orElse(schema.getName());
                final Field field = modelGenerator.getField(clazz, name, schema);

                if (parameter.getIn() != null) {
                    final Field.In in = Field.In.valueOf(parameter.getIn().toUpperCase());
                    field.setIn(in);
                }
                clazz.field(field);
            }
            if (clazz.getFields().size() == 0) {
                return new VoidClazz();
            }
            return clazz.build();
        }

        return new VoidClazz();
    }

    private boolean isPagingParameter(final Schema schema) {
        if ("#/components/parameters/per_page".equals(schema.getRef())) return true;
        if ("#/components/parameters/page".equals(schema.getRef())) return true;
        return false;
    }

    private Schema getSchema(final Parameter parameter) {
        if (parameter.getSchema() != null) {
            return parameter.getSchema();
        }

        if (parameter.getRef() != null) {
            final Schema schema = new Schema();
            schema.setRef(parameter.getRef());
            return schema;
        }

        throw new IllegalStateException("No schema found for parameter: " + parameter);
    }

    private String asRequestName(final String summary) {
        return Words.getTypeName(summary);
    }

    private String asMethodName(final String summary) {
        return Words.getVariableName(summary);
    }
//    public EndpointClazz
}
