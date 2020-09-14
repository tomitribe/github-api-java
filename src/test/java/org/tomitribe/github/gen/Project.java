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

import org.tomitribe.util.JarLocation;
import org.tomitribe.util.dir.Name;

import java.io.File;
import java.util.stream.Stream;

public interface Project extends Dir {
    Src src();

    Dir target();

    @Name("pom.xml")
    File pomXml();

    static Project root() {
        final File testClasses = JarLocation.jarLocation(Project.class);
        final File target = testClasses.getParentFile();
        final File project = target.getParentFile();
        return from(project);
    }

    default Stream<String> paths() {
        return files()
                .map(File::getAbsolutePath)
                .map(s -> s.substring(get().getAbsolutePath().length() + 1))
                .sorted();
    }

    static Project from(final File project) {
        return org.tomitribe.util.dir.Dir.of(Project.class, project);
    }
}
