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

import java.util.List;
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
public class CreateOrganizationInvitation {

    @JsonbProperty("email")
    private String email;

    @JsonbProperty("invitee_id")
    private Integer inviteeId;

    @JsonbTransient
    @PathParam("org")
    private String org;

    @JsonbProperty("role")
    @JsonbTypeAdapter(RoleAdapter.class)
    private Role role;

    @JsonbProperty("team_ids")
    private List<Integer> teamIds;

    public enum Role {

        ADMIN("admin"), DIRECT_MEMBER("direct_member"), BILLING_MANAGER("billing_manager");

        private final String name;

        Role(final String name) {
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

    public static class RoleAdapter extends EnumAdapter<Role> {

        public RoleAdapter() {
            super(Role.class);
        }
    }
}
