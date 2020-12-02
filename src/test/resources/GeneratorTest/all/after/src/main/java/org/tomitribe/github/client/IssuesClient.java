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
import org.tomitribe.github.model.AddAssigneesToIssue;
import org.tomitribe.github.model.AddLabelsToIssue;
import org.tomitribe.github.model.CheckIfUserCanBeAssigned;
import org.tomitribe.github.model.CreateIssue;
import org.tomitribe.github.model.CreateIssueComment;
import org.tomitribe.github.model.CreateLabel;
import org.tomitribe.github.model.CreateMilestone;
import org.tomitribe.github.model.DeleteIssueComment;
import org.tomitribe.github.model.DeleteLabel;
import org.tomitribe.github.model.DeleteMilestone;
import org.tomitribe.github.model.GetIssue;
import org.tomitribe.github.model.GetIssueComment;
import org.tomitribe.github.model.GetIssueEvent;
import org.tomitribe.github.model.GetLabel;
import org.tomitribe.github.model.GetMilestone;
import org.tomitribe.github.model.Issue;
import org.tomitribe.github.model.IssueComment;
import org.tomitribe.github.model.IssueEvent;
import org.tomitribe.github.model.IssueEventForIssue;
import org.tomitribe.github.model.IssueSimple;
import org.tomitribe.github.model.Label;
import org.tomitribe.github.model.ListAssignees;
import org.tomitribe.github.model.ListIssueComments;
import org.tomitribe.github.model.ListIssueCommentsForRepository;
import org.tomitribe.github.model.ListIssueEvents;
import org.tomitribe.github.model.ListIssueEventsForRepository;
import org.tomitribe.github.model.ListIssuesAssignedToAuthenticatedUser;
import org.tomitribe.github.model.ListLabelsForIssue;
import org.tomitribe.github.model.ListLabelsForIssuesInMilestone;
import org.tomitribe.github.model.ListLabelsForRepository;
import org.tomitribe.github.model.ListMilestones;
import org.tomitribe.github.model.ListOrganizationIssuesAssignedToAuthenticatedUser;
import org.tomitribe.github.model.ListRepositoryIssues;
import org.tomitribe.github.model.ListTimelineEventsForIssue;
import org.tomitribe.github.model.ListUserAccountIssuesAssignedToAuthenticatedUser;
import org.tomitribe.github.model.LockIssue;
import org.tomitribe.github.model.Milestone;
import org.tomitribe.github.model.RemoveAllLabelsFromIssue;
import org.tomitribe.github.model.RemoveAssigneesFromIssue;
import org.tomitribe.github.model.RemoveLabelFromIssue;
import org.tomitribe.github.model.SetLabelsForIssue;
import org.tomitribe.github.model.SimpleUser;
import org.tomitribe.github.model.UnlockIssue;
import org.tomitribe.github.model.UpdateIssue;
import org.tomitribe.github.model.UpdateIssueComment;
import org.tomitribe.github.model.UpdateLabel;
import org.tomitribe.github.model.UpdateMilestone;

public interface IssuesClient {

    @POST
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    @OperationId("issues/add-assignees")
    @Docs("https://developer.github.com/v3/issues/assignees/#add-assignees-to-an-issue")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("assignees")
    IssueSimple addAssigneesToIssue(final AddAssigneesToIssue addAssigneesToIssue);

    @POST
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/labels")
    @OperationId("issues/add-labels")
    @Docs("https://developer.github.com/v3/issues/labels/#add-labels-to-an-issue")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    Stream<Label> addLabelsToIssue(final AddLabelsToIssue addLabelsToIssue);

    @GET
    @Path("/repos/{owner}/{repo}/assignees/{assignee}")
    @OperationId("issues/check-user-can-be-assigned")
    @Docs("https://developer.github.com/v3/issues/assignees/#check-if-a-user-can-be-assigned")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("assignees")
    void checkIfUserCanBeAssigned(final CheckIfUserCanBeAssigned checkIfUserCanBeAssigned);

    @GET
    @Path("/repos/{owner}/{repo}/assignees/{assignee}")
    @OperationId("issues/check-user-can-be-assigned")
    @Docs("https://developer.github.com/v3/issues/assignees/#check-if-a-user-can-be-assigned")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("assignees")
    void checkIfUserCanBeAssigned(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("assignee") final String assignee);

