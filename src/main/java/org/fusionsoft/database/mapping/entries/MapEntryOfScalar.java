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
package org.fusionsoft.database.mapping.entries;

import java.util.Map;
import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;

/**
 * The {@link Map.Entry} of {@link Scalar}.
 * @param <K> The type of key parameter.
 * @param <V> The type of value parameter.
 * @since 0.1
 */
public class MapEntryOfScalar<K, V> implements Map.Entry<K, V> {

    /**
     * The {@link Unchecked} {@link Scalar} encapsulated.
     */
    private final Unchecked<? extends Map.Entry<K, V>> scalar;

    /**
     * Instantiates a new Map entry of scalar.
     * @param scalar The {@link Scalar} of {@link Map.Entry} to be encapsulated.
     */
    public MapEntryOfScalar(final Scalar<? extends Map.Entry<K, V>> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public final K getKey() {
        return this.scalar.value().getKey();
    }

    @Override
    public final V getValue() {
        return this.scalar.value().getValue();
    }

    @Override
    public final V setValue(final V value) {
        return this.scalar.value().setValue(value);
    }

}
