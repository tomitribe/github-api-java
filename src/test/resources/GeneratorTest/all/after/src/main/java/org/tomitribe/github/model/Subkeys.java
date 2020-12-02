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

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subkeys {

    @JsonbProperty("can_certify")
    private Boolean canCertify;

    @JsonbProperty("can_encrypt_comms")
    private Boolean canEncryptComms;

    @JsonbProperty("can_encrypt_storage")
    private Boolean canEncryptStorage;

    @JsonbProperty("can_sign")
    private Boolean canSign;

    @JsonbProperty("created_at")
    private String createdAt;

    @JsonbProperty("emails")
    private List<Emails> emails;

    @JsonbProperty("expires_at")
    private String expiresAt;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("key_id")
    private String keyId;

    @JsonbProperty("primary_key_id")
    private Integer primaryKeyId;

    @JsonbProperty("public_key")
    private String publicKey;

    @JsonbProperty("raw_key")
    private String rawKey;

    @JsonbProperty("subkeys")
    private List<Subkeys> subkeys;
}
