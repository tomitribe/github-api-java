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
import org.tomitribe.github.gen.code.model.Clazz;
import org.tomitribe.github.gen.code.model.ClazzRenderer;
import org.tomitribe.github.gen.code.model.Field;
import org.tomitribe.util.Files;
import org.tomitribe.util.IO;
import org.tomitribe.util.Join;
import org.tomitribe.util.dir.Dir;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.tomitribe.github.gen.code.model.Field.In.PATH;
import static org.tomitribe.github.gen.code.model.Field.In.QUERY;

public class ClazzRendererTest {

    private final Dir resources = Project.root().src().test().resources().dir(ClazzRendererTest.class.getSimpleName());

    @Test
    public void test() throws IOException {

        final Clazz clazz = Clazz.builder()
                .name("PullRequest")
                .componentId("#/components/schemas/pull-request-minimal")
                .build()
                .add(Field.field("repository_url", "String").build())
                .add(Field.field("pull_request_url", "String").build())
                .add(Field.field("pull_request_number", "Integer").build())
                .add(Field.field("labels", "String").collection(true).build())
                .add(Field.field("owner", "String").in(PATH).build())
                .add(Field.field("repo", "String").in(PATH).build())
                .add(Field.field("state", "State").in(QUERY).build());

        final File tmpdir = Files.tmpdir();
        final Project actual = Project.from(tmpdir);
        final ClazzRenderer renderer = new ClazzRenderer(actual, "org.tomitribe.github.model");
        renderer.render(clazz);

        final Project expected = Project.from(resources.file("simple"));

        assertProject(expected, actual);
    }

    public static void assertProject(final Project expected, final Project actual) throws IOException {

        final String expectedPaths = Join.join("\n", expected.paths().collect(Collectors.toList()));
        final String actualPaths = Join.join("\n", actual.paths().collect(Collectors.toList()));
        assertEquals(expectedPaths, actualPaths);

        final List<String> paths = expected.paths().collect(Collectors.toList());
        for (final String path : paths) {
            final String expectedContent = IO.slurp(expected.file(path));
            final String actualContent = IO.slurp(actual.file(path));
            assertEquals(path, expectedContent, actualContent);
        }
    }
}