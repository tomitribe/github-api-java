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

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import org.tomitribe.github.model.DeleteDiscussion;
import org.tomitribe.github.model.GetDiscussion;
import org.tomitribe.github.model.TeamDiscussion;
import org.tomitribe.github.model.UpdateDiscussion;

public interface TeamsClient {

    @DELETE
    @Path("/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}")
    @OperationId("teams/delete-discussion-in-org")
    @Docs("https://developer.github.com/v3/teams/discussions/#delete-a-discussion")
    @EnabledForGithubApps
    @Category("teams")
    @Subcategory("discussions")
    void deleteDiscussion(final DeleteDiscussion deleteDiscussion);

    @DELETE
    @Path("/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}")
    @OperationId("teams/delete-discussion-in-org")
    @Docs("https://developer.github.com/v3/teams/discussions/#delete-a-discussion")
    @EnabledForGithubApps
    @Category("teams")
    @Subcategory("discussions")
    void deleteDiscussion(@PathParam("org") final String org, @PathParam("team_slug") final String teamSlug, @PathParam("discussion-number") final int discussionNumber);

    @GET
    @Path("/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}")
    @OperationId("teams/get-discussion-in-org")
    @Docs("https://developer.github.com/v3/teams/discussions/#get-a-discussion")
    @EnabledForGithubApps
    @Preview("squirrel-girl")
    @Category("teams")
    @Subcategory("discussions")
    TeamDiscussion getDiscussion(final GetDiscussion getDiscussion);

    @GET
    @Path("/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}")
    @OperationId("teams/get-discussion-in-org")
    @Docs("https://developer.github.com/v3/teams/discussions/#get-a-discussion")
    @EnabledForGithubApps
    @Preview("squirrel-girl")
    @Category("teams")
    @Subcategory("discussions")
    TeamDiscussion getDiscussion(@PathParam("org") final String org, @PathParam("team_slug") final String teamSlug, @PathParam("discussion-number") final int discussionNumber);

    @PATCH
    @Path("/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}")
    @OperationId("teams/update-discussion-in-org")
    @Docs("https://developer.github.com/v3/teams/discussions/#update-a-discussion")
    @EnabledForGithubApps
    @Preview("squirrel-girl")
    @Category("teams")
    @Subcategory("discussions")
    TeamDiscussion updateDiscussion(final UpdateDiscussion updateDiscussion);
}
