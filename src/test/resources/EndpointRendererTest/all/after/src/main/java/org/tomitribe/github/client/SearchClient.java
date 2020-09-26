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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.tomitribe.github.model.CodeSearchResultItem;
import org.tomitribe.github.model.CodeSearchResultItemsPage;
import org.tomitribe.github.model.CommitSearchResultItem;
import org.tomitribe.github.model.CommitSearchResultItemsPage;
import org.tomitribe.github.model.IssueSearchResultItem;
import org.tomitribe.github.model.IssueSearchResultItemsPage;
import org.tomitribe.github.model.LabelSearchResultItem;
import org.tomitribe.github.model.LabelSearchResultItemsPage;
import org.tomitribe.github.model.RepoSearchResultItem;
import org.tomitribe.github.model.RepoSearchResultItemsPage;
import org.tomitribe.github.model.SearchCode;
import org.tomitribe.github.model.SearchCommits;
import org.tomitribe.github.model.SearchIssuesAndPullRequests;
import org.tomitribe.github.model.SearchLabels;
import org.tomitribe.github.model.SearchRepositories;
import org.tomitribe.github.model.SearchTopics;
import org.tomitribe.github.model.SearchUsers;
import org.tomitribe.github.model.TopicSearchResultItem;
import org.tomitribe.github.model.TopicSearchResultItemsPage;
import org.tomitribe.github.model.UserSearchResultItem;
import org.tomitribe.github.model.UserSearchResultItemsPage;

public interface SearchClient {

    @GET
    @Path("/search/code")
    @OperationId("search/code")
    @Docs("https://developer.github.com/v3/search/#search-code")
    @EnabledForGithubApps
    @Category("search")
    @Paged(CodeSearchResultItemsPage.class)
    Stream<CodeSearchResultItem> searchCode(final SearchCode searchCode);

    @GET
    @Path("/search/commits")
    @OperationId("search/commits")
    @Docs("https://developer.github.com/v3/search/#search-commits")
    @EnabledForGithubApps
    @Preview("cloak")
    @Category("search")
    @Paged(CommitSearchResultItemsPage.class)
    Stream<CommitSearchResultItem> searchCommits(final SearchCommits searchCommits);

    @GET
    @Path("/search/issues")
    @OperationId("search/issues-and-pull-requests")
    @Docs("https://developer.github.com/v3/search/#search-issues-and-pull-requests")
    @EnabledForGithubApps
    @Category("search")
    @Paged(IssueSearchResultItemsPage.class)
    Stream<IssueSearchResultItem> searchIssuesAndPullRequests(final SearchIssuesAndPullRequests searchIssuesAndPullRequests);

    @GET
    @Path("/search/labels")
    @OperationId("search/labels")
    @Docs("https://developer.github.com/v3/search/#search-labels")
    @EnabledForGithubApps
    @Category("search")
    @Paged(LabelSearchResultItemsPage.class)
    Stream<LabelSearchResultItem> searchLabels(final SearchLabels searchLabels);

    @GET
    @Path("/search/repositories")
    @OperationId("search/repos")
    @Docs("https://developer.github.com/v3/search/#search-repositories")
    @EnabledForGithubApps
    @Preview("mercy")
    @Category("search")
    @Paged(RepoSearchResultItemsPage.class)
    Stream<RepoSearchResultItem> searchRepositories(final SearchRepositories searchRepositories);

    @GET
    @Path("/search/topics")
    @OperationId("search/topics")
    @Docs("https://developer.github.com/v3/search/#search-topics")
    @EnabledForGithubApps
    @Preview("mercy")
    @Category("search")
    @Paged(TopicSearchResultItemsPage.class)
    Stream<TopicSearchResultItem> searchTopics(final SearchTopics searchTopics);

    @GET
    @Path("/search/users")
    @OperationId("search/users")
    @Docs("https://developer.github.com/v3/search/#search-users")
    @EnabledForGithubApps
    @Category("search")
    @Paged(UserSearchResultItemsPage.class)
    Stream<UserSearchResultItem> searchUsers(final SearchUsers searchUsers);
}
