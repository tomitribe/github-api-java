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
package org.tomitribe.github.client;


import org.tomitribe.github.core.ApiOld;
import org.tomitribe.github.core.Request;
import org.tomitribe.github.model.Code;
import org.tomitribe.github.model.CodePage;
import org.tomitribe.github.model.CreatePullRequest;
import org.tomitribe.github.model.PullRequest;
import org.tomitribe.github.model.RepositoriesPage;
import org.tomitribe.github.model.Repository;
import org.tomitribe.github.model.Topics;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.tomitribe.github.core.Request.from;
import static org.tomitribe.github.core.Request.target;

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

    private final ApiOld client;

    private GithubClient(final ApiOld client) {
        this.client = client;
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
        final Topics topics = client.get(target("repos/{owner}/{repo}/topics", owner, repo).response(Topics.class));
        return topics.getNames();
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

        final Topics response = client.put(request);

        return response.getNames();
    }

    public List<String> addTopics(final String owner, final String repo, final List<String> topics) {
        final List<String> list = new ArrayList<>();
        list.addAll(getTopics(owner, repo));
        list.addAll(topics);

        return setTopics(owner, repo, list);
    }

    public List<String> addTopics(final String owner, final String repo, final String... topics) {
        return addTopics(owner, repo, Arrays.asList(topics));
    }

    public Repository getRepository(final String owner, final String repo) {
        return client.get(target("repos/{owner}/{repo}").response(Repository.class));
    }

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
        return client.post(from("repos/{owner}/{repo}/pulls", createPullRequest)
                .response(PullRequest.class));
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
        final URI target = client.resolve("search/repositories?" + queryString);
        return client.stream(target, RepositoriesPage.class, RepositoriesPage::getItems);
    }

    public PullRequest getPullRequest(final String owner, final String repo, final int pullNumber) {
        return client.get(target("repos/{owner}/{repo}/pulls/{pull_number}", owner, repo, pullNumber).response(PullRequest.class));
    }

    public Stream<PullRequest> listPullRequests(final String owner, final String repo) {
        return client.stream(target("repos/{owner}/{repo}/pulls", owner, repo).response(PullRequest[].class), Arrays::asList);
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
        final URI target = client.resolve("search/code?" + queryString);

        return client.stream(target, CodePage.class, CodePage::getItems);
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final ApiOld.Builder builder = ApiOld.builder()
                .accept(MEDIA_TYPES);

        public Builder handler(final Consumer<Invocation.Builder> e) {
            this.builder.handler(e);
            return this;
        }

        public Builder oauthToken(final String oauthToken) {
            handler(GithubClient.oauthTokenAuthentication(oauthToken));
            return this;
        }

        public Builder githubApp(final GithubApp githubApp) {
            handler(GithubClient.githubAppAuthentication(githubApp));
            return this;
        }

        Builder responses(final Consumer<Object> responses) {
            this.builder.responses(responses);
            return this;
        }

        Builder requests(final Consumer<Object> requests) {
            this.builder.requests(requests);
            return this;
        }

        public Builder api(final URI api) {
            this.builder.api(api);
            return this;
        }

        public Builder client(final Client client) {
            this.builder.client(client);
            return this;
        }

        public GithubClient build() {
            return new GithubClient(builder.build());
        }
    }
}
