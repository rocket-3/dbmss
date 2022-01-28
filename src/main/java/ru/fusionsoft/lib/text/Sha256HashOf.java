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

import org.cactoos.Input;
import org.cactoos.Text;
import org.cactoos.bytes.Sha256DigestOf;
import org.cactoos.io.InputOf;
import org.cactoos.text.HexOf;
import org.cactoos.text.TextEnvelope;
import ru.fusionsoft.lib.bytes.BytesOfLong;
import ru.fusionsoft.lib.time.Utc;
import ru.fusionsoft.lib.time.UtcNow;

/**
 * The type of Text that is a hash of {@link UtcNow}.
 * @since 0.1
 */
public class Sha256HashOf extends TextEnvelope {

    /**
     * Instantiates a new Hash text of time.
     * @param input The Input to be used.
     */
    public Sha256HashOf(final Input input) {
        super(
            new HexOf(new Sha256DigestOf(input))
        );
    }

    /**
     * Instantiates a new Hash text of time.
     * @param text The Text to be hashed.
     */
    public Sha256HashOf(final Text text) {
        this(new InputOf(text));
    }

    /**
     * Instantiates a new Hash text of time.
     * @param time The Text to be hashed.
     */
    public Sha256HashOf(final Utc time) {
        this(
            new InputOf(
                new BytesOfLong(time::millis)
            )
        );
    }

}
