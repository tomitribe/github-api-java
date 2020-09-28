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

import lombok.Getter;
import org.tomitribe.github.gen.code.model.Clazz;
import org.tomitribe.github.gen.code.model.ClazzReference;
import org.tomitribe.github.gen.code.model.Field;
import org.tomitribe.github.gen.code.model.Name;
import org.tomitribe.github.gen.openapi.Content;
import org.tomitribe.github.gen.openapi.Example;
import org.tomitribe.github.gen.openapi.OpenApi;
import org.tomitribe.github.gen.openapi.Parameter;
import org.tomitribe.github.gen.openapi.Response;
import org.tomitribe.github.gen.openapi.Schema;
import org.tomitribe.github.gen.util.Words;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Will build and index the items under the components section of OpenAPI
 */
@Getter
public class ComponentIndex {
    private final ModelGenerator modelGenerator;
    private final OpenApi openApi;
    private final Map<String, Clazz> schemas;
    private final Map<String, Clazz> responses;
    private final Map<String, Field> parameters;
    private final Map<String, Object> examples;

    public ComponentIndex(final ModelGenerator modelGenerator, final OpenApi openApi) {
        this.modelGenerator = modelGenerator;
        this.openApi = openApi;

        this.schemas = indexSchemas(modelGenerator, openApi);
        this.responses = indexResponses(modelGenerator, openApi);
        this.parameters = indexParameters(modelGenerator, openApi);
        this.examples = indexExamples(modelGenerator, openApi);
    }

    public Field resolveParameter(final String reference) {
        final Field field = getParameters().get(reference);
        if (field == null) throw new NoSuchElementException("Parameter not found " + reference);
        return field;
    }

    public Object resolveExample(final String reference) {
        final Object example = getExamples().get(reference);
        if (example == null) throw new NoSuchElementException("Example not found " + reference);
        return example;
    }

    public Clazz resolveSchema(final String reference) {
        final Clazz clazz = getSchemas().get(reference);
        if (clazz == null) throw new NoSuchElementException("Schema not found " + reference);

        if (clazz instanceof ClazzReference) {
            final ClazzReference clazzReference = (ClazzReference) clazz;
            return resolveClazz(clazzReference.getReference());
        }

        return clazz;
    }

    public Clazz resolveClazz(final String reference) {
        final Object resolved = resolve(reference);

        if (resolved instanceof ClazzReference) {
            final ClazzReference clazzReference = (ClazzReference) resolved;
            return resolveClazz(clazzReference.getReference());
        }

        if (resolved instanceof Clazz) {
            return (Clazz) resolved;
        }

        final String message = String.format("Expected Clazz reference, found '%s' resolving to: %s", reference, resolved);
        throw new IllegalStateException(message);
    }

    public Clazz resolveResponse(final String reference) {
        final Clazz clazz = getResponses().get(reference);
        if (clazz == null) throw new NoSuchElementException("Response not found " + reference);
        return clazz;
    }

    public Object resolve(final String reference) {
        if (reference.startsWith("#/components/examples/")) return resolveExample(reference);
        if (reference.startsWith("#/components/parameters/")) return resolveParameter(reference);
        if (reference.startsWith("#/components/schemas/")) return resolveSchema(reference);
        if (reference.startsWith("#/components/responses/")) return resolveResponse(reference);
        throw new UnsupportedOperationException("Unsupported component type: " + reference);
    }

    private Map<String, Object> indexExamples(final ModelGenerator modelGenerator, final OpenApi openApi) {
        if (openApi.getComponents().getExamples() == null) return Collections.EMPTY_MAP;

        final Map<String, Object> map = new HashMap<>();
        for (final Map.Entry<String, Example> entry : openApi.getComponents().getExamples().entrySet()) {
            final String name = entry.getKey();
            final Example example = entry.getValue();

            map.put(getExampleId(name), example.getValue());
        }
        return map;
    }

    private Map<String, Field> indexParameters(final ModelGenerator modelGenerator, final OpenApi openApi) {
        if (openApi.getComponents().getParameters() == null) return Collections.EMPTY_MAP;

        final Map<String, Field> map = new HashMap<>();
        final Clazz.Builder clazz = Clazz.builder().name("ParameterComponents");
        final Map<String, Parameter> parameters = openApi.getComponents().getParameters();
        for (final Map.Entry<String, Parameter> entry : parameters.entrySet()) {
            final Parameter value = entry.getValue();

            final Field field = modelGenerator.getField(clazz, entry.getKey(), value.getSchema());
            if (value.getIn() != null) {
                final Field.In in = Field.In.valueOf(value.getIn().toUpperCase());
                field.setIn(in);
            }

            map.put(getParameterId(field), field);
        }
        return map;
    }

    private Map<String, Clazz> indexResponses(final ModelGenerator modelGenerator, final OpenApi openApi) {
        if (openApi.getComponents().getResponses() == null) return Collections.EMPTY_MAP;

        final Map<String, Clazz> map = new HashMap<>();

        for (final Map.Entry<String, Response> entry : openApi.getComponents().getResponses().entrySet()) {
            final String name = entry.getKey();
            final Response response = entry.getValue();
            final Map<String, Content> content = response.getContent();
            if (content == null) continue;

            final Content jsonResponse = content.get("application/json");
            if (jsonResponse == null) continue;

            final Clazz clazz = modelGenerator.build(jsonResponse.getSchema());
            if (clazz.getName() == null) {
                final String typeName = Words.getTypeName(name);
                clazz.setName(new Name(modelGenerator.getPackageName(), typeName));
            }
            final String responseId = getResponseId(name);
            clazz.addComponentId(responseId);
            map.put(responseId, clazz);
        }
        return map;
    }

    private Map<String, Clazz> indexSchemas(final ModelGenerator modelGenerator, final OpenApi openApi) {
        if (openApi.getComponents().getSchemas() == null) return Collections.EMPTY_MAP;

        final Map<String, Clazz> map = new HashMap<>();

        for (final Schema schema : openApi.getComponents().getSchemas().values()) {
            final Clazz clazz = modelGenerator.build(schema);
            final String componentId = getSchemaId(schema);
            clazz.addComponentId(componentId);
            map.put(componentId, clazz);
        }
        return map;
    }

    private String getSchemaId(final Schema schema) {
        return "#/components/schemas/" + schema.getName();
    }

    private String getParameterId(final Field field) {
        return "#/components/parameters/" + field.getJsonName();
    }

    private String getExampleId(final String name) {
        return "#/components/examples/" + name;
    }

    private String getResponseId(final String name) {
        return "#/components/responses/" + name;
    }

    private String getHeader(final String name) {
        return "#/components/headers/" + name;
    }
}
