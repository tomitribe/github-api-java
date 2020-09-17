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
import java.io.IOException;
import java.io.UncheckedIOException;

public interface Dir extends org.tomitribe.util.dir.Dir {

    default void write(final String fileName, final String contents) {
        final File file = file(fileName);
        write(file, contents);
    }

    default void write(final File file, final String contents) {
        try {
            IO.copy(IO.read(contents), file);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot write to file: " + file.getAbsolutePath(), e);
        }
    }

    default String read(final String fileName) {
        final File file = file(fileName);
        return read(file);
    }

    default String read(final File file) {
        try {
            return IO.slurp(file);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot read to file: " + file.getAbsolutePath(), e);
        }
    }
}
