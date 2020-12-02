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

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.ws.rs.PathParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tomitribe.github.core.EnumAdapter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTeamLegacy {

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("parent_team_id")
    private Integer parentTeamId;

    @JsonbProperty("permission")
    @JsonbTypeAdapter(PermissionAdapter.class)
    private Permission permission;

    @JsonbProperty("privacy")
    @JsonbTypeAdapter(PrivacyAdapter.class)
    private Privacy privacy;

    @JsonbTransient
    @PathParam("team-id")
    private Integer teamId;

    public enum Permission {

        PULL("pull"), PUSH("push"), ADMIN("admin");

        private final String name;

        Permission(final String name) {
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

    public static class PermissionAdapter extends EnumAdapter<Permission> {

        public PermissionAdapter() {
            super(Permission.class);
        }
    }

    public enum Privacy {

        SECRET("secret"), CLOSED("closed");

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
