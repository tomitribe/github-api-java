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
import java.util.List;

/**
 * <p>Triggered when someone installs (<code>created</code>) , uninstalls (<code>deleted</code>), or
 * accepts new permissions (<code>new_permissions_accepted</code>) for a GitHub App. When a GitHub App
 * owner requests new permissions, the person who installed the GitHub App must accept the new
 * permissions request.</p>
 * Used by:
 * -
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("installation")
@EqualsAndHashCode(callSuper = true)
public class InstallationEvent extends Event {

    /**
     * The action that was performed. Can be one of <code>created</code>, <code>deleted</code>, or
     * <code>new_permissions_accepted</code>.
     */
    @JsonbProperty("action")
    private String action;

    /**
     * The installation itself.
     */
    @JsonbProperty("installation")
    private Installation installation;

    @JsonbProperty("repositories")
    private List<RepositoryRef> repositories;

    @JsonbProperty("sender")
    private Sender sender;
}
