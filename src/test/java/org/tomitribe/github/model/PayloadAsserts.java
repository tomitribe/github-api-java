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
package org.tomitribe.github.model;

import org.tomitribe.util.IO;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.io.IOException;
import java.net.URL;

import static org.tomitribe.github.JsonAsserts.assertJson;

public class PayloadAsserts {

    private PayloadAsserts() {
    }

    public static void assertPayload(final Class<?> eventClass) throws IOException {
        final ClassLoader loader = PayloadAsserts.class.getClassLoader();
        final URL resource = loader.getResource("payloads/" + eventClass.getSimpleName() + ".json");
        final String expected = IO.slurp(resource);

        final JsonbConfig config = new JsonbConfig().withFormatting(true);

        // Create Jsonb with custom configuration
        final Jsonb jsonb = JsonbBuilder.create(config);

        final Object object = jsonb.fromJson(expected, eventClass);
        final String actual = jsonb.toJson(object);

        assertJson(expected, actual);
    }
}
