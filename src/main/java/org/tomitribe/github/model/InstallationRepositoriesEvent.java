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
 * <p>Triggered when a repository is <code>added</code> or <code>removed</code> from an
 * installation.</p>
 * Used by:
 * -
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("installation_repositories")
@EqualsAndHashCode(callSuper = true)
public class InstallationRepositoriesEvent extends Event {

    /**
     * The action that was performed. Can be either <code>added</code> or <code>removed</code>.
     */
    @JsonbProperty("action")
    private String action;

    /**
     * The installation itself.
     */
    @JsonbProperty("installation")
    private Installation installation;

    /**
     * An array of repository objects, which were added to the installation.
     */
    @JsonbProperty("repositories_added")
    private List<RepositoriesAdded> repositoriesAdded;

    /**
     * An array of repository objects, which were removed from the installation.
     */
    @JsonbProperty("repositories_removed")
    private String[] repositoriesRemoved;

    /**
     * The choice of repositories the installation is on. Can be either <code>selected</code> or
     * <code>all</code>.
     */
    @JsonbProperty("repository_selection")
    private String repositorySelection;

    @JsonbProperty("sender")
    private Sender sender;
}
