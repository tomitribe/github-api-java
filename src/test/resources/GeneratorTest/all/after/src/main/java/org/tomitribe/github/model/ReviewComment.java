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
import javax.json.bind.annotation.JsonbTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tomitribe.github.core.DateAdapter;
import org.tomitribe.github.core.EnumAdapter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ComponentId("#/components/schemas/review-comment")
public class ReviewComment {

    @JsonbProperty("author_association")
    private String authorAssociation;

    @JsonbProperty("body")
    private String body;

    @JsonbProperty("body_html")
    private String bodyHtml;

    @JsonbProperty("body_text")
    private String bodyText;

    @JsonbProperty("commit_id")
    private String commitId;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("diff_hunk")
    private String diffHunk;

    @JsonbProperty("html_url")
    private URI htmlUrl;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("in_reply_to_id")
    private Integer inReplyToId;

    @JsonbProperty("line")
    private Integer line;

    @JsonbProperty("_links")
    private Links links;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("original_commit_id")
    private String originalCommitId;

    @JsonbProperty("original_line")
    private Integer originalLine;

    @JsonbProperty("original_position")
    private Integer originalPosition;

    @JsonbProperty("original_start_line")
    private Integer originalStartLine;

    @JsonbProperty("path")
    private String path;

    @JsonbProperty("position")
    private Integer position;

    @JsonbProperty("pull_request_review_id")
    private Integer pullRequestReviewId;

    @JsonbProperty("pull_request_url")
    private URI pullRequestUrl;

    @JsonbProperty("side")
    @JsonbTypeAdapter(SideAdapter.class)
    private Side side;

    @JsonbProperty("start_line")
    private Integer startLine;

    @JsonbProperty("start_side")
    @JsonbTypeAdapter(StartSideAdapter.class)
    private StartSide startSide;

    @JsonbProperty("updated_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date updatedAt;

    @JsonbProperty("url")
    private URI url;

    @JsonbProperty("user")
    private SimpleUser user;

    public enum Side {

        LEFT("LEFT"), RIGHT("RIGHT");

        private final String name;

        Side(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static class SideAdapter extends EnumAdapter<Side> {

        public SideAdapter() {
            super(Side.class);
        }
    }

    public enum StartSide {

        LEFT("LEFT"), RIGHT("RIGHT");

        private final String name;

        StartSide(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static class StartSideAdapter extends EnumAdapter<StartSide> {

        public StartSideAdapter() {
            super(StartSide.class);
        }
    }
}
