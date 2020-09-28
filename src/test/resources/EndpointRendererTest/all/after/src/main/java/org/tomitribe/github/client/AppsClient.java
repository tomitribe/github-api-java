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
import org.tomitribe.github.model.AddRepositoryToAppInstallation;
import org.tomitribe.github.model.Authorization;
import org.tomitribe.github.model.CheckAuthorization;
import org.tomitribe.github.model.CheckAuthorizationResponse;
import org.tomitribe.github.model.CheckToken;
import org.tomitribe.github.model.ContentReferenceAttachment;
import org.tomitribe.github.model.CreateContentAttachment;
import org.tomitribe.github.model.CreateGitHubAppFromManifest;
import org.tomitribe.github.model.CreateGitHubAppFromManifestResponse;
import org.tomitribe.github.model.CreateInstallationAccessTokenForApp;
import org.tomitribe.github.model.DeleteAppAuthorization;
import org.tomitribe.github.model.DeleteAppToken;
import org.tomitribe.github.model.DeleteInstallationForAuthenticatedApp;
import org.tomitribe.github.model.GetApp;
import org.tomitribe.github.model.GetInstallationForAuthenticatedApp;
import org.tomitribe.github.model.GetOrganizationInstallationForAuthenticatedApp;
import org.tomitribe.github.model.GetRepositoryInstallationForAuthenticatedApp;
import org.tomitribe.github.model.GetSubscriptionPlanForAccount;
import org.tomitribe.github.model.GetSubscriptionPlanForAccountStubbed;
import org.tomitribe.github.model.GetUserInstallationForAuthenticatedApp;
import org.tomitribe.github.model.Installation;
import org.tomitribe.github.model.InstallationToken;
import org.tomitribe.github.model.InstallationsPage;
import org.tomitribe.github.model.Integration;
import org.tomitribe.github.model.ListAccountsForPlan;
import org.tomitribe.github.model.ListAccountsForPlanStubbed;
import org.tomitribe.github.model.ListInstallationsForAuthenticatedApp;
import org.tomitribe.github.model.ListRepositoriesAccessibleToUserAccessToken;
import org.tomitribe.github.model.MarketplaceListingPlan;
import org.tomitribe.github.model.MarketplacePurchase;
import org.tomitribe.github.model.RemoveRepositoryFromAppInstallation;
import org.tomitribe.github.model.RepositoriesPage;
import org.tomitribe.github.model.Repository;
import org.tomitribe.github.model.ResetAuthorization;
import org.tomitribe.github.model.ResetToken;
import org.tomitribe.github.model.RevokeAuthorizationForApplication;
import org.tomitribe.github.model.RevokeGrantForApplication;
import org.tomitribe.github.model.SuspendAppInstallation;
import org.tomitribe.github.model.UnsuspendAppInstallation;
import org.tomitribe.github.model.UserMarketplacePurchase;

public interface AppsClient {

    @PUT
    @Path("/user/installations/{installation_id}/repositories/{repository_id}")
    @OperationId("apps/add-repo-to-installation")
    @Docs("https://developer.github.com/v3/apps/installations/#add-a-repository-to-an-app-installation")
    @Preview("machine-man")
    @Category("apps")
    @Subcategory("installations")
    void addRepositoryToAppInstallation(final AddRepositoryToAppInstallation addRepositoryToAppInstallation);

    @PUT
    @Path("/user/installations/{installation_id}/repositories/{repository_id}")
    @OperationId("apps/add-repo-to-installation")
    @Docs("https://developer.github.com/v3/apps/installations/#add-a-repository-to-an-app-installation")
    @Preview("machine-man")
    @Category("apps")
    @Subcategory("installations")
    void addRepositoryToAppInstallation(@PathParam("installation_id") final int installationId, @PathParam("repository_id") final int repositoryId);

    @GET
    @Path("/applications/{client_id}/tokens/{access_token}")
    @OperationId("apps/check-authorization")
    @Docs("https://developer.github.com/v3/apps/oauth_applications/#check-an-authorization")
    @RemovalDate("2021-05-05")
    @DeprecationDate("2020-02-14")
    @Category("apps")
    @Subcategory("oauth-applications")
    CheckAuthorizationResponse checkAuthorization(final CheckAuthorization checkAuthorization);

