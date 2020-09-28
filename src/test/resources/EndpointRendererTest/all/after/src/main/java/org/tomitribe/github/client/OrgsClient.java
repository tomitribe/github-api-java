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
import org.tomitribe.github.model.BlockUserFromOrganization;
import org.tomitribe.github.model.CheckIfUserIsBlockedByOrganization;
import org.tomitribe.github.model.CheckOrganizationMembershipForUser;
import org.tomitribe.github.model.CheckPublicOrganizationMembershipForUser;
import org.tomitribe.github.model.ConvertOrganizationMemberToOutsideCollaborator;
import org.tomitribe.github.model.CreateOrganizationInvitation;
import org.tomitribe.github.model.CreateOrganizationWebhook;
import org.tomitribe.github.model.CredentialAuthorization;
import org.tomitribe.github.model.DeleteOrganizationWebhook;
import org.tomitribe.github.model.GetOrganization;
import org.tomitribe.github.model.GetOrganizationMembershipForAuthenticatedUser;
import org.tomitribe.github.model.GetOrganizationMembershipForUser;
import org.tomitribe.github.model.GetOrganizationWebhook;
import org.tomitribe.github.model.Installation;
import org.tomitribe.github.model.InstallationsPage;
import org.tomitribe.github.model.ListAppInstallationsForOrganization;
import org.tomitribe.github.model.ListOrganizationInvitationTeams;
import org.tomitribe.github.model.ListOrganizationMembers;
import org.tomitribe.github.model.ListOrganizationMembershipsForAuthenticatedUser;
import org.tomitribe.github.model.ListOrganizationWebhooks;
import org.tomitribe.github.model.ListOrganizations;
import org.tomitribe.github.model.ListOrganizationsForUser;
import org.tomitribe.github.model.ListOutsideCollaboratorsForOrganization;
import org.tomitribe.github.model.ListPendingOrganizationInvitations;
import org.tomitribe.github.model.ListPublicOrganizationMembers;
import org.tomitribe.github.model.ListSAMLSSOAuthorizationsForOrganization;
import org.tomitribe.github.model.ListUsersBlockedByOrganization;
import org.tomitribe.github.model.OrgHook;
import org.tomitribe.github.model.OrgMembership;
import org.tomitribe.github.model.OrganizationFull;
import org.tomitribe.github.model.OrganizationInvitation;
import org.tomitribe.github.model.OrganizationSimple;
import org.tomitribe.github.model.PingOrganizationWebhook;
import org.tomitribe.github.model.RemoveOrganizationMember;
import org.tomitribe.github.model.RemoveOrganizationMembershipForUser;
import org.tomitribe.github.model.RemoveOutsideCollaboratorFromOrganization;
import org.tomitribe.github.model.RemovePublicOrganizationMembershipForAuthenticatedUser;
import org.tomitribe.github.model.RemoveSAMLSSOAuthorizationForOrganization;
import org.tomitribe.github.model.SetOrganizationMembershipForUser;
import org.tomitribe.github.model.SetPublicOrganizationMembershipForAuthenticatedUser;
import org.tomitribe.github.model.SimpleUser;
import org.tomitribe.github.model.Team;
import org.tomitribe.github.model.UnblockUserFromOrganization;
import org.tomitribe.github.model.UpdateOrganization;
import org.tomitribe.github.model.UpdateOrganizationMembershipForAuthenticatedUser;
import org.tomitribe.github.model.UpdateOrganizationWebhook;

public interface OrgsClient {

    @PUT
    @Path("/orgs/{org}/blocks/{username}")
    @OperationId("orgs/block-user")
    @Docs("https://developer.github.com/v3/orgs/blocking/#block-a-user-from-an-organization")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("blocking")
    void blockUserFromOrganization(final BlockUserFromOrganization blockUserFromOrganization);

    @PUT
    @Path("/orgs/{org}/blocks/{username}")
    @OperationId("orgs/block-user")
    @Docs("https://developer.github.com/v3/orgs/blocking/#block-a-user-from-an-organization")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("blocking")
    void blockUserFromOrganization(@PathParam("org") final String org, @PathParam("username") final String username);

