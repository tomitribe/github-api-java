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

import org.tomitribe.github.gen.code.endpoint.EndpointMethod;
import org.tomitribe.github.gen.code.model.ArrayClazz;
import org.tomitribe.github.gen.code.model.Clazz;
import org.tomitribe.github.gen.code.model.ClazzReference;
import org.tomitribe.github.gen.code.model.Field;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ResolveReferences {

    private final Set<Clazz> seen = new HashSet<>();
    private final ComponentIndex componentIndex;

    public ResolveReferences(final ComponentIndex componentIndex) {
        this.componentIndex = componentIndex;
    }

    public void resolveMethod(final EndpointMethod method) {
        if (method.getRequest() instanceof ClazzReference) {
            method.setRequest(resolve((ClazzReference) method.getRequest()));
        }
        if (method.getResponse() instanceof ClazzReference) {
            method.setResponse(resolve((ClazzReference) method.getResponse()));
        }

        resolve(method.getRequest());
        resolve(method.getResponse());
    }

    public void resolve(final Clazz clazz) {
        /*
         * If we have already started processing this class, do
         * not continue as that would cause an endless loop
         */
        if (isResolved(clazz)) return;

        if (clazz.getParent() instanceof ClazzReference) {
            clazz.setParent(resolve((ClazzReference) clazz.getParent()));
        }

        final List<Field> fields = clazz.getFields();
        for (int i = 0; i < fields.size(); i++) {
            final Field field = fields.get(i);
            final String reference = field.getReference();
            if (reference == null) continue;

            final Object resolved = componentIndex.resolve(reference);

            if (resolved instanceof Field) {

                final Field resolvedField = (Field) resolved;
                fields.set(i, resolvedField);

            } else if (resolved instanceof ArrayClazz) {

                final ArrayClazz resolvedClazz = (ArrayClazz) resolved;
                field.setType(resolvedClazz.getOf().getName());
                field.setCollection(true);
                field.setReference(null);
            } else if (resolved instanceof Clazz) {

                final Clazz resolvedClazz = (Clazz) resolved;
                field.setType(resolvedClazz.getName());
                field.setReference(null);
            } else {
                final String message = String.format("Expected Clazz or Field reference, found '%s' resolving to: %s", reference, resolved);
                throw new IllegalStateException(message);
            }
        }

        if (clazz instanceof ArrayClazz) {
            final ArrayClazz arrayClazz = (ArrayClazz) clazz;
            final Clazz item = arrayClazz.getOf();
            if (item instanceof ClazzReference) {
                final ClazzReference clazzReference = (ClazzReference) item;
                final Clazz resolvedItem = resolveClazz(clazzReference.getReference());
                arrayClazz.setOf(resolvedItem);
            }
        }
    }

    private boolean isResolved(final Clazz clazz) {
        return !seen.add(clazz);
    }

    private Clazz resolve(final ClazzReference clazzReference) {
        Objects.requireNonNull(clazzReference, "ClazzReference cannot be null");
        Objects.requireNonNull(clazzReference.getReference(), "Reference name cannot be null");

        final String reference = clazzReference.getReference();

        return resolveClazz(reference);
    }

    private Clazz resolveClazz(final String reference) {
        return componentIndex.resolveClazz(reference);
    }

}
