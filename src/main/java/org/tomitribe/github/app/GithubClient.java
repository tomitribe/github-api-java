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

import static org.tomitribe.github.app.Request.target;

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
    private final List<Consumer<Invocation.Builder>> handlers;
    private final Consumer<Object> responses;
    private final Client client;

    private GithubClient(final URI host, final Client client, final List<Consumer<Invocation.Builder>> handlers, final Consumer<Object> responses) {
        this.host = normalize(host);
        this.handlers = handlers;
        this.client = client
//                .register(new MessageLogger.RequestFilter())
//                .register(new MessageLogger.ResponseFilter())
        ;
        this.responses = responses;
    }

    private URI normalize(final URI uri) {
        final String string = uri.toASCIIString();
        if (string.endsWith("/")) return uri;
        return URI.create(string + "/");
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
        final String content = client.target(host.resolve(String.format("repos/%s/%s/topics", owner, repo)))
                .request()
                .accept("application/vnd.github.mercy-preview+json")
                .get(String.class);

        return JsonMarshalling.unmarshal(Topics.class, content).getNames();
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
        final Request<Topics> request = target("repos/{owner}/{repo}/topics", owner, repo)
                .body(new Topics(topics))
                .response(Topics.class);

        final Topics response = put(request);

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
        return get(target("repos/{owner}/{repo}").response(Repository.class));
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
                .owner(owner)
                .repo(repo)
                .title(title)
                .head(head)
                .base(base)
                .body(body)
                .maintainerCanModify(maintainerCanModify)
                .draft(draft)
                .build();

        return createPullRequest(createPullRequest);
    }

    public PullRequest createPullRequest(final CreatePullRequest createPullRequest) {
        final Request request = Request.from("repos/{owner}/{repo}/pulls", createPullRequest);


        return post(request, PullRequest.class);
    }

    /**
     * Executes a POST to the specified path using the specified 'post' object
     * marshalled to json via Jsonb and sent as 'application/json'
     *
     * @param responseType  A Jsonb compatible class to marshall the response
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    private <JsonbType> JsonbType post(final Request request, final Class<JsonbType> responseType) {
        final URI uri = request.getURI();
        final Invocation.Builder builder = client.target(resolve(uri))
                .request()
                .accept(MEDIA_TYPES);

        handlers.forEach(requestConsumer -> requestConsumer.accept(builder));

        final Entity<String> entity = request.getEntity();

        final String content = builder.post(entity, String.class);

        responses.accept(content);

        return JsonMarshalling.unmarshal(responseType, content);
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
        final URI target = host.resolve("search/repositories?" + queryString);
        // new RecordPayloads<>(new File("/Users/dblevins/work/tomitribe/github-api-java/src/test/resources/GithubClientSearchRepositoriesTest"), "page")
        return stream(target, RepositoriesPage.class, RepositoriesPage::getItems);
    }

    public PullRequest getPullRequest(final String owner, final String repo, final int pullNumber) {
        return get(target("repos/{owner}/{repo}/pulls/{pull_number}", owner, repo, pullNumber).response(PullRequest.class));
    }

    public Stream<PullRequest> listPullRequests(final String owner, final String repo) {
        return stream(PullRequest[].class, Arrays::asList, "repos/%s/%s/pulls", owner, repo);
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
        final URI target = host.resolve("search/code?" + queryString);

        return stream(target, CodePage.class, CodePage::getItems);
    }

    private URI resolve(final String path, final Object... details) {
        return host.resolve(String.format(path, details));
    }

    private URI resolve(final URI path) {
        return host.resolve(path);
    }

    public static Consumer<Invocation.Builder> oauthTokenAuthentication(final String oauthToken) {
        return request -> request.header("Authorization", "token " + oauthToken);
    }

    public static Consumer<Invocation.Builder> githubAppAuthentication(final GithubApp githubApp) {
        final AtomicReference<Token> cache = new AtomicReference<>(githubApp.createToken());
        return request -> {
            final Token token = cache.updateAndGet(current -> current.isExpired() ? githubApp.createToken() : current);
            request.header("Authorization", "Bearer " + token.getToken());
        };
    }

    /**
     * Executes a PUT to the specified path using the specified 'put' object
     * marshalled to json via Jsonb and sent as 'application/json'
     *
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    private <JsonbType> JsonbType put(final Request<JsonbType> request) {
        Objects.requireNonNull(request, () -> "Request cannot be null");
        Objects.requireNonNull(request.getResponseType(), () -> "Request responseType cannot be null");

        final URI uri = request.getURI();

        Objects.requireNonNull(uri, () -> "Request.getURI cannot be null");

        final Invocation.Builder builder = client.target(resolve(uri))
                .request()
                .accept(MEDIA_TYPES);

        handlers.forEach(requestConsumer -> requestConsumer.accept(builder));

        final Entity<String> entity = request.getEntity();

        final String content = builder.put(entity, String.class);

        responses.accept(content);

        return (JsonbType) JsonMarshalling.unmarshal(request.getResponseType(), content);
    }

    /**
     * Executes a PATCH to the specified path using the specified 'patch' object
     * marshalled to json via Jsonb and sent as 'application/json'
     *
     * @param responseType  A Jsonb compatible class to marshall the response
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    private <JsonbType> JsonbType patch(final Request request, final Class<JsonbType> responseType) {
        final URI uri = request.getURI();
        final Invocation.Builder builder = client.target(resolve(uri))
                .request()
                .accept(MEDIA_TYPES);

        handlers.forEach(requestConsumer -> requestConsumer.accept(builder));

        final Entity<String> entity = request.getEntity();

        final String content = builder.method("PATCH", entity, String.class);

        responses.accept(content);

        return JsonMarshalling.unmarshal(responseType, content);
    }

    /**
     * Executes a GET to the specified path
     *
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    private <JsonbType> JsonbType get(final Request<JsonbType> request) {
        final URI uri = request.getURI();
        final Invocation.Builder builder = client.target(resolve(uri))
                .request()
                .accept(MEDIA_TYPES);

        handlers.forEach(requestConsumer -> requestConsumer.accept(builder));

        final String content = builder.get(String.class);

        responses.accept(content);

        return JsonMarshalling.unmarshal(request.getResponseType(), content);
    }

    /**
     * Executes a DELETE to the specified path
     *
     * @param responseType  A Jsonb compatible class to marshall the response
     * @param <JsonbType> A Jsonb compatible class to marshall the response
     * @return A fully Jsonb unmarshalled instance of JsonbType
     */
    private <JsonbType> JsonbType delete(final Request request, final Class<JsonbType> responseType) {
        final URI uri = request.getURI();
        final Invocation.Builder builder = client.target(resolve(uri))
                .request()
                .accept(MEDIA_TYPES);

        handlers.forEach(requestConsumer -> requestConsumer.accept(builder));

        final String content = builder.get(String.class);

        responses.accept(content);

        return JsonMarshalling.unmarshal(responseType, content);
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


    private <Page, Item> Stream<Item> stream(final Class<Page> pageClass, final Function<Page, List<Item>> getItems, final String path, final Object... details) {
        final URI target = resolve(path, details);
        return stream(target, pageClass, getItems);
    }

    private <Page, Item> Stream<Item> stream(final URI target, final Class<Page> pageClass, final Function<Page, List<Item>> getItems) {
        final Paged<Page> supplier = new Paged(pageClass, target);

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
        private final List<Consumer<Invocation.Builder>> handlers = new ArrayList<>();
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
}