    @GET
    @Path("/orgs/{org}/blocks/{username}")
    @OperationId("orgs/check-blocked-user")
    @Docs("https://developer.github.com/v3/orgs/blocking/#check-if-a-user-is-blocked-by-an-organization")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("blocking")
    void checkIfUserIsBlockedByOrganization(final CheckIfUserIsBlockedByOrganization checkIfUserIsBlockedByOrganization);

    @GET
    @Path("/orgs/{org}/blocks/{username}")
    @OperationId("orgs/check-blocked-user")
    @Docs("https://developer.github.com/v3/orgs/blocking/#check-if-a-user-is-blocked-by-an-organization")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("blocking")
    void checkIfUserIsBlockedByOrganization(@PathParam("org") final String org, @PathParam("username") final String username);

    @GET
    @Path("/orgs/{org}/members/{username}")
    @OperationId("orgs/check-membership-for-user")
    @Docs("https://developer.github.com/v3/orgs/members/#check-organization-membership-for-a-user")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    void checkOrganizationMembershipForUser(final CheckOrganizationMembershipForUser checkOrganizationMembershipForUser);

    @GET
    @Path("/orgs/{org}/members/{username}")
    @OperationId("orgs/check-membership-for-user")
    @Docs("https://developer.github.com/v3/orgs/members/#check-organization-membership-for-a-user")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    void checkOrganizationMembershipForUser(@PathParam("org") final String org, @PathParam("username") final String username);

    @GET
    @Path("/orgs/{org}/public_members/{username}")
    @OperationId("orgs/check-public-membership-for-user")
    @Docs("https://developer.github.com/v3/orgs/members/#check-public-organization-membership-for-a-user")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    void checkPublicOrganizationMembershipForUser(final CheckPublicOrganizationMembershipForUser checkPublicOrganizationMembershipForUser);

    @GET
    @Path("/orgs/{org}/public_members/{username}")
    @OperationId("orgs/check-public-membership-for-user")
    @Docs("https://developer.github.com/v3/orgs/members/#check-public-organization-membership-for-a-user")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    void checkPublicOrganizationMembershipForUser(@PathParam("org") final String org, @PathParam("username") final String username);

    @PUT
    @Path("/orgs/{org}/outside_collaborators/{username}")
    @OperationId("orgs/convert-member-to-outside-collaborator")
    @Docs("https://developer.github.com/v3/orgs/outside_collaborators/#convert-an-organization-member-to-outside-collaborator")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("outside-collaborators")
    void convertOrganizationMemberToOutsideCollaborator(final ConvertOrganizationMemberToOutsideCollaborator convertOrganizationMemberToOutsideCollaborator);

    @PUT
    @Path("/orgs/{org}/outside_collaborators/{username}")
    @OperationId("orgs/convert-member-to-outside-collaborator")
    @Docs("https://developer.github.com/v3/orgs/outside_collaborators/#convert-an-organization-member-to-outside-collaborator")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("outside-collaborators")
    void convertOrganizationMemberToOutsideCollaborator(@PathParam("org") final String org, @PathParam("username") final String username);

    @POST
    @Path("/orgs/{org}/invitations")
    @OperationId("orgs/create-invitation")
    @Docs("https://developer.github.com/v3/orgs/members/#create-an-organization-invitation")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    OrganizationInvitation createOrganizationInvitation(final CreateOrganizationInvitation createOrganizationInvitation);

    @POST
    @Path("/orgs/{org}/hooks")
    @OperationId("orgs/create-webhook")
    @Docs("https://developer.github.com/v3/orgs/hooks/#create-an-organization-webhook")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("hooks")
    OrgHook createOrganizationWebhook(final CreateOrganizationWebhook createOrganizationWebhook);

    @DELETE
    @Path("/orgs/{org}/hooks/{hook_id}")
    @OperationId("orgs/delete-webhook")
    @Docs("https://developer.github.com/v3/orgs/hooks/#delete-an-organization-webhook")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("hooks")
    void deleteOrganizationWebhook(final DeleteOrganizationWebhook deleteOrganizationWebhook);

