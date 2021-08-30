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
package org.fusionsoft.lib.collection;

import org.cactoos.Proc;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.ScalarOf;

/**
 * The type of Iterable that is validated at each usage.
 * @param <X> The type of iterable parameter.
 * @since 0.1
 */
public class ValidatedIterable<X> extends IterableEnvelope<X> {

    /**
     * Ctor.
     * @param validation The Proc of Iterable X to be encapsulated.
     * @param col The wrapped collection.
     */
    public ValidatedIterable(
        final Proc<Iterable<X>> validation,
        final Iterable<X> col
    ) {
        super(
            new IterableOf<>(
                new ScalarOf<>(
                    () -> {
                        validation.exec(col);
                        return col.iterator();
                    }
                )
            )
        );
    }

}
