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
 * - IssueCommentEvent
 * - IssuesEvent
 * - MilestoneEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Milestone {

    @JsonbProperty("closed_at")
    private String closedAt;

    @JsonbProperty("closed_issues")
    private Long closedIssues;

    @JsonbProperty("created_at")
    private String createdAt;

    @JsonbProperty("creator")
    private Creator creator;

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("due_on")
    private String dueOn;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("labels_url")
    private String labelsUrl;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("number")
    private Long number;

    @JsonbProperty("open_issues")
    private Long openIssues;

    @JsonbProperty("state")
    private String state;

    @JsonbProperty("title")
    private String title;

    @JsonbProperty("updated_at")
    private String updatedAt;

    @JsonbProperty("url")
    private String url;
}
