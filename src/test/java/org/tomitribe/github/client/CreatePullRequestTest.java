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
import org.tomitribe.github.JsonAsserts;
import org.tomitribe.github.MockService;
import org.tomitribe.github.Resources;
import org.tomitribe.github.model.PullRequest;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;

import static org.junit.Assert.assertEquals;


public class CreatePullRequestTest {

    private static URI uri;

    @BeforeClass
    public static void before() throws Exception {
        uri = MockGithub.run();
    }

    @Test
    public void test() throws Exception {

        final GithubClient githubClient = GithubClient.builder()
                .oauthToken("23456789dfghjklkjhgfdsdfghuiytrewertyui")
                .api(uri)
                .build();

        final PullRequest pullRequest = githubClient.createPullRequest(
                "colors",
                "orange",
                "This is awesome",
                "fancyorg:critical-fixes",
                "master",
                "Let's get this done!",
                true,
                true
        );

        assertEquals(new Long(2), pullRequest.getNumber());
        assertEquals("Update the README with new information.", pullRequest.getTitle());
    }


    @Path("/")
    public static class MockGithub {

        @POST
        @Path("repos/{owner}/{name}/pulls")
        public Response createPullRequest(@HeaderParam("authorization") final String authorization,
                                          @HeaderParam("accept") final String accept,
                                          String body) throws IOException {

            // Assert the client sends the authorization header github will need
            if (!authorization.contains("token")) throw new WebApplicationException(401);
            if (!authorization.contains("23456789dfghjklkjhgfdsdfghuiytrewertyui")) throw new WebApplicationException(401);
            // Assert the client sends the accept header github will need to allow the preview api call
            if (!accept.contains("application/vnd.github.shadow-cat-preview+json")) throw new WebApplicationException(400);

            JsonAsserts.assertJsonb("{\n" +
                    "  \"maintainer_can_modify\":true,\n" +
                    "  \"base\":\"master\",\n" +
                    "  \"body\":\"Let's get this done!\",\n" +
                    "  \"draft\":true,\n" +
                    "  \"head\":\"fancyorg:critical-fixes\",\n" +
                    "  \"title\":\"This is awesome\"\n" +
                    "}", body);

            final String response = Resources.read(CreatePullRequestTest.class, "create-pull-request-response.json");
            return Response.ok(response, MediaType.APPLICATION_JSON_TYPE).build();
        }

        public static URI run() throws Exception {
            return MockService.run(MockGithub.class);
        }
    }
}