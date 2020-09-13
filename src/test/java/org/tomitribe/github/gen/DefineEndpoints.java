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

import lombok.Data;
import org.tomitribe.github.gen.openapi.Content;
import org.tomitribe.github.gen.openapi.OpenApi;
import org.tomitribe.github.gen.openapi.OpenApiTest;
import org.tomitribe.github.gen.openapi.Path;
import org.tomitribe.github.gen.openapi.Response;
import org.tomitribe.github.gen.openapi.Schema;
import org.tomitribe.util.IO;
import org.tomitribe.util.Join;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefineEndpoints {

    private final Project project = Project.root();
    private final List<Model> models;

    public DefineEndpoints() {
        models = project.src().main().java().model()
                .files()
                .map(Model::parse)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {
        new DefineEndpoints().main();
    }

    public static final Map<String, String> responseTypes = new HashMap<>();

    static {
        responseTypes.put("post /markdown", "String");
        responseTypes.put("put /orgs/{org}/actions/secrets/{secret_name}", "void");
        responseTypes.put("post /repos/{owner}/{repo}/actions/runs/{run_id}/cancel", "void");
        responseTypes.put("post /repos/{owner}/{repo}/actions/runs/{run_id}/rerun", "void");
        responseTypes.put("put /repos/{owner}/{repo}/actions/secrets/{secret_name}", "void");
        responseTypes.put("post /repos/{owner}/{repo}/check-suites/{check_suite_id}/rerequest", "void");
        responseTypes.put("put /repos/{owner}/{repo}/notifications", "void");
        responseTypes.put("delete /repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers", "void");
        responseTypes.put("put /orgs/{org}/outside_collaborators/{username}", "void");
    }

    @Data
    public static class Model {
        private final String name;
        private final List<String> properties = new ArrayList<String>();

        public Model(final String name, final List<String> properties) {
            this.name = name;
            this.properties.addAll(properties);
        }

        public static Model parse(final File sourceFile) {

            try {
                final List<String> properties = Stream.of(IO.slurp(sourceFile).split("\n"))
                        .filter(s -> s.contains("@JsonbProperty("))
                        .map(s -> s.replaceAll(".*@JsonbProperty\\(\"(.*)\"\\).*", "$1"))
                        .collect(Collectors.toList());

                final String name = sourceFile.getName().replace(".java", "");
                return new Model(name, properties);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }

        }
    }

    private void main() throws IOException {
        final Gen gen = project.src().test().resources().gen();


        final OpenApi openApi = OpenApi.parse(IO.slurp(gen.getGithubOpenApiJson()));
        final List<OpenApiTest.Endpoint> endpoints = openApi.getPaths().values().stream()
                .flatMap(Path::getMethods)
                .map(OpenApiTest.Endpoint::new)
                .filter(endpoint -> endpoint.getResponse() != null)
                .collect(Collectors.toList());

        for (final OpenApiTest.Endpoint endpoint : endpoints) {
            final Response response = endpoint.getResponse();

            if (response.getName().equals("204")) continue;

            final Map<String, Content> contentTypes = response.getContent();
            if (contentTypes == null) continue;

            final Content applicationJson = contentTypes.get("application/json");
            if (applicationJson == null) continue;

            final Schema schema = applicationJson.getSchema();
            if (schema == null) {
                System.out.println("MISSING SCHEMA: " + endpoint.getTarget());
            }

            final String ref = schema.getRef();
            if (ref == null) {
                final Model model = guessModel(schema);

                System.out.println("MISSING REF: " + endpoint.getTarget());
            }
        }
    }

    private Model guessModel(final Schema schema) {

        final Map<String, Schema> properties = schema.getProperties();
        if (properties == null) {
            throw new IllegalStateException("Schema has no properties: " + schema);
        }
        final Set<String> keys = properties.keySet();

        final List<Model> matching = models.stream()
                .filter(model -> model.properties.containsAll(keys))
                .collect(Collectors.toList());

        if (matching.size() > 1) {
            final List<String> names = matching.stream().map(Model::getName)
                    .collect(Collectors.toList());

            final String message = String.format("The schema keys match several models: %nkeys%s%nmodels:%s%n", Join.join(", ", keys), Join.join(", ", names));
            throw new IllegalStateException(message);
        }
        return null;
    }
}
