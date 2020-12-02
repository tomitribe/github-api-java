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
public class Annotations {

    @JsonbProperty("annotation_level")
    @JsonbTypeAdapter(AnnotationLevelAdapter.class)
    private AnnotationLevel annotationLevel;

    @JsonbProperty("end_column")
    private Integer endColumn;

    @JsonbProperty("end_line")
    private Integer endLine;

    @JsonbProperty("message")
    private String message;

    @JsonbProperty("path")
    private String path;

    @JsonbProperty("raw_details")
    private String rawDetails;

    @JsonbProperty("start_column")
    private Integer startColumn;

    @JsonbProperty("start_line")
    private Integer startLine;

    @JsonbProperty("title")
    private String title;

    public enum AnnotationLevel {

        NOTICE("notice"), WARNING("warning"), FAILURE("failure");

        private final String name;

        AnnotationLevel(final String name) {
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

    public static class AnnotationLevelAdapter extends EnumAdapter<AnnotationLevel> {

        public AnnotationLevelAdapter() {
            super(AnnotationLevel.class);
        }
    }
}
