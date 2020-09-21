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
package org.tomitribe.github.gen.code.endpoint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.tomitribe.github.gen.code.model.Clazz;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
public class EndpointMethod {

    private Clazz response;
    private Clazz request;
    private String method;
    private String path;
    private String category;
    private String subcategory;
    private String summary;
    private String javaMethod;
    private String operationId;
    private String removalDate;
    private String deprecationDate;
    private String docs;
    private List<String> previews;
    private boolean githubCloudOnly;
    private boolean enabledForGitHubApps;

    public static class Builder {
        private List<String> previews = new ArrayList<>();

        public Builder preview(final String preview) {
            this.previews.add(preview);
            return this;
        }
    }
}
