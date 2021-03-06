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
import java.util.List;
import javax.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ComponentId("#/components/schemas/commit-search-result-item")
public class CommitSearchResultItem {

    @JsonbProperty("author")
    private SimpleUser author;

    @JsonbProperty("comments_url")
    private URI commentsUrl;

    @JsonbProperty("commit")
    private Commit commit;

    @JsonbProperty("committer")
    private GitUser committer;

    @JsonbProperty("html_url")
    private URI htmlUrl;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("parents")
    private List<Parents> parents;

    @JsonbProperty("repository")
    private MinimalRepository repository;

    @JsonbProperty("score")
    private Integer score;

    @JsonbProperty("sha")
    private String sha;

    @JsonbProperty("text_matches")
    private List<SearchResultTextMatches> textMatches;

    @JsonbProperty("url")
    private URI url;
}
