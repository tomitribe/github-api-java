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
import org.tomitribe.github.model.BaseGist;
import org.tomitribe.github.model.CheckIfGistIsStarred;
import org.tomitribe.github.model.CreateGist;
import org.tomitribe.github.model.CreateGistComment;
import org.tomitribe.github.model.DeleteGist;
import org.tomitribe.github.model.DeleteGistComment;
import org.tomitribe.github.model.ForkGist;
import org.tomitribe.github.model.GetGist;
import org.tomitribe.github.model.GetGistComment;
import org.tomitribe.github.model.GetGistRevision;
import org.tomitribe.github.model.GistComment;
import org.tomitribe.github.model.GistCommit;
import org.tomitribe.github.model.GistFull;
import org.tomitribe.github.model.ListGistComments;
import org.tomitribe.github.model.ListGistCommits;
import org.tomitribe.github.model.ListGistForks;
import org.tomitribe.github.model.ListGistsForAuthenticatedUser;
import org.tomitribe.github.model.ListGistsForUser;
import org.tomitribe.github.model.ListPublicGists;
import org.tomitribe.github.model.ListStarredGists;
import org.tomitribe.github.model.StarGist;
import org.tomitribe.github.model.UnstarGist;
import org.tomitribe.github.model.UpdateGist;
import org.tomitribe.github.model.UpdateGistComment;

public interface GistsClient {

    @GET
    @Path("/gists/{gist_id}/star")
    @OperationId("gists/check-is-starred")
    @Docs("https://developer.github.com/v3/gists/#check-if-a-gist-is-starred")
    @Category("gists")
    void checkIfGistIsStarred(final CheckIfGistIsStarred checkIfGistIsStarred);

    @POST
    @Path("/gists")
    @OperationId("gists/create")
    @Docs("https://developer.github.com/v3/gists/#create-a-gist")
    @Category("gists")
    GistFull createGist(final CreateGist createGist);

    @POST
    @Path("/gists/{gist_id}/comments")
    @OperationId("gists/create-comment")
    @Docs("https://developer.github.com/v3/gists/comments/#create-a-gist-comment")
    @Category("gists")
    @Subcategory("comments")
    GistComment createGistComment(final CreateGistComment createGistComment);

    @DELETE
    @Path("/gists/{gist_id}")
    @OperationId("gists/delete")
    @Docs("https://developer.github.com/v3/gists/#delete-a-gist")
    @Category("gists")
    void deleteGist(final DeleteGist deleteGist);

    @DELETE
    @Path("/gists/{gist_id}/comments/{comment_id}")
    @OperationId("gists/delete-comment")
    @Docs("https://developer.github.com/v3/gists/comments/#delete-a-gist-comment")
    @Category("gists")
    @Subcategory("comments")
    void deleteGistComment(final DeleteGistComment deleteGistComment);

    @POST
    @Path("/gists/{gist_id}/forks")
    @OperationId("gists/fork")
    @Docs("https://developer.github.com/v3/gists/#fork-a-gist")
    @Category("gists")
    BaseGist forkGist(final ForkGist forkGist);

    @GET
    @Path("/gists/{gist_id}")
    @OperationId("gists/get")
    @Docs("https://developer.github.com/v3/gists/#get-a-gist")
    @Category("gists")
    GistFull getGist(final GetGist getGist);

    @GET
    @Path("/gists/{gist_id}/comments/{comment_id}")
    @OperationId("gists/get-comment")
    @Docs("https://developer.github.com/v3/gists/comments/#get-a-gist-comment")
    @Category("gists")
    @Subcategory("comments")
    GistComment getGistComment(final GetGistComment getGistComment);

    @GET
    @Path("/gists/{gist_id}/{sha}")
    @OperationId("gists/get-revision")
    @Docs("https://developer.github.com/v3/gists/#get-a-gist-revision")
    @Category("gists")
    GistFull getGistRevision(final GetGistRevision getGistRevision);

    @GET
    @Path("/gists/{gist_id}/comments")
    @OperationId("gists/list-comments")
    @Docs("https://developer.github.com/v3/gists/comments/#list-gist-comments")
    @Category("gists")
    @Subcategory("comments")
    Stream<GistComment> listGistComments(final ListGistComments listGistComments);

    @GET
    @Path("/gists/{gist_id}/commits")
    @OperationId("gists/list-commits")
    @Docs("https://developer.github.com/v3/gists/#list-gist-commits")
    @Category("gists")
    Stream<GistCommit> listGistCommits(final ListGistCommits listGistCommits);

    @GET
    @Path("/gists/{gist_id}/forks")
    @OperationId("gists/list-forks")
    @Docs("https://developer.github.com/v3/gists/#list-gist-forks")
    @Category("gists")
    Stream<GistFull> listGistForks(final ListGistForks listGistForks);

    @GET
    @Path("/gists")
    @OperationId("gists/list")
    @Docs("https://developer.github.com/v3/gists/#list-gists-for-the-authenticated-user")
    @Category("gists")
    Stream<BaseGist> listGistsForAuthenticatedUser(final ListGistsForAuthenticatedUser listGistsForAuthenticatedUser);

    @GET
    @Path("/users/{username}/gists")
    @OperationId("gists/list-for-user")
    @Docs("https://developer.github.com/v3/gists/#list-gists-for-a-user")
    @Category("gists")
    Stream<BaseGist> listGistsForUser(final ListGistsForUser listGistsForUser);

    @GET
    @Path("/gists/public")
    @OperationId("gists/list-public")
    @Docs("https://developer.github.com/v3/gists/#list-public-gists")
    @Category("gists")
    Stream<BaseGist> listPublicGists(final ListPublicGists listPublicGists);

    @GET
    @Path("/gists/starred")
    @OperationId("gists/list-starred")
    @Docs("https://developer.github.com/v3/gists/#list-starred-gists")
    @Category("gists")
    Stream<BaseGist> listStarredGists(final ListStarredGists listStarredGists);

    @PUT
    @Path("/gists/{gist_id}/star")
    @OperationId("gists/star")
    @Docs("https://developer.github.com/v3/gists/#star-a-gist")
    @Category("gists")
    void starGist(final StarGist starGist);

    @DELETE
    @Path("/gists/{gist_id}/star")
    @OperationId("gists/unstar")
    @Docs("https://developer.github.com/v3/gists/#unstar-a-gist")
    @Category("gists")
    void unstarGist(final UnstarGist unstarGist);

    @PATCH
    @Path("/gists/{gist_id}")
    @OperationId("gists/update")
    @Docs("https://developer.github.com/v3/gists/#update-a-gist")
    @Category("gists")
    GistFull updateGist(final UpdateGist updateGist);

    @PATCH
    @Path("/gists/{gist_id}/comments/{comment_id}")
    @OperationId("gists/update-comment")
    @Docs("https://developer.github.com/v3/gists/comments/#update-a-gist-comment")
    @Category("gists")
    @Subcategory("comments")
    GistComment updateGistComment(final UpdateGistComment updateGistComment);
}
