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

import lombok.ToString;
import org.apache.openejb.util.Strings;
import org.tomitribe.github.gen.ParseEvents;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@ToString
public class Clazz {
    private final String name;
    private final Map<String, Field> fields = new LinkedHashMap<>();
    private final Set<String> usedBy = new TreeSet<>();
    private final ParseEvents.Event event;


    public Clazz(String name) {
        this(name, null);
    }

    public Clazz(String name, final ParseEvents.Event o) {
        if (name.startsWith("_")) {
            name = Strings.ucfirst(name.substring(1));
        }
        this.name = name;
        this.event = o;
    }

    public ParseEvents.Event getEvent() {
        return event;
    }

    public String getName() {
        return name;
    }

    public Map<String, Field> getFields() {
        return fields;
    }

    public void addUsedBy(final String type) {
        this.usedBy.add(type);
    }

    public Set<String> getUsedBy() {
        return usedBy;
    }

}
