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
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Api {

    private final MediaType[] mediaTypes;
    private final URI host;
    private final List<Consumer<Invocation.Builder>> handlers;
    private final Consumer<Object> responses;
    private final Client client;

    private Api(final URI host, final Client client, final List<Consumer<Invocation.Builder>> handlers, final Consumer<Object> responses, final List<MediaType> mediaTypes) {
        this.host = normalize(host);
        this.handlers = handlers;
        this.client = client
//                .register(new MessageLogger.RequestFilter())
//                .register(new MessageLogger.ResponseFilter())
        ;
        this.responses = responses;
        this.mediaTypes = mediaTypes.toArray(new MediaType[0]);
    }

    private URI normalize(final URI uri) {
        final String string = uri.toASCIIString();
        if (string.endsWith("/")) return uri;
        return URI.create(string + "/");
    }

    public URI resolve(final String path, final Object... details) {
        return host.resolve(String.format(path, details));
    }

    public URI resolve(final URI path) {
        return host.resolve(path);
    }

    /**
     * Executes a POST to the specified path using the specified 'post' object
     * marshalled to json via Jsonb and sent as 'application/json'
     *
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    public <JsonbType> JsonbType post(final Request<JsonbType> request) {
        return execute(request, "POST");
    }

    /**
     * Executes a PUT to the specified path using the specified 'put' object
     * marshalled to json via Jsonb and sent as 'application/json'
     *
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    public <JsonbType> JsonbType put(final Request<JsonbType> request) {
        return execute(request, "PUT");
    }

    /**
     * Executes a PATCH to the specified path using the specified 'patch' object
     * marshalled to json via Jsonb and sent as 'application/json'
     *
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    public <JsonbType> JsonbType patch(final Request<JsonbType> request) {
        return execute(request, "PATCH");
    }

    /**
     * Executes a HEAD to the specified path using the specified 'patch' object
     * marshalled to json via Jsonb and sent as 'application/json'
     *
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    public <JsonbType> JsonbType head(final Request<JsonbType> request) {
        return execute(request, "HEAD");
    }

    /**
     * Executes a OPTIONS to the specified path using the specified 'patch' object
     * marshalled to json via Jsonb and sent as 'application/json'
     *
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    public <JsonbType> JsonbType options(final Request<JsonbType> request) {
        return execute(request, "OPTIONS");
    }

    /**
     * Executes a GET to the specified path
     *
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    public <JsonbType> JsonbType get(final Request<JsonbType> request) {
        return execute(request, "GET");
    }

    /**
     * Executes a DELETE to the specified path
     *
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    public <JsonbType> JsonbType delete(final Request<JsonbType> request) {
        return execute(request, "DELETE");
    }


    private <JsonbType> JsonbType execute(final Request<JsonbType> request, final String post) {
        final URI uri = request.getURI();
        final Invocation.Builder builder = client.target(resolve(uri))
                .request();

        request.getHeaderParams().forEach(builder::header);

        handlers.forEach(requestConsumer -> requestConsumer.accept(builder));

        final Entity<String> entity = request.getEntity();

        final String content = builder.method(post, entity, String.class);

        responses.accept(content);

        if (content == null || content.length() == 0) {
            return null;
        } else {
            return JsonMarshalling.unmarshal(request.getResponseType(), content);
        }
    }

    public <Page, Item> Stream<Item> stream(final Request<Page> request, final Function<Page, List<Item>> getItems) {
        final URI uri = resolve(request.getURI());
        final Invocation.Builder builder = client.target(uri)
                .request()
                .accept(mediaTypes);

        handlers.forEach(requestConsumer -> requestConsumer.accept(builder));

        final Paged<Page> supplier = new Paged<>(request.getResponseType(), uri);

        return Suppliers.asStream(supplier)
                .map(getItems)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream);
    }

    public <Page, Item> Stream<Item> stream(final Class<Page> pageClass, final Function<Page, List<Item>> getItems, final String path, final Object... details) {
        final URI target = resolve(path, details);
        return stream(target, pageClass, getItems);
    }

    public <Page, Item> Stream<Item> stream(final URI target, final Class<Page> pageClass, final Function<Page, List<Item>> getItems) {
        final Paged<Page> supplier = new Paged(pageClass, target);

        return Suppliers.asStream(supplier)
                .map(getItems)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream);
    }


    private class Paged<JsonbType> implements Supplier<JsonbType> {
        private final Class<JsonbType> jsonbType;
        private Invocation.Builder next;

        public Paged(final Class<JsonbType> jsonbType, final URI target) {
            this(jsonbType, client.target(target).request());
        }

        public Paged(final Class<JsonbType> jsonbType, final Invocation.Builder invocation) {
            this.jsonbType = jsonbType;
            this.next = invocation;
        }

        @Override
        public JsonbType get() {
            if (next == null) return null;

            final Response response = next.get();

            try {
                final String json = response.readEntity(String.class);
                responses.accept(json);
                final Jsonb jsonb = JsonbBuilder.create();
                return jsonb.fromJson(json, jsonbType);
            } finally {
                final Link link = Link.parse(response.getHeaderString("Link"));

                if (link.hasNext()) {
                    next = client.target(link.getNext()).request();
                } else {
                    next = null;
                }
            }
        }
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private URI api;
        private Client client;
        private final List<Consumer<Invocation.Builder>> handlers = new ArrayList<>();
        private final List<MediaType> mediaTypes = new ArrayList<MediaType>();

        private Consumer<Object> responses = o -> {
        };

        public Builder handler(final Consumer<Invocation.Builder> e) {
            handlers.add(e);
            return this;
        }

        public Builder accept(final MediaType... mediaTypes) {
            this.mediaTypes.addAll(Arrays.asList(mediaTypes));
            return this;
        }

        public Builder responses(final Consumer<Object> responses) {
            this.responses = responses;
            return this;
        }

        public Builder requests(final Consumer<Object> responses) {
            this.responses = responses;
            return this;
        }

        public Builder api(final URI api) {
            this.api = api;
            return this;
        }

        public Builder client(final Client client) {
            this.client = client;
            return this;
        }

        public Api build() {
            if (client == null) client = ClientBuilder.newClient();

            return new Api(api, client, handlers, responses, mediaTypes);
        }
    }
}
