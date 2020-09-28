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
package org.tomitribe.github.gen.code.endpoint;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import org.tomitribe.github.client.Category;
import org.tomitribe.github.client.DeprecationDate;
import org.tomitribe.github.client.Docs;
import org.tomitribe.github.client.EnabledForGithubApps;
import org.tomitribe.github.client.GithubCloudOnly;
import org.tomitribe.github.client.OperationId;
import org.tomitribe.github.client.Paged;
import org.tomitribe.github.client.Preview;
import org.tomitribe.github.client.RemovalDate;
import org.tomitribe.github.client.Subcategory;
import org.tomitribe.github.gen.ClassDefinition;
import org.tomitribe.github.gen.Package;
import org.tomitribe.github.gen.Project;
import org.tomitribe.github.gen.code.model.ArrayClazz;
import org.tomitribe.github.gen.code.model.Clazz;
import org.tomitribe.github.gen.code.model.ClazzRenderer;
import org.tomitribe.github.gen.code.model.Field;
import org.tomitribe.github.gen.code.model.Name;
import org.tomitribe.github.gen.code.model.VoidClazz;
import org.tomitribe.util.IO;
import org.tomitribe.util.Strings;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.tomitribe.github.gen.EndpointGenerator.getPagedItem;

public class EndpointRenderer {

    private final Project project;
    private final String packageName;
    private final ClazzRenderer clazzRenderer;

    public EndpointRenderer(final Project project, final String clientPackage, final String modelPackage) {
        this.project = project;
        this.packageName = clientPackage;
        this.clazzRenderer = new ClazzRenderer(project, modelPackage);
    }

    public void render(final Clazz clazz) {
        clazzRenderer.render(clazz);
    }
    
    public void render(final Endpoint endpoint) {
        final String className = endpoint.getClassName();
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
            content = classTemplate(className);
        }

        final ClassDefinition definition = ClassDefinition.parse(content);
        if (definition.getClazz() == null) throw new IllegalStateException("Parsed clazz is null");

        endpoint.getMethods()
                .stream()
                .flatMap(this::imports)
                .sorted()
                .distinct()
                .filter(s -> !"void".equals(s))
                .forEach(definition::addImport);

        endpoint.getMethods().sort(Comparator.comparing(EndpointMethod::getMethod));
        endpoint.getMethods().sort(Comparator.comparing(EndpointMethod::getPath));

