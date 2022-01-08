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
package org.fusionsoft.lib.collection;

import org.cactoos.Scalar;

/**
 * The {@link Scalar} of single {@link T} element from collection or fallback {@link T} on empty.
 * @param <T> The type parameter.
 * @since 0.1
 */
public class SingleOrEmptyFallback<T> implements Scalar<T> {

    /**
     * The Iterable encapsulated.
     */
    private final Iterable<? extends T> iterable;

    /**
     * The T encapsulated.
     */
    private final T fallback;

    /**
     * Instantiates a new Single or empty fallback.
     * @param iterable The {@link Iterable} to be encapsulated.
     * @param fallback The fallback {@link T} entity to be encapsulated.
     */
    public SingleOrEmptyFallback(final Iterable<? extends T> iterable, final T fallback) {
        this.iterable = iterable;
        this.fallback = fallback;
    }

    @Override
    public final T value() {
        T value = this.fallback;
        if (this.iterable.iterator().hasNext()) {
            value = new Single<>(this.iterable).value();
        }
        return value;
    }

}
