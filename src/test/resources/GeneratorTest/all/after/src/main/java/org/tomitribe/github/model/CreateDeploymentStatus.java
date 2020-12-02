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
public class CreateDeploymentStatus {

    @JsonbProperty("auto_inactive")
    private Boolean autoInactive;

    @JsonbTransient
    @PathParam("deployment_id")
    private Integer deploymentId;

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("environment")
    @JsonbTypeAdapter(EnvironmentAdapter.class)
    private Environment environment;

    @JsonbProperty("environment_url")
    private String environmentUrl;

    @JsonbProperty("log_url")
    private String logUrl;

    @JsonbTransient
    @PathParam("owner")
    private String owner;

    @JsonbTransient
    @PathParam("repo")
    private String repo;

    @JsonbProperty("state")
    @JsonbTypeAdapter(StateAdapter.class)
    private State state;

    @JsonbProperty("target_url")
    private String targetUrl;

    public enum Environment {

        PRODUCTION("production"), STAGING("staging"), QA("qa");

        private final String name;

        Environment(final String name) {
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

    public static class EnvironmentAdapter extends EnumAdapter<Environment> {

        public EnvironmentAdapter() {
            super(Environment.class);
        }
    }

    public enum State {

        ERROR("error"),
        FAILURE("failure"),
        INACTIVE("inactive"),
        IN_PROGRESS("in_progress"),
        QUEUED("queued"),
        PENDING("pending"),
        SUCCESS("success");

        private final String name;

        State(final String name) {
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

    public static class StateAdapter extends EnumAdapter<State> {

        public StateAdapter() {
            super(State.class);
        }
    }
}
