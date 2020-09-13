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

import org.tomitribe.github.gen.openapi.ExampleReference;
import org.tomitribe.github.gen.openapi.OpenApi;
import org.tomitribe.util.IO;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

public class MatchExamplesWithContents {

    public static void main(String[] args) throws Exception {
        new MatchExamplesWithContents().main();
    }

    private void main() throws Exception {
        final Project project = Project.root();
        final Resources resources = project.src().test().resources();
        final Dir examplesOpenapi = resources.examplesOpenapi();

        final Gen gen = resources.gen();

        final OpenApi openApi = OpenApi.parse(IO.slurp(gen.getGithubOpenApiJson()));

        try (final PrintStream out = IO.print(gen.file("found-examples.txt"))) {
            openApi.getExampleReferences()
                    .map(ExampleReference::getExample)
                    .map(s -> "/components/examples/" + s)
                    .sorted()
                    .forEach(out::println);
        }

        final List<ExampleReference> exampleReferences = openApi.getExampleReferences()
                .collect(Collectors.toList());
        for (final ExampleReference exampleReference : exampleReferences) {
            System.out.printf("%s  ->  %s%n", exampleReference.getComponent(), exampleReference.getExample());
        }
    }


}