    @DELETE
    @Path("/orgs/{org}/hooks/{hook_id}")
    @OperationId("orgs/delete-webhook")
    @Docs("https://developer.github.com/v3/orgs/hooks/#delete-an-organization-webhook")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("hooks")
    void deleteOrganizationWebhook(@PathParam("org") final String org, @PathParam("hook-id") final int hookId);

    @GET
    @Path("/orgs/{org}")
    @OperationId("orgs/get")
    @Docs("https://developer.github.com/v3/orgs/#get-an-organization")
    @EnabledForGithubApps
    @Preview("surtur")
    @Category("orgs")
    OrganizationFull getOrganization(final GetOrganization getOrganization);

    @GET
    @Path("/orgs/{org}")
    @OperationId("orgs/get")
    @Docs("https://developer.github.com/v3/orgs/#get-an-organization")
    @EnabledForGithubApps
    @Preview("surtur")
    @Category("orgs")
    OrganizationFull getOrganization(@PathParam("org") final String org);

    @GET
    @Path("/user/memberships/orgs/{org}")
    @OperationId("orgs/get-membership-for-authenticated-user")
    @Docs("https://developer.github.com/v3/orgs/members/#get-an-organization-membership-for-the-authenticated-user")
    @Category("orgs")
    @Subcategory("members")
    OrgMembership getOrganizationMembershipForAuthenticatedUser(final GetOrganizationMembershipForAuthenticatedUser getOrganizationMembershipForAuthenticatedUser);

    @GET
    @Path("/user/memberships/orgs/{org}")
    @OperationId("orgs/get-membership-for-authenticated-user")
    @Docs("https://developer.github.com/v3/orgs/members/#get-an-organization-membership-for-the-authenticated-user")
    @Category("orgs")
    @Subcategory("members")
    OrgMembership getOrganizationMembershipForAuthenticatedUser(@PathParam("org") final String org);

    @GET
    @Path("/orgs/{org}/memberships/{username}")
    @OperationId("orgs/get-membership-for-user")
    @Docs("https://developer.github.com/v3/orgs/members/#get-organization-membership-for-a-user")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    OrgMembership getOrganizationMembershipForUser(final GetOrganizationMembershipForUser getOrganizationMembershipForUser);

    @GET
    @Path("/orgs/{org}/memberships/{username}")
    @OperationId("orgs/get-membership-for-user")
    @Docs("https://developer.github.com/v3/orgs/members/#get-organization-membership-for-a-user")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    OrgMembership getOrganizationMembershipForUser(@PathParam("org") final String org, @PathParam("username") final String username);

    @GET
    @Path("/orgs/{org}/hooks/{hook_id}")
    @OperationId("orgs/get-webhook")
    @Docs("https://developer.github.com/v3/orgs/hooks/#get-an-organization-webhook")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("hooks")
    OrgHook getOrganizationWebhook(final GetOrganizationWebhook getOrganizationWebhook);

    @GET
    @Path("/orgs/{org}/hooks/{hook_id}")
    @OperationId("orgs/get-webhook")
    @Docs("https://developer.github.com/v3/orgs/hooks/#get-an-organization-webhook")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("hooks")
    OrgHook getOrganizationWebhook(@PathParam("org") final String org, @PathParam("hook-id") final int hookId);

    @GET
    @Path("/orgs/{org}/installations")
    @OperationId("orgs/list-app-installations")
    @Docs("https://developer.github.com/v3/orgs/#list-app-installations-for-an-organization")
    @EnabledForGithubApps
    @Preview("machine-man")
    @Category("orgs")
    @Paged(InstallationsPage.class)
    Stream<Installation> listAppInstallationsForOrganization(final ListAppInstallationsForOrganization listAppInstallationsForOrganization);

    @GET
    @Path("/orgs/{org}/installations")
    @OperationId("orgs/list-app-installations")
    @Docs("https://developer.github.com/v3/orgs/#list-app-installations-for-an-organization")
    @EnabledForGithubApps
    @Preview("machine-man")
    @Category("orgs")
    @Paged(InstallationsPage.class)
    Stream<Installation> listAppInstallationsForOrganization(@PathParam("org") final String org);

