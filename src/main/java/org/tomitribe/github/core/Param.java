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

import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Data
public class Param<T> {

    private final Supplier<Object> value;
    private final T source;
    private final String name;
    private final Type type;

    public Param(final Supplier<Object> value, final T source, final String name, final Type type) {
        this.value = value;
        this.source = source;
        this.name = type == Type.HEADER ? name.toLowerCase() : name;
        this.type = type;
    }

    public Object get() {
        return value.get();
    }

    enum Type {
        BODY,
        HEADER,
        PATH,
        QUERY,
        UNKNOWN
    }

    public static List<Param<Field>> fromFields(final Object annotatedObject) {
        final List<Param<Field>> list = new ArrayList<>();

        final Field[] fields = annotatedObject.getClass().getDeclaredFields();

        for (final Field field : fields) {
            list.add(from(field, new FieldValue(field, annotatedObject)));
        }

        return list;
    }

    public static List<Param<Parameter>> from(final Method method, final Object[] args) {
        final List<Param<Parameter>> list = new ArrayList<>();

        final Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            final Parameter parameter = parameters[i];
            final Object arg = args[i];

            list.add(from(parameter, () -> arg));
        }

        return list;
    }

    public static <T extends AnnotatedElement> Param<T> from(final T element, final Supplier<Object> value) {
        if (element.isAnnotationPresent(QueryParam.class)) {
            final QueryParam param = element.getAnnotation(QueryParam.class);
            return new Param<>(value, element, param.value(), Type.QUERY);
        }

        if (element.isAnnotationPresent(PathParam.class)) {
            final PathParam param = element.getAnnotation(PathParam.class);
            return new Param<>(value, element, param.value(), Type.PATH);
        }

        if (element.isAnnotationPresent(HeaderParam.class)) {
            final HeaderParam param = element.getAnnotation(HeaderParam.class);
            return new Param<>(value, element, param.value(), Type.HEADER);
        }

        if (element.isAnnotationPresent(JsonbProperty.class)) {
            final JsonbProperty param = element.getAnnotation(JsonbProperty.class);
            return new Param<>(value, element, param.value(), Type.BODY);
        }

        return new Param<>(value, element, null, Type.UNKNOWN);
    }

    private static class FieldValue implements Supplier<Object> {
        private final Field field;
        private final Object annotatedObject;

        public FieldValue(final Field field, final Object annotatedObject) {
            this.field = Request.SetAccessible.on(field);
            this.annotatedObject = annotatedObject;
        }

        @Override
        public Object get() {
            try {
                return field.get(annotatedObject);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Cannot get value of field: " + field.toGenericString(), e);
            }
        }
    }
}
