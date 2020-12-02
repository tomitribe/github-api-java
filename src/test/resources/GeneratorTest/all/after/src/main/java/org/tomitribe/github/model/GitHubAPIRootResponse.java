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
package org.tomitribe.github.model;

import java.net.URI;
import javax.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GitHubAPIRootResponse {

    @JsonbProperty("authorizations_url")
    private URI authorizationsUrl;

    @JsonbProperty("code_search_url")
    private URI codeSearchUrl;

    @JsonbProperty("commit_search_url")
    private URI commitSearchUrl;

    @JsonbProperty("current_user_authorizations_html_url")
    private URI currentUserAuthorizationsHtmlUrl;

    @JsonbProperty("current_user_repositories_url")
    private URI currentUserRepositoriesUrl;

    @JsonbProperty("current_user_url")
    private URI currentUserUrl;

    @JsonbProperty("emails_url")
    private URI emailsUrl;

    @JsonbProperty("emojis_url")
    private URI emojisUrl;

    @JsonbProperty("events_url")
    private URI eventsUrl;

    @JsonbProperty("feeds_url")
    private URI feedsUrl;

    @JsonbProperty("followers_url")
    private URI followersUrl;

    @JsonbProperty("following_url")
    private URI followingUrl;

    @JsonbProperty("gists_url")
    private URI gistsUrl;

    @JsonbProperty("hub_url")
    private URI hubUrl;

    @JsonbProperty("issue_search_url")
    private URI issueSearchUrl;

    @JsonbProperty("issues_url")
    private URI issuesUrl;

    @JsonbProperty("keys_url")
    private URI keysUrl;

    @JsonbProperty("label_search_url")
    private URI labelSearchUrl;

    @JsonbProperty("notifications_url")
    private URI notificationsUrl;

    @JsonbProperty("organization_repositories_url")
    private URI organizationRepositoriesUrl;

    @JsonbProperty("organization_teams_url")
    private URI organizationTeamsUrl;

    @JsonbProperty("organization_url")
    private URI organizationUrl;

    @JsonbProperty("public_gists_url")
    private URI publicGistsUrl;

    @JsonbProperty("rate_limit_url")
    private URI rateLimitUrl;

    @JsonbProperty("repository_search_url")
    private URI repositorySearchUrl;

    @JsonbProperty("repository_url")
    private URI repositoryUrl;

    @JsonbProperty("starred_gists_url")
    private URI starredGistsUrl;

    @JsonbProperty("starred_url")
    private URI starredUrl;

    @JsonbProperty("topic_search_url")
    private URI topicSearchUrl;

    @JsonbProperty("user_organizations_url")
    private URI userOrganizationsUrl;

    @JsonbProperty("user_repositories_url")
    private URI userRepositoriesUrl;

    @JsonbProperty("user_search_url")
    private URI userSearchUrl;

    @JsonbProperty("user_url")
    private URI userUrl;
}
