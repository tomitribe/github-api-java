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
@ComponentId("#/components/schemas/interaction-limit")
public class InteractionLimit {

    @JsonbProperty("expires_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date expiresAt;

    @JsonbProperty("limit")
    @JsonbTypeAdapter(LimitAdapter.class)
    private Limit limit;

    @JsonbProperty("origin")
    private String origin;

    public enum Limit {

        EXISTING_USERS("existing_users"), CONTRIBUTORS_ONLY("contributors_only"), COLLABORATORS_ONLY("collaborators_only");

        private final String name;

        Limit(final String name) {
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

    public static class LimitAdapter extends EnumAdapter<Limit> {

        public LimitAdapter() {
            super(Limit.class);
        }
    }
}
