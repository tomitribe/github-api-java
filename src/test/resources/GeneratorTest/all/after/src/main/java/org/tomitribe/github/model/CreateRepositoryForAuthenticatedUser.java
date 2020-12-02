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
public class CreateRepositoryForAuthenticatedUser {

    @JsonbProperty("private")
    private Boolean _private;

    @JsonbProperty("allow_merge_commit")
    private Boolean allowMergeCommit;

    @JsonbProperty("allow_rebase_merge")
    private Boolean allowRebaseMerge;

    @JsonbProperty("allow_squash_merge")
    private Boolean allowSquashMerge;

    @JsonbProperty("auto_init")
    private Boolean autoInit;

    @JsonbProperty("delete_branch_on_merge")
    private Boolean deleteBranchOnMerge;

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("gitignore_template")
    private String gitignoreTemplate;

    @JsonbProperty("has_downloads")
    private Boolean hasDownloads;

    @JsonbProperty("has_issues")
    private Boolean hasIssues;

    @JsonbProperty("has_projects")
    private Boolean hasProjects;

    @JsonbProperty("has_wiki")
    private Boolean hasWiki;

    @JsonbProperty("homepage")
    private String homepage;

    @JsonbProperty("is_template")
    private Boolean isTemplate;

    @JsonbProperty("license_template")
    private String licenseTemplate;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("team_id")
    private Integer teamId;
}