    @GET
    @Path("/orgs/{org}/invitations/{invitation_id}/teams")
    @OperationId("orgs/list-invitation-teams")
    @Docs("https://developer.github.com/v3/orgs/members/#list-organization-invitation-teams")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    @Paged(Team[].class)
    Stream<Team> listOrganizationInvitationTeams(final ListOrganizationInvitationTeams listOrganizationInvitationTeams);

    @GET
    @Path("/orgs/{org}/invitations/{invitation_id}/teams")
    @OperationId("orgs/list-invitation-teams")
    @Docs("https://developer.github.com/v3/orgs/members/#list-organization-invitation-teams")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    @Paged(Team[].class)
    Stream<Team> listOrganizationInvitationTeams(@PathParam("org") final String org, @PathParam("invitation_id") final int invitationId);

    @GET
    @Path("/orgs/{org}/members")
    @OperationId("orgs/list-members")
    @Docs("https://developer.github.com/v3/orgs/members/#list-organization-members")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    @Paged(SimpleUser[].class)
    Stream<SimpleUser> listOrganizationMembers(final ListOrganizationMembers listOrganizationMembers);

    @GET
    @Path("/orgs/{org}/members")
    @OperationId("orgs/list-members")
    @Docs("https://developer.github.com/v3/orgs/members/#list-organization-members")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    @Paged(SimpleUser[].class)
    Stream<SimpleUser> listOrganizationMembers(@PathParam("org") final String org);

    @GET
    @Path("/user/memberships/orgs")
    @OperationId("orgs/list-memberships-for-authenticated-user")
    @Docs("https://developer.github.com/v3/orgs/members/#list-organization-memberships-for-the-authenticated-user")
    @Category("orgs")
    @Subcategory("members")
    @Paged(OrgMembership[].class)
    Stream<OrgMembership> listOrganizationMembershipsForAuthenticatedUser(final ListOrganizationMembershipsForAuthenticatedUser listOrganizationMembershipsForAuthenticatedUser);

    @GET
    @Path("/orgs/{org}/hooks")
    @OperationId("orgs/list-webhooks")
    @Docs("https://developer.github.com/v3/orgs/hooks/#list-organization-webhooks")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("hooks")
    @Paged(OrgHook[].class)
    Stream<OrgHook> listOrganizationWebhooks(final ListOrganizationWebhooks listOrganizationWebhooks);

    @GET
    @Path("/orgs/{org}/hooks")
    @OperationId("orgs/list-webhooks")
    @Docs("https://developer.github.com/v3/orgs/hooks/#list-organization-webhooks")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("hooks")
    @Paged(OrgHook[].class)
    Stream<OrgHook> listOrganizationWebhooks(@PathParam("org") final String org);

    @GET
    @Path("/organizations")
    @OperationId("orgs/list")
    @Docs("https://developer.github.com/v3/orgs/#list-organizations")
    @EnabledForGithubApps
    @Category("orgs")
    @Paged(OrganizationSimple[].class)
    Stream<OrganizationSimple> listOrganizations(final ListOrganizations listOrganizations);

    @GET
    @Path("/user/orgs")
    @OperationId("orgs/list-for-authenticated-user")
    @Docs("https://developer.github.com/v3/orgs/#list-organizations-for-the-authenticated-user")
    @Category("orgs")
    @Paged(OrganizationSimple[].class)
    Stream<OrganizationSimple> listOrganizationsForAuthenticatedUser();

    @GET
    @Path("/users/{username}/orgs")
    @OperationId("orgs/list-for-user")
    @Docs("https://developer.github.com/v3/orgs/#list-organizations-for-a-user")
    @EnabledForGithubApps
    @Category("orgs")
    @Paged(OrganizationSimple[].class)
    Stream<OrganizationSimple> listOrganizationsForUser(final ListOrganizationsForUser listOrganizationsForUser);

    @GET
    @Path("/users/{username}/orgs")
    @OperationId("orgs/list-for-user")
    @Docs("https://developer.github.com/v3/orgs/#list-organizations-for-a-user")
    @EnabledForGithubApps
    @Category("orgs")
    @Paged(OrganizationSimple[].class)
    Stream<OrganizationSimple> listOrganizationsForUser(@PathParam("username") final String username);

