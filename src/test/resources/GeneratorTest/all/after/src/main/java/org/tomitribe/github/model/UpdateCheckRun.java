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

import java.util.List;
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
public class UpdateCheckRun {

    @JsonbProperty("actions")
    private List<Actions> actions;

    @JsonbTransient
    @PathParam("check_run_id")
    private Integer checkRunId;

    @JsonbProperty("completed_at")
    private String completedAt;

    @JsonbProperty("conclusion")
    @JsonbTypeAdapter(ConclusionAdapter.class)
    private Conclusion conclusion;

    @JsonbProperty("details_url")
    private String detailsUrl;

    @JsonbProperty("external_id")
    private String externalId;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("output")
    private Output output;

    @JsonbTransient
    @PathParam("owner")
    private String owner;

    @JsonbTransient
    @PathParam("repo")
    private String repo;

    @JsonbProperty("started_at")
    private String startedAt;

    @JsonbProperty("status")
    @JsonbTypeAdapter(StatusAdapter.class)
    private Status status;

    public enum Conclusion {

        SUCCESS("success"),
        FAILURE("failure"),
        NEUTRAL("neutral"),
        CANCELLED("cancelled"),
        SKIPPED("skipped"),
        TIMED_OUT("timed_out"),
        ACTION_REQUIRED("action_required");

        private final String name;

        Conclusion(final String name) {
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

    public static class ConclusionAdapter extends EnumAdapter<Conclusion> {

        public ConclusionAdapter() {
            super(Conclusion.class);
        }
    }

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
