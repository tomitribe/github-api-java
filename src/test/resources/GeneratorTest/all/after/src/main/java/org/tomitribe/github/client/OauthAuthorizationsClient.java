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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import org.tomitribe.github.model.ApplicationGrant;
import org.tomitribe.github.model.Authorization;
import org.tomitribe.github.model.CreateNewAuthorization;
import org.tomitribe.github.model.DeleteAuthorization;
import org.tomitribe.github.model.DeleteGrant;
import org.tomitribe.github.model.GetOrCreateAuthorizationForSpecificApp;
import org.tomitribe.github.model.GetOrCreateAuthorizationForSpecificAppAndFingerprint;
import org.tomitribe.github.model.GetSingleAuthorization;
import org.tomitribe.github.model.GetSingleGrant;
import org.tomitribe.github.model.UpdateExistingAuthorization;

public interface OauthAuthorizationsClient {

    @POST
    @Path("/authorizations")
    @OperationId("oauth-authorizations/create-authorization")
    @Docs("https://developer.github.com/v3/oauth_authorizations/#create-a-new-authorization")
    @RemovalDate("2020-11-13")
    @DeprecationDate("2020-02-14")
    @Category("oauth-authorizations")
    Authorization createNewAuthorization(final CreateNewAuthorization createNewAuthorization);

    @DELETE
    @Path("/authorizations/{authorization_id}")
    @OperationId("oauth-authorizations/delete-authorization")
    @Docs("https://developer.github.com/v3/oauth_authorizations/#delete-an-authorization")
    @RemovalDate("2020-11-13")
    @DeprecationDate("2020-02-14")
    @Category("oauth-authorizations")
    void deleteAuthorization(final DeleteAuthorization deleteAuthorization);

    @DELETE
    @Path("/authorizations/{authorization_id}")
    @OperationId("oauth-authorizations/delete-authorization")
    @Docs("https://developer.github.com/v3/oauth_authorizations/#delete-an-authorization")
    @RemovalDate("2020-11-13")
    @DeprecationDate("2020-02-14")
    @Category("oauth-authorizations")
    void deleteAuthorization(@PathParam("authorization_id") final int authorizationId);

    @DELETE
    @Path("/applications/grants/{grant_id}")
    @OperationId("oauth-authorizations/delete-grant")
    @Docs("https://developer.github.com/v3/oauth_authorizations/#delete-a-grant")
    @RemovalDate("2020-11-13")
    @DeprecationDate("2020-02-14")
    @Category("oauth-authorizations")
    void deleteGrant(final DeleteGrant deleteGrant);

    @DELETE
    @Path("/applications/grants/{grant_id}")
    @OperationId("oauth-authorizations/delete-grant")
    @Docs("https://developer.github.com/v3/oauth_authorizations/#delete-a-grant")
    @RemovalDate("2020-11-13")
    @DeprecationDate("2020-02-14")
    @Category("oauth-authorizations")
    void deleteGrant(@PathParam("grant_id") final int grantId);

    @PUT
    @Path("/authorizations/clients/{client_id}")
    @OperationId("oauth-authorizations/get-or-create-authorization-for-app")
    @Docs("https://developer.github.com/v3/oauth_authorizations/#get-or-create-an-authorization-for-a-specific-app")
    @RemovalDate("2020-11-13")
    @DeprecationDate("2020-02-14")
    @Category("oauth-authorizations")
    Authorization getOrCreateAuthorizationForSpecificApp(final GetOrCreateAuthorizationForSpecificApp getOrCreateAuthorizationForSpecificApp);

    @PUT
    @Path("/authorizations/clients/{client_id}/{fingerprint}")
    @OperationId("oauth-authorizations/get-or-create-authorization-for-app-and-fingerprint")
    @Docs("https://developer.github.com/v3/oauth_authorizations/#get-or-create-an-authorization-for-a-specific-app-and-fingerprint")
    @RemovalDate("2020-11-13")
    @DeprecationDate("2020-02-14")
    @Category("oauth-authorizations")
    Authorization getOrCreateAuthorizationForSpecificAppAndFingerprint(final GetOrCreateAuthorizationForSpecificAppAndFingerprint getOrCreateAuthorizationForSpecificAppAndFingerprint);

    @GET
    @Path("/authorizations/{authorization_id}")
    @OperationId("oauth-authorizations/get-authorization")
    @Docs("https://developer.github.com/v3/oauth_authorizations/#get-a-single-authorization")
    @RemovalDate("2020-11-13")
    @DeprecationDate("2020-02-14")
    @Category("oauth-authorizations")
    Authorization getSingleAuthorization(final GetSingleAuthorization getSingleAuthorization);

    @GET
    @Path("/authorizations/{authorization_id}")
    @OperationId("oauth-authorizations/get-authorization")
    @Docs("https://developer.github.com/v3/oauth_authorizations/#get-a-single-authorization")
    @RemovalDate("2020-11-13")
    @DeprecationDate("2020-02-14")
    @Category("oauth-authorizations")
    Authorization getSingleAuthorization(@PathParam("authorization_id") final int authorizationId);

    @GET
    @Path("/applications/grants/{grant_id}")
    @OperationId("oauth-authorizations/get-grant")
    @Docs("https://developer.github.com/v3/oauth_authorizations/#get-a-single-grant")
    @RemovalDate("2020-11-13")
    @DeprecationDate("2020-02-14")
    @Category("oauth-authorizations")
    ApplicationGrant getSingleGrant(final GetSingleGrant getSingleGrant);

    @GET
    @Path("/applications/grants/{grant_id}")
    @OperationId("oauth-authorizations/get-grant")
    @Docs("https://developer.github.com/v3/oauth_authorizations/#get-a-single-grant")
    @RemovalDate("2020-11-13")
    @DeprecationDate("2020-02-14")
    @Category("oauth-authorizations")
    ApplicationGrant getSingleGrant(@PathParam("grant_id") final int grantId);

    @GET
    @Path("/authorizations")
    @OperationId("oauth-authorizations/list-authorizations")
    @Docs("https://developer.github.com/v3/oauth_authorizations/#list-your-authorizations")
    @RemovalDate("2020-11-13")
    @DeprecationDate("2020-02-14")
    @Category("oauth-authorizations")
    @Paged(Authorization[].class)
    Stream<Authorization> listYourAuthorizations();

    @GET
    @Path("/applications/grants")
    @OperationId("oauth-authorizations/list-grants")
    @Docs("https://developer.github.com/v3/oauth_authorizations/#list-your-grants")
    @RemovalDate("2020-11-13")
    @DeprecationDate("2020-02-14")
    @Category("oauth-authorizations")
    @Paged(ApplicationGrant[].class)
    Stream<ApplicationGrant> listYourGrants();

    @PATCH
    @Path("/authorizations/{authorization_id}")
    @OperationId("oauth-authorizations/update-authorization")
    @Docs("https://developer.github.com/v3/oauth_authorizations/#update-an-existing-authorization")
    @RemovalDate("2020-11-13")
    @DeprecationDate("2020-02-14")
    @Category("oauth-authorizations")
    Authorization updateExistingAuthorization(final UpdateExistingAuthorization updateExistingAuthorization);
}
