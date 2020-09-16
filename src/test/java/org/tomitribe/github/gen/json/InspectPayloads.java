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
package org.tomitribe.github.gen.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.tomitribe.github.core.JsonMarshalling;
import org.tomitribe.github.gen.Project;
import org.tomitribe.github.gen.html.Endpoint;
import org.tomitribe.github.gen.html.Response;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.tomitribe.github.gen.util.Words.getTypeName;
import static org.tomitribe.github.gen.util.Words.getVariableName;

public class InspectPayloads {

    public static void main(String[] args) {
        final List<Endpoint> endpoints = Project.root().src().test().resources().gen().json().files()
                .filter(file -> file.getName().endsWith(".json"))
                .map(file -> JsonMarshalling.unmarshal(Endpoint[].class, file))
                .flatMap(Stream::of)
                .collect(Collectors.toList());

        final Count count = new Count();
        for (final Endpoint endpoint : endpoints) {
            final Signature signature = implySignature(endpoint);
            final String description = String.format("%s: %s", endpoint.getGroup(), endpoint.getTitle());

            System.out.printf("%-70s %s%n", description, signature);
            count.add(signature);
        }

        System.out.println(count);
    }

    @Data
    @AllArgsConstructor
    @Builder(toBuilder = true, builderClassName = "Builder")
    public static class Signature {
        private final Endpoint endpoint;
        private final String name;
        private final String returnType;
        private final boolean stream;

        @Override
        public String toString() {
            if (isStream()) {
                return String.format("Stream<%s> %s();", returnType, name);
            } else {
                return String.format("%s %s();", returnType, name);
            }
        }
    }

    public static class Count {
        private int total;
        private int missing;

        public Count() {
        }

        public void add(final Signature signature) {
            total++;
            if (signature == null) missing++;
        }

        @Override
        public String toString() {
            final int covered = total - missing;
            final double percent = (covered / (double) total) * 100;
            return String.format("Total %s, covered %s, missing %s (%.2f%%)", total, covered, missing, percent);
        }
    }

    private final static Pattern PATTERN = Pattern.compile("(get or create|get|add|set|remove|list|delete|update|create|disable|enable|check|reset|revoke|suspend|unsuspend|unlock) (.*)");

    private static Signature implySignature(final Endpoint endpoint) {
        final String title = endpoint.getTitle().toLowerCase()
                .replace("get all", "list")
                .replace(" a ", " ")
                .replace(" the ", " ")
                .replace(" an ", " ");

        final String[] parts = title.split(" for ");
        final String action = parts[0];
        final Matcher matcher = PATTERN.matcher(action);
        if (!matcher.find()) return null;

        final String verb = matcher.group(1);
        final String subject = getTypeName(matcher.group(2));

        final String name = getVariableName(title);

        return normalize(Signature.builder()
                .endpoint(endpoint)
                .returnType(subject)
                .name(name)
                .build());
    }

    private static Signature normalize(final Signature signature) {
        return Function.<Signature>identity()
                .andThen(InspectPayloads::voidReturn)
                .andThen(InspectPayloads::returnsStream)
                .andThen(InspectPayloads::fixRepositoriesReference)
                .apply(signature);
    }

    private static Signature voidReturn(final Signature signature) {
        final boolean isVoid = signature.getEndpoint().getResponses().stream()
                .anyMatch(response -> response.getCode().contains("204"));

        if (!isVoid) return signature;
        return signature.toBuilder().returnType("void").build();
    }

    private static Signature fixRepositoriesReference(final Signature signature) {
        if (signature.getReturnType().contains("Repositories")) {
            return signature.toBuilder()
                    .returnType("Repository")
                    .stream(true)
                    .build();
        }

        return signature;
    }

    private static Signature watcher(final Signature signature) {
        if (signature.getReturnType().equals("Watcher")) {
            return signature.toBuilder()
                    .returnType("User")
                    .build();
        }

        return signature;
    }

    private static Signature returnsStream(final Signature signature) {
        final boolean isCollection = signature.getEndpoint().getResponses().stream()
                .map(Response::getContent)
                .filter(Objects::nonNull)
                .anyMatch(s -> s.startsWith("["));

        if (!isCollection) return signature;

        final String returnType = signature.getReturnType().replaceAll("s$", "");

        return signature.toBuilder()
                .returnType(returnType)
                .stream(true)
                .build();
    }

}
