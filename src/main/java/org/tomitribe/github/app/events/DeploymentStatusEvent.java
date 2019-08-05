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

package org.tomitribe.github.app.events;

import javax.json.bind.annotation.JsonbProperty;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

/**

 * <p>Represents a <a href="/v3/repos/deployments/#list-deployment-statuses">deployment status</a>.</p>
 * 
 * <p>Events of this type are not visible in timelines. These events are only used to trigger
 * hooks.</p>
 * Used by:
 * - 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("deployment_status")
public class DeploymentStatusEvent {

/**
 * The <a href="/v3/repos/deployments/#list-deployment-statuses">deployment status</a>.
 */
    @JsonbProperty("deployment_status")
    private DeploymentStatus deploymentStatus;

/**
 * The <a href="/v3/repos/deployments/#list-deployments">deployment</a> that this status is associated
 * with.
 */
    @JsonbProperty("deployment")
    private Deployment deployment;

/**
 * The <a href="/v3/repos/">repository</a> for this deployment.
 */
    @JsonbProperty("repository")
    private Repository repository;

    @JsonbProperty("sender")
    private Sender sender;

}
