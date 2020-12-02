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
import org.tomitribe.github.model.AddProjectCollaborator;
import org.tomitribe.github.model.CreateOrganizationProject;
import org.tomitribe.github.model.CreateProjectCard;
import org.tomitribe.github.model.CreateProjectColumn;
import org.tomitribe.github.model.CreateRepositoryProject;
import org.tomitribe.github.model.CreateUserProject;
import org.tomitribe.github.model.DeleteProject;
import org.tomitribe.github.model.DeleteProjectCard;
import org.tomitribe.github.model.DeleteProjectColumn;
import org.tomitribe.github.model.GetProject;
import org.tomitribe.github.model.GetProjectCard;
import org.tomitribe.github.model.GetProjectColumn;
import org.tomitribe.github.model.GetProjectPermissionForUser;
import org.tomitribe.github.model.ListOrganizationProjects;
import org.tomitribe.github.model.ListProjectCards;
import org.tomitribe.github.model.ListProjectCollaborators;
import org.tomitribe.github.model.ListProjectColumns;
import org.tomitribe.github.model.ListRepositoryProjects;
import org.tomitribe.github.model.ListUserProjects;
import org.tomitribe.github.model.MoveProjectCard;
import org.tomitribe.github.model.MoveProjectCardResponse;
import org.tomitribe.github.model.MoveProjectColumn;
import org.tomitribe.github.model.MoveProjectColumnResponse;
import org.tomitribe.github.model.Project;
import org.tomitribe.github.model.ProjectCard;
import org.tomitribe.github.model.ProjectColumn;
import org.tomitribe.github.model.RemoveUserAsCollaborator;
import org.tomitribe.github.model.RepositoryCollaboratorPermission;
import org.tomitribe.github.model.SimpleUser;
import org.tomitribe.github.model.UpdateExistingProjectCard;
import org.tomitribe.github.model.UpdateExistingProjectColumn;
import org.tomitribe.github.model.UpdateProject;

public interface ProjectsClient {

    @PUT
    @Path("/projects/{project_id}/collaborators/{username}")
    @OperationId("projects/add-collaborator")
    @Docs("https://developer.github.com/v3/projects/collaborators/#add-project-collaborator")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("collaborators")
    void addProjectCollaborator(final AddProjectCollaborator addProjectCollaborator);

    @POST
    @Path("/orgs/{org}/projects")
    @OperationId("projects/create-for-org")
    @Docs("https://developer.github.com/v3/projects/#create-an-organization-project")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    Project createOrganizationProject(final CreateOrganizationProject createOrganizationProject);

    @POST
    @Path("/projects/columns/{column_id}/cards")
    @OperationId("projects/create-card")
    @Docs("https://developer.github.com/v3/projects/cards/#create-a-project-card")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("cards")
    ProjectCard createProjectCard(final CreateProjectCard createProjectCard);

    @POST
    @Path("/projects/columns/{column_id}/cards")
    @OperationId("projects/create-card")
    @Docs("https://developer.github.com/v3/projects/cards/#create-a-project-card")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("cards")
    ProjectCard createProjectCard(@PathParam("column_id") final int columnId);

    @POST
    @Path("/projects/{project_id}/columns")
    @OperationId("projects/create-column")
    @Docs("https://developer.github.com/v3/projects/columns/#create-a-project-column")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("columns")
    ProjectColumn createProjectColumn(final CreateProjectColumn createProjectColumn);

    @POST
    @Path("/repos/{owner}/{repo}/projects")
    @OperationId("projects/create-for-repo")
    @Docs("https://developer.github.com/v3/projects/#create-a-repository-project")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    Project createRepositoryProject(final CreateRepositoryProject createRepositoryProject);

    @POST
    @Path("/user/projects")
    @OperationId("projects/create-for-authenticated-user")
    @Docs("https://developer.github.com/v3/projects/#create-a-user-project")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    Project createUserProject(final CreateUserProject createUserProject);

    @DELETE
    @Path("/projects/{project_id}")
    @OperationId("projects/delete")
    @Docs("https://developer.github.com/v3/projects/#delete-a-project")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    void deleteProject(final DeleteProject deleteProject);

    @DELETE
    @Path("/projects/{project_id}")
    @OperationId("projects/delete")
    @Docs("https://developer.github.com/v3/projects/#delete-a-project")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    void deleteProject(@PathParam("project-id") final int projectId);

    @DELETE
    @Path("/projects/columns/cards/{card_id}")
    @OperationId("projects/delete-card")
    @Docs("https://developer.github.com/v3/projects/cards/#delete-a-project-card")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("cards")
    void deleteProjectCard(final DeleteProjectCard deleteProjectCard);

