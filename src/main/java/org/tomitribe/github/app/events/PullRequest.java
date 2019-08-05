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

    @JsonbProperty("url")
    private String url;

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("number")
    private Long number;

    @JsonbProperty("head")
    private Head head;

    @JsonbProperty("base")
    private Base base;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("diff_url")
    private String diffUrl;

    @JsonbProperty("patch_url")
    private String patchUrl;

    @JsonbProperty("issue_url")
    private String issueUrl;

    @JsonbProperty("state")
    private String state;

    @JsonbProperty("locked")
    private Boolean locked;

    @JsonbProperty("title")
    private String title;

    @JsonbProperty("user")
    private User user;

    @JsonbProperty("body")
    private String body;

    @JsonbProperty("created_at")
    private String createdAt;

    @JsonbProperty("updated_at")
    private String updatedAt;

    @JsonbProperty("closed_at")
    private String closedAt;

    @JsonbProperty("merged_at")
    private String mergedAt;

    @JsonbProperty("merge_commit_sha")
    private String mergeCommitSha;

    @JsonbProperty("assignee")
    private String assignee;

    @JsonbProperty("assignees")
    private String[] assignees;

    @JsonbProperty("requested_reviewers")
    private String[] requestedReviewers;

    @JsonbProperty("requested_teams")
    private String[] requestedTeams;

    @JsonbProperty("labels")
    private String[] labels;

    @JsonbProperty("milestone")
    private String milestone;

    @JsonbProperty("commits_url")
    private String commitsUrl;

    @JsonbProperty("review_comments_url")
    private String reviewCommentsUrl;

    @JsonbProperty("review_comment_url")
    private String reviewCommentUrl;

    @JsonbProperty("comments_url")
    private String commentsUrl;

    @JsonbProperty("statuses_url")
    private String statusesUrl;

    @JsonbProperty("_links")
    private Links links;

    @JsonbProperty("author_association")
    private String authorAssociation;

    @JsonbProperty("draft")
    private Boolean draft;

    @JsonbProperty("merged")
    private Boolean merged;

    @JsonbProperty("mergeable")
    private String mergeable;

    @JsonbProperty("rebaseable")
    private String rebaseable;

    @JsonbProperty("mergeable_state")
    private String mergeableState;

    @JsonbProperty("merged_by")
    private String mergedBy;

    @JsonbProperty("comments")
    private Long comments;

    @JsonbProperty("review_comments")
    private Long reviewComments;

    @JsonbProperty("maintainer_can_modify")
    private Boolean maintainerCanModify;

    @JsonbProperty("commits")
    private Long commits;

    @JsonbProperty("additions")
    private Long additions;

    @JsonbProperty("deletions")
    private Long deletions;

    @JsonbProperty("changed_files")
    private Long changedFiles;

    @JsonbProperty("href")
    private String href;

}
