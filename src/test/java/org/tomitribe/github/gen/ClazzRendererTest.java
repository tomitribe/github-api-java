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

import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;
import org.tomitribe.github.gen.code.model.Clazz;
import org.tomitribe.github.gen.code.model.ClazzRenderer;
import org.tomitribe.github.gen.code.model.EnumClazz;
import org.tomitribe.github.gen.code.model.Field;
import org.tomitribe.util.Files;
import org.tomitribe.util.IO;
import org.tomitribe.util.JarLocation;
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

    private final Dir resources = getResources();

    @Test
    public void simple() throws IOException {

        final Clazz clazz = Clazz.builder()
                .name("PullRequest")
                .componentId("#/components/schemas/pull-request-minimal")
                .componentId("#/components/schemas/pull-request")
                .field(Field.field("repository_url", "String").build())
                .field(Field.field("pull_request_url", "String").build())
                .field(Field.field("pull_request_number", "Integer").build())
                .field(Field.field("labels", "String").collection(true).build())
                .field(Field.field("owner", "String").in(PATH).build())
                .field(Field.field("repo", "String").in(PATH).build())
                .field(Field.field("state", "State").in(QUERY).build())
                .build();

        final File tmpdir = Files.tmpdir();
        final Project actual = Project.from(tmpdir);
        final ClazzRenderer renderer = new ClazzRenderer(actual, "org.tomitribe.github.model");
        renderer.render(clazz);

        final Project expected = Project.from(resources.file("simple"));

        assertProject(expected, actual);
    }

    @Test
    public void update() throws IOException {

        final Clazz clazz = Clazz.builder()
                .name("PullRequest")
                .componentId("#/components/schemas/pull-request-minimal")
                .componentId("#/components/schemas/pull-request")
                .field(Field.field("repository_url", "String").build())
                .field(Field.field("pull_request_url", "String").build())
                .field(Field.field("pull_request_number", "Integer").build())
                .field(Field.field("labels", "String").collection(true).build())
                .field(Field.field("owner", "String").in(PATH).build())
                .field(Field.field("repo", "String").in(PATH).build())
                .field(Field.field("state", "State").in(QUERY).build())
                .build();

        final Project actual = Project.from(Files.tmpdir());

        IO.copyDirectory(resources.file("update/before"), actual.get());

        final ClazzRenderer renderer = new ClazzRenderer(actual, "org.tomitribe.github.model");
        renderer.render(clazz);

        final Project expected = Project.from(resources.file("update/after"));

        assertProject(expected, actual);
    }

    @Test
    public void enums() throws Exception {
        final Clazz clazz = Clazz.builder()
                .name("PullRequest")
                .componentId("#/components/schemas/pull-request-minimal")
                .componentId("#/components/schemas/pull-request")
                .field(Field.field("repository_url", "String").build())
                .field(Field.field("pull_request_url", "String").build())
                .field(Field.field("pull_request_number", "Integer").build())
                .field(Field.field("labels", "String").collection(true).build())
                .field(Field.field("owner", "String").in(PATH).build())
                .field(Field.field("repo", "String").in(PATH).build())
                .field(Field.field("state", "State").in(QUERY).build())
                .innerClass(EnumClazz.of("State", "created", "updated", "popularity", "long-running"))
                .build();

        final File tmpdir = Files.tmpdir();
        final Project actual = Project.from(tmpdir);
        final ClazzRenderer renderer = new ClazzRenderer(actual, "org.tomitribe.github.model");
        renderer.render(clazz);

        final Project expected = Project.from(resources.file("enums"));

        assertProject(expected, actual);
    }


    @Test
    public void updateEnums() throws IOException {

        final Clazz clazz = Clazz.builder()
                .name("PullRequest")
                .componentId("#/components/schemas/pull-request-minimal")
                .componentId("#/components/schemas/pull-request")
                .field(Field.field("repository_url", "String").build())
                .field(Field.field("pull_request_url", "String").build())
                .field(Field.field("pull_request_number", "Integer").build())
                .field(Field.field("labels", "String").collection(true).build())
                .field(Field.field("owner", "String").in(PATH).build())
                .field(Field.field("repo", "String").in(PATH).build())
                .field(Field.field("state", "State").in(QUERY).build())
                .innerClass(EnumClazz.of("State", "created", "updated", "popularity", "long-running"))
                .build();

        final Project actual = Project.from(Files.tmpdir());

        IO.copyDirectory(resources.file("updateEnums/before"), actual.get());

        final ClazzRenderer renderer = new ClazzRenderer(actual, "org.tomitribe.github.model");
        renderer.render(clazz);

        final Project expected = Project.from(resources.file("updateEnums/after"));

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

    public static Dir getResources() {
        final Class<ClazzRendererTest> clazz = ClazzRendererTest.class;
        final File testClasses = JarLocation.jarLocation(clazz);
        final File testResources = new File(testClasses, clazz.getSimpleName());
        return Dir.of(Dir.class, testResources);
    }
}
