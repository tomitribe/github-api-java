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
package org.tomitribe.github.app;


import org.tomitribe.github.app.events.Code;
import org.tomitribe.github.app.events.CodePage;
import org.tomitribe.github.app.events.CreatePullRequest;
import org.tomitribe.github.app.events.Link;
import org.tomitribe.github.app.events.PullRequest;
import org.tomitribe.github.app.events.RepositoriesPage;
import org.tomitribe.github.app.events.Repository;
import org.tomitribe.github.app.events.Topics;

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
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Using the `org.kohsuke.github.GitHub` client is preferred, however it doesn't
 * implement any of the preview APIs.
 *
 * Preview features we need:
 *  - Github Apps Event API
 *  - Repository Topics  https://developer.github.com/v3/repos/#replace-all-topics-for-a-repository
 *
 *  Anywhere Github requires an accept header like 'application/vnd.github.nightshade-preview+json'
 *  this will be a feature not implemented in the `org.kohsuke.github.GitHub` client and
 *  will need to be implemented here.
 */
public class GithubClient {

    private static final MediaType[] MEDIA_TYPES = {
            MediaType.APPLICATION_JSON_TYPE,
            MediaType.valueOf("application/vnd.github.v3+json"),
            /*
             * See https://developer.github.com/v3/repos/#replace-all-topics-for-a-repository
             */
            MediaType.valueOf("application/vnd.github.mercy-preview+json"),
            /*
             * Enables several Github Apps APIs
             */
            MediaType.valueOf("application/vnd.github.machine-man-preview+json"),
            /*
             * Enables 'draft' PR creation
             * https://developer.github.com/v3/pulls/#create-a-pull-request
             */
            MediaType.valueOf("application/vnd.github.shadow-cat-preview+json")
    };

    private final URI host;
    private final List<Consumer<Request>> handlers;
    private final Consumer<Object> responses;
    private final Client client;

    private GithubClient(final URI host, final Client client, final List<Consumer<Request>> handlers, final Consumer<Object> responses) {
        this.host = host;
        this.handlers = handlers;
        this.client = client;
        this.responses = responses;
    }

    /**
     * Retrieve the list of topics for the specified repository
     *
     * authentication: Github OAuth Token required
     * @param owner The user or organization that owns this repository
     * @param repo The repository name itself
     * @return the full list of topics on this repository
     */
    public List<String> getTopics(final String owner, final String repo) {
        final String content = client.target(host.resolve(String.format("/repos/%s/%s/topics", owner, repo)))
                .request()
                .accept("application/vnd.github.mercy-preview+json")
                .get(String.class);

        return JsonMarshalling.unmarshal(Topics.class, content).getNames();
    }

    public class Installations {
        public String getAccessToken(final long installationId) {
            return post(String.class, "/app/installations/%s/access_tokens", installationId);
        }
    }

    /**
     * Replaces any existing topics for this repository with the specified list of topics
     *
     * authentication: Github OAuth Token required
     * @param owner The user or organization that owns this repository
     * @param repo The repository name itself
     * @param topics List of topics to set to this repository, replacing any previously existing topics
     * @return the full list of topics now on this repository
     */
    public List<String> setTopics(final String owner, final String repo, final List<String> topics) {

        final Topics response = put(new Topics(topics), Topics.class, "/repos/%s/%s/topics", owner, repo);

        return response.getNames();
    }

    /**
     * Adds the listed topics to the existing set of topics for this repository
     *
     * authentication: Github OAuth Token required
     * @param owner The user or organization that owns this repository
     * @param repo The repository name itself
     * @param topics List of topics to add to this repository
     * @return the full list of topics now on this repository
     */
    public List<String> addTopics(final String owner, final String repo, final List<String> topics) {
        final List<String> list = new ArrayList<>();
        list.addAll(getTopics(owner, repo));
        list.addAll(topics);

        return setTopics(owner, repo, list);
    }

