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
package ru.fusionsoft.database.migration.typecasts;

import org.cactoos.Text;

/**
 * The {@link Typecast} implementation.
 * @since 0.1
 */
public class SimpleTypecast implements Typecast {

    /**
     * The Text encapsulated.
     */
    private final Text first;

    /**
     * The Text encapsulated.
     */
    private final Text second;

    /**
     * The Boolean encapsulated.
     */
    private final Boolean possible;

    /**
     * Instantiates a new Simple typecast.
     * @param source The {@link Text} target be encapsulated.
     * @param target The {@link Text} target be encapsulated.
     * @param implicit The {@link Boolean} target be encapsulated.
     */
    public SimpleTypecast(final Text source, final Text target, final Boolean implicit) {
        this.first = source;
        this.second = target;
        this.possible = implicit;
    }

    @Override
    public final Text current() {
        return this.first;
    }

    @Override
    public final Text next() {
        return this.second;
    }

    @Override
    public final Boolean implicit() {
        return this.possible;
    }

}