        for (final EndpointMethod method : endpoint.getMethods()) {
            final MethodDeclaration methodDeclaration = new MethodDeclaration();
            methodDeclaration.setName(method.getJavaMethod());

            if (method.getRequest() instanceof VoidClazz) {
                // skip setting any parameters
            } else {
                methodDeclaration.addParameter(param(
                        method.getRequest().getName().getSimpleName(),
                        Strings.lcfirst(method.getRequest().getName().getSimpleName())));
            }

            if (method.getResponse().isPaged()) {
                if (method.getResponse() instanceof ArrayClazz) {
                    final ArrayClazz arrayClazz = (ArrayClazz) method.getResponse();
                    methodDeclaration.setType(String.format("Stream<%s>", arrayClazz.getOf().getName().getSimpleName()));
                } else {
                    final Field pagedItem = getPagedItem(method.getResponse());
                    methodDeclaration.setType(String.format("Stream<%s>", pagedItem.getType().getSimpleName()));
                }
            } else if (isArrayOfArray(method)) {
                final Name arrayType = getImport(method.getResponse());
                methodDeclaration.setType(String.format("%s[][]", asRequiredType(arrayType)));
            } else if (isArray(method)) {
                final ArrayClazz arrayClazz = (ArrayClazz) method.getResponse();
                methodDeclaration.setType(String.format("Stream<%s>", arrayClazz.getOf().getName().getSimpleName()));
            } else {
                methodDeclaration.setType(asRequiredType(method.getResponse().getName()));
            }

            final Annotations annotations = new Annotations(methodDeclaration, definition);

            // Add @GET, @PUT, @POST
            annotations.add("@%s", method.getMethod().toUpperCase());
            annotations.add(Path.class, method.getPath());

            if (method.getOperationId() != null) {
                annotations.add(OperationId.class, method.getOperationId());
            }

            if (method.getDocs() != null) {
                annotations.add(Docs.class, method.getDocs());
            }

            if (method.getRemovalDate() != null) {
                annotations.add(RemovalDate.class, method.getRemovalDate());
            }

            if (method.getDeprecationDate() != null) {
                annotations.add(DeprecationDate.class, method.getDeprecationDate());
            }

            if (method.isGithubCloudOnly()) {
                annotations.add(GithubCloudOnly.class);
            }

            if (method.isEnabledForGitHubApps()) {
                annotations.add(EnabledForGithubApps.class);
            }

            for (final String preview : method.getPreviews()) {
                annotations.addRepeatable(Preview.class, preview);
            }

            if (method.getCategory() != null) {
                annotations.add(Category.class, method.getCategory());
            }

            if (method.getSubcategory() != null) {
                annotations.add(Subcategory.class, method.getSubcategory());
            }

            if (method.getResponse().isPaged()) {
                if (method.getResponse() instanceof ArrayClazz) {
                    final ArrayClazz arrayClazz = (ArrayClazz) method.getResponse();
                    annotations.add("@%s(%s[].class)", Paged.class.getSimpleName(),
                            arrayClazz.getOf().getName().getSimpleName());
                } else {
                    annotations.add(Paged.class, method.getResponse().getName());
                }
            }

            methodDeclaration.setBody(null);
            definition.getClazz().addMember(methodDeclaration);

            /*
             * Some people may not like the all-in-one request object style
             * of methods and may prefer individual parameters.
             * Let's generate an overloaded version of the method that has
             * each path parameter individually.
             */
            if (canOverload(method)) {
                final MethodDeclaration overloaded = new MethodDeclaration();
                overloaded.setType(methodDeclaration.getType());
                overloaded.setAnnotations(methodDeclaration.getAnnotations());
                overloaded.setName(methodDeclaration.getName());
                overloaded.setBody(null);

                method.getRequest().getFields().stream()
                        .filter(field -> field.getIn().equals(Field.In.PATH))
                        .peek(field -> definition.addImport(field.getName()))
                        .map(this::asParameter)
                        .forEach(overloaded::addParameter);

                definition.getClazz().addMember(overloaded);
            }
        }