    @GET
    @Path("/applications/{client_id}/tokens/{access_token}")
    @OperationId("apps/check-authorization")
    @Docs("https://developer.github.com/v3/apps/oauth_applications/#check-an-authorization")
    @RemovalDate("2021-05-05")
    @DeprecationDate("2020-02-14")
    @Category("apps")
    @Subcategory("oauth-applications")
    CheckAuthorizationResponse checkAuthorization(@PathParam("client-id") final String clientId, @PathParam("access-token") final String accessToken);

    @POST
    @Path("/applications/{client_id}/token")
    @OperationId("apps/check-token")
    @Docs("https://developer.github.com/v3/apps/oauth_applications/#check-a-token")
    @Category("apps")
    @Subcategory("oauth-applications")
    Authorization checkToken(final CheckToken checkToken);

    @POST
    @Path("/content_references/{content_reference_id}/attachments")
    @OperationId("apps/create-content-attachment")
    @Docs("https://developer.github.com/v3/apps/installations/#create-a-content-attachment")
    @EnabledForGithubApps
    @Preview("corsair")
    @Category("apps")
    @Subcategory("installations")
    ContentReferenceAttachment createContentAttachment(final CreateContentAttachment createContentAttachment);

    @POST
    @Path("/app-manifests/{code}/conversions")
    @OperationId("apps/create-from-manifest")
    @Docs("https://developer.github.com/v3/apps/#create-a-github-app-from-a-manifest")
    @Category("apps")
    CreateGitHubAppFromManifestResponse createGitHubAppFromManifest(final CreateGitHubAppFromManifest createGitHubAppFromManifest);

    @POST
    @Path("/app-manifests/{code}/conversions")
    @OperationId("apps/create-from-manifest")
    @Docs("https://developer.github.com/v3/apps/#create-a-github-app-from-a-manifest")
    @Category("apps")
    CreateGitHubAppFromManifestResponse createGitHubAppFromManifest(@PathParam("code") final String code);

    @POST
    @Path("/app/installations/{installation_id}/access_tokens")
    @OperationId("apps/create-installation-access-token")
    @Docs("https://developer.github.com/v3/apps/#create-an-installation-access-token-for-an-app")
    @EnabledForGithubApps
    @Preview("machine-man")
    @Category("apps")
    InstallationToken createInstallationAccessTokenForApp(final CreateInstallationAccessTokenForApp createInstallationAccessTokenForApp);

    @DELETE
    @Path("/applications/{client_id}/grant")
    @OperationId("apps/delete-authorization")
    @Docs("https://developer.github.com/v3/apps/oauth_applications/#delete-an-app-authorization")
    @Category("apps")
    @Subcategory("oauth-applications")
    void deleteAppAuthorization(final DeleteAppAuthorization deleteAppAuthorization);

    @DELETE
    @Path("/applications/{client_id}/token")
    @OperationId("apps/delete-token")
    @Docs("https://developer.github.com/v3/apps/oauth_applications/#delete-an-app-token")
    @Category("apps")
    @Subcategory("oauth-applications")
    void deleteAppToken(final DeleteAppToken deleteAppToken);

    @DELETE
    @Path("/app/installations/{installation_id}")
    @OperationId("apps/delete-installation")
    @Docs("https://developer.github.com/v3/apps/#delete-an-installation-for-the-authenticated-app")
    @Preview("machine-man")
    @Category("apps")
    void deleteInstallationForAuthenticatedApp(final DeleteInstallationForAuthenticatedApp deleteInstallationForAuthenticatedApp);

    @DELETE
    @Path("/app/installations/{installation_id}")
    @OperationId("apps/delete-installation")
    @Docs("https://developer.github.com/v3/apps/#delete-an-installation-for-the-authenticated-app")
    @Preview("machine-man")
    @Category("apps")
    void deleteInstallationForAuthenticatedApp(@PathParam("installation_id") final int installationId);

    @GET
    @Path("/apps/{app_slug}")
    @OperationId("apps/get-by-slug")
    @Docs("https://developer.github.com/v3/apps/#get-an-app")
    @EnabledForGithubApps
    @Preview("machine-man")
    @Category("apps")
    Integration getApp(final GetApp getApp);

    @GET
    @Path("/apps/{app_slug}")
    @OperationId("apps/get-by-slug")
    @Docs("https://developer.github.com/v3/apps/#get-an-app")
    @EnabledForGithubApps
    @Preview("machine-man")
    @Category("apps")
    Integration getApp(@PathParam("app_slug") final String appSlug);

