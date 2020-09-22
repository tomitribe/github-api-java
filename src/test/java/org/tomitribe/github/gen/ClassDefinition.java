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

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import lombok.Data;
import org.tomitribe.github.gen.code.model.Name;
import org.tomitribe.util.IO;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class ClassDefinition {
    private final CompilationUnit unit;
    private final ClassOrInterfaceDeclaration clazz;
    private final JavaParser javaParser = new JavaParser();

    public String toString() {
        return unit.toString();
    }

    public Optional<EnumDeclaration> selectEnum(final String name) {
        return selectEnums()
                .filter(enumDeclaration -> enumDeclaration.getNameAsString().equals(name))
                .findAny();
    }

    public Stream<EnumDeclaration> selectEnums() {
        return clazz.getMembers().stream()
                .filter(bodyDeclaration -> bodyDeclaration instanceof EnumDeclaration)
                .map(EnumDeclaration.class::cast);
    }

    public Optional<ClassDefinition> selectInnerClass(final String name) {
        return clazz.getMembers().stream()
                .filter(bodyDeclaration -> bodyDeclaration instanceof ClassOrInterfaceDeclaration)
                .map(ClassOrInterfaceDeclaration.class::cast)
                .filter(enumDeclaration -> enumDeclaration.getNameAsString().equals(name))
                .map(classOrInterfaceDeclaration -> new ClassDefinition(unit, classOrInterfaceDeclaration))
                .findAny();
    }


    public void addImport(final Name className) {
        addImport(className.toString());
    }

    public void addImport(final String className) {
        final boolean exists = unit.getImports().stream()
                .map(ImportDeclaration::getNameAsString)
                .anyMatch(s -> s.equals(className));

        if (!exists) {
            unit.addImport(className);
        }
    }

    public Map<String, FieldDeclaration> mapFields() {
        final Function<FieldDeclaration, String> getName = field -> field.getVariable(0).getNameAsString();
        return clazz.getFields().stream().collect(Collectors.toMap(getName, Function.identity()));
    }

    public void addAnnotation(final String annotationSource) {
        addAnnotation(this.clazz, annotationSource);
    }

    public void addRepeatableAnnotation(final String annotationSource) {
        addRepeatableAnnotation(this.clazz, annotationSource);
    }

    public void addAnnotation(final NodeWithAnnotations<?> node, final String annotationSource) {
        final AnnotationExpr annotationExpr = get(javaParser.parseAnnotation(annotationSource));

        final String annotationName = annotationExpr.getNameAsString();

        if (node.isAnnotationPresent(annotationName)) {
            final List<AnnotationExpr> annotations = node.getAnnotations()
                    .stream()
                    .filter(annotationExpr1 -> !annotationName.equals(annotationExpr1.getNameAsString()))
                    .collect(Collectors.toList());
            annotations.add(annotationExpr);
            node.setAnnotations(new NodeList<>(annotations));
        } else {
            node.addAnnotation(annotationExpr);
        }
    }

    public static <T> T get(final ParseResult<T> result) {
        if (!result.isSuccessful()) throw new ParseProblemException(result.getProblems());
        return result.getResult().get();
    }

    public void addRepeatableAnnotation(final NodeWithAnnotations<?> node, final String annotationSource) {
        final AnnotationExpr annotationExpr = get(javaParser.parseAnnotation(annotationSource));

        final String annotationName = annotationExpr.getNameAsString();

        if (node.isAnnotationPresent(annotationName)) {
            final String annotationString = annotationExpr.toString();

            final List<AnnotationExpr> annotations = node.getAnnotations()
                    .stream()
                    .filter(existing -> !existing.toString().equals(annotationString))
                    .collect(Collectors.toList());

            annotations.add(annotationExpr);
            node.setAnnotations(new NodeList<>(annotations));
        } else {
            node.addAnnotation(annotationExpr);
        }
    }

    public ClassDefinition clean() {

        { // Remove duplicates and sort
            final List<ImportDeclaration> imports = unit.getImports().stream()
                    .distinct()
                    .sorted(Comparator.comparing(ImportDeclaration::getNameAsString))
                    .collect(Collectors.toList());

            unit.setImports(new NodeList<>(imports));
        }

        final NodeList<BodyDeclaration<?>> members = new NodeList<>(clazz.getMembers());
        members.sort(new NameComparator());
        members.sort(new TypeComparator());

        clazz.setMembers(members);

        return this;
    }

    public static ClassDefinition parse(final File file) {
        try {
            return parse(IO.slurp(file));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static ClassDefinition parse(final String source) {
        final JavaParser javaParser = new JavaParser();
        final CompilationUnit unit = get(javaParser.parse(source));
        final ClassOrInterfaceDeclaration clazz = Utils.getClazz(unit);
        return new ClassDefinition(unit, clazz);
    }

    /**
     * Simply for style-purposes we like to have fields first, constructors second, methods
     * third and inner classes at the end.
     *
     * This also helps us ensure any diffs are meaningful and not random reformatting.
     */
    private static class TypeComparator implements Comparator<BodyDeclaration<?>> {
        @Override
        public int compare(final BodyDeclaration<?> bodyA, final BodyDeclaration<?> bodyB) {
            final int a = score(bodyA);
            final int b = score(bodyB);
            return Integer.compare(a, b);
        }

        private int score(final BodyDeclaration<?> body) {
            if (body instanceof FieldDeclaration) return 0;
            if (body instanceof ConstructorDeclaration) return 1;
            if (body instanceof MethodDeclaration) return 2;
            return 10;
        }
    }

    /**
     * Simply for style-purposes we like to have the fields in alphabetical order.
     *
     * This also helps us ensure any diffs are meaningful and not random reformatting.
     */
    private static class NameComparator implements Comparator<BodyDeclaration<?>> {
        @Override
        public int compare(final BodyDeclaration<?> bodyA, final BodyDeclaration<?> bodyB) {
            final String a = getName(bodyA);
            final String b = getName(bodyB);
            return a.compareTo(b);
        }

        private String getName(final BodyDeclaration<?> body) {
            if (body instanceof NodeWithSimpleName) {
                final NodeWithSimpleName<?> node = (NodeWithSimpleName<?>) body;
                return node.getNameAsString();
            }

            if (body instanceof FieldDeclaration) {
                final FieldDeclaration field = (FieldDeclaration) body;
                final VariableDeclarator node = field.getVariables().get(0);
                return node.getNameAsString();
            }

            throw new UnsupportedOperationException("Unknown type " + body.getClass().getSimpleName());
        }
    }
}
