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
public class Schema {

    @JsonbTransient
    private String name;
    
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
