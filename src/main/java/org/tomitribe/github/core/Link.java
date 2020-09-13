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

import lombok.Data;
import org.tomitribe.util.Join;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Github paging API involves a header called `Link` that looks roughly as follows:
 *
 * Link: &lt;https://api.github.com/repositories?q=filename%3Apom.xml&since=270>; rel="next", <https://api.github.com/repositories{?since}>; rel="first"
 * Link: &lt;https://api.github.com/repositories?q=filename%3Apom.xml&since=66050>; rel="next", <https://api.github.com/repositories{?since}>; rel="first"
 *
 * Link: &lt;https://api.github.com/repositories{?since}>; rel="first"
 */
@Data
public class Link {

    private final URI next;
    private final URI prev;
    private final URI first;
    private final URI last;

    public Link(final URI next, final URI prev, final URI first, final URI last) {
        this.next = next;
        this.prev = prev;
        this.first = first;
        this.last = last;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean hasNext() {
        return getNext() != null;
    }

    public URI getNext() {
        return next;
    }

    public URI getFirst() {
        return first;
    }

    public URI getLast() {
        return last;
    }

    public URI getPrev() {
        return prev;
    }

    public String asHeader() {
        final List<String> parts = new ArrayList<>();
        final String format = "<%s>; rel=\"%s\"";

        if (prev != null) parts.add(String.format(format, prev, "prev"));
        if (next != null) parts.add(String.format(format, next, "next"));
        if (last != null) parts.add(String.format(format, last, "last"));
        if (first != null) parts.add(String.format(format, first, "first"));

        return Join.join(", ", parts);
    }

    public static Link parse(final String headerValue) {
        if (headerValue == null) return Link.builder().build();
        final String[] links = headerValue.split(" *, *");

        return new Link(
                getLink(links, "next"),
                getLink(links, "prev"),
                getLink(links, "first"),
                getLink(links, "last")
        );
    }

    public static URI getLink(final String[] links, final String link) {
        final String type = "rel=\"" + link + "\"";
        return (URI) Stream.of(links)
                .filter(s -> s.contains(type))
                .map(s -> s.replaceAll(".*<|>.*", ""))
                .map(s -> s.replace("{?since}", ""))
                .map(URI::create)
                .findFirst()
                .orElse(null);
    }

    public static class Builder {
        private URI next;
        private URI prev;
        private URI first;
        private URI last;

        private Builder() {
        }

        public Builder next(final URI next) {
            this.next = next;
            return this;
        }

        public Builder prev(final URI prev) {
            this.prev = prev;
            return this;
        }

        public Builder first(final URI first) {
            this.first = first;
            return this;
        }

        public Builder last(final URI last) {
            this.last = last;
            return this;
        }

        public Builder next(final String format, final Object... args) {
            this.next = URI.create(String.format(format, args));
            return this;
        }

        public Builder first(final String format, final Object... args) {
            this.first = URI.create(String.format(format, args));
            return this;
        }

        public Builder prev(final String format, final Object... args) {
            this.prev = URI.create(String.format(format, args));
            return this;
        }

        public Builder last(final String format, final Object... args) {
            this.last = URI.create(String.format(format, args));
            return this;
        }

        public Link build() {
            return new Link(next, prev, first, last);
        }

        @Override
        public String toString() {
            return "Link.Builder(next=" + this.next + ", prev=" + this.prev + ", first=" + this.first + ", last=" + this.last + ")";
        }
    }
}
