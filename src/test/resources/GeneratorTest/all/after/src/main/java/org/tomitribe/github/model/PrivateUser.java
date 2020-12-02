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
@ComponentId("#/components/schemas/private-user")
public class PrivateUser {

    @JsonbProperty("avatar_url")
    private URI avatarUrl;

    @JsonbProperty("bio")
    private String bio;

    @JsonbProperty("blog")
    private String blog;

    @JsonbProperty("business_plus")
    private Boolean businessPlus;

    @JsonbProperty("collaborators")
    private Integer collaborators;

    @JsonbProperty("company")
    private String company;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("disk_usage")
    private Integer diskUsage;

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

    @JsonbProperty("ldap_dn")
    private String ldapDn;

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

    @JsonbProperty("owned_private_repos")
    private Integer ownedPrivateRepos;

    @JsonbProperty("plan")
    private Plan plan;

    @JsonbProperty("private_gists")
    private Integer privateGists;

    @JsonbProperty("public_gists")
    private Integer publicGists;

    @JsonbProperty("public_repos")
    private Integer publicRepos;

    @JsonbProperty("received_events_url")
    private URI receivedEventsUrl;

    @JsonbProperty("repos_url")
    private URI reposUrl;

    @JsonbProperty("site_admin")
    private Boolean siteAdmin;

    @JsonbProperty("starred_url")
    private String starredUrl;

    @JsonbProperty("subscriptions_url")
    private URI subscriptionsUrl;

    @JsonbProperty("suspended_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date suspendedAt;

    @JsonbProperty("total_private_repos")
    private Integer totalPrivateRepos;

    @JsonbProperty("twitter_username")
    private String twitterUsername;

    @JsonbProperty("two_factor_authentication")
    private Boolean twoFactorAuthentication;

    @JsonbProperty("type")
    private String type;

    @JsonbProperty("updated_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date updatedAt;

    @JsonbProperty("url")
    private URI url;
}
