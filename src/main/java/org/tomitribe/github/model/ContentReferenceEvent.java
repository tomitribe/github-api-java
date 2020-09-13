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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.json.bind.annotation.JsonbProperty;

/**
 * <p>Triggered when the body or comment of an issue or pull request includes a URL that matches a
 * configured content reference domain. Only GitHub Apps can receive this event. GitHub Apps that have
 * the <code>content_references</code> <code>write</code> permission and subscribe to the
 * <code>content_reference</code> event receive this webhook event when a new content reference is
 * <code>created</code>. See "<a href="/apps/using-content-attachments/">Using content attachments</a>"
 * to learn more about content references and attachments.</p>
 *
 * <p>Webhook events are triggered based on the specificity of the domain you register. For example, if
 * you register a subdomain (<code>https://subdomain.example.com</code>) then only URLs for the
 * subdomain trigger this event. If you register a domain (<code>https://example.com</code>) then URLs
 * for domain and all subdomains trigger this event. See "<a
 * href="/v3/apps/installations/#create-a-content-attachment">Create a content attachment</a>" to
 * create a new content attachment.</p>
 * Used by:
 * -
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GithubEvent("content_reference")
@EqualsAndHashCode(callSuper = true)
public class ContentReferenceEvent extends Event {

    @JsonbProperty("action")
    private String action;

    @JsonbProperty("content_reference")
    private ContentReference contentReference;

    @JsonbProperty("installation")
    private Installation installation;

    @JsonbProperty("repository")
    private Repository repository;

    @JsonbProperty("sender")
    private Sender sender;
}
