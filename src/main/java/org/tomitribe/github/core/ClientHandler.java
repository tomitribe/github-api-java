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

import org.tomitribe.github.client.Paged;
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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

        final String body;

        if (unknown.size() == 1) {
            final Object annotatedObject = unknown.get(0);
            fields.addAll(Param.fromFields(annotatedObject));
            final boolean hasContent = fields.stream()
                    .anyMatch(param -> param.getType().equals(Param.Type.BODY));

            if (hasContent) {
                body = JsonMarshalling.toFormattedJson(annotatedObject);
            } else {
                body = null;
            }
        } else {
            body = null;
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

        if (method.getReturnType().equals(Void.class)) {
            final Request<?> request = new Request<>(path, body, queryParams, headerParams, pathParams);
            final Function<Request<?>, Object> clientMethod = getRequestMethod(method);
            return clientMethod.apply(request);
        }

        if (method.getReturnType().equals(Stream.class)) {
            final Paged paged = method.getAnnotation(Paged.class);
            final Class<?> pageType = paged.value();
            final Class<?> streamType = (Class<?>) Generics.getReturnType(method);
            final Request<?> request = new Request<>(path, body, queryParams, headerParams, pathParams).response(pageType);

            if (pageType.isArray()) {
                return client.stream(request, page -> Arrays.asList((Object[]) page));
            } else {
                final Optional<Method> arrayReturn = Stream.of(pageType.getMethods())
                        .filter(method1 -> method1.getReturnType().isArray())
                        .filter(method1 -> method1.getReturnType().getComponentType().equals(streamType))
                        .filter(method1 -> method1.getParameters().length == 0)
                        .filter(method1 -> Modifier.isPublic(method1.getModifiers()))
                        .sorted(Comparator.comparing(Method::getName))
                        .findFirst();

                if (arrayReturn.isPresent()) {
                    final Method getItemsArray = arrayReturn.get();
                    return client.stream(request, page -> {
                        try {
                            final Object[] items = (Object[]) getItemsArray.invoke(page);
                            return Arrays.asList(items);
                        } catch (final IllegalAccessException e) {
                            throw new IllegalStateException("Page items cannot be accessed via: " + getItemsArray, e);
                        } catch (InvocationTargetException e) {
                            throw new IllegalStateException("Page items cannot be accessed via: " + getItemsArray, e.getCause());
                        }
                    });
                }

                final Optional<Method> listReturn = Stream.of(pageType.getMethods())
                        .filter(method1 -> List.class.isAssignableFrom(method1.getReturnType()))
                        .filter(method1 -> Generics.getReturnType(method1).equals(streamType))
                        .filter(method1 -> method1.getParameters().length == 0)
                        .filter(method1 -> Modifier.isPublic(method1.getModifiers()))
                        .sorted(Comparator.comparing(Method::getName))
                        .findFirst();

                if (listReturn.isPresent()) {
                    final Method getItemsList = listReturn.get();
                    return client.stream(request, page -> {
                        try {
                            return (List<?>) getItemsList.invoke(page);
                        } catch (final IllegalAccessException e) {
                            throw new IllegalStateException("Page items cannot be accessed via: " + getItemsList, e);
                        } catch (InvocationTargetException e) {
                            throw new IllegalStateException("Page items cannot be accessed via: " + getItemsList, e.getCause());
                        }
                    });
                }

                throw new InvalidMethodSignatureException("Unsupported Stream method", method);
            }
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
                .collect(Collectors.toMap(
                        Param::getName, // Key mapper
                        Param::get,     // Value mapper
                        (existing, replacement) -> replacement, // Merge function
                        LinkedHashMap::new // Supplier for LinkedHashMap
                ));
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
