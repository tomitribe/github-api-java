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
import org.junit.Before;
import org.junit.Test;
import org.tomitribe.github.core.Request;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import java.net.URI;

import static org.junit.Assert.assertEquals;

public class RequestAnnotationsTest {

    private Request<?> request;

    @Before
    public void before() {
        final Orange orange = Orange.builder()
                .base("orange")
                .direction(Orange.Direction.asc)
                .head("cabeza")
                .owner("tomitribe")
                .repo("orange")
                .sort(Orange.Sort.long_running)
                .state(Orange.State.closed)
                .link("http://foo.example.com/")
                .draft(true)
                .build();

        request = Request.from("/repos/{owner}/{repo}/pulls", orange);
    }

    @Test
    public void getPath() {
        assertEquals("/repos/{owner}/{repo}/pulls", request.getPath());
    }

    @Test
    public void getURI() {
        final URI uri = request.getURI();
        assertEquals("/repos/tomitribe/orange/pulls?base=orange&direction=asc&head=cabeza&sort=long-running&state=closed", uri.toASCIIString());
    }

    @Test
    public void getQueryParams() {
        final String actual = request.getQueryParams().entrySet().stream()
                .map(entry -> String.format("Query{name='%s', value='%s'}", entry.getKey(), entry.getValue()))
                .reduce((s, s2) -> s + "\n" + s2)
                .get();

        assertEquals("Query{name='base', value='orange'}\n" +
                "Query{name='direction', value='asc'}\n" +
                "Query{name='head', value='cabeza'}\n" +
                "Query{name='sort', value='long-running'}\n" +
                "Query{name='state', value='closed'}", actual);
    }

    @Test
    public void getHeaderParams() {
        final String actual = request.getHeaderParams().entrySet().stream()
                .map(entry -> String.format("Header{name='%s', value='%s'}", entry.getKey(), entry.getValue()))
                .reduce((s, s2) -> s + "\n" + s2)
                .get();

        assertEquals("Header{name='link', value='http://foo.example.com/'}", actual);
    }

    @Test
    public void getPathParams() {
        final String actual = request.getPathParams().entrySet().stream()
                .map(entry -> String.format("Path{name='%s', value='%s'}", entry.getKey(), entry.getValue()))
                .reduce((s, s2) -> s + "\n" + s2)
                .get();

        assertEquals("Path{name='owner', value='tomitribe'}\n" +
                "Path{name='repo', value='orange'}", actual);
    }

    @Test
    public void getBody() {

        assertEquals("{\n" +
                "  \"draft\":true\n" +
                "}", request.getBody());
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Orange {

        @JsonbTransient
        @PathParam("owner")
        private String owner;

        @JsonbTransient
        @PathParam("repo")
        private String repo;

        @JsonbTransient
        @QueryParam("state")
        private State state;

        @JsonbTransient
        @QueryParam("head")
        private String head;

        @JsonbTransient
        @QueryParam("base")
        private String base;

        @JsonbTransient
        @QueryParam("sort")
        private Sort sort;

        @JsonbTransient
        @QueryParam("direction")
        private Direction direction;

        @JsonbTransient
        @HeaderParam("link")
        private String link;

        @JsonbProperty("draft")
        private Boolean draft;

        public enum State {
            open, closed, all;
        }

        public enum Sort {
            created("created"),
            updated("updated"),
            popularity("popularity"),
            long_running("long-running");

            private final String name;

            Sort(final String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return name;
            }
        }

        public enum Direction {
            asc, desc
        }
    }

}
