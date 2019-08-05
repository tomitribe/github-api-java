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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

/**
 * Used by:
 * - CheckRunEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckRun {

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("head_sha")
    private String headSha;

    @JsonbProperty("external_id")
    private String externalId;

    @JsonbProperty("url")
    private String url;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("details_url")
    private String detailsUrl;

    @JsonbProperty("status")
    private String status;

    @JsonbProperty("conclusion")
    private String conclusion;

    @JsonbProperty("started_at")
    private String startedAt;

    @JsonbProperty("completed_at")
    private String completedAt;

    @JsonbProperty("output")
    private Output output;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("check_suite")
    private CheckSuite checkSuite;

    @JsonbProperty("app")
    private App app;

    @JsonbProperty("pull_requests")
    private List<PullRequest> pullRequests;

}
