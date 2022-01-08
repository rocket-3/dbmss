/*
 * Copyright (C) 2018-2022 FusionSoft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.
 *
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.fusionsoft.lib.ci.credentials;

/**
 * The type of {@link Credentials} that can be constructed of text values.
 * @since 0.1
 */
public class SimpleCredentials implements Credentials {

    /**
     * The String encapsulated.
     */
    private final String name;

    /**
     * The String encapsulated.
     */
    private final String pwd;

    /**
     * Instantiates a new Simple credentials.
     * @param username The String to be encapsulated.
     * @param password The String to be encapsulated.
     */
    public SimpleCredentials(final String username, final String password) {
        this.name = username;
        this.pwd = password;
    }

    @Override
    public final String username() {
        return this.name;
    }

    @Override
    public final String password() {
        return this.pwd;
    }

}
