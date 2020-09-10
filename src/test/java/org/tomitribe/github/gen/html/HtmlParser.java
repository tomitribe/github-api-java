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

import org.tomitribe.github.core.JsonMarshalling;
import org.tomitribe.github.gen.Dir;
import org.tomitribe.github.gen.Gen;
import org.tomitribe.github.gen.Project;
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

    /**
     * Use this to regenerate the src/test/resources/gen/json files
     */
    public static void main(String[] args) {
        final Gen gen = Project.root().src().test().resources().gen();
        final Dir jsonDir = gen.json();

        final List<File> htmlFiles = gen.html().files()
                .filter(file -> file.getName().endsWith(".html"))
                .collect(Collectors.toList());

        for (final File htmlFile : htmlFiles) {
            try {
                final String jsonFile = htmlFile.getName().replace(".html", ".json");
                final List<Endpoint> endpoints = parse(htmlFile);
                final String json = JsonMarshalling.toFormattedJson(endpoints);

                IO.copy(IO.read(json), jsonDir.file(jsonFile));
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    }

    public static List<Endpoint> parse(final File file) throws IOException {
        final String group = file.getName().replace(".html", "");
        final String page = IO.slurp(file);
        final StreamLexer lexer = new StreamLexer(IO.read(page));

        final String article = lexer.read("<div id=\"article-contents\"", "</article>");

        final List<Endpoint> endpoints = Stream.of(article.split("<h3 "))
                .map(s -> "<h3 " + s)
                .filter(s -> s.contains("<span class=\"bg-blue text-white rounded"))
                .map(content -> parseEndpoint(content, group))
                .collect(Collectors.toList());

        return endpoints;
    }

    /**
     * We have already split the html into one section per endpoint, so in
     * this method we know we are dealing with a subset of the document
     * dedicated to just one Endpoint definition.
     */
    static Endpoint parseEndpoint(final String content, final String group) {
        final Endpoint endpoint = new Endpoint();
        final Target target = new Target();

        endpoint.setGroup(group);

        final Function<String, String> trim = String::trim;
        final Function<String, String> stripHtml = HtmlParser::stripHtml;

        final AtomicReference<Response> currentResponse = new AtomicReference<>();

        /*
         * A StreamBuilder will effectively decorate/wrap an InputStream with
         * very specialized FilterInputStream instances that will either watch
         * for specific content, replace specific content or even slice off
         * a specific chunk of the stream so it can be passed to something that
         * consumes an InputStream.
         *
         * Because the underlying InputStream is being wrapped several times
         * in a chain, order does matter.  Each directive will wrap the stream
         * and therefore subsequent directives will see any changes made.
         */
        final StreamBuilder stream = StreamBuilder.create(IO.read(content))

                /*
                 * Parse the Parameter Properties and remove them from the document
                 * to simplify the subsequent parsing
                 */
                .replace("<details>", "</details>", s -> {
                    parseParameterProperties(endpoint, s);
                    return "";
                })

                /*
                 * Watch for the endpoint name (example `list-organization-repositories`)
                 */
                .watch("<h3 id=\"", "\"", endpoint::setId)

                /*
                 * Watch for the endpoint title (example "List organization repositories")
                 */
                .substream("<h3", "</h3>", h3 -> StreamBuilder.create(h3)
                        .substream("<a", "/a>", a -> StreamBuilder.create(a)
                                .watch(">", "<", stripHtml.and(endpoint::setTitle))
                                .get())
                        .get())

                /*
                 * Watch for the method and path
                 */
                .substream("<code><span class=\"bg-blue text-white rounded", "/code>", code -> StreamBuilder.create(code)
                        .watch("\">", "</span>", target::setMethod)
                        .watch("span>", "<", trim.and(target::setPath))
                        .get())

                /*
                 * Watch for the parameters.  Because we removed "<details>" from the document
                 * we will not be thrown off by any tables nested inside this table.  This part
                 * will fail if the above replace("<details>"..) line is removed
                 */
                .substream(">Parameters</a>", "</table>", params -> StreamBuilder.create(params)
                        .watch("<tr>", "</tr>", s -> parseParameter(endpoint, s))
                        .get())

                /*
                 * Grab the Shell code example and save it
                 */
                .watch("<pre><code class=\"hljs language-shell\">", "</code>", shell -> {
                    final CodeSample codeSample = new CodeSample("Shell", stripHtml(shell));
                    endpoint.getCodeSamples().add(codeSample);
                })

                /*
                 * When we see a "Status: " we know the next element is a payload sample
                 * Keep the status in a Response object and be prepared to fill in the
                 * payload shortly.
                 */
                .watch("<pre><code>Status:", "</code>", status -> {
                    final Response r = new Response(status.trim(), null);
                    endpoint.getResponses().add(r);
                    currentResponse.set(r);
                })

                /*
                 * We reached the payload sample for the above Response object.
                 * Grab the current Response and set the payload.
                 */
                .substream("<div class=\"height-constrained-code-block\"><pre><code", "</div>", block -> StreamBuilder.create(block)
                        .watch(">", "</code>", stripHtml.and(currentResponse.get()::setContent))
                        .get())
                // ends
                ;

        /*
         * Now that we've built our beautiful parsing chain, we only
         * need to read from the stream to "pull" the bytes through
         * the above code.
         *
         * The run method effectively reads the stream and discards
         * the bytes.  When this completes we should have our data.
         */
        try {
            stream.run();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        endpoint.getParameters().remove(null);
        endpoint.setTarget(target);
        return endpoint;
    }

    static String stripHtml(final String content) {
        // The exact list of entities were pulled from the downloaded html files
        // put into an html document and the following table produced
        /*
        cd src/test/resources/gen/html
        cat *.html | perl -pe 's,(&[^;]+;),\n$1\n,g' | grep '^&' | sort | uniq | \
        while read n; do E=${n/&/&amp;}; echo ".replace(&quot;$E&quot;, &quot;$n&quot;)<br>"; done > entities.html
         */
        return content.replaceAll("<[^>]+>", "")
                .replace("&#x1F389;", "🎉")
                .replace("&#x1F440;", "👀")
                .replace("&#x1F44D;", "👍")
                .replace("&#x1F44E;", "👎")
                .replace("&#x1F604;", "😄")
                .replace("&#x1F615;", "😕")
                .replace("&#x1F680;", "🚀")
                .replace("&#x2019;", "’")
                .replace("&#x201C;", "“")
                .replace("&#x201D;", "”")
                .replace("&#x261D;", "☝")
                .replace("&#x2764;", "❤")
                .replace("&#xA0;", " ")
                .replace("&#xB7;", "·")
                .replace("&#xFE0F;", "️")
                .replace("&amp;", "&")
                .replace("&apos;", "'")
                .replace("&copy;", "©")
                .replace("&gt;", ">")
                .replace("&lt;", "<")
                .replace("&quot;", "\"")
                ;
    }

    private static void parseParameter(final Endpoint endpoint, final String content) {
        if (content.contains("<th>")) return;
        if (content.contains("<nested>")) {
            parseParameterProperties(endpoint, content);
        } else {
            endpoint.getParameters().add(parseParameter(content));
        }
    }

    private static Parameter parseParameter(final String content) {
        /*
         * Sample value of `content` is
         *
         *   <td><code>accept</code></td>
         *   <td class="opacity-70">string</td>
         *   <td class="opacity-70">header</td>
         *   <td class="opacity-70">
         *     <p>Setting to <code>application/vnd.github.v3+json</code> is recommended</p>
         *
         *   </td>
         */
        final String[] parts = content.trim().replaceAll("\n", " ")
                .replaceAll(" +", " ")
                .replaceAll("<td[^>]+>", "\n")
                .replaceAll("</td>", "")
                .trim()
                .split("\n");

        final String name = stripHtml(parts[0]);
        final String type = stripHtml(parts[1]);
        final String in = stripHtml(parts[2]);
        final String description = parts.length == 4 ? parts[3].trim() : null;
        return new Parameter(name, type, in, description);
    }

    static void parseParameterProperties(final Endpoint endpoint, final String content) {
        /*
         * Sample value of `content` is
         *
         *   <summary class="text-gray"><h4 id="required_status_checks-object">Properties of the <code>required_status_checks</code> object</h4></summary>
         *    <table>
         *      <tbody>
         *
         *          <tr>
         *            <td class="opacity-60"><code>strict</code> (boolean)</td>
         *            <td class="opacity-60"><p><strong>Required</strong>. Require branches to be up to date before merging.</p></td>
         *          </tr>
         *
         *          <tr>
         *            <td class="opacity-60"><code>contexts</code> (array of strings)</td>
         *            <td class="opacity-60"><p><strong>Required</strong>. The list of status checks to require in order to merge into this branch</p></td>
         *          </tr>
         *
         *      </tbody>
         */

        final AtomicReference<String> owner = new AtomicReference<>();
        final StreamBuilder stream = StreamBuilder.create(IO.read(content))
                .watch("Properties of the <code>", "</code>", owner::set)
                .substream("<tr>", "</tr>", row -> {
                    final ParameterProperty property = new ParameterProperty();
                    property.setOf(owner.get());
                    endpoint.getParameterProperties().add(property);
                    return StreamBuilder.create(row)
                            .watch("<td class=\"opacity-60\"><code>", "</code>", property::setName)
                            .watch("</code> (", ")</td>", property::setType)
                            .watch("<td class=\"opacity-60\"><p>", "</p></td>", property::setDescription)
                            .get();
                });


        try {
            stream.run();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
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
