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

import org.junit.Test;
import org.tomitribe.github.core.Request;

import static org.junit.Assert.assertEquals;

public class RequestBuilderTest {

//    @Test
//    public void test() throws Exception {
//        final Request<?> request = Request.target("/repos/{owner}/{repo}/pulls", "tomitribe", "orange");
//
//        request.getPathParams()
//
//        assertEquals("", request.getURI().toASCIIString());
//
//    }

    @Test
    public void getPathParams() {
        final Request<?> request = Request.target("/repos/{owner}/{repo}/pulls", "tomitribe", "orange");
        final String actual = request.getPathParams().entrySet().stream()
                .map(entry -> String.format("Path{name='%s', value='%s'}", entry.getKey(), entry.getValue()))
                .reduce((s, s2) -> s + "\n" + s2)
                .get();

        assertEquals("Path{name='owner', value='tomitribe'}\n" +
                "Path{name='repo', value='orange'}", actual);
    }

    @Test
    public void getURI() {
        final Request<?> request = Request.target("/repos/{owner}/{repo}/pulls", "tomitribe", "orange");
        

        assertEquals("/repos/tomitribe/orange/pulls", request.getURI().toASCIIString());
    }

}
