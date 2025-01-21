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

import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tomitribe.github.core.EnumAdapter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListRepositoriesForAuthenticatedUser {

    @JsonbTransient
    @QueryParam("affiliation")
    private String affiliation;

    @JsonbTransient
    @QueryParam("before")
    private String before;

    @JsonbTransient
    @QueryParam("direction")
    @JsonbTypeAdapter(DirectionAdapter.class)
    private Direction direction;

    @JsonbTransient
    @QueryParam("since")
    private String since;

    @JsonbTransient
    @QueryParam("sort")
    @JsonbTypeAdapter(SortAdapter.class)
    private Sort sort;

    @JsonbTransient
    @QueryParam("type")
    @JsonbTypeAdapter(TypeAdapter.class)
    private Type type;

    @JsonbTransient
    @QueryParam("visibility")
    @JsonbTypeAdapter(VisibilityAdapter.class)
    private Visibility visibility;

    public enum Direction {

        ASC("asc"), DESC("desc");

        private final String name;

        Direction(final String name) {
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

    public static class DirectionAdapter extends EnumAdapter<Direction> {

        public DirectionAdapter() {
            super(Direction.class);
        }
    }

    public enum Sort {

        CREATED("created"), UPDATED("updated"), PUSHED("pushed"), FULL_NAME("full_name");

        private final String name;

        Sort(final String name) {
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

    public static class SortAdapter extends EnumAdapter<Sort> {

        public SortAdapter() {
            super(Sort.class);
        }
    }

    public enum Type {

        ALL("all"), OWNER("owner"), PUBLIC("public"), PRIVATE("private"), MEMBER("member");

        private final String name;

        Type(final String name) {
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

    public static class TypeAdapter extends EnumAdapter<Type> {

        public TypeAdapter() {
            super(Type.class);
        }
    }

    public enum Visibility {

        ALL("all"), PUBLIC("public"), PRIVATE("private");

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
