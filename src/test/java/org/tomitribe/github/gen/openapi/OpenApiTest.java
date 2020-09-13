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
package org.tomitribe.github.gen.openapi;

import lombok.Data;
import org.junit.Test;
import org.tomitribe.github.JsonAsserts;
import org.tomitribe.github.gen.html.Target;
import org.tomitribe.github.model.PayloadAsserts;
import org.tomitribe.util.IO;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.Collections.EMPTY_LIST;
import static org.junit.Assert.assertEquals;

public class OpenApiTest {

    @Test
    public void test() throws Exception {
        final String expected = getOpenApiJson();
        JsonAsserts.assertJsonb(expected, OpenApi.class);
    }

    @Data
    public static class Endpoint {
        private final Github github;
        private final List<Preview> previews;
        private Method method;
        private Response response;
        private Target target;

        public Endpoint(final Method method) {
            this.method = method;
            this.github = method.getGithub();
            this.previews = github.getPreviews() == null ? EMPTY_LIST : github.getPreviews();

            this.response = method.getResponses().values().stream()
                    .filter(r -> r.getName().startsWith("2"))
                    .min(Comparator.comparing(Response::getName))
                    .orElse(null);
            this.target = new Target(method.getName(), method.getPath().getName());
        }

        public String getCategory() {
            return github.getCategory();
        }

        public String getSubcategory() {
            return github.getSubcategory();
        }

        public List<Preview> getPreviews() {
            return previews;
        }

        @Override
        public String toString() {
            return "Resource{" +
                    "target='" + target +
                    "', response=" + response +
                    '}';
        }
    }

    @Test
    public void pathNames() throws Exception {
        final OpenApi openApi = OpenApi.parse(getOpenApiJson());

        for (final Map.Entry<String, Path> entry : openApi.getPaths().entrySet()) {
            assertEquals(entry.getKey(), entry.getValue().getName());
        }
    }

    public String getOpenApiJson() throws IOException {
        final ClassLoader loader = PayloadAsserts.class.getClassLoader();
        final URL resource = loader.getResource("gen/api.github.com.json");
        return IO.slurp(resource);
    }

}
