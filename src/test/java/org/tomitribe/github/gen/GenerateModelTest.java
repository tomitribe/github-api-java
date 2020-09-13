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

import org.junit.Ignore;
import org.junit.Test;
import org.tomitribe.github.Resources;
import org.tomitribe.github.core.JsonMarshalling;
import org.tomitribe.github.gen.code.model.Clazz;
import org.tomitribe.github.gen.code.model.ClazzRenderer;
import org.tomitribe.github.gen.openapi.Schema;
import org.tomitribe.util.Files;
import org.tomitribe.util.dir.Dir;

import java.io.File;
import java.util.Objects;

public class GenerateModelTest {

    @Test
    @Ignore
    public void test() throws Exception {
        final String content = Resources.read(GenerateModelTest.class, "pull-request-minimal.json");

        final Schema schema = JsonMarshalling.unmarshal(Schema.class, content);
        schema.setName("pull-request-minimal");
        final ModelGenerator modelGenerator = new ModelGenerator();

        final Clazz clazz = modelGenerator.build(schema);

        final File tmpdir = Files.tmpdir();
        final Project project = Dir.of(Project.class, tmpdir);

        final ClazzRenderer renderer = new ClazzRenderer(project, "org.tomitribe.github.model");
        renderer.render(clazz);
    }

    public static class ModelGenerator {

        public Clazz build(final Schema schema) {
            Objects.requireNonNull(schema.getName(), "Schema name");
            return null;
        }

    }
}
