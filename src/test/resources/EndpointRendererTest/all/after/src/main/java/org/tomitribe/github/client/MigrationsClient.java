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
import org.tomitribe.github.model.CancelImport;
import org.tomitribe.github.model.DeleteOrganizationMigrationArchive;
import org.tomitribe.github.model.DeleteUserMigrationArchive;
import org.tomitribe.github.model.GetCommitAuthors;
import org.tomitribe.github.model.GetImportStatus;
import org.tomitribe.github.model.GetLargeFiles;
import org.tomitribe.github.model.GetOrganizationMigrationStatus;
import org.tomitribe.github.model.GetUserMigrationStatus;
import org.tomitribe.github.model.ListOrganizationMigrations;
import org.tomitribe.github.model.ListRepositoriesForUserMigration;
import org.tomitribe.github.model.ListRepositoriesInOrganizationMigration;
import org.tomitribe.github.model.MapCommitAuthor;
import org.tomitribe.github.model.Migration;
import org.tomitribe.github.model.MinimalRepository;
import org.tomitribe.github.model.PorterAuthor;
import org.tomitribe.github.model.PorterLargeFile;
import org.tomitribe.github.model.StartImport;
import org.tomitribe.github.model.StartOrganizationMigration;
import org.tomitribe.github.model.StartUserMigration;
import org.tomitribe.github.model.UnlockOrganizationRepository;
import org.tomitribe.github.model.UnlockUserRepository;
import org.tomitribe.github.model.UpdateGitLFSPreference;
import org.tomitribe.github.model.UpdateImport;
import org.tomitribe.github.model._import;

public interface MigrationsClient {

    @DELETE
    @Path("/repos/{owner}/{repo}/import")
    @OperationId("migrations/cancel-import")
    @Docs("https://developer.github.com/v3/migrations/source_imports/#cancel-an-import")
    @EnabledForGithubApps
    @Category("migrations")
    @Subcategory("source-imports")
    void cancelImport(final CancelImport cancelImport);