    @POST
    @Path("/repos/{owner}/{repo}/issues")
    @OperationId("issues/create")
    @Docs("https://developer.github.com/v3/issues/#create-an-issue")
    @EnabledForGithubApps
    @Category("issues")
    Issue createIssue(final CreateIssue createIssue);

    @POST
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/comments")
    @OperationId("issues/create-comment")
    @Docs("https://developer.github.com/v3/issues/comments/#create-an-issue-comment")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("comments")
    IssueComment createIssueComment(final CreateIssueComment createIssueComment);

    @POST
    @Path("/repos/{owner}/{repo}/labels")
    @OperationId("issues/create-label")
    @Docs("https://developer.github.com/v3/issues/labels/#create-a-label")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    Label createLabel(final CreateLabel createLabel);

    @POST
    @Path("/repos/{owner}/{repo}/milestones")
    @OperationId("issues/create-milestone")
    @Docs("https://developer.github.com/v3/issues/milestones/#create-a-milestone")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("milestones")
    Milestone createMilestone(final CreateMilestone createMilestone);

    @DELETE
    @Path("/repos/{owner}/{repo}/issues/comments/{comment_id}")
    @OperationId("issues/delete-comment")
    @Docs("https://developer.github.com/v3/issues/comments/#delete-an-issue-comment")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("comments")
    void deleteIssueComment(final DeleteIssueComment deleteIssueComment);

    @DELETE
    @Path("/repos/{owner}/{repo}/issues/comments/{comment_id}")
    @OperationId("issues/delete-comment")
    @Docs("https://developer.github.com/v3/issues/comments/#delete-an-issue-comment")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("comments")
    void deleteIssueComment(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("comment_id") final int commentId);

    @DELETE
    @Path("/repos/{owner}/{repo}/labels/{name}")
    @OperationId("issues/delete-label")
    @Docs("https://developer.github.com/v3/issues/labels/#delete-a-label")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    void deleteLabel(final DeleteLabel deleteLabel);

    @DELETE
    @Path("/repos/{owner}/{repo}/labels/{name}")
    @OperationId("issues/delete-label")
    @Docs("https://developer.github.com/v3/issues/labels/#delete-a-label")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    void deleteLabel(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("name") final String name);

    @DELETE
    @Path("/repos/{owner}/{repo}/milestones/{milestone_number}")
    @OperationId("issues/delete-milestone")
    @Docs("https://developer.github.com/v3/issues/milestones/#delete-a-milestone")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("milestones")
    void deleteMilestone(final DeleteMilestone deleteMilestone);

    @DELETE
    @Path("/repos/{owner}/{repo}/milestones/{milestone_number}")
    @OperationId("issues/delete-milestone")
    @Docs("https://developer.github.com/v3/issues/milestones/#delete-a-milestone")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("milestones")
    void deleteMilestone(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("milestone_number") final int milestoneNumber);

    @GET
    @Path("/repos/{owner}/{repo}/issues/{issue_number}")
    @OperationId("issues/get")
    @Docs("https://developer.github.com/v3/issues/#get-an-issue")
    @EnabledForGithubApps
    @Preview("squirrel-girl")
    @Category("issues")
    Issue getIssue(final GetIssue getIssue);

    @GET
    @Path("/repos/{owner}/{repo}/issues/{issue_number}")
    @OperationId("issues/get")
    @Docs("https://developer.github.com/v3/issues/#get-an-issue")
    @EnabledForGithubApps
    @Preview("squirrel-girl")
    @Category("issues")
    Issue getIssue(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("issue_number") final int issueNumber);

    @GET
    @Path("/repos/{owner}/{repo}/issues/comments/{comment_id}")
    @OperationId("issues/get-comment")
    @Docs("https://developer.github.com/v3/issues/comments/#get-an-issue-comment")
    @EnabledForGithubApps
    @Preview("machine-man")
    @Preview("squirrel-girl")
    @Category("issues")
    @Subcategory("comments")
    IssueComment getIssueComment(final GetIssueComment getIssueComment);

