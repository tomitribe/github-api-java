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

import org.tomitribe.github.gen.util.Words;
import org.tomitribe.util.IO;
import org.tomitribe.util.Join;
import org.tomitribe.util.PrintString;
import org.tomitribe.util.Strings;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateExamplesOpenApiTest {

    public static void main(String[] args) throws Exception {
        new GenerateExamplesOpenApiTest().main();
    }

    private void main() throws Exception {
        final Section test = Project.root().src().test();
        final Dir examplesOpenapi = test.resources().examplesOpenapi();

        final PrintString out = new PrintString();

        final List<String> methods = examplesOpenapi.files()
                .filter(file -> file.getName().endsWith(".json"))
                .map(this::toTestMethod)
                .collect(Collectors.toList());

        final String testClass = toTestClass(methods);

        IO.copy(IO.read(testClass),
                test.java().file("org/tomitribe/github/model/ExamplesOpenApiTest.java")
        );
    }

    private String toTestMethod(final File file) {
        String method = Words.getVariableName(file.getName().replace(".json", ""));
        if (method.equals("import")) method = "_" + method;

        final String contents;
        try {
            contents = IO.slurp(file);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        if (contents.startsWith("[")) {
            return String.format("    @Test\n" +
                    "    public void %s() throws IOException {\n" +
                    "        assertExample(ArrayList.class, \"%s\");\n" +
                    "    }\n", method, file.getName());
        } else {
            return String.format("    @Test\n" +
                    "    public void %s() throws IOException {\n" +
                    "        assertExample(HashMap.class, \"%s\");\n" +
                    "    }\n", method, file.getName());
        }

    }

    private String toTestClass(final List<String> methods) {
        return "/*\n" +
                " * Licensed to the Apache Software Foundation (ASF) under one or more\n" +
                " * contributor license agreements.  See the NOTICE file distributed with\n" +
                " * this work for additional information regarding copyright ownership.\n" +
                " * The ASF licenses this file to You under the Apache License, Version 2.0\n" +
                " * (the \"License\"); you may not use this file except in compliance with\n" +
                " * the License.  You may obtain a copy of the License at\n" +
                " *\n" +
                " *     http://www.apache.org/licenses/LICENSE-2.0\n" +
                " *\n" +
                " *  Unless required by applicable law or agreed to in writing, software\n" +
                " *  distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                " *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                " *  See the License for the specific language governing permissions and\n" +
                " *  limitations under the License.\n" +
                " */\n" +
                "package org.tomitribe.github.model;\n" +
                "\n" +
                "import org.junit.Test;\n" +
                "import org.tomitribe.github.JsonAsserts;\n" +
                "import org.tomitribe.github.Resources;\n" +
                "import org.tomitribe.github.model.*;\n" +
                "\n" +
                "import java.io.IOException;\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.HashMap;\n" +
                "\n" +
                "public class ExamplesOpenApiTest {\n" +
                "\n" +
                Join.join("\n", methods) +
                "\n" +
                "\n" +
                "    public static void assertExample(final Class<?> clazz, final String name) throws IOException {\n" +
                "        final String expected = Resources.read(ExamplesOpenApiTest.class, name);\n" +
                "        JsonAsserts.assertJsonb(expected, clazz);\n" +
                "    }\n" +
                "\n" +
                "}\n";
    }
}
