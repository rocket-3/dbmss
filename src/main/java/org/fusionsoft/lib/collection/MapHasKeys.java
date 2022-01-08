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

import java.util.Map;
import java.util.Set;
import org.cactoos.map.MapEnvelope;
import org.fusionsoft.lib.exception.TextOfMap;
import org.fusionsoft.lib.exception.ValueNotFoundException;

/**
 * The type of Map that can be constructed of another map and keys
 *  to be validated.
 * @param <X> The type parameter of key.
 * @param <Y> The type parameter of value.
 * @since 0.1
 */
public class MapHasKeys<X, Y> extends MapEnvelope<X, Y> {

    /**
     * Instantiates a new Map has keys.
     * @param keys The keys the map should contain.
     * @param map The original map to be tested before each operation on it.
     * @throws RuntimeException Throws at the moment of usage,
     *  if any of `keys` is not found in `map`
     * @implNote Also wrapped by {@link StrictMap}
     * @since 0.1
     */
    public MapHasKeys(final Set<X> keys, final Map<X, Y> map) {
        super(
            new StrictMap<>(
                new ValidatedMap<>(
                    m -> keys.forEach(
                        k -> {
                            if (!map.containsKey(k)) {
                                throw new ValueNotFoundException(
                                    k.toString(),
                                    new TextOfMap(map).asString()
                                );
                            }
                        }
                    ),
                    map
                )
            )
        );
    }

}
