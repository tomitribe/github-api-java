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

import java.util.Date;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tomitribe.github.core.DateAdapter;
import org.tomitribe.github.core.EnumAdapter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ComponentId("#/components/schemas/code-scanning-alert")
public class CodeScanningAlert {

    @JsonbProperty("closed_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date closedAt;

    @JsonbProperty("closed_by")
    private SimpleUser closedBy;

    @JsonbProperty("closed_reason")
    @JsonbTypeAdapter(ClosedReasonAdapter.class)
    private ClosedReason closedReason;

    @JsonbProperty("created_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date createdAt;

    @JsonbProperty("html_url")
    private String htmlUrl;

    @JsonbProperty("number")
    private Integer number;

    @JsonbProperty("open")
    private Boolean open;

    @JsonbProperty("rule_description")
    private String ruleDescription;

    @JsonbProperty("rule_id")
    private String ruleId;

    @JsonbProperty("rule_severity")
    @JsonbTypeAdapter(RuleSeverityAdapter.class)
    private RuleSeverity ruleSeverity;

    @JsonbProperty("tool")
    private String tool;

    @JsonbProperty("url")
    private String url;

    public enum ClosedReason {

        FALSE_POSITIVE("false positive"), WONT_FIX("won't fix"), USED_IN_TESTS("used in tests");

        private final String name;

        ClosedReason(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static class ClosedReasonAdapter extends EnumAdapter<ClosedReason> {

        public ClosedReasonAdapter() {
            super(ClosedReason.class);
        }
    }

    public enum RuleSeverity {

        NONE("none"), NOTE("note"), WARNING("warning"), ERROR("error");

        private final String name;

        RuleSeverity(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static class RuleSeverityAdapter extends EnumAdapter<RuleSeverity> {

        public RuleSeverityAdapter() {
            super(RuleSeverity.class);
        }
    }
}
