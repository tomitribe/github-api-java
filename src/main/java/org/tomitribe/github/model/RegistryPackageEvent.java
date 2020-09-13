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
 * <div class="alert note">
 *
 * <p>GitHub Package Registry is currently available in limited public beta. To request to join the
 * limited public beta, see the "<a href="https://github.com/features/package-registry">GitHub Package
 * Registry page</a>."</p>
 *
 * </div>
 *
 * <p>Triggered when a package version is <code>published</code> or <code>updated</code> in GitHub
 * Package Registry. See "<a
 * href="https://help.github.com/en/categories/managing-packages-with-github-package-registry">Managing
 * packages with GitHub Package Registry</a>" in the GitHub Help documentation to learn more about
 * GitHub Package Registry.</p>
 * Used by:
 * -
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("registry_package")
@EqualsAndHashCode(callSuper = true)
public class RegistryPackageEvent extends Event {

    /**
     * The action that was performed. Can be <code>published</code> or <code>updated</code>.
     */
    @JsonbProperty("action")
    private String action;

    /**
     * Information about the registry package.
     */
    @JsonbProperty("registry_package")
    private RegistryPackage registryPackage;

    /**
     * The <a href="/v3/repos/">repository</a> object.
     */
    @JsonbProperty("repository")
    private Repository repository;

    /**
     * The GitHub user who is importing the repository.
     */
    @JsonbProperty("sender")
    private Sender sender;
}
