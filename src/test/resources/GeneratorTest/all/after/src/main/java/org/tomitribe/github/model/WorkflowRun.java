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
@ComponentId("#/components/schemas/workflow-run")
public class WorkflowRun {

    @JsonbProperty("artifacts_url")
    private String artifactsUrl;

    @JsonbProperty("cancel_url")
    private String cancelUrl;

    @JsonbProperty("check_suite_url")
    private String checkSuiteUrl;

    @JsonbProperty("conclusion")
    private String conclusion;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("event")
    private String event;

    @JsonbProperty("head_branch")
    private String headBranch;

    @JsonbProperty("head_commit")
    private SimpleCommit headCommit;

    @JsonbProperty("head_repository")
    private MinimalRepository headRepository;

    @JsonbProperty("head_repository_id")
    private Integer headRepositoryId;

    @JsonbProperty("head_sha")
    private String headSha;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("jobs_url")
    private String jobsUrl;

    @JsonbProperty("logs_url")
    private String logsUrl;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("pull_requests")
    private List<PullRequestMinimal> pullRequests;

    @JsonbProperty("repository")
    private MinimalRepository repository;

    @JsonbProperty("rerun_url")
    private String rerunUrl;

    @JsonbProperty("run_number")
    private Integer runNumber;

    @JsonbProperty("status")
    private String status;

    @JsonbProperty("updated_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date updatedAt;

    @JsonbProperty("url")
    private String url;

    @JsonbProperty("workflow_id")
    private Integer workflowId;

    @JsonbProperty("workflow_url")
    private String workflowUrl;
}
