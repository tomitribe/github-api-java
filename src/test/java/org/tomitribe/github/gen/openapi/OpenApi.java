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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

}
