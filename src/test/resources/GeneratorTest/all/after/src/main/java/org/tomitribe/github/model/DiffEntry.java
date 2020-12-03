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
import javax.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ComponentId("#/components/schemas/diff-entry")
public class DiffEntry {

    @JsonbProperty("additions")
    private Integer additions;

    @JsonbProperty("blob_url")
    private URI blobUrl;

    @JsonbProperty("changes")
    private Integer changes;

    @JsonbProperty("contents_url")
    private URI contentsUrl;

    @JsonbProperty("deletions")
    private Integer deletions;

    @JsonbProperty("filename")
    private String filename;

    @JsonbProperty("patch")
    private String patch;

    @JsonbProperty("previous_filename")
    private String previousFilename;

    @JsonbProperty("raw_url")
    private URI rawUrl;

    @JsonbProperty("sha")
    private String sha;

    @JsonbProperty("status")
    private String status;
}