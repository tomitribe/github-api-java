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

import org.tomitribe.util.Strings;

import javax.json.JsonValue;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static javax.json.JsonValue.ValueType.FALSE;
import static javax.json.JsonValue.ValueType.NUMBER;
import static javax.json.JsonValue.ValueType.OBJECT;
import static javax.json.JsonValue.ValueType.STRING;
import static javax.json.JsonValue.ValueType.TRUE;

public class AttributeAccessor {

    private static final AttributeAccessor INSTANCE = new AttributeAccessor();

    public static AttributeAccessor get() {
        return INSTANCE;
    }

    final Map<JsonValue.ValueType, Function<Attribute, String>> generators = new HashMap<>();

    public AttributeAccessor() {
        generators.put(NUMBER, this::numericAccessor);
        generators.put(STRING, this::stringAccessor);
        generators.put(OBJECT, this::objectAccessor);
        generators.put(FALSE, this::booleanAccessor);
        generators.put(TRUE, this::booleanAccessor);
    }

    public Function<Attribute, String> getGenerator(final Attribute attribute) {
        return generators.get(attribute.getValue().getValueType());
    }

    public String numericAccessor(Attribute attribute) {
        if (attribute.getValue().toString().contains(".")) {
            return doubleAccessor(attribute);
        } else {
            return longAccessor(attribute);
        }
    }

    public String has(Attribute attribute) {
        return String.format("" +
                "    public boolean has%s() {\n" +
                "        try {\n" +
                "            %s();\n" +
                "            return true;\n" +
                "        } catch (NullPointerException e) {\n" +
                "            return false;\n" +
                "        }\n" +
                "    }\n", Strings.ucfirst(attribute.getJavaName()), attribute.getJavaName());
    }

    public String stringAccessor(Attribute attribute) {
        return String.format("" +
                "    public String %s() {\n" +
                "        return object.getString(\"%s\");\n" +
                "    }\n", attribute.getJavaName(), attribute.getName());
    }

    public String objectAccessor(Attribute attribute) {
        return String.format("" +
                "    public JsonObject %s() {\n" +
                "        return object.getJsonObject(\"%s\");\n" +
                "    }\n", attribute.getJavaName(), attribute.getName());
    }

    public String booleanAccessor(Attribute attribute) {
        return String.format("" +
                "    public boolean %s() {\n" +
                "        return object.getBoolean(\"%s\");\n" +
                "    }\n", attribute.getJavaName(), attribute.getName());
    }

    public String doubleAccessor(Attribute attribute) {
        return String.format("" +
                "    public double %s() {\n" +
                "        return object.getJsonNumber(\"%s\").doubleValue();\n" +
                "    }\n", attribute.getJavaName(), attribute.getName());
    }

    public String longAccessor(Attribute attribute) {
        return String.format("" +
                "    public long %s() {\n" +
                "        return object.getJsonNumber(\"%s\").longValue();\n" +
                "    }\n", attribute.getJavaName(), attribute.getName());
    }
}
