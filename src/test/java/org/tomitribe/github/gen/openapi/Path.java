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
import java.util.Objects;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Path {
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

    @JsonbTransient
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

    @Override
    public String toString() {
        return name;
    }
}
