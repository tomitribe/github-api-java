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
 * - CheckRunEvent
 * - CheckSuiteEvent
 * - InstallationEvent
 * - InstallationRepositoriesEvent
 * - TeamEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Permissions {

    @JsonbProperty("admin")
    private Boolean admin;

    @JsonbProperty("administration")
    private String administration;

    @JsonbProperty("checks")
    private String checks;

    @JsonbProperty("contents")
    private String contents;

    @JsonbProperty("deployments")
    private String deployments;

    @JsonbProperty("issues")
    private String issues;

    @JsonbProperty("members")
    private String members;

    @JsonbProperty("metadata")
    private String metadata;

    @JsonbProperty("organization_administration")
    private String organizationAdministration;

    @JsonbProperty("organization_hooks")
    private String organizationHooks;

    @JsonbProperty("organization_plan")
    private String organizationPlan;

    @JsonbProperty("organization_projects")
    private String organizationProjects;

    @JsonbProperty("organization_user_blocking")
    private String organizationUserBlocking;

    @JsonbProperty("pages")
    private String pages;

    @JsonbProperty("pull")
    private Boolean pull;

    @JsonbProperty("pull_requests")
    private String pullRequests;

    @JsonbProperty("push")
    private Boolean push;

    @JsonbProperty("repository_hooks")
    private String repositoryHooks;

    @JsonbProperty("repository_projects")
    private String repositoryProjects;

    @JsonbProperty("statuses")
    private String statuses;

    @JsonbProperty("team_discussions")
    private String teamDiscussions;

    @JsonbProperty("vulnerability_alerts")
    private String vulnerabilityAlerts;
}
