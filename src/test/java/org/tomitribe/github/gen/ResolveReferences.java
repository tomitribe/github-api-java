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

import org.tomitribe.github.gen.code.endpoint.Endpoint;
import org.tomitribe.github.gen.code.model.Clazz;
import org.tomitribe.github.gen.code.model.ClazzReference;
import org.tomitribe.github.gen.code.model.Field;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ResolveReferences {

    private final List<Clazz> classes;
    private final ComponentIndex componentIndex;
    private final List<Endpoint> endpoints;

    public ResolveReferences(final List<Clazz> classes, final ComponentIndex componentIndex, final List<Endpoint> endpoints) {
        this.classes = classes;
        this.componentIndex = componentIndex;
        this.endpoints = endpoints;
    }

    public static void resolve(final List<Clazz> classes, final ComponentIndex componentIndex, final List<Endpoint> endpoints) {
        new ResolveReferences(classes, componentIndex, endpoints);
    }

    private void resolve(final Clazz clazz) {
        if (clazz.getParent() instanceof ClazzReference) {
            clazz.setParent(resolve((ClazzReference) clazz.getParent()));
        }

        final List<Field> fields = clazz.getFields();
        for (int i = 0; i < fields.size(); i++) {
            final Field field = fields.get(i);
            final String reference = field.getReference();
            if (reference != null) {

            }
        }
    }

    private Clazz resolve(final ClazzReference clazzReference) {
        Objects.requireNonNull(clazzReference, "ClazzReference cannot be null");
        Objects.requireNonNull(clazzReference.getReference(), "Reference name cannot be null");

        final String reference = clazzReference.getReference();

        final Clazz clazz;
        if (reference.startsWith("#/components/schemas/")) {
            clazz = componentIndex.getSchemas().get(reference);
        } else if (reference.startsWith("#/components/responses/")) {
            clazz = componentIndex.getResponses().get(reference);
        } else {
            throw new IllegalStateException("Reference type not supported: " + reference);
        }

        if (clazz == null) {
            throw new NoSuchElementException("Component not found " + reference);
        }

        return clazz;
    }
}
