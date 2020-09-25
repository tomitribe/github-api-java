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

import org.tomitribe.github.client.Preview;
import org.tomitribe.github.client.Previews;
import org.tomitribe.util.Join;
import org.tomitribe.util.reflect.Generics;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ClientHandler implements InvocationHandler {

    private final Api client;

    public ClientHandler(final Api client) {
        this.client = client;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) {
        final Path pathAnnotation = method.getAnnotation(Path.class);
        final String path = pathAnnotation.value().replaceAll("^/", "");

        final List<Param<Parameter>> params = Param.from(method, args);

        final List<Object> unknown = params.stream()
                .filter(parameterParam -> parameterParam.getType().equals(Param.Type.UNKNOWN))
                .map(Param::get)
                .collect(Collectors.toList());

        if (unknown.size() > 1) {
            throw new InvalidMethodSignatureException("Client interface methods may only have one non-annotated parameter. Found " + unknown.size(), method);
        }

        final List<Param<Field>> fields = new ArrayList<>();

        if (unknown.size() == 1) {
            fields.addAll(Param.fromFields(unknown.get(0)));
        }

        final Map<String, Object> pathParams = mapParams(params, fields, Param.Type.PATH);
        final Map<String, Object> queryParams = mapParams(params, fields, Param.Type.QUERY);
        final Map<String, Object> headerParams = mapParams(params, fields, Param.Type.HEADER);

        final Set<String> accept = getAccept(headerParams);
        accept.add("application/json");

        if (method.isAnnotationPresent(Preview.class)) {
            addPreviews(accept, method.getAnnotation(Preview.class));
        }

        if (method.isAnnotationPresent(Previews.class)) {
            addPreviews(accept, method.getAnnotation(Previews.class).value());
        }

        headerParams.put("accept", Join.join(", ", accept));

        // TODO support http entities
        final String body = null;

        if (method.getReturnType().equals(Void.class)) {
            final Request<?> request = new Request<>(path, body, queryParams, headerParams, pathParams);
            final Function<Request<?>, Object> clientMethod = getRequestMethod(method);
            return clientMethod.apply(request);
        }

        if (method.getReturnType().equals(Stream.class)) {
            final Class<?> streamType = Generics.getReturnType(method).getClass();
            final Request<?> request = new Request<>(path, body, queryParams, headerParams, pathParams).response(streamType);
            final Function<Request<?>, Object> clientMethod = getRequestMethod(method);
            return clientMethod.apply(request);
        }

        final Class<?> returnType = method.getReturnType();
        final Request<?> request = new Request<>(path, body, queryParams, headerParams, pathParams).response(returnType);
        final Function<Request<?>, Object> clientMethod = getRequestMethod(method);
        return clientMethod.apply(request);
    }

    private void addPreviews(final Set<String> accept, final Preview... previews) {
        for (final Preview preview : previews) {
            final String type = String.format("application/vnd.github.%s-preview+json", preview.value());
            accept.add(type);
        }
    }

    private Set<String> getAccept(final Map<String, Object> headerParams) {
        final Object accept = headerParams.get("accept");

        if (accept == null) return new HashSet<>();
        final String[] values = (accept + "").split(" *, *");
        return new HashSet<>(Arrays.asList(values));
    }

    private Map<String, Object> mapParams(final List<Param<Parameter>> params, final List<Param<Field>> fields, final Param.Type path) {
        return Stream.concat(fields.stream(), params.stream())
                .filter(param -> path.equals(param.getType()))
                .filter(param -> param.get() != null)
                .collect(Collectors.toMap(Param::getName, Param::get));
    }

    private Function<Request<?>, Object> getRequestMethod(final Method method) {
        if (method.isAnnotationPresent(GET.class)) return client::get;
        if (method.isAnnotationPresent(POST.class)) return client::post;
        if (method.isAnnotationPresent(PUT.class)) return client::put;
        if (method.isAnnotationPresent(DELETE.class)) return client::delete;
        if (method.isAnnotationPresent(PATCH.class)) return client::patch;
        if (method.isAnnotationPresent(OPTIONS.class)) return client::options;
        if (method.isAnnotationPresent(HEAD.class)) return client::head;
        throw new InvalidMethodSignatureException("Method must be annotated with one of @GET, @POST, @PUT, @DELETE, @PATCH, @OPTIONS or @HEAD", method);
    }
}