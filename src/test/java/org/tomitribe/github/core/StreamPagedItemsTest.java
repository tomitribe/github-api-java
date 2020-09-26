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
package org.tomitribe.github.core;

import org.apache.openejb.util.Join;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tomitribe.github.JsonAsserts;
import org.tomitribe.github.MockService;
import org.tomitribe.github.Resources;
import org.tomitribe.github.TestLinks;
import org.tomitribe.github.client.GithubClient;
import org.tomitribe.github.client.Paged;
import org.tomitribe.github.model.RepositoriesPage;
import org.tomitribe.github.model.Repository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;


public class StreamPagedItemsTest {

    private static URI uri;

    @BeforeClass
    public static void before() throws Exception {
        uri = MockGithub.run();
    }

    private SearchClient getClient() {
        final Client client = ClientBuilder.newClient()
                .register(new MessageLogger.RequestFilter())
                .register(new MessageLogger.ResponseFilter());

        final Api api = Api.builder()
                .api(uri)
                .client(client)
                .handler(builder -> builder.header("authorization", "token 23456789dfghjklkjhgfdsdfghuiytrewertyui"))
                .build();
        return (SearchClient) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{SearchClient.class}, new ClientHandler(api));
    }

    public interface SearchClient {
        @GET
        @Path("/search/repositories")
        @Paged(RepositoriesPage.class)
        Stream<Repository> searchRepositories(@QueryParam("q") final String queryString);
    }

    @Test
    public void listNames() throws Exception {

        final SearchClient client = getClient();

        final List<Repository> list = client.searchRepositories("tomee").collect(Collectors.toList());
        final List<String> actual = list.stream()
                .map(Repository::getName)
                .collect(Collectors.toList());
        assertEquals("tomee\n" +
                "docker-tomee\n" +
                "tomee-jaxrs-starter-project\n" +
                "tomee-buildpack\n" +
                "tomee-site-generator\n" +
                "tomee-jaxrs-angular-starter-project\n" +
                "tomee-tck\n" +
                "javaee7-samples\n" +
                "docker_tomee\n" +
                "tomeemaven\n" +
                "tomee-buildpack-resource-configuration\n" +
                "tomee-chatterbox\n" +
                "tomee-site\n" +
                "oscm\n" +
                "docklands\n" +
                "tomee-mongodb-starter-project\n" +
                "vaadinized-tomee-jee6-app-stub\n" +
                "heroku-buildpack-tomee\n" +
                "50-shades-of-tomee\n" +
                "tomcat_to_tomee\n" +
                "tomee-jboss-forge-plugin\n" +
                "cargo\n" +
                "tomee-mvn-installation-example\n" +
                "persistence-test\n" +
                "mongo-realm\n" +
                "tomee\n" +
                "tomee-cli\n" +
                "openshift-cartridge-tomee\n" +
                "tomee-mvn-plugin-jpa21-setup\n" +
                "site-tomee-ng\n" +
                "custom-maven-archetypes\n" +
                "wad\n" +
                "TomEE-Boot-Example\n" +
                "tomee-hibernate-jpa-test\n" +
                "tomee-dev2ops\n" +
                "jcache-tomee\n" +
                "tomee-boot\n" +
                "chef-tomee\n" +
                "tomee-maven-demo\n" +
                "docker-tomee\n" +
                "skeleton-starter-flow-cdi\n" +
                "TomEE\n" +
                "nes-controller\n" +
                "Quickstart-JSF2.3-Samples\n" +
                "MdbExample\n" +
                "hello-prometheus\n" +
                "canjs-tomee-sample\n" +
                "swagger-in-tomee\n" +
                "TomEE-JavaEE7-demo\n" +
                "tomee-issue-2143-example\n" +
                "Vagrant4SIW\n" +
                "watchedmovies\n" +
                "TomeePOM\n" +
                "tomeeDemo\n" +
                "TomeeTest\n" +
                "tomee7-example\n" +
                "TomeePolygot\n" +
                "tomee704\n" +
                "tomee2027\n" +
                "tomeeEJB\n" +
                "Tomeek26-\n" +
                "tomee1\n" +
                "TomeeAnsible\n" +
                "heroes-service\n" +
                "Tomee170\n" +
                "tomeeSkeleton\n" +
                "TomeeCDITest\n" +
                "tomee-microprofile-rest-client-demo\n" +
                "FitTrackerAppServer\n" +
                "tomee-t01\n" +
                "tomee7-plus\n" +
                "tomeeBuildPack\n" +
                "tomeeVm\n" +
                "Tomee20000\n" +
                "TomeeMdbTest\n" +
                "tomee-codi\n" +
                "TomeeEnumRest\n" +
                "TomeeDeltaEar\n" +
                "TomeeJaxrsValidation\n" +
                "tomeeeS.github.io\n" +
                "tomee16-clickstack\n" +
                "tomee7-control\n" +
                "my-tomee17-examples\n" +
                "tomee17-ejb-topics\n" +
                "TomeeMdbDebug\n" +
                "TomeeRestfulWs\n" +
                "tomee7-hibernate-archetype\n" +
                "tomeekk20.github.io\n" +
                "tomee-flyway\n" +
                "projet-C1-2018\n" +
                "TomeeBlobTest\n" +
                "tomee17-templates\n" +
                "tomee17mauro\n" +
                "nbpfcrudgen-lazytest-tomee7\n" +
                "tomee7-dev-starter\n" +
                "tomee17-mdb-topics\n" +
                "TomeeDsValidation\n" +
                "training\n" +
                "Tomeer333.github.io\n" +
                "tomee\n" +
                "tomeekk6.github.io\n" +
                "tomee\n" +
                "tomee\n" +
                "tomee\n" +
                "tomee\n" +
                "BaseTomeeApp\n" +
                "Tomee\n" +
                "Tomee\n" +
                "prova_tomee1.7.3\n" +
                "tomee\n" +
                "tomee\n" +
                "tomee\n" +
                "tomee\n" +
                "ocp-tomee7-jaxrs-po\n" +
                "tomee\n" +
                "Tomee\n" +
                "tomee\n" +
                "tomee\n" +
                "tomee\n" +
                "mongotest\n" +
                "tomee\n" +
                "tomee\n" +
                "ejb31-tomeeplus172-snippets\n" +
                "tomee\n" +
                "POC-Tomee-Servlet\n" +
                "TOMEE\n" +
                "DeltaspikeEar\n" +
                "docker-image-apache-tomee\n" +
                "tomeevaadin\n" +
                "tomeemailtest\n" +
                "tomeeto\n" +
                "tomee-sample\n" +
                "apps.tomee\n" +
                "simple-singleton\n" +
                "tomee-samples\n" +
                "jta_test\n" +
                "Themes-tomee1.7.1-plus-deltaspike\n" +
                "mytestcrud-tomee1.7.2plus\n" +
                "tomee-juju\n" +
                "TomEE\n" +
                "tomee-site-pub\n" +
                "SigreRHEE\n" +
                "ntm_servers\n" +
                "tomee-sample\n" +
                "tomee-patch-plugin\n" +
                "javaee-tomee-jpa\n" +
                "todolist\n" +
                "tomee-example\n" +
                "tomee-ear\n" +
                "tomee-microprofile-config\n" +
                "tomee-microprofile-config\n" +
                "moviefun\n" +
                "tomeediy\n" +
                "openshift-tomee-rest-example\n" +
                "TomEE_Deployer_In_Ansible\n" +
                "generator-jangular\n" +
                "tomEEexample\n" +
                "tomeetest\n" +
                "tomeeaxis\n" +
                "mytest_tomee1.7.2_no-cdi\n" +
                "datasource-tomee\n" +
                "apache-tomee-plus-1.7.4\n" +
                "tomeefreezetestcase\n" +
                "tomeeherokudemo\n" +
                "tomeenfm\n" +
                "tomeetsleather\n" +
                "tomeetoo\n" +
                "tomeeapp\n" +
                "Sample\n" +
                "ASTomEE\n" +
                "tomee-spring\n" +
                "sample-tomee-heroku\n" +
                "cloud-employeeslistapp-tomee\n" +
                "tomee-war\n" +
                "tomee-eclipse-plugin\n" +
                "tomeedemo\n" +
                "tomEE\n" +
                "toMee\n" +
                "tomeeapp\n" +
                "tomeebug\n" +
                "TomEE\n" +
                "boxdata\n" +
                "tomEE\n" +
                "TomEE\n" +
                "TomEE\n" +
                "tomEE\n" +
                "tomee-hibernate\n" +
                "tomee-bluemix\n" +
                "tomee-jaxrs\n" +
                "TomEEServlets\n" +
                "tomee-camel\n" +
                "jsf-batch-example\n" +
                "clusterize\n" +
                "test-jee\n" +
                "address-manager\n" +
                "embedded_tomee\n" +
                "vagrant_tomee\n" +
                "tomee_test\n" +
                "Ignite\n" +
                "tomee-hello-jax-rs-jpa\n" +
                "tomee-sample\n" +
                "openshift-default-tomee-app\n" +
                "backend\n" +
                "tomee-test\n" +
                "tomee-plume\n" +
                "tomee-cditest\n" +
                "tomee-clickstack\n" +
                "prjTomee\n" +
                "tomee_vestige\n" +
                "tomee-build\n" +
                "tomee-jstl-test\n" +
                "tomee-boot\n" +
                "java-mp-tomtribe-tomee-maven-example\n" +
                "OEIS-Enhancer\n" +
                "CaseBook\n" +
                "test-tomee\n" +
                "tomee_hazelcast\n" +
                "Jenkins_Tomee\n" +
                "tomee-embedded\n" +
                "tomee-composition\n" +
                "tomee-tck\n" +
                "tomee-example\n" +
                "EJB_3-X-on-tomee-plus\n" +
                "tomee-charm-layer\n" +
                "MongoEclipse\n" +
                "vagrant-tomee\n" +
                "demo-tomee\n" +
                "rest-tomee\n" +
                "ToMeet\n" +
                "tomee-manual\n" +
                "TomEECDI\n" +
                "tomee-jaas\n" +
                "microprofile-tomee\n" +
                "tomee-jakarta\n" +
                "tomee-boot-xtend\n" +
                "hello_ejb\n" +
                "tomee-jsf-managedBean-and-ejb\n" +
                "TomEEArchetype\n" +
                "tomee-sample\n" +
                "GoTomeeting", Join.join("\n", actual));
    }

    @Test
    public void fullRecord() throws Exception {

        final GithubClient githubClient = GithubClient.builder().api(uri).build();

        final String expectedJson = "{\n" +
                "  \"archive_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/{archive_format}{/ref}\",\n" +
                "  \"archived\":false,\n" +
                "  \"assignees_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/assignees{/user}\",\n" +
                "  \"blobs_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/git/blobs{/sha}\",\n" +
                "  \"branches_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/branches{/branch}\",\n" +
                "  \"clone_url\":\"https://github.com/EmilLubomirov/CaseBook.git\",\n" +
                "  \"collaborators_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/collaborators{/collaborator}\",\n" +
                "  \"comments_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/comments{/number}\",\n" +
                "  \"commits_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/commits{/sha}\",\n" +
                "  \"compare_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/compare/{base}...{head}\",\n" +
                "  \"contents_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/contents/{+path}\",\n" +
                "  \"contributors_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/contributors\",\n" +
                "  \"created_at\":\"2020-06-27T08:52:58Z\",\n" +
                "  \"default_branch\":\"master\",\n" +
                "  \"deployments_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/deployments\",\n" +
                "  \"description\":\"Application with Java and TomEE\",\n" +
                "  \"disabled\":false,\n" +
                "  \"downloads_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/downloads\",\n" +
                "  \"events_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/events\",\n" +
                "  \"fork\":false,\n" +
                "  \"forks\":0,\n" +
                "  \"forks_count\":0,\n" +
                "  \"forks_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/forks\",\n" +
                "  \"full_name\":\"EmilLubomirov/CaseBook\",\n" +
                "  \"git_commits_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/git/commits{/sha}\",\n" +
                "  \"git_refs_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/git/refs{/sha}\",\n" +
                "  \"git_tags_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/git/tags{/sha}\",\n" +
                "  \"git_url\":\"git://github.com/EmilLubomirov/CaseBook.git\",\n" +
                "  \"has_downloads\":true,\n" +
                "  \"has_issues\":true,\n" +
                "  \"has_pages\":false,\n" +
                "  \"has_projects\":true,\n" +
                "  \"has_wiki\":true,\n" +
                "  \"hooks_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/hooks\",\n" +
                "  \"html_url\":\"https://github.com/EmilLubomirov/CaseBook\",\n" +
                "  \"id\":275334258,\n" +
                "  \"issue_comment_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/issues/comments{/number}\",\n" +
                "  \"issue_events_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/issues/events{/number}\",\n" +
                "  \"issues_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/issues{/number}\",\n" +
                "  \"keys_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/keys{/key_id}\",\n" +
                "  \"labels_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/labels{/name}\",\n" +
                "  \"language\":\"Java\",\n" +
                "  \"languages_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/languages\",\n" +
                "  \"merges_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/merges\",\n" +
                "  \"milestones_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/milestones{/number}\",\n" +
                "  \"name\":\"CaseBook\",\n" +
                "  \"node_id\":\"MDEwOlJlcG9zaXRvcnkyNzUzMzQyNTg=\",\n" +
                "  \"notifications_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/notifications{?since,all,participating}\",\n" +
                "  \"open_issues\":0,\n" +
                "  \"open_issues_count\":0,\n" +
                "  \"owner\":{\n" +
                "    \"avatar_url\":\"https://avatars3.githubusercontent.com/u/52040772?v=4\",\n" +
                "    \"events_url\":\"https://api.github.com/users/EmilLubomirov/events{/privacy}\",\n" +
                "    \"followers_url\":\"https://api.github.com/users/EmilLubomirov/followers\",\n" +
                "    \"following_url\":\"https://api.github.com/users/EmilLubomirov/following{/other_user}\",\n" +
                "    \"gists_url\":\"https://api.github.com/users/EmilLubomirov/gists{/gist_id}\",\n" +
                "    \"gravatar_id\":\"\",\n" +
                "    \"html_url\":\"https://github.com/EmilLubomirov\",\n" +
                "    \"id\":52040772,\n" +
                "    \"login\":\"EmilLubomirov\",\n" +
                "    \"node_id\":\"MDQ6VXNlcjUyMDQwNzcy\",\n" +
                "    \"organizations_url\":\"https://api.github.com/users/EmilLubomirov/orgs\",\n" +
                "    \"received_events_url\":\"https://api.github.com/users/EmilLubomirov/received_events\",\n" +
                "    \"repos_url\":\"https://api.github.com/users/EmilLubomirov/repos\",\n" +
                "    \"site_admin\":false,\n" +
                "    \"starred_url\":\"https://api.github.com/users/EmilLubomirov/starred{/owner}{/repo}\",\n" +
                "    \"subscriptions_url\":\"https://api.github.com/users/EmilLubomirov/subscriptions\",\n" +
                "    \"type\":\"User\",\n" +
                "    \"url\":\"https://api.github.com/users/EmilLubomirov\"\n" +
                "  },\n" +
                "  \"private\":false,\n" +
                "  \"pulls_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/pulls{/number}\",\n" +
                "  \"pushed_at\":\"2020-06-27T08:58:11Z\",\n" +
                "  \"releases_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/releases{/id}\",\n" +
                "  \"score\":1.0,\n" +
                "  \"size\":44,\n" +
                "  \"ssh_url\":\"git@github.com:EmilLubomirov/CaseBook.git\",\n" +
                "  \"stargazers_count\":0,\n" +
                "  \"stargazers_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/stargazers\",\n" +
                "  \"statuses_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/statuses/{sha}\",\n" +
                "  \"subscribers_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/subscribers\",\n" +
                "  \"subscription_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/subscription\",\n" +
                "  \"svn_url\":\"https://github.com/EmilLubomirov/CaseBook\",\n" +
                "  \"tags_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/tags\",\n" +
                "  \"teams_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/teams\",\n" +
                "  \"trees_url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook/git/trees{/sha}\",\n" +
                "  \"updated_at\":\"2020-06-27T08:58:14Z\",\n" +
                "  \"url\":\"https://api.github.com/repos/EmilLubomirov/CaseBook\",\n" +
                "  \"watchers\":0,\n" +
                "  \"watchers_count\":0\n" +
                "}";

        final Repository actual = githubClient.searchRepositories("q=tomee")
                .filter(repository -> repository.getName().equals("CaseBook"))
                .findFirst()
                .get();

        JsonAsserts.assertJsonb(expectedJson, actual);
    }

    /**
     * Two streams that produce overlapping results can be merged
     * into one distinct stream allowing "or" searches.
     */
    @Test
    public void searchesCanBeJoinedDistinctly() throws Exception {

        final GithubClient githubClient = GithubClient.builder().api(uri).build();

        /*
         * Produce a stream with duplicate entries, but not duplicate instances
         */
        final List<String> actual = Stream.concat(
                githubClient.searchRepositories("q=tomee"),
                githubClient.searchRepositories("q=tomee")
        )
                .distinct()
                .map(Repository::getName)
                .collect(Collectors.toList());

        /*
         * Ensure the stream can be joined distinctly (no duplicates)
         */
        assertEquals("tomee\n" +
                "docker-tomee\n" +
                "tomee-jaxrs-starter-project\n" +
                "tomee-buildpack\n" +
                "tomee-site-generator\n" +
                "tomee-jaxrs-angular-starter-project\n" +
                "tomee-tck\n" +
                "javaee7-samples\n" +
                "docker_tomee\n" +
                "tomeemaven\n" +
                "tomee-buildpack-resource-configuration\n" +
                "tomee-chatterbox\n" +
                "tomee-site\n" +
                "oscm\n" +
                "docklands\n" +
                "tomee-mongodb-starter-project\n" +
                "vaadinized-tomee-jee6-app-stub\n" +
                "heroku-buildpack-tomee\n" +
                "50-shades-of-tomee\n" +
                "tomcat_to_tomee\n" +
                "tomee-jboss-forge-plugin\n" +
                "cargo\n" +
                "tomee-mvn-installation-example\n" +
                "persistence-test\n" +
                "mongo-realm\n" +
                "tomee\n" +
                "tomee-cli\n" +
                "openshift-cartridge-tomee\n" +
                "tomee-mvn-plugin-jpa21-setup\n" +
                "site-tomee-ng\n" +
                "custom-maven-archetypes\n" +
                "wad\n" +
                "TomEE-Boot-Example\n" +
                "tomee-hibernate-jpa-test\n" +
                "tomee-dev2ops\n" +
                "jcache-tomee\n" +
                "tomee-boot\n" +
                "chef-tomee\n" +
                "tomee-maven-demo\n" +
                "docker-tomee\n" +
                "skeleton-starter-flow-cdi\n" +
                "TomEE\n" +
                "nes-controller\n" +
                "Quickstart-JSF2.3-Samples\n" +
                "MdbExample\n" +
                "hello-prometheus\n" +
                "canjs-tomee-sample\n" +
                "swagger-in-tomee\n" +
                "TomEE-JavaEE7-demo\n" +
                "tomee-issue-2143-example\n" +
                "Vagrant4SIW\n" +
                "watchedmovies\n" +
                "TomeePOM\n" +
                "tomeeDemo\n" +
                "TomeeTest\n" +
                "tomee7-example\n" +
                "TomeePolygot\n" +
                "tomee704\n" +
                "tomee2027\n" +
                "tomeeEJB\n" +
                "Tomeek26-\n" +
                "tomee1\n" +
                "TomeeAnsible\n" +
                "heroes-service\n" +
                "Tomee170\n" +
                "tomeeSkeleton\n" +
                "TomeeCDITest\n" +
                "tomee-microprofile-rest-client-demo\n" +
                "FitTrackerAppServer\n" +
                "tomee-t01\n" +
                "tomee7-plus\n" +
                "tomeeBuildPack\n" +
                "tomeeVm\n" +
                "Tomee20000\n" +
                "TomeeMdbTest\n" +
                "tomee-codi\n" +
                "TomeeEnumRest\n" +
                "TomeeDeltaEar\n" +
                "TomeeJaxrsValidation\n" +
                "tomeeeS.github.io\n" +
                "tomee16-clickstack\n" +
                "tomee7-control\n" +
                "my-tomee17-examples\n" +
                "tomee17-ejb-topics\n" +
                "TomeeMdbDebug\n" +
                "TomeeRestfulWs\n" +
                "tomee7-hibernate-archetype\n" +
                "tomeekk20.github.io\n" +
                "tomee-flyway\n" +
                "projet-C1-2018\n" +
                "TomeeBlobTest\n" +
                "tomee17-templates\n" +
                "tomee17mauro\n" +
                "nbpfcrudgen-lazytest-tomee7\n" +
                "tomee7-dev-starter\n" +
                "tomee17-mdb-topics\n" +
                "TomeeDsValidation\n" +
                "training\n" +
                "Tomeer333.github.io\n" +
                "tomee\n" +
                "tomeekk6.github.io\n" +
                "tomee\n" +
                "tomee\n" +
                "tomee\n" +
                "tomee\n" +
                "BaseTomeeApp\n" +
                "Tomee\n" +
                "Tomee\n" +
                "prova_tomee1.7.3\n" +
                "tomee\n" +
                "tomee\n" +
                "tomee\n" +
                "tomee\n" +
                "ocp-tomee7-jaxrs-po\n" +
                "tomee\n" +
                "Tomee\n" +
                "tomee\n" +
                "tomee\n" +
                "tomee\n" +
                "mongotest\n" +
                "tomee\n" +
                "tomee\n" +
                "ejb31-tomeeplus172-snippets\n" +
                "tomee\n" +
                "POC-Tomee-Servlet\n" +
                "TOMEE\n" +
                "DeltaspikeEar\n" +
                "docker-image-apache-tomee\n" +
                "tomeevaadin\n" +
                "tomeemailtest\n" +
                "tomeeto\n" +
                "tomee-sample\n" +
                "apps.tomee\n" +
                "simple-singleton\n" +
                "tomee-samples\n" +
                "jta_test\n" +
                "Themes-tomee1.7.1-plus-deltaspike\n" +
                "mytestcrud-tomee1.7.2plus\n" +
                "tomee-juju\n" +
                "TomEE\n" +
                "tomee-site-pub\n" +
                "SigreRHEE\n" +
                "ntm_servers\n" +
                "tomee-sample\n" +
                "tomee-patch-plugin\n" +
                "javaee-tomee-jpa\n" +
                "todolist\n" +
                "tomee-example\n" +
                "tomee-ear\n" +
                "tomee-microprofile-config\n" +
                "moviefun\n" +
                "tomeediy\n" +
                "openshift-tomee-rest-example\n" +
                "TomEE_Deployer_In_Ansible\n" +
                "generator-jangular\n" +
                "tomEEexample\n" +
                "tomeetest\n" +
                "tomeeaxis\n" +
                "mytest_tomee1.7.2_no-cdi\n" +
                "datasource-tomee\n" +
                "apache-tomee-plus-1.7.4\n" +
                "tomeefreezetestcase\n" +
                "tomeeherokudemo\n" +
                "tomeenfm\n" +
                "tomeetsleather\n" +
                "tomeetoo\n" +
                "tomeeapp\n" +
                "Sample\n" +
                "ASTomEE\n" +
                "tomee-spring\n" +
                "sample-tomee-heroku\n" +
                "cloud-employeeslistapp-tomee\n" +
                "tomee-war\n" +
                "tomee-eclipse-plugin\n" +
                "tomeedemo\n" +
                "tomEE\n" +
                "toMee\n" +
                "tomeeapp\n" +
                "tomeebug\n" +
                "TomEE\n" +
                "boxdata\n" +
                "tomEE\n" +
                "TomEE\n" +
                "TomEE\n" +
                "tomEE\n" +
                "tomee-hibernate\n" +
                "tomee-bluemix\n" +
                "tomee-jaxrs\n" +
                "TomEEServlets\n" +
                "tomee-camel\n" +
                "jsf-batch-example\n" +
                "clusterize\n" +
                "test-jee\n" +
                "address-manager\n" +
                "embedded_tomee\n" +
                "vagrant_tomee\n" +
                "tomee_test\n" +
                "Ignite\n" +
                "tomee-hello-jax-rs-jpa\n" +
                "tomee-sample\n" +
                "openshift-default-tomee-app\n" +
                "backend\n" +
                "tomee-test\n" +
                "tomee-plume\n" +
                "tomee-cditest\n" +
                "tomee-clickstack\n" +
                "prjTomee\n" +
                "tomee_vestige\n" +
                "tomee-build\n" +
                "tomee-jstl-test\n" +
                "tomee-boot\n" +
                "java-mp-tomtribe-tomee-maven-example\n" +
                "OEIS-Enhancer\n" +
                "CaseBook\n" +
                "test-tomee\n" +
                "tomee_hazelcast\n" +
                "Jenkins_Tomee\n" +
                "tomee-embedded\n" +
                "tomee-composition\n" +
                "tomee-tck\n" +
                "tomee-example\n" +
                "EJB_3-X-on-tomee-plus\n" +
                "tomee-charm-layer\n" +
                "MongoEclipse\n" +
                "vagrant-tomee\n" +
                "demo-tomee\n" +
                "rest-tomee\n" +
                "ToMeet\n" +
                "tomee-manual\n" +
                "TomEECDI\n" +
                "tomee-jaas\n" +
                "microprofile-tomee\n" +
                "tomee-jakarta\n" +
                "tomee-boot-xtend\n" +
                "hello_ejb\n" +
                "tomee-jsf-managedBean-and-ejb\n" +
                "TomEEArchetype\n" +
                "tomee-sample\n" +
                "GoTomeeting", Join.join("\n", actual));
    }


    @Path("/search/repositories")
    public static class MockGithub {
        @GET
        public Response page(@QueryParam("page") int page) throws IOException {

            if (page == 0) page = 1;

            final Link link = TestLinks.getLink(uri, "%s/search/repositories?q=tomee&page=%s", page, 8);

            return Response.ok()
                    .header("Link", link.asHeader())
                    .entity(read(String.format("page%s.json", page)))
                    .build();
        }

        private String read(final String name) throws IOException {
            return Resources.read(StreamPagedItemsTest.class, name);
        }

        public static URI run() throws Exception {
            return MockService.run(MockGithub.class);
        }

    }

}