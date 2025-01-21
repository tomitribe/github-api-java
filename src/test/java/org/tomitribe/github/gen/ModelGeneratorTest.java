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

import org.junit.Test;
import org.tomitribe.github.JsonAsserts;
import org.tomitribe.github.Resources;
import org.tomitribe.github.core.JsonMarshalling;
import org.tomitribe.github.gen.code.model.Clazz;
import org.tomitribe.github.gen.code.model.ClazzReference;
import org.tomitribe.github.gen.code.model.ClazzRenderer;
import org.tomitribe.github.gen.openapi.Schema;
import org.tomitribe.util.Files;
import org.tomitribe.util.IO;
import org.tomitribe.util.dir.Dir;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.tomitribe.github.gen.ProjectAsserts.assertProject;

public class ModelGeneratorTest {

    private final Dir resources = Resources.resources(ModelGeneratorTest.class);

    @Test
    public void simple() throws Exception {
        assertScenario("simple", "pull-request-minimal.json", "pull-request-minimal");
    }

    @Test
    public void enums() throws Exception {
        assertScenario("enums", "enums.json", "enums");
    }

    @Test
    public void voteEnums() throws Exception {
        assertScenario("voteEnums", "voteEnums.json", "enums");
    }

    @Test
    public void arrays() throws Exception {
        assertScenario("arrays", "arrays.json", "arrays");
    }

    @Test
    public void stringFormats() throws Exception {
        assertScenario("stringFormats", "stringFormats.json", "stringFormats");
    }

    /**
     * For the moment we will process anyOf as 'Object'.  Considering the field
     * isn't strongly-typed, we don't have much of a choice.
     */
    @Test
    public void anyOf() throws Exception {
        assertScenario("anyOf", "anyOf.json", "anyOf");
    }

    /**
     * For the moment we will process oneOf as 'Object'.  Considering the field
     * isn't strongly-typed, we don't have much of a choice.
     */
    @Test
    public void oneOf() throws Exception {
        assertScenario("oneOf", "oneOf.json", "oneOf");
    }

    /**
     * In OpenAPI schema 'additionalProperties' indicates a map.  The key
     * will always be string.  The value of additionalProperties indicated
     * what is inside the map.  Therefore additionalProperties pointing
     * to string indicates a Map<String,String>
     *
     * This test asserts map types of String, Long and Boolean
     */
    @Test
    public void additionalPropertiesSimple() throws Exception {
        assertScenario("additionalPropertiesSimple", "additionalPropertiesSimple.json", "additionalProperties");
    }

    @Test
    public void additionalPropertiesObject() throws Exception {
        assertScenario("additionalPropertiesObject", "additionalPropertiesObject.json", "additionalProperties");
    }

    @Test
    public void additionalPropertiesTrue() throws Exception {
        assertScenario("additionalPropertiesTrue", "additionalPropertiesTrue.json", "additionalProperties");
    }


    @Test
    public void allOf2() throws Exception {

        final Dir test = this.resources.dir("allOf2");

        final String content = IO.slurp(test.file("allOf2.json"));
        final String expected = IO.slurp(test.file("expected.json"));

        final Schema schema = JsonMarshalling.unmarshal(Schema.class, content);
        schema.setName("allOf2");

        final ModelGenerator modelGenerator = getModelGenerator();

        final Clazz clazz = modelGenerator.build(schema);

        assertNotNull(clazz);
        assertNotNull(clazz.getParent());

        final List<Clazz> classes = modelGenerator.getClasses();
        final String json = JsonMarshalling.toFormattedJson(classes);
        JsonAsserts.assertJson(expected, json);
    }

    private ModelGenerator getModelGenerator() {
        return new ModelGenerator("org.tomitribe.github.model");
    }

    @Test
    public void allOf1() throws Exception {

        final Dir test = this.resources.dir("allOf1");

        final String content = IO.slurp(test.file("allOf1.json"));
        final String expected = IO.slurp(test.file("expected.json"));

        final Schema schema = JsonMarshalling.unmarshal(Schema.class, content);
        schema.setName("allOf1");

        final ModelGenerator modelGenerator = getModelGenerator();

        modelGenerator.build(schema);

        final List<Clazz> classes = modelGenerator.getClasses();
        final String json = JsonMarshalling.toFormattedJson(classes);
        JsonAsserts.assertJson(expected, json);
    }

    @Test
    public void arrayOfRefs() throws Exception {

        final Dir test = this.resources.dir("arrayOfRefs");

        final String content = IO.slurp(test.file("arrayOfRefs.json"));
        final String expected = IO.slurp(test.file("expected.json"));

        final Schema schema = JsonMarshalling.unmarshal(Schema.class, content);
        schema.setName("arrayOfRefs");

        final ModelGenerator modelGenerator = getModelGenerator();

        modelGenerator.build(schema);

        final List<Clazz> classes = modelGenerator.getClasses();
        final String json = JsonMarshalling.toFormattedJson(classes);
        JsonAsserts.assertJson(expected, json);
    }

    @Test
    public void classReference() throws Exception {
        final String content = "{\n" +
                "  \"$ref\": \"#/components/schemas/integration\"\n" +
                "}";
        final Schema schema = JsonMarshalling.unmarshal(Schema.class, content);

        final ModelGenerator modelGenerator = getModelGenerator();

        final Clazz clazz = modelGenerator.build(schema);

        assertTrue(clazz instanceof ClazzReference);

    }

    public void assertScenario(final String simple, final String name, final String name1) throws IOException {
        final Dir test = this.resources.dir(simple);

        final String content = IO.slurp(test.file(name));

        final Schema schema = JsonMarshalling.unmarshal(Schema.class, content);
        schema.setName(name1);

        final ModelGenerator modelGenerator = getModelGenerator();

        modelGenerator.build(schema);

        final Project actual = Project.from(Files.tmpdir());
        final Project expected = Project.from(test.file("after"));

        final ClazzRenderer renderer = new ClazzRenderer(actual, "org.tomitribe.github.model");

        for (final Clazz aClass : modelGenerator.getClasses()) {
            renderer.render(aClass);
        }

        assertProject(expected, actual);
    }

}
