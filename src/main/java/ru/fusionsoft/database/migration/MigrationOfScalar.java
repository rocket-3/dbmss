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
package ru.fusionsoft.database.migration;

import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.Unchecked;

/**
 * The {@link Migration} that uses {@link Scalar} returning value as delegate.
 * @since 0.1
 */
public class MigrationOfScalar implements Migration {

    /**
     * The {@link Unchecked} of Migration encapsulated.
     */
    private final Unchecked<Migration> origin;

    /**
     * Instantiates a new Migration of scalar.
     * @param origin The {@link Scalar} of Migration to be encapsulated.
     */
    public MigrationOfScalar(final Scalar<Migration> origin) {
        this.origin = new Unchecked<>(origin);
    }

    @Override
    public final Text description() {
        return this.origin.value().description();
    }

    @Override
    public final Text sql() {
        return this.origin.value().sql();
    }

}
