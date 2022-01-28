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
package ru.fusionsoft.lib.text;

import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.Unchecked;

/**
 * The {@link org.cactoos.Text} that is hex value of {@link Long}.
 * @since 0.1
 */
public class HexOfLong implements Text {

    /**
     * The {@link Scalar} of {@link Long} encapsulated.
     */
    private final Unchecked<Long> scalar;

    /**
     * Instantiates a new Hex of long.
     * @param value The {@link Long} to be encapsulated.
     */
    public HexOfLong(final Long value) {
        this(() -> value);
    }

    /**
     * Instantiates a new Hex of long in lazy manner.
     * @param scalar The {@link Scalar} of {@link Long} to be encapsulated.
     */
    public HexOfLong(final Scalar<Long> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public final String asString() {
        return Long.toHexString(this.scalar.value());
    }

}
