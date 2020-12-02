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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Config {

    @JsonbProperty("address")
    private String address;

    @JsonbProperty("content_type")
    private String contentType;

    @JsonbProperty("digest")
    private String digest;

    @JsonbProperty("email")
    private String email;

    @JsonbProperty("insecure_ssl")
    private String insecureSsl;

    @JsonbProperty("password")
    private String password;

    @JsonbProperty("room")
    private String room;

    @JsonbProperty("secret")
    private String secret;

    @JsonbProperty("subdomain")
    private String subdomain;

    @JsonbProperty("token")
    private String token;

    @JsonbProperty("url")
    private String url;

    @JsonbProperty("username")
    private String username;
}
