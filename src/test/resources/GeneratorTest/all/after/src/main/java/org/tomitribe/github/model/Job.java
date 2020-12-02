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

import java.util.Date;
import java.util.List;
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
@ComponentId("#/components/schemas/job")
public class Job {

    @JsonbProperty("check_run_url")
    private String checkRunUrl;

    @JsonbProperty("completed_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date completedAt;

    @JsonbProperty("conclusion")
    private String conclusion;

    @JsonbProperty("head_sha")
    private String headSha;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("run_id")
    private Integer runId;

    @JsonbProperty("run_url")
    private String runUrl;

    @JsonbProperty("started_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date startedAt;

    @JsonbProperty("status")
    @JsonbTypeAdapter(StatusAdapter.class)
    private Status status;

    @JsonbProperty("steps")
    private List<Steps> steps;

    @JsonbProperty("url")
    private String url;

    public enum Status {

        QUEUED("queued"), IN_PROGRESS("in_progress"), COMPLETED("completed");

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
