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
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tomitribe.github.core.EnumAdapter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tree {

    @JsonbProperty("content")
    private String content;

    @JsonbProperty("mode")
    @JsonbTypeAdapter(ModeAdapter.class)
    private Mode mode;

    @JsonbProperty("path")
    private String path;

    @JsonbProperty("sha")
    private String sha;

    @JsonbProperty("size")
    private Integer size;

    @JsonbProperty("type")
    @JsonbTypeAdapter(TypeAdapter.class)
    private Type type;

    @JsonbProperty("url")
    private URI url;

    public enum Mode {

        _100644("100644"), _100755("100755"), _040000("040000"), _160000("160000"), _120000("120000");

        private final String name;

        Mode(final String name) {
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

    public static class ModeAdapter extends EnumAdapter<Mode> {

        public ModeAdapter() {
            super(Mode.class);
        }
    }

    public enum Type {

        BLOB("blob"), TREE("tree"), COMMIT("commit");

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
}
