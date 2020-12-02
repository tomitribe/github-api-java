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
import org.tomitribe.github.model.CheckIfPullRequestHasBeenMerged;
import org.tomitribe.github.model.CreatePullRequest;
import org.tomitribe.github.model.CreateReplyForReviewComment;
import org.tomitribe.github.model.CreateReviewCommentForPullRequest;
import org.tomitribe.github.model.CreateReviewForPullRequest;
import org.tomitribe.github.model.DeletePendingReviewForPullRequest;
import org.tomitribe.github.model.DeleteReviewCommentForPullRequest;
import org.tomitribe.github.model.DiffEntry;
import org.tomitribe.github.model.DismissReviewForPullRequest;
import org.tomitribe.github.model.GetPullRequest;
import org.tomitribe.github.model.GetReviewCommentForPullRequest;
import org.tomitribe.github.model.GetReviewForPullRequest;
import org.tomitribe.github.model.ListCommentsForPullRequestReview;
import org.tomitribe.github.model.ListCommitsOnPullRequest;
import org.tomitribe.github.model.ListPullRequests;
import org.tomitribe.github.model.ListPullRequestsFiles;
import org.tomitribe.github.model.ListRequestedReviewersForPullRequest;
import org.tomitribe.github.model.ListReviewCommentsInRepository;
import org.tomitribe.github.model.ListReviewCommentsOnPullRequest;
import org.tomitribe.github.model.ListReviewsForPullRequest;
import org.tomitribe.github.model.MergePullRequest;
import org.tomitribe.github.model.PullRequest;
import org.tomitribe.github.model.PullRequestMergeResult;
import org.tomitribe.github.model.PullRequestReview;
import org.tomitribe.github.model.PullRequestReviewComment;
import org.tomitribe.github.model.PullRequestReviewRequest;
import org.tomitribe.github.model.PullRequestSimple;
import org.tomitribe.github.model.RequestReviewersForPullRequest;
import org.tomitribe.github.model.ReviewComment;
import org.tomitribe.github.model.SimpleCommit;
import org.tomitribe.github.model.SubmitReviewForPullRequest;
import org.tomitribe.github.model.UpdatePullRequest;
import org.tomitribe.github.model.UpdatePullRequestBranch;
import org.tomitribe.github.model.UpdatePullRequestBranchResponse;
import org.tomitribe.github.model.UpdateReviewCommentForPullRequest;
import org.tomitribe.github.model.UpdateReviewForPullRequest;

public interface PullsClient {

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    @OperationId("pulls/check-if-merged")
    @Docs("https://developer.github.com/v3/pulls/#check-if-a-pull-request-has-been-merged")
    @EnabledForGithubApps
    @Category("pulls")
    void checkIfPullRequestHasBeenMerged(final CheckIfPullRequestHasBeenMerged checkIfPullRequestHasBeenMerged);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    @OperationId("pulls/check-if-merged")
    @Docs("https://developer.github.com/v3/pulls/#check-if-a-pull-request-has-been-merged")
    @EnabledForGithubApps
    @Category("pulls")
    void checkIfPullRequestHasBeenMerged(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("pull-number") final int pullNumber);

    @POST
    @Path("/repos/{owner}/{repo}/pulls")
    @OperationId("pulls/create")
    @Docs("https://developer.github.com/v3/pulls/#create-a-pull-request")
    @EnabledForGithubApps
    @Preview("sailor-v")
    @Category("pulls")
    PullRequest createPullRequest(final CreatePullRequest createPullRequest);

    @POST
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/comments/{comment_id}/replies")
    @OperationId("pulls/create-reply-for-review-comment")
    @Docs("https://developer.github.com/v3/pulls/comments/#create-a-reply-for-a-review-comment")
    @Category("pulls")
    @Subcategory("comments")
    PullRequestReviewComment createReplyForReviewComment(final CreateReplyForReviewComment createReplyForReviewComment);

    @POST
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    @OperationId("pulls/create-review-comment")
    @Docs("https://developer.github.com/v3/pulls/comments/#create-a-review-comment-for-a-pull-request")
    @EnabledForGithubApps
    @Preview("comfort-fade")
    @Category("pulls")
    @Subcategory("comments")
    PullRequestReviewComment createReviewCommentForPullRequest(final CreateReviewCommentForPullRequest createReviewCommentForPullRequest);

