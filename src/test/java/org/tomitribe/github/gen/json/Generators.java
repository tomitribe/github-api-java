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

import org.apache.openejb.util.URLs;
import org.tomitribe.util.IO;
import org.tomitribe.util.Strings;

import javax.json.JsonObject;
import javax.json.JsonReaderFactory;
import javax.json.JsonValue;
import javax.json.spi.JsonProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static javax.json.JsonValue.ValueType.OBJECT;

public class Generators {

    private Generators() {
    }

    public static Map<String, JsonValue> collectRecursively(final JsonObject json) {

        final HashMap<String, JsonValue> fields = new LinkedHashMap<>();
        for (final Map.Entry<String, JsonValue> entry : json.entrySet()) {
            // Add the field
            fields.put(entry.getKey(), entry.getValue());

            // if it is an object, recurse
            if (entry.getValue().getValueType().equals(OBJECT)) {
                fields.putAll(collectRecursively((JsonObject) entry.getValue()));
            }
        }

        return fields;
    }

    public static JsonObject parse(File jsonFile) {
        try {
            final JsonProvider provider = JsonProvider.provider();
            final JsonReaderFactory readerFactory = provider.createReaderFactory(Collections.EMPTY_MAP);
            return readerFactory.createReader(IO.read(jsonFile)).readObject();
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    public static JsonObject parse(final String jsonFile) {
        final JsonProvider provider = JsonProvider.provider();
        final JsonReaderFactory readerFactory = provider.createReaderFactory(Collections.EMPTY_MAP);
        return readerFactory.createReader(IO.read(jsonFile)).readObject();
    }

    public static File getParentFile(final String name) {
        return getFile(name).getParentFile();
    }

    public static File getFile(String name) {
        return URLs.toFile(Generators.class.getClassLoader().getResource(name));
    }
}
