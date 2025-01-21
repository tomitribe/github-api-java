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

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class EnumClazz extends Clazz {
    final List<String> values;

    public EnumClazz(final Name name, final List<String> values) {
        super(null, name, null, null);
        this.values = new ArrayList<>();
        if (values != null) {
            this.values.addAll(values);
        }
    }

    public static EnumClazz of(final String type, final String... values) {
        return of(type, Arrays.asList(values));
    }

    public static EnumClazz of(final String type, final List<String> values) {
        if (values == null || values.size() == 0) throw new IllegalArgumentException("Enum requires at least one value");
        return new EnumClazz(Name.name(type), values);
    }

    public static String asConstant(final String value) {
        if (value.equals("+1")) return "PLUS_ONE";
        if (value.equals("-1")) return "MINUS_ONE";
        return value
                .replace("'", "")
                .replace("!", "")
                .replaceAll("[^A-Za-z0-9]", "_")
                .replaceAll("__+", "_")
                .replaceAll("^_|_$", "")
                .replaceAll("^([0-9])", "_$1")
                .toUpperCase();
    }
}
