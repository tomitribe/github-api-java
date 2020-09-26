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
package org.tomitribe.github.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.tomitribe.github.model.RepoSearchResultItem;
import org.tomitribe.github.model.RepoSearchResultItemsPage;
import org.tomitribe.github.model.SearchRepositories;

public interface SearchClient {

    @GET
    @Path("/search/repositories")
    @OperationId("search/repos")
    @Docs("https://developer.github.com/v3/search/#search-repositories")
    @EnabledForGithubApps
    @Preview("mercy")
    @Category("search")
    @Paged(RepoSearchResultItemsPage.class)
    Stream<RepoSearchResultItem> searchRepositories(final SearchRepositories searchRepositories);
}
