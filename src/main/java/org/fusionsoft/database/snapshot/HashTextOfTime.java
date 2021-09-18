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
package org.fusionsoft.database.snapshot;

import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOfScalar;

/**
 * The type of Text that is a hash of {@link AstronomicalTime}.
 * @since 0.1
 * @todo #15:15min Implement hashing of AstronomicalTime.
 */
public class HashTextOfTime extends TextEnvelope {

    /**
     * Instantiates a new Hash text of time.
     * @param time The AstronomicalTime to be encapsulated.
     */
    public HashTextOfTime(final AstronomicalTime time) {
        super(
            new TextOfScalar(
                () -> time.toString()
            )
        );
    }

}
