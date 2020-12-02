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
@ComponentId("#/components/schemas/issue-simple")
public class IssueSimple {

    @JsonbProperty("active_lock_reason")
    private String activeLockReason;

    @JsonbProperty("assignee")
    private SimpleUser assignee;

    @JsonbProperty("assignees")
    private List<SimpleUser> assignees;

    @JsonbProperty("author_association")
    private String authorAssociation;

    @JsonbProperty("body")
    private String body;

    @JsonbProperty("body_html")
    private String bodyHtml;

    @JsonbProperty("body_text")
    private String bodyText;

    @JsonbProperty("closed_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date closedAt;

    @JsonbProperty("comments")
    private Integer comments;

    @JsonbProperty("comments_url")
    private URI commentsUrl;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("events_url")
    private URI eventsUrl;

    @JsonbProperty("html_url")
    private URI htmlUrl;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("labels")
    private List<Labels> labels;

    @JsonbProperty("labels_url")
    private String labelsUrl;

    @JsonbProperty("locked")
    private Boolean locked;

    @JsonbProperty("milestone")
    private Milestone milestone;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("number")
    private Integer number;

    @JsonbProperty("performed_via_github_app")
    private Integration performedViaGithubApp;

    @JsonbProperty("pull_request")
    private PullRequest pullRequest;

    @JsonbProperty("repository")
    private Repository repository;

    @JsonbProperty("repository_url")
    private URI repositoryUrl;

    @JsonbProperty("state")
    private String state;

    @JsonbProperty("timeline_url")
    private URI timelineUrl;

    @JsonbProperty("title")
    private String title;

    @JsonbProperty("updated_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date updatedAt;

    @JsonbProperty("url")
    private URI url;

    @JsonbProperty("user")
    private SimpleUser user;
}
