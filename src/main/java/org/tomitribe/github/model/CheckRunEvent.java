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
 * <p>Triggered when a check run is <code>created</code>, <code>rerequested</code>,
 * <code>completed</code>, or has a <code>requested_action</code>. The <code>checks</code> permission
 * allows you to use the checks API. If you plan to create or modify check runs, your GitHub App will
 * need to have the <code>checks:write</code> permission. If you only plan to consume check runs, your
 * GitHub App only needs the <code>checks:read</code> permission.</p>
 *
 * <p>GitHub Apps with the <code>checks:write</code> permission will receive the
 * <code>rerequested</code> action without subscribing to the <code>check_run</code> webhook event. The
 * <code>rerequested</code> action occurs when someone requests to re-run your app's check from the
 * pull request UI. See "<a href="https://help.github.com/articles/about-status-checks#checks">About
 * status checks</a>" for more details about the GitHub UI. When you receive a <code>rerequested</code>
 * action, you'll need to <a href="/v3/checks/runs/#create-a-check-run">create a new check run</a>.
 * Only the GitHub App that someone requests to re-run the check will receive the
 * <code>rerequested</code> payload. Similarly, only the GitHub App someone requests to perform an
 * action specified by the app will receive the <code>requested_action</code> payload.</p>
 *
 * <p>GitHub Apps that have the <code>checks:read</code> permission and subscribe to the
 * <code>check_run</code> webhook event receive the <code>created</code> and <code>completed</code>
 * action payloads for all check runs in the app's repository. Repositories and organizations that
 * subscribe to the <code>check_run</code> webhook event only receive <code>created</code> and
 * <code>completed</code> event actions.</p>
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
@GithubEvent("check_run")
@EqualsAndHashCode(callSuper = true)
public class CheckRunEvent extends Event {

    @JsonbProperty("action")
    private String action;

    @JsonbProperty("check_run")
    private CheckRun checkRun;

    @JsonbProperty("repository")
    private Repository repository;

    @JsonbProperty("sender")
    private Sender sender;
}
