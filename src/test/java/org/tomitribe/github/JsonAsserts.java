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
package org.tomitribe.github;

import org.junit.Assert;
import org.tomitribe.util.IO;
import org.tomitribe.util.PrintString;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.junit.Assert.fail;

public class JsonAsserts {

    private JsonAsserts() {
    }

    public static void assertJson(final String expected, final String actual) {
        final String e = normalize(expected);
        final String a = normalize(actual);
        Assert.assertEquals(e, a);
    }

    private static String normalize(final String json) {
        final JsonReader reader = Json.createReader(IO.read(json));

        final JsonValue object = reader.readValue();
        final JsonValue jsonObject = sort(object);

        final Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        final JsonGeneratorFactory jgf = Json.createGeneratorFactory(properties);
        final PrintString out = new PrintString();
        final JsonGenerator jg = jgf.createGenerator(out);
        jg.write(jsonObject);
        jg.flush();

        return out.toString();
    }

    private static JsonValue sort(final JsonValue jsonValue) {
        if (jsonValue.getValueType().equals(JsonValue.ValueType.OBJECT)) {
            return sort(jsonValue.asJsonObject());
        } else if (jsonValue.getValueType().equals(JsonValue.ValueType.ARRAY)) {
            return sort(jsonValue.asJsonArray());
        } else {
            return jsonValue;
        }
    }

    private static JsonArray sort(final JsonArray jsonArray) {
        final JsonArrayBuilder copy = Json.createArrayBuilder();
        for (final JsonValue value : jsonArray) {
            copy.add(sort(value));
        }
        return copy.build();
    }

    private static JsonObject sort(final JsonObject jsonObject) {
        final JsonObjectBuilder copy = Json.createObjectBuilder();

        jsonObject.keySet().stream()
                .sorted()
                .filter(s1 -> !JsonValue.ValueType.NULL.equals(jsonObject.get(s1).getValueType()))
                .forEach(s -> {
                            final JsonValue sorted = sort(jsonObject.get(s));
                            copy.add(s, sorted);
                        }
                );

        return copy.build();
    }

    public static void assertJsonb(final String expectedJson, final Object actualObject) {
        final Jsonb jsonb = JsonbBuilder.create();
        final String actualJson = jsonb.toJson(actualObject);
        assertJson(expectedJson, actualJson);
    }

    public static <Object> void assertJsonb(final String expectedJson, final Class<Object> aClass) {
        {
            final Jsonb jsonb = JsonbBuilder.create();
            final Object object = jsonb.fromJson(expectedJson, aClass);
            final String actual = jsonb.toJson(object);
            assertJson(expectedJson, actual);
        }
    }

    public static <Object> void assertJsonb(final String json, final Class<Object> aClass, final Consumer<Object>... mutators) {
        // Unmarshal, marshal and assert both json documents are the same
        // We should not gain or lose any data
        {
            final Jsonb jsonb = JsonbBuilder.create();
            final Object object = jsonb.fromJson(json, aClass);
            final String actual = jsonb.toJson(object);
            assertJson(json, actual);
        }

        // Unmarshal, mutate the object, marshal and assert both json documents are NOT the same
        // We should not gain or lose any data
        for (final Consumer<Object> mutator : mutators) {
            try {
                final Jsonb jsonb = JsonbBuilder.create();
                final Object object = jsonb.fromJson(json, aClass);

                mutator.accept(object);

                final String actual = jsonb.toJson(object);
                assertJson(json, actual);

                fail("Expected a comparison failure");
            } catch (org.junit.ComparisonFailure expected) {
                // pass
            }
        }
    }

    public static void assertJsonbReadWrite(final Object data, final String expectedJson) {
        final Jsonb jsonb = JsonbBuilder.create();

        { // Assert write
            final String actualJson = jsonb.toJson(data);
            assertJson(expectedJson, actualJson);
        }

        { // Assert read
            final Object object = jsonb.fromJson(expectedJson, data.getClass());
            final String actualJson = jsonb.toJson(object);
            assertJson(expectedJson, actualJson);
        }
    }
}
