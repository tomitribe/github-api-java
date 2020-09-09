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

import org.tomitribe.github.model.CheckRunEvent;
import org.tomitribe.github.model.CheckSuiteEvent;
import org.tomitribe.github.model.CommitCommentEvent;
import org.tomitribe.github.model.ContentReferenceEvent;
import org.tomitribe.github.model.CreateEvent;
import org.tomitribe.github.model.DeleteEvent;
import org.tomitribe.github.model.DeployKeyEvent;
import org.tomitribe.github.model.DeploymentEvent;
import org.tomitribe.github.model.DeploymentStatusEvent;
import org.tomitribe.github.model.ForkEvent;
import org.tomitribe.github.model.GitHubAppAuthorizationEvent;
import org.tomitribe.github.model.GollumEvent;
import org.tomitribe.github.model.InstallationEvent;
import org.tomitribe.github.model.InstallationRepositoriesEvent;
import org.tomitribe.github.model.IssueCommentEvent;
import org.tomitribe.github.model.IssuesEvent;
import org.tomitribe.github.model.LabelEvent;
import org.tomitribe.github.model.MemberEvent;
import org.tomitribe.github.model.MembershipEvent;
import org.tomitribe.github.model.MetaEvent;
import org.tomitribe.github.model.MilestoneEvent;
import org.tomitribe.github.model.OrgBlockEvent;
import org.tomitribe.github.model.OrganizationEvent;
import org.tomitribe.github.model.PageBuildEvent;
import org.tomitribe.github.model.ProjectCardEvent;
import org.tomitribe.github.model.ProjectColumnEvent;
import org.tomitribe.github.model.ProjectEvent;
import org.tomitribe.github.model.PublicEvent;
import org.tomitribe.github.model.PullRequestEvent;
import org.tomitribe.github.model.PullRequestReviewCommentEvent;
import org.tomitribe.github.model.PullRequestReviewEvent;
import org.tomitribe.github.model.PushEvent;
import org.tomitribe.github.model.RegistryPackageEvent;
import org.tomitribe.github.model.ReleaseEvent;
import org.tomitribe.github.model.RepositoryEvent;
import org.tomitribe.github.model.RepositoryImportEvent;
import org.tomitribe.github.model.RepositoryVulnerabilityAlertEvent;
import org.tomitribe.github.model.SecurityAdvisoryEvent;
import org.tomitribe.github.model.StarEvent;
import org.tomitribe.github.model.StatusEvent;
import org.tomitribe.github.model.TeamAddEvent;
import org.tomitribe.github.model.TeamEvent;
import org.tomitribe.github.model.WatchEvent;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

//CHECKSTYLE:OFF 
@Path("/event")
@Consumes(MediaType.APPLICATION_JSON)
public interface GithubEventsResource {

    @Path("check_run")
    default void event(final CheckRunEvent CheckRunEvent) {
    }

    @Path("check_suite")
    default void event(final CheckSuiteEvent CheckSuiteEvent) {
    }

    @Path("commit_comment")
    default void event(final CommitCommentEvent CommitCommentEvent) {
    }

    @Path("content_reference")
    default void event(final ContentReferenceEvent ContentReferenceEvent) {
    }

    @Path("create")
    default void event(final CreateEvent CreateEvent) {
    }

    @Path("delete")
    default void event(final DeleteEvent DeleteEvent) {
    }

    @Path("deploy_key")
    default void event(final DeployKeyEvent DeployKeyEvent) {
    }

    @Path("deployment")
    default void event(final DeploymentEvent DeploymentEvent) {
    }

    @Path("deployment_status")
    default void event(final DeploymentStatusEvent DeploymentStatusEvent) {
    }

    @Path("fork")
    default void event(final ForkEvent ForkEvent) {
    }

    @Path("github_app_authorization")
    default void event(final GitHubAppAuthorizationEvent GitHubAppAuthorizationEvent) {
    }

    @Path("gollum")
    default void event(final GollumEvent GollumEvent) {
    }

    @Path("installation")
    default void event(final InstallationEvent InstallationEvent) {
    }

    @Path("installation_repositories")
    default void event(final InstallationRepositoriesEvent InstallationRepositoriesEvent) {
    }

    @Path("issue_comment")
    default void event(final IssueCommentEvent IssueCommentEvent) {
    }

    @Path("issues")
    default void event(final IssuesEvent IssuesEvent) {
    }

    @Path("label")
    default void event(final LabelEvent LabelEvent) {
    }

    @Path("member")
    default void event(final MemberEvent MemberEvent) {
    }

    @Path("membership")
    default void event(final MembershipEvent MembershipEvent) {
    }

    @Path("meta")
    default void event(final MetaEvent MetaEvent) {
    }

    @Path("milestone")
    default void event(final MilestoneEvent MilestoneEvent) {
    }

    @Path("organization")
    default void event(final OrganizationEvent OrganizationEvent) {
    }

    @Path("org_block")
    default void event(final OrgBlockEvent OrgBlockEvent) {
    }

    @Path("page_build")
    default void event(final PageBuildEvent PageBuildEvent) {
    }

    @Path("project_card")
    default void event(final ProjectCardEvent ProjectCardEvent) {
    }

    @Path("project_column")
    default void event(final ProjectColumnEvent ProjectColumnEvent) {
    }

    @Path("project")
    default void event(final ProjectEvent ProjectEvent) {
    }

    @Path("public")
    default void event(final PublicEvent PublicEvent) {
    }

    @Path("pull_request")
    default void event(final PullRequestEvent PullRequestEvent) {
    }

    @Path("pull_request_review")
    default void event(final PullRequestReviewEvent PullRequestReviewEvent) {
    }

    @Path("pull_request_review_comment")
    default void event(final PullRequestReviewCommentEvent PullRequestReviewCommentEvent) {
    }

    @Path("push")
    default void event(final PushEvent PushEvent) {
    }

    @Path("registry_package")
    default void event(final RegistryPackageEvent RegistryPackageEvent) {
    }

    @Path("release")
    default void event(final ReleaseEvent ReleaseEvent) {
    }

    @Path("repository")
    default void event(final RepositoryEvent RepositoryEvent) {
    }

    @Path("repository_import")
    default void event(final RepositoryImportEvent RepositoryImportEvent) {
    }

    @Path("repository_vulnerability_alert")
    default void event(final RepositoryVulnerabilityAlertEvent RepositoryVulnerabilityAlertEvent) {
    }

    @Path("security_advisory")
    default void event(final SecurityAdvisoryEvent SecurityAdvisoryEvent) {
    }

    @Path("star")
    default void event(final StarEvent StarEvent) {
    }

    @Path("status")
    default void event(final StatusEvent StatusEvent) {
    }

    @Path("team")
    default void event(final TeamEvent TeamEvent) {
    }

    @Path("team_add")
    default void event(final TeamAddEvent TeamAddEvent) {
    }

    @Path("watch")
    default void event(final WatchEvent WatchEvent) {
    }

//CHECKSTYLE:ON
}
