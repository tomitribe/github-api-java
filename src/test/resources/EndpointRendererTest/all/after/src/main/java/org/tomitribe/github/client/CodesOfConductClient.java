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
import javax.ws.rs.Path;
import org.tomitribe.github.model.CodeOfConduct;
import org.tomitribe.github.model.GetCodeOfConduct;
import org.tomitribe.github.model.GetCodeOfConductForRepository;

public interface CodesOfConductClient {

    @GET
    @Path("/codes_of_conduct")
    @OperationId("codes-of-conduct/get-all-codes-of-conduct")
    @Docs("https://developer.github.com/v3/codes_of_conduct/#get-all-codes-of-conduct")
    @EnabledForGithubApps
    @Preview("scarlet-witch")
    @Category("codes-of-conduct")
    Stream<CodeOfConduct> getAllCodesOfConduct();

    @GET
    @Path("/codes_of_conduct/{key}")
    @OperationId("codes-of-conduct/get-conduct-code")
    @Docs("https://developer.github.com/v3/codes_of_conduct/#get-a-code-of-conduct")
    @EnabledForGithubApps
    @Preview("scarlet-witch")
    @Category("codes-of-conduct")
    CodeOfConduct getCodeOfConduct(final GetCodeOfConduct getCodeOfConduct);

    @GET
    @Path("/codes_of_conduct/{key}")
    @OperationId("codes-of-conduct/get-conduct-code")
    @Docs("https://developer.github.com/v3/codes_of_conduct/#get-a-code-of-conduct")
    @EnabledForGithubApps
    @Preview("scarlet-witch")
    @Category("codes-of-conduct")
    CodeOfConduct getCodeOfConduct(@PathParam("key") final String key);

    @GET
    @Path("/repos/{owner}/{repo}/community/code_of_conduct")
    @OperationId("codes-of-conduct/get-for-repo")
    @Docs("https://developer.github.com/v3/codes_of_conduct/#get-the-code-of-conduct-for-a-repository")
    @EnabledForGithubApps
    @Preview("scarlet-witch")
    @Category("codes-of-conduct")
    CodeOfConduct getCodeOfConductForRepository(final GetCodeOfConductForRepository getCodeOfConductForRepository);

    @GET
    @Path("/repos/{owner}/{repo}/community/code_of_conduct")
    @OperationId("codes-of-conduct/get-for-repo")
    @Docs("https://developer.github.com/v3/codes_of_conduct/#get-the-code-of-conduct-for-a-repository")
    @EnabledForGithubApps
    @Preview("scarlet-witch")
    @Category("codes-of-conduct")
    CodeOfConduct getCodeOfConductForRepository(@PathParam("owner") final String owner, @PathParam("repo") final String repo);
}
