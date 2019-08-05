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

 * <p>Triggered when a <a href="/v3/repos/releases/#get-a-single-release">release</a> is
 * <code>published</code>, <code>unpublished</code>, <code>created</code>, <code>edited</code>,
 * <code>deleted</code>, or <code>prereleased</code>.</p>
 * Used by:
 * - 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("release")
public class ReleaseEvent {

/**
 * The action that was performed. Currently, can be <code>published</code>, <code>unpublished</code>,
 * <code>created</code>, <code>edited</code>, <code>deleted</code>, or <code>prereleased</code>.
 */
    @JsonbProperty("action")
    private String action;

/**
 * The <a href="/v3/repos/releases/#get-a-single-release">release</a> object.
 */
    @JsonbProperty("release")
    private Release release;

    @JsonbProperty("repository")
    private Repository repository;

    @JsonbProperty("sender")
    private Sender sender;

}
