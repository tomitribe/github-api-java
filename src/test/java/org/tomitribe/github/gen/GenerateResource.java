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

import org.apache.openejb.util.Strings;
import org.tomitribe.util.IO;
import org.tomitribe.util.PrintString;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GenerateResource {

    private final File packageDir;
    private final String packageName;

    public GenerateResource(final File packageDir, final String packageName) {
        this.packageDir = packageDir;
        this.packageName = packageName;
    }

    public void generate(final List<ParseEvents.Event> events) {
        final PrintString out = new PrintString();
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
                "package org.tomitribe.github.app;\n" +
                "\n" +
                "import org.tomitribe.github.model.PushEvent;\n" +
                "\n" +
                "import javax.ws.rs.Consumes;\n" +
                "import javax.ws.rs.Path;\n" +
                "import javax.ws.rs.core.MediaType;\n" +
                "\n" +
                "@Path(\"/event\")\n" +
                "@Consumes(MediaType.APPLICATION_JSON)\n" +
                "public interface GithubEventsResource {\n" +
                "\n");

        for (final ParseEvents.Event event : events) {
            out.printf("\n" +
                            "    @Path(\"%s\")\n" +
                            "    default void event(final %s %s) {\n" +
                            "    }\n",
                    event.getWebhookName(),
                    event.getTypeName(),
                    Strings.ucfirst(event.getTypeName())
            );
        }

        out.print("\n}\n");

        final File dir = packageDir;
        final File file = new File(dir, "GithubEventsResource.java");
        try {
            IO.copy(out.toByteArray(), file);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot write class file: " + file.getAbsolutePath(), e);
        }
    }
}
