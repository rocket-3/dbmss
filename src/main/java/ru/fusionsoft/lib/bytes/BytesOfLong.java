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
package ru.fusionsoft.lib.bytes;

import java.nio.ByteBuffer;
import org.cactoos.Bytes;
import org.cactoos.Scalar;

/**
 * The {@link Bytes} that can be constructed of {@link Long} or {@link Scalar} of {@link Long}.
 * @since 0.1
 */
public class BytesOfLong implements Bytes {

    /**
     * The Scalar of Long encapsulated.
     */
    private final Scalar<Long> scalar;

    /**
     * Instantiates a new Bytes of long.
     * @param value The {@link long} to be encapsulated.
     */
    public BytesOfLong(final long value) {
        this(() -> value);
    }

    /**
     * Instantiates a new Bytes of long.
     * @param scalar The {@link Scalar<Long>} to be encapsulated.
     */
    public BytesOfLong(final Scalar<Long> scalar) {
        this.scalar = scalar;
    }

    @Override
    public byte[] asBytes() throws Exception {
        //@checkstyle MagicNumberCheck (1 lines)
        return ByteBuffer.allocate(8).putLong(this.scalar.value()).array();
    }

}
