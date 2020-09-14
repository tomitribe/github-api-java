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
package org.tomitribe.github.core;

import javax.json.bind.adapter.JsonbAdapter;
import java.util.NoSuchElementException;

/**
 * Github uses dashes in enum names which doesn't work with Java and the
 * typical converters.  This converter implementation use the toString()
 * value of the enum to map-to-from json rather than the enum.name() value.
 */
public class EnumAdapter<T extends Enum<T>> implements JsonbAdapter<T, String> {
    private final Class<T> type;

    public EnumAdapter(final Class<T> type) {
        this.type = type;
    }

    @Override
    public String adaptToJson(final T obj) throws Exception {
        return obj.toString();
    }

    @Override
    public T adaptFromJson(final String obj) throws Exception {
        for (final T value : type.getEnumConstants()) {
            if (obj.equals(value.toString())) return value;
        }
        throw new NoSuchElementException(obj);
    }
}
