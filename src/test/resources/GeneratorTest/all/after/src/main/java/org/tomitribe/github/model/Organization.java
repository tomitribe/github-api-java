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
@ComponentId("#/components/schemas/organization")
public class Organization {

    @JsonbProperty("avatar_url")
    private String avatarUrl;

    @JsonbProperty("blog")
    private URI blog;

    @JsonbProperty("company")
    private String company;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("email")
    private String email;

    @JsonbProperty("events_url")
    private URI eventsUrl;

    @JsonbProperty("followers")
    private Integer followers;

    @JsonbProperty("following")
    private Integer following;

    @JsonbProperty("has_organization_projects")
    private Boolean hasOrganizationProjects;

    @JsonbProperty("has_repository_projects")
    private Boolean hasRepositoryProjects;

    @JsonbProperty("hooks_url")
    private String hooksUrl;

    @JsonbProperty("html_url")
    private URI htmlUrl;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("is_verified")
    private Boolean isVerified;

    @JsonbProperty("issues_url")
    private String issuesUrl;

    @JsonbProperty("location")
    private String location;

    @JsonbProperty("login")
    private String login;

    @JsonbProperty("members_url")
    private String membersUrl;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("plan")
    private Plan plan;

    @JsonbProperty("public_gists")
    private Integer publicGists;

    @JsonbProperty("public_members_url")
    private String publicMembersUrl;

    @JsonbProperty("public_repos")
    private Integer publicRepos;

    @JsonbProperty("repos_url")
    private URI reposUrl;

    @JsonbProperty("type")
    private String type;

    @JsonbProperty("updated_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date updatedAt;

    @JsonbProperty("url")
    private URI url;
}
