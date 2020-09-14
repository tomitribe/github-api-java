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
package org.tomitribe.github.gen.code.model;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import org.apache.commons.lang3.text.WordUtils;
import org.tomitribe.github.gen.ClassDefinition;
import org.tomitribe.github.gen.Package;
import org.tomitribe.github.gen.Project;
import org.tomitribe.util.IO;
import org.tomitribe.util.Join;
import org.tomitribe.util.PrintString;
import org.tomitribe.util.Strings;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClazzRenderer {

    private final Project project;
    private final String packageName;

    public ClazzRenderer(final Project project, final String packageName) {
        this.project = project;
        this.packageName = packageName;
    }

    public void render(final Clazz clazz) {
        final String className = clazz.getName();
        final Package aPackage = project.src().main().java().packageName(packageName);
        final File sourceFile = aPackage.file(className + ".java");

        final String content;
        if (sourceFile.exists()) {
            try {
                content = IO.slurp(sourceFile);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        } else {
            content = template(className, packageName);
        }

        final ClassDefinition definition = ClassDefinition.parse(content);
        if (definition.getClazz() == null) throw new IllegalStateException("Parsed clazz is null");

        clazz.getFields().stream()
                .map(Field::getIn)
                .flatMap(this::imports)
                .sorted()
                .distinct()
                .forEach(definition::addImport);

        clazz.getComponentIds().stream()
                .map(s -> String.format("@ComponentId(\"%s\")", s))
                .forEach(definition::addRepeatableAnnotation);

        final Map<String, FieldDeclaration> fields = definition.mapFields();
        for (final Field field : clazz.getFields()) {

            final FieldDeclaration fieldDeclaration;
            if (fields.containsKey(field.getName())) {
                fieldDeclaration = fields.get(field.getName());
            } else {
                fieldDeclaration = definition.getClazz().addField(field.getType(), field.getName(), Modifier.Keyword.PRIVATE);
            }

            final VariableDeclarator variable = fieldDeclaration.getVariable(0);
            if (field.isCollection()){
                variable.setType(String.format("List<%s>", field.getType()));
            } else {
                variable.setType(field.getType());
            }

            switch (field.getIn()) {
                case BODY: {
                    definition.addAnnotation(fieldDeclaration, String.format("@JsonbProperty(\"%s\")", field.getJsonName()));
                    break;
                }
                case PATH: {
                    definition.addAnnotation(fieldDeclaration, "@JsonbTransient");
                    definition.addAnnotation(fieldDeclaration, String.format("@PathParam(\"%s\")", field.getJsonName()));
                    break;
                }
                case QUERY: {
                    definition.addAnnotation(fieldDeclaration, "@JsonbTransient");
                    definition.addAnnotation(fieldDeclaration, String.format("@QueryParam(\"%s\")", field.getJsonName()));
                    break;
                }
                case HEADER: {
                    definition.addAnnotation(fieldDeclaration, "@JsonbTransient");
                    definition.addAnnotation(fieldDeclaration, String.format("@HeaderParam(\"%s\")", field.getJsonName()));
                    break;
                }
                default:
                    throw new UnsupportedOperationException(field.getIn().name());
            }
        }

        aPackage.write(className + ".java", definition.clean().toString());

        renderTestCase(clazz);
    }

    private String template(final String className, final String packageName) {
        final ClassLoader loader = this.getClass().getClassLoader();
        final String content;
        try {
            content = IO.slurp(loader.getResource("gen/templates/Model.java"));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return content.replace("the_package", packageName)
                .replace("Model", className);
    }

    private Stream<String> imports(final Field.In in) {
        switch (in) {
            case BODY: {
                return Stream.of("javax.json.bind.annotation.JsonbProperty");
            }
            case PATH: {
                return Stream.of("javax.json.bind.annotation.JsonbTransient", "javax.ws.rs.PathParam");
            }
            case QUERY: {
                return Stream.of("javax.json.bind.annotation.JsonbTransient", "javax.ws.rs.QueryParam");
            }
            case HEADER: {
                return Stream.of("javax.json.bind.annotation.JsonbTransient", "javax.ws.rs.HeaderParam");
            }
            default:
                throw new UnsupportedOperationException(in.name());
        }
    }

    private void renderTestCase(final Clazz clazz) {
        final PrintString out = new PrintString();
        out.printf("/*\n" +
                " * Licensed to the Apache Software Foundation (ASF) under one or more\n" +
                " * contributor license agreements.  See the NOTICE file distributed with\n" +
                " * this work for additional information regarding copyright ownership.\n" +
                " * The ASF licenses this file to You under the Apache License, Version 2.0\n" +
                " * (the \"License\"); you may not use this file except in compliance with\n" +
                " * the License.  You may obtain a copy of the License at\n" +
                " *\n" +
                " *     http://www.apache.org/licenses/LICENSE-2.0\n" +
                " *\n" +
                " *  Unless required by applicable law or agreed to in writing, software\n" +
                " *  distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                " *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                " *  See the License for the specific language governing permissions and\n" +
                " *  limitations under the License.\n" +
                " */\n" +
                "package " + packageName + ";\n" +
                "\n" +
                "import org.junit.Test;\n" +
                "\n" +
                "import java.io.IOException;\n" +
                "\n" +
                "import static org.tomitribe.github.app.events.PayloadAsserts.assertPayload;\n" +
                "\n" +
                "public class %sTest {\n" +
                "\n" +
                "    @Test\n" +
                "    public void parse() throws IOException {\n" +
                "        assertPayload(%s.class);\n" +
                "    }\n" +
                "}\n", clazz.getName(), clazz.getName());

        final Package aPackage = project.src().test().java().packageName(packageName);
        aPackage.write(clazz.getName() + "Test.java", new String(out.toByteArray()));
    }

    private static String formatComment(final String s) {
        final List<String> lines = Stream.of(s.split("\n"))
                .flatMap(s1 -> Stream.of(WordUtils.wrap(s1, 100).split("\n")))
                .map(s2 -> "\n * " + s2)
                .collect(Collectors.toList());

        return Join.join("", lines) + "\n";
    }

    private static String toProperty(final Field field) {
        final String name = field.getName();
        if (name.startsWith("_")) {
            return Strings.ucfirst(name.substring(1));
        }
        return Strings.ucfirst(name);
    }
}
