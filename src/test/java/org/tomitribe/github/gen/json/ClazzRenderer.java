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
package org.tomitribe.github.gen.json;

import org.apache.commons.lang3.text.WordUtils;
import org.tomitribe.github.gen.ParseEvents;
import org.tomitribe.util.IO;
import org.tomitribe.util.Join;
import org.tomitribe.util.PrintString;
import org.tomitribe.util.Strings;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClazzRenderer {

    private final File packageDir;
    private final String packageName;

    public ClazzRenderer(File packageDir, String packageName) {
        this.packageDir = packageDir;
        this.packageName = packageName;
    }

    public void render(final Clazz clazz) {
        final PrintString out = new PrintString();

        final ParseEvents.Event event = clazz.getEvent();
        final String className = clazz.getName();
        final String description;
        final String annotation;

        if (event != null && event.getDescription() != null) {
            final String s = event.getDescription();
            description = formatComment(s);
            annotation = "@GithubEvent(\"" + event.getWebhookName() + "\")\n";
        } else {
            description = "";
            annotation = "";
        }

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
                description +
                " * Used by:\n * - " + Join.join("\n * - ", clazz.getUsedBy()) + "\n" +
                " */\n" +
                "@Data\n" +
                "@Builder\n" +
                (clazz.getFields().size() > 0 ? "@AllArgsConstructor\n" : "") +
                "@NoArgsConstructor\n" +
                annotation +
                "public class " + className + " {\n\n");

        for (final Field field : clazz.getFields().values()) {

            if (event != null && event.getPayloadFields() != null) {
                final ParseEvents.PayloadField jsonField = event.getPayloadFields()
                        .stream()
                        .filter(payloadField -> payloadField.getKey().equals(field.getJsonName()))
                        .findFirst().orElse(null);

                if (jsonField != null && jsonField.getDescription() != null) {
                    out.printf("/**");
                    out.print(formatComment(jsonField.getDescription()));
                    out.printf(" */\n");
                }

            }

            out.printf("" +
                    "    @JsonbProperty(\"%s\")%n" +
                    "    private %s %s;%n%n", field.getJsonName(), field.getType(), field.getName());
        }

//        for (final Field field : clazz.getFields().values()) {
//            out.printf("" +
//                    "    public %s get%s() {%n" +
//                    "        return %s;%n" +
//                    "    }%n%n", field.getType(), toProperty(field), field.getName());
//
//            out.printf("" +
//                    "    public void set%s(final %s %s) {%n" +
//                    "        this.%s = %s;%n" +
//                    "    }%n%n", toProperty(field), field.getType(), field.getName(), field.getName(), field.getName());
//        }

        out.print("}\n");

        final File file = new File(packageDir, className + ".java");
        try {
            IO.copy(out.toByteArray(), file);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot write class file: " + file.getAbsolutePath(), e);
        }

        if (event != null) {
            renderTestCase(clazz);
        }
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

        final File dir = new File(this.packageDir.getAbsolutePath().replace("src/main/java", "src/test/java"));
        final File file = new File(dir, clazz.getName() + "Test.java");
        try {
            IO.copy(out.toByteArray(), file);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot write class file: " + file.getAbsolutePath(), e);
        }

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
