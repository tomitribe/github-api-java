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

import org.junit.Test;
import org.tomitribe.github.Resources;
import org.tomitribe.github.core.JsonMarshalling;
import org.tomitribe.github.gen.code.model.Clazz;
import org.tomitribe.github.gen.code.model.ClazzReference;
import org.tomitribe.github.gen.code.model.ClazzRenderer;
import org.tomitribe.github.gen.openapi.Schema;
import org.tomitribe.util.Files;
import org.tomitribe.util.IO;
import org.tomitribe.util.dir.Dir;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.tomitribe.github.gen.ProjectAsserts.assertProject;

public class ModelGeneratorTest {

    private final Dir resources = Resources.resources(ModelGeneratorTest.class);

    @Test
    public void simple() throws Exception {
        assertScenario("simple", "pull-request-minimal.json", "pull-request-minimal");
    }

    @Test
    public void enums() throws Exception {
        assertScenario("enums", "enums.json", "enums");
    }

    @Test
    public void arrays() throws Exception {
        assertScenario("arrays", "arrays.json", "arrays");
    }

    @Test
    public void classReference() throws Exception {
        final String content = "{\n" +
                "  \"$ref\": \"#/components/schemas/integration\"\n" +
                "}";
        final Schema schema = JsonMarshalling.unmarshal(Schema.class, content);

        final ModelGenerator modelGenerator = new ModelGenerator();

        final Clazz clazz = modelGenerator.build(schema);

        assertTrue(clazz instanceof ClazzReference);

    }

    public void assertScenario(final String simple, final String name, final String name1) throws IOException {
        final Dir test = this.resources.dir(simple);

        final String content = IO.slurp(test.file(name));

        final Schema schema = JsonMarshalling.unmarshal(Schema.class, content);
        schema.setName(name1);

        final ModelGenerator modelGenerator = new ModelGenerator();

        modelGenerator.build(schema);

        final Project actual = Project.from(Files.tmpdir());
        final Project expected = Project.from(test.file("after"));

        final ClazzRenderer renderer = new ClazzRenderer(actual, "org.tomitribe.github.model");

        for (final Clazz aClass : modelGenerator.getClasses()) {
            renderer.render(aClass);
        }

        assertProject(expected, actual);
    }

}
