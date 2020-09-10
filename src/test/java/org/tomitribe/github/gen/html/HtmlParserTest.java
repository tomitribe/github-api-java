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
package org.tomitribe.github.gen.html;

import org.junit.Test;
import org.tomitribe.github.JsonAsserts;
import org.tomitribe.github.Resources;
import org.tomitribe.github.gen.Gen;
import org.tomitribe.github.gen.Project;
import org.tomitribe.util.IO;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HtmlParserTest {

    @Test
    public void actions() throws Exception {
        assertHtml("actions");
    }

    @Test
    public void activity() throws Exception {
        assertHtml("activity");
    }

    @Test
    public void apps() throws Exception {
        assertHtml("apps");
    }

    @Test
    public void billing() throws Exception {
        assertHtml("billing");
    }

    @Test
    public void checks() throws Exception {
        assertHtml("checks");
    }

    @Test
    public void codeScanning() throws Exception {
        assertHtml("code-scanning");
    }

    @Test
    public void codesOfConduct() throws Exception {
        assertHtml("codes-of-conduct");
    }

    @Test
    public void emojis() throws Exception {
        assertHtml("emojis");
    }

    @Test
    public void enterpriseAdmin() throws Exception {
        assertHtml("enterprise-admin");
    }

    @Test
    public void gists() throws Exception {
        assertHtml("gists");
    }

    @Test
    public void git() throws Exception {
        assertHtml("git");
    }

    @Test
    public void gitignore() throws Exception {
        assertHtml("gitignore");
    }

    @Test
    public void index() throws Exception {
        assertHtml("index");
    }

    @Test
    public void interactions() throws Exception {
        assertHtml("interactions");
    }

    @Test
    public void issues() throws Exception {
        assertHtml("issues");
    }

    @Test
    public void licenses() throws Exception {
        assertHtml("licenses");
    }

    @Test
    public void markdown() throws Exception {
        assertHtml("markdown");
    }

    @Test
    public void meta() throws Exception {
        assertHtml("meta");
    }

    @Test
    public void migrations() throws Exception {
        assertHtml("migrations");
    }

    @Test
    public void oauthAuthorizations() throws Exception {
        assertHtml("oauth-authorizations");
    }

    @Test
    public void orgs() throws Exception {
        assertHtml("orgs");
    }

    @Test
    public void permissionsRequiredForGithubApps() throws Exception {
        assertHtml("permissions-required-for-github-apps");
    }

    @Test
    public void projects() throws Exception {
        assertHtml("projects");
    }

    @Test
    public void pulls() throws Exception {
        assertHtml("pulls");
    }

    @Test
    public void rateLimit() throws Exception {
        assertHtml("rate-limit");
    }

    @Test
    public void reactions() throws Exception {
        assertHtml("reactions");
    }

    @Test
    public void repos() throws Exception {
        assertHtml("repos");
    }

    @Test
    public void scim() throws Exception {
        assertHtml("scim");
    }

    @Test
    public void search() throws Exception {
        assertHtml("search");
    }

    @Test
    public void teams() throws Exception {
        assertHtml("teams");
    }

    @Test
    public void users() throws Exception {
        assertHtml("users");
    }


    public static void assertHtml(final String page) throws IOException {
        final Gen gen = Project.root().src().test().resources().gen();

        final File html = gen.html().file(page + ".html");
        final File json = gen.json().file(page + ".json");

        final List<Endpoint> endpoints = HtmlParser.parse(html);

        JsonAsserts.assertJsonb(IO.slurp(json), endpoints);
    }

    @Test
    public void stripHtml() throws Exception {
        final String input = Resources.read(HtmlParserTest.class, "stripHtml-input.html");
        final String expected = Resources.read(HtmlParserTest.class, "stripHtml-expected.json");

        final String actual = HtmlParser.stripHtml(input);
        assertEquals(expected, actual);
        JsonAsserts.assertJson(expected, actual);
    }

    @Test
    public void parseParameterProperties() throws Exception {
        final String input = "            <summary class=\"text-gray\"><h4 id=\"committer-object\">Properties of the <code>committer</code> object</h4></summary>\n" +
                "            <table>\n" +
                "              <tbody>\n" +
                "                \n" +
                "                  <tr>\n" +
                "                    <td class=\"opacity-60\"><code>name</code> (string)</td>\n" +
                "                    <td class=\"opacity-60\"><p><strong>Required</strong>. The name of the author or committer of the commit. You&apos;ll receive a <code>422</code> status code if <code>name</code> is omitted.</p></td>\n" +
                "                  </tr>\n" +
                "                \n" +
                "                  <tr>\n" +
                "                    <td class=\"opacity-60\"><code>email</code> (string)</td>\n" +
                "                    <td class=\"opacity-60\"><p><strong>Required</strong>. The email of the author or committer of the commit. You&apos;ll receive a <code>422</code> status code if <code>email</code> is omitted.</p></td>\n" +
                "                  </tr>\n" +
                "                \n" +
                "              </tbody>\n" +
                "            </table>\n";

        final Endpoint endpoint = new Endpoint();
        HtmlParser.parseParameterProperties(endpoint, input);
        final List<ParameterProperty> parameterProperties = endpoint.getParameterProperties();

        JsonAsserts.assertJsonb("[\n" +
                "  {\n" +
                "    \"description\":\"<strong>Required</strong>. The name of the author or committer of the commit. " +
                "You&apos;ll receive a <code>422</code> status code if <code>name</code> is omitted.\",\n" +
                "    \"name\":\"name\",\n" +
                "    \"of\":\"committer\",\n" +
                "    \"type\":\"string\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"description\":\"<strong>Required</strong>. The email of the author or committer of the commit. " +
                "You&apos;ll receive a <code>422</code> status code if <code>email</code> is omitted.\",\n" +
                "    \"name\":\"email\",\n" +
                "    \"of\":\"committer\",\n" +
                "    \"type\":\"string\"\n" +
                "  }\n" +
                "]", parameterProperties);
    }
}
