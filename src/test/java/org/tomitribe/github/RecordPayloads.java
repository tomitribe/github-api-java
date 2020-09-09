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
package org.tomitribe.github;

import org.apache.openejb.loader.Files;
import org.tomitribe.github.core.JsonMarshalling;
import org.tomitribe.util.IO;
import org.tomitribe.util.JarLocation;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class RecordPayloads<T> implements Consumer<T> {

    private final AtomicInteger count = new AtomicInteger();
    private final File dir;
    private final String prefix;

    public RecordPayloads(final File dir, final String prefix) {
        this.dir = dir;
        this.prefix = prefix;
    }

    public static RecordPayloads prefix(final String prefix) {
        final StackTraceElement caller = getCaller();
        final File testClasses = JarLocation.jarLocation(RecordPayloads.class);
        final File target = testClasses.getParentFile();
        final File module = target.getParentFile();
        final String className = caller.getClassName().replaceFirst(".*\\.", "");
        final File resources = new File(module, String.format("src/test/resources/%s", className));
        Files.mkdirs(resources);

        return new RecordPayloads(resources, prefix);
    }

    private static StackTraceElement getCaller() {
        final Throwable throwable = new Exception().fillInStackTrace();
        StackTraceElement stackTraceElement = null;
        final StackTraceElement[] stackTrace = throwable.getStackTrace();
        int i = 0;
        for (; i < stackTrace.length; i++) {
            final StackTraceElement element = stackTrace[i];
            if (!element.getClassName().equals(RecordPayloads.class.getName())) continue;
            stackTraceElement = stackTrace[i + 1];
        }
        return stackTraceElement;
    }

    @Override
    public void accept(final T t) {
        final File file = new File(dir, prefix + count.incrementAndGet() + ".json");
        final String json = JsonMarshalling.toFormattedJson(t);
        try {
            IO.copy(IO.read(json), file);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
