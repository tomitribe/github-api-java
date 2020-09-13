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

/**
 * <p>Triggered when a <a href="/v3/teams/#add-or-update-team-repository">repository is added to a
 * team</a>.</p>
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
@GithubEvent("team_add")
@EqualsAndHashCode(callSuper = true)
public class TeamAddEvent extends Event {

    @JsonbProperty("organization")
    private Organization organization;

    /**
     * The <a href="/v3/repos/">repository</a> that was added to this team.
     */
    @JsonbProperty("repository")
    private Repository repository;

    @JsonbProperty("sender")
    private Sender sender;

    /**
     * The <a href="/v3/teams/">team</a> that was modified.  <strong>Note:</strong> Older events may not
     * include this in the payload.
     */
    @JsonbProperty("team")
    private Team team;
}
