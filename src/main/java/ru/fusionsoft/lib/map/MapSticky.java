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

package ru.fusionsoft.lib.map;

import java.util.Map;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.Sticky;

public class MapSticky<K,V> extends MapOfScalar<K, V> {

    public MapSticky(final Map<K, V> origin) {
        super(
            new Sticky<>(
                () -> new MapOf<K, V>((Entry<? extends K, ? extends V>) origin.entrySet())
            )
        );
    }

}
