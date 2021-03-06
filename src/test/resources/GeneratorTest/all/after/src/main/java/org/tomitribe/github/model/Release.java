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
@ComponentId("#/components/schemas/release")
public class Release {

    @JsonbProperty("assets")
    private List<ReleaseAsset> assets;

    @JsonbProperty("assets_url")
    private URI assetsUrl;

    @JsonbProperty("author")
    private SimpleUser author;

    @JsonbProperty("body")
    private String body;

    @JsonbProperty("body_html")
    private String bodyHtml;

    @JsonbProperty("body_text")
    private String bodyText;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("draft")
    private Boolean draft;

    @JsonbProperty("html_url")
    private URI htmlUrl;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("prerelease")
    private Boolean prerelease;

    @JsonbProperty("published_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date publishedAt;

    @JsonbProperty("tag_name")
    private String tagName;

    @JsonbProperty("tarball_url")
    private URI tarballUrl;

    @JsonbProperty("target_commitish")
    private String targetCommitish;

    @JsonbProperty("upload_url")
    private String uploadUrl;

    @JsonbProperty("url")
    private URI url;

    @JsonbProperty("zipball_url")
    private URI zipballUrl;
}
