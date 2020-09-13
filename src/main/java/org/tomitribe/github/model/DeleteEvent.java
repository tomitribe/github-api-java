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
 * <p>Represents a <a href="/v3/git/refs/#delete-a-reference">deleted branch or tag</a>.</p>
 *
 * <div class="alert note">
 *
 * <p><strong>Note:</strong> You will not receive a webhook for this event when you delete more than
 * three tags at once.</p>
 *
 * </div>
 * Used by:
 * -
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("delete")
@EqualsAndHashCode(callSuper = true)
public class DeleteEvent extends Event {

    @JsonbProperty("pusher_type")
    private String pusherType;

    /**
     * The <a href="/v3/git/refs/"><code>git ref</code></a>.
     */
    @JsonbProperty("ref")
    private String ref;

    /**
     * The object that was deleted. Can be <code>branch</code> or <code>tag</code>.
     */
    @JsonbProperty("ref_type")
    private String refType;

    @JsonbProperty("repository")
    private Repository repository;

    @JsonbProperty("sender")
    private Sender sender;
}
