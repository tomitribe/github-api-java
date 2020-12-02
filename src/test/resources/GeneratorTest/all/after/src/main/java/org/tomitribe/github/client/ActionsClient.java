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
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import org.tomitribe.github.model.ActionsPublicKey;
import org.tomitribe.github.model.ActionsSecret;
import org.tomitribe.github.model.ActionsSecretsPage;
import org.tomitribe.github.model.AddSelectedRepositoryToOrganizationSecret;
import org.tomitribe.github.model.Artifact;
import org.tomitribe.github.model.ArtifactsPage;
import org.tomitribe.github.model.AuthenticationToken;
import org.tomitribe.github.model.CreateOrUpdateOrganizationSecret;
import org.tomitribe.github.model.CreateOrUpdateRepositorySecret;
import org.tomitribe.github.model.CreateRegistrationTokenForOrganization;
import org.tomitribe.github.model.CreateRegistrationTokenForRepository;
import org.tomitribe.github.model.CreateRemoveTokenForOrganization;
import org.tomitribe.github.model.CreateRemoveTokenForRepository;
import org.tomitribe.github.model.CreateWorkflowDispatchEvent;
import org.tomitribe.github.model.DeleteArtifact;
import org.tomitribe.github.model.DeleteOrganizationSecret;
import org.tomitribe.github.model.DeleteRepositorySecret;
import org.tomitribe.github.model.DeleteSelfHostedRunnerFromOrganization;
import org.tomitribe.github.model.DeleteSelfHostedRunnerFromRepository;
import org.tomitribe.github.model.DeleteWorkflowRun;
import org.tomitribe.github.model.DeleteWorkflowRunLogs;
import org.tomitribe.github.model.GetArtifact;
import org.tomitribe.github.model.GetJobForWorkflowRun;
import org.tomitribe.github.model.GetOrganizationPublicKey;
import org.tomitribe.github.model.GetOrganizationSecret;
import org.tomitribe.github.model.GetRepositoryPublicKey;
import org.tomitribe.github.model.GetRepositorySecret;
import org.tomitribe.github.model.GetSelfHostedRunnerForOrganization;
import org.tomitribe.github.model.GetSelfHostedRunnerForRepository;
import org.tomitribe.github.model.GetWorkflow;
import org.tomitribe.github.model.GetWorkflowRun;
import org.tomitribe.github.model.GetWorkflowRunUsage;
import org.tomitribe.github.model.GetWorkflowUsage;
import org.tomitribe.github.model.Job;
import org.tomitribe.github.model.JobsPage;
import org.tomitribe.github.model.ListArtifactsForRepository;
import org.tomitribe.github.model.ListJobsForWorkflowRun;
import org.tomitribe.github.model.ListOrganizationSecrets;
import org.tomitribe.github.model.ListRepositorySecrets;
import org.tomitribe.github.model.ListRepositoryWorkflows;
import org.tomitribe.github.model.ListRunnerApplicationsForOrganization;
import org.tomitribe.github.model.ListRunnerApplicationsForRepository;
import org.tomitribe.github.model.ListSelectedRepositoriesForOrganizationSecret;
import org.tomitribe.github.model.ListSelectedRepositoriesForOrganizationSecretResponse;
import org.tomitribe.github.model.ListSelfHostedRunnersForOrganization;
import org.tomitribe.github.model.ListSelfHostedRunnersForRepository;
import org.tomitribe.github.model.ListWorkflowRunArtifacts;
import org.tomitribe.github.model.ListWorkflowRuns;
import org.tomitribe.github.model.ListWorkflowRunsForRepository;
import org.tomitribe.github.model.OrganizationActionsSecret;
import org.tomitribe.github.model.OrganizationActionsSecretsPage;
import org.tomitribe.github.model.RemoveSelectedRepositoryFromOrganizationSecret;
import org.tomitribe.github.model.Runner;
import org.tomitribe.github.model.RunnerApplication;
import org.tomitribe.github.model.RunnersPage;
import org.tomitribe.github.model.SetSelectedRepositoriesForOrganizationSecret;
import org.tomitribe.github.model.Workflow;
import org.tomitribe.github.model.WorkflowRun;
import org.tomitribe.github.model.WorkflowRunUsage;
import org.tomitribe.github.model.WorkflowRunsPage;
import org.tomitribe.github.model.WorkflowUsage;
import org.tomitribe.github.model.WorkflowsPage;

