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
import org.tomitribe.github.core.JsonMarshalling;
import org.tomitribe.github.gen.code.model.ArrayClazz;
import org.tomitribe.github.gen.code.model.Clazz;
import org.tomitribe.github.gen.code.model.ClazzReference;
import org.tomitribe.github.gen.code.model.EnumClazz;
import org.tomitribe.github.gen.code.model.Field;
import org.tomitribe.github.gen.code.model.Name;
import org.tomitribe.github.gen.openapi.Schema;
import org.tomitribe.github.gen.util.Words;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.tomitribe.github.gen.code.model.Name.name;
import static org.tomitribe.github.gen.util.Words.getTypeName;

@Data
public class ModelGenerator {

    private final List<Clazz> classes = new ArrayList<>();
    private final String packageName;

    public ModelGenerator(final String packageName) {
        this.packageName = packageName;
    }

    public Clazz build(final Schema schema) {
        if (schema.getRef() != null) {
            final ClazzReference clazzReference = new ClazzReference(schema.getRef());
            classes.add(clazzReference);
            return clazzReference;
        }

        // TODO get the component Id in there somewhere
        // maybe that needs to happen before we get here
        final Clazz.Builder clazz = Clazz.builder().title(schema.getTitle());

        if (schema.getName() != null) {
            clazz.name(packageName + "." + getTypeName(schema.getName()));
        }

        if (schema.getAllOf() != null) {
            final List<Clazz> allOf = schema.getAllOf().stream()
                    .map(this::build)
                    .collect(Collectors.toList());

            if (isSubclass(allOf)) {
                final Clazz parent = allOf.get(0);
                final Clazz subclass = allOf.get(1);
                subclass.setParent(parent);
                subclass.setName(clazz.getName());
                return subclass;
            }
        }

        if (schema.getProperties() != null) {
            for (final Map.Entry<String, Schema> entry : schema.getProperties().entrySet()) {
                final String name = entry.getKey();
                final Schema value = entry.getValue();

                clazz.field(getField(clazz, name, value));
            }
        }

        if ("array".equals(schema.getType())) {
            final Clazz item = build(schema.getItems());
            if (item.getName() == null && schema.getName() != null) {
                item.setName(new Name(packageName, Words.getTypeName(schema.getName())));
            }
            return ArrayClazz.of(item);
        }

        if ("string".equals(schema.getType())) {
            return Clazz.of(String.class);
        }

        if ("boolean".equals(schema.getType())) {
            return Clazz.of(Boolean.class);
        }

        if ("integer".equals(schema.getType())) {
            return Clazz.of(Integer.class);
        }

        if ("date-time".equals(schema.getType())) {
            return Clazz.of(Date.class);
        }

        final Clazz build = clazz.build();
        classes.add(build);
        return build;
    }

    /**
     * In Github's OpenAPI definition they use `allOf` to express the concept
     * that the class being defined has a parent class and some fields of
     * its own.
     *
     * In this situation the allOf array will have exactly two elements.
     * The first element will be a reference to the parent, the second
     * element will be the fields that apply to this class (the subclass).
     */
    private boolean isSubclass(final List<Clazz> allOf) {
        if (allOf == null) return false;
        if (allOf.size() != 2) return false;
        if (!(allOf.get(0) instanceof ClazzReference)) return false;
        if (allOf.get(1).getFields() == null || allOf.get(1).getFields().size() == 0) return false;

        return true;
    }

    public Field getField(final Clazz.Builder clazz, final String name, final Schema value) {
        final String type = value.getType();
        if ("integer".equals(type)) {
            return Field.field(name, name(Integer.class)).build();
        }

        if ("string".equals(type) && value.getEnumValues() != null) {
            final String className = getTypeName(name);
            final EnumClazz enumClazz = EnumClazz.of(className, value.getEnumValues());
            clazz.innerClass(enumClazz);
            return Field.field(name, enumClazz.getName()).build();
        }

        if ("string".equals(type) && "uri".equals(value.getFormat())) {
            return Field.field(name, name(URI.class)).build();
        }

        if ("string".equals(type) && "date-time".equals(value.getFormat())) {
            return Field.field(name, name(Date.class)).build();
        }

        if ("string".equals(type)) {
            return Field.field(name, name(String.class)).build();
        }

        if ("boolean".equals(type)) {
            return Field.field(name, name(Boolean.class)).build();
        }

        if ("object".equals(type) && additionalProperties("string", value)) {
            return Field.field(name, name(String.class)).map(true).build();
        }

        if ("object".equals(type) && additionalProperties("integer", value)) {
            return Field.field(name, name(Long.class)).map(true).build();
        }

        if ("object".equals(type) && additionalProperties("boolean", value)) {
            return Field.field(name, name(Boolean.class)).map(true).build();
        }

        if ("object".equals(type) && additionalProperties("object", value)) {
            final String json = JsonMarshalling.toFormattedJson(value.getAdditionalProperties());
            final Schema schema = JsonMarshalling.unmarshal(Schema.class, json);
            final String singularName = name.replaceAll("s$", "");
            schema.setName(singularName);
            final Clazz mapType = build(schema);
            return Field.field(name, mapType.getName()).map(true).build();
        }

        if ("object".equals(type) && additionalPropertiesAny(value)) {
            return Field.field(name, name(Object.class)).map(true).build();
        }

        if ("object".equals(type)) {
            value.setName(name);
            final Clazz referencedClazz = build(value);
            return Field.field(name, referencedClazz.getName()).build();
        }

        if ("array".equals(type)) {
            final Schema items = value.getItems();
            if (items == null) throw new IllegalStateException("Array type missing items");

            final Field field = getField(clazz, name, items);
            field.setCollection(true);
            return field;
        }

        if (isAllOfRef(value)) {
            final String ref = value.getAllOf().get(0).getRef();
            return Field.field(name, null).reference(ref).build();
        }

        if (value.getAnyOf() != null) {
            return Field.field(name, name(Object.class)).build();
        }

        if (value.getOneOf() != null) {
            return Field.field(name, name(Object.class)).build();
        }

        if (value.getRef() != null) {
            return Field.field(name, null).reference(value.getRef()).build();
        }

        throw new UnsupportedOperationException("Unknown type: " + value);
    }

    private boolean additionalProperties(final String type, final Schema schema) {
        if (schema.getAdditionalProperties() == null) return false;
        if (!(schema.getAdditionalProperties() instanceof Map)) return false;
        final Map<String, Object> map = (Map<String, Object>) schema.getAdditionalProperties();

        return type.equals(map.get("type"));
    }

    private boolean additionalPropertiesAny(final Schema schema) {
        if (schema.getAdditionalProperties() == null) return false;
        if (!(schema.getAdditionalProperties() instanceof Boolean)) return false;
        return Boolean.TRUE.equals(schema.getAdditionalProperties());
    }

    private boolean isAllOfRef(final Schema schema) {
        if (schema.getAllOf() == null) return false;
        if (schema.getAllOf().size() != 1) return false;
        if (schema.getAllOf().get(0).getRef() == null) return false;
        return true;
    }

    private boolean isRef(final Schema schema) {
        if (schema.getRef() == null) return false;
        return true;
    }

}
