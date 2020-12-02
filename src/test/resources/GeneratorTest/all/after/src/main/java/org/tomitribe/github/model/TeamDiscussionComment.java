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
@ComponentId("#/components/schemas/team-discussion-comment")
public class TeamDiscussionComment {

    @JsonbProperty("author")
    private SimpleUser author;

    @JsonbProperty("body")
    private String body;

    @JsonbProperty("body_html")
    private String bodyHtml;

    @JsonbProperty("body_version")
    private String bodyVersion;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("discussion_url")
    private URI discussionUrl;

    @JsonbProperty("html_url")
    private URI htmlUrl;

    @JsonbProperty("last_edited_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date lastEditedAt;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("number")
    private Integer number;

    @JsonbProperty("reactions")
    private ReactionRollup reactions;

    @JsonbProperty("updated_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date updatedAt;

    @JsonbProperty("url")
    private URI url;
}
