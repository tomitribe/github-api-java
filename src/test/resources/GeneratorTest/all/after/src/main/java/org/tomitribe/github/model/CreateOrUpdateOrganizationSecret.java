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
public class CreateOrUpdateOrganizationSecret {

    @JsonbProperty("encrypted_value")
    private String encryptedValue;

    @JsonbProperty("key_id")
    private String keyId;

    @JsonbTransient
    @PathParam("org")
    private String org;

    @JsonbTransient
    @PathParam("secret_name")
    private String secretName;

    @JsonbProperty("selected_repository_ids")
    private List<String> selectedRepositoryIds;

    @JsonbProperty("visibility")
    @JsonbTypeAdapter(VisibilityAdapter.class)
    private Visibility visibility;

    public enum Visibility {

        ALL("all"), PRIVATE("private"), SELECTED("selected");

        private final String name;

        Visibility(final String name) {
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

    public static class VisibilityAdapter extends EnumAdapter<Visibility> {

        public VisibilityAdapter() {
            super(Visibility.class);
        }
    }
}
