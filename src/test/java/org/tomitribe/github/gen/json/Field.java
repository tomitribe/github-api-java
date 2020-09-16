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
import java.util.Set;
import java.util.TreeSet;

public class Field {
    private String name;
    private String jsonName;
    private String type;
    private Set<String> usedBy = new TreeSet<>();

    public Field(String jsonName, JsonValue value) {
        this.jsonName = jsonName;
        this.name = Words.getVariableName(jsonName);
        this.type = toJavaType(value);
    }

    public void addUsedBy(final String type) {
        this.usedBy.add(type);
    }

    public Set<String> getUsedBy() {
        return usedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJsonName() {
        return jsonName;
    }

    public void setJsonName(String jsonName) {
        this.jsonName = jsonName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private static String toJavaType(JsonValue value) {
        switch (value.getValueType()) {
            case OBJECT:
                return "Object";
            case ARRAY:
                return "String[]";
            case FALSE:
                return "Boolean";
            case TRUE:
                return "Boolean";
            case NULL:
                return "String";
            case NUMBER:
                return value.toString().contains(".") ? "Double" : "Long";
            case STRING:
                return "String";
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (!name.equals(field.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
