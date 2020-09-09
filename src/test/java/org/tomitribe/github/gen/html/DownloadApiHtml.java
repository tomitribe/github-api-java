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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tomitribe.github.gen.Html;
import org.tomitribe.github.gen.Project;
import org.tomitribe.swizzle.stream.StreamBuilder;
import org.tomitribe.util.IO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Download API reference documentation from https://docs.github.com/en/rest/reference
 * and store the text in test/resources.
 *
 * With the content downloaded we can continuously work on parsing out data
 * quickly without having to spend time re-downloading.
 *
 * Similarly, we can conveniently get diffs when the API changes
 */
public class DownloadApiHtml {

    private URI uri = URI.create("https://docs.github.com/en/rest/reference");
    private Client client = ClientBuilder.newClient();

    public static void main(String[] args) throws IOException {
        new DownloadApiHtml().main();
    }

    private void main() throws IOException {
        final Project project = Project.root();
        final Html html = project.src().test().resources().gen().html();

        final String content = getContent(uri);
        html.write("index.html", content);

        final List<String> pages = new ArrayList<>();

        StreamBuilder.create(IO.read(content))
                .substream("<div id=\"article-contents\" class=\"article-grid-body\">", "</div>", in -> StreamBuilder.create(in)
                        .watch("href=\"/en/rest/reference/", "\"", pages::add)
                        .get())
                .run();

        for (final String page : pages) {
            final URI uri = this.uri.resolve("/en/rest/reference/" + page);
            html.write(page + ".html", getContent(uri));
        }
    }

    private String getContent(final URI uri) {
        return client.target(uri).request().get(String.class);
    }

}
