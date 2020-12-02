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
import javax.json.bind.annotation.JsonbTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tomitribe.github.core.DateAdapter;
import org.tomitribe.github.core.EnumAdapter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ComponentId("#/components/schemas/pull-request")
public class PullRequest {

    @JsonbProperty("active_lock_reason")
    private String activeLockReason;

    @JsonbProperty("additions")
    private Integer additions;

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

    @JsonbProperty("changed_files")
    private Integer changedFiles;

    @JsonbProperty("closed_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date closedAt;

    @JsonbProperty("comments")
    private Integer comments;

    @JsonbProperty("comments_url")
    private URI commentsUrl;

    @JsonbProperty("commits")
    private Integer commits;

    @JsonbProperty("commits_url")
    private URI commitsUrl;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("deletions")
    private Integer deletions;

    @JsonbProperty("diff_url")
    private URI diffUrl;

    @JsonbProperty("draft")
    private Boolean draft;

    @JsonbProperty("head")
    private Head head;

    @JsonbProperty("href")
    private String href;

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
    @JsonbTypeAdapter(DateAdapter.class)
    private Date mergedAt;

    @JsonbProperty("merged_by")
    private SimpleUser mergedBy;

    @JsonbProperty("milestone")
    private Milestone milestone;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("number")
    private Integer number;

    @JsonbProperty("patch_url")
    private URI patchUrl;

    @JsonbProperty("rebaseable")
    private Boolean rebaseable;

    @JsonbProperty("requested_reviewers")
    private List<SimpleUser> requestedReviewers;

    @JsonbProperty("requested_teams")
    private List<TeamSimple> requestedTeams;

    @JsonbProperty("review_comment_url")
    private String reviewCommentUrl;

    @JsonbProperty("review_comments")
    private Integer reviewComments;

    @JsonbProperty("review_comments_url")
    private URI reviewCommentsUrl;

    @JsonbProperty("state")
    @JsonbTypeAdapter(StateAdapter.class)
    private State state;

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

    public enum State {

        OPEN("open"), CLOSED("closed");

        private final String name;

        State(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static class StateAdapter extends EnumAdapter<State> {

        public StateAdapter() {
            super(State.class);
        }
    }
}
