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
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
            if (path.get != null) path.get.setName("get");
            if (path.put != null) path.put.setName("put");
            if (path.post != null) path.post.setName("post");
            if (path.delete != null) path.delete.setName("delete");
            if (path.trace != null) path.trace.setName("trace");
            if (path.options != null) path.options.setName("options");
            if (path.patch != null) path.patch.setName("patch");
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

        return openApi;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Components {

        @JsonbProperty("schemas")
        private Map<String, Schema> schemas;

        @JsonbProperty("examples")
        private Map<String, Example> examples;

        @JsonbProperty("responses")
        private Map<String, Response> responses;

        @JsonbProperty("headers")
        private Map<String, Header> headers;

        @JsonbProperty("parameters")
        private Map<String, Parameter> parameters;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Example {
        @JsonbProperty("value")
        private Object value;

        @JsonbProperty("summary")
        private String summary;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Path {
        @JsonbTransient
        private String name;

        @JsonbProperty("get")
        private Method get;

        @JsonbProperty("put")
        private Method put;

        @JsonbProperty("post")
        private Method post;

        @JsonbProperty("delete")
        private Method delete;

        @JsonbProperty("trace")
        private Method trace;

        @JsonbProperty("options")
        private Method options;

        @JsonbProperty("patch")
        private Method patch;

        public Stream<Method> getMethods() {
            return Stream.of(
                    get,
                    patch,
                    post,
                    put,
                    delete,
                    trace,
                    options
            ).filter(Objects::nonNull);
        }
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Method {
        @JsonbTransient
        private String name;

        @JsonbProperty("summary")
        private String summary;

        @JsonbProperty("description")
        private String description;

        @JsonbProperty("tags")
        private List<String> tags;

        @JsonbProperty("responses")
        private Map<String, Response> responses;

        @JsonbProperty("operationId")
        private String operationId;

        @JsonbProperty("x-github")
        private Github github;

        @JsonbProperty("externalDocs")
        private ExternalDocs externalDocs;

        @JsonbProperty("parameters")
        private List<Parameter> parameters;

        @JsonbProperty("deprecated")
        private Boolean deprecated;

        @JsonbProperty("requestBody")
        private RequestBody requestBody;

        @JsonbProperty("servers")
        private List<Server> servers;

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Ref {
        @JsonbProperty("$ref")
        private String ref;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Preview {
        @JsonbProperty("name")
        private String name;

        @JsonbProperty("note")
        private String note;

        @JsonbProperty("required")
        private Boolean required;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Parameter {
        @JsonbProperty("name")
        private String name;

        @JsonbProperty("description")
        private String description;

        @JsonbProperty("in")
        private String in;

        @JsonbProperty("required")
        private Boolean required;

        @JsonbProperty("schema")
        private Schema schema;

        @JsonbProperty("$ref")
        private String ref;

        @JsonbProperty("example")
        private Object example;

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Schema {
        @JsonbProperty("type")
        private String type;

        @JsonbProperty("properties")
        private Map<String, Schema> properties;

        @JsonbProperty("required")
        private List<String> required;

        @JsonbProperty("$ref")
        private String ref;

        @JsonbProperty("items")
        private Schema items;

        @JsonbProperty("allOf")
        private List<Schema> allOf;

        @JsonbProperty("anyOf")
        private List<Schema> anyOf;

        @JsonbProperty("oneOf")
        private List<Schema> oneOf;

        @JsonbProperty("nullable")
        private Boolean nullable;

        @JsonbProperty("additionalProperties")
        private Object additionalProperties;

        @JsonbProperty("enum")
        private List<String> enumValues;

        @JsonbProperty("default")
        private Object defaultValue;

        @JsonbProperty("example")
        private Object example;

        @JsonbProperty("format")
        private String format;

        @JsonbProperty("description")
        private String description;

        @JsonbProperty("title")
        private String title;

        @JsonbProperty("maxLength")
        private Long maxLength;

        @JsonbProperty("minLength")
        private Long minLength;

        @JsonbProperty("minimum")
        private Long minimum;

        @JsonbProperty("maximum")
        private Long maximum;

        @JsonbProperty("minItems")
        private Long minItems;

        @JsonbProperty("pattern")
        private String pattern;

        @JsonbProperty("maxProperties")
        private Long maxProperties;

        @JsonbProperty("readOnly")
        private Boolean readOnly;

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class SchemaItems {
        @JsonbProperty("$ref")
        private String ref;

        @JsonbProperty("type")
        private String type;


    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Github {
        @JsonbProperty("githubCloudOnly")
        private Boolean githubCloudOnly;

        @JsonbProperty("enabledForGitHubApps")
        private Boolean enabledForGitHubApps;

        @JsonbProperty("previews")
        private List<Preview> previews;

        @JsonbProperty("category")
        private String category;

        @JsonbProperty("deprecationDate")
        private String deprecationDate;

        @JsonbProperty("removalDate")
        private String removalDate;

        @JsonbProperty("subcategory")
        private String subcategory;

        @JsonbProperty("requestBodyParameterName")
        private String requestBodyParameterName;

        @JsonbProperty("triggersNotification")
        private Boolean triggersNotification;

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Header {
        @JsonbProperty("$ref")
        private String ref;

        @JsonbProperty("example")
        private String example;

        @JsonbProperty("schema")
        private Schema schema;


    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Response {
        @JsonbTransient
        private String name;

        @JsonbProperty("description")
        private String description;

        @JsonbProperty("content")
        private Map<String, Content> content;

        @JsonbProperty("$ref")
        private String ref;

        @JsonbProperty("headers")
        private Map<String, Header> headers;

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Content {
        @JsonbTransient
        private String name;

        @JsonbProperty("schema")
        private Schema schema;

        @JsonbProperty("examples")
        private Map<String, Object> examples;

        @JsonbProperty("example")
        private Object example;

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class RequestBody {

        @JsonbProperty("content")
        private Map<String, Content> content;

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class ExternalDocs {
        @JsonbProperty("description")
        private String description;

        @JsonbProperty("url")
        private String url;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Server {
        @JsonbProperty("url")
        private String url;

        @JsonbProperty("variables")
        private Map<String, Object> variables;

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Info {
        @JsonbProperty("version")
        private String version;

        @JsonbProperty("title")
        private String title;

        @JsonbProperty("description")
        private String description;

        @JsonbProperty("license")
        private Map<String, Object> license;

        @JsonbProperty("termsOfService")
        private String termsOfService;

        @JsonbProperty("contact")
        private Map<String, Object> contact;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Tag {
        @JsonbProperty("name")
        private String name;

        @JsonbProperty("description")
        private String description;

    }

}