    @DELETE
    @Path("/repos/{owner}/{repo}/import")
    @OperationId("migrations/cancel-import")
    @Docs("https://developer.github.com/v3/migrations/source_imports/#cancel-an-import")
    @EnabledForGithubApps
    @Category("migrations")
    @Subcategory("source-imports")
    void cancelImport(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @DELETE
    @Path("/orgs/{org}/migrations/{migration_id}/archive")
    @OperationId("migrations/delete-archive-for-org")
    @Docs("https://developer.github.com/v3/migrations/orgs/#delete-an-organization-migration-archive")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("orgs")
    void deleteOrganizationMigrationArchive(final DeleteOrganizationMigrationArchive deleteOrganizationMigrationArchive);

    @DELETE
    @Path("/orgs/{org}/migrations/{migration_id}/archive")
    @OperationId("migrations/delete-archive-for-org")
    @Docs("https://developer.github.com/v3/migrations/orgs/#delete-an-organization-migration-archive")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("orgs")
    void deleteOrganizationMigrationArchive(@PathParam("org") final String org, @PathParam("migration_id") final int migrationId);

    @DELETE
    @Path("/user/migrations/{migration_id}/archive")
    @OperationId("migrations/delete-archive-for-authenticated-user")
    @Docs("https://developer.github.com/v3/migrations/users/#delete-a-user-migration-archive")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("users")
    void deleteUserMigrationArchive(final DeleteUserMigrationArchive deleteUserMigrationArchive);

    @DELETE
    @Path("/user/migrations/{migration_id}/archive")
    @OperationId("migrations/delete-archive-for-authenticated-user")
    @Docs("https://developer.github.com/v3/migrations/users/#delete-a-user-migration-archive")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("users")
    void deleteUserMigrationArchive(@PathParam("migration_id") final int migrationId);

    @GET
    @Path("/repos/{owner}/{repo}/import/authors")
    @OperationId("migrations/get-commit-authors")
    @Docs("https://developer.github.com/v3/migrations/source_imports/#get-commit-authors")
    @EnabledForGithubApps
    @Category("migrations")
    @Subcategory("source-imports")
    Stream<PorterAuthor> getCommitAuthors(final GetCommitAuthors getCommitAuthors);

    @GET
    @Path("/repos/{owner}/{repo}/import/authors")
    @OperationId("migrations/get-commit-authors")
    @Docs("https://developer.github.com/v3/migrations/source_imports/#get-commit-authors")
    @EnabledForGithubApps
    @Category("migrations")
    @Subcategory("source-imports")
    Stream<PorterAuthor> getCommitAuthors(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/repos/{owner}/{repo}/import")
    @OperationId("migrations/get-import-status")
    @Docs("https://developer.github.com/v3/migrations/source_imports/#get-an-import-status")
    @EnabledForGithubApps
    @Category("migrations")
    @Subcategory("source-imports")
    _import getImportStatus(final GetImportStatus getImportStatus);

    @GET
    @Path("/repos/{owner}/{repo}/import")
    @OperationId("migrations/get-import-status")
    @Docs("https://developer.github.com/v3/migrations/source_imports/#get-an-import-status")
    @EnabledForGithubApps
    @Category("migrations")
    @Subcategory("source-imports")
    _import getImportStatus(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/repos/{owner}/{repo}/import/large_files")
    @OperationId("migrations/get-large-files")
    @Docs("https://developer.github.com/v3/migrations/source_imports/#get-large-files")
    @EnabledForGithubApps
    @Category("migrations")
    @Subcategory("source-imports")
    Stream<PorterLargeFile> getLargeFiles(final GetLargeFiles getLargeFiles);

    @GET
    @Path("/repos/{owner}/{repo}/import/large_files")
    @OperationId("migrations/get-large-files")
    @Docs("https://developer.github.com/v3/migrations/source_imports/#get-large-files")
    @EnabledForGithubApps
    @Category("migrations")
    @Subcategory("source-imports")
    Stream<PorterLargeFile> getLargeFiles(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/orgs/{org}/migrations/{migration_id}")
    @OperationId("migrations/get-status-for-org")
    @Docs("https://developer.github.com/v3/migrations/orgs/#get-an-organization-migration-status")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("orgs")
    Migration getOrganizationMigrationStatus(final GetOrganizationMigrationStatus getOrganizationMigrationStatus);

    @GET
    @Path("/orgs/{org}/migrations/{migration_id}")
    @OperationId("migrations/get-status-for-org")
    @Docs("https://developer.github.com/v3/migrations/orgs/#get-an-organization-migration-status")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("orgs")
    Migration getOrganizationMigrationStatus(@PathParam("org") final String org, @PathParam("migration_id") final int migrationId);

    @GET
    @Path("/user/migrations/{migration_id}")
    @OperationId("migrations/get-status-for-authenticated-user")
    @Docs("https://developer.github.com/v3/migrations/users/#get-a-user-migration-status")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("users")
    Migration getUserMigrationStatus(final GetUserMigrationStatus getUserMigrationStatus);

    @GET
    @Path("/user/migrations/{migration_id}")
    @OperationId("migrations/get-status-for-authenticated-user")
    @Docs("https://developer.github.com/v3/migrations/users/#get-a-user-migration-status")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("users")
    Migration getUserMigrationStatus(@PathParam("migration_id") final int migrationId);

    @GET
    @Path("/orgs/{org}/migrations")
    @OperationId("migrations/list-for-org")
    @Docs("https://developer.github.com/v3/migrations/orgs/#list-organization-migrations")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("orgs")
    @Paged(Migration[].class)
    Stream<Migration> listOrganizationMigrations(final ListOrganizationMigrations listOrganizationMigrations);

    @GET
    @Path("/orgs/{org}/migrations")
    @OperationId("migrations/list-for-org")
    @Docs("https://developer.github.com/v3/migrations/orgs/#list-organization-migrations")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("orgs")
    @Paged(Migration[].class)
    Stream<Migration> listOrganizationMigrations(@PathParam("org") final String org);

    @GET
    @Path("/user/migrations/{migration_id}/repositories")
    @OperationId("migrations/list-repos-for-user")
    @Docs("https://developer.github.com/v3/migrations/users/#list-repositories-for-a-user-migration")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("users")
    @Paged(MinimalRepository[].class)
    Stream<MinimalRepository> listRepositoriesForUserMigration(final ListRepositoriesForUserMigration listRepositoriesForUserMigration);

    @GET
    @Path("/user/migrations/{migration_id}/repositories")
    @OperationId("migrations/list-repos-for-user")
    @Docs("https://developer.github.com/v3/migrations/users/#list-repositories-for-a-user-migration")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("users")
    @Paged(MinimalRepository[].class)
    Stream<MinimalRepository> listRepositoriesForUserMigration(@PathParam("migration_id") final int migrationId);

    @GET
    @Path("/orgs/{org}/migrations/{migration_id}/repositories")
    @OperationId("migrations/list-repos-for-org")
    @Docs("https://developer.github.com/v3/migrations/orgs/#list-repositories-in-an-organization-migration")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("orgs")
    @Paged(MinimalRepository[].class)
    Stream<MinimalRepository> listRepositoriesInOrganizationMigration(final ListRepositoriesInOrganizationMigration listRepositoriesInOrganizationMigration);

    @GET
    @Path("/orgs/{org}/migrations/{migration_id}/repositories")
    @OperationId("migrations/list-repos-for-org")
    @Docs("https://developer.github.com/v3/migrations/orgs/#list-repositories-in-an-organization-migration")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("orgs")
    @Paged(MinimalRepository[].class)
    Stream<MinimalRepository> listRepositoriesInOrganizationMigration(@PathParam("org") final String org, @PathParam("migration_id") final int migrationId);

    @GET
    @Path("/user/migrations")
    @OperationId("migrations/list-for-authenticated-user")
    @Docs("https://developer.github.com/v3/migrations/users/#list-user-migrations")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("users")
    @Paged(Migration[].class)
    Stream<Migration> listUserMigrations();

    @PATCH
    @Path("/repos/{owner}/{repo}/import/authors/{author_id}")
    @OperationId("migrations/map-commit-author")
    @Docs("https://developer.github.com/v3/migrations/source_imports/#map-a-commit-author")
    @EnabledForGithubApps
    @Category("migrations")
    @Subcategory("source-imports")
    PorterAuthor mapCommitAuthor(final MapCommitAuthor mapCommitAuthor);

    @PUT
    @Path("/repos/{owner}/{repo}/import")
    @OperationId("migrations/start-import")
    @Docs("https://developer.github.com/v3/migrations/source_imports/#start-an-import")
    @EnabledForGithubApps
    @Category("migrations")
    @Subcategory("source-imports")
    _import startImport(final StartImport startImport);

    @POST
    @Path("/orgs/{org}/migrations")
    @OperationId("migrations/start-for-org")
    @Docs("https://developer.github.com/v3/migrations/orgs/#start-an-organization-migration")
    @Category("migrations")
    @Subcategory("orgs")
    Migration startOrganizationMigration(final StartOrganizationMigration startOrganizationMigration);

    @POST
    @Path("/user/migrations")
    @OperationId("migrations/start-for-authenticated-user")
    @Docs("https://developer.github.com/v3/migrations/users/#start-a-user-migration")
    @Category("migrations")
    @Subcategory("users")
    Migration startUserMigration(final StartUserMigration startUserMigration);

    @DELETE
    @Path("/orgs/{org}/migrations/{migration_id}/repos/{repo_name}/lock")
    @OperationId("migrations/unlock-repo-for-org")
    @Docs("https://developer.github.com/v3/migrations/orgs/#unlock-an-organization-repository")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("orgs")
    void unlockOrganizationRepository(final UnlockOrganizationRepository unlockOrganizationRepository);

    @DELETE
    @Path("/orgs/{org}/migrations/{migration_id}/repos/{repo_name}/lock")
    @OperationId("migrations/unlock-repo-for-org")
    @Docs("https://developer.github.com/v3/migrations/orgs/#unlock-an-organization-repository")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("orgs")
    void unlockOrganizationRepository(@PathParam("org") final String org, @PathParam("migration_id") final int migrationId, @PathParam("repo_name") final String repoName);

    @DELETE
    @Path("/user/migrations/{migration_id}/repos/{repo_name}/lock")
    @OperationId("migrations/unlock-repo-for-authenticated-user")
    @Docs("https://developer.github.com/v3/migrations/users/#unlock-a-user-repository")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("users")
    void unlockUserRepository(final UnlockUserRepository unlockUserRepository);

    @DELETE
    @Path("/user/migrations/{migration_id}/repos/{repo_name}/lock")
    @OperationId("migrations/unlock-repo-for-authenticated-user")
    @Docs("https://developer.github.com/v3/migrations/users/#unlock-a-user-repository")
    @Preview("wyandotte")
    @Category("migrations")
    @Subcategory("users")
    void unlockUserRepository(@PathParam("migration_id") final int migrationId, @PathParam("repo_name") final String repoName);

    @PATCH
    @Path("/repos/{owner}/{repo}/import/lfs")
    @OperationId("migrations/set-lfs-preference")
    @Docs("https://developer.github.com/v3/migrations/source_imports/#update-git-lfs-preference")
    @EnabledForGithubApps
    @Category("migrations")
    @Subcategory("source-imports")
    _import updateGitLFSPreference(final UpdateGitLFSPreference updateGitLFSPreference);

    @PATCH
    @Path("/repos/{owner}/{repo}/import")
    @OperationId("migrations/update-import")
    @Docs("https://developer.github.com/v3/migrations/source_imports/#update-an-import")
    @EnabledForGithubApps
    @Category("migrations")
    @Subcategory("source-imports")
    _import updateImport(final UpdateImport updateImport);
}
