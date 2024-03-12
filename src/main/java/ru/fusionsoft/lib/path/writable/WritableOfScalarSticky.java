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
package ru.fusionsoft.lib.path.writable;

import org.cactoos.Scalar;
import org.cactoos.scalar.Sticky;
import ru.fusionsoft.lib.path.Writable;

/**
 * The {@link WritableOfScalar}, but {@link Scalar} becomes {@link Sticky}.
 * @since 0.1
 */
public class WritableOfScalarSticky extends WritableOfScalar {

    /**
     * Instantiates a new Writable of scalar sticky.
     * @param writable The {@link Scalar} to be encapsulated.
     */
    public WritableOfScalarSticky(final Scalar<Writable> writable) {
        super(new Sticky<>(writable));
    }

}
