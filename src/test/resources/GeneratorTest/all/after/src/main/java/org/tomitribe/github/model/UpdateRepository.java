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
public class UpdateRepository {

    @JsonbProperty("private")
    private Boolean _private;

    @JsonbProperty("allow_merge_commit")
    private Boolean allowMergeCommit;

    @JsonbProperty("allow_rebase_merge")
    private Boolean allowRebaseMerge;

    @JsonbProperty("allow_squash_merge")
    private Boolean allowSquashMerge;

    @JsonbProperty("archived")
    private Boolean archived;

    @JsonbProperty("default_branch")
    private String defaultBranch;

    @JsonbProperty("delete_branch_on_merge")
    private Boolean deleteBranchOnMerge;

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("has_issues")
    private Boolean hasIssues;

    @JsonbProperty("has_projects")
    private Boolean hasProjects;

    @JsonbProperty("has_wiki")
    private Boolean hasWiki;

    @JsonbProperty("homepage")
    private String homepage;

    @JsonbProperty("is_template")
    private Boolean isTemplate;

    @JsonbProperty("name")
    private String name;

    @JsonbTransient
    @PathParam("owner")
    private String owner;

    @JsonbTransient
    @PathParam("repo")
    private String repo;

    @JsonbProperty("visibility")
    @JsonbTypeAdapter(VisibilityAdapter.class)
    private Visibility visibility;

    public enum Visibility {

        PUBLIC("public"), PRIVATE("private"), VISIBILITY("visibility"), INTERNAL("internal");

        private final String name;

        Visibility(final String name) {
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

    public static class VisibilityAdapter extends EnumAdapter<Visibility> {

        public VisibilityAdapter() {
            super(Visibility.class);
        }
    }
}
