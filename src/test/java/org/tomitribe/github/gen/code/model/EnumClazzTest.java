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
package org.tomitribe.github.gen.code.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class EnumClazzTest {

    @Test
    public void asConstant() {
        /*
         * Non-alphanumeric characters are translated to an underscore (_)
         */
        assertEquals("GOOD_STUFF", EnumClazz.asConstant("good @#$%^&*()_-+~`\"{}[]\\|?/.,<> stuff"));

        /*
         * Apostrophes are deleted, not translated to an underscore (_)
         */
        assertEquals("WONT_FIX", EnumClazz.asConstant("won't fix"));

        /*
         * We cannot have leading or trailing spaces
         * We cannot have multiple underscores in a row
         */
        assertEquals("MANY_SPACES", EnumClazz.asConstant("   many   spaces  "));

        /*
         * If the constant starts with a number this isn't legal java syntax.
         * We deal with this by adding an underscore.
         */
        assertEquals("_40GB", EnumClazz.asConstant("40GB"));
    }
}
