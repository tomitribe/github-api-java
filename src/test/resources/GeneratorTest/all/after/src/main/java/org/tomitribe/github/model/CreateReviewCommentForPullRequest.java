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

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.ws.rs.PathParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tomitribe.github.core.EnumAdapter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewCommentForPullRequest {

    @JsonbProperty("body")
    private String body;

    @JsonbProperty("commit_id")
    private String commitId;

    @JsonbProperty("in_reply_to")
    private Integer inReplyTo;

    @JsonbProperty("line")
    private Integer line;

    @JsonbTransient
    @PathParam("owner")
    private String owner;

    @JsonbProperty("path")
    private String path;

    @JsonbProperty("position")
    private Integer position;

    @JsonbTransient
    @PathParam("pull-number")
    private Integer pullNumber;

    @JsonbTransient
    @PathParam("repo")
    private String repo;

    @JsonbProperty("side")
    @JsonbTypeAdapter(SideAdapter.class)
    private Side side;

    @JsonbProperty("start_line")
    private Integer startLine;

    @JsonbProperty("start_side")
    @JsonbTypeAdapter(StartSideAdapter.class)
    private StartSide startSide;

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

        LEFT("LEFT"), RIGHT("RIGHT"), SIDE("side");

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
