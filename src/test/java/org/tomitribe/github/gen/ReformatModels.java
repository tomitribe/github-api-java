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

import org.tomitribe.util.IO;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReformatModels {

    public static void main(String[] args) throws Exception {
        new ReformatModels().main();
    }

    private void main() throws Exception {
        final Project project = Project.root();
        final Package model = project.src().main().java().model();
        final List<File> files = model.files().collect(Collectors.toList());

        for (final File file : files) {
            final String source = IO.slurp(file);

            // Perform transformations
            final String modified = Stream.of(source)
                    .map(SortFields::apply)
                    .map(Reformat::apply)
                    .findFirst().get();

            model.write(file, modified);
        }
    }
}