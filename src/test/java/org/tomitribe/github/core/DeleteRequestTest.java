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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tomitribe.github.MockService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.URI;

import static org.junit.Assert.assertEquals;


public class DeleteRequestTest {

    private static URI uri;

    @BeforeClass
    public static void before() throws Exception {
        uri = MockGithub.run();
    }

    @Test
    public void returnVoid() throws Exception {
        final DeleteClient client = getClient();
        try {
            client.returnVoid("red", "green", 418);
        } catch (final ClientErrorException e) {
            assertEquals(418, e.getResponse().getStatus());
        }

        client.returnVoid("red", "green", null);
    }

    @Test
    public void returnJson() throws Exception {
        final DeleteClient client = getClient();
        try {
            client.returnJson("red", "green", 489);
        } catch (final ClientErrorException e) {
            assertEquals(489, e.getResponse().getStatus());
        }

        final Message message = client.returnJson("red", "green", null);
        assertEquals("here you go", message.getText());
    }

    private DeleteClient getClient() {
        final Client client = ClientBuilder.newClient()
                .register(new MessageLogger.RequestFilter())
                .register(new MessageLogger.ResponseFilter());

        final Api api = Api.builder()
                .api(uri)
                .client(client)
                .handler(builder -> builder.header("authorization", "token 23456789dfghjklkjhgfdsdfghuiytrewertyui"))
                .build();
        return (DeleteClient) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{DeleteClient.class}, new ClientHandler(api));
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

    public interface DeleteClient {
        @DELETE
        @Path("repos/{owner}/{name}/returnVoid")
        void returnVoid(@PathParam("owner") final String owner, @PathParam("name") final String name, @QueryParam("code") final Integer code);

        @DELETE
        @Path("repos/{owner}/{name}/returnJson")
        Message returnJson(@PathParam("owner") final String owner, @PathParam("name") final String name, @QueryParam("code") final Integer code);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Message {
        private String text;
    }

    @Path("/")
    public static class MockGithub {

        @Context
        private HttpServletRequest request;

        @DELETE
        @Path("repos/{owner}/{name}/returnVoid")
        public void returnVoid(@PathParam("owner") final String owner,
                               @PathParam("name") final String name,
                               @QueryParam("code") Integer code) throws IOException {
            if (code != null) throw new WebApplicationException(code);
        }

        @DELETE
        @Path("repos/{owner}/{name}/returnJson")
        public Message returnJson(@PathParam("owner") final String owner,
                                  @PathParam("name") final String name,
                                  @QueryParam("code") final Integer code) throws IOException {
            if (code != null) throw new WebApplicationException(code);
            return new Message("here you go");
        }

        public static URI run() throws Exception {
            return MockService.run(MockGithub.class);
        }
    }
}