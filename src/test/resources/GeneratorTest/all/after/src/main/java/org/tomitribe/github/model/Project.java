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
@ComponentId("#/components/schemas/project")
public class Project {

    @JsonbProperty("private")
    private Boolean _private;

    @JsonbProperty("body")
    private String body;

    @JsonbProperty("cards_url")
    private URI cardsUrl;

    @JsonbProperty("columns_url")
    private URI columnsUrl;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("creator")
    private SimpleUser creator;

    @JsonbProperty("html_url")
    private URI htmlUrl;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("number")
    private Integer number;

    @JsonbProperty("organization_permission")
    @JsonbTypeAdapter(OrganizationPermissionAdapter.class)
    private OrganizationPermission organizationPermission;

    @JsonbProperty("owner_url")
    private URI ownerUrl;

    @JsonbProperty("permissions")
    private Permissions permissions;

    @JsonbProperty("state")
    private String state;

    @JsonbProperty("updated_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date updatedAt;

    @JsonbProperty("url")
    private URI url;

    public enum OrganizationPermission {

        READ("read"), WRITE("write"), ADMIN("admin"), NONE("none");

        private final String name;

        OrganizationPermission(final String name) {
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

    public static class OrganizationPermissionAdapter extends EnumAdapter<OrganizationPermission> {

        public OrganizationPermissionAdapter() {
            super(OrganizationPermission.class);
        }
    }
}
