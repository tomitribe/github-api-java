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
import java.util.List;

/**
 * Authenticating as an installation lets you perform actions in the API for that installation.
 * Before authenticating as an installation, you must create an installation access token. These
 * installation access tokens are used by GitHub Apps to authenticate.
 *
 * By default, installation access tokens are scoped to all the repositories that an installation
 * can access. You can limit the scope of the installation access token to specific repositories
 * by using the repository_ids parameter. See the Create a new installation token endpoint for more
 * details. Installation access tokens have the permissions configured by the GitHub App and expire
 * after one hour.
 *
 * To create an installation access token, include the JWT generated above in the Authorization
 * header in the API request:
 *
 * curl -i -X POST \
 * -H "Authorization: Bearer YOUR_JWT" \
 * -H "Accept: application/vnd.github.machine-man-preview+json" \
 * https://api.github.com/app/installations/:installation_id/access_tokens
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenResponse {

    @JsonbProperty("expires_at")
    private String expiresAt;

    @JsonbProperty("permissions")
    private Permissions permissions;

    @JsonbProperty("repositories")
    private List<Repository> repositories;

    @JsonbProperty("repository_selection")
    private String repositorySelection;

    @JsonbProperty("sender")
    private Sender sender;

    @JsonbProperty("token")
    private String token;
}
