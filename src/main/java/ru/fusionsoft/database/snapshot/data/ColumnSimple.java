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
package ru.fusionsoft.database.snapshot.data;

import org.cactoos.Text;

/**
 * The naive implementation of {@link Column}.
 * @since 0.1
 */
public class ColumnSimple implements Column {

    /**
     * The Text encapsulated.
     */
    private final Text text;

    /**
     * The Number encapsulated.
     */
    private final Number number;

    /**
     * The ValueFormat encapsulated.
     */
    private final ValueFormat tformat;

    /**
     * Instantiates a new Column simple.
     * @param name The {@link Text} to be encapsulated.
     * @param order The {@link Number} to be encapsulated.
     * @param format The {@link ValueFormat} to be encapsulated.
     */
    public ColumnSimple(final Text name, final Number order, final ValueFormat format) {
        this.text = name;
        this.number = order;
        this.tformat = format;
    }

    @Override
    public final Text name() {
        return this.text;
    }

    @Override
    public final Number order() {
        return this.number;
    }

    @Override
    public final ValueFormat format() {
        return this.tformat;
    }

}