public interface ActionsClient {

    @PUT
    @Path("/orgs/{org}/actions/secrets/{secret_name}/repositories/{repository_id}")
    @OperationId("actions/add-selected-repo-to-org-secret")
    @Docs("https://developer.github.com/v3/actions/secrets/#add-selected-repository-to-an-organization-secret")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    void addSelectedRepositoryToOrganizationSecret(final AddSelectedRepositoryToOrganizationSecret addSelectedRepositoryToOrganizationSecret);

    @PUT
    @Path("/orgs/{org}/actions/secrets/{secret_name}/repositories/{repository_id}")
    @OperationId("actions/add-selected-repo-to-org-secret")
    @Docs("https://developer.github.com/v3/actions/secrets/#add-selected-repository-to-an-organization-secret")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    void addSelectedRepositoryToOrganizationSecret(@PathParam("org") final String org, @PathParam("secret_name") final String secretName, @PathParam("repository_id") final int repositoryId);

    @PUT
    @Path("/orgs/{org}/actions/secrets/{secret_name}")
    @OperationId("actions/create-or-update-org-secret")
    @Docs("https://developer.github.com/v3/actions/secrets/#create-or-update-an-organization-secret")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    void createOrUpdateOrganizationSecret(final CreateOrUpdateOrganizationSecret createOrUpdateOrganizationSecret);

    @PUT
    @Path("/repos/{owner}/{repo}/actions/secrets/{secret_name}")
    @OperationId("actions/create-or-update-repo-secret")
    @Docs("https://developer.github.com/v3/actions/secrets/#create-or-update-a-repository-secret")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    void createOrUpdateRepositorySecret(final CreateOrUpdateRepositorySecret createOrUpdateRepositorySecret);

    @POST
    @Path("/orgs/{org}/actions/runners/registration-token")
    @OperationId("actions/create-registration-token-for-org")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#create-a-registration-token-for-an-organization")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    AuthenticationToken createRegistrationTokenForOrganization(final CreateRegistrationTokenForOrganization createRegistrationTokenForOrganization);

    @POST
    @Path("/orgs/{org}/actions/runners/registration-token")
    @OperationId("actions/create-registration-token-for-org")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#create-a-registration-token-for-an-organization")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    AuthenticationToken createRegistrationTokenForOrganization(@PathParam("org") final String org);

    @POST
    @Path("/repos/{owner}/{repo}/actions/runners/registration-token")
    @OperationId("actions/create-registration-token-for-repo")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#create-a-registration-token-for-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    AuthenticationToken createRegistrationTokenForRepository(final CreateRegistrationTokenForRepository createRegistrationTokenForRepository);

    @POST
    @Path("/repos/{owner}/{repo}/actions/runners/registration-token")
    @OperationId("actions/create-registration-token-for-repo")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#create-a-registration-token-for-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    AuthenticationToken createRegistrationTokenForRepository(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @POST
    @Path("/orgs/{org}/actions/runners/remove-token")
    @OperationId("actions/create-remove-token-for-org")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#create-a-remove-token-for-an-organization")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    AuthenticationToken createRemoveTokenForOrganization(final CreateRemoveTokenForOrganization createRemoveTokenForOrganization);

    @POST
    @Path("/orgs/{org}/actions/runners/remove-token")
    @OperationId("actions/create-remove-token-for-org")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#create-a-remove-token-for-an-organization")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    AuthenticationToken createRemoveTokenForOrganization(@PathParam("org") final String org);

    @POST
    @Path("/repos/{owner}/{repo}/actions/runners/remove-token")
    @OperationId("actions/create-remove-token-for-repo")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#create-a-remove-token-for-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    AuthenticationToken createRemoveTokenForRepository(final CreateRemoveTokenForRepository createRemoveTokenForRepository);

