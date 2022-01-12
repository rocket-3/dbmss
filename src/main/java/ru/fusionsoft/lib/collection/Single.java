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
package ru.fusionsoft.lib.collection;

import java.text.MessageFormat;
import org.cactoos.Scalar;
import org.cactoos.set.SetOf;
import ru.fusionsoft.lib.exception.ValidationException;

/**
 * The {@link Scalar} of single value from {@link Iterable},
 *  throws {@link RuntimeException} if there no single value.
 * @param <T> The type parameter.
 * @since 0.1
 */
public class Single<T> implements Scalar<T> {

    /**
     * The Iterable of T encapsulated.
     */
    private final Iterable<T> iterable;

    /**
     * Instantiates a new Single.
     * @param iterable The Iterable of T to be encapsulated.
     */
    public Single(final Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Override
    public final T value() {
        final int size = new SetOf<T>(this.iterable).size();
        if (size != 1) {
            throw new ValidationException(
                MessageFormat.format(
                    "The iterable contains {0} elements, expected one only.",
                    size
                )
            );
        }
        return this.iterable.iterator().next();
    }

}
