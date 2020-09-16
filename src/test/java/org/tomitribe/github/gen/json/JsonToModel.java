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

import org.tomitribe.github.gen.ParseEvents;
import org.tomitribe.github.gen.util.Words;
import org.tomitribe.util.Strings;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.json.JsonValue.ValueType.ARRAY;
import static javax.json.JsonValue.ValueType.FALSE;
import static javax.json.JsonValue.ValueType.NUMBER;
import static javax.json.JsonValue.ValueType.OBJECT;
import static javax.json.JsonValue.ValueType.TRUE;

public class JsonToModel {

    private String currentType;
    private final Map<String, Clazz> classes = new HashMap<>();

    public static List<Clazz> generate(final List<ParseEvents.Event> events) {
        final JsonToModel generator = new JsonToModel();
        events.stream().forEach(generator::generate);
        return new ArrayList<>(generator.classes.values());
    }

    private void generate(final ParseEvents.Event event) {
        try {
            currentType = event.getTypeName();
            final Clazz clazz = new Clazz(currentType, event);
            classes.put(clazz.getName(), clazz);
            final JsonObject jsonObject = Generators.parse(event.getPayload().getJson());
            process(clazz, jsonObject);
        } catch (Exception e) {
            System.err.println(event.getTypeName());
            throw new IllegalStateException(e);
        }
    }

    public void process(final Clazz self, final JsonObject json) {
        for (final Map.Entry<String, JsonValue> entry : json.entrySet()) {

            final JsonValue value = entry.getValue();
            final String key = entry.getKey();

            final Field field = self.getFields().computeIfAbsent(key, s -> new Field(key, value));

            if (currentType != null) field.addUsedBy(currentType);

            // if it is an object, recurse
            if (value.getValueType().equals(OBJECT)) {
                final String childName = getClassName(key, self);
                final Clazz child = getClazz(childName);
                field.setType(child.getName());

                if (currentType != null) child.addUsedBy(currentType);

                process(child, (JsonObject) value);
            }

            if (value.getValueType().equals(ARRAY)) {
                final JsonArray array = (JsonArray) value;
                if (array.size() == 0) continue;

                final JsonValue.ValueType childType = getArrayType(array);

                if (childType.equals(OBJECT)) {
                    for (final JsonValue jsonValue : array) {
                        final String childName = getClassName(key, self, true);
                        final Clazz child = getClazz(childName);
                        field.setType("List<" + child.getName() + ">");

                        if (currentType != null) child.addUsedBy(currentType);

                        process(child, (JsonObject) jsonValue);
                    }
                } else if (childType.equals(FALSE) || childType.equals(TRUE)) {
                    field.setType("boolean[]");
                } else if (childType.equals(NUMBER)) {
                    field.setType("long[]");
                }
            }

        }
    }

    public static JsonValue.ValueType getArrayType(JsonArray array) {
        final JsonValue child = array.get(0);
        return child.getValueType();
    }

    private String getClassName(final String jsonName, final Clazz parent) {
        return getClassName(jsonName, parent, false);
    }

    private String getClassName(final String jsonName, final Clazz parent, final boolean isArray) {
        String name = Strings.ucfirst(Words.getVariableName(jsonName));
        if ("Params".equals(name)) {
            name = parent.getName() + name;
        }
        if (isArray) {
            if (name.endsWith("Axes")) return name.replace("Axes", "Axis");
            if (name.endsWith("Branches")) return "Branch";
            if (name.endsWith("Vulnerabilities")) return "Vulnerability";
            if (name.endsWith("Repositories")) return "RepositoryRef";
            if (name.endsWith("s")) return name.substring(0, name.length() - 1);
        }
        return name;
    }

    private Clazz getClazz(String name) {
        return classes.computeIfAbsent(name, Clazz::new);
    }


}
