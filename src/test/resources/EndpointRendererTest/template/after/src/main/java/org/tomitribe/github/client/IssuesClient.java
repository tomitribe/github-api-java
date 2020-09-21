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

import java.util.stream.Stream;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.tomitribe.github.model.CreateIssue;
import org.tomitribe.github.model.Issue;
import org.tomitribe.github.model.IssueSimple;
import org.tomitribe.github.model.ListRepositoryIssues;

public interface IssuesClient {

    @POST
    @Path("/repos/{owner}/{repo}/issues")
    @OperationId("issues/create")
    @Docs("https://developer.github.com/v3/issues/#create-an-issue")
    @EnabledForGithubApps
    @Category("issues")
    Issue createIssue(final CreateIssue createIssue);

    @GET
    @Path("/repos/{owner}/{repo}/issues")
    @OperationId("issues/list-for-repo")
    @Docs("https://developer.github.com/v3/issues/#list-repository-issues")
    @EnabledForGithubApps
    @Preview("machine-man")
    @Preview("squirrel-girl")
    @Category("issues")
    Stream<IssueSimple> listRepositoryIssues(final ListRepositoryIssues listRepositoryIssues);
}
