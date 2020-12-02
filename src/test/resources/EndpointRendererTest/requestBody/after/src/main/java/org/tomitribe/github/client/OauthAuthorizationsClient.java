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
package org.tomitribe.github.client;

import java.util.stream.Stream;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.tomitribe.github.model.Authorization;
import org.tomitribe.github.model.CreateNewAuthorization;

public interface OauthAuthorizationsClient {

    @POST
    @Path("/authorizations")
    @OperationId("oauth-authorizations/create-authorization")
    @Docs("https://developer.github.com/v3/oauth_authorizations/#create-a-new-authorization")
    @RemovalDate("2020-11-13")
    @DeprecationDate("2020-02-14")
    @Category("oauth-authorizations")
    Authorization createNewAuthorization(final CreateNewAuthorization createNewAuthorization);

    @GET
    @Path("/authorizations")
    @OperationId("oauth-authorizations/list-authorizations")
    @Docs("https://developer.github.com/v3/oauth_authorizations/#list-your-authorizations")
    @RemovalDate("2020-11-13")
    @DeprecationDate("2020-02-14")
    @Category("oauth-authorizations")
    @Paged(Authorization[].class)
    Stream<Authorization> listYourAuthorizations();
}
