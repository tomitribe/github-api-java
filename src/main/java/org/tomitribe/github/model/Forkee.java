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
 * - ForkEvent
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Forkee {

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("full_name")
    private String fullName;

    @JsonbProperty("private")
    private Boolean _private;

    @JsonbProperty("owner")
    private Owner owner;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("fork")
    private Boolean fork;

    @JsonbProperty("url")
    private String url;

    @JsonbProperty("forks_url")
    private String forksUrl;

    @JsonbProperty("keys_url")
    private String keysUrl;

    @JsonbProperty("collaborators_url")
    private String collaboratorsUrl;

    @JsonbProperty("teams_url")
    private String teamsUrl;

    @JsonbProperty("hooks_url")
    private String hooksUrl;

    @JsonbProperty("issue_events_url")
    private String issueEventsUrl;

    @JsonbProperty("events_url")
    private String eventsUrl;

    @JsonbProperty("assignees_url")
    private String assigneesUrl;

    @JsonbProperty("branches_url")
    private String branchesUrl;

    @JsonbProperty("tags_url")
    private String tagsUrl;

    @JsonbProperty("blobs_url")
    private String blobsUrl;

    @JsonbProperty("git_tags_url")
    private String gitTagsUrl;

    @JsonbProperty("git_refs_url")
    private String gitRefsUrl;

    @JsonbProperty("trees_url")
    private String treesUrl;

    @JsonbProperty("statuses_url")
    private String statusesUrl;

    @JsonbProperty("languages_url")
    private String languagesUrl;

    @JsonbProperty("stargazers_url")
    private String stargazersUrl;

    @JsonbProperty("contributors_url")
    private String contributorsUrl;

    @JsonbProperty("subscribers_url")
    private String subscribersUrl;

    @JsonbProperty("subscription_url")
    private String subscriptionUrl;

    @JsonbProperty("commits_url")
    private String commitsUrl;

    @JsonbProperty("git_commits_url")
    private String gitCommitsUrl;

    @JsonbProperty("comments_url")
    private String commentsUrl;

    @JsonbProperty("issue_comment_url")
    private String issueCommentUrl;

    @JsonbProperty("contents_url")
    private String contentsUrl;

    @JsonbProperty("compare_url")
    private String compareUrl;

    @JsonbProperty("merges_url")
    private String mergesUrl;

    @JsonbProperty("archive_url")
    private String archiveUrl;

    @JsonbProperty("downloads_url")
    private String downloadsUrl;

    @JsonbProperty("issues_url")
    private String issuesUrl;

    @JsonbProperty("pulls_url")
    private String pullsUrl;

    @JsonbProperty("milestones_url")
    private String milestonesUrl;

    @JsonbProperty("notifications_url")
    private String notificationsUrl;

    @JsonbProperty("labels_url")
    private String labelsUrl;

    @JsonbProperty("releases_url")
    private String releasesUrl;

    @JsonbProperty("deployments_url")
    private String deploymentsUrl;

    @JsonbProperty("created_at")
    private String createdAt;

    @JsonbProperty("updated_at")
    private String updatedAt;

    @JsonbProperty("pushed_at")
    private String pushedAt;

    @JsonbProperty("git_url")
    private String gitUrl;

    @JsonbProperty("ssh_url")
    private String sshUrl;

    @JsonbProperty("clone_url")
    private String cloneUrl;

    @JsonbProperty("svn_url")
    private String svnUrl;

    @JsonbProperty("homepage")
    private String homepage;

    @JsonbProperty("size")
    private Long size;

    @JsonbProperty("stargazers_count")
    private Long stargazersCount;

    @JsonbProperty("watchers_count")
    private Long watchersCount;

    @JsonbProperty("language")
    private String language;

    @JsonbProperty("has_issues")
    private Boolean hasIssues;

    @JsonbProperty("has_projects")
    private Boolean hasProjects;

    @JsonbProperty("has_downloads")
    private Boolean hasDownloads;

    @JsonbProperty("has_wiki")
    private Boolean hasWiki;

    @JsonbProperty("has_pages")
    private Boolean hasPages;

    @JsonbProperty("forks_count")
    private Long forksCount;

    @JsonbProperty("mirror_url")
    private String mirrorUrl;

    @JsonbProperty("archived")
    private Boolean archived;

    @JsonbProperty("disabled")
    private Boolean disabled;

    @JsonbProperty("open_issues_count")
    private Long openIssuesCount;

    @JsonbProperty("license")
    private String license;

    @JsonbProperty("forks")
    private Long forks;

    @JsonbProperty("open_issues")
    private Long openIssues;

    @JsonbProperty("watchers")
    private Long watchers;

    @JsonbProperty("default_branch")
    private String defaultBranch;

    @JsonbProperty("public")
    private Boolean _public;

}
