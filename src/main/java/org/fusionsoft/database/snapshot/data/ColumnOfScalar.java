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

package org.fusionsoft.database.snapshot.data;

import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.Unchecked;

public class ColumnOfScalar implements Column {

    private final Unchecked<? extends Column> scalar;

    public ColumnOfScalar(final Scalar<? extends Column> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public Text name() {
        return this.scalar.value().name();
    }

    @Override
    public Number order() {
        return this.scalar.value().order();
    }

    @Override
    public ValueFormat format() {
        return this.scalar.value().format();
    }

}
