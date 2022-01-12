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

import java.sql.Connection;
import org.cactoos.iterable.Mapped;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.lib.collection.IterableAutoCloseable;

/**
 * The {@link IterableAutoCloseable} of {@link SeparateDataFileLineOfRow},
 *  can be constructed of {@link Connection} and parent {@link DbObject} of {@link DbdTableMapping}.
 * @since 0.1
 */
public class SeparateDataFileLinesOfTable extends IterableAutoCloseable<String> {

    /**
     * Instantiates a new Separate data file lines of table.
     * @param connection The {@link Connection} to be encapsulated.
     * @param table The parent {@link DbObject} of {@link DbdTableMapping} to be encapsulated.
     */
    public SeparateDataFileLinesOfTable(
        final Connection connection,
        final DbObject<DbdTableMapping> table
    ) {
        this(
            connection,
            table,
            new ColumnsOfTable(table)
        );
    }

    /**
     * Instantiates a new Separate data file lines of table.
     * @param connection The {@link Connection} to be encapsulated.
     * @param table The parent {@link DbObject} of {@link DbdTableMapping} to be encapsulated.
     * @param columns The {@link Iterable} of {@link Column} to be encapsulated.
     */
    public SeparateDataFileLinesOfTable(
        final Connection connection,
        final DbObject<DbdTableMapping> table,
        final Iterable<Column> columns
    ) {
        this(
            columns,
            new RowsOfTable(
                connection,
                table
            )
        );
    }

    /**
     * Instantiates a new Separate data file lines of table.
     * @param columns The {@link Iterable} of {@link Column} to be encapsulated.
     * @param rows The {@link RowsOfTable} to be encapsulated.
     */
    private SeparateDataFileLinesOfTable(
        final Iterable<Column> columns,
        final RowsOfTable rows
    ) {
        super(
            new Mapped<>(
                row -> new SeparateDataFileLineOfRow(row, columns).asString(),
                rows
            ),
            rows
        );
    }

}
