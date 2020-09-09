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
 * Used by:
 * - RegistryPackageEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackageVersion {

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("version")
    private String version;

    @JsonbProperty("summary")
    private String summary;

    @JsonbProperty("body")
    private String body;

    @JsonbProperty("body_html")
    private String bodyHtml;

    @JsonbProperty("release")
    private Release release;

    @JsonbProperty("manifest")
    private String manifest;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("tag_name")
    private String tagName;

    @JsonbProperty("target_commitish")
    private String targetCommitish;

    @JsonbProperty("target_oid")
    private String targetOid;

    @JsonbProperty("draft")
    private Boolean draft;

    @JsonbProperty("prerelease")
    private Boolean prerelease;

    @JsonbProperty("created_at")
    private String createdAt;

    @JsonbProperty("updated_at")
    private String updatedAt;

    @JsonbProperty("metadata")
    private String[] metadata;

    @JsonbProperty("package_files")
    private List<PackageFile> packageFiles;

    @JsonbProperty("author")
    private Author author;

    @JsonbProperty("installation_command")
    private String installationCommand;

}
