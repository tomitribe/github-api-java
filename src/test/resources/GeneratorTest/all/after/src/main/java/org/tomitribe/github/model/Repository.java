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

import java.net.URI;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tomitribe.github.core.DateAdapter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ComponentId("#/components/schemas/repository")
public class Repository {

    @JsonbProperty("private")
    private Boolean _private;

    @JsonbProperty("allow_merge_commit")
    private Boolean allowMergeCommit;

    @JsonbProperty("allow_rebase_merge")
    private Boolean allowRebaseMerge;

    @JsonbProperty("allow_squash_merge")
    private Boolean allowSquashMerge;

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
    private URI contributorsUrl;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("default_branch")
    private String defaultBranch;

    @JsonbProperty("delete_branch_on_merge")
    private Boolean deleteBranchOnMerge;

    @JsonbProperty("deployments_url")
    private URI deploymentsUrl;

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("disabled")
    private Boolean disabled;

    @JsonbProperty("downloads_url")
    private URI downloadsUrl;

    @JsonbProperty("events_url")
    private URI eventsUrl;

    @JsonbProperty("fork")
    private Boolean fork;

    @JsonbProperty("forks")
    private Integer forks;

    @JsonbProperty("forks_count")
    private Integer forksCount;

    @JsonbProperty("forks_url")
    private URI forksUrl;

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
    private URI homepage;

    @JsonbProperty("hooks_url")
    private URI hooksUrl;

    @JsonbProperty("html_url")
    private URI htmlUrl;

    @JsonbProperty("id")
    private Integer id;

    @JsonbProperty("is_template")
    private Boolean isTemplate;

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
    private URI languagesUrl;

    @JsonbProperty("license")
    private LicenseSimple license;

    @JsonbProperty("master_branch")
    private String masterBranch;

    @JsonbProperty("merges_url")
    private URI mergesUrl;

    @JsonbProperty("milestones_url")
    private String milestonesUrl;

    @JsonbProperty("mirror_url")
    private URI mirrorUrl;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("network_count")
    private Integer networkCount;

    @JsonbProperty("node_id")
    private String nodeId;

    @JsonbProperty("notifications_url")
    private String notificationsUrl;

    @JsonbProperty("open_issues")
    private Integer openIssues;

    @JsonbProperty("open_issues_count")
    private Integer openIssuesCount;

    @JsonbProperty("owner")
    private SimpleUser owner;

    @JsonbProperty("permissions")
    private Permissions permissions;

    @JsonbProperty("pulls_url")
    private String pullsUrl;

    @JsonbProperty("pushed_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date pushedAt;

    @JsonbProperty("releases_url")
    private String releasesUrl;

    @JsonbProperty("size")
    private Integer size;

    @JsonbProperty("ssh_url")
    private String sshUrl;

    @JsonbProperty("stargazers_count")
    private Integer stargazersCount;

    @JsonbProperty("stargazers_url")
    private URI stargazersUrl;

    @JsonbProperty("starred_at")
    private String starredAt;

    @JsonbProperty("statuses_url")
    private String statusesUrl;

    @JsonbProperty("subscribers_count")
    private Integer subscribersCount;

    @JsonbProperty("subscribers_url")
    private URI subscribersUrl;

    @JsonbProperty("subscription_url")
    private URI subscriptionUrl;

    @JsonbProperty("svn_url")
    private URI svnUrl;

    @JsonbProperty("tags_url")
    private URI tagsUrl;

    @JsonbProperty("teams_url")
    private URI teamsUrl;

    @JsonbProperty("temp_clone_token")
    private String tempCloneToken;

    @JsonbProperty("template_repository")
    private TemplateRepository templateRepository;

    @JsonbProperty("topics")
    private List<String> topics;

    @JsonbProperty("trees_url")
    private String treesUrl;

    @JsonbProperty("updated_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date updatedAt;

    @JsonbProperty("url")
    private URI url;

    @JsonbProperty("visibility")
    private String visibility;

    @JsonbProperty("watchers")
    private Integer watchers;

    @JsonbProperty("watchers_count")
    private Integer watchersCount;
}