    @GET
    @Path("/app")
    @OperationId("apps/get-authenticated")
    @Docs("https://developer.github.com/v3/apps/#get-the-authenticated-app")
    @EnabledForGithubApps
    @Preview("machine-man")
    @Category("apps")
    Integration getAuthenticatedApp();

    @GET
    @Path("/app/installations/{installation_id}")
    @OperationId("apps/get-installation")
    @Docs("https://developer.github.com/v3/apps/#get-an-installation-for-the-authenticated-app")
    @EnabledForGithubApps
    @Preview("machine-man")
    @Category("apps")
    Installation getInstallationForAuthenticatedApp(final GetInstallationForAuthenticatedApp getInstallationForAuthenticatedApp);

    @GET
    @Path("/app/installations/{installation_id}")
    @OperationId("apps/get-installation")
    @Docs("https://developer.github.com/v3/apps/#get-an-installation-for-the-authenticated-app")
    @EnabledForGithubApps
    @Preview("machine-man")
    @Category("apps")
    Installation getInstallationForAuthenticatedApp(@PathParam("installation_id") final int installationId);

    @GET
    @Path("/orgs/{org}/installation")
    @OperationId("apps/get-org-installation")
    @Docs("https://developer.github.com/v3/apps/#get-an-organization-installation-for-the-authenticated-app")
    @Preview("machine-man")
    @Category("apps")
    Installation getOrganizationInstallationForAuthenticatedApp(final GetOrganizationInstallationForAuthenticatedApp getOrganizationInstallationForAuthenticatedApp);

    @GET
    @Path("/orgs/{org}/installation")
    @OperationId("apps/get-org-installation")
    @Docs("https://developer.github.com/v3/apps/#get-an-organization-installation-for-the-authenticated-app")
    @Preview("machine-man")
    @Category("apps")
    Installation getOrganizationInstallationForAuthenticatedApp(@PathParam("org") final String org);

    @GET
    @Path("/repos/{owner}/{repo}/installation")
    @OperationId("apps/get-repo-installation")
    @Docs("https://developer.github.com/v3/apps/#get-a-repository-installation-for-the-authenticated-app")
    @Preview("machine-man")
    @Category("apps")
    Installation getRepositoryInstallationForAuthenticatedApp(final GetRepositoryInstallationForAuthenticatedApp getRepositoryInstallationForAuthenticatedApp);

