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
package ru.fusionsoft.database.snapshot.query;

import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.Unchecked;

/**
 * The type of {@link Query} that can be constructed of {@link Scalar}.
 * @param <E> The E type of Query parameter.
 * @since 0.1
 */
public class QueryOfScalar<E extends Text> implements Query<E> {

    /**
     * The Scalar of Query encapsulated.
     */
    private final Unchecked<Query<E>> scalar;

    /**
     * Instantiates a new Query of scalar.
     * @param scalar The Scalar of Query to be encapsulated.
     */
    public QueryOfScalar(final Scalar<Query<E>> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public final String asString() throws Exception {
        return this.scalar.value().asString();
    }

    @Override
    public final String outcomeFor(final E prop) {
        return this.scalar.value().outcomeFor(prop);
    }

}
