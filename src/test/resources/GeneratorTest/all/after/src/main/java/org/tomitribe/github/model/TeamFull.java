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

import java.net.URI;
import java.util.Date;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tomitribe.github.core.DateAdapter;
import org.tomitribe.github.core.EnumAdapter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ComponentId("#/components/schemas/team-full")
public class TeamFull {

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("html_url")
    private URI htmlUrl;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("ldap_dn")
    private String ldapDn;

    @JsonbProperty("members_count")
    private Integer membersCount;

    @JsonbProperty("members_url")
    private String membersUrl;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("organization")
    private Organization organization;

    @JsonbProperty("parent")
    private TeamSimple parent;

    @JsonbProperty("permission")
    private String permission;

    @JsonbProperty("privacy")
    @JsonbTypeAdapter(PrivacyAdapter.class)
    private Privacy privacy;

    @JsonbProperty("repos_count")
    private Integer reposCount;

    @JsonbProperty("repositories_url")
    private URI repositoriesUrl;

    @JsonbProperty("slug")
    private String slug;

    @JsonbProperty("updated_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date updatedAt;

    @JsonbProperty("url")
    private URI url;

    public enum Privacy {

        CLOSED("closed"), SECRET("secret");

        private final String name;

        Privacy(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static class PrivacyAdapter extends EnumAdapter<Privacy> {

        public PrivacyAdapter() {
            super(Privacy.class);
        }
    }
}