    @GET
    @Path("/orgs/{org}/outside_collaborators")
    @OperationId("orgs/list-outside-collaborators")
    @Docs("https://developer.github.com/v3/orgs/outside_collaborators/#list-outside-collaborators-for-an-organization")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("outside-collaborators")
    @Paged(SimpleUser[].class)
    Stream<SimpleUser> listOutsideCollaboratorsForOrganization(final ListOutsideCollaboratorsForOrganization listOutsideCollaboratorsForOrganization);

    @GET
    @Path("/orgs/{org}/outside_collaborators")
    @OperationId("orgs/list-outside-collaborators")
    @Docs("https://developer.github.com/v3/orgs/outside_collaborators/#list-outside-collaborators-for-an-organization")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("outside-collaborators")
    @Paged(SimpleUser[].class)
    Stream<SimpleUser> listOutsideCollaboratorsForOrganization(@PathParam("org") final String org);

    @GET
    @Path("/orgs/{org}/invitations")
    @OperationId("orgs/list-pending-invitations")
    @Docs("https://developer.github.com/v3/orgs/members/#list-pending-organization-invitations")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    @Paged(OrganizationInvitation[].class)
    Stream<OrganizationInvitation> listPendingOrganizationInvitations(final ListPendingOrganizationInvitations listPendingOrganizationInvitations);

    @GET
    @Path("/orgs/{org}/invitations")
    @OperationId("orgs/list-pending-invitations")
    @Docs("https://developer.github.com/v3/orgs/members/#list-pending-organization-invitations")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    @Paged(OrganizationInvitation[].class)
    Stream<OrganizationInvitation> listPendingOrganizationInvitations(@PathParam("org") final String org);

    @GET
    @Path("/orgs/{org}/public_members")
    @OperationId("orgs/list-public-members")
    @Docs("https://developer.github.com/v3/orgs/members/#list-public-organization-members")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    @Paged(SimpleUser[].class)
    Stream<SimpleUser> listPublicOrganizationMembers(final ListPublicOrganizationMembers listPublicOrganizationMembers);

    @GET
    @Path("/orgs/{org}/public_members")
    @OperationId("orgs/list-public-members")
    @Docs("https://developer.github.com/v3/orgs/members/#list-public-organization-members")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    @Paged(SimpleUser[].class)
    Stream<SimpleUser> listPublicOrganizationMembers(@PathParam("org") final String org);

    @GET
    @Path("/orgs/{org}/credential-authorizations")
    @OperationId("orgs/list-saml-sso-authorizations")
    @Docs("https://developer.github.com/v3/orgs/#list-saml-sso-authorizations-for-an-organization")
    @GithubCloudOnly
    @EnabledForGithubApps
    @Category("orgs")
    Stream<CredentialAuthorization> listSAMLSSOAuthorizationsForOrganization(final ListSAMLSSOAuthorizationsForOrganization listSAMLSSOAuthorizationsForOrganization);

    @GET
    @Path("/orgs/{org}/credential-authorizations")
    @OperationId("orgs/list-saml-sso-authorizations")
    @Docs("https://developer.github.com/v3/orgs/#list-saml-sso-authorizations-for-an-organization")
    @GithubCloudOnly
    @EnabledForGithubApps
    @Category("orgs")
    Stream<CredentialAuthorization> listSAMLSSOAuthorizationsForOrganization(@PathParam("org") final String org);

    @GET
    @Path("/orgs/{org}/blocks")
    @OperationId("orgs/list-blocked-users")
    @Docs("https://developer.github.com/v3/orgs/blocking/#list-users-blocked-by-an-organization")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("blocking")
    Stream<SimpleUser> listUsersBlockedByOrganization(final ListUsersBlockedByOrganization listUsersBlockedByOrganization);

    @GET
    @Path("/orgs/{org}/blocks")
    @OperationId("orgs/list-blocked-users")
    @Docs("https://developer.github.com/v3/orgs/blocking/#list-users-blocked-by-an-organization")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("blocking")
    Stream<SimpleUser> listUsersBlockedByOrganization(@PathParam("org") final String org);

