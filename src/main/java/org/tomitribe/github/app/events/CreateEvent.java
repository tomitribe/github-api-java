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

 * <p>Represents a created branch or tag.</p>
 * 
 * <div class="alert note">
 * 
 * <p><strong>Note:</strong> You will not receive a webhook for this event when you push more than
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
@GithubEvent("create")
public class CreateEvent {

/**
 * The <a href="/v3/git/refs/"><code>git ref</code></a>.
 */
    @JsonbProperty("ref")
    private String ref;

/**
 * The object that was created. Can be either <code>branch</code> or <code>tag</code>.
 */
    @JsonbProperty("ref_type")
    private String refType;

/**
 * The name of the repository's default branch (usually <code>master</code>).
 */
    @JsonbProperty("master_branch")
    private String masterBranch;

/**
 * The repository's current description.
 */
    @JsonbProperty("description")
    private String description;

    @JsonbProperty("pusher_type")
    private String pusherType;

    @JsonbProperty("repository")
    private Repository repository;

    @JsonbProperty("sender")
    private Sender sender;

}
