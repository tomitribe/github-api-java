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

import javax.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ComponentId("#/components/schemas/issue-event-for-issue")
public class IssueEventForIssue {

    @JsonbProperty("actor")
    private SimpleUser actor;

    @JsonbProperty("author_association")
    private String authorAssociation;

    @JsonbProperty("body")
    private String body;

    @JsonbProperty("body_html")
    private String bodyHtml;

    @JsonbProperty("body_text")
    private String bodyText;

    @JsonbProperty("commit_id")
    private String commitId;

    @JsonbProperty("commit_url")
    private String commitUrl;

    @JsonbProperty("created_at")
    private String createdAt;

    @JsonbProperty("event")
    private String event;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("issue_url")
    private String issueUrl;

    @JsonbProperty("lock_reason")
    private String lockReason;

    @JsonbProperty("message")
    private String message;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("pull_request_url")
    private String pullRequestUrl;

    @JsonbProperty("sha")
    private String sha;

    @JsonbProperty("state")
    private String state;

    @JsonbProperty("submitted_at")
    private String submittedAt;

    @JsonbProperty("updated_at")
    private String updatedAt;

    @JsonbProperty("url")
    private String url;
}
