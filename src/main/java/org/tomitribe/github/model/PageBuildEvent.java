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
 * <p>Represents an attempted build of a GitHub Pages site, whether successful or not.</p>
 *
 * <p>Triggered on push to a GitHub Pages enabled branch (<code>gh-pages</code> for project pages,
 * <code>master</code> for user and organization pages).</p>
 *
 * <p>Events of this type are not visible in timelines. These events are only used to trigger
 * hooks.</p>
 * Used by:
 * -
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("page_build")
@EqualsAndHashCode(callSuper = true)
public class PageBuildEvent extends Event {

    /**
     * The <a href="/v3/repos/pages/#list-pages-builds">page build</a> itself.
     */
    @JsonbProperty("build")
    private Build build;

    @JsonbProperty("repository")
    private Repository repository;

    @JsonbProperty("sender")
    private Sender sender;
}
