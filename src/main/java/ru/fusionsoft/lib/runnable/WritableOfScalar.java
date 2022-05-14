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
package ru.fusionsoft.lib.runnable;

import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.path.Writable;

/**
 * The {@link Writable} that you can construct of {@link Scalar}.
 * @since 0.1
 */
public class WritableOfScalar implements Writable {

    /**
     * The Scalar encapsulated.
     */
    private final Scalar<Writable> writable;

    /**
     * Instantiates a new Writable of scalar.
     * @param writable The {@link Scalar} to be encapsulated.
     */
    public WritableOfScalar(final Scalar<Writable> writable) {
        this.writable = writable;
    }

    @Override
    public final void writeTo(final Directory directory) {
        new Unchecked<>(this.writable).value().writeTo(directory);
    }

}