    @POST
    @Path("/orgs/{org}/hooks/{hook_id}/pings")
    @OperationId("orgs/ping-webhook")
    @Docs("https://developer.github.com/v3/orgs/hooks/#ping-an-organization-webhook")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("hooks")
    void pingOrganizationWebhook(final PingOrganizationWebhook pingOrganizationWebhook);

    @POST
    @Path("/orgs/{org}/hooks/{hook_id}/pings")
    @OperationId("orgs/ping-webhook")
    @Docs("https://developer.github.com/v3/orgs/hooks/#ping-an-organization-webhook")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("hooks")
    void pingOrganizationWebhook(@PathParam("org") final String org, @PathParam("hook-id") final int hookId);

    @DELETE
    @Path("/orgs/{org}/members/{username}")
    @OperationId("orgs/remove-member")
    @Docs("https://developer.github.com/v3/orgs/members/#remove-an-organization-member")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    void removeOrganizationMember(final RemoveOrganizationMember removeOrganizationMember);

    @DELETE
    @Path("/orgs/{org}/members/{username}")
    @OperationId("orgs/remove-member")
    @Docs("https://developer.github.com/v3/orgs/members/#remove-an-organization-member")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    void removeOrganizationMember(@PathParam("org") final String org, @PathParam("username") final String username);

    @DELETE
    @Path("/orgs/{org}/memberships/{username}")
    @OperationId("orgs/remove-membership-for-user")
    @Docs("https://developer.github.com/v3/orgs/members/#remove-organization-membership-for-a-user")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    void removeOrganizationMembershipForUser(final RemoveOrganizationMembershipForUser removeOrganizationMembershipForUser);

    @DELETE
    @Path("/orgs/{org}/memberships/{username}")
    @OperationId("orgs/remove-membership-for-user")
    @Docs("https://developer.github.com/v3/orgs/members/#remove-organization-membership-for-a-user")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    void removeOrganizationMembershipForUser(@PathParam("org") final String org, @PathParam("username") final String username);

    @DELETE
    @Path("/orgs/{org}/outside_collaborators/{username}")
    @OperationId("orgs/remove-outside-collaborator")
    @Docs("https://developer.github.com/v3/orgs/outside_collaborators/#remove-outside-collaborator-from-an-organization")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("outside-collaborators")
    void removeOutsideCollaboratorFromOrganization(final RemoveOutsideCollaboratorFromOrganization removeOutsideCollaboratorFromOrganization);

    @DELETE
    @Path("/orgs/{org}/outside_collaborators/{username}")
    @OperationId("orgs/remove-outside-collaborator")
    @Docs("https://developer.github.com/v3/orgs/outside_collaborators/#remove-outside-collaborator-from-an-organization")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("outside-collaborators")
    void removeOutsideCollaboratorFromOrganization(@PathParam("org") final String org, @PathParam("username") final String username);

    @DELETE
    @Path("/orgs/{org}/public_members/{username}")
    @OperationId("orgs/remove-public-membership-for-authenticated-user")
    @Docs("https://developer.github.com/v3/orgs/members/#remove-public-organization-membership-for-the-authenticated-user")
    @Category("orgs")
    @Subcategory("members")
    void removePublicOrganizationMembershipForAuthenticatedUser(final RemovePublicOrganizationMembershipForAuthenticatedUser removePublicOrganizationMembershipForAuthenticatedUser);

    @DELETE
    @Path("/orgs/{org}/public_members/{username}")
    @OperationId("orgs/remove-public-membership-for-authenticated-user")
    @Docs("https://developer.github.com/v3/orgs/members/#remove-public-organization-membership-for-the-authenticated-user")
    @Category("orgs")
    @Subcategory("members")
    void removePublicOrganizationMembershipForAuthenticatedUser(@PathParam("org") final String org, @PathParam("username") final String username);

