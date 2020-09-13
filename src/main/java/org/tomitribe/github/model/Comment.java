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
 * - CommitCommentEvent
 * - IssueCommentEvent
 * - PullRequestReviewCommentEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @JsonbProperty("author_association")
    private String authorAssociation;

    @JsonbProperty("body")
    private String body;

    @JsonbProperty("commit_id")
    private String commitId;

    @JsonbProperty("created_at")
    private String createdAt;

    @JsonbProperty("diff_hunk")
    private String diffHunk;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("issue_url")
    private String issueUrl;

    @JsonbProperty("line")
    private String line;

    @JsonbProperty("_links")
    private Links links;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("original_commit_id")
    private String originalCommitId;

    @JsonbProperty("original_position")
    private Long originalPosition;

    @JsonbProperty("path")
    private String path;

    @JsonbProperty("position")
    private String position;

    @JsonbProperty("pull_request_review_id")
    private Long pullRequestReviewId;

    @JsonbProperty("pull_request_url")
    private String pullRequestUrl;

    @JsonbProperty("updated_at")
    private String updatedAt;

    @JsonbProperty("url")
    private String url;

    @JsonbProperty("user")
    private User user;
}
