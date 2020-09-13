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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Used by:
 * - MembershipEvent
 * - OrgBlockEvent
 * - OrganizationEvent
 * - RepositoryImportEvent
 * - TeamAddEvent
 * - TeamEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Organization {

    @JsonbProperty("avatar_url")
    private String avatarUrl;

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("events_url")
    private String eventsUrl;

    @JsonbProperty("hooks_url")
    private String hooksUrl;

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("issues_url")
    private String issuesUrl;

    @JsonbProperty("login")
    private String login;

    @JsonbProperty("members_url")
    private String membersUrl;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("public_members_url")
    private String publicMembersUrl;

    @JsonbProperty("repos_url")
    private String reposUrl;

    @JsonbProperty("url")
    private String url;
}
