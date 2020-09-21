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
package org.tomitribe.github.gen.util;

import org.tomitribe.util.Strings;

import java.util.Arrays;
import java.util.List;

public class Words {
    private static final List<String> keywords = Arrays.asList(
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
            "class", "const", "continue", "default", "do", "double", "else", "enum", "exports",
            "extends", "false", "final", "finally", "float", "for", "goto", "if", "implements",
            "import", "instanceof", "int", "interface", "long", "module", "native", "new", "null",
            "package", "private", "protected", "public", "requires", "return", "short", "static",
            "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient",
            "true", "try", "var", "void", "volatile", "while"
    );

    private Words() {
    }

    public static String getTypeName(final String jsonName) {
        if ("-1".equals(jsonName)) {
            return "negativeOne";
        }

        if ("+1".equals(jsonName)) {
            return "positiveOne";
        }

        final String cleaned = jsonName
                .replace("_", "-")
                .replace(" ", "-")
                .replace("(", "-")
                .replace(")", "-")
                .replace("@", "")
                .replace("-a-", "-")
                .replace("-an-", "-")
                .replace("-the-", "-")
                .replaceAll("-+", "-")
                .replaceAll("^-+", "");

        if (keywords.contains(cleaned)) {
            return "_" + cleaned;
        }

        try {
            return Strings.camelCase(cleaned);
        } catch (final Exception e) {
            throw new IllegalStateException(String.format("Cannot camelCase string '%s'", jsonName), e);
        }
    }

    public static String getVariableName(final String jsonName) {
        return Strings.lcfirst(getTypeName(jsonName));
    }

}
