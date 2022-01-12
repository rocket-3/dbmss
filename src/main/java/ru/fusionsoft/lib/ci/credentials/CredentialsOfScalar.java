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
package ru.fusionsoft.lib.ci.credentials;

import org.cactoos.Scalar;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;

/**
 * The type of {@link Credentials} of {@link Scalar} delegate.
 * @since 0.1
 */
public class CredentialsOfScalar implements Credentials {

    /**
     * The Unchecked Scalar of Credentials encapsulated.
     */
    private final Unchecked<Credentials> scalar;

    /**
     * Instantiates a new Credentials envelope.
     * @param scalar The Scalar of Credentials to be encapsulated.
     */
    public CredentialsOfScalar(final Scalar<Credentials> scalar) {
        this.scalar = new Unchecked<>(
            new Sticky<>(
                scalar
            )
        );
    }

    @Override
    public final String username() {
        return this.scalar.value().username();
    }

    @Override
    public final String password() {
        return this.scalar.value().password();
    }

}
