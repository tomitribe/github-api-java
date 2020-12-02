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

import java.util.stream.Stream;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import org.tomitribe.github.model.CheckIfRepositoryIsStarredByAuthenticatedUser;
import org.tomitribe.github.model.DeleteRepositorySubscription;
import org.tomitribe.github.model.DeleteThreadSubscription;
import org.tomitribe.github.model.Event;
import org.tomitribe.github.model.Feed;
import org.tomitribe.github.model.GetRepositorySubscription;
import org.tomitribe.github.model.GetThread;
import org.tomitribe.github.model.GetThreadSubscriptionForAuthenticatedUser;
import org.tomitribe.github.model.ListEventsForAuthenticatedUser;
import org.tomitribe.github.model.ListEventsReceivedByAuthenticatedUser;
import org.tomitribe.github.model.ListNotificationsForAuthenticatedUser;
import org.tomitribe.github.model.ListOrganizationEventsForAuthenticatedUser;
import org.tomitribe.github.model.ListPublicEventsForNetworkOfRepositories;
import org.tomitribe.github.model.ListPublicEventsForUser;
import org.tomitribe.github.model.ListPublicEventsReceivedByUser;
import org.tomitribe.github.model.ListPublicOrganizationEvents;
import org.tomitribe.github.model.ListRepositoriesStarredByAuthenticatedUser;
import org.tomitribe.github.model.ListRepositoriesStarredByUser;
import org.tomitribe.github.model.ListRepositoriesWatchedByUser;
import org.tomitribe.github.model.ListRepositoryEvents;
import org.tomitribe.github.model.ListRepositoryNotificationsForAuthenticatedUser;
import org.tomitribe.github.model.ListStargazers;
import org.tomitribe.github.model.ListWatchers;
import org.tomitribe.github.model.MarkNotificationsAsRead;
import org.tomitribe.github.model.MarkNotificationsAsReadResponse;
import org.tomitribe.github.model.MinimalRepository;
import org.tomitribe.github.model.Repository;
import org.tomitribe.github.model.RepositorySubscription;
import org.tomitribe.github.model.SetRepositorySubscription;
import org.tomitribe.github.model.SetThreadSubscription;
import org.tomitribe.github.model.SimpleUser;
import org.tomitribe.github.model.StarRepositoryForAuthenticatedUser;
import org.tomitribe.github.model.Thread;
import org.tomitribe.github.model.ThreadSubscription;
import org.tomitribe.github.model.UnstarRepositoryForAuthenticatedUser;

public interface ActivityClient {

    @GET
    @Path("/user/starred/{owner}/{repo}")
    @OperationId("activity/check-repo-is-starred-by-authenticated-user")
    @Docs("https://developer.github.com/v3/activity/starring/#check-if-a-repository-is-starred-by-the-authenticated-user")
    @Category("activity")
    @Subcategory("starring")
    void checkIfRepositoryIsStarredByAuthenticatedUser(final CheckIfRepositoryIsStarredByAuthenticatedUser checkIfRepositoryIsStarredByAuthenticatedUser);

