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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.json.bind.annotation.JsonbProperty;

/**
 * <p>Triggered when a <a href="/v3/pulls">pull request</a> is <code>assigned</code>,
 * <code>unassigned</code>, <code>labeled</code>, <code>unlabeled</code>, <code>opened</code>,
 * <code>edited</code>, <code>closed</code>, <code>reopened</code>, <code>synchronize</code>,
 * <code>ready_for_review</code>, <code>locked</code>, <code>unlocked</code> or when a pull request
 * review is requested or removed.</p>
 * Used by:
 * -
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("pull_request")
@EqualsAndHashCode(callSuper = true)
public class PullRequestEvent extends Event {

    /**
     * The action that was performed. Can be one of <code>assigned</code>, <code>unassigned</code>,
     * <code>review_requested</code>, <code>review_request_removed</code>, <code>labeled</code>,
     * <code>unlabeled</code>, <code>opened</code>, <code>edited</code>, <code>closed</code>,
     * <code>ready_for_review</code>, <code>locked</code>, <code>unlocked</code>, or <code>reopened</code>.
     * If the action is <code>closed</code> and the <code>merged</code> key is <code>false</code>, the pull
     * request was closed with unmerged commits. If the action is <code>closed</code> and the
     * <code>merged</code> key is <code>true</code>, the pull request was merged. While webhooks are also
     * triggered when a pull request is synchronized, Events API timelines don't include pull request
     * events with the <code>synchronize</code> action.
     */
    @JsonbProperty("action")
    private String action;

    /**
     * The pull request number.
     */
    @JsonbProperty("number")
    private Long number;

    /**
     * The <a href="/v3/pulls">pull request</a> itself.
     */
    @JsonbProperty("pull_request")
    private PullRequest pullRequest;

    @JsonbProperty("repository")
    private Repository repository;

    @JsonbProperty("sender")
    private Sender sender;
}
