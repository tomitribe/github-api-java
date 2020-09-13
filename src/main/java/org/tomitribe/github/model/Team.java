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
 * - MembershipEvent
 * - TeamAddEvent
 * - TeamEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("members_url")
    private String membersUrl;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("permission")
    private String permission;

    @JsonbProperty("privacy")
    private String privacy;

    @JsonbProperty("repositories_url")
    private String repositoriesUrl;

    @JsonbProperty("slug")
    private String slug;

    @JsonbProperty("url")
    private String url;
}
