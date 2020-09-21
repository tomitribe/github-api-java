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
package org.tomitribe.github.gen;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.tomitribe.github.core.JsonMarshalling;
import org.tomitribe.github.gen.html.Endpoint;
import org.tomitribe.github.gen.openapi.Method;
import org.tomitribe.github.gen.openapi.OpenApi;
import org.tomitribe.util.IO;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MethodNames {

    public static void main(String[] args) throws IOException {
        final Gen gen = Project.root().src().test().resources().gen();
        gen.getGithubOpenApiJson();

        final OpenApi openApi = OpenApi.parse(IO.slurp(gen.getGithubOpenApiJson()));
        final Map<String, Name> openapiNames = openApi.getMethods()
                .map(Name::from)
                .collect(Collectors.toMap(Name::getTarget, Function.identity()));

        final Map<String, Name> htmlNames = gen.json()
                .files()
                .flatMap(MethodNames::parse)
                .map(MethodNames::toName)
                .collect(Collectors.toMap(Name::getTarget, Function.identity()));

        for (final Name api : openapiNames.values()) {
            final Name html = htmlNames.get(api.getTarget());
            if (html == null) {
                System.out.println("MISSING: " + api.getTarget());
                continue;
            }

            final String summary = api.getSummary().replace("(", "").replace(")", "");
            if (summary.equalsIgnoreCase(html.getSummary())) continue;
            System.out.printf("%-60s %-60s %s%n", api.target, api.summary, html.summary);
        }
    }

    private static Name toName(final Endpoint endpoint) {
        final String target = endpoint.getTarget().getMethod().toUpperCase() + " " + endpoint.getTarget().getPath();
        return new Name(target, endpoint.getId().replace("-", " ").toLowerCase());
    }

    private static Stream<Endpoint> parse(final File file) {
        final Endpoint[] endpoints = JsonMarshalling.unmarshal(Endpoint[].class, file);
        return Stream.of(endpoints);
    }

    @Data
    @AllArgsConstructor
    public static class Name {
        private final String target;
        private final String summary;

        public static Name from(final Method method) {
            final String target = String.format("%s %s", method.getName().toUpperCase(), method.getPath().getName());

            return new Name(target, method.getSummary().toLowerCase());
        }
    }
}
