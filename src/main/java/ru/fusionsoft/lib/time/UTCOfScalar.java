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
package ru.fusionsoft.lib.time;

import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;

/**
 * The {@link UTC}, that can be constructed of {@link Scalar} of {@link Long}.
 * @since 0.1
 */
public class UTCOfScalar implements UTC {

    /**
     * The Unchecked<Long> encapsulated.
     */
    private final Unchecked<Long> scalar;

    /**
     * Instantiates a new Utc of scalar.
     * @param scalar The {@link Scalar<Long>} to be encapsulated.
     */
    public UTCOfScalar(final Scalar<Long> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public Long millis() {
        return this.scalar.value();
    }

}
