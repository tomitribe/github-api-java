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

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * It's our desire to be able to rename a class and have all
 * references to that class reflect the rename.  This Name
 * class allows us to effectively hand out something like a
 * mutable String so when a class is renamed, all references
 * to it are also updated.
 */
@Data
@AllArgsConstructor
public class Name {
    public static final Name OBJECT = name(Object.class);
    public static final Name STRING = name(String.class);
    public static final Name URI = name(java.net.URI.class);
    public static final Name DATE = name(Date.class);
    public static final Name BOOLEAN = name(Boolean.class);
    public static final Name INTEGER = name(Integer.class);
    public static final Name LONG = name(Long.class);

    private String packageName;
    private String simpleName;

    public static Name name(final Class<?> clazz) {
        return name(clazz.getName());
    }

    public static Name name(final String name) {
        final int dot = name.lastIndexOf(".");
        if (dot > 0) {
            final String simpleName = name.substring(dot + 1);
            final String packageName = name.substring(0, dot);
            return new Name(packageName, simpleName);
        }
        return new Name(null, name);
    }

    public String toString() {
        if (packageName == null) return simpleName;
        return packageName + "." + simpleName;
    }
}
