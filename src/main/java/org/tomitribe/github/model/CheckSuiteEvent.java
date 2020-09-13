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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.json.bind.annotation.JsonbProperty;

/**
 * <p>Triggered when a check suite is <code>completed</code>, <code>requested</code>, or
 * <code>rerequested</code>. The <code>checks</code> permission allows you to use the Checks API. If
 * you plan to create or modify check runs, your GitHub App will need to have the
 * <code>checks:write</code> permission. If you only plan to consume check runs, your GitHub App only
 * needs the <code>checks:read</code> permission.</p>
 *
 * <p>GitHub Apps with the <code>checks:write</code> permission will receive the <code>requested</code>
 * and <code>rerequested</code> action payloads without subscribing to the <code>check_suite</code>
 * webhook event. The <code>requested</code> action triggers when new code is pushed to the app's
 * repository. A <code>rerequested</code> action occurs when someone requests to re-run the entire
 * check suite from the pull request UI. See "<a
 * href="https://help.github.com/articles/about-status-checks#checks">About status checks</a>" for more
 * details about the GitHub UI. When you receive the <code>requested</code> or <code>rerequested</code>
 * action events, you'll need to <a href="/v3/checks/runs/#create-a-check-run">create a new check
 * run</a>. Only the GitHub App that is being asked to run a check will receive the
 * <code>requested</code> and <code>rerequested</code> payloads.</p>
 *
 * <p>GitHub Apps that have the <code>checks:read</code> permission and subscribe to the
 * <code>check_suite</code> webhook event receive the <code>completed</code> action payload for all
 * check suites in the app's repository. Repositories and organizations that subscribe to the
 * <code>check_suite</code> webhook event only receive the <code>completed</code> event action.</p>
 *
 * <div class="alert note">
 *
 * <p><strong>Note:</strong> The Checks API only looks for pushes in the repository where the check
 * suite or check run were created.  Pushes to a branch in a forked repository are not detected and
 * return an empty <code>pull_requests</code> array and a <code>null</code> value for
 * <code>head_branch</code>.</p>
 *
 * </div>
 * Used by:
 * -
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("check_suite")
@EqualsAndHashCode(callSuper = true)
public class CheckSuiteEvent extends Event {

    /**
     * The action performed. Can be <code>completed</code>, <code>requested</code> or
     * <code>rerequested</code>.
     */
    @JsonbProperty("action")
    private String action;

    /**
     * The <a href="/v3/checks/suites/">check_suite</a>.
     */
    @JsonbProperty("check_suite")
    private CheckSuite checkSuite;

    @JsonbProperty("repository")
    private Repository repository;

    @JsonbProperty("sender")
    private Sender sender;
}
