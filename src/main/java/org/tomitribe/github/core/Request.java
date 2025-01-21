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

import javax.json.bind.Jsonb;
import javax.json.bind.annotation.JsonbProperty;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.net.URI;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Request<ResponseType> {

    private final String path;
    private String body;
    private Class<ResponseType> responseType;
    private final Map<String, Object> pathParams;
    private final Map<String, Object> queryParams;
    private final Map<String, Object> headerParams;

    public Request(final String path, final String body, final Map<String, Object> queryParams, final Map<String, Object> headerParams, final Map<String, Object> pathParams) {
        this.path = path;
        this.body = body;
        this.queryParams = queryParams;
        this.headerParams = headerParams;
        this.pathParams = pathParams;
    }
    private Request(final Request<?> request, final Class<ResponseType> responseType) {
        this.path = request.path;
        this.body = request.body;
        this.queryParams = new TreeMap<>(request.queryParams);
        this.headerParams = new TreeMap<>(request.headerParams);
        this.pathParams = new TreeMap<>(request.pathParams);
        this.responseType = responseType;
    }

    public Request<ResponseType> path(final String name, final Object value) {
        this.pathParams.put(name, value);
        return this;
    }

    public Request<ResponseType> query(final String name, final Object value) {
        this.queryParams.put(name, value);
        return this;
    }

    public Request<ResponseType> header(final String name, final Object value) {
        this.headerParams.put(name, value);
        return this;
    }

    public Request<ResponseType> body(final Object value) {
        this.body = JsonMarshalling.toFormattedJson(value);
        return this;
    }

    public <T> Request<T> response(final Class<T> responseType) {
        return new Request<>(this, responseType);
    }

    public String getPath() {
        return path;
    }

    public Class<ResponseType> getResponseType() {
        return responseType;
    }

    public Map<String, Object> getPathParams() {
        return pathParams;
    }

    public boolean hasBody() {
        return body != null;
    }

    public URI getURI() {
        return toUriBuilder().resolveTemplates(pathParams).build();
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public Map<String, Object> getHeaderParams() {
        return headerParams;
    }

    public String getBody() {
        return body;
    }

    public Entity<String> getEntity() {
        return hasBody() ? Entity.entity(this.getBody(), MediaType.APPLICATION_JSON_TYPE) : null;
    }

    public UriBuilder toUriBuilder() {
        final UriBuilder builder = UriBuilder.fromPath(path);

        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        return builder;
    }

    public static Request<?> target(final String path, final Object... pathParameters) {
        final Template template = new Template(path);
        final HashMap<String, Object> pathParams = new HashMap<>();

        final List<String> variables = template.getVariables();
        final ListIterator<String> iterator = variables.listIterator();
        for (int i = 0; i < pathParameters.length; i++) {
            if (!iterator.hasNext()) {
                final String message = String.format("Excess path parameters supplied.  Path %s contains %s parameters, but %s were supplied. ",
                        path, variables.size(), pathParameters.length);
                throw new IllegalStateException(message);
            }
            final String name = iterator.next();
            final Object parameter = pathParameters[i];
            pathParams.put(name, parameter);
        }

        return new Request<>(path, null, new HashMap<>(), new HashMap<>(), pathParams);
    }

    public static Request<?> from(final String pathTemplate, final Object annotatedObject) {
        final Map<AnnotatedField.Type, List<AnnotatedField>> fields = Stream.of(annotatedObject.getClass().getDeclaredFields())
                .sorted(Comparator.comparing(Field::getName))
                .map(field -> AnnotatedField.from(field, annotatedObject))
                .collect(Collectors.groupingBy(AnnotatedField::getType));

        final Map<String, Object> queryParams = mapToObject(fields, AnnotatedField.Type.QUERY);
        final Map<String, Object> headerParams = mapToObject(fields, AnnotatedField.Type.HEADER);
        final Map<String, Object> pathParams = mapToObject(fields, AnnotatedField.Type.PATH);

        final String json = fields.get(AnnotatedField.Type.BODY) == null ? null : toJson(annotatedObject);

        return new Request<>(pathTemplate, json, queryParams, headerParams, pathParams);
    }

    private static Map<String, Object> mapToObject(final Map<AnnotatedField.Type, List<AnnotatedField>> fields, final AnnotatedField.Type type) {
        final List<AnnotatedField> list = fields.getOrDefault(type, Collections.EMPTY_LIST);

        return list.stream()
                .collect(Collectors.toMap(
                        AnnotatedField::getName, // Key mapper
                        AnnotatedField::getValue, // Value mapper
                        (existing, replacement) -> replacement, // Merge function for duplicates
                        LinkedHashMap::new // Supplier for the desired Map type
                ));
    }

    private static String toJson(final Object body) {
        final Jsonb jsonb = JsonbInstances.get();
        return jsonb.toJson(body);
    }

//    public static class Builder {
//        private final String path;
//        private String body;
//        private Map<String, Object> pathParams = new HashMap<>();
//        private Map<String, Object> queryParams = new HashMap<>();
//        private Map<String, Object> headerParams = new HashMap<>();
//
//        public Builder(final String path) {
//            this.path = path;
//        }
//
//        public String path() {
//            return this.path;
//        }
//
//        public String body() {
//            return this.body;
//        }
//
//        public Map<String, Object> pathParams() {
//            return this.pathParams;
//        }
//
//        public Map<String, Object> queryParams() {
//            return this.queryParams;
//        }
//
//        public Map<String, Object> headerParams() {
//            return this.headerParams;
//        }
//
//        public Builder body(final String body) {
//            this.body = body;
//            return this;
//        }
//
//        public Builder path(final String name, final Object value) {
//            this.pathParams.put(name, value);
//            return this;
//        }
//
//        public Builder query(final String name, final Object value) {
//            this.queryParams.put(name, value);
//            return this;
//        }
//
//        public Builder header(final String name, final Object value) {
//            this.headerParams.put(name, value);
//            return this;
//        }
//
//        public Request build() {
//            return new Request(path, body, queryParams, headerParams, pathParams);
//        }
//    }

    private static class AnnotatedField {
        private final Object object;
        private final Field field;
        private final String name;
        private final Type type;

        public AnnotatedField(final Object object, final Field field, final String name, final Type type) {
            this.object = object;
            this.field = SetAccessible.on(field);
            this.name = name;
            this.type = type;
        }

        public Field getField() {
            return field;
        }

        public Object getValue() {
            try {
                return field.get(object);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Cannot get value of field: " + field.toGenericString(), e);
            }
        }

        public String getName() {
            return name;
        }

        public Type getType() {
            return type;
        }

        public static AnnotatedField from(final Field field, final Object object) {
            if (field.isAnnotationPresent(QueryParam.class)) {
                final QueryParam param = field.getAnnotation(QueryParam.class);
                return new AnnotatedField(object, field, param.value(), Type.QUERY);
            }

            if (field.isAnnotationPresent(PathParam.class)) {
                final PathParam param = field.getAnnotation(PathParam.class);
                return new AnnotatedField(object, field, param.value(), Type.PATH);
            }

            if (field.isAnnotationPresent(HeaderParam.class)) {
                final HeaderParam param = field.getAnnotation(HeaderParam.class);
                return new AnnotatedField(object, field, param.value(), Type.HEADER);
            }

            if (field.isAnnotationPresent(JsonbProperty.class)) {
                final JsonbProperty param = field.getAnnotation(JsonbProperty.class);
                return new AnnotatedField(object, field, param.value(), Type.BODY);
            }

            throw new UnsupportedOperationException("Field must be annotated QueryParam, PathParam, HeaderParam or JsonbProperty: " + field.toGenericString());
        }

        enum Type {
            BODY,
            HEADER,
            PATH,
            QUERY
        }
    }

    public static class SetAccessible<T extends AccessibleObject> implements PrivilegedAction<T> {
        private final T object;

        public SetAccessible(final T object) {
            this.object = object;
        }

        public T run() {
            object.setAccessible(true);
            return object;
        }

        public static <T extends AccessibleObject> T on(final T object) {
            return (T) AccessController.doPrivileged(new SetAccessible<>(object));
        }
    }
}
