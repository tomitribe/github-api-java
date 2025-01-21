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
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import org.apache.commons.lang3.text.WordUtils;
import org.tomitribe.github.core.DateAdapter;
import org.tomitribe.github.core.EnumAdapter;
import org.tomitribe.github.gen.ClassDefinition;
import org.tomitribe.github.gen.Package;
import org.tomitribe.github.gen.Project;
import org.tomitribe.util.IO;
import org.tomitribe.util.Join;
import org.tomitribe.util.PrintString;
import org.tomitribe.util.Strings;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.tomitribe.github.gen.code.model.Name.DATE;
import static org.tomitribe.github.gen.code.model.Name.OBJECT;

public class ClazzRenderer {

    static final Logger LOGGER = Logger.getLogger(ClazzRenderer.class.getSimpleName());

    private final Project project;
    private final String packageName;

    public ClazzRenderer(final Project project, final String packageName) {
        this.project = project;
        this.packageName = packageName;
    }

    public void render(final Clazz clazz) {
        if (clazz.getName() == null) {
            LOGGER.warning("Class has no name: " + clazz);
            return;
        }
        final String className = clazz.getName().getSimpleName();
        final Package aPackage = project.src().main().java().packageName(packageName);
        final File sourceFile = aPackage.file(className + ".java");

        LOGGER.info(String.format("Generating class '%s'", clazz.getName()));

        final String content;
        if (sourceFile.exists()) {
            try {
                content = IO.slurp(sourceFile);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        } else {
            content = classTemplate(className);
        }

        final ClassDefinition definition;
        try {
            definition = ClassDefinition.parse(content);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        if (definition.getClazz() == null) throw new IllegalStateException("Parsed clazz is null");

        imports(clazz)
                .sorted()
                .distinct()
                .forEach(definition::addImport);

        clazz.getComponentIds().stream()
                .sorted()
                .map(s -> String.format("@ComponentId(\"%s\")", s))
                .forEach(definition::addRepeatableAnnotation);

        // Add any enums or inner classes
        addInnerClasses(clazz, definition);

        addFields(clazz, definition);

        aPackage.write(className + ".java", definition.clean().toString());

        renderTestCase(clazz);
    }

    public void addFields(final Clazz clazz, final ClassDefinition definition) {
        final List<String> enums = definition.selectEnums()
                .map(NodeWithSimpleName::getNameAsString)
                .collect(Collectors.toList());

        final Map<String, FieldDeclaration> fields = definition.mapFields();
        for (final Field field : clazz.getFields()) {

            final FieldDeclaration fieldDeclaration;
            if (fields.containsKey(field.getName())) {
                fieldDeclaration = fields.get(field.getName());
            } else {
                fieldDeclaration = definition.getClazz().addField(field.getType().getSimpleName(), field.getName(), Modifier.Keyword.PRIVATE);
            }

            final VariableDeclarator variable = fieldDeclaration.getVariable(0);
            if (field.isCollection()) {

                variable.setType(String.format("List<%s>", field.getType().getSimpleName()));

            } else if (field.isMap()) {

                variable.setType(String.format("Map<String, %s>", field.getType().getSimpleName()));

            } else {
                if (OBJECT.equals(field.getType())) {
                    /*
                     * If Clazz says the type is Object and the source already has a type,
                     * just use what ever type the source has and do not override it
                     */
                    if (variable.getType() == null) {
                        variable.setType(field.getType().getSimpleName());
                    }
                } else {
                    variable.setType(field.getType().getSimpleName());
                }
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

            if (enums.contains(field.getType().getSimpleName())) {
                definition.addAnnotation(fieldDeclaration, String.format("@JsonbTypeAdapter(%sAdapter.class)", field.getType()));
            }

            if (DATE.equals(field.getType())) {
                definition.addAnnotation(fieldDeclaration, "@JsonbTypeAdapter(DateAdapter.class)");
            }
        }
    }

    public void addInnerClasses(final Clazz clazz, final ClassDefinition definition) {
        for (final Clazz innerClass : clazz.getInnerClasses()) {
            LOGGER.info(String.format("Adding inner class '%s' to '%s'", innerClass.getName(), clazz.getName()));
            if (innerClass instanceof EnumClazz) {
                final EnumClazz enumClazz = (EnumClazz) innerClass;

                final ClassDefinition template = ClassDefinition.parse(enumTemplate(enumClazz.getName().getSimpleName()));

                { // Add the enum declaration
                    final Optional<EnumDeclaration> existing = definition.selectEnum(enumClazz.getName().getSimpleName());
                    final EnumDeclaration enumDeclaration = existing
                            .orElseGet(template.selectEnum(enumClazz.getName().getSimpleName())::get);

                    final Map<String, EnumConstantDeclaration> entries = enumDeclaration.getEntries().stream()
                            .collect(Collectors.toMap(EnumConstantDeclaration::getNameAsString, Function.identity()));

                    for (final String value : enumClazz.getValues()) {
                        final String constant = EnumClazz.asConstant(value);
                        if (entries.containsKey(constant)) continue;

                        // Some github enums have dashes such as "long-running"
                        // We need a version that is safe to be used as the constant

                        final EnumConstantDeclaration constantDeclaration = new EnumConstantDeclaration(constant);
                        constantDeclaration.addArgument(new StringLiteralExpr(value));
                        enumDeclaration.addEntry(constantDeclaration);
                    }

                    if (!existing.isPresent()) {
                        definition.getClazz().addMember(enumDeclaration);
                    }
                }

                { // Now add the enum adapter
                    final String name = enumClazz.getName() + "Adapter";

                    if (!definition.selectInnerClass(name).isPresent()) {
                        definition.getClazz().addMember(template.selectInnerClass(name).get().getClazz());
                    }
                }
            }
        }
    }

    private String classTemplate(final String className) {
        return readTemplate("Model").replace("the_package", packageName)
                .replace("Model", className);
    }

    private String enumTemplate(final String className) {
        return readTemplate("ModelEnum").replace("the_package", packageName)
                .replace("Orange", className);
    }

    private String readTemplate(final String templateName) {
        final ClassLoader loader = this.getClass().getClassLoader();
        final String content;
        try {
            content = IO.slurp(loader.getResource("gen/templates/" + templateName + ".java"));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return content;
    }

    private Stream<String> imports(final Clazz clazz) {
        final List<String> imports = new ArrayList<>();

        for (final Clazz innerClass : clazz.getInnerClasses()) {
            if (innerClass instanceof EnumClazz) {
                imports.add(JsonbTypeAdapter.class.getName());
                imports.add(EnumAdapter.class.getName());
            }
        }

        for (final Field field : clazz.getFields()) {
            if (field.isCollection()) {
                imports.add(List.class.getName());
            }

            if (field.isMap()) {
                imports.add(Map.class.getName());
            }

            imports.add(field.getType().toString());

            if (DATE.equals(field.getType())) {
                imports.add(DateAdapter.class.getName());
            }

            switch (field.getIn()) {
                case BODY: {
                    imports.add(JsonbProperty.class.getName());
                    break;
                }
                case PATH: {
                    imports.add(JsonbTransient.class.getName());
                    imports.add(PathParam.class.getName());
                    break;
                }
                case QUERY: {
                    imports.add(JsonbTransient.class.getName());
                    imports.add(QueryParam.class.getName());
                    break;
                }
                case HEADER: {
                    imports.add(JsonbTransient.class.getName());
                    imports.add(HeaderParam.class.getName());
                    break;
                }
                default:
                    throw new UnsupportedOperationException(field.getIn().name());
            }
        }

        return imports.stream();
    }

    private void renderTestCase(final Clazz clazz) {
        final PrintString out = new PrintString();
        final String name = clazz.getName().getSimpleName();
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
                "}\n", name, name);

        final Package aPackage = project.src().test().java().packageName(packageName);
        aPackage.write(name + "Test.java", new String(out.toByteArray()));
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

    public static class UnmodifiableNodeList<N extends Node> extends NodeList<N> {
        @Override
        public void sort(final Comparator<? super N> comparator) {
            super.sort(comparator);
        }


    }
}
