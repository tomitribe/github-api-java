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
import javax.ws.rs.Path;
import org.tomitribe.github.model.Blob;
import org.tomitribe.github.model.CreateBlob;
import org.tomitribe.github.model.CreateCommit;
import org.tomitribe.github.model.CreateReference;
import org.tomitribe.github.model.CreateTagObject;
import org.tomitribe.github.model.CreateTree;
import org.tomitribe.github.model.DeleteReference;
import org.tomitribe.github.model.GetBlob;
import org.tomitribe.github.model.GetCommit;
import org.tomitribe.github.model.GetReference;
import org.tomitribe.github.model.GetTag;
import org.tomitribe.github.model.GetTree;
import org.tomitribe.github.model.GitCommit;
import org.tomitribe.github.model.GitRef;
import org.tomitribe.github.model.GitTag;
import org.tomitribe.github.model.GitTree;
import org.tomitribe.github.model.ListMatchingReferences;
import org.tomitribe.github.model.ShortBlob;
import org.tomitribe.github.model.UpdateReference;

public interface GitClient {

    @POST
    @Path("/repos/{owner}/{repo}/git/blobs")
    @OperationId("git/create-blob")
    @Docs("https://developer.github.com/v3/git/blobs/#create-a-blob")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("blobs")
    ShortBlob createBlob(final CreateBlob createBlob);

    @POST
    @Path("/repos/{owner}/{repo}/git/commits")
    @OperationId("git/create-commit")
    @Docs("https://developer.github.com/v3/git/commits/#create-a-commit")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("commits")
    GitCommit createCommit(final CreateCommit createCommit);

    @POST
    @Path("/repos/{owner}/{repo}/git/refs")
    @OperationId("git/create-ref")
    @Docs("https://developer.github.com/v3/git/refs/#create-a-reference")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("refs")
    GitRef createReference(final CreateReference createReference);

    @POST
    @Path("/repos/{owner}/{repo}/git/tags")
    @OperationId("git/create-tag")
    @Docs("https://developer.github.com/v3/git/tags/#create-a-tag-object")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("tags")
    GitTag createTagObject(final CreateTagObject createTagObject);

    @POST
    @Path("/repos/{owner}/{repo}/git/trees")
    @OperationId("git/create-tree")
    @Docs("https://developer.github.com/v3/git/trees/#create-a-tree")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("trees")
    GitTree createTree(final CreateTree createTree);

    @DELETE
    @Path("/repos/{owner}/{repo}/git/refs/{ref}")
    @OperationId("git/delete-ref")
    @Docs("https://developer.github.com/v3/git/refs/#delete-a-reference")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("refs")
    void deleteReference(final DeleteReference deleteReference);

    @DELETE
    @Path("/repos/{owner}/{repo}/git/refs/{ref}")
    @OperationId("git/delete-ref")
    @Docs("https://developer.github.com/v3/git/refs/#delete-a-reference")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("refs")
    void deleteReference(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("ref") final String ref);

    @GET
    @Path("/repos/{owner}/{repo}/git/blobs/{file_sha}")
    @OperationId("git/get-blob")
    @Docs("https://developer.github.com/v3/git/blobs/#get-a-blob")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("blobs")
    Blob getBlob(final GetBlob getBlob);

    @GET
    @Path("/repos/{owner}/{repo}/git/blobs/{file_sha}")
    @OperationId("git/get-blob")
    @Docs("https://developer.github.com/v3/git/blobs/#get-a-blob")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("blobs")
    Blob getBlob(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("file_sha") final String fileSha);

    @GET
    @Path("/repos/{owner}/{repo}/git/commits/{commit_sha}")
    @OperationId("git/get-commit")
    @Docs("https://developer.github.com/v3/git/commits/#get-a-commit")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("commits")
    GitCommit getCommit(final GetCommit getCommit);

    @GET
    @Path("/repos/{owner}/{repo}/git/commits/{commit_sha}")
    @OperationId("git/get-commit")
    @Docs("https://developer.github.com/v3/git/commits/#get-a-commit")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("commits")
    GitCommit getCommit(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("commit_sha") final String commitSha);

    @GET
    @Path("/repos/{owner}/{repo}/git/ref/{ref}")
    @OperationId("git/get-ref")
    @Docs("https://developer.github.com/v3/git/refs/#get-a-reference")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("refs")
    GitRef getReference(final GetReference getReference);

    @GET
    @Path("/repos/{owner}/{repo}/git/ref/{ref}")
    @OperationId("git/get-ref")
    @Docs("https://developer.github.com/v3/git/refs/#get-a-reference")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("refs")
    GitRef getReference(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("ref") final String ref);

    @GET
    @Path("/repos/{owner}/{repo}/git/tags/{tag_sha}")
    @OperationId("git/get-tag")
    @Docs("https://developer.github.com/v3/git/tags/#get-a-tag")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("tags")
    GitTag getTag(final GetTag getTag);

    @GET
    @Path("/repos/{owner}/{repo}/git/tags/{tag_sha}")
    @OperationId("git/get-tag")
    @Docs("https://developer.github.com/v3/git/tags/#get-a-tag")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("tags")
    GitTag getTag(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("tag_sha") final String tagSha);

    @GET
    @Path("/repos/{owner}/{repo}/git/trees/{tree_sha}")
    @OperationId("git/get-tree")
    @Docs("https://developer.github.com/v3/git/trees/#get-a-tree")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("trees")
    GitTree getTree(final GetTree getTree);

    @GET
    @Path("/repos/{owner}/{repo}/git/trees/{tree_sha}")
    @OperationId("git/get-tree")
    @Docs("https://developer.github.com/v3/git/trees/#get-a-tree")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("trees")
    GitTree getTree(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("tree_sha") final String treeSha);

    @GET
    @Path("/repos/{owner}/{repo}/git/matching-refs/{ref}")
    @OperationId("git/list-matching-refs")
    @Docs("https://developer.github.com/v3/git/refs/#list-matching-references")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("refs")
    @Paged(GitRef[].class)
    Stream<GitRef> listMatchingReferences(final ListMatchingReferences listMatchingReferences);

    @GET
    @Path("/repos/{owner}/{repo}/git/matching-refs/{ref}")
    @OperationId("git/list-matching-refs")
    @Docs("https://developer.github.com/v3/git/refs/#list-matching-references")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("refs")
    @Paged(GitRef[].class)
    Stream<GitRef> listMatchingReferences(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("ref") final String ref);

    @PATCH
    @Path("/repos/{owner}/{repo}/git/refs/{ref}")
    @OperationId("git/update-ref")
    @Docs("https://developer.github.com/v3/git/refs/#update-a-reference")
    @EnabledForGithubApps
    @Category("git")
    @Subcategory("refs")
    GitRef updateReference(final UpdateReference updateReference);
}
