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
package org.tomitribe.github.client;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.tomitribe.churchkey.Key;
import org.tomitribe.churchkey.Keys;
import org.tomitribe.util.IO;
import org.tomitribe.util.Join;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Utilities for generating a JWT for testing
 */
public class Tokens {

    private Tokens() {
    }

    /**
     * Read a PEM encoded private key from the classpath
     *
     * @param pemFileName - key file resource name
     * @return PrivateKey
     * @throws Exception on decode failure
     */
    public static PrivateKey readPrivateKey(String pemFileName) throws Exception {
        final Key key = getKey(pemFileName);

        final List<String> issues = new ArrayList<String>();

        if (!Key.Type.PRIVATE.equals(key.getType())) {
            issues.add(key.getType().name().toLowerCase());
        }
        if (!Key.Algorithm.RSA.equals(key.getAlgorithm())) {
            issues.add(key.getType().name().toLowerCase());
        }
        if (issues.size() != 0) {
            throw new IllegalStateException("Expected a private rsa key, but found a " + Join.join(" ", issues) + " key.");
        }
        return (PrivateKey) key.getKey();
    }

    public static Key getKey(final String pemFileName) throws IOException {

        { // Resolve the PEM file as full path
            final File file = new File(pemFileName);
            if (file.exists()) return decodePem(file);
        }

        { // Look in .ssh dir by exact name
            final File file = new File(Home.get().ssh(), pemFileName);
            if (file.exists()) return decodePem(file);
        }

        { // Look in .ssh dir by approximate name - starts with pemFileName
            final Key key = Stream.of(Home.get().ssh().listFiles())
                    .sorted(Comparator.comparing(File::getName))
                    .filter(file -> file.getName().startsWith(pemFileName))
                    .limit(1)
                    .map(Tokens::decodePem)
                    .findFirst().orElse(null);
            if (key != null) return key;
        }

        { // Look in the classpath
            final ClassLoader classLoader = Tokens.class.getClassLoader();
            final URL resource = classLoader.getResource(pemFileName);
            if (resource != null) {
                return Keys.decode(IO.readBytes(resource));
            }
        }

        throw new NoPemFileFoundException(pemFileName);
    }

    private static Key decodePem(final File file) {
        try {
            return Keys.decode(IO.readBytes(file));
        } catch (IOException e) {
            final String message = String.format("Unable to read PEM file: %s", file.getAbsolutePath());
            throw new UncheckedIOException(message, e);
        }
    }

    public static Token createToken(final int appId, final String pemFileName) {

        try {
            final PrivateKey pk = readPrivateKey(pemFileName);

            final JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                    .type(JOSEObjectType.JWT)
                    .build();

            final Date iat = new Date();
            final Date exp = new Date(iat.getTime() + TimeUnit.MINUTES.toMillis(50));

            final JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .issuer("" + appId)
                    .expirationTime(exp)
                    .issueTime(iat)
                    .build();


            final SignedJWT jwt = new SignedJWT(header, claims);

            jwt.sign(new RSASSASigner(pk));

            return new Token(exp.getTime() - TimeUnit.MINUTES.toMillis(1), jwt.serialize());
        } catch (Exception e) {
            throw new RuntimeException("Could not sign JWT");
        }
    }
}
