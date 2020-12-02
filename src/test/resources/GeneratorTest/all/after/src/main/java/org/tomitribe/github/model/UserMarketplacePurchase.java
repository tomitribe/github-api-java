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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tomitribe.github.core.DateAdapter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ComponentId("#/components/schemas/user-marketplace-purchase")
public class UserMarketplacePurchase {

    @JsonbProperty("account")
    private MarketplaceAccount account;

    @JsonbProperty("billing_cycle")
    private String billingCycle;

    @JsonbProperty("free_trial_ends_on")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date freeTrialEndsOn;

    @JsonbProperty("next_billing_date")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date nextBillingDate;

    @JsonbProperty("on_free_trial")
    private Boolean onFreeTrial;

    @JsonbProperty("plan")
    private MarketplaceListingPlan plan;

    @JsonbProperty("unit_count")
    private Integer unitCount;

    @JsonbProperty("updated_at")
    @JsonbTypeAdapter(DateAdapter.class)
    private Date updatedAt;
}
