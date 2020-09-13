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

import org.apache.commons.lang3.text.WordUtils;
import org.tomitribe.github.gen.Package;
import org.tomitribe.github.gen.Project;
import org.tomitribe.util.Join;
import org.tomitribe.util.PrintString;
import org.tomitribe.util.Strings;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClazzRenderer {

    private final Project project;
    private final String packageName;

    public ClazzRenderer(final Project project, final String packageName) {
        this.project = project;
        this.packageName = packageName;
    }

    public void render(final Clazz clazz) {
        final PrintString out = new PrintString();

        final String className = clazz.getName();

        out.print("/*\n" +
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
                "\n" +
                "package " + packageName + ";\n" +
                "\n" +
                "import javax.json.bind.annotation.JsonbProperty;\n" +
                "\n" +
                "import lombok.Data;\n" +
                "import lombok.Builder;\n" +
                "import lombok.AllArgsConstructor;\n" +
                "import lombok.NoArgsConstructor;\n" +
                "import java.util.List;\n" +
                "\n" +
                "/**\n" +
                "" +
                " */\n" +
                "@Data\n" +
                "@Builder\n" +
                (clazz.getFields().size() > 0 ? "@AllArgsConstructor\n" : "") +
                "@NoArgsConstructor\n" +
                "" +
                "public class " + className + " {\n\n");

        for (final Field field : clazz.getFields()) {
            out.printf("" +
                    "    @JsonbProperty(\"%s\")%n" +
                    "    private %s %s;%n%n", field.getJsonName(), field.getType(), field.getName());
        }

        out.print("}\n");

        final Package aPackage = project.src().main().java().packageName(packageName);
        aPackage.write(className + ".java", new String(out.toByteArray()));

        renderTestCase(clazz);
    }

    private void renderTestCase(final Clazz clazz) {
        final PrintString out = new PrintString();
        out.printf("/*\n" +
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
                "package " + packageName + ";\n" +
                "\n" +
                "import org.junit.Test;\n" +
                "\n" +
                "import java.io.IOException;\n" +
                "\n" +
                "import static org.tomitribe.github.app.events.PayloadAsserts.assertPayload;\n" +
                "\n" +
                "public class %sTest {\n" +
                "\n" +
                "    @Test\n" +
                "    public void parse() throws IOException {\n" +
                "        assertPayload(%s.class);\n" +
                "    }\n" +
                "}\n", clazz.getName(), clazz.getName());

        final Package aPackage = project.src().test().java().packageName(packageName);
        aPackage.write(clazz.getName() + "Test.java", new String(out.toByteArray()));
    }

    private static String formatComment(final String s) {
        final List<String> lines = Stream.of(s.split("\n"))
                .flatMap(s1 -> Stream.of(WordUtils.wrap(s1, 100).split("\n")))
                .map(s2 -> "\n * " + s2)
                .collect(Collectors.toList());

        return Join.join("", lines) + "\n";
    }

    private static String toProperty(final Field field) {
        final String name = field.getName();
        if (name.startsWith("_")) {
            return Strings.ucfirst(name.substring(1));
        }
        return Strings.ucfirst(name);
    }
}
