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

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PullRequest {

    @JsonbProperty("repository_url")
    private String repository_url;

    @JsonbProperty("pull_request_url")
    private String pull_request_url;

    @JsonbProperty("pull_request_number")
    private Integer pull_request_number;

    @JsonbProperty("labels")
    private String labels;

    @JsonbProperty("owner")
    private String owner;

    @JsonbProperty("repo")
    private String repo;

    @JsonbProperty("state")
    private State state;

}
