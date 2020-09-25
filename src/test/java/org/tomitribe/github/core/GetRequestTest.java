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
import org.tomitribe.github.MockService;
import org.tomitribe.github.Resources;
import org.tomitribe.github.client.Category;
import org.tomitribe.github.client.Docs;
import org.tomitribe.github.client.EnabledForGithubApps;
import org.tomitribe.github.client.OperationId;
import org.tomitribe.github.client.Preview;
import org.tomitribe.github.model.PullRequest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.tomitribe.util.Join.join;


public class GetRequestTest {

    private static URI uri;

    @BeforeClass
    public static void before() throws Exception {
        uri = MockGithub.run();
    }

    @Test
    public void getWithAnnotatedParameters() throws Exception {
        final PullsClient pullsClient = getPullsClient();

        final PullRequest pullRequest = pullsClient.getPullRequest("jakartaee", "specifications", 263);

        assertEquals(new Long(263), pullRequest.getNumber());
        assertEquals("Jakarta Security 2.0", pullRequest.getTitle());
    }

    @Test
    public void getWithAnnotatedFields() throws Exception {
        final PullsClient pullsClient = getPullsClient();

        final GetPullRequest getPullRequest = GetPullRequest.builder()
                .owner("jakartaee")
                .repo("specifications")
                .pullNumber(263)
                .build();
        final PullRequest pullRequest = pullsClient.getPullRequest(getPullRequest);

        assertEquals(new Long(263), pullRequest.getNumber());
        assertEquals("Jakarta Security 2.0", pullRequest.getTitle());
    }

    private PullsClient getPullsClient() {
        final Client client = ClientBuilder.newClient()
                .register(new MessageLogger.RequestFilter())
                .register(new MessageLogger.ResponseFilter());

        final Api api = Api.builder()
                .api(uri)
                .client(client)
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
        @GET
        @Path("/repos/{owner}/{repo}/pulls/{pull_number}")
        @OperationId("pulls/get")
        @Docs("https://developer.github.com/v3/pulls/#get-a-pull-request")
        @EnabledForGithubApps
        @Preview("sailor-v")
        @Category("pulls")
        PullRequest getPullRequest(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("pull_number") final int pullNumber);

        @GET
        @Path("/repos/{owner}/{repo}/pulls/{pull_number}")
        @OperationId("pulls/get")
        @Docs("https://developer.github.com/v3/pulls/#get-a-pull-request")
        @EnabledForGithubApps
        @Preview("sailor-v")
        @Category("pulls")
        PullRequest getPullRequest(final GetPullRequest getPullRequest);
    }

    @Path("/")
    public static class MockGithub {

        @Context
        private HttpServletRequest request;

        @GET
        @Path("repos/{owner}/{repo}/pulls/{pull_number}")
        public Response createPullRequest(@PathParam("owner") final String owner,
                                          @PathParam("repo") final String repo,
                                          @PathParam("pull_number") final int pullNumber) throws IOException {

            assertEquals("jakartaee", owner);
            assertEquals("specifications", repo);
            assertEquals(263, pullNumber);

            final List<String> accepts = Arrays.asList(request.getHeader("accept").split(" *, *"));
            accepts.sort(String::compareTo);

            assertEquals("application/json\n" +
                    "application/vnd.github.sailor-v-preview+json", join("\n", accepts));

            final String response = Resources.read(GetRequestTest.class, "response1.json");
            return Response.ok(response, MediaType.APPLICATION_JSON_TYPE).build();
        }

        public static URI run() throws Exception {
            return MockService.run(MockGithub.class);
        }
    }

}