    @GET
    @Path("/repos/{owner}/{repo}/issues/comments/{comment_id}")
    @OperationId("issues/get-comment")
    @Docs("https://developer.github.com/v3/issues/comments/#get-an-issue-comment")
    @EnabledForGithubApps
    @Preview("machine-man")
    @Preview("squirrel-girl")
    @Category("issues")
    @Subcategory("comments")
    IssueComment getIssueComment(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("comment_id") final int commentId);

    @GET
    @Path("/repos/{owner}/{repo}/issues/events/{event_id}")
    @OperationId("issues/get-event")
    @Docs("https://developer.github.com/v3/issues/events/#get-an-issue-event")
    @EnabledForGithubApps
    @Preview("starfox")
    @Preview("machine-man")
    @Preview("sailor-v")
    @Category("issues")
    @Subcategory("events")
    IssueEvent getIssueEvent(final GetIssueEvent getIssueEvent);

    @GET
    @Path("/repos/{owner}/{repo}/issues/events/{event_id}")
    @OperationId("issues/get-event")
    @Docs("https://developer.github.com/v3/issues/events/#get-an-issue-event")
    @EnabledForGithubApps
    @Preview("starfox")
    @Preview("machine-man")
    @Preview("sailor-v")
    @Category("issues")
    @Subcategory("events")
    IssueEvent getIssueEvent(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("event_id") final int eventId);

    @GET
    @Path("/repos/{owner}/{repo}/labels/{name}")
    @OperationId("issues/get-label")
    @Docs("https://developer.github.com/v3/issues/labels/#get-a-label")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    Label getLabel(final GetLabel getLabel);

    @GET
    @Path("/repos/{owner}/{repo}/labels/{name}")
    @OperationId("issues/get-label")
    @Docs("https://developer.github.com/v3/issues/labels/#get-a-label")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    Label getLabel(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("name") final String name);

    @GET
    @Path("/repos/{owner}/{repo}/milestones/{milestone_number}")
    @OperationId("issues/get-milestone")
    @Docs("https://developer.github.com/v3/issues/milestones/#get-a-milestone")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("milestones")
    Milestone getMilestone(final GetMilestone getMilestone);

    @GET
    @Path("/repos/{owner}/{repo}/milestones/{milestone_number}")
    @OperationId("issues/get-milestone")
    @Docs("https://developer.github.com/v3/issues/milestones/#get-a-milestone")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("milestones")
    Milestone getMilestone(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("milestone_number") final int milestoneNumber);

    @GET
    @Path("/repos/{owner}/{repo}/assignees")
    @OperationId("issues/list-assignees")
    @Docs("https://developer.github.com/v3/issues/assignees/#list-assignees")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("assignees")
    @Paged(SimpleUser[].class)
    Stream<SimpleUser> listAssignees(final ListAssignees listAssignees);

