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
@ComponentId("#/components/schemas/simple-user")
public class SimpleUser {

    @JsonbProperty("avatar_url")
    private URI avatarUrl;

    @JsonbProperty("events_url")
    private String eventsUrl;

    @JsonbProperty("followers_url")
    private URI followersUrl;

    @JsonbProperty("following_url")
    private String followingUrl;

    @JsonbProperty("gists_url")
    private String gistsUrl;

    @JsonbProperty("gravatar_id")
    private String gravatarId;

    @JsonbProperty("html_url")
    private URI htmlUrl;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("login")
    private String login;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("organizations_url")
    private URI organizationsUrl;

    @JsonbProperty("received_events_url")
    private URI receivedEventsUrl;

    @JsonbProperty("repos_url")
    private URI reposUrl;

    @JsonbProperty("site_admin")
    private Boolean siteAdmin;

    @JsonbProperty("starred_at")
    private String starredAt;

    @JsonbProperty("starred_url")
    private String starredUrl;

    @JsonbProperty("subscriptions_url")
    private URI subscriptionsUrl;

    @JsonbProperty("type")
    private String type;

    @JsonbProperty("url")
    private URI url;
}
