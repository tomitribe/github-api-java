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
package org.tomitribe.github.core;

import lombok.Builder;
import lombok.Data;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tomitribe.github.JsonAsserts;
import org.tomitribe.github.MockService;
import org.tomitribe.github.Resources;
import org.tomitribe.github.client.Category;
import org.tomitribe.github.client.Docs;
import org.tomitribe.github.client.EnabledForGithubApps;
import org.tomitribe.github.client.OperationId;
import org.tomitribe.github.client.Preview;
import org.tomitribe.github.model.CreatePullRequest;
import org.tomitribe.github.model.PullRequest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.URI;

import static org.junit.Assert.assertEquals;


public class PatchRequestTest {

    private static URI uri;

    @BeforeClass
    public static void before() throws Exception {
        uri = MockGithub.run();
    }

    @Test
    public void test() throws Exception {
        final PullsClient pullsClient = getPullsClient();

        final CreatePullRequest createPullRequest = CreatePullRequest.builder()
                .owner("colors")
                .repo("orange")
                .title("This is awesome")
                .head("fancyorg:critical-fixes")
                .base("master")
                .body("Let's get this done!")
                .maintainerCanModify(true)
                .draft(true).build();

        final PullRequest pullRequest = pullsClient.createPullRequest(createPullRequest);

        assertEquals(new Long(2), pullRequest.getNumber());
        assertEquals("Update the README with new information.", pullRequest.getTitle());
    }

    private PullsClient getPullsClient() {
        final Client client = ClientBuilder.newClient()
                .register(new MessageLogger.RequestFilter())
                .register(new MessageLogger.ResponseFilter());

        final Api api = Api.builder()
                .api(uri)
                .client(client)
                .handler(builder -> builder.header("authorization", "token 23456789dfghjklkjhgfdsdfghuiytrewertyui"))
                .build();
        return (PullsClient) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{PullsClient.class}, new ClientHandler(api));
    }

    @Data
    @Builder(builderClassName = "Builder")
    public static class GetPullRequest {
        @PathParam("owner")
        final String owner;
        @PathParam("repo")
        final String repo;
        @PathParam("pull_number")
        final int pullNumber;
    }

    public interface PullsClient {
        @PATCH
        @Path("/repos/{owner}/{repo}/pulls")
        @OperationId("pulls/create")
        @Docs("https://developer.github.com/v3/pulls/#create-a-pull-request")
        @EnabledForGithubApps
        @Preview("sailor-v")
        @Category("pulls")
        PullRequest createPullRequest(final CreatePullRequest createPullRequest);
    }

    @Path("/")
    public static class MockGithub {

        @Context
        private HttpServletRequest request;

        @PATCH
        @Path("repos/{owner}/{name}/pulls")
        public Response createPullRequest(@HeaderParam("authorization") final String authorization,
                                          @HeaderParam("accept") final String accept,
                                          String body) throws IOException {

            RequestAsserts.from(request)
                    .assertAccepts("application/json\napplication/vnd.github.sailor-v-preview+json");

            // Assert the client sends the authorization header github will need
            if (!authorization.contains("token")) throw new WebApplicationException(401);
            if (!authorization.contains("23456789dfghjklkjhgfdsdfghuiytrewertyui")) throw new WebApplicationException(401);

            JsonAsserts.assertJsonb("{\n" +
                    "  \"maintainer_can_modify\":true,\n" +
                    "  \"base\":\"master\",\n" +
                    "  \"body\":\"Let's get this done!\",\n" +
                    "  \"draft\":true,\n" +
                    "  \"head\":\"fancyorg:critical-fixes\",\n" +
                    "  \"title\":\"This is awesome\"\n" +
                    "}", body);

            final String response = Resources.read(PatchRequestTest.class, "response1.json");
            return Response.ok(response, MediaType.APPLICATION_JSON_TYPE).build();
        }

        public static URI run() throws Exception {
            return MockService.run(MockGithub.class);
        }
    }
}