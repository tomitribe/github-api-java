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
package org.tomitribe.github.gen.code.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.tomitribe.github.gen.util.Words;

@Data
@AllArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
public class Field {
    private String name;
    private String jsonName;
    private Name type;
    @lombok.Builder.Default
    private In in = In.BODY;
    private boolean collection;
    private boolean map;
    private String reference;

    public enum In {
        PATH,
        QUERY,
        HEADER,
        BODY;
    }

    public static Field.Builder field(final String jsonName, final Name type) {
        if (jsonName == null) {
            return new Builder().type(type);
        } else {
            final String javaName = Words.getVariableName(jsonName);
            return new Builder().jsonName(jsonName).type(type).name(javaName);
        }
    }

}