    @GET
    @Path("/repos/{owner}/{repo}/assignees")
    @OperationId("issues/list-assignees")
    @Docs("https://developer.github.com/v3/issues/assignees/#list-assignees")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("assignees")
    @Paged(SimpleUser[].class)
    Stream<SimpleUser> listAssignees(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/comments")
    @OperationId("issues/list-comments")
    @Docs("https://developer.github.com/v3/issues/comments/#list-issue-comments")
    @EnabledForGithubApps
    @Preview("squirrel-girl")
    @Category("issues")
    @Subcategory("comments")
    @Paged(IssueComment[].class)
    Stream<IssueComment> listIssueComments(final ListIssueComments listIssueComments);

    @GET
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/comments")
    @OperationId("issues/list-comments")
    @Docs("https://developer.github.com/v3/issues/comments/#list-issue-comments")
    @EnabledForGithubApps
    @Preview("squirrel-girl")
    @Category("issues")
    @Subcategory("comments")
    @Paged(IssueComment[].class)
    Stream<IssueComment> listIssueComments(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("issue_number") final int issueNumber);

    @GET
    @Path("/repos/{owner}/{repo}/issues/comments")
    @OperationId("issues/list-comments-for-repo")
    @Docs("https://developer.github.com/v3/issues/comments/#list-issue-comments-for-a-repository")
    @EnabledForGithubApps
    @Preview("squirrel-girl")
    @Category("issues")
    @Subcategory("comments")
    @Paged(IssueComment[].class)
    Stream<IssueComment> listIssueCommentsForRepository(final ListIssueCommentsForRepository listIssueCommentsForRepository);

    @GET
    @Path("/repos/{owner}/{repo}/issues/comments")
    @OperationId("issues/list-comments-for-repo")
    @Docs("https://developer.github.com/v3/issues/comments/#list-issue-comments-for-a-repository")
    @EnabledForGithubApps
    @Preview("squirrel-girl")
    @Category("issues")
    @Subcategory("comments")
    @Paged(IssueComment[].class)
    Stream<IssueComment> listIssueCommentsForRepository(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/events")
    @OperationId("issues/list-events")
    @Docs("https://developer.github.com/v3/issues/events/#list-issue-events")
    @EnabledForGithubApps
    @Preview("starfox")
    @Preview("sailor-v")
    @Category("issues")
    @Subcategory("events")
    @Paged(IssueEventForIssue[].class)
    Stream<IssueEventForIssue> listIssueEvents(final ListIssueEvents listIssueEvents);

    @GET
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/events")
    @OperationId("issues/list-events")
    @Docs("https://developer.github.com/v3/issues/events/#list-issue-events")
    @EnabledForGithubApps
    @Preview("starfox")
    @Preview("sailor-v")
    @Category("issues")
    @Subcategory("events")
    @Paged(IssueEventForIssue[].class)
    Stream<IssueEventForIssue> listIssueEvents(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("issue_number") final int issueNumber);

    @GET
    @Path("/repos/{owner}/{repo}/issues/events")
    @OperationId("issues/list-events-for-repo")
    @Docs("https://developer.github.com/v3/issues/events/#list-issue-events-for-a-repository")
    @EnabledForGithubApps
    @Preview("starfox")
    @Preview("sailor-v")
    @Category("issues")
    @Subcategory("events")
    @Paged(IssueEvent[].class)
    Stream<IssueEvent> listIssueEventsForRepository(final ListIssueEventsForRepository listIssueEventsForRepository);

    @GET
    @Path("/repos/{owner}/{repo}/issues/events")
    @OperationId("issues/list-events-for-repo")
    @Docs("https://developer.github.com/v3/issues/events/#list-issue-events-for-a-repository")
    @EnabledForGithubApps
    @Preview("starfox")
    @Preview("sailor-v")
    @Category("issues")
    @Subcategory("events")
    @Paged(IssueEvent[].class)
    Stream<IssueEvent> listIssueEventsForRepository(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/issues")
    @OperationId("issues/list")
    @Docs("https://developer.github.com/v3/issues/#list-issues-assigned-to-the-authenticated-user")
    @Preview("machine-man")
    @Preview("squirrel-girl")
    @Category("issues")
    @Paged(Issue[].class)
    Stream<Issue> listIssuesAssignedToAuthenticatedUser(final ListIssuesAssignedToAuthenticatedUser listIssuesAssignedToAuthenticatedUser);

    @GET
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/labels")
    @OperationId("issues/list-labels-on-issue")
    @Docs("https://developer.github.com/v3/issues/labels/#list-labels-for-an-issue")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    @Paged(Label[].class)
    Stream<Label> listLabelsForIssue(final ListLabelsForIssue listLabelsForIssue);

    @GET
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/labels")
    @OperationId("issues/list-labels-on-issue")
    @Docs("https://developer.github.com/v3/issues/labels/#list-labels-for-an-issue")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    @Paged(Label[].class)
    Stream<Label> listLabelsForIssue(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("issue_number") final int issueNumber);

    @GET
    @Path("/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    @OperationId("issues/list-labels-for-milestone")
    @Docs("https://developer.github.com/v3/issues/labels/#list-labels-for-issues-in-a-milestone")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    @Paged(Label[].class)
    Stream<Label> listLabelsForIssuesInMilestone(final ListLabelsForIssuesInMilestone listLabelsForIssuesInMilestone);

    @GET
    @Path("/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    @OperationId("issues/list-labels-for-milestone")
    @Docs("https://developer.github.com/v3/issues/labels/#list-labels-for-issues-in-a-milestone")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    @Paged(Label[].class)
    Stream<Label> listLabelsForIssuesInMilestone(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("milestone_number") final int milestoneNumber);

    @GET
    @Path("/repos/{owner}/{repo}/labels")
    @OperationId("issues/list-labels-for-repo")
    @Docs("https://developer.github.com/v3/issues/labels/#list-labels-for-a-repository")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    @Paged(Label[].class)
    Stream<Label> listLabelsForRepository(final ListLabelsForRepository listLabelsForRepository);

    @GET
    @Path("/repos/{owner}/{repo}/labels")
    @OperationId("issues/list-labels-for-repo")
    @Docs("https://developer.github.com/v3/issues/labels/#list-labels-for-a-repository")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    @Paged(Label[].class)
    Stream<Label> listLabelsForRepository(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/repos/{owner}/{repo}/milestones")
    @OperationId("issues/list-milestones")
    @Docs("https://developer.github.com/v3/issues/milestones/#list-milestones")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("milestones")
    @Paged(Milestone[].class)
    Stream<Milestone> listMilestones(final ListMilestones listMilestones);

    @GET
    @Path("/repos/{owner}/{repo}/milestones")
    @OperationId("issues/list-milestones")
    @Docs("https://developer.github.com/v3/issues/milestones/#list-milestones")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("milestones")
    @Paged(Milestone[].class)
    Stream<Milestone> listMilestones(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/orgs/{org}/issues")
    @OperationId("issues/list-for-org")
    @Docs("https://developer.github.com/v3/issues/#list-organization-issues-assigned-to-the-authenticated-user")
    @Preview("machine-man")
    @Preview("squirrel-girl")
    @Category("issues")
    @Paged(Issue[].class)
    Stream<Issue> listOrganizationIssuesAssignedToAuthenticatedUser(final ListOrganizationIssuesAssignedToAuthenticatedUser listOrganizationIssuesAssignedToAuthenticatedUser);

    @GET
    @Path("/orgs/{org}/issues")
    @OperationId("issues/list-for-org")
    @Docs("https://developer.github.com/v3/issues/#list-organization-issues-assigned-to-the-authenticated-user")
    @Preview("machine-man")
    @Preview("squirrel-girl")
    @Category("issues")
    @Paged(Issue[].class)
    Stream<Issue> listOrganizationIssuesAssignedToAuthenticatedUser(@PathParam("org") final String org);

    @GET
    @Path("/repos/{owner}/{repo}/issues")
    @OperationId("issues/list-for-repo")
    @Docs("https://developer.github.com/v3/issues/#list-repository-issues")
    @EnabledForGithubApps
    @Preview("machine-man")
    @Preview("squirrel-girl")
    @Category("issues")
    @Paged(IssueSimple[].class)
    Stream<IssueSimple> listRepositoryIssues(final ListRepositoryIssues listRepositoryIssues);

    @GET
    @Path("/repos/{owner}/{repo}/issues")
    @OperationId("issues/list-for-repo")
    @Docs("https://developer.github.com/v3/issues/#list-repository-issues")
    @EnabledForGithubApps
    @Preview("machine-man")
    @Preview("squirrel-girl")
    @Category("issues")
    @Paged(IssueSimple[].class)
    Stream<IssueSimple> listRepositoryIssues(@PathParam("owner") final String owner, @PathParam("repo") final String repo);

    @GET
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    @OperationId("issues/list-events-for-timeline")
    @Docs("https://developer.github.com/v3/issues/timeline/#list-timeline-events-for-an-issue")
    @EnabledForGithubApps
    @Preview("mockingbird")
    @Preview("starfox")
    @Category("issues")
    @Subcategory("timeline")
    @Paged(IssueEventForIssue[].class)
    Stream<IssueEventForIssue> listTimelineEventsForIssue(final ListTimelineEventsForIssue listTimelineEventsForIssue);

    @GET
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    @OperationId("issues/list-events-for-timeline")
    @Docs("https://developer.github.com/v3/issues/timeline/#list-timeline-events-for-an-issue")
    @EnabledForGithubApps
    @Preview("mockingbird")
    @Preview("starfox")
    @Category("issues")
    @Subcategory("timeline")
    @Paged(IssueEventForIssue[].class)
    Stream<IssueEventForIssue> listTimelineEventsForIssue(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("issue_number") final int issueNumber);

    @GET
    @Path("/user/issues")
    @OperationId("issues/list-for-authenticated-user")
    @Docs("https://developer.github.com/v3/issues/#list-user-account-issues-assigned-to-the-authenticated-user")
    @Preview("machine-man")
    @Preview("squirrel-girl")
    @Category("issues")
    @Paged(Issue[].class)
    Stream<Issue> listUserAccountIssuesAssignedToAuthenticatedUser(final ListUserAccountIssuesAssignedToAuthenticatedUser listUserAccountIssuesAssignedToAuthenticatedUser);

    @PUT
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/lock")
    @OperationId("issues/lock")
    @Docs("https://developer.github.com/v3/issues/#lock-an-issue")
    @EnabledForGithubApps
    @Preview("sailor-v")
    @Category("issues")
    void lockIssue(final LockIssue lockIssue);

    @DELETE
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/labels")
    @OperationId("issues/remove-all-labels")
    @Docs("https://developer.github.com/v3/issues/labels/#remove-all-labels-from-an-issue")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    void removeAllLabelsFromIssue(final RemoveAllLabelsFromIssue removeAllLabelsFromIssue);

    @DELETE
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/labels")
    @OperationId("issues/remove-all-labels")
    @Docs("https://developer.github.com/v3/issues/labels/#remove-all-labels-from-an-issue")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    void removeAllLabelsFromIssue(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("issue_number") final int issueNumber);

    @DELETE
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    @OperationId("issues/remove-assignees")
    @Docs("https://developer.github.com/v3/issues/assignees/#remove-assignees-from-an-issue")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("assignees")
    IssueSimple removeAssigneesFromIssue(final RemoveAssigneesFromIssue removeAssigneesFromIssue);

    @DELETE
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    @OperationId("issues/remove-label")
    @Docs("https://developer.github.com/v3/issues/labels/#remove-a-label-from-an-issue")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    Stream<Label> removeLabelFromIssue(final RemoveLabelFromIssue removeLabelFromIssue);

    @DELETE
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    @OperationId("issues/remove-label")
    @Docs("https://developer.github.com/v3/issues/labels/#remove-a-label-from-an-issue")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    Stream<Label> removeLabelFromIssue(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("issue_number") final int issueNumber, @PathParam("name") final String name);

    @PUT
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/labels")
    @OperationId("issues/set-labels")
    @Docs("https://developer.github.com/v3/issues/labels/#set-labels-for-an-issue")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    Stream<Label> setLabelsForIssue(final SetLabelsForIssue setLabelsForIssue);

    @DELETE
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/lock")
    @OperationId("issues/unlock")
    @Docs("https://developer.github.com/v3/issues/#unlock-an-issue")
    @EnabledForGithubApps
    @Category("issues")
    void unlockIssue(final UnlockIssue unlockIssue);

    @DELETE
    @Path("/repos/{owner}/{repo}/issues/{issue_number}/lock")
    @OperationId("issues/unlock")
    @Docs("https://developer.github.com/v3/issues/#unlock-an-issue")
    @EnabledForGithubApps
    @Category("issues")
    void unlockIssue(@PathParam("owner") final String owner, @PathParam("repo") final String repo, @PathParam("issue_number") final int issueNumber);

    @PATCH
    @Path("/repos/{owner}/{repo}/issues/{issue_number}")
    @OperationId("issues/update")
    @Docs("https://developer.github.com/v3/issues/#update-an-issue")
    @EnabledForGithubApps
    @Category("issues")
    Issue updateIssue(final UpdateIssue updateIssue);

    @PATCH
    @Path("/repos/{owner}/{repo}/issues/comments/{comment_id}")
    @OperationId("issues/update-comment")
    @Docs("https://developer.github.com/v3/issues/comments/#update-an-issue-comment")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("comments")
    IssueComment updateIssueComment(final UpdateIssueComment updateIssueComment);

    @PATCH
    @Path("/repos/{owner}/{repo}/labels/{name}")
    @OperationId("issues/update-label")
    @Docs("https://developer.github.com/v3/issues/labels/#update-a-label")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("labels")
    Label updateLabel(final UpdateLabel updateLabel);

    @PATCH
    @Path("/repos/{owner}/{repo}/milestones/{milestone_number}")
    @OperationId("issues/update-milestone")
    @Docs("https://developer.github.com/v3/issues/milestones/#update-a-milestone")
    @EnabledForGithubApps
    @Category("issues")
    @Subcategory("milestones")
    Milestone updateMilestone(final UpdateMilestone updateMilestone);
}
