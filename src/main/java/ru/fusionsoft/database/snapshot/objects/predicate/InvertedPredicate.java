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
package ru.fusionsoft.database.snapshot.objects.predicate;

import org.cactoos.Func;

/**
 * Predicate decorator, which inverts the value.
 * @param <T> The type parameter.
 * @since 0.1
 */
public class InvertedPredicate<T> implements Func<T, Boolean> {

    /**
     * The predicate encapsulated.
     */
    private final Func<T, Boolean> original;

    /**
     * Instantiates a new Inverted predicate.
     * @param original The predicate to be encapsulated.
     */
    public InvertedPredicate(final Func<T, Boolean> original) {
        this.original = original;
    }

    @Override
    public final Boolean apply(final T input) throws Exception {
        return !this.original.apply(input);
    }

}
