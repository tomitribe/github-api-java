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

    @JsonbProperty("app")
    private App app;

    @JsonbProperty("check_suite")
    private CheckSuite checkSuite;

    @JsonbProperty("completed_at")
    private String completedAt;

    @JsonbProperty("conclusion")
    private String conclusion;

    @JsonbProperty("details_url")
    private String detailsUrl;

    @JsonbProperty("external_id")
    private String externalId;

    @JsonbProperty("head_sha")
    private String headSha;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("output")
    private Output output;

    @JsonbProperty("pull_requests")
    private List<PullRequest> pullRequests;

    @JsonbProperty("started_at")
    private String startedAt;

    @JsonbProperty("status")
    private String status;

    @JsonbProperty("url")
    private String url;
}
