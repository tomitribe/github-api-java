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

package org.tomitribe.github.app.events;

import javax.json.bind.annotation.JsonbProperty;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Used by:
 * - RegistryPackageEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackageFile {

    @JsonbProperty("download_url")
    private String downloadUrl;

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("sha256")
    private String sha256;

    @JsonbProperty("sha1")
    private String sha1;

    @JsonbProperty("md5")
    private String md5;

    @JsonbProperty("content_type")
    private String contentType;

    @JsonbProperty("state")
    private String state;

    @JsonbProperty("size")
    private Long size;

    @JsonbProperty("created_at")
    private String createdAt;

    @JsonbProperty("updated_at")
    private String updatedAt;

}
