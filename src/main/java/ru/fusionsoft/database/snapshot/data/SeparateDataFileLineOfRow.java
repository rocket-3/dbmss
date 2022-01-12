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

import java.text.MessageFormat;
import org.cactoos.Text;

/**
 * The {@link Text} of a line in a separate data file,
 *  scan be constructed of {@link Row} and {@link Iterable} of {@link Column}.
 * @since 0.1
 */
public class SeparateDataFileLineOfRow implements Text {

    /**
     * The Row encapsulated.
     */
    private final Row row;

    /**
     * The Iterable of {@link Column} encapsulated.
     */
    private final Iterable<Column> cols;

    /**
     * Instantiates a new Separate data file line of row.
     * @param row The {@link Row} to be encapsulated.
     * @param cols The {@link Iterable} of {@link Column} to be encapsulated.
     */
    public SeparateDataFileLineOfRow(final Row row, final Iterable<Column> cols) {
        this.row = row;
        this.cols = cols;
    }

    @Override
    public final String asString() {
        return MessageFormat.format(
            "{0}: [{1}]",
            this.row.label(),
            String.join(
                ", ",
                new StorableFormattedValuesOfRow(this.row, this.cols)
            )
        );
    }

}
