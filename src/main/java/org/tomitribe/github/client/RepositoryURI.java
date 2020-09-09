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

import lombok.Builder;
import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Builder(toBuilder = true, builderClassName = "Builder")
public class RepositoryURI {

    private final String scheme;
    private final String host;
    private final String owner;
    private final String name;

    public String getFullName() {
        return owner + "/" + name;
    }

    @Override
    public String toString() {
        if (scheme == null) {
            return String.format("%s/%s", owner, name);
        } else if (scheme.equals("git")) {
            return String.format("git@%s:%s/%s.git", host, owner, name);
        } else if (scheme.equals("http") || scheme.equals("https")) {
            return String.format("%s://%s/%s/%s.git", scheme, host, owner, name);
        } else {
            throw new UnsupportedOperationException("Uknown scheme: " + scheme);
        }
    }

    public static RepositoryURI parse(final String uri) {
        if (uri == null) throw new IllegalArgumentException("uri is null");

        final Pattern pattern;

        if (uri.startsWith("http")) {
            pattern = Pattern.compile("(https?)://([^/]+)/([^/]+)/([^/]+).git");
        } else if (uri.startsWith("git")) {
            pattern = Pattern.compile("(git)@(.+?):([^:/]+?)/([^/]+).git");
        } else if (uri.startsWith("/")) {
            pattern = Pattern.compile("(.*)/([^/]+)");
            final Matcher matcher = pattern.matcher(uri);

            if (!matcher.matches()) {
                throw new IllegalArgumentException(String.format("Invalid git file path: '%s'", uri));
            }

            if (matcher.groupCount() != 2) {
                throw new IllegalArgumentException(String.format("Invalid git file path: '%s'", uri));
            }

            return builder()
                    .owner(matcher.group(1))
                    .name(matcher.group(2))
                    .build();
        } else {
            throw new UnsupportedOperationException("Unknown url scheme: " + uri);
        }

        final Matcher matcher = pattern.matcher(uri);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("Invalid git url: '%s'", uri));
        }

        if (matcher.groupCount() != 4) {
            throw new IllegalArgumentException(String.format("Invalid git url: '%s'", uri));
        }

        return builder()
                .scheme(matcher.group(1))
                .host(matcher.group(2))
                .owner(matcher.group(3))
                .name(matcher.group(4))
                .build();

    }
}
