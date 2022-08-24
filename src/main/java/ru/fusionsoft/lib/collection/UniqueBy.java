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

import org.cactoos.Func;
import org.cactoos.map.MapOf;

/**
 * The {@link Iterable}, but you can supply a hashcode function to index and filter out repeating
 *  elements.
 * @param <T> The iterable type parameter
 * @since 0.1
 */
public class UniqueBy<T> extends IterableOfScalarSticky<T> {

    /**
     * Instantiates a new Unique by.
     * @param key The key function
     * @param origin The iterable
     * @param <K> The key type
     */
    public <K> UniqueBy(final Func<T, K> key, final Iterable<T> origin) {
        super(
            () -> {
                return new MapOf<K, T>(
                    key,
                    item -> item,
                    origin
                ).values();
            }
        );
    }

}
