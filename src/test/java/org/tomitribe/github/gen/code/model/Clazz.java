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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
public class Clazz {
    private String name;
    private String title;
    private Clazz parent;
    private final List<String> componentIds = new ArrayList<>();
    private final List<Field> fields = new ArrayList<>();
    private final List<Clazz> innerClasses = new ArrayList<>();

    public Clazz addComponentId(final String componentId) {
        componentIds.add(componentId);
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

    public static class Builder {
        private String name;
        private String title;
        private Clazz parent;
        private final List<String> componentIds = new ArrayList<>();
        private final List<Field> fields = new ArrayList<>();
        private final List<Clazz> innerClasses = new ArrayList<>();

        public String getName() {
            return name;
        }

        public String getTitle() {
            return title;
        }

        public Clazz getParent() {
            return parent;
        }

        public List<String> getComponentIds() {
            return componentIds;
        }

        public List<Field> getFields() {
            return fields;
        }

        public List<Clazz> getInnerClasses() {
            return innerClasses;
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

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder parent(Clazz parent) {
            this.parent = parent;
            return this;
        }

        public Clazz build() {
            final Clazz clazz = new Clazz(name, title, parent);
            componentIds.forEach(clazz::addComponentId);
            fields.forEach(clazz::addField);
            innerClasses.forEach(clazz::addInnerClass);
            return clazz;
        }
    }
}
