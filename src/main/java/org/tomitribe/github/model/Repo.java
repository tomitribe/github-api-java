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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Used by:
 * - CheckRunEvent
 * - CheckSuiteEvent
 * - PullRequestEvent
 * - PullRequestReviewCommentEvent
 * - PullRequestReviewEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Repo {

    @JsonbProperty("private")
    private Boolean _private;

    @JsonbProperty("archive_url")
    private String archiveUrl;

    @JsonbProperty("archived")
    private Boolean archived;

    @JsonbProperty("assignees_url")
    private String assigneesUrl;

    @JsonbProperty("blobs_url")
    private String blobsUrl;

    @JsonbProperty("branches_url")
    private String branchesUrl;

    @JsonbProperty("clone_url")
    private String cloneUrl;

    @JsonbProperty("collaborators_url")
    private String collaboratorsUrl;

    @JsonbProperty("comments_url")
    private String commentsUrl;

    @JsonbProperty("commits_url")
    private String commitsUrl;

    @JsonbProperty("compare_url")
    private String compareUrl;

    @JsonbProperty("contents_url")
    private String contentsUrl;

    @JsonbProperty("contributors_url")
    private String contributorsUrl;

    @JsonbProperty("created_at")
    private String createdAt;

    @JsonbProperty("default_branch")
    private String defaultBranch;

    @JsonbProperty("deployments_url")
    private String deploymentsUrl;

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("disabled")
    private Boolean disabled;

    @JsonbProperty("downloads_url")
    private String downloadsUrl;

    @JsonbProperty("events_url")
    private String eventsUrl;

    @JsonbProperty("fork")
    private Boolean fork;

    @JsonbProperty("forks")
    private Long forks;

    @JsonbProperty("forks_count")
    private Long forksCount;

    @JsonbProperty("forks_url")
    private String forksUrl;

    @JsonbProperty("full_name")
    private String fullName;

    @JsonbProperty("git_commits_url")
    private String gitCommitsUrl;

    @JsonbProperty("git_refs_url")
    private String gitRefsUrl;

    @JsonbProperty("git_tags_url")
    private String gitTagsUrl;

    @JsonbProperty("git_url")
    private String gitUrl;

    @JsonbProperty("has_downloads")
    private Boolean hasDownloads;

    @JsonbProperty("has_issues")
    private Boolean hasIssues;

    @JsonbProperty("has_pages")
    private Boolean hasPages;

    @JsonbProperty("has_projects")
    private Boolean hasProjects;

    @JsonbProperty("has_wiki")
    private Boolean hasWiki;

    @JsonbProperty("homepage")
    private String homepage;

    @JsonbProperty("hooks_url")
    private String hooksUrl;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("issue_comment_url")
    private String issueCommentUrl;

    @JsonbProperty("issue_events_url")
    private String issueEventsUrl;

    @JsonbProperty("issues_url")
    private String issuesUrl;

    @JsonbProperty("keys_url")
    private String keysUrl;

    @JsonbProperty("labels_url")
    private String labelsUrl;

    @JsonbProperty("language")
    private String language;

    @JsonbProperty("languages_url")
    private String languagesUrl;

    @JsonbProperty("license")
    private License license;

    @JsonbProperty("merges_url")
    private String mergesUrl;

    @JsonbProperty("milestones_url")
    private String milestonesUrl;

    @JsonbProperty("mirror_url")
    private String mirrorUrl;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("notifications_url")
    private String notificationsUrl;

    @JsonbProperty("open_issues")
    private Long openIssues;

    @JsonbProperty("open_issues_count")
    private Long openIssuesCount;

    @JsonbProperty("owner")
    private Owner owner;

    @JsonbProperty("pulls_url")
    private String pullsUrl;

    @JsonbProperty("pushed_at")
    private String pushedAt;

    @JsonbProperty("releases_url")
    private String releasesUrl;

    @JsonbProperty("size")
    private Long size;

    @JsonbProperty("ssh_url")
    private String sshUrl;

    @JsonbProperty("stargazers_count")
    private Long stargazersCount;

    @JsonbProperty("stargazers_url")
    private String stargazersUrl;

    @JsonbProperty("statuses_url")
    private String statusesUrl;

    @JsonbProperty("subscribers_url")
    private String subscribersUrl;

    @JsonbProperty("subscription_url")
    private String subscriptionUrl;

    @JsonbProperty("svn_url")
    private String svnUrl;

    @JsonbProperty("tags_url")
    private String tagsUrl;

    @JsonbProperty("teams_url")
    private String teamsUrl;

    @JsonbProperty("topics")
    private String[] topics;

    @JsonbProperty("trees_url")
    private String treesUrl;

    @JsonbProperty("updated_at")
    private String updatedAt;

    @JsonbProperty("url")
    private String url;

    @JsonbProperty("watchers")
    private Long watchers;

    @JsonbProperty("watchers_count")
    private Long watchersCount;
}
