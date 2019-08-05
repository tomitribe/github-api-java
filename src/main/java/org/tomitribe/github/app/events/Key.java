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

package org.tomitribe.github.app.events;

import javax.json.bind.annotation.JsonbProperty;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Used by:
 * - DeployKeyEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Key {

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("key")
    private String key;

    @JsonbProperty("url")
    private String url;

    @JsonbProperty("title")
    private String title;

    @JsonbProperty("verified")
    private Boolean verified;

    @JsonbProperty("created_at")
    private String createdAt;

    @JsonbProperty("read_only")
    private Boolean readOnly;

}
