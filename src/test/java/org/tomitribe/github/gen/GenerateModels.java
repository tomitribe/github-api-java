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

import org.tomitribe.github.gen.code.model.Clazz;
import org.tomitribe.github.gen.code.model.ClazzReference;
import org.tomitribe.github.gen.code.model.Field;
import org.tomitribe.github.gen.openapi.Method;
import org.tomitribe.github.gen.openapi.OpenApi;
import org.tomitribe.github.gen.openapi.Parameter;
import org.tomitribe.github.gen.openapi.Path;
import org.tomitribe.github.gen.openapi.Schema;
import org.tomitribe.util.IO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GenerateModels {

    private final List<Clazz> classes = new ArrayList<Clazz>();
    private final Map<String, Clazz> components = new HashMap<>();
    private final Clazz parameters;
    private Map<String, Schema> schemas;
    private final OpenApi openApi;
    private final ModelGenerator modelGenerator;

    public static void main(String[] args) throws Exception {
        new GenerateModels().main();
    }

    private final Project project = Project.root();

    public GenerateModels() throws IOException {
        final Gen gen = project.src().test().resources().gen();

        this.openApi = OpenApi.parse(IO.slurp(gen.getGithubOpenApiJson()));
        this.schemas = openApi.getComponents().getSchemas();
        this.modelGenerator = new ModelGenerator("org.tomitribe.github.model");

        for (final Schema schema : this.schemas.values()) {
            final Clazz clazz = modelGenerator.build(schema);
            clazz.addComponentId(getComponentId(schema));
        }

        this.parameters = getParameters();

        {
            final List<Method> methods = openApi.getPaths().values().stream()
                    .flatMap(Path::getMethods)
                    .collect(Collectors.toList());

            for (final Method method : methods) {
                final String target = String.format("%s %s", method.getName(), method.getPath().getName());
                final Clazz.Builder clazz = Clazz.builder().endpoint(target);
                
            }
        }

        this.classes.addAll(modelGenerator.getClasses());

        { // Create an index of all the componentIds
            for (final Clazz clazz : this.classes) {
                for (final String componentId : clazz.getComponentIds()) {
                    components.put(componentId, clazz);
                }
            }
        }
    }

    private Clazz getParameters() {
        final Clazz.Builder clazz = Clazz.builder().name("ParameterComponents");
        final Map<String, Parameter> parameters = openApi.getComponents().getParameters();
        for (final Map.Entry<String, Parameter> entry : parameters.entrySet()) {
            final Parameter value = entry.getValue();

            final Field field = modelGenerator.getField(clazz, entry.getKey(), value.getSchema());
            if (value.getIn() != null) {
                final Field.In in = Field.In.valueOf(value.getIn().toUpperCase());
                field.setIn(in);
            }
            clazz.field(field);
        }
        final Clazz build = clazz.build();
        return build;
    }


    private void main() throws Exception {

        resolveReferences();


        final List<Clazz> withDuplicates = classes;
        for (final Clazz clazz : withDuplicates) {
            if (clazz.getFields() == null) continue;
            clazz.getFields().sort(Comparator.comparing(Field::getName));
        }

        final List<Clazz> classes = withDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());

        pruneConflicts(classes);

        final Map<String, List<Clazz>> conflicts = conflicts(classes);

        final Consumer<String> print = s -> System.out.println("  - " + s);
        for (final Map.Entry<String, List<Clazz>> entry : conflicts.entrySet()) {
            System.out.println("");
            System.out.println("--------------------------------");
            System.out.println(entry.getKey());

            final List<Clazz> list = entry.getValue();
            list.sort(Comparator.comparing(clazz -> clazz.getFields().size()));
            final Clazz largest = list.remove(list.size() - 1);
            for (final Clazz clazz : list) {
                System.out.println("-");
                final Diff diff = Diff.diff(largest, clazz);
                diff.common.forEach(print);
                diff.conflict.forEach(print);
                diff.missing.forEach(print);
                diff.unused.forEach(print);
            }

        }
        for (final Clazz clazz : classes) {
            System.out.println(clazz);
        }
    }

    private void resolveReferences() {
        for (final Clazz clazz : classes) {

            // Resolve parent
            final Clazz parent = clazz.getParent();
            if (parent instanceof ClazzReference) {
                final String reference = ((ClazzReference) parent).getReference();
                final Clazz component = getComponent(clazz, reference);
                component.getId().getReferences().addAll(parent.getId().getReferences());
                clazz.setParent(component);
                clazz.getId().addReference(component.getId());
            }

            for (final Field field : clazz.getFields()) {
                final String reference = field.getReference();
                if (reference == null) continue;
                final Clazz component = getComponent(clazz, reference);
                field.setType(component.getName());
                clazz.getId().addReference(component.getId());
            }
        }
    }

    private Clazz getComponent(final Clazz clazz, final String reference) {
        final Clazz component = components.get(reference);
        if (component == null) {
            throw new NoSuchElementException(String.format("Component '%s' cannot be found.  Referenced by %s", reference, clazz.getName()));
        }
        return component;
    }

    private void pruneConflicts(final List<Clazz> classes) {
        final Map<String, List<Clazz>> conflicts = conflicts(classes);

        for (final List<Clazz> list : conflicts.values()) {
            list.sort(Comparator.comparing(clazz -> clazz.getFields().size()));
            final Clazz largest = list.remove(list.size() - 1);

            final List<Clazz> notNeeded = list.stream()
                    .filter(largest::isSupersetOf)
                    .collect(Collectors.toList());

            /*
             * Delete the duplicate (subset) classes we
             * don't need and add capture any componentIds
             * to the class that will be replacing them
             */
            notNeeded.stream()
                    .peek(list::remove)
                    .peek(classes::remove)
                    .map(Clazz::getComponentIds)
                    .flatMap(Collection::stream)
                    .forEach(largest::addComponentId);
        }
    }

    private Map<String, List<Clazz>> conflicts(final List<Clazz> classes) {
        final Map<String, List<Clazz>> map = classes.stream()
                .filter(clazz -> clazz.getName() != null)
                .collect(Collectors.groupingBy(clazz -> clazz.getName().toString()));

        final Map<String, List<Clazz>> conflicts = map.values().stream()
                .filter(clazzes -> clazzes.size() > 1)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(clazz -> clazz.getName().toString()));
        return conflicts;
    }

    private String getComponentId(final Schema schema) {
        return "#/components/schemas/" + schema.getName();
    }


    public static class Diff {
        final List<String> unused = new ArrayList<String>();
        final List<String> missing = new ArrayList<String>();
        final List<String> conflict = new ArrayList<String>();
        final List<String> common = new ArrayList<String>();

        /**
         * Analyzes if the class supplied is effectively a subset of this
         * class.
         *
         * To be considered a subset, the class supplied must have all the
         * fields of this class by json name and type.  This class may have
         * more fields (superset).
         */
        public static Diff diff(final Clazz clazzA, final Clazz clazzB) {
            final Diff diff = new Diff();

            final Map<String, Field> aFields = clazzA.getFields().stream()
                    .collect(Collectors.toMap(Field::getJsonName, Function.identity()));

            final Map<String, Field> bFields = clazzB.getFields().stream()
                    .collect(Collectors.toMap(Field::getJsonName, Function.identity()));

            for (final String name : bFields.keySet()) {
                final Field fieldA = aFields.remove(name);
                final Field fieldB = bFields.get(name);

                if (fieldA == null) {
                    diff.missing.add("Missing:\t" + id(fieldB));
                    continue;
                }

                final String a = id(fieldA);
                final String b = id(fieldB);

                if (!a.equals(b)) {
                    diff.conflict.add(String.format("Conflict:\t%s\t%s", a, b));
                } else {
                    diff.common.add(String.format("Common:\t%s", a));
                }
            }

            for (final Field field : aFields.values()) {
                diff.unused.add(String.format("Unused:\t%s", id(field)));
            }

            return diff;
        }

        private static String id(final Field field) {
            if (field.isCollection()) {
                return String.format("%s:List<%s>:%s", field.getIn(), field.getType(), field.getJsonName());
            }

            if (field.isMap()) {
                return String.format("%s:Map<String,%s>:%s", field.getIn(), field.getType(), field.getJsonName());
            }

            return String.format("%s:%s:%s", field.getIn(), field.getType(), field.getJsonName());
        }
    }
}
