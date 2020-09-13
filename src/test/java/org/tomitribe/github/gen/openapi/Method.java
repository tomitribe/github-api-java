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
import javax.json.bind.annotation.JsonbTransient;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Method {
    @JsonbTransient
    private Path path;

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