    @DELETE
    @Path("/orgs/{org}/credential-authorizations/{credential_id}")
    @OperationId("orgs/remove-saml-sso-authorization")
    @Docs("https://developer.github.com/v3/orgs/#remove-a-saml-sso-authorization-for-an-organization")
    @GithubCloudOnly
    @EnabledForGithubApps
    @Category("orgs")
    void removeSAMLSSOAuthorizationForOrganization(final RemoveSAMLSSOAuthorizationForOrganization removeSAMLSSOAuthorizationForOrganization);

    @DELETE
    @Path("/orgs/{org}/credential-authorizations/{credential_id}")
    @OperationId("orgs/remove-saml-sso-authorization")
    @Docs("https://developer.github.com/v3/orgs/#remove-a-saml-sso-authorization-for-an-organization")
    @GithubCloudOnly
    @EnabledForGithubApps
    @Category("orgs")
    void removeSAMLSSOAuthorizationForOrganization(@PathParam("org") final String org, @PathParam("credential_id") final int credentialId);

    @PUT
    @Path("/orgs/{org}/memberships/{username}")
    @OperationId("orgs/set-membership-for-user")
    @Docs("https://developer.github.com/v3/orgs/members/#set-organization-membership-for-a-user")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("members")
    OrgMembership setOrganizationMembershipForUser(final SetOrganizationMembershipForUser setOrganizationMembershipForUser);

    @PUT
    @Path("/orgs/{org}/public_members/{username}")
    @OperationId("orgs/set-public-membership-for-authenticated-user")
    @Docs("https://developer.github.com/v3/orgs/members/#set-public-organization-membership-for-the-authenticated-user")
    @Category("orgs")
    @Subcategory("members")
    void setPublicOrganizationMembershipForAuthenticatedUser(final SetPublicOrganizationMembershipForAuthenticatedUser setPublicOrganizationMembershipForAuthenticatedUser);

    @PUT
    @Path("/orgs/{org}/public_members/{username}")
    @OperationId("orgs/set-public-membership-for-authenticated-user")
    @Docs("https://developer.github.com/v3/orgs/members/#set-public-organization-membership-for-the-authenticated-user")
    @Category("orgs")
    @Subcategory("members")
    void setPublicOrganizationMembershipForAuthenticatedUser(@PathParam("org") final String org, @PathParam("username") final String username);

    @DELETE
    @Path("/orgs/{org}/blocks/{username}")
    @OperationId("orgs/unblock-user")
    @Docs("https://developer.github.com/v3/orgs/blocking/#unblock-a-user-from-an-organization")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("blocking")
    void unblockUserFromOrganization(final UnblockUserFromOrganization unblockUserFromOrganization);

    @DELETE
    @Path("/orgs/{org}/blocks/{username}")
    @OperationId("orgs/unblock-user")
    @Docs("https://developer.github.com/v3/orgs/blocking/#unblock-a-user-from-an-organization")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("blocking")
    void unblockUserFromOrganization(@PathParam("org") final String org, @PathParam("username") final String username);

    @PATCH
    @Path("/orgs/{org}")
    @OperationId("orgs/update")
    @Docs("https://developer.github.com/v3/orgs/#update-an-organization")
    @EnabledForGithubApps
    @Preview("surtur")
    @Category("orgs")
    OrganizationFull updateOrganization(final UpdateOrganization updateOrganization);

    @PATCH
    @Path("/user/memberships/orgs/{org}")
    @OperationId("orgs/update-membership-for-authenticated-user")
    @Docs("https://developer.github.com/v3/orgs/members/#update-an-organization-membership-for-the-authenticated-user")
    @Category("orgs")
    @Subcategory("members")
    OrgMembership updateOrganizationMembershipForAuthenticatedUser(final UpdateOrganizationMembershipForAuthenticatedUser updateOrganizationMembershipForAuthenticatedUser);

    @PATCH
    @Path("/orgs/{org}/hooks/{hook_id}")
    @OperationId("orgs/update-webhook")
    @Docs("https://developer.github.com/v3/orgs/hooks/#update-an-organization-webhook")
    @EnabledForGithubApps
    @Category("orgs")
    @Subcategory("hooks")
    OrgHook updateOrganizationWebhook(final UpdateOrganizationWebhook updateOrganizationWebhook);
}
