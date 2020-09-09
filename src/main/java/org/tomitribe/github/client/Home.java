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
package org.tomitribe.github.client;

import org.tomitribe.util.dir.Name;

import java.io.File;

/**
 * Strongly-typed version of user.home
 */
public interface Home {

    @Name(".ssh")
    File ssh();

    static Home get() {
        return from(System.getProperty("user.home"));
    }

    static Home from(final String path) {
        return from(new File(path).getAbsoluteFile());
    }

    static Home from(final File file) {
        return org.tomitribe.util.dir.Dir.of(Home.class, file);
    }
}
