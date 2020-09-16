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
import java.util.List;
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
public class AnyOf {

    @JsonbProperty("access_tokens_url")
    private URI accessTokensUrl;

    @JsonbProperty("account")
    private Object account;

    @JsonbProperty("app_id")
    private Integer appId;

    @JsonbProperty("app_slug")
    private String appSlug;

    @JsonbProperty("contact_email")
    private String contactEmail;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("events")
    private List<String> events;

    @JsonbProperty("html_url")
    private URI htmlUrl;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("permissions")
    private Permissions permissions;

    @JsonbProperty("repositories_url")
    private URI repositoriesUrl;

    @JsonbProperty("repository_selection")
    @JsonbTypeAdapter(RepositorySelectionAdapter.class)
    private RepositorySelection repositorySelection;

    @JsonbProperty("single_file_name")
    private String singleFileName;

    @JsonbProperty("suspended_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date suspendedAt;

    @JsonbProperty("target_id")
    private Integer targetId;

    @JsonbProperty("target_type")
    private String targetType;

    @JsonbProperty("updated_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date updatedAt;

    public enum RepositorySelection {

        ALL("all"), SELECTED("selected");

        private final String name;

        RepositorySelection(final String name) {
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

    public static class RepositorySelectionAdapter extends EnumAdapter<RepositorySelection> {

        public RepositorySelectionAdapter() {
            super(RepositorySelection.class);
        }
    }
}
