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

    public EnumClazz(final String name, final List<String> values) {
        super(name, null);
        this.values = new ArrayList<>();
        if (values != null) {
            this.values.addAll(values);
        }
    }

    public static EnumClazz of(final String type, final String... values) {
        if (values == null || values.length == 0) throw new IllegalArgumentException("Enum requires at least one value");
        return new EnumClazz(type, Arrays.asList(values));
    }
}
