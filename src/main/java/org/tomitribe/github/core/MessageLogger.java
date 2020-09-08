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

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import java.io.IOException;

public class MessageLogger {

    @Priority(Priorities.USER)
    public static class RequestFilter implements ClientRequestFilter {

        @Override
        public void filter(final ClientRequestContext request) throws IOException {
            final String queryString = request.getUri().getQuery() == null ? "" : "?" + request.getUri().getQuery();
            System.out.println("> " + request.getMethod() + " " + request.getUri().toASCIIString() + queryString + " HTTP 1.1");
            StringBuilder headers = new StringBuilder();
            request.getHeaders().forEach((k, v) -> {
                headers.append("> " + k + ": " + v.stream().findFirst().get() + "\n");
            });
            System.out.print(headers);
            System.out.print(">\n");
        }
    }

    @Priority(Priorities.USER)
    public static class ResponseFilter implements ClientResponseFilter {

        @Override
        public void filter(ClientRequestContext request, ClientResponseContext response) throws IOException {
            System.out.println("< HTTP 1.1 " + response.getStatus() + " " + response.getStatusInfo().getReasonPhrase());
            StringBuilder headers = new StringBuilder();
            response.getHeaders().forEach((k, v) -> {
                headers.append("< " + k + ": " + v.stream().findFirst().get() + "\n");
            });
            System.out.println(headers);
        }
    }
}
