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

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.json.bind.annotation.JsonbTransient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class Clazz {
    @JsonbTransient
    private final Id id;

    private Name name;
    @EqualsAndHashCode.Exclude
    private String title;
    private Clazz parent;
    @EqualsAndHashCode.Exclude
    private final Set<String> componentIds = new HashSet<>();
    @JsonbTransient
    private final Set<String> endpoints = new HashSet<>();
    private final List<Field> fields = new ArrayList<>();
    private final List<Clazz> innerClasses = new ArrayList<>();
    @JsonbTransient
    private boolean paged;
    @JsonbTransient
    private final List<Example> examples = new ArrayList<>();

    public Clazz(final Id id, final Name name, final String title, final Clazz parent) {
        this.id = id != null ? id : Id.next();
        this.name = name;
        this.title = title;
        this.parent = parent;
        this.id.owner = this;
    }

    public Clazz addComponentId(final String componentId) {
        componentIds.add(componentId);
        return this;
    }

    public Clazz addEndpoint(final String method) {
        endpoints.add(method);
        return this;
    }

    public Clazz addField(final Field field) {
        fields.add(field);
        return this;
    }

    public Clazz addInnerClass(final Clazz innerClass) {
        innerClasses.add(innerClass);
        return this;
    }

    public static Clazz of(final Class<?> clazz) {
        return new Clazz(Id.next(), Name.name(clazz), null, null) {
            @Override
            public void setName(final Name name) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void setParent(final Clazz parent) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void setTitle(final String title) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Clazz addComponentId(final String componentId) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Clazz addEndpoint(final String method) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Clazz addField(final Field field) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Clazz addInnerClass(final Clazz innerClass) {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * There are roughly 1700 classes that get generated from the Github OpenAPI definition.
     *
     * This makes debugging exceptions in the generation process exceptionally hard.  It is
     * unrealistic to put a breakpoint and step past it potentially 1700 times.  To make
     * debugging survivable, this Id class gives each class instance a predictable number
     * that can be used to create conditional breakpoints.
     *
     * For example let's say an exception is being thrown from the EndpointRenderer and a
     * breakpoint on the related catch clause shows a Clazz instance with id 1357 is missing
     * some necessary data.  Let's also say that there's a specific line in ModelGenerator
     * where that data should be filled in.  We would put a breakpoint on that line in
     * ModelGenerator with the condition `clazz.getId().getId() == 1357`
     */
    public static class Id {
        private static final AtomicInteger ids = new AtomicInteger(1000);

        private final int id;
        private Clazz owner;
        private final List<Id> references = new ArrayList<>();

        public Id(final int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public Clazz getOwner() {
            return owner;
        }

        public List<Id> getReferences() {
            return references;
        }

        public void addReference(final Id id) {
            this.references.add(id);
        }

        public static Id next() {
            return new Id(ids.getAndIncrement());
        }

        @Override
        public String toString() {
            return id + ":" + references.size();
        }
    }

    /**
     * Analyzes if the class supplied is effectively a subset of this
     * class.
     *
     * To be considered a subset, the class supplied must have all the
     * fields of this class by json name and type.  This class may have
     * more fields (superset).
     */
    public boolean isSupersetOf(final Clazz that) {
        final Map<String, Field> thisFields = this.getFields().stream()
                .collect(Collectors.toMap(Field::getJsonName, Function.identity()));

        final Map<String, Field> thatFields = that.getFields().stream()
                .collect(Collectors.toMap(Field::getJsonName, Function.identity()));

        if (thatFields.size() > thisFields.size()) return false;

        for (final Map.Entry<String, Field> entry : thatFields.entrySet()) {
            final Field a = thisFields.get(entry.getKey());
            final Field b = entry.getValue();

            if (a == null) return false;
            if (equals(Field::getJsonName, a, b)) return false;
            if (equals(Field::getType, a, b)) return false;
            if (equals(Field::getIn, a, b)) return false;
        }

        return true;
    }

    private <T> boolean equals(final Function<T, ?> method, final T objectA, final T objectB) {
        final Object a = method.apply(objectA);
        final Object b = method.apply(objectB);

        if (a == null && b == null) return true;
        if (a == null) return false;
        return a.equals(b);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Id id = Id.next();
        private Name name;
        private String title;
        private Clazz parent;
        private final Set<String> componentIds = new HashSet<>();
        private final Set<String> endpoint = new HashSet<>();
        private final List<Field> fields = new ArrayList<>();
        private final List<Clazz> innerClasses = new ArrayList<>();
        private final List<Clazz> references = new ArrayList<>();

        public List<Clazz> getReferences() {
            return references;
        }

        public Id getId() {
            return id;
        }

        public Name getName() {
            return name;
        }

        public String getTitle() {
            return title;
        }

        public Clazz getParent() {
            return parent;
        }

        public Set<String> getComponentIds() {
            return componentIds;
        }

        public List<Field> getFields() {
            return fields;
        }

        public List<Clazz> getInnerClasses() {
            return innerClasses;
        }

        public Set<String> getEndpoint() {
            return endpoint;
        }

        public Builder field(final Field field) {
            fields.add(field);
            return this;
        }

        public Builder innerClass(final Clazz innerClass) {
            innerClasses.add(innerClass);
            return this;
        }

        public Builder componentId(final String componentId) {
            componentIds.add(componentId);
            return this;
        }

        public Builder endpoint(final String endpoint) {
            this.endpoint.add(endpoint);
            return this;
        }

        public Builder name(final String name) {
            this.name = Name.name(name);
            return this;
        }

        public Builder title(final String title) {
            this.title = title;
            return this;
        }

        public Builder parent(final Clazz parent) {
            this.parent = parent;
            return this;
        }

        public Clazz build() {
            final Clazz clazz = new Clazz(id, name, title, parent);
            componentIds.forEach(clazz::addComponentId);
            endpoint.forEach(clazz::addEndpoint);
            fields.forEach(clazz::addField);
            innerClasses.forEach(clazz::addInnerClass);
            return clazz;
        }
    }
}
