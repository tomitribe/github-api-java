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
import java.util.List;
import javax.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tomitribe.github.core.DateAdapter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ComponentId("#/components/schemas/credential-authorization")
public class CredentialAuthorization {

    @JsonbProperty("credential_accessed_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date credentialAccessedAt;

    @JsonbProperty("credential_authorized_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date credentialAuthorizedAt;

    @JsonbProperty("credential_id")
    private Integer credentialId;

    @JsonbProperty("credential_type")
    private String credentialType;

    @JsonbProperty("fingerprint")
    private String fingerprint;

    @JsonbProperty("login")
    private String login;

    @JsonbProperty("scopes")
    private List<String> scopes;

    @JsonbProperty("token_last_eight")
    private String tokenLastEight;
}
