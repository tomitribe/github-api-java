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
@ComponentId("#/components/schemas/topic-search-result-item")
public class TopicSearchResultItem {

    @JsonbProperty("aliases")
    private List<Aliases> aliases;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("created_by")
    private String createdBy;

    @JsonbProperty("curated")
    private Boolean curated;

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("display_name")
    private String displayName;

    @JsonbProperty("featured")
    private Boolean featured;

    @JsonbProperty("logo_url")
    private URI logoUrl;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("related")
    private List<Related> related;

    @JsonbProperty("released")
    private String released;

    @JsonbProperty("repository_count")
    private Integer repositoryCount;

    @JsonbProperty("score")
    private Integer score;

    @JsonbProperty("short_description")
    private String shortDescription;

    @JsonbProperty("text_matches")
    private List<SearchResultTextMatches> textMatches;

    @JsonbProperty("updated_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date updatedAt;
}
