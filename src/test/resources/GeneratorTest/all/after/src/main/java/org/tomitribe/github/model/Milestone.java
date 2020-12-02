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
@ComponentId("#/components/schemas/milestone")
public class Milestone {

    @JsonbProperty("closed_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date closedAt;

    @JsonbProperty("closed_issues")
    private Integer closedIssues;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("creator")
    private SimpleUser creator;

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("due_on")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date dueOn;

    @JsonbProperty("html_url")
    private URI htmlUrl;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("labels_url")
    private URI labelsUrl;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("number")
    private Integer number;

    @JsonbProperty("open_issues")
    private Integer openIssues;

    @JsonbProperty("state")
    @JsonbTypeAdapter(StateAdapter.class)
    private State state;

    @JsonbProperty("title")
    private String title;

    @JsonbProperty("updated_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date updatedAt;

    @JsonbProperty("url")
    private URI url;

    public enum State {

        OPEN("open"), CLOSED("closed");

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
