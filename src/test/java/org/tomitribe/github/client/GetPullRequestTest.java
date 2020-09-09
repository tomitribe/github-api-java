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

import org.junit.BeforeClass;
import org.junit.Test;
import org.tomitribe.github.MockService;
import org.tomitribe.github.Resources;
import org.tomitribe.github.model.PullRequest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;

import static org.junit.Assert.assertEquals;


public class GetPullRequestTest {

    private static URI uri;

    @BeforeClass
    public static void before() throws Exception {
        uri = MockGithub.run();
    }

    @Test
    public void test() throws Exception {

        final GithubClient githubClient = GithubClient.builder()
                .api(uri)
                .build();

        final PullRequest pullRequest = githubClient.getPullRequest("jakartaee", "specifications", 263);

        assertEquals(new Long(263), pullRequest.getNumber());
        assertEquals("Jakarta Security 2.0", pullRequest.getTitle());
    }


    @Path("/")
    public static class MockGithub {

        @GET
        @Path("repos/{owner}/{repo}/pulls/{pull_number}")
        public Response createPullRequest(@PathParam("owner") final String owner,
                                          @PathParam("repo") final String repo,
                                          @PathParam("pull_number") final int pullNumber) throws IOException {

            assertEquals("jakartaee", owner);
            assertEquals("specifications", repo);
            assertEquals(263, pullNumber);

            final String response = Resources.read(GetPullRequestTest.class, "response1.json");
            return Response.ok(response, MediaType.APPLICATION_JSON_TYPE).build();
        }

        public static URI run() throws Exception {
            return MockService.run(MockGithub.class);
        }
    }
}