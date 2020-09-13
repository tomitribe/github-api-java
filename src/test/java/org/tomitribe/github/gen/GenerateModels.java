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

import lombok.Data;
import org.tomitribe.github.gen.code.model.Clazz;
import org.tomitribe.github.gen.code.model.Field;
import org.tomitribe.github.gen.openapi.OpenApi;
import org.tomitribe.github.gen.openapi.Schema;
import org.tomitribe.util.IO;
import org.tomitribe.util.Strings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GenerateModels {

    private final Map<String, Clazz> classes = new HashMap<>();
    private final Map<String, Clazz> refs = new HashMap<>();
    private Map<String, Schema> schemas;
    private final OpenApi openApi;

    public static void main(String[] args) throws Exception {
        new GenerateModels().main();
    }

    private final Project project = Project.root();

    public GenerateModels() throws IOException {
        final Gen gen = project.src().test().resources().gen();

        this.openApi = OpenApi.parse(IO.slurp(gen.getGithubOpenApiJson()));
        this.schemas = openApi.getComponents().getSchemas();
    }

    private void main() throws Exception {

        final Collection<Schema> schemas = this.schemas.values();

        // Make initial objects for all top-level components
        for (final Schema schema : schemas) {
            final String id = getComponentId(schema);
            final String name = Strings.camelCase(schema.getName());
            final Clazz.Builder builder = Clazz.builder()
                    .name(name)
                    .componentId(id);
            final Clazz clazz = builder.build();

            add(clazz);
        }

        final List<Component> components = new ArrayList<Component>();

        // Make initial objects for all top-level components
        for (final Schema schema : schemas) {
            final String id = getComponentId(schema);
            final String name = Strings.camelCase(schema.getName());
            final Clazz.Builder builder = Clazz.builder()
                    .name(name)
                    .componentId(id);
            final Clazz clazz = builder.build();
            components.add(new Component(clazz, schema));
            add(clazz);
        }

        for (final Component component : components) {
            process(component);
        }

        System.out.println(refs.size());
    }

    private void process(final Component component) {
        final Schema schema = component.schema;
        final Clazz clazz = component.clazz;

        if (schema.getAllOf() != null) {
            expandParentClazz(component);
        }

        if (schema.getProperties() != null) {
            applyProperties(clazz, schema.getProperties());
        }
    }


    @Data
    static class Component {
        private final Clazz clazz;
        private final Schema schema;
    }

    private String getComponentId(final Schema schema) {
        return "#/components/schemas/" + schema.getName();
    }

    private void add(final Clazz clazz) {
        refs.put(clazz.getComponentId(), clazz);
        classes.put(clazz.getName(), clazz);
    }

    private Clazz get(final String id) {
        if (refs.containsKey(id)) return refs.get(id);
        if (classes.containsKey(id)) return classes.get(id);
        return null;
    }

    /**
     * When there is a parent class the allOf will contain two entries, one to the parent class
     * and then the actual schema definition for this class (the subclass)
     */
    private void expandParentClazz(final Component component) {
        final Schema schema = component.schema;
        final Clazz clazz = component.clazz;

        final List<Schema> allOf = schema.getAllOf();

        final List<Schema> parents = allOf.stream()
                .filter(schema1 -> schema1.getRef() != null)
                .collect(Collectors.toList());

        final List<Schema> children = allOf.stream()
                .filter(schema1 -> schema1.getRef() == null)
                .filter(schema1 -> schema1.getProperties() != null)
                .collect(Collectors.toList());

        if (parents.size() + children.size() != allOf.size()) throw new IllegalStateException("There are schema elements we do not understand");
        if (parents.size() > 1) throw new IllegalStateException("There are multiple parents");
        if (parents.size() == 0) throw new IllegalStateException("There are no parents");
        if (children.size() > 1) throw new IllegalStateException("There are multiple children");
        if (children.size() == 0) throw new IllegalStateException("There are no children");

        final Schema parent = parents.get(0);
        final Schema us = children.get(0);

        final String parentRef = parent.getRef();
        final Clazz parentClazz = refs.get(parentRef);

        if (parentClazz == null) throw new IllegalStateException("Parent not found " + parentRef);

        clazz.setParent(parentClazz);

        applyProperties(clazz, us.getProperties());

    }

    private void applyProperties(final Clazz clazz, final Map<String, Schema> properties) {
        for (final Map.Entry<String, Schema> entry : properties.entrySet()) {
            applyProperty(clazz, entry.getKey(), entry.getValue());
        }
    }

    private void applyProperty(final Clazz clazz, final String name, final Schema value) {
        final String javaName = Strings.lcfirst(Strings.camelCase(name));

        if (value.getAnyOf() != null) throw new UnsupportedOperationException("Schema anyOf not supported: " + value);
        if (value.getOneOf() != null) throw new UnsupportedOperationException("Schema oneOf not supported: " + value);
        if (value.getAdditionalProperties() != null) throw new UnsupportedOperationException("Schema additionalProperties not supported: " + value);
        System.out.println(javaName);
        /*
         * Does the schema refer to another top-level component?
         */
        if (value.getAllOf() != null && value.getAllOf().size() == 1) {
            final Schema schema = value.getAllOf().get(0);
            final String ref = schema.getRef();
            if (ref == null) throw new IllegalStateException("Expected reference. field=" + name);

            final Clazz target = refs.get(ref);
            if (target == null) throw new IllegalStateException("Reference not found: " + ref);

            clazz.getFields().add(new Field(javaName, name, target.getName(), false));
            return;
        }

        if (value.getType() == null) throw new IllegalStateException("Schemas should have a reference or a type " + value);

        final String type = value.getType();

        if ("string".equals(type)) {
            clazz.getFields().add(new Field(javaName, name, "String", false));
            return;
        }

        if ("integer".equals(type)) {
            clazz.getFields().add(new Field(javaName, name, "Integer", false));
            return;
        }

        if ("array".equals(type)) {
            if (value.getItems() == null) throw new IllegalStateException("Expected array item schema");

            final Schema itemSchema = value.getItems();

            // Ensure there isn't data hiding in places we don't look
            if (itemSchema.getRef() != null) throw new UnsupportedOperationException("Schema references for arrays is unsupported");
            if (itemSchema.getAnyOf() != null) throw new UnsupportedOperationException("Schema anyOf not supported: " + value);
            if (itemSchema.getOneOf() != null) throw new UnsupportedOperationException("Schema oneOf not supported: " + value);
            if (itemSchema.getAdditionalProperties() != null) throw new UnsupportedOperationException("Schema additionalProperties not supported: " + value);

            System.out.println(itemSchema.getType());

            final String nes = name.replaceAll("s$", "");
            clazz.getFields().add(new Field(javaName, name, "Integer", true));
            return;
        }

        System.out.println(type);

    }
}
