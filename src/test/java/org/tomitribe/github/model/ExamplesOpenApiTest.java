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

import org.junit.Test;
import org.tomitribe.github.JsonAsserts;
import org.tomitribe.github.Resources;
import org.tomitribe.github.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExamplesOpenApiTest {

    @Test
    public void authorizationWithUser() throws IOException {
        assertExample(HashMap.class, "authorization-with-user.json");
    }

    @Test
    public void fileCommitExampleForUpdatingAFile() throws IOException {
        assertExample(HashMap.class, "file-commit-example-for-updating-a-file.json");
    }

    @Test
    public void actionsSecret() throws IOException {
        assertExample(HashMap.class, "actions-secret.json");
    }

    @Test
    public void gitRef() throws IOException {
        assertExample(HashMap.class, "git-ref.json");
    }

    @Test
    public void codeOfConductSimpleItems() throws IOException {
        assertExample(ArrayList.class, "code-of-conduct-simple-items.json");
    }

    @Test
    public void publicRepositoryPaginated() throws IOException {
        assertExample(HashMap.class, "public-repository-paginated.json");
    }

    @Test
    public void simplePullRequestReviewRequest() throws IOException {
        assertExample(HashMap.class, "simple-pull-request-review-request.json");
    }

    @Test
    public void organizationActionsSecretPaginated() throws IOException {
        assertExample(HashMap.class, "organization-actions-secret-paginated.json");
    }

    @Test
    public void reviewCommentItems() throws IOException {
        assertExample(ArrayList.class, "review-comment-items.json");
    }

    @Test
    public void projectColumnItems() throws IOException {
        assertExample(ArrayList.class, "project-column-items.json");
    }

    @Test
    public void hovercard() throws IOException {
        assertExample(HashMap.class, "hovercard.json");
    }

    @Test
    public void baseInstallationForAuthUserPaginated() throws IOException {
        assertExample(HashMap.class, "base-installation-for-auth-user-paginated.json");
    }

    @Test
    public void pullRequestMergeResultResponseIfMergeWasSuccessful() throws IOException {
        assertExample(HashMap.class, "pull-request-merge-result-response-if-merge-was-successful.json");
    }

    @Test
    public void pullRequestReview4() throws IOException {
        assertExample(HashMap.class, "pull-request-review-4.json");
    }

    @Test
    public void branchRestrictionPolicy() throws IOException {
        assertExample(HashMap.class, "branch-restriction-policy.json");
    }

    @Test
    public void artifactPaginated() throws IOException {
        assertExample(HashMap.class, "artifact-paginated.json");
    }

    @Test
    public void milestoneItems() throws IOException {
        assertExample(ArrayList.class, "milestone-items.json");
    }

    @Test
    public void workflow() throws IOException {
        assertExample(HashMap.class, "workflow.json");
    }

    @Test
    public void migration2() throws IOException {
        assertExample(HashMap.class, "migration-2.json");
    }

    @Test
    public void projectCardItems() throws IOException {
        assertExample(ArrayList.class, "project-card-items.json");
    }

    @Test
    public void repositoryInvitationResponseWhenANewInvitationIsCreated() throws IOException {
        assertExample(HashMap.class, "repository-invitation-response-when-a-new-invitation-is-created.json");
    }

    @Test
    public void pageBuildItems() throws IOException {
        assertExample(ArrayList.class, "page-build-items.json");
    }

    @Test
    public void jobPaginated() throws IOException {
        assertExample(HashMap.class, "job-paginated.json");
    }

    @Test
    public void checkSuitePreference() throws IOException {
        assertExample(HashMap.class, "check-suite-preference.json");
    }

    @Test
    public void emailItems2() throws IOException {
        assertExample(ArrayList.class, "email-items-2.json");
    }

    @Test
    public void teamDiscussionCommentItems() throws IOException {
        assertExample(ArrayList.class, "team-discussion-comment-items.json");
    }

    @Test
    public void actionsPublicKey() throws IOException {
        assertExample(HashMap.class, "actions-public-key.json");
    }

    @Test
    public void organizationSimpleItems() throws IOException {
        assertExample(ArrayList.class, "organization-simple-items.json");
    }

    @Test
    public void codeSearchResultItemPaginated() throws IOException {
        assertExample(HashMap.class, "code-search-result-item-paginated.json");
    }

    @Test
    public void stargazerItemsAlternativeResponseWithStarCreationTimestamps() throws IOException {
        assertExample(ArrayList.class, "stargazer-items-alternative-response-with-star-creation-timestamps.json");
    }

    @Test
    public void repositoryCollaboratorPermission() throws IOException {
        assertExample(HashMap.class, "repository-collaborator-permission.json");
    }

    @Test
    public void project2() throws IOException {
        assertExample(HashMap.class, "project-2.json");
    }

    @Test
    public void hook() throws IOException {
        assertExample(HashMap.class, "hook.json");
    }

    @Test
    public void deploymentAdvancedExample() throws IOException {
        assertExample(HashMap.class, "deployment-advanced-example.json");
    }

    @Test
    public void repositorySubscription() throws IOException {
        assertExample(HashMap.class, "repository-subscription.json");
    }

    @Test
    public void gist3() throws IOException {
        assertExample(HashMap.class, "gist-3.json");
    }

    @Test
    public void importExample2() throws IOException {
        assertExample(HashMap.class, "import-example-2.json");
    }

    @Test
    public void fullRepository() throws IOException {
        assertExample(HashMap.class, "full-repository.json");
    }

    @Test
    public void simpleUserItemsDefaultResponse() throws IOException {
        assertExample(ArrayList.class, "simple-user-items-default-response.json");
    }

    @Test
    public void gist2() throws IOException {
        assertExample(HashMap.class, "gist-2.json");
    }

    @Test
    public void installation() throws IOException {
        assertExample(HashMap.class, "installation.json");
    }

    @Test
    public void authenticationToken() throws IOException {
        assertExample(HashMap.class, "authentication-token.json");
    }

    @Test
    public void thread() throws IOException {
        assertExample(HashMap.class, "thread.json");
    }

    @Test
    public void baseInstallation() throws IOException {
        assertExample(HashMap.class, "base-installation.json");
    }

    @Test
    public void project3() throws IOException {
        assertExample(HashMap.class, "project-3.json");
    }

    @Test
    public void orgMembershipResponseIfUserWasPreviouslyUnaffiliatedWithOrganization() throws IOException {
        assertExample(HashMap.class, "org-membership-response-if-user-was-previously-unaffiliated-with-organization.json");
    }

    @Test
    public void pullRequestReviewComment2() throws IOException {
        assertExample(HashMap.class, "pull-request-review-comment-2.json");
    }

    @Test
    public void milestone() throws IOException {
        assertExample(HashMap.class, "milestone.json");
    }

    @Test
    public void workflowRun() throws IOException {
        assertExample(HashMap.class, "workflow-run.json");
    }

    @Test
    public void issueSimpleItems() throws IOException {
        assertExample(ArrayList.class, "issue-simple-items.json");
    }

    @Test
    public void emailItems3() throws IOException {
        assertExample(ArrayList.class, "email-items-3.json");
    }

    @Test
    public void gistComment() throws IOException {
        assertExample(HashMap.class, "gist-comment.json");
    }

    @Test
    public void contributorItemsResponseIfRepositoryContainsContent() throws IOException {
        assertExample(ArrayList.class, "contributor-items-response-if-repository-contains-content.json");
    }

    @Test
    public void gistCommitItems() throws IOException {
        assertExample(ArrayList.class, "gist-commit-items.json");
    }

    @Test
    public void gitignoreTemplate() throws IOException {
        assertExample(HashMap.class, "gitignore-template.json");
    }

    @Test
    public void combinedBillingUsage() throws IOException {
        assertExample(HashMap.class, "combined-billing-usage.json");
    }

    @Test
    public void organizationInvitation() throws IOException {
        assertExample(HashMap.class, "organization-invitation.json");
    }

    @Test
    public void language() throws IOException {
        assertExample(HashMap.class, "language.json");
    }

    @Test
    public void orgMembershipItems() throws IOException {
        assertExample(ArrayList.class, "org-membership-items.json");
    }

    @Test
    public void branchProtection() throws IOException {
        assertExample(HashMap.class, "branch-protection.json");
    }

    @Test
    public void repositoryCollaboratorPermissionResponseIfUserHasAdminPermissions() throws IOException {
        assertExample(HashMap.class, "repository-collaborator-permission-response-if-user-has-admin-permissions.json");
    }

    @Test
    public void protectedBranchPullRequestReview() throws IOException {
        assertExample(HashMap.class, "protected-branch-pull-request-review.json");
    }

    @Test
    public void gistForkItems() throws IOException {
        assertExample(ArrayList.class, "gist-fork-items.json");
    }

    @Test
    public void releaseItems() throws IOException {
        assertExample(ArrayList.class, "release-items.json");
    }

    @Test
    public void publicUserDefaultResponse() throws IOException {
        assertExample(HashMap.class, "public-user-default-response.json");
    }

    @Test
    public void interactionLimit() throws IOException {
        assertExample(HashMap.class, "interaction-limit.json");
    }

    @Test
    public void pullRequestReview5() throws IOException {
        assertExample(HashMap.class, "pull-request-review-5.json");
    }

    @Test
    public void codeFrequencyStatItems2() throws IOException {
        assertExample(ArrayList.class, "code-frequency-stat-items-2.json");
    }

    @Test
    public void deployment() throws IOException {
        assertExample(HashMap.class, "deployment.json");
    }

    @Test
    public void gitCommit2() throws IOException {
        assertExample(HashMap.class, "git-commit-2.json");
    }

    @Test
    public void gitRefItems() throws IOException {
        assertExample(ArrayList.class, "git-ref-items.json");
    }

    @Test
    public void issueCommentItems() throws IOException {
        assertExample(ArrayList.class, "issue-comment-items.json");
    }

    @Test
    public void label2() throws IOException {
        assertExample(HashMap.class, "label-2.json");
    }

    @Test
    public void organizationFullResponseWithGitHubPlanInformation() throws IOException {
        assertExample(HashMap.class, "organization-full-response-with-git-hub-plan-information.json");
    }

    @Test
    public void key() throws IOException {
        assertExample(HashMap.class, "key.json");
    }

    @Test
    public void commitCommentItems() throws IOException {
        assertExample(ArrayList.class, "commit-comment-items.json");
    }

    @Test
    public void shortBranchWithProtectionItems() throws IOException {
        assertExample(ArrayList.class, "short-branch-with-protection-items.json");
    }

    @Test
    public void orgMembershipResponseIfUserAlreadyHadMembershipWithOrganization() throws IOException {
        assertExample(HashMap.class, "org-membership-response-if-user-already-had-membership-with-organization.json");
    }

    @Test
    public void pullRequestReviewCommentExampleForAMultiLineComment() throws IOException {
        assertExample(HashMap.class, "pull-request-review-comment-example-for-a-multi-line-comment.json");
    }

    @Test
    public void workflowRunPaginated() throws IOException {
        assertExample(HashMap.class, "workflow-run-paginated.json");
    }

    @Test
    public void viewTraffic() throws IOException {
        assertExample(HashMap.class, "view-traffic.json");
    }

    @Test
    public void project() throws IOException {
        assertExample(HashMap.class, "project.json");
    }

    @Test
    public void repository3() throws IOException {
        assertExample(HashMap.class, "repository-3.json");
    }

    @Test
    public void tagItems() throws IOException {
        assertExample(ArrayList.class, "tag-items.json");
    }

    @Test
    public void pullRequestReviewItems() throws IOException {
        assertExample(ArrayList.class, "pull-request-review-items.json");
    }

    @Test
    public void pullRequest() throws IOException {
        assertExample(HashMap.class, "pull-request.json");
    }

    @Test
    public void contentReferenceAttachment() throws IOException {
        assertExample(HashMap.class, "content-reference-attachment.json");
    }

    @Test
    public void deploymentSimpleExample() throws IOException {
        assertExample(HashMap.class, "deployment-simple-example.json");
    }

    @Test
    public void installationToken() throws IOException {
        assertExample(HashMap.class, "installation-token.json");
    }

    @Test
    public void topic() throws IOException {
        assertExample(HashMap.class, "topic.json");
    }

    @Test
    public void projectItems3() throws IOException {
        assertExample(ArrayList.class, "project-items-3.json");
    }

    @Test
    public void baseInstallationItems() throws IOException {
        assertExample(ArrayList.class, "base-installation-items.json");
    }

    @Test
    public void simpleCommitItems() throws IOException {
        assertExample(ArrayList.class, "simple-commit-items.json");
    }

    @Test
    public void authorizationResponseIfReturningAnExistingToken2() throws IOException {
        assertExample(HashMap.class, "authorization-response-if-returning-an-existing-token-2.json");
    }

    @Test
    public void teamMembershipResponseIfUserIsATeamMaintainer() throws IOException {
        assertExample(HashMap.class, "team-membership-response-if-user-is-a-team-maintainer.json");
    }

    @Test
    public void repoSearchResultItemPaginated() throws IOException {
        assertExample(HashMap.class, "repo-search-result-item-paginated.json");
    }

    @Test
    public void installationPaginated() throws IOException {
        assertExample(HashMap.class, "installation-paginated.json");
    }

    @Test
    public void orgMembership() throws IOException {
        assertExample(HashMap.class, "org-membership.json");
    }

    @Test
    public void checkRun() throws IOException {
        assertExample(HashMap.class, "check-run.json");
    }

    @Test
    public void checkRunPaginated() throws IOException {
        assertExample(HashMap.class, "check-run-paginated.json");
    }

    @Test
    public void teamDiscussion() throws IOException {
        assertExample(HashMap.class, "team-discussion.json");
    }

    @Test
    public void blob() throws IOException {
        assertExample(HashMap.class, "blob.json");
    }

    @Test
    public void threadSubscription() throws IOException {
        assertExample(HashMap.class, "thread-subscription.json");
    }

    @Test
    public void issueWithRepoItems() throws IOException {
        assertExample(ArrayList.class, "issue-with-repo-items.json");
    }

    @Test
    public void credentialAuthorizationItems() throws IOException {
        assertExample(ArrayList.class, "credential-authorization-items.json");
    }

    @Test
    public void migrationWithShortOrg2() throws IOException {
        assertExample(HashMap.class, "migration-with-short-org-2.json");
    }

    @Test
    public void groupMapping3() throws IOException {
        assertExample(HashMap.class, "group-mapping-3.json");
    }

    @Test
    public void teamFullItems() throws IOException {
        assertExample(ArrayList.class, "team-full-items.json");
    }

    @Test
    public void teamProject() throws IOException {
        assertExample(HashMap.class, "team-project.json");
    }

    @Test
    public void scimUserListResponseWithoutFilter() throws IOException {
        assertExample(HashMap.class, "scim-user-list-response-without-filter.json");
    }

    @Test
    public void porterAuthor() throws IOException {
        assertExample(HashMap.class, "porter-author.json");
    }

    @Test
    public void branchWithProtection() throws IOException {
        assertExample(HashMap.class, "branch-with-protection.json");
    }

    @Test
    public void issueEventForIssueItems() throws IOException {
        assertExample(ArrayList.class, "issue-event-for-issue-items.json");
    }

    @Test
    public void combinedCommitStatus() throws IOException {
        assertExample(HashMap.class, "combined-commit-status.json");
    }

    @Test
    public void integrationFromManifest() throws IOException {
        assertExample(HashMap.class, "integration-from-manifest.json");
    }

    @Test
    public void migration() throws IOException {
        assertExample(HashMap.class, "migration.json");
    }

    @Test
    public void teamRepositoryAlternativeResponseWithExtraRepositoryInformation() throws IOException {
        assertExample(HashMap.class, "team-repository-alternative-response-with-extra-repository-information.json");
    }

    @Test
    public void deployKey() throws IOException {
        assertExample(HashMap.class, "deploy-key.json");
    }

    @Test
    public void issue() throws IOException {
        assertExample(HashMap.class, "issue.json");
    }

    @Test
    public void userSearchResultItemPaginated() throws IOException {
        assertExample(HashMap.class, "user-search-result-item-paginated.json");
    }

    @Test
    public void migrationItems() throws IOException {
        assertExample(ArrayList.class, "migration-items.json");
    }

    @Test
    public void privateUserResponseWithPublicProfileInformation() throws IOException {
        assertExample(HashMap.class, "private-user-response-with-public-profile-information.json");
    }

    @Test
    public void gitTag() throws IOException {
        assertExample(HashMap.class, "git-tag.json");
    }

    @Test
    public void teamDiscussionComment2() throws IOException {
        assertExample(HashMap.class, "team-discussion-comment-2.json");
    }

    @Test
    public void diffEntryItems() throws IOException {
        assertExample(ArrayList.class, "diff-entry-items.json");
    }

    @Test
    public void _import() throws IOException {
        assertExample(HashMap.class, "import.json");
    }

    @Test
    public void privateUser() throws IOException {
        assertExample(HashMap.class, "private-user.json");
    }

    @Test
    public void repository() throws IOException {
        assertExample(HashMap.class, "repository.json");
    }

    @Test
    public void issueEventItems() throws IOException {
        assertExample(ArrayList.class, "issue-event-items.json");
    }

    @Test
    public void collaboratorItems() throws IOException {
        assertExample(ArrayList.class, "collaborator-items.json");
    }

    @Test
    public void licenseContent() throws IOException {
        assertExample(HashMap.class, "license-content.json");
    }

    @Test
    public void pullRequestReview3() throws IOException {
        assertExample(HashMap.class, "pull-request-review-3.json");
    }

    @Test
    public void codeScanningAlert() throws IOException {
        assertExample(HashMap.class, "code-scanning-alert.json");
    }

    @Test
    public void teamItemsResponseIfChildTeamsExist() throws IOException {
        assertExample(ArrayList.class, "team-items-response-if-child-teams-exist.json");
    }

    @Test
    public void organizationInvitationItems() throws IOException {
        assertExample(ArrayList.class, "organization-invitation-items.json");
    }

    @Test
    public void authenticationToken2() throws IOException {
        assertExample(HashMap.class, "authentication-token-2.json");
    }

    @Test
    public void teamDiscussionComment() throws IOException {
        assertExample(HashMap.class, "team-discussion-comment.json");
    }

    @Test
    public void contentFile() throws IOException {
        assertExample(HashMap.class, "content-file.json");
    }

    @Test
    public void contentFileResponseIfContentIsADirectory() throws IOException {
        assertExample(ArrayList.class, "content-file-response-if-content-is-a-directory.json");
    }

    @Test
    public void contentTrafficItems() throws IOException {
        assertExample(ArrayList.class, "content-traffic-items.json");
    }

    @Test
    public void issueSearchResultItemPaginated() throws IOException {
        assertExample(HashMap.class, "issue-search-result-item-paginated.json");
    }

    @Test
    public void orgMembership2() throws IOException {
        assertExample(HashMap.class, "org-membership-2.json");
    }

    @Test
    public void groupMapping2() throws IOException {
        assertExample(HashMap.class, "group-mapping-2.json");
    }

    @Test
    public void authorization() throws IOException {
        assertExample(HashMap.class, "authorization.json");
    }

    @Test
    public void gistCommentItems() throws IOException {
        assertExample(ArrayList.class, "gist-comment-items.json");
    }

    @Test
    public void starredRepositoryItemsAlternativeResponseWithStarCreationTimestamps() throws IOException {
        assertExample(ArrayList.class, "starred-repository-items-alternative-response-with-star-creation-timestamps.json");
    }

    @Test
    public void commitComment() throws IOException {
        assertExample(HashMap.class, "commit-comment.json");
    }

    @Test
    public void shortBranchItems() throws IOException {
        assertExample(ArrayList.class, "short-branch-items.json");
    }

    @Test
    public void protectedBranchAdminEnforced() throws IOException {
        assertExample(HashMap.class, "protected-branch-admin-enforced.json");
    }

    @Test
    public void checkSuitePaginated() throws IOException {
        assertExample(HashMap.class, "check-suite-paginated.json");
    }

    @Test
    public void projectItems() throws IOException {
        assertExample(ArrayList.class, "project-items.json");
    }

    @Test
    public void checkAnnotationItems() throws IOException {
        assertExample(ArrayList.class, "check-annotation-items.json");
    }

    @Test
    public void orgHook2() throws IOException {
        assertExample(HashMap.class, "org-hook-2.json");
    }

    @Test
    public void issueSimple() throws IOException {
        assertExample(HashMap.class, "issue-simple.json");
    }

    @Test
    public void releaseAssetItems() throws IOException {
        assertExample(ArrayList.class, "release-asset-items.json");
    }

    @Test
    public void codeFrequencyStatItems() throws IOException {
        assertExample(ArrayList.class, "code-frequency-stat-items.json");
    }

    @Test
    public void deploymentStatusItems() throws IOException {
        assertExample(ArrayList.class, "deployment-status-items.json");
    }

    @Test
    public void statusItems() throws IOException {
        assertExample(ArrayList.class, "status-items.json");
    }

    @Test
    public void interactionLimit2() throws IOException {
        assertExample(HashMap.class, "interaction-limit-2.json");
    }

    @Test
    public void labelSearchResultItemPaginated() throws IOException {
        assertExample(HashMap.class, "label-search-result-item-paginated.json");
    }

    @Test
    public void repositoryInvitation() throws IOException {
        assertExample(HashMap.class, "repository-invitation.json");
    }

    @Test
    public void reactionItems() throws IOException {
        assertExample(ArrayList.class, "reaction-items.json");
    }

    @Test
    public void actionsSecretPaginated() throws IOException {
        assertExample(HashMap.class, "actions-secret-paginated.json");
    }

    @Test
    public void projectItems2() throws IOException {
        assertExample(ArrayList.class, "project-items-2.json");
    }

    @Test
    public void marketplacePurchaseItems() throws IOException {
        assertExample(ArrayList.class, "marketplace-purchase-items.json");
    }

    @Test
    public void authorization3() throws IOException {
        assertExample(HashMap.class, "authorization-3.json");
    }

    @Test
    public void threadItems() throws IOException {
        assertExample(ArrayList.class, "thread-items.json");
    }

    @Test
    public void teamMembershipResponseIfUserHasAnActiveMembershipWithTeam() throws IOException {
        assertExample(HashMap.class, "team-membership-response-if-user-has-an-active-membership-with-team.json");
    }

    @Test
    public void issueComment() throws IOException {
        assertExample(HashMap.class, "issue-comment.json");
    }

    @Test
    public void commit() throws IOException {
        assertExample(HashMap.class, "commit.json");
    }

    @Test
    public void teamMembershipResponseIfUsersMembershipWithTeamIsNowActive() throws IOException {
        assertExample(HashMap.class, "team-membership-response-if-users-membership-with-team-is-now-active.json");
    }

    @Test
    public void workflowUsage() throws IOException {
        assertExample(HashMap.class, "workflow-usage.json");
    }

    @Test
    public void topicSearchResultItemPaginated() throws IOException {
        assertExample(HashMap.class, "topic-search-result-item-paginated.json");
    }

    @Test
    public void workflowPaginated() throws IOException {
        assertExample(HashMap.class, "workflow-paginated.json");
    }

    @Test
    public void packagesBillingUsage() throws IOException {
        assertExample(HashMap.class, "packages-billing-usage.json");
    }

    @Test
    public void teamItems() throws IOException {
        assertExample(ArrayList.class, "team-items.json");
    }

    @Test
    public void authorizationResponseIfReturningAnExistingToken() throws IOException {
        assertExample(HashMap.class, "authorization-response-if-returning-an-existing-token.json");
    }

    @Test
    public void teamProjectItems() throws IOException {
        assertExample(ArrayList.class, "team-project-items.json");
    }

    @Test
    public void commitComment2() throws IOException {
        assertExample(HashMap.class, "commit-comment-2.json");
    }

    @Test
    public void teamMembershipResponseIfUsersMembershipWithTeamIsNowPending() throws IOException {
        assertExample(HashMap.class, "team-membership-response-if-users-membership-with-team-is-now-pending.json");
    }

    @Test
    public void pullRequestReview() throws IOException {
        assertExample(HashMap.class, "pull-request-review.json");
    }

    @Test
    public void release() throws IOException {
        assertExample(HashMap.class, "release.json");
    }

    @Test
    public void pageBuild() throws IOException {
        assertExample(HashMap.class, "page-build.json");
    }

    @Test
    public void keySimpleItems() throws IOException {
        assertExample(ArrayList.class, "key-simple-items.json");
    }

    @Test
    public void runner() throws IOException {
        assertExample(HashMap.class, "runner.json");
    }

    @Test
    public void integrationItems() throws IOException {
        assertExample(ArrayList.class, "integration-items.json");
    }

    @Test
    public void minimalRepositoryItems() throws IOException {
        assertExample(ArrayList.class, "minimal-repository-items.json");
    }

    @Test
    public void releaseAssetResponseForSuccessfulUpload() throws IOException {
        assertExample(HashMap.class, "release-asset-response-for-successful-upload.json");
    }

    @Test
    public void orgMembershipResponseIfUserHasAnActiveAdminMembershipWithOrganization() throws IOException {
        assertExample(HashMap.class, "org-membership-response-if-user-has-an-active-admin-membership-with-organization.json");
    }

    @Test
    public void hookItems() throws IOException {
        assertExample(ArrayList.class, "hook-items.json");
    }

    @Test
    public void licenseSimpleItems() throws IOException {
        assertExample(ArrayList.class, "license-simple-items.json");
    }

    @Test
    public void orgMembershipResponseIfUserHasAPendingMembershipWithOrganization() throws IOException {
        assertExample(HashMap.class, "org-membership-response-if-user-has-a-pending-membership-with-organization.json");
    }

    @Test
    public void releaseAsset() throws IOException {
        assertExample(HashMap.class, "release-asset.json");
    }

    @Test
    public void gitTree() throws IOException {
        assertExample(HashMap.class, "git-tree.json");
    }

    @Test
    public void projectColumn() throws IOException {
        assertExample(HashMap.class, "project-column.json");
    }

    @Test
    public void referrerTrafficItems() throws IOException {
        assertExample(ArrayList.class, "referrer-traffic-items.json");
    }

    @Test
    public void teamDiscussionItems() throws IOException {
        assertExample(ArrayList.class, "team-discussion-items.json");
    }

    @Test
    public void codeOfConduct2() throws IOException {
        assertExample(HashMap.class, "code-of-conduct-2.json");
    }

    @Test
    public void marketplaceListingPlanItems() throws IOException {
        assertExample(ArrayList.class, "marketplace-listing-plan-items.json");
    }

    @Test
    public void pullRequestReviewComment() throws IOException {
        assertExample(HashMap.class, "pull-request-review-comment.json");
    }

    @Test
    public void gitCommit() throws IOException {
        assertExample(HashMap.class, "git-commit.json");
    }

    @Test
    public void runnerApplicationItems() throws IOException {
        assertExample(ArrayList.class, "runner-application-items.json");
    }

    @Test
    public void rateLimitOverview() throws IOException {
        assertExample(HashMap.class, "rate-limit-overview.json");
    }

    @Test
    public void communityProfile() throws IOException {
        assertExample(HashMap.class, "community-profile.json");
    }

    @Test
    public void reaction() throws IOException {
        assertExample(HashMap.class, "reaction.json");
    }

    @Test
    public void fileCommit() throws IOException {
        assertExample(HashMap.class, "file-commit.json");
    }

    @Test
    public void repositoryInvitationItems() throws IOException {
        assertExample(ArrayList.class, "repository-invitation-items.json");
    }

    @Test
    public void keyItems() throws IOException {
        assertExample(ArrayList.class, "key-items.json");
    }

    @Test
    public void teamMembershipResponseIfUserHasAPendingMembershipWithTeam() throws IOException {
        assertExample(HashMap.class, "team-membership-response-if-user-has-a-pending-membership-with-team.json");
    }

    @Test
    public void job() throws IOException {
        assertExample(HashMap.class, "job.json");
    }

    @Test
    public void commitComparison() throws IOException {
        assertExample(HashMap.class, "commit-comparison.json");
    }

    @Test
    public void repositoryPaginated2() throws IOException {
        assertExample(HashMap.class, "repository-paginated-2.json");
    }

    @Test
    public void migrationWithShortOrg() throws IOException {
        assertExample(HashMap.class, "migration-with-short-org.json");
    }

    @Test
    public void shortBlob() throws IOException {
        assertExample(HashMap.class, "short-blob.json");
    }

    @Test
    public void labelItems() throws IOException {
        assertExample(ArrayList.class, "label-items.json");
    }

    @Test
    public void scimUser() throws IOException {
        assertExample(HashMap.class, "scim-user.json");
    }

    @Test
    public void issueEvent() throws IOException {
        assertExample(HashMap.class, "issue-event.json");
    }

    @Test
    public void applicationGrantItems() throws IOException {
        assertExample(ArrayList.class, "application-grant-items.json");
    }

    @Test
    public void license() throws IOException {
        assertExample(HashMap.class, "license.json");
    }

    @Test
    public void pullRequestReviewCommentItems() throws IOException {
        assertExample(ArrayList.class, "pull-request-review-comment-items.json");
    }

    @Test
    public void gitTreeResponseRecursivelyRetrievingATree() throws IOException {
        assertExample(HashMap.class, "git-tree-response-recursively-retrieving-a-tree.json");
    }

    @Test
    public void runnerPaginated() throws IOException {
        assertExample(HashMap.class, "runner-paginated.json");
    }

    @Test
    public void authorization2() throws IOException {
        assertExample(HashMap.class, "authorization-2.json");
    }

    @Test
    public void emailItems() throws IOException {
        assertExample(ArrayList.class, "email-items.json");
    }

    @Test
    public void cloneTraffic() throws IOException {
        assertExample(HashMap.class, "clone-traffic.json");
    }

    @Test
    public void deploymentItems() throws IOException {
        assertExample(ArrayList.class, "deployment-items.json");
    }

    @Test
    public void scimUserListResponseWithFilter() throws IOException {
        assertExample(HashMap.class, "scim-user-list-response-with-filter.json");
    }

    @Test
    public void orgHook() throws IOException {
        assertExample(HashMap.class, "org-hook.json");
    }

    @Test
    public void privateUserResponseWithPublicAndPrivateProfileInformation() throws IOException {
        assertExample(HashMap.class, "private-user-response-with-public-and-private-profile-information.json");
    }

    @Test
    public void groupMapping() throws IOException {
        assertExample(HashMap.class, "group-mapping.json");
    }

    @Test
    public void gist() throws IOException {
        assertExample(HashMap.class, "gist.json");
    }

    @Test
    public void codeOfConduct() throws IOException {
        assertExample(HashMap.class, "code-of-conduct.json");
    }

    @Test
    public void teamRepositoryAlternativeResponseWithRepositoryPermissions() throws IOException {
        assertExample(HashMap.class, "team-repository-alternative-response-with-repository-permissions.json");
    }

    @Test
    public void branchShortItems() throws IOException {
        assertExample(ArrayList.class, "branch-short-items.json");
    }

    @Test
    public void contributorActivityItems() throws IOException {
        assertExample(ArrayList.class, "contributor-activity-items.json");
    }

    @Test
    public void import2() throws IOException {
        assertExample(HashMap.class, "import-2.json");
    }

    @Test
    public void protectedBranchAdminEnforced2() throws IOException {
        assertExample(HashMap.class, "protected-branch-admin-enforced-2.json");
    }

    @Test
    public void applicationGrant() throws IOException {
        assertExample(HashMap.class, "application-grant.json");
    }

    @Test
    public void labelItems2() throws IOException {
        assertExample(ArrayList.class, "label-items-2.json");
    }

    @Test
    public void publicRepositoryItems() throws IOException {
        assertExample(ArrayList.class, "public-repository-items.json");
    }

    @Test
    public void migrationWithShortOrgItems() throws IOException {
        assertExample(ArrayList.class, "migration-with-short-org-items.json");
    }

    @Test
    public void organizationFullDefaultResponse() throws IOException {
        assertExample(HashMap.class, "organization-full-default-response.json");
    }

    @Test
    public void marketplacePurchase() throws IOException {
        assertExample(HashMap.class, "marketplace-purchase.json");
    }

    @Test
    public void statusCheckPolicy() throws IOException {
        assertExample(HashMap.class, "status-check-policy.json");
    }

    @Test
    public void orgMembershipResponseIfUserHasAnActiveMembershipWithOrganization() throws IOException {
        assertExample(HashMap.class, "org-membership-response-if-user-has-an-active-membership-with-organization.json");
    }

    @Test
    public void teamFull() throws IOException {
        assertExample(HashMap.class, "team-full.json");
    }

    @Test
    public void gpgKey() throws IOException {
        assertExample(HashMap.class, "gpg-key.json");
    }

    @Test
    public void userMarketplacePurchaseItems() throws IOException {
        assertExample(ArrayList.class, "user-marketplace-purchase-items.json");
    }

    @Test
    public void actionsBillingUsage() throws IOException {
        assertExample(HashMap.class, "actions-billing-usage.json");
    }

    @Test
    public void porterAuthorItems() throws IOException {
        assertExample(ArrayList.class, "porter-author-items.json");
    }

    @Test
    public void fullRepositoryResponseWithScarletWitchPreviewMediaType() throws IOException {
        assertExample(HashMap.class, "full-repository-response-with-scarlet-witch-preview-media-type.json");
    }

    @Test
    public void porterLargeFileItems() throws IOException {
        assertExample(ArrayList.class, "porter-large-file-items.json");
    }

    @Test
    public void integration() throws IOException {
        assertExample(HashMap.class, "integration.json");
    }

    @Test
    public void authorizationItems() throws IOException {
        assertExample(ArrayList.class, "authorization-items.json");
    }

    @Test
    public void contentFileResponseIfContentIsAFile() throws IOException {
        assertExample(HashMap.class, "content-file-response-if-content-is-a-file.json");
    }

    @Test
    public void pageBuildStatus() throws IOException {
        assertExample(HashMap.class, "page-build-status.json");
    }

    @Test
    public void label() throws IOException {
        assertExample(HashMap.class, "label.json");
    }

    @Test
    public void workflowRunUsage() throws IOException {
        assertExample(HashMap.class, "workflow-run-usage.json");
    }

    @Test
    public void fileCommitExampleForCreatingAFile() throws IOException {
        assertExample(HashMap.class, "file-commit-example-for-creating-a-file.json");
    }

    @Test
    public void baseGist() throws IOException {
        assertExample(HashMap.class, "base-gist.json");
    }

    @Test
    public void deploymentStatus() throws IOException {
        assertExample(HashMap.class, "deployment-status.json");
    }

    @Test
    public void baseGistItems() throws IOException {
        assertExample(ArrayList.class, "base-gist-items.json");
    }

    @Test
    public void status() throws IOException {
        assertExample(HashMap.class, "status.json");
    }

    @Test
    public void apiOverview() throws IOException {
        assertExample(HashMap.class, "api-overview.json");
    }

    @Test
    public void checkRunExampleOfInProgressConclusion() throws IOException {
        assertExample(HashMap.class, "check-run-example-of-in-progress-conclusion.json");
    }

    @Test
    public void codeScanningAlertItems() throws IOException {
        assertExample(ArrayList.class, "code-scanning-alert-items.json");
    }

    @Test
    public void checkRunExampleOfCompletedConclusion() throws IOException {
        assertExample(HashMap.class, "check-run-example-of-completed-conclusion.json");
    }

    @Test
    public void importResponse() throws IOException {
        assertExample(HashMap.class, "import-response.json");
    }

    @Test
    public void checkSuite() throws IOException {
        assertExample(HashMap.class, "check-suite.json");
    }

    @Test
    public void publicUserResponseWithGitHubPlanInformation() throws IOException {
        assertExample(HashMap.class, "public-user-response-with-git-hub-plan-information.json");
    }

    @Test
    public void importExample1() throws IOException {
        assertExample(HashMap.class, "import-example-1.json");
    }

    @Test
    public void page() throws IOException {
        assertExample(HashMap.class, "page.json");
    }

    @Test
    public void commitSearchResultItemPaginated() throws IOException {
        assertExample(HashMap.class, "commit-search-result-item-paginated.json");
    }

    @Test
    public void pullRequestSimpleItems() throws IOException {
        assertExample(ArrayList.class, "pull-request-simple-items.json");
    }

    @Test
    public void commitActivityItems() throws IOException {
        assertExample(ArrayList.class, "commit-activity-items.json");
    }

    @Test
    public void contentFileResponseIfContentIsASubmodule() throws IOException {
        assertExample(HashMap.class, "content-file-response-if-content-is-a-submodule.json");
    }

    @Test
    public void feed() throws IOException {
        assertExample(HashMap.class, "feed.json");
    }

    @Test
    public void gpgKeyItems() throws IOException {
        assertExample(ArrayList.class, "gpg-key-items.json");
    }

    @Test
    public void deployKeyItems() throws IOException {
        assertExample(ArrayList.class, "deploy-key-items.json");
    }

    @Test
    public void gitTreeDefaultResponse() throws IOException {
        assertExample(HashMap.class, "git-tree-default-response.json");
    }

    @Test
    public void simpleUserItems() throws IOException {
        assertExample(ArrayList.class, "simple-user-items.json");
    }

    @Test
    public void fullRepositoryDefaultResponse() throws IOException {
        assertExample(HashMap.class, "full-repository-default-response.json");
    }

    @Test
    public void repositorySubscriptionResponseIfYouSubscribeToTheRepository() throws IOException {
        assertExample(HashMap.class, "repository-subscription-response-if-you-subscribe-to-the-repository.json");
    }

    @Test
    public void participationStats() throws IOException {
        assertExample(HashMap.class, "participation-stats.json");
    }

    @Test
    public void contentFileResponseIfContentIsASymlink() throws IOException {
        assertExample(HashMap.class, "content-file-response-if-content-is-a-symlink.json");
    }

    @Test
    public void pullRequestReviewRequest() throws IOException {
        assertExample(HashMap.class, "pull-request-review-request.json");
    }

    @Test
    public void orgHookItems() throws IOException {
        assertExample(ArrayList.class, "org-hook-items.json");
    }

    @Test
    public void organizationFull() throws IOException {
        assertExample(HashMap.class, "organization-full.json");
    }

    @Test
    public void repositoryItemsDefaultResponse() throws IOException {
        assertExample(ArrayList.class, "repository-items-default-response.json");
    }

    @Test
    public void repositoryPaginated() throws IOException {
        assertExample(HashMap.class, "repository-paginated.json");
    }

    @Test
    public void minimalRepositoryItems2() throws IOException {
        assertExample(ArrayList.class, "minimal-repository-items-2.json");
    }

    @Test
    public void projectCard() throws IOException {
        assertExample(HashMap.class, "project-card.json");
    }

    @Test
    public void teamDiscussion2() throws IOException {
        assertExample(HashMap.class, "team-discussion-2.json");
    }

    @Test
    public void organizationActionsSecret() throws IOException {
        assertExample(HashMap.class, "organization-actions-secret.json");
    }

    @Test
    public void artifact() throws IOException {
        assertExample(HashMap.class, "artifact.json");
    }


    public static void assertExample(final Class<?> clazz, final String name) throws IOException {
        final String expected = Resources.read(ExamplesOpenApiTest.class, name);
        JsonAsserts.assertJsonb(expected, clazz);
    }

}