    /**
     * Adds the listed topics to the existing set of topics for this repository
     *
     * authentication: Github OAuth Token required
     * @param owner The user or organization that owns this repository
     * @param repo The repository name itself
     * @param topics List of topics to add to this repository
     * @return the full list of topics now on this repository
     */
    public List<String> addTopics(final String owner, final String repo, final String... topics) {
        return addTopics(owner, repo, Arrays.asList(topics));
    }

    /**
     * Retrieve the metadata for the specified repository, including any topics
     * @param owner The user or organization that owns this repository
     * @param repo The repository name itself
     * @return the full list of topics now on this repository
     */
    public Repository getRepository(final String owner, final String repo) {
        return get(Repository.class, "/repos/%s/%s", owner, repo);
    }

    /**
     * Create pull request
     *
     * https://developer.github.com/v3/pulls/#create-a-pull-request
     *
     * authentication: Github OAuth Token required
     * @param owner Required. The user or organization that owns this repository
     * @param repo Required. The repository name itself
     * @param title Required. The title of the new pull request
     * @param head Required. The name of the branch where your changes are implemented. For cross-repository
     *            pull requests in the same network, namespace head with a user like this: username:branch.
     * @param base Required. The name of the branch you want the changes pulled into. This should be an e
     *             xisting branch on the current repository. You cannot submit a pull request to one repository
     *             that requests a merge to a base of another repository.
     * @param body Nullable. The contents of the pull request.
     * @param maintainerCanModify Indicates whether maintainers can modify the pull request.
     * @param draft Indicates whether the pull request is a draft. See "Draft Pull Requests" in the GitHub
     *              Help documentation to learn more.
     * @return The resulting PullRequest data
     */
    public PullRequest createPullRequest(final String owner,
                                         final String repo,
                                         final String title,
                                         final String head,
                                         final String base,
                                         final String body,
                                         final boolean maintainerCanModify,
                                         final boolean draft) {
        final CreatePullRequest createPullRequest = CreatePullRequest.builder()
                .title(title)
                .head(head)
                .base(base)
                .body(body)
                .maintainerCanModify(maintainerCanModify)
                .draft(draft)
                .build();

        return post(createPullRequest, PullRequest.class, "/repos/%s/%s/pulls", owner, repo);
    }

    /**
     * Requires the full HTTP query string as would be supplied to a command like curl.  For example, these
     * are all valid:
     *
     *  - `searchRepositories("q=tomee")`
     *  - `searchRepositories("q=tetris+language:assembly&sort=stars&order=desc")`
     *
     * @param queryString
     * @see <a href="https://developer.github.com/v3/search/#search-repositories">Github API Repository Search params</a>
     * @see <a href="https://help.github.com/en/articles/searching-for-repositories">Repository Search Syntax</a>
     */
    public Stream<Repository> searchRepositories(final String queryString) {
        final URI target = host.resolve("/search/repositories?" + queryString);
        // new RecordPayloads<>(new File("/Users/dblevins/work/tomitribe/github-api-java/src/test/resources/GithubClientSearchRepositoriesTest"), "page")
        return stream(target, RepositoriesPage.class, RepositoriesPage::getItems);
    }

    public PullRequest getPullRequest(final String owner, final String repo, final int pullNumber) {
        return get(PullRequest.class, "/repos/%s/%s/pulls/%s", owner, repo, pullNumber);
    }

    public Stream<PullRequest> listPullRequests(final String owner, final String repo) {
        return stream(PullRequest[].class, Arrays::asList, "/repos/%s/%s/pulls", owner, repo);
    }

    /**
     * Requires the full HTTP query string as would be supplied to a command like curl.  For example, these
     * are all valid:
     *
     *  - `searchCode("q=addClass+in:file+language:js+repo:jquery/jquery")`
     *
     * @param queryString
     * @see <a href="https://developer.github.com/v3/search/#search-code">Github API Code Search params</a>
     * @see <a href="https://help.github.com/en/articles/searching-code">Code Search Syntax</a>
     */
    public Stream<Code> searchCode(final String queryString) {
        final URI target = host.resolve("/search/code?" + queryString);

        return stream(target, CodePage.class, CodePage::getItems);
    }

