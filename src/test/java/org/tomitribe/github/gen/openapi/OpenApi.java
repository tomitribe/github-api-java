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

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;
import java.util.Map;

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
    private Map<String, Object> components;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Path {
        @JsonbProperty("get")
        private Method get;

        @JsonbProperty("put")
        private Object put;

        @JsonbProperty("post")
        private Object post;

        @JsonbProperty("delete")
        private Object delete;

        @JsonbProperty("trace")
        private Object trace;

        @JsonbProperty("options")
        private Object options;

        @JsonbProperty("patch")
        private Object patch;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Method {
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
        private Map<String, Property> properties;

        @JsonbProperty("required")
        private List<String> required;

        @JsonbProperty("$ref")
        private String ref;

        @JsonbProperty("items")
        private SchemaItems items;

        @JsonbProperty("allOf")
        private List<Ref> allOf;

        @JsonbProperty("oneOf")
        private List<Ref> oneOf;

        @JsonbProperty("nullable")
        private Boolean nullable;

        @JsonbProperty("additionalProperties")
        private Object additionalProperties;

        @JsonbProperty("enum")
        private List<String> enumValues;

        @JsonbProperty("default")
        private String defaultValue;

        @JsonbProperty("example")
        private Object example;

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
        @JsonbProperty("schema")
        private Schema schema;

        @JsonbProperty("examples")
        private Map<String, Object> examples;

        @JsonbProperty("example")
        private List<String> example;

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Property {
        @JsonbProperty("format")
        private String format;

        @JsonbProperty("type")
        private String type;

        @JsonbProperty("allOf")
        private List<Ref> allOf;

        @JsonbProperty("items")
        private SchemaItems items;

        @JsonbProperty("example")
        private Object example;


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
