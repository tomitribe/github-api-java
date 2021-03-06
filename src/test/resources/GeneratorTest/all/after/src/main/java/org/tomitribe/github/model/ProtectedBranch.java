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
import javax.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ComponentId("#/components/schemas/protected-branch")
public class ProtectedBranch {

    @JsonbProperty("allow_deletions")
    private AllowDeletions allowDeletions;

    @JsonbProperty("allow_force_pushes")
    private AllowForcePushes allowForcePushes;

    @JsonbProperty("enforce_admins")
    private EnforceAdmins enforceAdmins;

    @JsonbProperty("required_linear_history")
    private RequiredLinearHistory requiredLinearHistory;

    @JsonbProperty("required_pull_request_reviews")
    private RequiredPullRequestReviews requiredPullRequestReviews;

    @JsonbProperty("required_signatures")
    private RequiredSignatures requiredSignatures;

    @JsonbProperty("required_status_checks")
    private StatusCheckPolicy requiredStatusChecks;

    @JsonbProperty("restrictions")
    private BranchRestrictionPolicy restrictions;

    @JsonbProperty("url")
    private URI url;
}
