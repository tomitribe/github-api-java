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
import javax.ws.rs.Path;
import org.tomitribe.github.model.GetAllCommonlyUsedLicenses;
import org.tomitribe.github.model.GetLicense;
import org.tomitribe.github.model.GetLicenseForRepository;
import org.tomitribe.github.model.License;
import org.tomitribe.github.model.LicenseContent;
import org.tomitribe.github.model.LicenseSimple;

public interface LicensesClient {

    @GET
    @Path("/licenses")
    @OperationId("licenses/get-all-commonly-used")
    @Docs("https://developer.github.com/v3/licenses/#get-all-commonly-used-licenses")
    @EnabledForGithubApps
    @Category("licenses")
    Stream<LicenseSimple> getAllCommonlyUsedLicenses(final GetAllCommonlyUsedLicenses getAllCommonlyUsedLicenses);

    @GET
    @Path("/licenses/{license}")
    @OperationId("licenses/get")
    @Docs("https://developer.github.com/v3/licenses/#get-a-license")
    @EnabledForGithubApps
    @Category("licenses")
    License getLicense(final GetLicense getLicense);

    @GET
    @Path("/licenses/{license}")
    @OperationId("licenses/get")
    @Docs("https://developer.github.com/v3/licenses/#get-a-license")
    @EnabledForGithubApps
    @Category("licenses")
    License getLicense(@PathParam("license") final String license);

    @GET
    @Path("/repos/{owner}/{repo}/license")
    @OperationId("licenses/get-for-repo")
    @Docs("https://developer.github.com/v3/licenses/#get-the-license-for-a-repository")
    @EnabledForGithubApps
    @Category("licenses")
    LicenseContent getLicenseForRepository(final GetLicenseForRepository getLicenseForRepository);

    @GET
    @Path("/repos/{owner}/{repo}/license")
    @OperationId("licenses/get-for-repo")
    @Docs("https://developer.github.com/v3/licenses/#get-the-license-for-a-repository")
    @EnabledForGithubApps
    @Category("licenses")
    LicenseContent getLicenseForRepository(@PathParam("owner") final String owner, @PathParam("repo") final String repo);
}
