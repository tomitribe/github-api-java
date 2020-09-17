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
package org.tomitribe.github.gen.openapi;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.tomitribe.github.core.JsonMarshalling;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OpenApi {
    @JsonbProperty("openapi")
    private String openapi;

    @JsonbProperty("info")
    private Info info;

    @JsonbProperty("tags")
    private List<Tag> tags;

    @JsonbProperty("servers")
    private List<Server> servers;

    @JsonbProperty("externalDocs")
    private ExternalDocs externalDocs;

    @JsonbProperty("paths")
    private Map<String, Path> paths;

    @JsonbProperty("components")
    private Components components;

    public static OpenApi parse(final String json) {
        final OpenApi openApi = JsonMarshalling.unmarshal(OpenApi.class, json);

        // Tell every Path instance what their actual path is
        for (final Map.Entry<String, Path> entry : openApi.paths.entrySet()) {
            entry.getValue().setName(entry.getKey());
        }

        // Tell every Method instance what kind of method it is
        for (final Path path : openApi.paths.values()) {
            if (path.getGet() != null) path.getGet().setName("get");
            if (path.getPut() != null) path.getPut().setName("put");
            if (path.getPost() != null) path.getPost().setName("post");
            if (path.getDelete() != null) path.getDelete().setName("delete");
            if (path.getTrace() != null) path.getTrace().setName("trace");
            if (path.getOptions() != null) path.getOptions().setName("options");
            if (path.getPatch() != null) path.getPatch().setName("patch");
        }

        // Link every Method path to its owning Path
        for (final Path path : openApi.paths.values()) {
            path.getMethods().forEach(method -> method.setPath(path));
        }

        // Tell every Response instance what its name is in the map
        openApi.paths.values().stream()
                .flatMap(Path::getMethods)
                .forEach(method -> {
                    for (final Map.Entry<String, Response> entry : method.getResponses().entrySet()) {
                        entry.getValue().setName(entry.getKey());
                    }
                });

        // Tell every response Content instance what its name is in the map
        openApi.paths.values().stream()
                .flatMap(Path::getMethods)
                .map(Method::getResponses)
                .filter(Objects::nonNull)
                .map(Map::values)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .filter(response -> response.getContent() != null)
                .forEach(response -> {
                    for (final Map.Entry<String, Content> entry : response.getContent().entrySet()) {
                        entry.getValue().setName(entry.getKey());
                    }
                });

        // Tell every requestBody Content instance what its name is in the map
        openApi.paths.values().stream()
                .flatMap(Path::getMethods)
                .map(Method::getRequestBody)
                .filter(Objects::nonNull)
                .filter(requestBody -> requestBody.getContent() != null)
                .forEach(requestBody -> {
                    for (final Map.Entry<String, Content> entry : requestBody.getContent().entrySet()) {
                        entry.getValue().setName(entry.getKey());
                    }
                });

        // Tell every Schema in componets what its name is in the map
        final Map<String, Schema> schemas = openApi.getComponents().getSchemas();
        for (final Map.Entry<String, Schema> entry : schemas.entrySet()) {
            entry.getValue().setName(entry.getKey());
        }

        return openApi;
    }

    @JsonbTransient
    public Stream<Content> getContents() {
        final Stream<Content> responseContent = this.getPaths().values().stream()
                .flatMap(Path::getMethods)
                .map(Method::getResponses)
                .filter(Objects::nonNull)
                .map(Map::values)
                .flatMap(Collection::stream)
                .map(Response::getContent)
                .filter(Objects::nonNull)
                .map(Map::values)
                .flatMap(Collection::stream);

        final Stream<Content> requestContent = this.getPaths().values().stream()
                .flatMap(Path::getMethods)
                .map(Method::getRequestBody)
                .filter(Objects::nonNull)
                .map(RequestBody::getContent)
                .filter(Objects::nonNull)
                .map(Map::values)
                .flatMap(Collection::stream);

        return Stream.concat(responseContent, requestContent);
    }

    @JsonbTransient
    public Stream<ExampleReference> getExampleReferences() {
        return getContents()
                .flatMap(this::getExampleReferences)
                .distinct()
                .sorted(Comparator.comparing(ExampleReference::getComponent));
    }

    @JsonbTransient
    public Stream<Method> getMethods() {
        return getPaths().values().stream()
                .flatMap(Path::getMethods);
    }

    @JsonbTransient
    private Stream<ExampleReference> getExampleReferences(final Content content) {
        final Schema schema = content.getSchema();
        if (schema == null) return Stream.of();

        final Map<String, Object> examples = content.getExamples();
        if (examples == null) return Stream.of();

        final List<String> exampleNames = examples.values().stream()
                .map(Map.class::cast)
                .map(map -> map.get("$ref"))
                .filter(Objects::nonNull)
                .map(String.class::cast)
                .map(OpenApi::getRefName)
                .collect(Collectors.toList());

        if ("array".equals(schema.getType())) {
            final Schema items = schema.getItems();
            if (items == null) throw new IllegalStateException("Array type missing items");

            final String componentRef = items.getRef();
            if (componentRef == null) return Stream.of();

            final String component = getRefName(componentRef);
            return exampleNames.stream()
                    .map(s -> new ExampleReference(component, null, s, true));
        }

        if (schema.getRef() != null) {
            final String componentRef = schema.getRef();
            if (componentRef == null) return Stream.of();

            final String component = getRefName(componentRef);
            return exampleNames.stream()
                    .map(s -> new ExampleReference(component, null, s, false));
        }

        if ("object".equals(schema.getType()) &&
                exampleNames.stream().anyMatch(s -> s.contains("paginated"))) {
            final Schema array = schema.getProperties().values().stream()
                    .filter(property -> "array".equals(property.getType()))
                    .findFirst().orElseThrow(() -> new IllegalStateException("Missing items array"));

            final Schema items = array.getItems();
            if (items == null) throw new IllegalStateException("Missing array type");

            final String ref = items.getRef();
            if (ref == null) throw new IllegalStateException("Missing items ref");

            final String item = getRefName(ref);
            final String page = item + "-page";
            return exampleNames.stream()
                    .map(s -> new ExampleReference(page, item, s, false));

        }

        return Stream.of();
    }

    public static String getRefName(final String ref) {
        return ref.replaceAll(".*/", "");
    }

}
