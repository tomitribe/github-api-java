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
import org.junit.Test;
import org.tomitribe.github.Resources;
import org.tomitribe.github.core.JsonMarshalling;
import org.tomitribe.github.gen.code.model.Clazz;
import org.tomitribe.github.gen.code.model.ClazzRenderer;
import org.tomitribe.github.gen.code.model.EnumClazz;
import org.tomitribe.github.gen.code.model.Field;
import org.tomitribe.github.gen.openapi.Schema;
import org.tomitribe.util.Files;
import org.tomitribe.util.IO;
import org.tomitribe.util.Strings;
import org.tomitribe.util.dir.Dir;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @Data
    public static class ModelGenerator {

        private final List<Clazz> classes = new ArrayList<>();

        public Clazz build(final Schema schema) {
            Objects.requireNonNull(schema.getName(), "Schema name");

            // TODO get the component Id in there somewhere
            // maybe that needs to happen before we get here
            final Clazz.Builder clazz = Clazz.builder()
                    .name(Strings.camelCase(schema.getName()));

            final Map<String, Schema> properties = schema.getProperties();
            for (final Map.Entry<String, Schema> entry : properties.entrySet()) {
                final String name = entry.getKey();
                final Schema value = entry.getValue();

                clazz.field(getField(clazz, name, value));
            }

            final Clazz build = clazz.build();
            classes.add(build);
            return build;
        }

        public Field getField(final Clazz.Builder clazz, final String name, final Schema value) {
            final String type = value.getType();
            if ("integer".equals(type)) {
                return Field.field(name, "Integer").build();
            }

            if ("string".equals(type) && value.getEnumValues() != null) {
                final String className = Strings.camelCase(name);
                clazz.innerClass(EnumClazz.of(className, value.getEnumValues()));
                return Field.field(name, className).build();
            }

            if ("string".equals(type)) {
                return Field.field(name, "String").build();
            }

            if ("boolean".equals(type)) {
                return Field.field(name, "Boolean").build();
            }

            if ("object".equals(type)) {
                value.setName(name);
                final Clazz referencedClazz = build(value);
                return Field.field(name, referencedClazz.getName()).build();
            }

//            if ("array".equals(type)) {
//                return Field.field(name, "Boolean").build();
//                final Schema items = value.getItems();
//                if (items == null) throw new IllegalStateException("Array type missing items");
//
//                final String componentRef = items.getRef();
//                if (componentRef == null) return Stream.of();
//
//                final String component = getRefName(componentRef);
//                return exampleNames.stream()
//                        .map(s -> new ExampleReference(component, null, s, true));
//            }

            throw new UnsupportedOperationException("Unknown type: " + type);
        }

    }
}
