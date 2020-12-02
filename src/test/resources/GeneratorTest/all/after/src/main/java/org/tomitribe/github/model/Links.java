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

import java.net.URI;
import java.util.List;
import javax.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Links {

    @JsonbProperty("comments")
    private Link comments;

    @JsonbProperty("commits")
    private Link commits;

    @JsonbProperty("current_user")
    private LinkWithType currentUser;

    @JsonbProperty("current_user_actor")
    private LinkWithType currentUserActor;

    @JsonbProperty("current_user_organization")
    private LinkWithType currentUserOrganization;

    @JsonbProperty("current_user_organizations")
    private List<LinkWithType> currentUserOrganizations;

    @JsonbProperty("current_user_public")
    private LinkWithType currentUserPublic;

    @JsonbProperty("git")
    private URI git;

    @JsonbProperty("html")
    private Link html;

    @JsonbProperty("issue")
    private Link issue;

    @JsonbProperty("pull_request")
    private Link pullRequest;

    @JsonbProperty("review_comment")
    private Link reviewComment;

    @JsonbProperty("review_comments")
    private Link reviewComments;

    @JsonbProperty("security_advisories")
    private LinkWithType securityAdvisories;

    @JsonbProperty("self")
    private Link self;

    @JsonbProperty("statuses")
    private Link statuses;

    @JsonbProperty("timeline")
    private LinkWithType timeline;

    @JsonbProperty("user")
    private LinkWithType user;
}
