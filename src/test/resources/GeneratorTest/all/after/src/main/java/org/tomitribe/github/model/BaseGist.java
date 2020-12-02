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
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tomitribe.github.core.DateAdapter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ComponentId("#/components/schemas/base-gist")
public class BaseGist {

    @JsonbProperty("public")
    private Boolean _public;

    @JsonbProperty("comments")
    private Integer comments;

    @JsonbProperty("comments_url")
    private URI commentsUrl;

    @JsonbProperty("commits_url")
    private URI commitsUrl;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("files")
    private Map<String, File> files;

    @JsonbProperty("forks")
    private List<Forks> forks;

    @JsonbProperty("forks_url")
    private URI forksUrl;

    @JsonbProperty("git_pull_url")
    private URI gitPullUrl;

    @JsonbProperty("git_push_url")
    private URI gitPushUrl;

    @JsonbProperty("history")
    private List<History> history;

    @JsonbProperty("html_url")
    private URI htmlUrl;

    @JsonbProperty("id")
    private String id;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("owner")
    private SimpleUser owner;

    @JsonbProperty("truncated")
    private Boolean truncated;

    @JsonbProperty("updated_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date updatedAt;

    @JsonbProperty("url")
    private URI url;

    @JsonbProperty("user")
    private SimpleUser user;
}