    @DELETE
    @Path("/projects/columns/cards/{card_id}")
    @OperationId("projects/delete-card")
    @Docs("https://developer.github.com/v3/projects/cards/#delete-a-project-card")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("cards")
    void deleteProjectCard(@PathParam("card_id") final int cardId);

    @DELETE
    @Path("/projects/columns/{column_id}")
    @OperationId("projects/delete-column")
    @Docs("https://developer.github.com/v3/projects/columns/#delete-a-project-column")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("columns")
    void deleteProjectColumn(final DeleteProjectColumn deleteProjectColumn);

    @DELETE
    @Path("/projects/columns/{column_id}")
    @OperationId("projects/delete-column")
    @Docs("https://developer.github.com/v3/projects/columns/#delete-a-project-column")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("columns")
    void deleteProjectColumn(@PathParam("column_id") final int columnId);

    @GET
    @Path("/projects/{project_id}")
    @OperationId("projects/get")
    @Docs("https://developer.github.com/v3/projects/#get-a-project")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    Project getProject(final GetProject getProject);

    @GET
    @Path("/projects/{project_id}")
    @OperationId("projects/get")
    @Docs("https://developer.github.com/v3/projects/#get-a-project")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    Project getProject(@PathParam("project-id") final int projectId);

    @GET
    @Path("/projects/columns/cards/{card_id}")
    @OperationId("projects/get-card")
    @Docs("https://developer.github.com/v3/projects/cards/#get-a-project-card")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("cards")
    ProjectCard getProjectCard(final GetProjectCard getProjectCard);

    @GET
    @Path("/projects/columns/cards/{card_id}")
    @OperationId("projects/get-card")
    @Docs("https://developer.github.com/v3/projects/cards/#get-a-project-card")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("cards")
    ProjectCard getProjectCard(@PathParam("card_id") final int cardId);

    @GET
    @Path("/projects/columns/{column_id}")
    @OperationId("projects/get-column")
    @Docs("https://developer.github.com/v3/projects/columns/#get-a-project-column")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("columns")
    ProjectColumn getProjectColumn(final GetProjectColumn getProjectColumn);

    @GET
    @Path("/projects/columns/{column_id}")
    @OperationId("projects/get-column")
    @Docs("https://developer.github.com/v3/projects/columns/#get-a-project-column")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("columns")
    ProjectColumn getProjectColumn(@PathParam("column_id") final int columnId);

    @GET
    @Path("/projects/{project_id}/collaborators/{username}/permission")
    @OperationId("projects/get-permission-for-user")
    @Docs("https://developer.github.com/v3/projects/collaborators/#get-project-permission-for-a-user")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("collaborators")
    RepositoryCollaboratorPermission getProjectPermissionForUser(final GetProjectPermissionForUser getProjectPermissionForUser);

    @GET
    @Path("/projects/{project_id}/collaborators/{username}/permission")
    @OperationId("projects/get-permission-for-user")
    @Docs("https://developer.github.com/v3/projects/collaborators/#get-project-permission-for-a-user")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("collaborators")
    RepositoryCollaboratorPermission getProjectPermissionForUser(@PathParam("project-id") final int projectId, @PathParam("username") final String username);

    @GET
    @Path("/orgs/{org}/projects")
    @OperationId("projects/list-for-org")
    @Docs("https://developer.github.com/v3/projects/#list-organization-projects")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Paged(Project[].class)
    Stream<Project> listOrganizationProjects(final ListOrganizationProjects listOrganizationProjects);

    @GET
    @Path("/orgs/{org}/projects")
    @OperationId("projects/list-for-org")
    @Docs("https://developer.github.com/v3/projects/#list-organization-projects")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Paged(Project[].class)
    Stream<Project> listOrganizationProjects(@PathParam("org") final String org);

    @GET
    @Path("/projects/columns/{column_id}/cards")
    @OperationId("projects/list-cards")
    @Docs("https://developer.github.com/v3/projects/cards/#list-project-cards")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("cards")
    @Paged(ProjectCard[].class)
    Stream<ProjectCard> listProjectCards(final ListProjectCards listProjectCards);

    @GET
    @Path("/projects/columns/{column_id}/cards")
    @OperationId("projects/list-cards")
    @Docs("https://developer.github.com/v3/projects/cards/#list-project-cards")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("cards")
    @Paged(ProjectCard[].class)
    Stream<ProjectCard> listProjectCards(@PathParam("column_id") final int columnId);

