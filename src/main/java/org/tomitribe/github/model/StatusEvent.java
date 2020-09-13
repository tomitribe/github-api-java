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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

/**
 * <p>Triggered when the status of a Git commit changes.</p>
 *
 * <p>Events of this type are not visible in timelines. These events are only used to trigger
 * hooks.</p>
 * Used by:
 * -
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("status")
@EqualsAndHashCode(callSuper = true)
public class StatusEvent extends Event {

    /**
     * An array of branch objects containing the status' SHA. Each branch contains the given SHA, but the
     * SHA may or may not be the head of the branch. The array includes a maximum of 10 branches.
     */
    @JsonbProperty("branches")
    private List<Branch> branches;

    @JsonbProperty("commit")
    private Commit commit;

    @JsonbProperty("context")
    private String context;

    @JsonbProperty("created_at")
    private String createdAt;

    /**
     * The optional human-readable description added to the status.
     */
    @JsonbProperty("description")
    private String description;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("repository")
    private Repository repository;

    @JsonbProperty("sender")
    private Sender sender;

    /**
     * The Commit SHA.
     */
    @JsonbProperty("sha")
    private String sha;

    /**
     * The new state. Can be <code>pending</code>, <code>success</code>, <code>failure</code>, or
     * <code>error</code>.
     */
    @JsonbProperty("state")
    private String state;

    /**
     * The optional link added to the status.
     */
    @JsonbProperty("target_url")
    private String targetUrl;

    @JsonbProperty("updated_at")
    private String updatedAt;
}
