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
 * <p>Triggered when the webhook that this event is configured on is deleted. This event will only
 * listen for changes to the particular hook the event is installed on. Therefore, it must be selected
 * for each hook that you'd like to recieve meta events for.</p>
 * Used by:
 * -
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("meta")
@EqualsAndHashCode(callSuper = true)
public class MetaEvent extends Event {

    /**
     * The action performed. Can be <code>deleted</code>.
     */
    @JsonbProperty("action")
    private String action;

    /**
     * The modified webhook. This will contain different keys based on the type of webhook it is:
     * repository, organization, business, app, or GitHub Marketplace.
     */
    @JsonbProperty("hook")
    private Hook hook;

    /**
     * The id of the modified webhook.
     */
    @JsonbProperty("hook_id")
    private Long hookId;

    @JsonbProperty("repository")
    private Repository repository;

    @JsonbProperty("sender")
    private Sender sender;
}
