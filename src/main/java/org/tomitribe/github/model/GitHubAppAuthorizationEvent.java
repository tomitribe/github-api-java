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
 * <p>Triggered when someone revokes their authorization of a GitHub App. A GitHub App receives this
 * webhook by default and cannot unsubscribe from this event. This event is not available in the <a
 * href="/v3/activity/events/">Events API</a>.</p>
 *
 * <p>Anyone can revoke their authorization of a GitHub App from their <a
 * href="https://github.com/settings/apps/authorizations">GitHub account settings page</a>. Revoking
 * the authorization of a GitHub App does not uninstall the GitHub App. You should program your GitHub
 * App so that when it receives this webhook, it stops calling the API on behalf of the person who
 * revoked the token. If your GitHub App continues to use a revoked access token, it will receive the
 * <code>401 Bad Credentials</code> error.
 * For details about user-to-server requests, which require GitHub App authorization, see "<a
 * href="/apps/building-github-apps/identifying-and-authorizing-users-for-github-apps/">Identifying and
 * authorizing users for GitHub Apps</a>."</p>
 * Used by:
 * -
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("github_app_authorization")
@EqualsAndHashCode(callSuper = true)
public class GitHubAppAuthorizationEvent extends Event {

    @JsonbProperty("action")
    private String action;

    @JsonbProperty("sender")
    private Sender sender;
}
