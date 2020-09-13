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
 * <p>Triggered when someone <a href="/v3/activity/starring/#star-a-repository">stars a repository</a>.
 *  This event is not related to <a href="/v3/activity/watching/">watching a repository</a>.
 * See <a href="/changes/2012-09-05-watcher-api/">this API blog post</a> for an explanation.</p>
 *
 * <p>The event’s actor is the <a href="/v3/users/">user</a> who starred a repository, and the
 * event’s repository is the <a href="/v3/repos/">repository</a> that was starred.</p>
 * Used by:
 * -
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("watch")
@EqualsAndHashCode(callSuper = true)
public class WatchEvent extends Event {

    /**
     * The action that was performed. Currently, can only be <code>started</code>.
     */
    @JsonbProperty("action")
    private String action;

    @JsonbProperty("repository")
    private Repository repository;

    @JsonbProperty("sender")
    private Sender sender;
}
