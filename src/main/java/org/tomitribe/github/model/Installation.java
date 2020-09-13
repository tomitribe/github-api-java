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
 * - ContentReferenceEvent
 * - InstallationEvent
 * - InstallationRepositoriesEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Installation {

    @JsonbProperty("access_tokens_url")
    private String accessTokensUrl;

    @JsonbProperty("account")
    private Account account;

    @JsonbProperty("app_id")
    private Long appId;

    @JsonbProperty("created_at")
    private Long createdAt;

    @JsonbProperty("events")
    private String[] events;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("permissions")
    private Permissions permissions;

    @JsonbProperty("repositories_url")
    private String repositoriesUrl;

    @JsonbProperty("repository_selection")
    private String repositorySelection;

    @JsonbProperty("single_file_name")
    private String singleFileName;

    @JsonbProperty("target_id")
    private Long targetId;

    @JsonbProperty("target_type")
    private String targetType;

    @JsonbProperty("updated_at")
    private Long updatedAt;
}
