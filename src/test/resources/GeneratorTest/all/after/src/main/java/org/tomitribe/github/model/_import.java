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
@ComponentId("#/components/schemas/import")
public class _import {

    @JsonbProperty("authors_count")
    private Integer authorsCount;

    @JsonbProperty("authors_url")
    private URI authorsUrl;

    @JsonbProperty("commit_count")
    private Integer commitCount;

    @JsonbProperty("error_message")
    private String errorMessage;

    @JsonbProperty("failed_step")
    private String failedStep;

    @JsonbProperty("has_large_files")
    private Boolean hasLargeFiles;

    @JsonbProperty("html_url")
    private URI htmlUrl;

    @JsonbProperty("import_percent")
    private Integer importPercent;

    @JsonbProperty("large_files_count")
    private Integer largeFilesCount;

    @JsonbProperty("large_files_size")
    private Integer largeFilesSize;

    @JsonbProperty("message")
    private String message;

    @JsonbProperty("project_choices")
    private List<ProjectChoices> projectChoices;

    @JsonbProperty("push_percent")
    private Integer pushPercent;

    @JsonbProperty("repository_url")
    private URI repositoryUrl;

    @JsonbProperty("status")
    @JsonbTypeAdapter(StatusAdapter.class)
    private Status status;

    @JsonbProperty("status_text")
    private String statusText;

    @JsonbProperty("svc_root")
    private String svcRoot;

    @JsonbProperty("svn_root")
    private String svnRoot;

    @JsonbProperty("tfvc_project")
    private String tfvcProject;

    @JsonbProperty("url")
    private URI url;

    @JsonbProperty("use_lfs")
    private String useLfs;

    @JsonbProperty("vcs")
    private String vcs;

    @JsonbProperty("vcs_url")
    private String vcsUrl;

    public enum Status {

        AUTH("auth"),
        ERROR("error"),
        NONE("none"),
        DETECTING("detecting"),
        CHOOSE("choose"),
        AUTH_FAILED("auth_failed"),
        IMPORTING("importing"),
        MAPPING("mapping"),
        WAITING_TO_PUSH("waiting_to_push"),
        PUSHING("pushing"),
        COMPLETE("complete"),
        SETUP("setup"),
        UNKNOWN("unknown"),
        DETECTION_FOUND_MULTIPLE("detection_found_multiple"),
        DETECTION_FOUND_NOTHING("detection_found_nothing"),
        DETECTION_NEEDS_AUTH("detection_needs_auth");

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
