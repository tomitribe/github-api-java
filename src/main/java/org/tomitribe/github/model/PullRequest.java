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
 * - CheckRunEvent
 * - CheckSuiteEvent
 * - PullRequestEvent
 * - PullRequestReviewCommentEvent
 * - PullRequestReviewEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PullRequest {

    @JsonbProperty("additions")
    private Long additions;

    @JsonbProperty("assignee")
    private String assignee;

    @JsonbProperty("assignees")
    private String[] assignees;

    @JsonbProperty("author_association")
    private String authorAssociation;

    @JsonbProperty("base")
    private Base base;

    @JsonbProperty("body")
    private String body;

    @JsonbProperty("changed_files")
    private Long changedFiles;

    @JsonbProperty("closed_at")
    private String closedAt;

    @JsonbProperty("comments")
    private Long comments;

    @JsonbProperty("comments_url")
    private String commentsUrl;

    @JsonbProperty("commits")
    private Long commits;

    @JsonbProperty("commits_url")
    private String commitsUrl;

    @JsonbProperty("created_at")
    private String createdAt;

    @JsonbProperty("deletions")
    private Long deletions;

    @JsonbProperty("diff_url")
    private String diffUrl;

    @JsonbProperty("draft")
    private Boolean draft;

    @JsonbProperty("head")
    private Head head;

    @JsonbProperty("href")
    private String href;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("issue_url")
    private String issueUrl;

    @JsonbProperty("labels")
    private Label[] labels;

    @JsonbProperty("_links")
    private Links links;

    @JsonbProperty("locked")
    private Boolean locked;

    @JsonbProperty("maintainer_can_modify")
    private Boolean maintainerCanModify;

    @JsonbProperty("merge_commit_sha")
    private String mergeCommitSha;

    @JsonbProperty("mergeable")
    private Boolean mergeable;

    @JsonbProperty("mergeable_state")
    private String mergeableState;

    @JsonbProperty("merged")
    private Boolean merged;

    @JsonbProperty("merged_at")
    private String mergedAt;

    @JsonbProperty("merged_by")
    private String mergedBy;

    @JsonbProperty("milestone")
    private Milestone milestone;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("number")
    private Long number;

    @JsonbProperty("patch_url")
    private String patchUrl;

    @JsonbProperty("rebaseable")
    private Boolean rebaseable;

    @JsonbProperty("requested_reviewers")
    private User[] requestedReviewers;

    @JsonbProperty("requested_teams")
    private String[] requestedTeams;

    @JsonbProperty("review_comment_url")
    private String reviewCommentUrl;

    @JsonbProperty("review_comments")
    private Long reviewComments;

    @JsonbProperty("review_comments_url")
    private String reviewCommentsUrl;

    @JsonbProperty("state")
    private String state;

    @JsonbProperty("statuses_url")
    private String statusesUrl;

    @JsonbProperty("title")
    private String title;

    @JsonbProperty("updated_at")
    private String updatedAt;

    @JsonbProperty("url")
    private String url;

    @JsonbProperty("user")
    private User user;
}
