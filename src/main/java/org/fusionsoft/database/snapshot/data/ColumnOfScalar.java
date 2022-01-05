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
package org.fusionsoft.database.snapshot.data;

import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;

/**
 * The {@link Column}, can be constructed of {@link Scalar}/{@link java.util.function.Supplier}.
 * @since 0.1
 */
public class ColumnOfScalar implements Column {

    /**
     * The {@link Unchecked} {@link Scalar} of {@link Column} encapsulated.
     */
    private final Unchecked<? extends Column> scalar;

    /**
     * Instantiates a new Column of scalar.
     * @param scalar The {@link Scalar} {@link Column} to be encapsulated.
     */
    public ColumnOfScalar(final Scalar<? extends Column> scalar) {
        this.scalar = new Unchecked<>(new Sticky<>(scalar));
    }

    @Override
    public final Text name() {
        return this.scalar.value().name();
    }

    @Override
    public final Number order() {
        return this.scalar.value().order();
    }

    @Override
    public final ValueFormat format() {
        return this.scalar.value().format();
    }

}
