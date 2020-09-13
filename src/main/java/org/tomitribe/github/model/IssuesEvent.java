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
 * <p>Triggered when an <a href="/v3/issues">issue</a> is <code>opened</code>, <code>edited</code>,
 * <code>deleted</code>, <code>transferred</code>, <code>pinned</code>, <code>unpinned</code>,
 * <code>closed</code>, <code>reopened</code>, <code>assigned</code>, <code>unassigned</code>,
 * <code>labeled</code>, <code>unlabeled</code>, <code>locked</code>, <code>unlocked</code>,
 * <code>milestoned</code>, or <code>demilestoned</code>.</p>
 * Used by:
 * -
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("issues")
@EqualsAndHashCode(callSuper = true)
public class IssuesEvent extends Event {

    /**
     * The action that was performed. Can be one of <code>opened</code>, <code>edited</code>,
     * <code>deleted</code>, <code>transferred</code>, <code>pinned</code>, <code>unpinned</code>,
     * <code>closed</code>, <code>reopened</code>, <code>assigned</code>, <code>unassigned</code>,
     * <code>labeled</code>, <code>unlabeled</code>, <code>locked</code>, <code>unlocked</code>,
     * <code>milestoned</code>, or <code>demilestoned</code>.
     */
    @JsonbProperty("action")
    private String action;

    /**
     * The changes to the issue if the action was <code>edited</code>.
     */
    @JsonbProperty("changes")
    private Changes changes;

    /**
     * The <a href="/v3/issues">issue</a> itself.
     */
    @JsonbProperty("issue")
    private Issue issue;

    @JsonbProperty("repository")
    private Repository repository;

    @JsonbProperty("sender")
    private Sender sender;
}