    @POST
    @Path("/repos/{owner}/{repo}/actions/runners/remove-token")
    @OperationId("actions/create-remove-token-for-repo")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#create-a-remove-token-for-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    AuthenticationToken createRemoveTokenForRepository(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @POST
    @Path("/repos/{owner}/{repo}/actions/workflows/{workflow_id}/dispatches")
    @OperationId("actions/create-workflow-dispatch")
    @Docs("https://developer.github.com/v3/actions/workflows/#create-a-workflow-dispatch-event")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflows")
    void createWorkflowDispatchEvent(final CreateWorkflowDispatchEvent createWorkflowDispatchEvent);

    @DELETE
    @Path("/repos/{owner}/{repo}/actions/artifacts/{artifact_id}")
    @OperationId("actions/delete-artifact")
    @Docs("https://developer.github.com/v3/actions/artifacts/#delete-an-artifact")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("artifacts")
    void deleteArtifact(final DeleteArtifact deleteArtifact);

    @DELETE
    @Path("/repos/{owner}/{repo}/actions/artifacts/{artifact_id}")
    @OperationId("actions/delete-artifact")
    @Docs("https://developer.github.com/v3/actions/artifacts/#delete-an-artifact")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("artifacts")
    void deleteArtifact(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("artifact_id") final int artifactId);

    @DELETE
    @Path("/orgs/{org}/actions/secrets/{secret_name}")
    @OperationId("actions/delete-org-secret")
    @Docs("https://developer.github.com/v3/actions/secrets/#delete-an-organization-secret")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    void deleteOrganizationSecret(final DeleteOrganizationSecret deleteOrganizationSecret);

    @DELETE
    @Path("/orgs/{org}/actions/secrets/{secret_name}")
    @OperationId("actions/delete-org-secret")
    @Docs("https://developer.github.com/v3/actions/secrets/#delete-an-organization-secret")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    void deleteOrganizationSecret(@PathParam("org") final String org, @PathParam("secret_name") final String secretName);

    @DELETE
    @Path("/repos/{owner}/{repo}/actions/secrets/{secret_name}")
    @OperationId("actions/delete-repo-secret")
    @Docs("https://developer.github.com/v3/actions/secrets/#delete-a-repository-secret")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    void deleteRepositorySecret(final DeleteRepositorySecret deleteRepositorySecret);

    @DELETE
    @Path("/repos/{owner}/{repo}/actions/secrets/{secret_name}")
    @OperationId("actions/delete-repo-secret")
    @Docs("https://developer.github.com/v3/actions/secrets/#delete-a-repository-secret")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    void deleteRepositorySecret(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("secret_name") final String secretName);

    @DELETE
    @Path("/orgs/{org}/actions/runners/{runner_id}")
    @OperationId("actions/delete-self-hosted-runner-from-org")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#delete-a-self-hosted-runner-from-an-organization")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    void deleteSelfHostedRunnerFromOrganization(final DeleteSelfHostedRunnerFromOrganization deleteSelfHostedRunnerFromOrganization);

    @DELETE
    @Path("/orgs/{org}/actions/runners/{runner_id}")
    @OperationId("actions/delete-self-hosted-runner-from-org")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#delete-a-self-hosted-runner-from-an-organization")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    void deleteSelfHostedRunnerFromOrganization(@PathParam("org") final String org, @PathParam("runner_id") final int runnerId);

    @DELETE
    @Path("/repos/{owner}/{repo}/actions/runners/{runner_id}")
    @OperationId("actions/delete-self-hosted-runner-from-repo")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#delete-a-self-hosted-runner-from-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    void deleteSelfHostedRunnerFromRepository(final DeleteSelfHostedRunnerFromRepository deleteSelfHostedRunnerFromRepository);

    @DELETE
    @Path("/repos/{owner}/{repo}/actions/runners/{runner_id}")
    @OperationId("actions/delete-self-hosted-runner-from-repo")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#delete-a-self-hosted-runner-from-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    void deleteSelfHostedRunnerFromRepository(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("runner_id") final int runnerId);

    @DELETE
    @Path("/repos/{owner}/{repo}/actions/runs/{run_id}")
    @OperationId("actions/delete-workflow-run")
    @Docs("https://developer.github.com/v3/actions/workflow-runs/#delete-a-workflow-run")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflow-runs")
    void deleteWorkflowRun(final DeleteWorkflowRun deleteWorkflowRun);

