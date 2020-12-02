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
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tomitribe.github.core.DateAdapter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ComponentId("#/components/schemas/user-search-result-item")
public class UserSearchResultItem {

    @JsonbProperty("avatar_url")
    private URI avatarUrl;

    @JsonbProperty("bio")
    private String bio;

    @JsonbProperty("blog")
    private String blog;

    @JsonbProperty("company")
    private String company;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("email")
    private String email;

    @JsonbProperty("events_url")
    private String eventsUrl;

    @JsonbProperty("followers")
    private Integer followers;

    @JsonbProperty("followers_url")
    private URI followersUrl;

    @JsonbProperty("following")
    private Integer following;

    @JsonbProperty("following_url")
    private String followingUrl;

    @JsonbProperty("gists_url")
    private String gistsUrl;

    @JsonbProperty("gravatar_id")
    private String gravatarId;

    @JsonbProperty("hireable")
    private Boolean hireable;

    @JsonbProperty("html_url")
    private URI htmlUrl;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("location")
    private String location;

    @JsonbProperty("login")
    private String login;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("organizations_url")
    private URI organizationsUrl;

    @JsonbProperty("public_gists")
    private Integer publicGists;

    @JsonbProperty("public_repos")
    private Integer publicRepos;

    @JsonbProperty("received_events_url")
    private URI receivedEventsUrl;

    @JsonbProperty("repos_url")
    private URI reposUrl;

    @JsonbProperty("score")
    private Integer score;

    @JsonbProperty("site_admin")
    private Boolean siteAdmin;

    @JsonbProperty("starred_url")
    private String starredUrl;

    @JsonbProperty("subscriptions_url")
    private URI subscriptionsUrl;

    @JsonbProperty("suspended_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date suspendedAt;

    @JsonbProperty("text_matches")
    private List<SearchResultTextMatches> textMatches;

    @JsonbProperty("type")
    private String type;

    @JsonbProperty("updated_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date updatedAt;

    @JsonbProperty("url")
    private URI url;
}
