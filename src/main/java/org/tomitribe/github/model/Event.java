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
package org.tomitribe.github.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.json.bind.annotation.JsonbProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Actor {

        @JsonbProperty("id")
        private String id;

        @JsonbProperty("login")
        private String login;

        @JsonbProperty("display_login")
        private String displayLogin;

        @JsonbProperty("gravatar_id")
        private String gravatarId;

        @JsonbProperty("url")
        private String url;

        @JsonbProperty("avatar_url")
        private String avatarUrl;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Repo {

        @JsonbProperty("id")
        private String id;

        @JsonbProperty("name")
        private String name;

        @JsonbProperty("url")
        private String url;
    }

    @JsonbProperty("actor")
    private Actor actor;

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("payload")
    private String payload;

    @JsonbProperty("repo")
    private Repo repo;

    @JsonbProperty("type")
    private String type;
}
