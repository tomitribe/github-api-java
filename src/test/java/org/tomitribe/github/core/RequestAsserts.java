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

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.tomitribe.util.Join.join;

public class RequestAsserts {
    private final HttpServletRequest request;

    public RequestAsserts(final HttpServletRequest request) {
        this.request = request;
    }

    public RequestAsserts assertAccepts(final String expected) {
        final List<String> accepts = Arrays.asList(request.getHeader("accept").split(" *, *"));
        accepts.sort(String::compareTo);

        assertEquals(expected, join("\n", accepts));
        return this;
    }

    public static RequestAsserts from(final HttpServletRequest request) {
        return new RequestAsserts(request);
    }
}
