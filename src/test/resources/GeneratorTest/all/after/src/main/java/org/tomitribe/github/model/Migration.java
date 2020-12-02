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
@ComponentId("#/components/schemas/migration")
public class Migration {

    @JsonbProperty("archive_url")
    private URI archiveUrl;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("exclude")
    private List<Exclude> exclude;

    @JsonbProperty("exclude_attachments")
    private Boolean excludeAttachments;

    @JsonbProperty("guid")
    private String guid;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("lock_repositories")
    private Boolean lockRepositories;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("owner")
    private SimpleUser owner;

    @JsonbProperty("repositories")
    private List<Repository> repositories;

    @JsonbProperty("state")
    private String state;

    @JsonbProperty("updated_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date updatedAt;

    @JsonbProperty("url")
    private URI url;
}