    private Invocation.Builder request(final String path, final Object... details) {
        final Invocation.Builder builder = client.target(resolve(path, details))
                .request()
                .accept(MEDIA_TYPES);

        final Request request = new Request(builder);
        handlers.forEach(requestConsumer -> requestConsumer.accept(request));

        return builder;
    }

    private URI resolve(final String path, final Object... details) {
        return host.resolve(String.format(path, details));
    }

    public static Consumer<Request> oauthTokenAuthentication(final String oauthToken) {
        return request -> request.header("Authorization", "token " + oauthToken);
    }

    public static Consumer<Request> githubAppAuthentication(final GithubApp githubApp) {
        final AtomicReference<Token> cache = new AtomicReference<>(githubApp.createToken());
        return request -> {
            final Token token = cache.updateAndGet(current -> current.isExpired() ? githubApp.createToken() : current);
            request.header("Authorization", "Bearer " + token.getToken());
        };
    }

    /**
     * Executes a POST to the specified path using no payload
     *
     * @param responseType  A Jsonb compatible class to marshall the response
     * @param path the API path relative to host used to build this client
     * @param details the path parameters that apply to the path
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    private <JsonbType> JsonbType post(final Class<JsonbType> responseType, final String path, final Object... details) {
        final String content = request(path, details).post(null, String.class);

        responses.accept(content);

        return JsonMarshalling.unmarshal(responseType, content);
    }

    /**
     * Executes a POST to the specified path using the specified 'post' object
     * marshalled to json via Jsonb and sent as 'application/json'
     *
     * @param responseType  A Jsonb compatible class to marshall the response
     * @param path the API path relative to host used to build this client
     * @param details the path parameters that apply to the path
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    private <JsonbType> JsonbType post(final Object post, final Class<JsonbType> responseType, final String path, final Object... details) {
        final String payload = JsonMarshalling.toFormattedJson(post);
        final Entity<String> entity = Entity.entity(payload, MediaType.APPLICATION_JSON_TYPE);

        final String content = request(path, details).post(entity, String.class);

        responses.accept(content);

        return JsonMarshalling.unmarshal(responseType, content);
    }

    /**
     * Executes a PUT to the specified path using the specified 'put' object
     * marshalled to json via Jsonb and sent as 'application/json'
     *
     * @param responseType  A Jsonb compatible class to marshall the response
     * @param path the API path relative to host used to build this client
     * @param details the path parameters that apply to the path
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    private <JsonbType> JsonbType put(final Object put, final Class<JsonbType> responseType, final String path, final Object... details) {
        final String payload = JsonMarshalling.toFormattedJson(put);
        final Entity<String> entity = Entity.entity(payload, MediaType.APPLICATION_JSON_TYPE);

        final String content = request(path, details).put(entity, String.class);

        responses.accept(content);

        return JsonMarshalling.unmarshal(responseType, content);
    }

    /**
     * Executes a PUT to the specified path using no payload
     *
     * @param responseType  A Jsonb compatible class to marshall the response
     * @param path the API path relative to host used to build this client
     * @param details the path parameters that apply to the path
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    private <JsonbType> JsonbType put(final Class<JsonbType> responseType, final String path, final Object... details) {
        final String content = request(path, details).put(null, String.class);

        responses.accept(content);

        return JsonMarshalling.unmarshal(responseType, content);
    }

    /**
     * Executes a PATCH to the specified path using the specified 'patch' object
     * marshalled to json via Jsonb and sent as 'application/json'
     *
     * @param responseType  A Jsonb compatible class to marshall the response
     * @param path the API path relative to host used to build this client
     * @param details the path parameters that apply to the path
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    private <JsonbType> JsonbType patch(final Object patch, final Class<JsonbType> responseType, final String path, final Object... details) {
        final String payload = JsonMarshalling.toFormattedJson(patch);
        final Entity<String> entity = Entity.entity(payload, MediaType.APPLICATION_JSON_TYPE);

        final String content = request(path, details).method("PATCH", entity, String.class);

        responses.accept(content);

        return JsonMarshalling.unmarshal(responseType, content);
    }

    /**
     * Executes a PATCH to the specified path using no payload
     *
     * @param responseType  A Jsonb compatible class to marshall the response
     * @param path the API path relative to host used to build this client
     * @param details the path parameters that apply to the path
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    private <JsonbType> JsonbType patch(final Class<JsonbType> responseType, final String path, final Object... details) {
        final String content = request(path, details).method("PATCH", String.class);

        responses.accept(content);

        return JsonMarshalling.unmarshal(responseType, content);
    }

    /**
     * Executes a GET to the specified path
     *
     * @param responseType  A Jsonb compatible class to marshall the response
     * @param path the API path relative to host used to build this client
     * @param details the path parameters that apply to the path
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    private <JsonbType> JsonbType get(final Class<JsonbType> responseType, final String path, final Object... details) {
        final String content = request(path, details).get(String.class);

        responses.accept(content);

        return JsonMarshalling.unmarshal(responseType, content);
    }

    /**
     * Executes a DELETE to the specified path
     *
     * @param responseType  A Jsonb compatible class to marshall the response
     * @param path the API path relative to host used to build this client
     * @param details the path parameters that apply to the path
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    private <JsonbType> JsonbType delete(final Class<JsonbType> responseType, final String path, final Object... details) {
        final String content = request(path, details).delete(String.class);

        responses.accept(content);

        return JsonMarshalling.unmarshal(responseType, content);
    }

    private class PagedSupplier<JsonbType> implements Supplier<JsonbType> {
        private final Class<JsonbType> jsonbType;
        private URI next;

        public PagedSupplier(final URI next, final Class<JsonbType> jsonbType) {
            this.next = next;
            this.jsonbType = jsonbType;
        }

        @Override
        public JsonbType get() {
            if (next == null) return null;

            final Response response = client.target(next)
                    .request()
                    .get();

            try {
                final String json = response.readEntity(String.class);
                responses.accept(json);
                final Jsonb jsonb = JsonbBuilder.create();
                return jsonb.fromJson(json, jsonbType);
            } finally {
                final Link link = Link.parse(response.getHeaderString("Link"));

                if (link.hasNext()) {
                    next = link.getNext();
                } else {
                    next = null;
                }
            }
        }
    }

    private <Page, Item> Stream<Item> stream(final Class<Page> pageClass, final Function<Page, List<Item>> getItems, final String path, final Object... details) {
        final URI target = resolve(path, details);
        return stream(target, pageClass, getItems);
    }

    private <Page, Item> Stream<Item> stream(final URI target, final Class<Page> pageClass, final Function<Page, List<Item>> getItems) {
        final PagedSupplier<Page> supplier = new PagedSupplier(target, pageClass);

        return Suppliers.asStream(supplier)
                .map(getItems)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private URI api;
        private Client client;
        private final List<Consumer<Request>> handlers = new ArrayList<>();
        private Consumer<Object> responses = o -> {
        };

        public Builder oauthToken(final String oauthToken) {
            handlers.add(GithubClient.oauthTokenAuthentication(oauthToken));
            return this;
        }

        public Builder githubApp(final GithubApp githubApp) {
            handlers.add(GithubClient.githubAppAuthentication(githubApp));
            return this;
        }

        Builder responses(final Consumer<Object> responses) {
            this.responses = responses;
            return this;
        }

        Builder requests(final Consumer<Object> responses) {
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

        public GithubClient build() {
            if (client == null) client = ClientBuilder.newClient();

            return new GithubClient(api, client, handlers, responses);
        }
    }

    /**
     * Thin abstraction so we don't bind ourselves to Jersey Client
     */
    public static class Request {
        private final Invocation.Builder builder;

        public Request(final Invocation.Builder builder) {
            this.builder = builder;
        }

        public Request accept(final MediaType... mediaTypes) {
            builder.accept(mediaTypes);
            return this;
        }

        public Request header(final String name, final Object value) {
            builder.header(name, value);
            return this;
        }
    }

}
