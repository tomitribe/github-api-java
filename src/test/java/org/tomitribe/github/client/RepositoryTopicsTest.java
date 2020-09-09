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
import org.tomitribe.github.model.Topics;
import org.tomitribe.github.core.JsonMarshalling;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class RepositoryTopicsTest {

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

        {
            final List<String> expected = Arrays.asList("red", "green", "blue");
            final List<String> actual = githubClient.getTopics("foo", "bar");
            assertEquals(expected, actual);
        }

        {
            final List<String> expected = Arrays.asList("circle", "triangle");
            final List<String> actual = githubClient.setTopics("foo", "bar", expected);
            assertEquals(expected, actual);
        }

        {
            final List<String> expected = Arrays.asList("red", "green", "blue", "circle", "square");
            final List<String> actual = githubClient.addTopics("foo", "bar", "circle", "square");
            assertEquals(expected, actual);
        }
    }


    @Path("/")
    public static class MockGithub {

        @GET
        @Path("repos/{owner}/{name}/topics")
        public Response getTopics(@PathParam("owner") String owner, @PathParam("owner") String name, @HeaderParam("accept") final String accept) {
            if (!accept.contains("application/vnd.github.mercy-preview+json")) throw new WebApplicationException(400);
            final Topics jsonObject = new Topics(Arrays.asList("red", "green", "blue"));
            final String json = JsonMarshalling.toFormattedJson(jsonObject);
            return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
        }

        @PUT
        @Path("repos/{owner}/{name}/topics")
        public Response putTopics(@PathParam("owner") String owner,
                                  @PathParam("owner") String name,
                                  @HeaderParam("authorization") final String authorization,
                                  @HeaderParam("accept") final String accept,
                                  String body) {

            // Assert the client sends the authorization header github will need
            if (!authorization.contains("token")) throw new WebApplicationException(401);

            // Assert the client sends the accept header github will need to allow the preview api call
            if (!accept.contains("application/vnd.github.mercy-preview+json")) throw new WebApplicationException(400);

            return Response.ok(body, MediaType.APPLICATION_JSON_TYPE).build();
        }

        public static URI run() throws Exception {
            return MockService.run(MockGithub.class);
        }

    }
}