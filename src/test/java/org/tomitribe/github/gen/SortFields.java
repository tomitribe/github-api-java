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

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

import java.util.ArrayList;
import java.util.List;

/**
 * Ensures the order of annotations on the Resource methods
 * is consistent across all the classes
 */
public class SortFields {

    public static String apply(final String source) {
        final ClassDefinition definition = ClassDefinition.parse(source);
        final ClassOrInterfaceDeclaration clazz = definition.getClazz();

        if (clazz == null) return source;

        final List<FieldDeclaration> fields = new ArrayList<>(clazz.getFields());
        fields.sort((o1, o2) -> {
            final VariableDeclarator a = o1.getVariables().get(0);
            final VariableDeclarator b = o2.getVariables().get(0);
            return a.getNameAsString().compareTo(b.getNameAsString());
        });
        fields.forEach(clazz::remove);
        fields.forEach(clazz::addMember);
        return definition.toString();
    }

}
