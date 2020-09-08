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
/**
 * The goal of the core package is to hold all generic
 * client code that isn't specific to the Github API and
 * could potentially be used by clients for other APIs
 *
 * Our goal is to keep the GithubClient and similar classes
 * as thin as possible.
 *
 * In the future the core package may be moved out to a
 * more generic location and shared by any future client
 * apis we might create.
 */
package org.tomitribe.github.core;