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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.tomitribe.github.model.ActionsBillingUsage;
import org.tomitribe.github.model.CombinedBillingUsage;
import org.tomitribe.github.model.GetGitHubActionsBillingForEnterprise;
import org.tomitribe.github.model.GetGitHubPackagesBillingForEnterprise;
import org.tomitribe.github.model.GetSharedStorageBillingForEnterprise;
import org.tomitribe.github.model.PackagesBillingUsage;

public interface EnterpriseAdminClient {

    @GET
    @Path("/enterprises/{enterprise_id}/settings/billing/actions")
    @OperationId("billing/get-github-actions-billing-ghe")
    @Docs("https://developer.github.com/v3/billing/#get-github-actions-billing-for-an-enterprise")
    @GithubCloudOnly
    @Category("enterprise-admin")
    @Subcategory("billing")
    ActionsBillingUsage getGitHubActionsBillingForEnterprise(final GetGitHubActionsBillingForEnterprise getGitHubActionsBillingForEnterprise);

    @GET
    @Path("/enterprises/{enterprise_id}/settings/billing/actions")
    @OperationId("billing/get-github-actions-billing-ghe")
    @Docs("https://developer.github.com/v3/billing/#get-github-actions-billing-for-an-enterprise")
    @GithubCloudOnly
    @Category("enterprise-admin")
    @Subcategory("billing")
    ActionsBillingUsage getGitHubActionsBillingForEnterprise(@PathParam("enterprise-id") final String enterpriseId);

    @GET
    @Path("/enterprises/{enterprise_id}/settings/billing/packages")
    @OperationId("billing/get-github-packages-billing-ghe")
    @Docs("https://developer.github.com/v3/billing/#get-github-packages-billing-for-an-enterprise")
    @GithubCloudOnly
    @Category("enterprise-admin")
    @Subcategory("billing")
    PackagesBillingUsage getGitHubPackagesBillingForEnterprise(final GetGitHubPackagesBillingForEnterprise getGitHubPackagesBillingForEnterprise);

    @GET
    @Path("/enterprises/{enterprise_id}/settings/billing/packages")
    @OperationId("billing/get-github-packages-billing-ghe")
    @Docs("https://developer.github.com/v3/billing/#get-github-packages-billing-for-an-enterprise")
    @GithubCloudOnly
    @Category("enterprise-admin")
    @Subcategory("billing")
    PackagesBillingUsage getGitHubPackagesBillingForEnterprise(@PathParam("enterprise-id") final String enterpriseId);

    @GET
    @Path("/enterprises/{enterprise_id}/settings/billing/shared-storage")
    @OperationId("billing/get-shared-storage-billing-ghe")
    @Docs("https://developer.github.com/v3/billing/#get-shared-storage-billing-for-an-enterprise")
    @GithubCloudOnly
    @Category("enterprise-admin")
    @Subcategory("billing")
    CombinedBillingUsage getSharedStorageBillingForEnterprise(final GetSharedStorageBillingForEnterprise getSharedStorageBillingForEnterprise);

    @GET
    @Path("/enterprises/{enterprise_id}/settings/billing/shared-storage")
    @OperationId("billing/get-shared-storage-billing-ghe")
    @Docs("https://developer.github.com/v3/billing/#get-shared-storage-billing-for-an-enterprise")
    @GithubCloudOnly
    @Category("enterprise-admin")
    @Subcategory("billing")
    CombinedBillingUsage getSharedStorageBillingForEnterprise(@PathParam("enterprise-id") final String enterpriseId);
}
