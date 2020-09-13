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
 * <p>Triggered when an organization blocks or unblocks a user. These events are only used to trigger
 * organization hooks.</p>
 * Used by:
 * -
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("org_block")
@EqualsAndHashCode(callSuper = true)
public class OrgBlockEvent extends Event {

    /**
     * The action performed. Can be <code>blocked</code> or <code>unblocked</code>.
     */
    @JsonbProperty("action")
    private String action;

    /**
     * Information about the user that was blocked or unblocked.
     */
    @JsonbProperty("blocked_user")
    private BlockedUser blockedUser;

    /**
     * Information about the organization that blocked or unblocked the user.
     */
    @JsonbProperty("organization")
    private Organization organization;

    /**
     * Information about the user who sent the blocking/unblocking request on behalf of the organization.
     */
    @JsonbProperty("sender")
    private Sender sender;
}
