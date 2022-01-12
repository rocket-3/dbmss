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

import java.sql.ResultSet;
import org.cactoos.func.UncheckedBiFunc;
import org.cactoos.list.ListOf;

/**
 * The {@link RowOfArray} extension, can be constructed
 *  of {@link ResultSet} and {@link Iterable} of {@link Column}.
 * @since 0.1
 */
public class RowOfResultSet extends RowOfArray {

    /**
     * Instantiates a new Row of result set.
     * @param num The {@link long} to be encapsulated.
     * @param rset The {@link ResultSet} to be encapsulated.
     * @param columns The {@link Iterable} {@link Column} to be encapsulated.
     */
    public RowOfResultSet(final long num, final ResultSet rset, final Iterable<Column> columns) {
        super(
            String.valueOf(num),
            new UncheckedBiFunc<>(
                (ResultSet data, Iterable<Column> cols) -> {
                    final String[] value = new String[new ListOf<>(cols).size()];
                    for (final Column col : cols) {
                        final int index = col.order().intValue();
                        value[index - 1] = col.format().ofResultSet(data, index);
                    }
                    return value;
                }
            ).apply(rset, columns)
        );
    }

}
