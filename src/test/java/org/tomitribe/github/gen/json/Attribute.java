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
package org.tomitribe.github.gen.json;

import org.tomitribe.github.gen.util.Words;

import javax.json.JsonValue;

import static org.tomitribe.github.gen.util.Words.getVariableName;

public class Attribute implements Comparable<Attribute> {
    private final String name;
    private final JsonValue value;
    private final String javaName;

    public Attribute(String name, JsonValue value) {
        this(name, value, getVariableName(name));
    }

    public Attribute(String name, JsonValue value, String javaName) {
        this.name = name;
        this.value = value;
        this.javaName = javaName;
    }

    public String getName() {
        return name;
    }

    public JsonValue getValue() {
        return value;
    }

    public String getJavaName() {
        return javaName;
    }

    @Override
    public int compareTo(Attribute o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attribute attribute = (Attribute) o;

        if (!name.equals(attribute.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
