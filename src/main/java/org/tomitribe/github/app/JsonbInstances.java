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
package org.tomitribe.github.app;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

public class JsonbInstances {

    private JsonbInstances() {
    }

    private static final ThreadLocal<Jsonb> INSTANCES = ThreadLocal.withInitial(() -> {
        final JsonbConfig config = new JsonbConfig()
                .setProperty("johnzon.failOnMissingCreatorValues", false)
                .withFormatting(true);
        return JsonbBuilder.create(config);
    });

    public static Jsonb get() {
        return INSTANCES.get();
    }
}
