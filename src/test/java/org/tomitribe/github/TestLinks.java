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
package org.tomitribe.github;

import org.tomitribe.github.core.Link;

import javax.ws.rs.WebApplicationException;
import java.net.URI;

public class TestLinks {
    private TestLinks() {
    }

    public static Link getLink(final URI uri, final String format, final int page, final int last) {
        final Link.Builder link = Link.builder();
        final int first = 1;

        link.prev(format, uri, page - 1);
        link.next(format, uri, page + 1);
        link.first(format, uri, first);
        link.last(format, uri, last);

        if (page < first || page > last) {
            throw new WebApplicationException(400);
        } else if (first == page) {
            link.prev(null).first(null);
        } else if (last == page) {
            link.next(null).last(null);
        }

        return link.build();
    }
}