    @GET
    @Path("/projects/{project_id}/collaborators")
    @OperationId("projects/list-collaborators")
    @Docs("https://developer.github.com/v3/projects/collaborators/#list-project-collaborators")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("collaborators")
    @Paged(SimpleUser[].class)
    Stream<SimpleUser> listProjectCollaborators(final ListProjectCollaborators listProjectCollaborators);

    @GET
    @Path("/projects/{project_id}/collaborators")
    @OperationId("projects/list-collaborators")
    @Docs("https://developer.github.com/v3/projects/collaborators/#list-project-collaborators")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("collaborators")
    @Paged(SimpleUser[].class)
    Stream<SimpleUser> listProjectCollaborators(@PathParam("project-id") final int projectId);

    @GET
    @Path("/projects/{project_id}/columns")
    @OperationId("projects/list-columns")
    @Docs("https://developer.github.com/v3/projects/columns/#list-project-columns")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("columns")
    @Paged(ProjectColumn[].class)
    Stream<ProjectColumn> listProjectColumns(final ListProjectColumns listProjectColumns);

    @GET
    @Path("/projects/{project_id}/columns")
    @OperationId("projects/list-columns")
    @Docs("https://developer.github.com/v3/projects/columns/#list-project-columns")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("columns")
    @Paged(ProjectColumn[].class)
    Stream<ProjectColumn> listProjectColumns(@PathParam("project-id") final int projectId);

    @GET
    @Path("/repos/{owner}/{repo}/projects")
    @OperationId("projects/list-for-repo")
    @Docs("https://developer.github.com/v3/projects/#list-repository-projects")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Paged(Project[].class)
    Stream<Project> listRepositoryProjects(final ListRepositoryProjects listRepositoryProjects);

    @GET
    @Path("/repos/{owner}/{repo}/projects")
    @OperationId("projects/list-for-repo")
    @Docs("https://developer.github.com/v3/projects/#list-repository-projects")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Paged(Project[].class)
    Stream<Project> listRepositoryProjects(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/users/{username}/projects")
    @OperationId("projects/list-for-user")
    @Docs("https://developer.github.com/v3/projects/#list-user-projects")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Paged(Project[].class)
    Stream<Project> listUserProjects(final ListUserProjects listUserProjects);

    @GET
    @Path("/users/{username}/projects")
    @OperationId("projects/list-for-user")
    @Docs("https://developer.github.com/v3/projects/#list-user-projects")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Paged(Project[].class)
    Stream<Project> listUserProjects(@PathParam("username") final String username);

    @POST
    @Path("/projects/columns/cards/{card_id}/moves")
    @OperationId("projects/move-card")
    @Docs("https://developer.github.com/v3/projects/cards/#move-a-project-card")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("cards")
    MoveProjectCardResponse moveProjectCard(final MoveProjectCard moveProjectCard);

    @POST
    @Path("/projects/columns/{column_id}/moves")
    @OperationId("projects/move-column")
    @Docs("https://developer.github.com/v3/projects/columns/#move-a-project-column")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("columns")
    MoveProjectColumnResponse moveProjectColumn(final MoveProjectColumn moveProjectColumn);

    @DELETE
    @Path("/projects/{project_id}/collaborators/{username}")
    @OperationId("projects/remove-collaborator")
    @Docs("https://developer.github.com/v3/projects/collaborators/#remove-project-collaborator")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("collaborators")
    void removeUserAsCollaborator(final RemoveUserAsCollaborator removeUserAsCollaborator);

    @DELETE
    @Path("/projects/{project_id}/collaborators/{username}")
    @OperationId("projects/remove-collaborator")
    @Docs("https://developer.github.com/v3/projects/collaborators/#remove-project-collaborator")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("collaborators")
    void removeUserAsCollaborator(@PathParam("project-id") final int projectId, @PathParam("username") final String username);

    @PATCH
    @Path("/projects/columns/cards/{card_id}")
    @OperationId("projects/update-card")
    @Docs("https://developer.github.com/v3/projects/cards/#update-a-project-card")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("cards")
    ProjectCard updateExistingProjectCard(final UpdateExistingProjectCard updateExistingProjectCard);

    @PATCH
    @Path("/projects/columns/{column_id}")
    @OperationId("projects/update-column")
    @Docs("https://developer.github.com/v3/projects/columns/#update-a-project-column")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    @Subcategory("columns")
    ProjectColumn updateExistingProjectColumn(final UpdateExistingProjectColumn updateExistingProjectColumn);

    @PATCH
    @Path("/projects/{project_id}")
    @OperationId("projects/update")
    @Docs("https://developer.github.com/v3/projects/#update-a-project")
    @EnabledForGithubApps
    @Preview("inertia")
    @Category("projects")
    Project updateProject(final UpdateProject updateProject);
}
