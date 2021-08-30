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

import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The test for {@link StrictMap}.
 * @since 0.1
 */
class StrictMapTest {

    /**
     * Can get existing value.
     */
    @Test
    void canGetExistingValue() {
        Assertions.assertDoesNotThrow(
            () -> new StrictMap<>(
                new MapOf<>(
                    new MapEntry<>("a", "1"),
                    new MapEntry<>("b", "2")
                )
            ).get("a")
        );
    }

    /**
     * Throws {@link RuntimeException} on getting absent value.
     */
    @Test
    void canNotGetAbsentValue() {
        Assertions.assertThrows(
            RuntimeException.class,
            () -> new StrictMap<>(
                new MapOf<>(
                    new MapEntry<>("a", "1")
                )
            ).get("b")
        );
    }

}
