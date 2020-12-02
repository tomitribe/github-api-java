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
import javax.json.bind.annotation.JsonbTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tomitribe.github.core.EnumAdapter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ComponentId("#/components/schemas/commit-comparison")
public class CommitComparison {

    @JsonbProperty("ahead_by")
    private Integer aheadBy;

    @JsonbProperty("base_commit")
    private Commit baseCommit;

    @JsonbProperty("behind_by")
    private Integer behindBy;

    @JsonbProperty("commits")
    private List<Commit> commits;

    @JsonbProperty("diff_url")
    private URI diffUrl;

    @JsonbProperty("files")
    private List<DiffEntry> files;

    @JsonbProperty("html_url")
    private URI htmlUrl;

    @JsonbProperty("merge_base_commit")
    private Commit mergeBaseCommit;

    @JsonbProperty("patch_url")
    private URI patchUrl;

    @JsonbProperty("permalink_url")
    private URI permalinkUrl;

    @JsonbProperty("status")
    @JsonbTypeAdapter(StatusAdapter.class)
    private Status status;

    @JsonbProperty("total_commits")
    private Integer totalCommits;

    @JsonbProperty("url")
    private URI url;

    public enum Status {

        DIVERGED("diverged"), AHEAD("ahead"), BEHIND("behind"), IDENTICAL("identical");

        private final String name;

        Status(final String name) {
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

    public static class StatusAdapter extends EnumAdapter<Status> {

        public StatusAdapter() {
            super(Status.class);
        }
    }
}
