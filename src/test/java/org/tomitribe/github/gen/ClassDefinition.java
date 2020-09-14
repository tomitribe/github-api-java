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
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class ClassDefinition {
    private final CompilationUnit unit;
    private final ClassOrInterfaceDeclaration clazz;
    private final JavaParser javaParser = new JavaParser();

    public String toString() {
        return unit.toString();
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
        final ParseResult<AnnotationExpr> result = javaParser.parseAnnotation(annotationSource);
        if (!result.isSuccessful()) throw new ParseProblemException(result.getProblems());
        final AnnotationExpr annotationExpr = result.getResult().get();

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

    public void addRepeatableAnnotation(final NodeWithAnnotations<?> node, final String annotationSource) {
        final ParseResult<AnnotationExpr> result = javaParser.parseAnnotation(annotationSource);
        if (!result.isSuccessful()) throw new ParseProblemException(result.getProblems());
        final AnnotationExpr annotationExpr = result.getResult().get();

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

        { // sort fields
            final List<FieldDeclaration> fields = new ArrayList<>(clazz.getFields());
            fields.sort((o1, o2) -> {
                final VariableDeclarator a = o1.getVariables().get(0);
                final VariableDeclarator b = o2.getVariables().get(0);
                return a.getNameAsString().compareTo(b.getNameAsString());
            });
            fields.forEach(clazz::remove);
            fields.forEach(clazz::addMember);
        }

        return this;
    }

    public static ClassDefinition parse(final String source) {
        final JavaParser javaParser = new JavaParser();
        final ParseResult<CompilationUnit> parse = javaParser.parse(source);
        final CompilationUnit unit = parse.getResult().get();
        final ClassOrInterfaceDeclaration clazz = Utils.getClazz(unit);
        return new ClassDefinition(unit, clazz);
    }
}
