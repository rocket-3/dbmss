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
package org.fusionsoft.database.ci.credentials;

import org.cactoos.Fallback;
import org.cactoos.scalar.ScalarWithFallback;
import org.fusionsoft.lib.ci.credentials.Credentials;
import org.fusionsoft.lib.ci.credentials.CredentialsOfScalar;
import org.fusionsoft.lib.ci.credentials.SimpleCredentials;
import org.fusionsoft.lib.exception.ValueNotFoundException;

/**
 * The type of {@link Credentials} that are {@link CredsFromPgMvnDProps}
 *  or {@link CredsFromPgSecretFile} instead.
 * @since 0.1
 */
public class CredsOfPgTestDatabase extends CredentialsOfScalar {

    /**
     * Instantiates a new Creds of pg test database.
     */
    public CredsOfPgTestDatabase() {
        super(
            new ScalarWithFallback<>(
                () -> {
                    final Credentials creds = new CredsFromPgMvnDProps();
                    return new SimpleCredentials(
                        creds.username(),
                        creds.password()
                    );
                },
                new Fallback.From<>(
                    ValueNotFoundException.class,
                    thr -> {
                        final Credentials creds = new CredsFromPgSecretFile();
                        return new SimpleCredentials(
                            creds.username(),
                            creds.password()
                        );
                    }
                )
            )
        );
    }

}
