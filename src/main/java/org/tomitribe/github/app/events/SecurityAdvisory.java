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
 * - SecurityAdvisoryEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SecurityAdvisory {

    @JsonbProperty("ghsa_id")
    private String ghsaId;

    @JsonbProperty("summary")
    private String summary;

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("severity")
    private String severity;

    @JsonbProperty("identifiers")
    private List<Identifier> identifiers;

    @JsonbProperty("references")
    private List<Reference> references;

    @JsonbProperty("published_at")
    private String publishedAt;

    @JsonbProperty("updated_at")
    private String updatedAt;

    @JsonbProperty("withdrawn_at")
    private String withdrawnAt;

    @JsonbProperty("vulnerabilities")
    private List<Vulnerability> vulnerabilities;

}