    @GET
    @Path("/user/starred/{owner}/{repo}")
    @OperationId("activity/check-repo-is-starred-by-authenticated-user")
    @Docs("https://developer.github.com/v3/activity/starring/#check-if-a-repository-is-starred-by-the-authenticated-user")
    @Category("activity")
    @Subcategory("starring")
    void checkIfRepositoryIsStarredByAuthenticatedUser(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @DELETE
    @Path("/repos/{owner}/{repo}/subscription")
    @OperationId("activity/delete-repo-subscription")
    @Docs("https://developer.github.com/v3/activity/watching/#delete-a-repository-subscription")
    @Category("activity")
    @Subcategory("watching")
    void deleteRepositorySubscription(final DeleteRepositorySubscription deleteRepositorySubscription);

    @DELETE
    @Path("/repos/{owner}/{repo}/subscription")
    @OperationId("activity/delete-repo-subscription")
    @Docs("https://developer.github.com/v3/activity/watching/#delete-a-repository-subscription")
    @Category("activity")
    @Subcategory("watching")
    void deleteRepositorySubscription(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @DELETE
    @Path("/notifications/threads/{thread_id}/subscription")
    @OperationId("activity/delete-thread-subscription")
    @Docs("https://developer.github.com/v3/activity/notifications/#delete-a-thread-subscription")
    @Category("activity")
    @Subcategory("notifications")
    void deleteThreadSubscription(final DeleteThreadSubscription deleteThreadSubscription);

    @DELETE
    @Path("/notifications/threads/{thread_id}/subscription")
    @OperationId("activity/delete-thread-subscription")
    @Docs("https://developer.github.com/v3/activity/notifications/#delete-a-thread-subscription")
    @Category("activity")
    @Subcategory("notifications")
    void deleteThreadSubscription(@PathParam("thread_id") final int threadId);

    @GET
    @Path("/feeds")
    @OperationId("activity/get-feeds")
    @Docs("https://developer.github.com/v3/activity/feeds/#get-feeds")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("feeds")
    Feed getFeeds();

    @GET
    @Path("/repos/{owner}/{repo}/subscription")
    @OperationId("activity/get-repo-subscription")
    @Docs("https://developer.github.com/v3/activity/watching/#get-a-repository-subscription")
    @Category("activity")
    @Subcategory("watching")
    RepositorySubscription getRepositorySubscription(final GetRepositorySubscription getRepositorySubscription);

    @GET
    @Path("/repos/{owner}/{repo}/subscription")
    @OperationId("activity/get-repo-subscription")
    @Docs("https://developer.github.com/v3/activity/watching/#get-a-repository-subscription")
    @Category("activity")
    @Subcategory("watching")
    RepositorySubscription getRepositorySubscription(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/notifications/threads/{thread_id}")
    @OperationId("activity/get-thread")
    @Docs("https://developer.github.com/v3/activity/notifications/#get-a-thread")
    @Category("activity")
    @Subcategory("notifications")
    Thread getThread(final GetThread getThread);

    @GET
    @Path("/notifications/threads/{thread_id}")
    @OperationId("activity/get-thread")
    @Docs("https://developer.github.com/v3/activity/notifications/#get-a-thread")
    @Category("activity")
    @Subcategory("notifications")
    Thread getThread(@PathParam("thread_id") final int threadId);

    @GET
    @Path("/notifications/threads/{thread_id}/subscription")
    @OperationId("activity/get-thread-subscription-for-authenticated-user")
    @Docs("https://developer.github.com/v3/activity/notifications/#get-a-thread-subscription-for-the-authenticated-user")
    @Category("activity")
    @Subcategory("notifications")
    ThreadSubscription getThreadSubscriptionForAuthenticatedUser(final GetThreadSubscriptionForAuthenticatedUser getThreadSubscriptionForAuthenticatedUser);

    @GET
    @Path("/notifications/threads/{thread_id}/subscription")
    @OperationId("activity/get-thread-subscription-for-authenticated-user")
    @Docs("https://developer.github.com/v3/activity/notifications/#get-a-thread-subscription-for-the-authenticated-user")
    @Category("activity")
    @Subcategory("notifications")
    ThreadSubscription getThreadSubscriptionForAuthenticatedUser(@PathParam("thread_id") final int threadId);

    @GET
    @Path("/users/{username}/events")
    @OperationId("activity/list-events-for-authenticated-user")
    @Docs("https://developer.github.com/v3/activity/events/#list-events-for-the-authenticated-user")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("events")
    Stream<Event> listEventsForAuthenticatedUser(final ListEventsForAuthenticatedUser listEventsForAuthenticatedUser);

    @GET
    @Path("/users/{username}/events")
    @OperationId("activity/list-events-for-authenticated-user")
    @Docs("https://developer.github.com/v3/activity/events/#list-events-for-the-authenticated-user")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("events")
    Stream<Event> listEventsForAuthenticatedUser(@PathParam("username") final String username);

    @GET
    @Path("/users/{username}/received_events")
    @OperationId("activity/list-received-events-for-user")
    @Docs("https://developer.github.com/v3/activity/events/#list-events-received-by-the-authenticated-user")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("events")
    Stream<Event> listEventsReceivedByAuthenticatedUser(final ListEventsReceivedByAuthenticatedUser listEventsReceivedByAuthenticatedUser);

    @GET
    @Path("/users/{username}/received_events")
    @OperationId("activity/list-received-events-for-user")
    @Docs("https://developer.github.com/v3/activity/events/#list-events-received-by-the-authenticated-user")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("events")
    Stream<Event> listEventsReceivedByAuthenticatedUser(@PathParam("username") final String username);

    @GET
    @Path("/notifications")
    @OperationId("activity/list-notifications-for-authenticated-user")
    @Docs("https://developer.github.com/v3/activity/notifications/#list-notifications-for-the-authenticated-user")
    @Category("activity")
    @Subcategory("notifications")
    @Paged(Thread[].class)
    Stream<Thread> listNotificationsForAuthenticatedUser(final ListNotificationsForAuthenticatedUser listNotificationsForAuthenticatedUser);

    @GET
    @Path("/users/{username}/events/orgs/{org}")
    @OperationId("activity/list-org-events-for-authenticated-user")
    @Docs("https://developer.github.com/v3/activity/events/#list-organization-events-for-the-authenticated-user")
    @Category("activity")
    @Subcategory("events")
    Stream<Event> listOrganizationEventsForAuthenticatedUser(final ListOrganizationEventsForAuthenticatedUser listOrganizationEventsForAuthenticatedUser);

    @GET
    @Path("/users/{username}/events/orgs/{org}")
    @OperationId("activity/list-org-events-for-authenticated-user")
    @Docs("https://developer.github.com/v3/activity/events/#list-organization-events-for-the-authenticated-user")
    @Category("activity")
    @Subcategory("events")
    Stream<Event> listOrganizationEventsForAuthenticatedUser(@PathParam("username") final String username, @PathParam("org") final String org);

    @GET
    @Path("/events")
    @OperationId("activity/list-public-events")
    @Docs("https://developer.github.com/v3/activity/events/#list-public-events")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("events")
    Stream<Event> listPublicEvents();

    @GET
    @Path("/networks/{owner}/{repo}/events")
    @OperationId("activity/list-public-events-for-repo-network")
    @Docs("https://developer.github.com/v3/activity/events/#list-public-events-for-a-network-of-repositories")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("events")
    Stream<Event> listPublicEventsForNetworkOfRepositories(final ListPublicEventsForNetworkOfRepositories listPublicEventsForNetworkOfRepositories);

    @GET
    @Path("/networks/{owner}/{repo}/events")
    @OperationId("activity/list-public-events-for-repo-network")
    @Docs("https://developer.github.com/v3/activity/events/#list-public-events-for-a-network-of-repositories")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("events")
    Stream<Event> listPublicEventsForNetworkOfRepositories(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/users/{username}/events/public")
    @OperationId("activity/list-public-events-for-user")
    @Docs("https://developer.github.com/v3/activity/events/#list-public-events-for-a-user")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("events")
    Stream<Event> listPublicEventsForUser(final ListPublicEventsForUser listPublicEventsForUser);

    @GET
    @Path("/users/{username}/events/public")
    @OperationId("activity/list-public-events-for-user")
    @Docs("https://developer.github.com/v3/activity/events/#list-public-events-for-a-user")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("events")
    Stream<Event> listPublicEventsForUser(@PathParam("username") final String username);

    @GET
    @Path("/users/{username}/received_events/public")
    @OperationId("activity/list-received-public-events-for-user")
    @Docs("https://developer.github.com/v3/activity/events/#list-public-events-received-by-a-user")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("events")
    Stream<Event> listPublicEventsReceivedByUser(final ListPublicEventsReceivedByUser listPublicEventsReceivedByUser);

    @GET
    @Path("/users/{username}/received_events/public")
    @OperationId("activity/list-received-public-events-for-user")
    @Docs("https://developer.github.com/v3/activity/events/#list-public-events-received-by-a-user")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("events")
    Stream<Event> listPublicEventsReceivedByUser(@PathParam("username") final String username);

    @GET
    @Path("/orgs/{org}/events")
    @OperationId("activity/list-public-org-events")
    @Docs("https://developer.github.com/v3/activity/events/#list-public-organization-events")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("events")
    Stream<Event> listPublicOrganizationEvents(final ListPublicOrganizationEvents listPublicOrganizationEvents);

    @GET
    @Path("/orgs/{org}/events")
    @OperationId("activity/list-public-org-events")
    @Docs("https://developer.github.com/v3/activity/events/#list-public-organization-events")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("events")
    Stream<Event> listPublicOrganizationEvents(@PathParam("org") final String org);

    @GET
    @Path("/user/starred")
    @OperationId("activity/list-repos-starred-by-authenticated-user")
    @Docs("https://developer.github.com/v3/activity/starring/#list-repositories-starred-by-the-authenticated-user")
    @Category("activity")
    @Subcategory("starring")
    @Paged(Repository[].class)
    Stream<Repository> listRepositoriesStarredByAuthenticatedUser(final ListRepositoriesStarredByAuthenticatedUser listRepositoriesStarredByAuthenticatedUser);

    @GET
    @Path("/users/{username}/starred")
    @OperationId("activity/list-repos-starred-by-user")
    @Docs("https://developer.github.com/v3/activity/starring/#list-repositories-starred-by-a-user")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("starring")
    @Paged(Repository[].class)
    Stream<Repository> listRepositoriesStarredByUser(final ListRepositoriesStarredByUser listRepositoriesStarredByUser);

    @GET
    @Path("/users/{username}/starred")
    @OperationId("activity/list-repos-starred-by-user")
    @Docs("https://developer.github.com/v3/activity/starring/#list-repositories-starred-by-a-user")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("starring")
    @Paged(Repository[].class)
    Stream<Repository> listRepositoriesStarredByUser(@PathParam("username") final String username);

    @GET
    @Path("/user/subscriptions")
    @OperationId("activity/list-watched-repos-for-authenticated-user")
    @Docs("https://developer.github.com/v3/activity/watching/#list-repositories-watched-by-the-authenticated-user")
    @Category("activity")
    @Subcategory("watching")
    @Paged(MinimalRepository[].class)
    Stream<MinimalRepository> listRepositoriesWatchedByAuthenticatedUser();

    @GET
    @Path("/users/{username}/subscriptions")
    @OperationId("activity/list-repos-watched-by-user")
    @Docs("https://developer.github.com/v3/activity/watching/#list-repositories-watched-by-a-user")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("watching")
    @Paged(MinimalRepository[].class)
    Stream<MinimalRepository> listRepositoriesWatchedByUser(final ListRepositoriesWatchedByUser listRepositoriesWatchedByUser);

    @GET
    @Path("/users/{username}/subscriptions")
    @OperationId("activity/list-repos-watched-by-user")
    @Docs("https://developer.github.com/v3/activity/watching/#list-repositories-watched-by-a-user")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("watching")
    @Paged(MinimalRepository[].class)
    Stream<MinimalRepository> listRepositoriesWatchedByUser(@PathParam("username") final String username);

    @GET
    @Path("/repos/{owner}/{repo}/events")
    @OperationId("activity/list-repo-events")
    @Docs("https://developer.github.com/v3/activity/events/#list-repository-events")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("events")
    Stream<Event> listRepositoryEvents(final ListRepositoryEvents listRepositoryEvents);

    @GET
    @Path("/repos/{owner}/{repo}/events")
    @OperationId("activity/list-repo-events")
    @Docs("https://developer.github.com/v3/activity/events/#list-repository-events")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("events")
    Stream<Event> listRepositoryEvents(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/repos/{owner}/{repo}/notifications")
    @OperationId("activity/list-repo-notifications-for-authenticated-user")
    @Docs("https://developer.github.com/v3/activity/notifications/#list-repository-notifications-for-the-authenticated-user")
    @Category("activity")
    @Subcategory("notifications")
    @Paged(Thread[].class)
    Stream<Thread> listRepositoryNotificationsForAuthenticatedUser(final ListRepositoryNotificationsForAuthenticatedUser listRepositoryNotificationsForAuthenticatedUser);

    @GET
    @Path("/repos/{owner}/{repo}/notifications")
    @OperationId("activity/list-repo-notifications-for-authenticated-user")
    @Docs("https://developer.github.com/v3/activity/notifications/#list-repository-notifications-for-the-authenticated-user")
    @Category("activity")
    @Subcategory("notifications")
    @Paged(Thread[].class)
    Stream<Thread> listRepositoryNotificationsForAuthenticatedUser(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/repos/{owner}/{repo}/stargazers")
    @OperationId("activity/list-stargazers-for-repo")
    @Docs("https://developer.github.com/v3/activity/starring/#list-stargazers")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("starring")
    @Paged(SimpleUser[].class)
    Stream<SimpleUser> listStargazers(final ListStargazers listStargazers);

    @GET
    @Path("/repos/{owner}/{repo}/stargazers")
    @OperationId("activity/list-stargazers-for-repo")
    @Docs("https://developer.github.com/v3/activity/starring/#list-stargazers")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("starring")
    @Paged(SimpleUser[].class)
    Stream<SimpleUser> listStargazers(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/repos/{owner}/{repo}/subscribers")
    @OperationId("activity/list-watchers-for-repo")
    @Docs("https://developer.github.com/v3/activity/watching/#list-watchers")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("watching")
    @Paged(SimpleUser[].class)
    Stream<SimpleUser> listWatchers(final ListWatchers listWatchers);

    @GET
    @Path("/repos/{owner}/{repo}/subscribers")
    @OperationId("activity/list-watchers-for-repo")
    @Docs("https://developer.github.com/v3/activity/watching/#list-watchers")
    @EnabledForGithubApps
    @Category("activity")
    @Subcategory("watching")
    @Paged(SimpleUser[].class)
    Stream<SimpleUser> listWatchers(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @PUT
    @Path("/notifications")
    @OperationId("activity/mark-notifications-as-read")
    @Docs("https://developer.github.com/v3/activity/notifications/#mark-notifications-as-read")
    @Category("activity")
    @Subcategory("notifications")
    MarkNotificationsAsReadResponse markNotificationsAsRead(final MarkNotificationsAsRead markNotificationsAsRead);

    @PUT
    @Path("/repos/{owner}/{repo}/subscription")
    @OperationId("activity/set-repo-subscription")
    @Docs("https://developer.github.com/v3/activity/watching/#set-a-repository-subscription")
    @Category("activity")
    @Subcategory("watching")
    RepositorySubscription setRepositorySubscription(final SetRepositorySubscription setRepositorySubscription);

    @PUT
    @Path("/notifications/threads/{thread_id}/subscription")
    @OperationId("activity/set-thread-subscription")
    @Docs("https://developer.github.com/v3/activity/notifications/#set-a-thread-subscription")
    @Category("activity")
    @Subcategory("notifications")
    ThreadSubscription setThreadSubscription(final SetThreadSubscription setThreadSubscription);

    @PUT
    @Path("/user/starred/{owner}/{repo}")
    @OperationId("activity/star-repo-for-authenticated-user")
    @Docs("https://developer.github.com/v3/activity/starring/#star-a-repository-for-the-authenticated-user")
    @Category("activity")
    @Subcategory("starring")
    void starRepositoryForAuthenticatedUser(final StarRepositoryForAuthenticatedUser starRepositoryForAuthenticatedUser);

    @PUT
    @Path("/user/starred/{owner}/{repo}")
    @OperationId("activity/star-repo-for-authenticated-user")
    @Docs("https://developer.github.com/v3/activity/starring/#star-a-repository-for-the-authenticated-user")
    @Category("activity")
    @Subcategory("starring")
    void starRepositoryForAuthenticatedUser(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @DELETE
    @Path("/user/starred/{owner}/{repo}")
    @OperationId("activity/unstar-repo-for-authenticated-user")
    @Docs("https://developer.github.com/v3/activity/starring/#unstar-a-repository-for-the-authenticated-user")
    @Category("activity")
    @Subcategory("starring")
    void unstarRepositoryForAuthenticatedUser(final UnstarRepositoryForAuthenticatedUser unstarRepositoryForAuthenticatedUser);

    @DELETE
    @Path("/user/starred/{owner}/{repo}")
    @OperationId("activity/unstar-repo-for-authenticated-user")
    @Docs("https://developer.github.com/v3/activity/starring/#unstar-a-repository-for-the-authenticated-user")
    @Category("activity")
    @Subcategory("starring")
    void unstarRepositoryForAuthenticatedUser(@PathParam("owner") final String owner, @PathParam("repo") final String repo);
}
