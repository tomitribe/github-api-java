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
 * <p>Triggered when a successful, cancelled, or failed repository import finishes for a GitHub
 * organization or a personal repository. To receive this event for a personal repository, you must
 * create an empty repository prior to the import. This event can be triggered using either the <a
 * href="https://help.github.com/articles/importing-a-repository-with-github-importer/">GitHub
 * Importer</a> or the <a href="/v3/migrations/source_imports/">Source imports API</a>.</p>
 * Used by:
 * -
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("repository_import")
@EqualsAndHashCode(callSuper = true)
public class RepositoryImportEvent extends Event {

    /**
     * The information about the organization where the imported repository will live.
     */
    @JsonbProperty("organization")
    private Organization organization;

    /**
     * The <a href="/v3/repos/">repository</a> you are importing.
     */
    @JsonbProperty("repository")
    private Repository repository;

    /**
     * The GitHub user who is importing the repository.
     */
    @JsonbProperty("sender")
    private Sender sender;

    /**
     * The final state of the import. This can be one of <code>success</code>, <code>cancelled</code>, or
     * <code>failure</code>.
     */
    @JsonbProperty("status")
    private String status;
}
