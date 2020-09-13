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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.json.bind.annotation.JsonbProperty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Annotation {

    /**
     * Required. The level of the annotation. Can be one of notice, warning, or failure.
     */
    @JsonbProperty("annotation_level")
    private String annotationLevel;

    /**
     * Required. The end line of the annotation.
     */
    @JsonbProperty("end_line")
    private Integer endLine;

    /**
     * The end column of the annotation. Annotations only support start_column and
     * end_column on the same line. Omit this parameter if start_line and end_line
     * have different values.
     */
    @JsonbProperty("end_column")
    private Integer end_column;

    /**
     * Required. A short description of the feedback for these lines of code. The maximum
     * size is 64 KB.
     */
    @JsonbProperty("message")
    private String message;

    /**
     * Required. The path of the file to add an annotation to. For example, assets/css/main.css.
     */
    @JsonbProperty("path")
    private String path;

    /**
     * Details about this annotation. The maximum size is 64 KB.
     */
    @JsonbProperty("raw_details")
    private String rawDetails;

    /**
     * The start column of the annotation. Annotations only support start_column and
     * end_column on the same line. Omit this parameter if start_line and end_line
     * have different values.
     */
    @JsonbProperty("start_column")
    private Integer startColumn;

    /**
     * Required. The start line of the annotation.
     */
    @JsonbProperty("start_line")
    private Integer startLine;

    /**
     * The title that represents the annotation. The maximum size is 255 characters.
     */
    @JsonbProperty("title")
    private String title;
}