    @GET
    @Path("/repos/{owner}/{repo}/installation")
    @OperationId("apps/get-repo-installation")
    @Docs("https://developer.github.com/v3/apps/#get-a-repository-installation-for-the-authenticated-app")
    @Preview("machine-man")
    @Category("apps")
    Installation getRepositoryInstallationForAuthenticatedApp(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/marketplace_listing/accounts/{account_id}")
    @OperationId("apps/get-subscription-plan-for-account")
    @Docs("https://developer.github.com/v3/apps/marketplace/#get-a-subscription-plan-for-an-account")
    @Category("apps")
    @Subcategory("marketplace")
    MarketplacePurchase getSubscriptionPlanForAccount(final GetSubscriptionPlanForAccount getSubscriptionPlanForAccount);

    @GET
    @Path("/marketplace_listing/accounts/{account_id}")
    @OperationId("apps/get-subscription-plan-for-account")
    @Docs("https://developer.github.com/v3/apps/marketplace/#get-a-subscription-plan-for-an-account")
    @Category("apps")
    @Subcategory("marketplace")
    MarketplacePurchase getSubscriptionPlanForAccount(@PathParam("account_id") final int accountId);

    @GET
    @Path("/marketplace_listing/stubbed/accounts/{account_id}")
    @OperationId("apps/get-subscription-plan-for-account-stubbed")
    @Docs("https://developer.github.com/v3/apps/marketplace/#get-a-subscription-plan-for-an-account-stubbed")
    @Category("apps")
    @Subcategory("marketplace")
    MarketplacePurchase getSubscriptionPlanForAccountStubbed(final GetSubscriptionPlanForAccountStubbed getSubscriptionPlanForAccountStubbed);

    @GET
    @Path("/marketplace_listing/stubbed/accounts/{account_id}")
    @OperationId("apps/get-subscription-plan-for-account-stubbed")
    @Docs("https://developer.github.com/v3/apps/marketplace/#get-a-subscription-plan-for-an-account-stubbed")
    @Category("apps")
    @Subcategory("marketplace")
    MarketplacePurchase getSubscriptionPlanForAccountStubbed(@PathParam("account_id") final int accountId);

    @GET
    @Path("/users/{username}/installation")
    @OperationId("apps/get-user-installation")
    @Docs("https://developer.github.com/v3/apps/#get-a-user-installation-for-the-authenticated-app")
    @Preview("machine-man")
    @Category("apps")
    Installation getUserInstallationForAuthenticatedApp(final GetUserInstallationForAuthenticatedApp getUserInstallationForAuthenticatedApp);

    @GET
    @Path("/users/{username}/installation")
    @OperationId("apps/get-user-installation")
    @Docs("https://developer.github.com/v3/apps/#get-a-user-installation-for-the-authenticated-app")
    @Preview("machine-man")
    @Category("apps")
    Installation getUserInstallationForAuthenticatedApp(@PathParam("username") final String username);

    @GET
    @Path("/marketplace_listing/plans/{plan_id}/accounts")
    @OperationId("apps/list-accounts-for-plan")
    @Docs("https://developer.github.com/v3/apps/marketplace/#list-accounts-for-a-plan")
    @Category("apps")
    @Subcategory("marketplace")
    @Paged(MarketplacePurchase[].class)
    Stream<MarketplacePurchase> listAccountsForPlan(final ListAccountsForPlan listAccountsForPlan);

    @GET
    @Path("/marketplace_listing/plans/{plan_id}/accounts")
    @OperationId("apps/list-accounts-for-plan")
    @Docs("https://developer.github.com/v3/apps/marketplace/#list-accounts-for-a-plan")
    @Category("apps")
    @Subcategory("marketplace")
    @Paged(MarketplacePurchase[].class)
    Stream<MarketplacePurchase> listAccountsForPlan(@PathParam("plan_id") final int planId);

    @GET
    @Path("/marketplace_listing/stubbed/plans/{plan_id}/accounts")
    @OperationId("apps/list-accounts-for-plan-stubbed")
    @Docs("https://developer.github.com/v3/apps/marketplace/#list-accounts-for-a-plan-stubbed")
    @Category("apps")
    @Subcategory("marketplace")
    @Paged(MarketplacePurchase[].class)
    Stream<MarketplacePurchase> listAccountsForPlanStubbed(final ListAccountsForPlanStubbed listAccountsForPlanStubbed);

    @GET
    @Path("/marketplace_listing/stubbed/plans/{plan_id}/accounts")
    @OperationId("apps/list-accounts-for-plan-stubbed")
    @Docs("https://developer.github.com/v3/apps/marketplace/#list-accounts-for-a-plan-stubbed")
    @Category("apps")
    @Subcategory("marketplace")
    @Paged(MarketplacePurchase[].class)
    Stream<MarketplacePurchase> listAccountsForPlanStubbed(@PathParam("plan_id") final int planId);

    @GET
    @Path("/user/installations")
    @OperationId("apps/list-installations-for-authenticated-user")
    @Docs("https://developer.github.com/v3/apps/installations/#list-app-installations-accessible-to-the-user-access-token")
    @Preview("machine-man")
    @Category("apps")
    @Subcategory("installations")
    @Paged(InstallationsPage.class)
    Stream<Installation> listAppInstallationsAccessibleToUserAccessToken();

    @GET
    @Path("/app/installations")
    @OperationId("apps/list-installations")
    @Docs("https://developer.github.com/v3/apps/#list-installations-for-the-authenticated-app")
    @EnabledForGithubApps
    @Preview("machine-man")
    @Category("apps")
    @Paged(Installation[].class)
    Stream<Installation> listInstallationsForAuthenticatedApp(final ListInstallationsForAuthenticatedApp listInstallationsForAuthenticatedApp);

    @GET
    @Path("/marketplace_listing/plans")
    @OperationId("apps/list-plans")
    @Docs("https://developer.github.com/v3/apps/marketplace/#list-plans")
    @Category("apps")
    @Subcategory("marketplace")
    @Paged(MarketplaceListingPlan[].class)
    Stream<MarketplaceListingPlan> listPlans();

    @GET
    @Path("/marketplace_listing/stubbed/plans")
    @OperationId("apps/list-plans-stubbed")
    @Docs("https://developer.github.com/v3/apps/marketplace/#list-plans-stubbed")
    @Category("apps")
    @Subcategory("marketplace")
    @Paged(MarketplaceListingPlan[].class)
    Stream<MarketplaceListingPlan> listPlansStubbed();

    @GET
    @Path("/installation/repositories")
    @OperationId("apps/list-repos-accessible-to-installation")
    @Docs("https://developer.github.com/v3/apps/installations/#list-repositories-accessible-to-the-app-installation")
    @EnabledForGithubApps
    @Preview("machine-man")
    @Preview("mercy")
    @Category("apps")
    @Subcategory("installations")
    @Paged(RepositoriesPage.class)
    Stream<Repository> listRepositoriesAccessibleToAppInstallation();

    @GET
    @Path("/user/installations/{installation_id}/repositories")
    @OperationId("apps/list-installation-repos-for-authenticated-user")
    @Docs("https://developer.github.com/v3/apps/installations/#list-repositories-accessible-to-the-user-access-token")
    @Preview("machine-man")
    @Preview("mercy")
    @Category("apps")
    @Subcategory("installations")
    @Paged(RepositoriesPage.class)
    Stream<Repository> listRepositoriesAccessibleToUserAccessToken(final ListRepositoriesAccessibleToUserAccessToken listRepositoriesAccessibleToUserAccessToken);

    @GET
    @Path("/user/installations/{installation_id}/repositories")
    @OperationId("apps/list-installation-repos-for-authenticated-user")
    @Docs("https://developer.github.com/v3/apps/installations/#list-repositories-accessible-to-the-user-access-token")
    @Preview("machine-man")
    @Preview("mercy")
    @Category("apps")
    @Subcategory("installations")
    @Paged(RepositoriesPage.class)
    Stream<Repository> listRepositoriesAccessibleToUserAccessToken(@PathParam("installation_id") final int installationId);

    @GET
    @Path("/user/marketplace_purchases")
    @OperationId("apps/list-subscriptions-for-authenticated-user")
    @Docs("https://developer.github.com/v3/apps/marketplace/#list-subscriptions-for-the-authenticated-user")
    @Category("apps")
    @Subcategory("marketplace")
    @Paged(UserMarketplacePurchase[].class)
    Stream<UserMarketplacePurchase> listSubscriptionsForAuthenticatedUser();

    @GET
    @Path("/user/marketplace_purchases/stubbed")
    @OperationId("apps/list-subscriptions-for-authenticated-user-stubbed")
    @Docs("https://developer.github.com/v3/apps/marketplace/#list-subscriptions-for-the-authenticated-user-stubbed")
    @Category("apps")
    @Subcategory("marketplace")
    @Paged(UserMarketplacePurchase[].class)
    Stream<UserMarketplacePurchase> listSubscriptionsForAuthenticatedUserStubbed();

    @DELETE
    @Path("/user/installations/{installation_id}/repositories/{repository_id}")
    @OperationId("apps/remove-repo-from-installation")
    @Docs("https://developer.github.com/v3/apps/installations/#remove-a-repository-from-an-app-installation")
    @Preview("machine-man")
    @Category("apps")
    @Subcategory("installations")
    void removeRepositoryFromAppInstallation(final RemoveRepositoryFromAppInstallation removeRepositoryFromAppInstallation);

    @DELETE
    @Path("/user/installations/{installation_id}/repositories/{repository_id}")
    @OperationId("apps/remove-repo-from-installation")
    @Docs("https://developer.github.com/v3/apps/installations/#remove-a-repository-from-an-app-installation")
    @Preview("machine-man")
    @Category("apps")
    @Subcategory("installations")
    void removeRepositoryFromAppInstallation(@PathParam("installation_id") final int installationId, @PathParam("repository_id") final int repositoryId);

    @POST
    @Path("/applications/{client_id}/tokens/{access_token}")
    @OperationId("apps/reset-authorization")
    @Docs("https://developer.github.com/v3/apps/oauth_applications/#reset-an-authorization")
    @RemovalDate("2021-05-05")
    @DeprecationDate("2020-02-14")
    @Category("apps")
    @Subcategory("oauth-applications")
    Authorization resetAuthorization(final ResetAuthorization resetAuthorization);

    @POST
    @Path("/applications/{client_id}/tokens/{access_token}")
    @OperationId("apps/reset-authorization")
    @Docs("https://developer.github.com/v3/apps/oauth_applications/#reset-an-authorization")
    @RemovalDate("2021-05-05")
    @DeprecationDate("2020-02-14")
    @Category("apps")
    @Subcategory("oauth-applications")
    Authorization resetAuthorization(@PathParam("client-id") final String clientId, @PathParam("access-token") final String accessToken);

    @PATCH
    @Path("/applications/{client_id}/token")
    @OperationId("apps/reset-token")
    @Docs("https://developer.github.com/v3/apps/oauth_applications/#reset-a-token")
    @Category("apps")
    @Subcategory("oauth-applications")
    Authorization resetToken(final ResetToken resetToken);

    @DELETE
    @Path("/applications/{client_id}/tokens/{access_token}")
    @OperationId("apps/revoke-authorization-for-application")
    @Docs("https://developer.github.com/v3/apps/oauth_applications/#revoke-an-authorization-for-an-application")
    @RemovalDate("2021-05-05")
    @DeprecationDate("2020-02-14")
    @Category("apps")
    @Subcategory("oauth-applications")
    void revokeAuthorizationForApplication(final RevokeAuthorizationForApplication revokeAuthorizationForApplication);

    @DELETE
    @Path("/applications/{client_id}/tokens/{access_token}")
    @OperationId("apps/revoke-authorization-for-application")
    @Docs("https://developer.github.com/v3/apps/oauth_applications/#revoke-an-authorization-for-an-application")
    @RemovalDate("2021-05-05")
    @DeprecationDate("2020-02-14")
    @Category("apps")
    @Subcategory("oauth-applications")
    void revokeAuthorizationForApplication(@PathParam("client-id") final String clientId, @PathParam("access-token") final String accessToken);

    @DELETE
    @Path("/applications/{client_id}/grants/{access_token}")
    @OperationId("apps/revoke-grant-for-application")
    @Docs("https://developer.github.com/v3/apps/oauth_applications/#revoke-a-grant-for-an-application")
    @RemovalDate("2021-05-05")
    @DeprecationDate("2020-02-14")
    @Category("apps")
    @Subcategory("oauth-applications")
    void revokeGrantForApplication(final RevokeGrantForApplication revokeGrantForApplication);

    @DELETE
    @Path("/applications/{client_id}/grants/{access_token}")
    @OperationId("apps/revoke-grant-for-application")
    @Docs("https://developer.github.com/v3/apps/oauth_applications/#revoke-a-grant-for-an-application")
    @RemovalDate("2021-05-05")
    @DeprecationDate("2020-02-14")
    @Category("apps")
    @Subcategory("oauth-applications")
    void revokeGrantForApplication(@PathParam("client-id") final String clientId, @PathParam("access-token") final String accessToken);

    @DELETE
    @Path("/installation/token")
    @OperationId("apps/revoke-installation-access-token")
    @Docs("https://developer.github.com/v3/apps/installations/#revoke-an-installation-access-token")
    @EnabledForGithubApps
    @Category("apps")
    @Subcategory("installations")
    void revokeInstallationAccessToken();

    @PUT
    @Path("/app/installations/{installation_id}/suspended")
    @OperationId("apps/suspend-installation")
    @Docs("https://developer.github.com/v3/apps/#suspend-an-app-installation")
    @Category("apps")
    void suspendAppInstallation(final SuspendAppInstallation suspendAppInstallation);

    @PUT
    @Path("/app/installations/{installation_id}/suspended")
    @OperationId("apps/suspend-installation")
    @Docs("https://developer.github.com/v3/apps/#suspend-an-app-installation")
    @Category("apps")
    void suspendAppInstallation(@PathParam("installation_id") final int installationId);

    @DELETE
    @Path("/app/installations/{installation_id}/suspended")
    @OperationId("apps/unsuspend-installation")
    @Docs("https://developer.github.com/v3/apps/#unsuspend-an-app-installation")
    @Category("apps")
    void unsuspendAppInstallation(final UnsuspendAppInstallation unsuspendAppInstallation);

    @DELETE
    @Path("/app/installations/{installation_id}/suspended")
    @OperationId("apps/unsuspend-installation")
    @Docs("https://developer.github.com/v3/apps/#unsuspend-an-app-installation")
    @Category("apps")
    void unsuspendAppInstallation(@PathParam("installation_id") final int installationId);
}
