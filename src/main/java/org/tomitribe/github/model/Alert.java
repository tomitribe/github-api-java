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

/**
 * Used by:
 * - RepositoryVulnerabilityAlertEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Alert {

    @JsonbProperty("affected_package_name")
    private String affectedPackageName;

    @JsonbProperty("affected_range")
    private String affectedRange;

    @JsonbProperty("external_identifier")
    private String externalIdentifier;

    @JsonbProperty("external_reference")
    private String externalReference;

    @JsonbProperty("fixed_in")
    private String fixedIn;

    @JsonbProperty("id")
    private Long id;
}
