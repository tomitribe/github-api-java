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
package org.tomitribe.github.gen.html;

import org.tomitribe.swizzle.stream.StreamBuilder;
import org.tomitribe.swizzle.stream.StreamLexer;
import org.tomitribe.util.IO;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HtmlParser {

    public static List<Endpoint> parse(final File file) throws Exception {
        final String page = IO.slurp(file);
        final StreamLexer lexer = new StreamLexer(IO.read(page));

        final String article = lexer.read("<div id=\"article-contents\"", "</article>");

        final List<Endpoint> endpoints = Stream.of(article.split("<h3 "))
                .map(s -> "<h3 " + s)
                .filter(s -> s.contains("<span class=\"bg-blue text-white rounded"))
                .map(HtmlParser::parseEndpoint)
                .collect(Collectors.toList());

        endpoints.forEach(System.out::println);

        return endpoints;
    }

    public static Endpoint parseEndpoint(final String content) {
        final Endpoint endpoint = new Endpoint();
        final Target target = new Target();

        final Function<String, String> trim = String::trim;
        final Function<String, Parameter> parseParameter = HtmlParser::parseParameter;

        final AtomicReference<Response> currentResponse = new AtomicReference<>();

        final StreamBuilder stream = StreamBuilder.create(IO.read(content))
                .watch("<h3 id=\"", "\"", endpoint::setId)
                .substream("<h3", "</h3>", h3 -> StreamBuilder.create(h3)
                        .substream("<a", "/a>", a -> StreamBuilder.create(a)
                                .watch(">", "<", endpoint::setTitle)
                                .get())
                        .get())
                .substream("<code><span class=\"bg-blue text-white rounded", "/code>", code -> StreamBuilder.create(code)
                        .watch("\">", "</span>", target::setMethod)
                        .watch("span>", "<", trim.and(target::setPath))
                        .get())
                .substream(">Parameters</a>", "</table>", params -> StreamBuilder.create(params)
                        .watch("<tr>", "</tr>", parseParameter.and(endpoint.getParameters()::add))
                        .get())
                .watch("<pre><code class=\"hljs language-shell\">", "</code>", s -> endpoint.getCodeSamples().add(new CodeSample("Shell", s)))
                .watch("<pre><code>Status:", "</code>", s1 -> {
                    final Response r = new Response(s1, null);
                    endpoint.getResponses().add(r);
                    currentResponse.set(r);
                })
                .substream("<div class=\"height-constrained-code-block\"><pre><code", "</div>", block -> StreamBuilder.create(block)
                        .watch(">", "</code>", currentResponse.get()::setContent)
                        .get())
                // ends
                ;

        try {
            stream.run();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        endpoint.setTarget(target);
        return endpoint;
    }

    private static Parameter parseParameter(final String content) {
        return new Parameter();
    }

    @FunctionalInterface
    public interface Function<T, R> {

        R apply(T t);

        default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
            Objects.requireNonNull(after);
            return (T t) -> after.apply(apply(t));
        }

        default Consumer<T> and(Consumer<R> last) {
            Objects.requireNonNull(last);
            return t -> {
                final R r = apply(t);
                last.accept(r);
            };
        }
    }
}
