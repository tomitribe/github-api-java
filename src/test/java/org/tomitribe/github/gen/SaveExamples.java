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

import org.tomitribe.github.core.JsonMarshalling;
import org.tomitribe.github.gen.openapi.Example;
import org.tomitribe.github.gen.openapi.OpenApi;
import org.tomitribe.util.Files;
import org.tomitribe.util.IO;

import java.io.File;
import java.util.Map;

public class SaveExamples {

    public static void main(String[] args) throws Exception {
        new SaveExamples().main();
    }

    private void main() throws Exception {
        final Project project = Project.root();
        final Resources resources = project.src().test().resources();
        final Dir examplesOpenapi = resources.examplesOpenapi();

        final Gen gen = resources.gen();

        final OpenApi openApi = OpenApi.parse(IO.slurp(gen.getGithubOpenApiJson()));

        for (final Map.Entry<String, Example> entry : openApi.getComponents().getExamples().entrySet()) {
            final String key = entry.getKey();
            final Example example = entry.getValue();
            final Object value = example.getValue();

            final String json = JsonMarshalling.toFormattedJson(value);

            examplesOpenapi.write(key + ".json", json);
        }
    }
}