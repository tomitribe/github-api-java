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
 * <p>Triggered on a push to a repository branch. Branch pushes and repository tag pushes also trigger
 * webhook <a href="/webhooks/#events"><code>push</code> events</a>.</p>
 *
 * <div class="alert note">
 *
 * <p><strong>Note:</strong> You will not receive a webhook for this event when you push more than
 * three tags at once.</p>
 *
 * </div>
 *
 * <div class="alert tip">
 *
 * <p><strong>Note</strong>: The webhook payload example following the table differs significantly from
 * the Events API payload described in the table. Among other differences, the webhook payload includes
 * both <code>sender</code> and <code>pusher</code> objects. Sender and pusher are the same user who
 * initiated the <code>push</code> event, but the <code>sender</code> object contains more detail.</p>
 *
 * </div>
 * Used by:
 * -
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("push")
@EqualsAndHashCode(callSuper = true)
public class PushEvent extends Event {

    @JsonbProperty("after")
    private String after;

    @JsonbProperty("base_ref")
    private String baseRef;

    /**
     * The SHA of the most recent commit on <code>ref</code> before the push.
     */
    @JsonbProperty("before")
    private String before;

    /**
     * An array of commit objects describing the pushed commits. (The array includes a maximum of 20
     * commits. If necessary, you can use the <a href="/v3/repos/commits/">Commits API</a> to fetch
     * additional commits. This limit is applied to timeline events only and isn't applied to webhook
     * deliveries.)
     */
    @JsonbProperty("commits")
    private String[] commits;

    @JsonbProperty("compare")
    private String compare;

    @JsonbProperty("created")
    private Boolean created;

    @JsonbProperty("deleted")
    private Boolean deleted;

    @JsonbProperty("forced")
    private Boolean forced;

    @JsonbProperty("head_commit")
    private String headCommit;

    @JsonbProperty("pusher")
    private Pusher pusher;

    /**
     * The full <a href="/v3/git/refs/"><code>git ref</code></a> that was pushed. Example:
     * <code>refs/heads/master</code>.
     */
    @JsonbProperty("ref")
    private String ref;

    @JsonbProperty("repository")
    private Repository repository;

    @JsonbProperty("sender")
    private Sender sender;
}
