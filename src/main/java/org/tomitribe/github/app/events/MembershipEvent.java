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

 * <p>Triggered when a user is <code>added</code> or <code>removed</code> from a team.</p>
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
public class MembershipEvent {

/**
 * The action that was performed. Can be <code>added</code> or <code>removed</code>.
 */
    @JsonbProperty("action")
    private String action;

/**
 * The scope of the membership. Currently, can only be <code>team</code>.
 */
    @JsonbProperty("scope")
    private String scope;

/**
 * The <a href="/v3/users/">user</a> that was added or removed.
 */
    @JsonbProperty("member")
    private Member member;

    @JsonbProperty("sender")
    private Sender sender;

/**
 * The <a href="/v3/teams/">team</a> for the membership.
 */
    @JsonbProperty("team")
    private Team team;

    @JsonbProperty("organization")
    private Organization organization;

}
