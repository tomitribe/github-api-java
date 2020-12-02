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
@ComponentId("#/components/schemas/pull-request-simple")
public class PullRequestSimple {

    @JsonbProperty("active_lock_reason")
    private String activeLockReason;

    @JsonbProperty("assignee")
    private SimpleUser assignee;

    @JsonbProperty("assignees")
    private List<SimpleUser> assignees;

    @JsonbProperty("author_association")
    private String authorAssociation;

    @JsonbProperty("base")
    private Base base;

    @JsonbProperty("body")
    private String body;

    @JsonbProperty("closed_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date closedAt;

    @JsonbProperty("comments_url")
    private URI commentsUrl;

    @JsonbProperty("commits_url")
    private URI commitsUrl;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("diff_url")
    private URI diffUrl;

    @JsonbProperty("draft")
    private Boolean draft;

    @JsonbProperty("head")
    private Head head;

    @JsonbProperty("html_url")
    private URI htmlUrl;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("issue_url")
    private URI issueUrl;

    @JsonbProperty("labels")
    private List<Labels> labels;

    @JsonbProperty("_links")
    private Links links;

    @JsonbProperty("locked")
    private Boolean locked;

    @JsonbProperty("merge_commit_sha")
    private String mergeCommitSha;

    @JsonbProperty("merged_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date mergedAt;

    @JsonbProperty("milestone")
    private Milestone milestone;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("number")
    private Integer number;

    @JsonbProperty("patch_url")
    private URI patchUrl;

    @JsonbProperty("requested_reviewers")
    private List<SimpleUser> requestedReviewers;

    @JsonbProperty("requested_teams")
    private List<TeamSimple> requestedTeams;

    @JsonbProperty("review_comment_url")
    private String reviewCommentUrl;

    @JsonbProperty("review_comments_url")
    private URI reviewCommentsUrl;

    @JsonbProperty("state")
    private String state;

    @JsonbProperty("statuses_url")
    private URI statusesUrl;

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