    @DELETE
    @Path("/repos/{owner}/{repo}/actions/runs/{run_id}")
    @OperationId("actions/delete-workflow-run")
    @Docs("https://developer.github.com/v3/actions/workflow-runs/#delete-a-workflow-run")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflow-runs")
    void deleteWorkflowRun(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("run-id") final int runId);

    @DELETE
    @Path("/repos/{owner}/{repo}/actions/runs/{run_id}/logs")
    @OperationId("actions/delete-workflow-run-logs")
    @Docs("https://developer.github.com/v3/actions/workflow-runs/#delete-workflow-run-logs")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflow-runs")
    void deleteWorkflowRunLogs(final DeleteWorkflowRunLogs deleteWorkflowRunLogs);

    @DELETE
    @Path("/repos/{owner}/{repo}/actions/runs/{run_id}/logs")
    @OperationId("actions/delete-workflow-run-logs")
    @Docs("https://developer.github.com/v3/actions/workflow-runs/#delete-workflow-run-logs")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflow-runs")
    void deleteWorkflowRunLogs(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("run-id") final int runId);

    @GET
    @Path("/repos/{owner}/{repo}/actions/artifacts/{artifact_id}")
    @OperationId("actions/get-artifact")
    @Docs("https://developer.github.com/v3/actions/artifacts/#get-an-artifact")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("artifacts")
    Artifact getArtifact(final GetArtifact getArtifact);

    @GET
    @Path("/repos/{owner}/{repo}/actions/artifacts/{artifact_id}")
    @OperationId("actions/get-artifact")
    @Docs("https://developer.github.com/v3/actions/artifacts/#get-an-artifact")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("artifacts")
    Artifact getArtifact(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("artifact_id") final int artifactId);

    @GET
    @Path("/repos/{owner}/{repo}/actions/jobs/{job_id}")
    @OperationId("actions/get-job-for-workflow-run")
    @Docs("https://developer.github.com/v3/actions/workflow-jobs/#get-a-job-for-a-workflow-run")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflow-jobs")
    Job getJobForWorkflowRun(final GetJobForWorkflowRun getJobForWorkflowRun);

    @GET
    @Path("/repos/{owner}/{repo}/actions/jobs/{job_id}")
    @OperationId("actions/get-job-for-workflow-run")
    @Docs("https://developer.github.com/v3/actions/workflow-jobs/#get-a-job-for-a-workflow-run")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflow-jobs")
    Job getJobForWorkflowRun(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("job_id") final int jobId);

    @GET
    @Path("/orgs/{org}/actions/secrets/public-key")
    @OperationId("actions/get-org-public-key")
    @Docs("https://developer.github.com/v3/actions/secrets/#get-an-organization-public-key")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    ActionsPublicKey getOrganizationPublicKey(final GetOrganizationPublicKey getOrganizationPublicKey);

    @GET
    @Path("/orgs/{org}/actions/secrets/public-key")
    @OperationId("actions/get-org-public-key")
    @Docs("https://developer.github.com/v3/actions/secrets/#get-an-organization-public-key")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    ActionsPublicKey getOrganizationPublicKey(@PathParam("org") final String org);

    @GET
    @Path("/orgs/{org}/actions/secrets/{secret_name}")
    @OperationId("actions/get-org-secret")
    @Docs("https://developer.github.com/v3/actions/secrets/#get-an-organization-secret")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    OrganizationActionsSecret getOrganizationSecret(final GetOrganizationSecret getOrganizationSecret);

    @GET
    @Path("/orgs/{org}/actions/secrets/{secret_name}")
    @OperationId("actions/get-org-secret")
    @Docs("https://developer.github.com/v3/actions/secrets/#get-an-organization-secret")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    OrganizationActionsSecret getOrganizationSecret(@PathParam("org") final String org, @PathParam("secret_name") final String secretName);

    @GET
    @Path("/repos/{owner}/{repo}/actions/secrets/public-key")
    @OperationId("actions/get-repo-public-key")
    @Docs("https://developer.github.com/v3/actions/secrets/#get-a-repository-public-key")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    ActionsPublicKey getRepositoryPublicKey(final GetRepositoryPublicKey getRepositoryPublicKey);

