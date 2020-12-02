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
@ComponentId("#/components/schemas/issue-event")
public class IssueEvent {

    @JsonbProperty("actor")
    private SimpleUser actor;

    @JsonbProperty("assignee")
    private SimpleUser assignee;

    @JsonbProperty("assigner")
    private SimpleUser assigner;

    @JsonbProperty("author_association")
    private String authorAssociation;

    @JsonbProperty("commit_id")
    private String commitId;

    @JsonbProperty("commit_url")
    private String commitUrl;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("dismissed_review")
    private IssueEventDismissedReview dismissedReview;

    @JsonbProperty("event")
    private String event;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("issue")
    private IssueSimple issue;

    @JsonbProperty("label")
    private IssueEventLabel label;

    @JsonbProperty("lock_reason")
    private String lockReason;

    @JsonbProperty("milestone")
    private IssueEventMilestone milestone;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("project_card")
    private IssueEventProjectCard projectCard;

    @JsonbProperty("rename")
    private IssueEventRename rename;

    @JsonbProperty("requested_reviewer")
    private SimpleUser requestedReviewer;

    @JsonbProperty("requested_team")
    private Team requestedTeam;

    @JsonbProperty("review_requester")
    private SimpleUser reviewRequester;

    @JsonbProperty("url")
    private URI url;
}
