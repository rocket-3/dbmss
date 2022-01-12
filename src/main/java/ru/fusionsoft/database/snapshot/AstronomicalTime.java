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
package ru.fusionsoft.database.snapshot;

import java.nio.ByteBuffer;
import java.util.Date;
import org.cactoos.Bytes;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;

/**
 * The class representing 'Astronomical type' at the moment of creation.
 * @since 0.1
 */
public class AstronomicalTime implements Scalar<Long>, Text, Bytes {

    /**
     * The scalar of Long encapsulated.
     */
    private final Unchecked<Long> scalar;

    /**
     * Instantiates a new Astronomical time.
     * @param millis The Scalar of Long to be encapsulated.
     */
    private AstronomicalTime(final Scalar<Long> millis) {
        this.scalar = new Unchecked<>(new Sticky<>(millis));
    }

    /**
     * Instantiates a new Astronomical time,
     *  which millis is taken at the moment of usage.
     */
    public AstronomicalTime() {
        this(
            () -> new Date().getTime()
        );
    }

    /**
     * Instantiates a new Astronomical time.
     * @param millis The milliseconds of time to represent.
     */
    public AstronomicalTime(final long millis) {
        this(
            () -> millis
        );
    }

    @Override
    public final String asString() {
        return this.value().toString();
    }

    @Override
    public final Long value() {
        return this.scalar.value();
    }

    @Override
    public final byte[] asBytes() {
        //@checkstyle MagicNumberCheck (1 lines)
        return ByteBuffer.allocate(8).putLong(this.value()).array();
    }

}