        aPackage.write(className + ".java", definition.clean().toString());
    }

    private Parameter asParameter(final Field field) {

        if (field.getIn().equals(Field.In.PATH)) {

            final Parameter parameter = new Parameter();
            parameter.addAnnotation(ann(PathParam.class, field.getJsonName()));
            parameter.setModifiers(Modifier.Keyword.FINAL);
            parameter.setType(asRequiredType(field.getType()));
            parameter.setName(field.getName());
            return parameter;

        }

        if (field.getIn().equals(Field.In.QUERY)) {

            final Parameter parameter = new Parameter();
            parameter.addAnnotation(ann(QueryParam.class, field.getJsonName()));
            parameter.setModifiers(Modifier.Keyword.FINAL);
            parameter.setType(field.getType().getSimpleName());
            parameter.setName(field.getName());
            return parameter;

        }

        throw new UnsupportedOperationException("Unsupported parameter type: " + field.getIn());
    }

    /**
     * When a method returns json "integer" we should not
     * return Integer but int.  Similarly, when a method
     * parameter takes json "integer" and that parameter
     * is a path parameter or other non-optional value,
     * we should also use the "int" version so users are
     * forced to supply values.
     */
    private String asRequiredType(final Name type) {
        if (Name.BOOLEAN.equals(type)) return "boolean";
        if (Name.INTEGER.equals(type)) return "int";
        if (Name.LONG.equals(type)) return "long";
        return type.getSimpleName();
    }

    private static SingleMemberAnnotationExpr ann(final Class<?> annotationType, final String value) {
        return new SingleMemberAnnotationExpr(
                new com.github.javaparser.ast.expr.Name(annotationType.getSimpleName()),
                new StringLiteralExpr(value));
    }

    private boolean canOverload(final EndpointMethod method) {
        int parameter = 0;
        final List<Field> fields = method.getRequest().getFields();
        for (final Field field : fields) {
            if (field.getIn().equals(Field.In.BODY)) return false;
            if (field.getIn().equals(Field.In.PATH)) parameter++;
        }
        return parameter > 0;
    }

    private boolean isArray(final EndpointMethod method) {
        return method.getResponse() instanceof ArrayClazz;
    }

    private boolean isArrayOfArray(final EndpointMethod method) {
        final Clazz clazz = method.getResponse();
        if (clazz instanceof ArrayClazz) {
            final ArrayClazz arrayClazz = (ArrayClazz) clazz;
            return arrayClazz.getOf() instanceof ArrayClazz;
        }
        return false;
    }

    public static class Imports {
        final List<String> imports = new ArrayList<>();

        public void add(final Class<?> clazz) {
            imports.add(clazz.getName());
        }

        public void add(final String className) {
            imports.add(className);
        }

        public void add(final Name className) {
            try {
                imports.add(className.toString());
            } catch (Exception e) {
                throw new IllegalStateException(String.format("Cannot add import for name: %s", className), e);
            }
        }

        private Stream<String> stream() {
            return imports.stream();
        }
    }

    private Stream<String> imports(final EndpointMethod method) {
        final Imports imports = new Imports();

        if (isArray(method)) {
            imports.add(Stream.class);
        }

        if (method.getResponse().isPaged()) {
            if (method.getResponse() instanceof ArrayClazz) {
                final ArrayClazz arrayClazz = (ArrayClazz) method.getResponse();
                imports.add(arrayClazz.getOf().getName());
            } else {
                imports.add(method.getResponse().getName());
                imports.add(getPagedItem(method.getResponse()).getType());
            }
        }

        if ("GET".equalsIgnoreCase(method.getMethod())) imports.add(GET.class);
        if ("POST".equalsIgnoreCase(method.getMethod())) imports.add(POST.class);
        if ("PUT".equalsIgnoreCase(method.getMethod())) imports.add(PUT.class);
        if ("DELETE".equalsIgnoreCase(method.getMethod())) imports.add(DELETE.class);
        if ("OPTIONS".equalsIgnoreCase(method.getMethod())) imports.add(OPTIONS.class);
        if ("PATCH".equalsIgnoreCase(method.getMethod())) imports.add(PATCH.class);
        if ("HEAD".equalsIgnoreCase(method.getMethod())) imports.add(HEAD.class);

        imports.add(Path.class);

        final List<Clazz> clazzes = Arrays.asList(method.getRequest(), method.getResponse());
        for (final Clazz clazz : clazzes) {
            if (clazz == null) continue;
            imports.add(getImport(clazz));
        }

        return imports.stream().filter(Objects::nonNull);
    }

    private Name getImport(final Clazz clazz) {
        if (clazz == null) return new Name(null, null);
        if (clazz instanceof VoidClazz) return new Name(null, null);
        if (clazz instanceof ArrayClazz) {
            final ArrayClazz arrayClazz = (ArrayClazz) clazz;
            return getImport(arrayClazz.getOf());
        }

        return clazz.getName();
    }

    public static class Annotations {
        private final MethodDeclaration methodDeclaration;
        private final ClassDefinition definition;

        public Annotations(final MethodDeclaration methodDeclaration, final ClassDefinition definition) {
            this.methodDeclaration = methodDeclaration;
            this.definition = definition;
        }

        public Annotations add(final String format, final Object... details) {
            final String annotation = String.format(format, details);
            definition.addAnnotation(methodDeclaration, annotation);
            return this;
        }

        public Annotations add(final Class<?> annotation, final String value) {
            return add("@%s(\"%s\")", annotation.getSimpleName(), value);
        }

        public Annotations add(final Class<?> annotation, final Name value) {
            return add("@%s(%s.class)", annotation.getSimpleName(), value.getSimpleName());
        }

        public Annotations add(final Class<?> annotation) {
            return add("@%s", annotation.getSimpleName());
        }

        public Annotations addRepeatable(final String format, final Object... details) {
            final String annotation = String.format(format, details);
            definition.addRepeatableAnnotation(methodDeclaration, annotation);
            return this;
        }

        public Annotations addRepeatable(final Class<?> annotation, final String value) {
            return addRepeatable("@%s(\"%s\")", annotation.getSimpleName(), value);
        }

        public Annotations addRepeatable(final Class<?> annotation) {
            return addRepeatable("@%s", annotation.getSimpleName());
        }

    }

    private Parameter param(final String type, final String name) {
        final Parameter parameter = new Parameter();
        parameter.setType(type);
        parameter.setName(name);
        parameter.setModifiers(new NodeList<>(Modifier.finalModifier()));
        return parameter;
    }

    private String classTemplate(final String className) {
        return readTemplate("Endpoint").replace("the_package", packageName)
                .replace("Endpoint", className);
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

}
