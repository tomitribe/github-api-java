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
import javax.json.bind.annotation.JsonbTransient;
import javax.ws.rs.PathParam;

/**
 * Example:
 *
 * {
 *   "title": "Amazing new feature",
 *   "body": "Please pull these awesome changes in!",
 *   "head": "octocat:new-feature",
 *   "base": "master"
 * }
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePullRequest {

    /**
     * Required. The name of the branch you want the changes pulled into.
     * This should be an existing branch on the current repository. You
     * cannot submit a pull request to one repository that requests a
     * merge to a base of another repository.
     */
    @JsonbProperty("base")
    private String base;

    /**
     * The contents of the pull request.
     */
    @JsonbProperty("body")
    private String body;

    /**
     * Indicates whether the pull request is a draft. See "Draft Pull Requests"
     * in the GitHub Help documentation to learn more.
     *
     * https://help.github.com/en/articles/about-pull-requests#draft-pull-requests
     */
    @JsonbProperty("draft")
    private Boolean draft;

    /**
     * Required. The name of the branch where your changes are implemented.
     * For cross-repository pull requests in the same network, namespace
     * head with a user like this: username:branch
     */
    @JsonbProperty("head")
    private String head;

    /**
     * Indicates whether maintainers can modify the pull request.
     *
     * https://help.github.com/articles/allowing-changes-to-a-pull-request-branch-created-from-a-fork/
     */
    @JsonbProperty("maintainer_can_modify")
    private Boolean maintainerCanModify;

    @JsonbTransient
    @PathParam("owner")
    private String owner;

    @JsonbTransient
    @PathParam("repo")
    private String repo;

    /**
     * Required. The title of the new pull request.
     */
    @JsonbProperty("title")
    private String title;
}
