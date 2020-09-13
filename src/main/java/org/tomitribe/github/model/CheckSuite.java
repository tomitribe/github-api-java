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
 * - CheckSuiteEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckSuite {

    @JsonbProperty("after")
    private String after;

    @JsonbProperty("app")
    private App app;

    @JsonbProperty("before")
    private String before;

    @JsonbProperty("check_runs_url")
    private String checkRunsUrl;

    @JsonbProperty("conclusion")
    private String conclusion;

    @JsonbProperty("created_at")
    private String createdAt;

    @JsonbProperty("head_branch")
    private String headBranch;

    @JsonbProperty("head_commit")
    private HeadCommit headCommit;

    @JsonbProperty("head_sha")
    private String headSha;

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("latest_check_runs_count")
    private Long latestCheckRunsCount;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("pull_requests")
    private List<PullRequest> pullRequests;

    @JsonbProperty("status")
    private String status;

    @JsonbProperty("updated_at")
    private String updatedAt;

    @JsonbProperty("url")
    private String url;
}
