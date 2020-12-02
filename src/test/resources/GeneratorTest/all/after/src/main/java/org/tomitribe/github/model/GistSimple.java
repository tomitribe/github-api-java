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

import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ComponentId("#/components/schemas/gist-simple")
public class GistSimple {

    @JsonbProperty("public")
    private Boolean _public;

    @JsonbProperty("comments")
    private Integer comments;

    @JsonbProperty("comments_url")
    private String commentsUrl;

    @JsonbProperty("commits_url")
    private String commitsUrl;

    @JsonbProperty("created_at")
    private String createdAt;

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("files")
    private Map<String, File> files;

    @JsonbProperty("forks_url")
    private String forksUrl;

    @JsonbProperty("git_pull_url")
    private String gitPullUrl;

    @JsonbProperty("git_push_url")
    private String gitPushUrl;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("id")
    private String id;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("owner")
    private SimpleUser owner;

    @JsonbProperty("truncated")
    private Boolean truncated;

    @JsonbProperty("updated_at")
    private String updatedAt;

    @JsonbProperty("url")
    private String url;

    @JsonbProperty("user")
    private String user;
}