    @POST
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    @OperationId("pulls/create-review")
    @Docs("https://developer.github.com/v3/pulls/reviews/#create-a-review-for-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    @Subcategory("reviews")
    PullRequestReview createReviewForPullRequest(final CreateReviewForPullRequest createReviewForPullRequest);

    @DELETE
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    @OperationId("pulls/delete-pending-review")
    @Docs("https://developer.github.com/v3/pulls/reviews/#delete-a-pending-review-for-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    @Subcategory("reviews")
    PullRequestReview deletePendingReviewForPullRequest(final DeletePendingReviewForPullRequest deletePendingReviewForPullRequest);

    @DELETE
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    @OperationId("pulls/delete-pending-review")
    @Docs("https://developer.github.com/v3/pulls/reviews/#delete-a-pending-review-for-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    @Subcategory("reviews")
    PullRequestReview deletePendingReviewForPullRequest(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("pull-number") final int pullNumber, @PathParam("review_id") final int reviewId);

    @DELETE
    @Path("/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    @OperationId("pulls/delete-review-comment")
    @Docs("https://developer.github.com/v3/pulls/comments/#delete-a-review-comment-for-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    @Subcategory("comments")
    void deleteReviewCommentForPullRequest(final DeleteReviewCommentForPullRequest deleteReviewCommentForPullRequest);

    @DELETE
    @Path("/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    @OperationId("pulls/delete-review-comment")
    @Docs("https://developer.github.com/v3/pulls/comments/#delete-a-review-comment-for-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    @Subcategory("comments")
    void deleteReviewCommentForPullRequest(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("comment_id") final int commentId);

    @PUT
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/dismissals")
    @OperationId("pulls/dismiss-review")
    @Docs("https://developer.github.com/v3/pulls/reviews/#dismiss-a-review-for-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    @Subcategory("reviews")
    PullRequestReview dismissReviewForPullRequest(final DismissReviewForPullRequest dismissReviewForPullRequest);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}")
    @OperationId("pulls/get")
    @Docs("https://developer.github.com/v3/pulls/#get-a-pull-request")
    @EnabledForGithubApps
    @Preview("sailor-v")
    @Category("pulls")
    PullRequest getPullRequest(final GetPullRequest getPullRequest);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}")
    @OperationId("pulls/get")
    @Docs("https://developer.github.com/v3/pulls/#get-a-pull-request")
    @EnabledForGithubApps
    @Preview("sailor-v")
    @Category("pulls")
    PullRequest getPullRequest(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("pull-number") final int pullNumber);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    @OperationId("pulls/get-review-comment")
    @Docs("https://developer.github.com/v3/pulls/comments/#get-a-review-comment-for-a-pull-request")
    @EnabledForGithubApps
    @Preview("comfort-fade")
    @Preview("squirrel-girl")
    @Category("pulls")
    @Subcategory("comments")
    PullRequestReviewComment getReviewCommentForPullRequest(final GetReviewCommentForPullRequest getReviewCommentForPullRequest);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    @OperationId("pulls/get-review-comment")
    @Docs("https://developer.github.com/v3/pulls/comments/#get-a-review-comment-for-a-pull-request")
    @EnabledForGithubApps
    @Preview("comfort-fade")
    @Preview("squirrel-girl")
    @Category("pulls")
    @Subcategory("comments")
    PullRequestReviewComment getReviewCommentForPullRequest(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("comment_id") final int commentId);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    @OperationId("pulls/get-review")
    @Docs("https://developer.github.com/v3/pulls/reviews/#get-a-review-for-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    @Subcategory("reviews")
    PullRequestReview getReviewForPullRequest(final GetReviewForPullRequest getReviewForPullRequest);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    @OperationId("pulls/get-review")
    @Docs("https://developer.github.com/v3/pulls/reviews/#get-a-review-for-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    @Subcategory("reviews")
    PullRequestReview getReviewForPullRequest(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("pull-number") final int pullNumber, @PathParam("review_id") final int reviewId);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    @OperationId("pulls/list-comments-for-review")
    @Docs("https://developer.github.com/v3/pulls/reviews/#list-comments-for-a-pull-request-review")
    @EnabledForGithubApps
    @Category("pulls")
    @Subcategory("reviews")
    @Paged(ReviewComment[].class)
    Stream<ReviewComment> listCommentsForPullRequestReview(final ListCommentsForPullRequestReview listCommentsForPullRequestReview);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    @OperationId("pulls/list-comments-for-review")
    @Docs("https://developer.github.com/v3/pulls/reviews/#list-comments-for-a-pull-request-review")
    @EnabledForGithubApps
    @Category("pulls")
    @Subcategory("reviews")
    @Paged(ReviewComment[].class)
    Stream<ReviewComment> listCommentsForPullRequestReview(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("pull-number") final int pullNumber, @PathParam("review_id") final int reviewId);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    @OperationId("pulls/list-commits")
    @Docs("https://developer.github.com/v3/pulls/#list-commits-on-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    @Paged(SimpleCommit[].class)
    Stream<SimpleCommit> listCommitsOnPullRequest(final ListCommitsOnPullRequest listCommitsOnPullRequest);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    @OperationId("pulls/list-commits")
    @Docs("https://developer.github.com/v3/pulls/#list-commits-on-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    @Paged(SimpleCommit[].class)
    Stream<SimpleCommit> listCommitsOnPullRequest(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("pull-number") final int pullNumber);

    @GET
    @Path("/repos/{owner}/{repo}/pulls")
    @OperationId("pulls/list")
    @Docs("https://developer.github.com/v3/pulls/#list-pull-requests")
    @EnabledForGithubApps
    @Preview("sailor-v")
    @Category("pulls")
    @Paged(PullRequestSimple[].class)
    Stream<PullRequestSimple> listPullRequests(final ListPullRequests listPullRequests);

    @GET
    @Path("/repos/{owner}/{repo}/pulls")
    @OperationId("pulls/list")
    @Docs("https://developer.github.com/v3/pulls/#list-pull-requests")
    @EnabledForGithubApps
    @Preview("sailor-v")
    @Category("pulls")
    @Paged(PullRequestSimple[].class)
    Stream<PullRequestSimple> listPullRequests(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/files")
    @OperationId("pulls/list-files")
    @Docs("https://developer.github.com/v3/pulls/#list-pull-requests-files")
    @EnabledForGithubApps
    @Category("pulls")
    @Paged(DiffEntry[].class)
    Stream<DiffEntry> listPullRequestsFiles(final ListPullRequestsFiles listPullRequestsFiles);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/files")
    @OperationId("pulls/list-files")
    @Docs("https://developer.github.com/v3/pulls/#list-pull-requests-files")
    @EnabledForGithubApps
    @Category("pulls")
    @Paged(DiffEntry[].class)
    Stream<DiffEntry> listPullRequestsFiles(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("pull-number") final int pullNumber);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    @OperationId("pulls/list-requested-reviewers")
    @Docs("https://developer.github.com/v3/pulls/review_requests/#list-requested-reviewers-for-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    @Subcategory("review-requests")
    PullRequestReviewRequest listRequestedReviewersForPullRequest(final ListRequestedReviewersForPullRequest listRequestedReviewersForPullRequest);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    @OperationId("pulls/list-requested-reviewers")
    @Docs("https://developer.github.com/v3/pulls/review_requests/#list-requested-reviewers-for-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    @Subcategory("review-requests")
    PullRequestReviewRequest listRequestedReviewersForPullRequest(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("pull-number") final int pullNumber);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/comments")
    @OperationId("pulls/list-review-comments-for-repo")
    @Docs("https://developer.github.com/v3/pulls/comments/#list-review-comments-in-a-repository")
    @EnabledForGithubApps
    @Preview("comfort-fade")
    @Preview("squirrel-girl")
    @Category("pulls")
    @Subcategory("comments")
    @Paged(PullRequestReviewComment[].class)
    Stream<PullRequestReviewComment> listReviewCommentsInRepository(final ListReviewCommentsInRepository listReviewCommentsInRepository);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/comments")
    @OperationId("pulls/list-review-comments-for-repo")
    @Docs("https://developer.github.com/v3/pulls/comments/#list-review-comments-in-a-repository")
    @EnabledForGithubApps
    @Preview("comfort-fade")
    @Preview("squirrel-girl")
    @Category("pulls")
    @Subcategory("comments")
    @Paged(PullRequestReviewComment[].class)
    Stream<PullRequestReviewComment> listReviewCommentsInRepository(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    @OperationId("pulls/list-review-comments")
    @Docs("https://developer.github.com/v3/pulls/comments/#list-review-comments-on-a-pull-request")
    @EnabledForGithubApps
    @Preview("comfort-fade")
    @Preview("squirrel-girl")
    @Category("pulls")
    @Subcategory("comments")
    @Paged(PullRequestReviewComment[].class)
    Stream<PullRequestReviewComment> listReviewCommentsOnPullRequest(final ListReviewCommentsOnPullRequest listReviewCommentsOnPullRequest);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    @OperationId("pulls/list-review-comments")
    @Docs("https://developer.github.com/v3/pulls/comments/#list-review-comments-on-a-pull-request")
    @EnabledForGithubApps
    @Preview("comfort-fade")
    @Preview("squirrel-girl")
    @Category("pulls")
    @Subcategory("comments")
    @Paged(PullRequestReviewComment[].class)
    Stream<PullRequestReviewComment> listReviewCommentsOnPullRequest(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("pull-number") final int pullNumber);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    @OperationId("pulls/list-reviews")
    @Docs("https://developer.github.com/v3/pulls/reviews/#list-reviews-for-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    @Subcategory("reviews")
    @Paged(PullRequestReview[].class)
    Stream<PullRequestReview> listReviewsForPullRequest(final ListReviewsForPullRequest listReviewsForPullRequest);

    @GET
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    @OperationId("pulls/list-reviews")
    @Docs("https://developer.github.com/v3/pulls/reviews/#list-reviews-for-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    @Subcategory("reviews")
    @Paged(PullRequestReview[].class)
    Stream<PullRequestReview> listReviewsForPullRequest(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("pull-number") final int pullNumber);

    @PUT
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    @OperationId("pulls/merge")
    @Docs("https://developer.github.com/v3/pulls/#merge-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    PullRequestMergeResult mergePullRequest(final MergePullRequest mergePullRequest);

    @POST
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    @OperationId("pulls/request-reviewers")
    @Docs("https://developer.github.com/v3/pulls/review_requests/#request-reviewers-for-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    @Subcategory("review-requests")
    PullRequestSimple requestReviewersForPullRequest(final RequestReviewersForPullRequest requestReviewersForPullRequest);

    @POST
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    @OperationId("pulls/submit-review")
    @Docs("https://developer.github.com/v3/pulls/reviews/#submit-a-review-for-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    @Subcategory("reviews")
    PullRequestReview submitReviewForPullRequest(final SubmitReviewForPullRequest submitReviewForPullRequest);

    @PATCH
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}")
    @OperationId("pulls/update")
    @Docs("https://developer.github.com/v3/pulls/#update-a-pull-request")
    @EnabledForGithubApps
    @Preview("sailor-v")
    @Category("pulls")
    PullRequest updatePullRequest(final UpdatePullRequest updatePullRequest);

    @PUT
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/update-branch")
    @OperationId("pulls/update-branch")
    @Docs("https://developer.github.com/v3/pulls/#update-a-pull-request-branch")
    @Preview("lydian")
    @Category("pulls")
    UpdatePullRequestBranchResponse updatePullRequestBranch(final UpdatePullRequestBranch updatePullRequestBranch);

    @PATCH
    @Path("/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    @OperationId("pulls/update-review-comment")
    @Docs("https://developer.github.com/v3/pulls/comments/#update-a-review-comment-for-a-pull-request")
    @EnabledForGithubApps
    @Preview("comfort-fade")
    @Category("pulls")
    @Subcategory("comments")
    PullRequestReviewComment updateReviewCommentForPullRequest(final UpdateReviewCommentForPullRequest updateReviewCommentForPullRequest);

    @PUT
    @Path("/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    @OperationId("pulls/update-review")
    @Docs("https://developer.github.com/v3/pulls/reviews/#update-a-review-for-a-pull-request")
    @EnabledForGithubApps
    @Category("pulls")
    @Subcategory("reviews")
    PullRequestReview updateReviewForPullRequest(final UpdateReviewForPullRequest updateReviewForPullRequest);
}
