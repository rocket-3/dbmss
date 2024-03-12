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

import org.cactoos.Scalar;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;

/**
 * {@link Scalar} of {@link Iterable} to {@link IterableEnvelope}.
 *  We should replace it and all lazy style calculus.
 * @param <T> The type parameter.
 * @since 0.1
 */
public class IterableOfScalarSticky<T> extends IterableEnvelope<T> {

    /**
     * Ctor.
     * @param scalar The wrapped scalar
     */
    public IterableOfScalarSticky(final Scalar<Iterable<T>> scalar) {
        super(
            new org.cactoos.iterable.Sticky<T>(
                new IterableOf<T>(
                    () -> scalar.value().iterator()
                )
            )
        );
    }

}
