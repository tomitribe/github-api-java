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

package org.tomitribe.github.app.events;

import javax.json.bind.annotation.JsonbProperty;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Used by:
 * - OrgBlockEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlockedUser {

    @JsonbProperty("login")
    private String login;

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("avatar_url")
    private String avatarUrl;

    @JsonbProperty("gravatar_id")
    private String gravatarId;

    @JsonbProperty("url")
    private String url;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("followers_url")
    private String followersUrl;

    @JsonbProperty("following_url")
    private String followingUrl;

    @JsonbProperty("gists_url")
    private String gistsUrl;

    @JsonbProperty("starred_url")
    private String starredUrl;

    @JsonbProperty("subscriptions_url")
    private String subscriptionsUrl;

    @JsonbProperty("organizations_url")
    private String organizationsUrl;

    @JsonbProperty("repos_url")
    private String reposUrl;

    @JsonbProperty("events_url")
    private String eventsUrl;

    @JsonbProperty("received_events_url")
    private String receivedEventsUrl;

    @JsonbProperty("type")
    private String type;

    @JsonbProperty("site_admin")
    private Boolean siteAdmin;

}
