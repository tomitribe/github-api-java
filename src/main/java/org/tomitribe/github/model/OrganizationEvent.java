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
 * <p>Triggered when an organization is deleted and renamed, and when a user is added, removed, or
 * invited to an organization.</p>
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
@GithubEvent("organization")
@EqualsAndHashCode(callSuper = true)
public class OrganizationEvent extends Event {

    /**
     * The action that was performed. Can be one of: <code>deleted</code>, <code>renamed</code>,
     * <code>member_added</code>, <code>member_removed</code>, or <code>member_invited</code>.
     */
    @JsonbProperty("action")
    private String action;

    /**
     * The membership between the user and the organization.  Not present when the action is
     * <code>member_invited</code>.
     */
    @JsonbProperty("membership")
    private Membership membership;

    /**
     * The organization in question.
     */
    @JsonbProperty("organization")
    private Organization organization;

    @JsonbProperty("sender")
    private Sender sender;
}
