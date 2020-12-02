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
public class Permissions {

    @JsonbProperty("admin")
    private Boolean admin;

    @JsonbProperty("can_create_repository")
    private Boolean canCreateRepository;

    @JsonbProperty("checks")
    private String checks;

    @JsonbProperty("contents")
    private String contents;

    @JsonbProperty("def_not_a_repo")
    private String defNotRepo;

    @JsonbProperty("deployments")
    private String deployments;

    @JsonbProperty("issues")
    private String issues;

    @JsonbProperty("maintain")
    private Boolean maintain;

    @JsonbProperty("metadata")
    private String metadata;

    @JsonbProperty("organization_administration")
    private String organizationAdministration;

    @JsonbProperty("pull")
    private Boolean pull;

    @JsonbProperty("pull_requests")
    private String pullRequests;

    @JsonbProperty("push")
    private Boolean push;

    @JsonbProperty("read")
    private Boolean read;

    @JsonbProperty("single_file")
    private String singleFile;

    @JsonbProperty("statuses")
    private String statuses;

    @JsonbProperty("triage")
    private Boolean triage;

    @JsonbProperty("write")
    private Boolean write;
}
