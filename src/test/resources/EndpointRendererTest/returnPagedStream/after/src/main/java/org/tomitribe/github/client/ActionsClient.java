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
import org.tomitribe.github.model.ListSelfHostedRunnersForRepository;
import org.tomitribe.github.model.Runner;
import org.tomitribe.github.model.RunnersPage;

public interface ActionsClient {

    @GET
    @Path("/repos/{owner}/{repo}/actions/runners")
    @OperationId("actions/list-self-hosted-runners-for-repo")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#list-self-hosted-runners-for-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    @Paged(RunnersPage.class)
    Stream<Runner> listSelfHostedRunnersForRepository(final ListSelfHostedRunnersForRepository listSelfHostedRunnersForRepository);

    @GET
    @Path("/repos/{owner}/{repo}/actions/runners")
    @OperationId("actions/list-self-hosted-runners-for-repo")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#list-self-hosted-runners-for-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    @Paged(RunnersPage.class)
    Stream<Runner> listSelfHostedRunnersForRepository(@PathParam("owner") final String owner, @PathParam("repo") final String repo);
}
