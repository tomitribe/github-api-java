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

package org.tomitribe.github.app.events;

import javax.json.bind.annotation.JsonbProperty;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

/**

 * <p>Triggered when an organization's team is <code>created</code>, <code>deleted</code>,
 * <code>edited</code>, <code>added_to_repository</code>, or <code>removed_from_repository</code>.</p>
 * 
 * <p>Events of this type are not visible in timelines. These events are only used to trigger
 * organization hooks.</p>
 * Used by:
 * - 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamEvent {

/**
 * The action that was performed. Can be one of <code>created</code>, <code>deleted</code>,
 * <code>edited</code>, <code>added_to_repository</code>, or <code>removed_from_repository</code>.
 */
    @JsonbProperty("action")
    private String action;

/**
 * The team itself.
 */
    @JsonbProperty("team")
    private Team team;

/**
 * The repository that was added or removed from to the team's purview if the action was
 * <code>added_to_repository</code>, <code>removed_from_repository</code>, or <code>edited</code>. For
 * <code>edited</code> actions, <code>repository</code> also contains the team's new permission levels
 * for the repository.
 */
    @JsonbProperty("repository")
    private Repository repository;

    @JsonbProperty("organization")
    private Organization organization;

    @JsonbProperty("sender")
    private Sender sender;

}
