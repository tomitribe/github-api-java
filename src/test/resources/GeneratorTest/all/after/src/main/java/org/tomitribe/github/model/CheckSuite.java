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

import java.util.Date;
import java.util.List;
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
@ComponentId("#/components/schemas/check-suite")
public class CheckSuite {

    @JsonbProperty("after")
    private String after;

    @JsonbProperty("app")
    private Integration app;

    @JsonbProperty("before")
    private String before;

    @JsonbProperty("check_runs_url")
    private String checkRunsUrl;

    @JsonbProperty("conclusion")
    private String conclusion;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("head_branch")
    private String headBranch;

    @JsonbProperty("head_commit")
    private SimpleCommit headCommit;

    @JsonbProperty("head_sha")
    private String headSha;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("latest_check_runs_count")
    private Integer latestCheckRunsCount;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("pull_requests")
    private List<PullRequestMinimal> pullRequests;

    @JsonbProperty("repository")
    private MinimalRepository repository;

    @JsonbProperty("status")
    private String status;

    @JsonbProperty("updated_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date updatedAt;

    @JsonbProperty("url")
    private String url;
}
