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
 *
 */
package org.fusionsoft.lib.collection;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Set;
import org.cactoos.map.MapEnvelope;

public class MapHasKeys<X, Y> extends MapEnvelope<X, Y> {

    /**
     * @param map  The original map to be tested before each operation on it.
     * @param keys The keys the map should contain.
     * @throws RuntimeException is thrown at the moment of usage, if any of `keys` is not found in `map`
     * @implNote also wrapped by StrictMap
     * @see StrictMap
     */
    public MapHasKeys(final Set<X> keys, final Map<X, Y> map) {
        super(
            new StrictMap<>(
                new ValidatedMap<>(
                    m -> keys.forEach(k -> {
                        if (! map.containsKey(k)) {
                            throw new RuntimeException(MessageFormat.format(
                                "No value found for key {0}",
                                k.toString()
                            ));
                        }
                    }),
                    map
                )
            )
        );
    }

}
