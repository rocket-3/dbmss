/*
 * Copyright (C) 2018-2021 FusionSoft
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

import org.cactoos.text.Joined;
import org.fusionsoft.lib.exception.ValueNotFoundException;

/**
 * The type of {@link Credentials} that can be constructed of.
 * @since 0.1
 */
public class CredentialsFromProperties extends CredentialsOfScalar {

    /**
     * Instantiates a new Credentials from properties.
     * @param propuser The properties key with username to be encapsulated.
     * @param proppassword The properties key with password to be encapsulated.
     */
    public CredentialsFromProperties(final String propuser, final String proppassword) {
        super(() -> {
            final String usr = System.getProperty(propuser);
            final String pwd = System.getProperty(proppassword);
            if (usr == null || usr.isEmpty() || pwd == null || pwd.isEmpty()) {
                throw new ValueNotFoundException(
                    new Joined(",", propuser, proppassword).asString(),
                    "System.props"
                );
            }
            return new SimpleCredentials(usr, pwd);
        });
    }

}
