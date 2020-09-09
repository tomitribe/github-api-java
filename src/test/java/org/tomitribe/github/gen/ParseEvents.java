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
package org.tomitribe.github.gen;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.tomitribe.github.gen.json.Clazz;
import org.tomitribe.github.gen.json.ClazzRenderer;
import org.tomitribe.github.gen.json.JsonToModel;
import org.tomitribe.util.IO;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParseEvents {

    public static void main(String[] args) throws IOException {
        final ClassLoader loader = ParseEvents.class.getClassLoader();
        final URL resource = loader.getResource("event-types.html");

        final String slurp = IO.slurp(resource);
        final List<String> sections = list(slurp.split("<h2>\n<a id="));
        sections.remove(0);

        final List<Event> events = sections.stream()
                .filter(s -> !s.contains("Events of this type are <strong>no longer delivered</strong>"))
                .map(Event::parse)
                .filter(event -> !event.getTypeName().equals("MarketplacePurchaseEvent"))
                .collect(Collectors.toList());

        final List<Clazz> generate = JsonToModel.generate(events);

        {
            final File packageDir = new File("/Users/dblevins/work/tomitribe/checkmate/src/main/java/org/tomitribe/github/app/events/");
            packageDir.mkdirs();
            final ClazzRenderer renderer = new ClazzRenderer(packageDir, "org.tomitribe.github.app.events");
            for (final Clazz clazz : generate) {
                System.out.println(clazz);
                renderer.render(clazz);
            }
        }

        events.stream().forEach(ParseEvents::savePayload);

        {
            final File packageDir = new File("/Users/dblevins/work/tomitribe/checkmate/src/main/java/org/tomitribe/github/app/");
            packageDir.mkdirs();
            final GenerateResource generateResource = new GenerateResource(packageDir, "org.tomitribe.github.app");

            generateResource.generate(events);
        }
    }

    private static void savePayload(final Event event) {
        if (event.getPayload() == null) return;
        try {
            final File dir = new File("/Users/dblevins/work/tomitribe/checkmate/src/test/resources/payloads");
            final File jsonFile = new File(dir, event.getTypeName() + ".json");
            IO.copy(IO.read(event.getPayload().getJson()), jsonFile);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    @Data
    @Builder
    @ToString
    @AllArgsConstructor
    public static class Event {
        private final String typeName;
        private final String webhookName;
        private final String description;
        private final List<PayloadField> payloadFields;
        private final Payload payload;

        public static Event parse(final String content) {
            try {
                final List<String> parts = list(content.split("<h3>\n<a id="));

                final String eventName = parts.get(0).split("</h2>")[0].replaceAll(".*>", "");
                final String description = parts.get(0).split("</h2>")[1].trim();

                final List<PayloadField> payloadFields = parts.stream()
                        .filter(s -> s.contains(">Events API payload<"))
                        .map(PayloadField::parseAll)
                        .findFirst()
                        .orElse(null);

                final String webhookName = parts.stream()
                        .filter(s -> s.contains(">Webhook event name<"))
                        .map(s -> s.split("</?code>")[1])
                        .findFirst()
                        .orElse(null);

                final Payload payload = parts.stream()
                        .filter(s -> s.contains(">Webhook payload example<"))
                        .map(Payload::parse)
                        .findFirst()
                        .orElse(null);


                return new Event(eventName, webhookName, description, payloadFields, payload);
            } catch (Throwable e) {
                System.err.println(content);
                e.printStackTrace();
                throw e;
            }
        }
    }


    @Data
    @Builder
    @AllArgsConstructor
    public static class Payload {
        private final String json;

        public static Payload parse(final String content) {
            final String raw = content.split("<pre class=\"highlight highlight-json\">")[1];
            final String json = raw.split("</?code>")[1].replaceAll("<[^>]+>", "");
            return new Payload(json);
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class PayloadField {
        private final String key;
        private final String type;
        private final String description;

        public static List<PayloadField> parseAll(final String content) {
            return Stream.of(content.split("</?tr>"))
                    .map(String::trim)
                    .filter(s -> s.length() != 0)
                    .filter(s1 -> s1.contains("<code>"))
                    .map(PayloadField::parse)
                    .collect(Collectors.toList());
        }

        public static PayloadField parse(final String line) {
            final List<String> columns = Stream.of(line.split("</?td>"))
                    .map(String::trim)
                    .filter(s -> s.length() != 0)
                    .collect(Collectors.toList());
            return new PayloadField(
                    columns.get(0).replaceAll("</?code>", ""),
                    columns.get(1).replaceAll("</?code>", ""),
                    columns.get(2)
            );
        }
    }

    private static ArrayList<String> list(final String[] split) {
        return new ArrayList<>(Arrays.asList(split));
    }
}