    @GET
    @Path("/repos/{owner}/{repo}/actions/secrets/public-key")
    @OperationId("actions/get-repo-public-key")
    @Docs("https://developer.github.com/v3/actions/secrets/#get-a-repository-public-key")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    ActionsPublicKey getRepositoryPublicKey(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/repos/{owner}/{repo}/actions/secrets/{secret_name}")
    @OperationId("actions/get-repo-secret")
    @Docs("https://developer.github.com/v3/actions/secrets/#get-a-repository-secret")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    ActionsSecret getRepositorySecret(final GetRepositorySecret getRepositorySecret);

    @GET
    @Path("/repos/{owner}/{repo}/actions/secrets/{secret_name}")
    @OperationId("actions/get-repo-secret")
    @Docs("https://developer.github.com/v3/actions/secrets/#get-a-repository-secret")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    ActionsSecret getRepositorySecret(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("secret_name") final String secretName);

    @GET
    @Path("/orgs/{org}/actions/runners/{runner_id}")
    @OperationId("actions/get-self-hosted-runner-for-org")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#get-a-self-hosted-runner-for-an-organization")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    Runner getSelfHostedRunnerForOrganization(final GetSelfHostedRunnerForOrganization getSelfHostedRunnerForOrganization);

    @GET
    @Path("/orgs/{org}/actions/runners/{runner_id}")
    @OperationId("actions/get-self-hosted-runner-for-org")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#get-a-self-hosted-runner-for-an-organization")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    Runner getSelfHostedRunnerForOrganization(@PathParam("org") final String org, @PathParam("runner_id") final int runnerId);

    @GET
    @Path("/repos/{owner}/{repo}/actions/runners/{runner_id}")
    @OperationId("actions/get-self-hosted-runner-for-repo")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#get-a-self-hosted-runner-for-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    Runner getSelfHostedRunnerForRepository(final GetSelfHostedRunnerForRepository getSelfHostedRunnerForRepository);

    @GET
    @Path("/repos/{owner}/{repo}/actions/runners/{runner_id}")
    @OperationId("actions/get-self-hosted-runner-for-repo")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#get-a-self-hosted-runner-for-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    Runner getSelfHostedRunnerForRepository(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("runner_id") final int runnerId);

    @GET
    @Path("/repos/{owner}/{repo}/actions/workflows/{workflow_id}")
    @OperationId("actions/get-workflow")
    @Docs("https://developer.github.com/v3/actions/workflows/#get-a-workflow")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflows")
    Workflow getWorkflow(final GetWorkflow getWorkflow);

    @GET
    @Path("/repos/{owner}/{repo}/actions/workflows/{workflow_id}")
    @OperationId("actions/get-workflow")
    @Docs("https://developer.github.com/v3/actions/workflows/#get-a-workflow")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflows")
    Workflow getWorkflow(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("workflow-id") final int workflowId);

    @GET
    @Path("/repos/{owner}/{repo}/actions/runs/{run_id}")
    @OperationId("actions/get-workflow-run")
    @Docs("https://developer.github.com/v3/actions/workflow-runs/#get-a-workflow-run")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflow-runs")
    WorkflowRun getWorkflowRun(final GetWorkflowRun getWorkflowRun);

    @GET
    @Path("/repos/{owner}/{repo}/actions/runs/{run_id}")
    @OperationId("actions/get-workflow-run")
    @Docs("https://developer.github.com/v3/actions/workflow-runs/#get-a-workflow-run")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflow-runs")
    WorkflowRun getWorkflowRun(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("run-id") final int runId);

    @GET
    @Path("/repos/{owner}/{repo}/actions/runs/{run_id}/timing")
    @OperationId("actions/get-workflow-run-usage")
    @Docs("https://developer.github.com/v3/actions/workflow-runs/#get-workflow-run-usage")
    @Category("actions")
    @Subcategory("workflow-runs")
    WorkflowRunUsage getWorkflowRunUsage(final GetWorkflowRunUsage getWorkflowRunUsage);

    @GET
    @Path("/repos/{owner}/{repo}/actions/runs/{run_id}/timing")
    @OperationId("actions/get-workflow-run-usage")
    @Docs("https://developer.github.com/v3/actions/workflow-runs/#get-workflow-run-usage")
    @Category("actions")
    @Subcategory("workflow-runs")
    WorkflowRunUsage getWorkflowRunUsage(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("run-id") final int runId);

    @GET
    @Path("/repos/{owner}/{repo}/actions/workflows/{workflow_id}/timing")
    @OperationId("actions/get-workflow-usage")
    @Docs("https://developer.github.com/v3/actions/workflows/#get-workflow-usage")
    @Category("actions")
    @Subcategory("workflows")
    WorkflowUsage getWorkflowUsage(final GetWorkflowUsage getWorkflowUsage);

    @GET
    @Path("/repos/{owner}/{repo}/actions/workflows/{workflow_id}/timing")
    @OperationId("actions/get-workflow-usage")
    @Docs("https://developer.github.com/v3/actions/workflows/#get-workflow-usage")
    @Category("actions")
    @Subcategory("workflows")
    WorkflowUsage getWorkflowUsage(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("workflow-id") final int workflowId);

    @GET
    @Path("/repos/{owner}/{repo}/actions/artifacts")
    @OperationId("actions/list-artifacts-for-repo")
    @Docs("https://developer.github.com/v3/actions/artifacts/#list-artifacts-for-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("artifacts")
    @Paged(ArtifactsPage.class)
    Stream<Artifact> listArtifactsForRepository(final ListArtifactsForRepository listArtifactsForRepository);

    @GET
    @Path("/repos/{owner}/{repo}/actions/artifacts")
    @OperationId("actions/list-artifacts-for-repo")
    @Docs("https://developer.github.com/v3/actions/artifacts/#list-artifacts-for-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("artifacts")
    @Paged(ArtifactsPage.class)
    Stream<Artifact> listArtifactsForRepository(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/repos/{owner}/{repo}/actions/runs/{run_id}/jobs")
    @OperationId("actions/list-jobs-for-workflow-run")
    @Docs("https://developer.github.com/v3/actions/workflow-jobs/#list-jobs-for-a-workflow-run")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflow-jobs")
    @Paged(JobsPage.class)
    Stream<Job> listJobsForWorkflowRun(final ListJobsForWorkflowRun listJobsForWorkflowRun);

    @GET
    @Path("/repos/{owner}/{repo}/actions/runs/{run_id}/jobs")
    @OperationId("actions/list-jobs-for-workflow-run")
    @Docs("https://developer.github.com/v3/actions/workflow-jobs/#list-jobs-for-a-workflow-run")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflow-jobs")
    @Paged(JobsPage.class)
    Stream<Job> listJobsForWorkflowRun(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("run-id") final int runId);

    @GET
    @Path("/orgs/{org}/actions/secrets")
    @OperationId("actions/list-org-secrets")
    @Docs("https://developer.github.com/v3/actions/secrets/#list-organization-secrets")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    @Paged(OrganizationActionsSecretsPage.class)
    Stream<OrganizationActionsSecret> listOrganizationSecrets(final ListOrganizationSecrets listOrganizationSecrets);

    @GET
    @Path("/orgs/{org}/actions/secrets")
    @OperationId("actions/list-org-secrets")
    @Docs("https://developer.github.com/v3/actions/secrets/#list-organization-secrets")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    @Paged(OrganizationActionsSecretsPage.class)
    Stream<OrganizationActionsSecret> listOrganizationSecrets(@PathParam("org") final String org);

    @GET
    @Path("/repos/{owner}/{repo}/actions/secrets")
    @OperationId("actions/list-repo-secrets")
    @Docs("https://developer.github.com/v3/actions/secrets/#list-repository-secrets")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    @Paged(ActionsSecretsPage.class)
    Stream<ActionsSecret> listRepositorySecrets(final ListRepositorySecrets listRepositorySecrets);

    @GET
    @Path("/repos/{owner}/{repo}/actions/secrets")
    @OperationId("actions/list-repo-secrets")
    @Docs("https://developer.github.com/v3/actions/secrets/#list-repository-secrets")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    @Paged(ActionsSecretsPage.class)
    Stream<ActionsSecret> listRepositorySecrets(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/repos/{owner}/{repo}/actions/workflows")
    @OperationId("actions/list-repo-workflows")
    @Docs("https://developer.github.com/v3/actions/workflows/#list-repository-workflows")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflows")
    @Paged(WorkflowsPage.class)
    Stream<Workflow> listRepositoryWorkflows(final ListRepositoryWorkflows listRepositoryWorkflows);

    @GET
    @Path("/repos/{owner}/{repo}/actions/workflows")
    @OperationId("actions/list-repo-workflows")
    @Docs("https://developer.github.com/v3/actions/workflows/#list-repository-workflows")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflows")
    @Paged(WorkflowsPage.class)
    Stream<Workflow> listRepositoryWorkflows(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/orgs/{org}/actions/runners/downloads")
    @OperationId("actions/list-runner-applications-for-org")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#list-runner-applications-for-an-organization")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    Stream<RunnerApplication> listRunnerApplicationsForOrganization(final ListRunnerApplicationsForOrganization listRunnerApplicationsForOrganization);

    @GET
    @Path("/orgs/{org}/actions/runners/downloads")
    @OperationId("actions/list-runner-applications-for-org")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#list-runner-applications-for-an-organization")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    Stream<RunnerApplication> listRunnerApplicationsForOrganization(@PathParam("org") final String org);

    @GET
    @Path("/repos/{owner}/{repo}/actions/runners/downloads")
    @OperationId("actions/list-runner-applications-for-repo")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#list-runner-applications-for-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    Stream<RunnerApplication> listRunnerApplicationsForRepository(final ListRunnerApplicationsForRepository listRunnerApplicationsForRepository);

    @GET
    @Path("/repos/{owner}/{repo}/actions/runners/downloads")
    @OperationId("actions/list-runner-applications-for-repo")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#list-runner-applications-for-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    Stream<RunnerApplication> listRunnerApplicationsForRepository(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/orgs/{org}/actions/secrets/{secret_name}/repositories")
    @OperationId("actions/list-selected-repos-for-org-secret")
    @Docs("https://developer.github.com/v3/actions/secrets/#list-selected-repositories-for-an-organization-secret")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    ListSelectedRepositoriesForOrganizationSecretResponse listSelectedRepositoriesForOrganizationSecret(final ListSelectedRepositoriesForOrganizationSecret listSelectedRepositoriesForOrganizationSecret);

    @GET
    @Path("/orgs/{org}/actions/secrets/{secret_name}/repositories")
    @OperationId("actions/list-selected-repos-for-org-secret")
    @Docs("https://developer.github.com/v3/actions/secrets/#list-selected-repositories-for-an-organization-secret")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    ListSelectedRepositoriesForOrganizationSecretResponse listSelectedRepositoriesForOrganizationSecret(@PathParam("org") final String org, @PathParam("secret_name") final String secretName);

    @GET
    @Path("/orgs/{org}/actions/runners")
    @OperationId("actions/list-self-hosted-runners-for-org")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#list-self-hosted-runners-for-an-organization")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    @Paged(RunnersPage.class)
    Stream<Runner> listSelfHostedRunnersForOrganization(final ListSelfHostedRunnersForOrganization listSelfHostedRunnersForOrganization);

    @GET
    @Path("/orgs/{org}/actions/runners")
    @OperationId("actions/list-self-hosted-runners-for-org")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#list-self-hosted-runners-for-an-organization")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    @Paged(RunnersPage.class)
    Stream<Runner> listSelfHostedRunnersForOrganization(@PathParam("org") final String org);

    @GET
    @Path("/repos/{owner}/{repo}/actions/runners")
    @OperationId("actions/list-self-hosted-runners-for-repo")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#list-self-hosted-runners-for-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    @Paged(RunnersPage.class)
    Stream<Runner> listSelfHostedRunnersForRepository(final ListSelfHostedRunnersForRepository listSelfHostedRunnersForRepository);

    @GET
    @Path("/repos/{owner}/{repo}/actions/runners")
    @OperationId("actions/list-self-hosted-runners-for-repo")
    @Docs("https://developer.github.com/v3/actions/self-hosted-runners/#list-self-hosted-runners-for-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("self-hosted-runners")
    @Paged(RunnersPage.class)
    Stream<Runner> listSelfHostedRunnersForRepository(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/repos/{owner}/{repo}/actions/runs/{run_id}/artifacts")
    @OperationId("actions/list-workflow-run-artifacts")
    @Docs("https://developer.github.com/v3/actions/artifacts/#list-workflow-run-artifacts")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("artifacts")
    @Paged(ArtifactsPage.class)
    Stream<Artifact> listWorkflowRunArtifacts(final ListWorkflowRunArtifacts listWorkflowRunArtifacts);

    @GET
    @Path("/repos/{owner}/{repo}/actions/runs/{run_id}/artifacts")
    @OperationId("actions/list-workflow-run-artifacts")
    @Docs("https://developer.github.com/v3/actions/artifacts/#list-workflow-run-artifacts")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("artifacts")
    @Paged(ArtifactsPage.class)
    Stream<Artifact> listWorkflowRunArtifacts(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("run-id") final int runId);

    @GET
    @Path("/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    @OperationId("actions/list-workflow-runs")
    @Docs("https://developer.github.com/v3/actions/workflow-runs/#list-workflow-runs")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflow-runs")
    @Paged(WorkflowRunsPage.class)
    Stream<WorkflowRun> listWorkflowRuns(final ListWorkflowRuns listWorkflowRuns);

    @GET
    @Path("/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    @OperationId("actions/list-workflow-runs")
    @Docs("https://developer.github.com/v3/actions/workflow-runs/#list-workflow-runs")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflow-runs")
    @Paged(WorkflowRunsPage.class)
    Stream<WorkflowRun> listWorkflowRuns(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("workflow-id") final int workflowId);

    @GET
    @Path("/repos/{owner}/{repo}/actions/runs")
    @OperationId("actions/list-workflow-runs-for-repo")
    @Docs("https://developer.github.com/v3/actions/workflow-runs/#list-workflow-runs-for-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflow-runs")
    @Paged(WorkflowRunsPage.class)
    Stream<WorkflowRun> listWorkflowRunsForRepository(final ListWorkflowRunsForRepository listWorkflowRunsForRepository);

    @GET
    @Path("/repos/{owner}/{repo}/actions/runs")
    @OperationId("actions/list-workflow-runs-for-repo")
    @Docs("https://developer.github.com/v3/actions/workflow-runs/#list-workflow-runs-for-a-repository")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("workflow-runs")
    @Paged(WorkflowRunsPage.class)
    Stream<WorkflowRun> listWorkflowRunsForRepository(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @DELETE
    @Path("/orgs/{org}/actions/secrets/{secret_name}/repositories/{repository_id}")
    @OperationId("actions/remove-selected-repo-from-org-secret")
    @Docs("https://developer.github.com/v3/actions/secrets/#remove-selected-repository-from-an-organization-secret")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    void removeSelectedRepositoryFromOrganizationSecret(final RemoveSelectedRepositoryFromOrganizationSecret removeSelectedRepositoryFromOrganizationSecret);

    @DELETE
    @Path("/orgs/{org}/actions/secrets/{secret_name}/repositories/{repository_id}")
    @OperationId("actions/remove-selected-repo-from-org-secret")
    @Docs("https://developer.github.com/v3/actions/secrets/#remove-selected-repository-from-an-organization-secret")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    void removeSelectedRepositoryFromOrganizationSecret(@PathParam("org") final String org, @PathParam("secret_name") final String secretName, @PathParam("repository_id") final int repositoryId);

    @PUT
    @Path("/orgs/{org}/actions/secrets/{secret_name}/repositories")
    @OperationId("actions/set-selected-repos-for-org-secret")
    @Docs("https://developer.github.com/v3/actions/secrets/#set-selected-repositories-for-an-organization-secret")
    @EnabledForGithubApps
    @Category("actions")
    @Subcategory("secrets")
    void setSelectedRepositoriesForOrganizationSecret(final SetSelectedRepositoriesForOrganizationSecret setSelectedRepositoriesForOrganizationSecret);
}
