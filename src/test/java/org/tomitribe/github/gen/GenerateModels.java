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

import org.tomitribe.github.gen.code.model.Clazz;
import org.tomitribe.github.gen.openapi.OpenApi;
import org.tomitribe.github.gen.openapi.Schema;
import org.tomitribe.util.IO;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GenerateModels {

    private final Map<String, Clazz> classes = new HashMap<>();
    private final Map<String, Clazz> refs = new HashMap<>();
    private Map<String, Schema> schemas;
    private final OpenApi openApi;

    public static void main(String[] args) throws Exception {
        new GenerateModels().main();
    }

    private final Project project = Project.root();

    public GenerateModels() throws IOException {
        final Gen gen = project.src().test().resources().gen();

        this.openApi = OpenApi.parse(IO.slurp(gen.getGithubOpenApiJson()));
        this.schemas = openApi.getComponents().getSchemas();
    }

    private void main() throws Exception {

        final ModelGenerator modelGenerator = new ModelGenerator();

        final Collection<Schema> schemas = this.schemas.values();

        // Make initial objects for all top-level components
        for (final Schema schema : schemas) {
            final String id = getComponentId(schema);

            final Clazz clazz = modelGenerator.build(schema);
            clazz.addComponentId(id);
        }

        for (final Clazz clazz : classes.values()) {
            System.out.println(clazz);
        }
    }

    private String getComponentId(final Schema schema) {
        return "#/components/schemas/" + schema.getName();
    }

}
