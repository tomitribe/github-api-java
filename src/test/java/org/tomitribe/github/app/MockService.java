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
package org.tomitribe.github.app;

import org.apache.tomee.bootstrap.Archive;
import org.apache.tomee.bootstrap.Server;
import org.tomitribe.util.IO;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public interface MockService {

    static URI run(final Class<?> serviceClass) throws Exception {
        return Server.builder()
                .add("webapps/ROOT/WEB-INF/classes", Archive.archive()
                        .add(serviceClass)
                ).build().getURI();
    }

    default Response response(final String name) throws IOException {
        final URL resource = this.getClass().getClassLoader().getResource(name);
        final String json = IO.slurp(resource);
        return Response.ok()
                .header("Content-Type", "application/json")
                .entity(json)
                .build();
    }
}
