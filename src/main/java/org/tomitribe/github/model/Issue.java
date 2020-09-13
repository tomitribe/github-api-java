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
import java.util.List;

/**
 * Used by:
 * - IssueCommentEvent
 * - IssuesEvent
 * - PullRequestEvent
 * - PullRequestReviewCommentEvent
 * - PullRequestReviewEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Issue {

    @JsonbProperty("assignee")
    private Assignee assignee;

    @JsonbProperty("assignees")
    private List<Assignee> assignees;

    @JsonbProperty("author_association")
    private String authorAssociation;

    @JsonbProperty("body")
    private String body;

    @JsonbProperty("closed_at")
    private String closedAt;

    @JsonbProperty("comments")
    private Long comments;

    @JsonbProperty("comments_url")
    private String commentsUrl;

    @JsonbProperty("created_at")
    private String createdAt;

    @JsonbProperty("events_url")
    private String eventsUrl;

    @JsonbProperty("href")
    private String href;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("labels")
    private List<Label> labels;

    @JsonbProperty("labels_url")
    private String labelsUrl;

    @JsonbProperty("locked")
    private Boolean locked;

    @JsonbProperty("milestone")
    private Milestone milestone;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("number")
    private Long number;

    @JsonbProperty("repository_url")
    private String repositoryUrl;

    @JsonbProperty("state")
    private String state;

    @JsonbProperty("title")
    private String title;

    @JsonbProperty("updated_at")
    private String updatedAt;

    @JsonbProperty("url")
    private String url;

    @JsonbProperty("user")
    private User user;
}
