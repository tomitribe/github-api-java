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


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RepositoryURITest {

    @Test
    public void parseSshUrl() {
        final RepositoryURI repositoryURI = RepositoryURI.parse("git@github.com:jakartaee/specifications.git");
        assertEquals("git", repositoryURI.getScheme());
        assertEquals("github.com", repositoryURI.getHost());
        assertEquals("jakartaee", repositoryURI.getOwner());
        assertEquals("specifications", repositoryURI.getName());
    }

    @Test
    public void parseHttpsUrl() {
        final RepositoryURI repositoryURI = RepositoryURI.parse("https://github.com/apache/tomee-site.git");
        assertEquals("https", repositoryURI.getScheme());
        assertEquals("github.com", repositoryURI.getHost());
        assertEquals("apache", repositoryURI.getOwner());
        assertEquals("tomee-site", repositoryURI.getName());
    }

    @Test
    public void parseHttpsUrlWithPort() {
        final RepositoryURI repositoryURI = RepositoryURI.parse("https://github.com:443/apache/tomee-site.git");
        assertEquals("https", repositoryURI.getScheme());
        assertEquals("github.com:443", repositoryURI.getHost());
        assertEquals("apache", repositoryURI.getOwner());
        assertEquals("tomee-site", repositoryURI.getName());
    }


    @Test
    public void filePath() {
        final RepositoryURI repositoryURI = RepositoryURI.parse("/var/folders/bd/f9ntqy1m8xj_fs006s6crtjh0000gn/T/temp5411678307648808546dir/templates");
        assertNull(repositoryURI.getScheme());
        assertNull(repositoryURI.getHost());
        assertEquals("/var/folders/bd/f9ntqy1m8xj_fs006s6crtjh0000gn/T/temp5411678307648808546dir", repositoryURI.getOwner());
        assertEquals("templates", repositoryURI.getName());

        assertEquals("/var/folders/bd/f9ntqy1m8xj_fs006s6crtjh0000gn/T/temp5411678307648808546dir/templates", repositoryURI.toString());
    }

    @Test
    public void getFullName() {
        final RepositoryURI repositoryURI = RepositoryURI.parse("https://github.com:443/apache/tomee-site.git");
        assertEquals("apache/tomee-site", repositoryURI.getFullName());
    }

    @Test
    public void testToStringHttp() {
        assertEquals("https://github.com:443/apache/tomee-site.git",
                RepositoryURI.parse("https://github.com:443/apache/tomee-site.git").toString());
    }

    @Test
    public void testToStringSsh() {
        assertEquals("git@red:green/blue.git",
                RepositoryURI.parse("git@red:green/blue.git").toString());
    }

}