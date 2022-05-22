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

import java.util.Comparator;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterable.Sorted;
import org.cactoos.iterable.Sticky;
import ru.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.mapping.dbd.ofobjects.DbdColumnsMappingsOfTable;
import ru.fusionsoft.database.snapshot.DbObject;

/**
 * The {@link Column} {@link Iterable} of {@link DbdColumnMapping} artifacts.
 * @since 0.1
 */
public class ColumnsOfTable extends IterableEnvelope<Column> {

    /**
     * Instantiates a new Columns of table.
     * @param cols The {@link Iterable} of {@link DbdColumnMapping} to be encapsulated.
     */
    public ColumnsOfTable(final Iterable<DbdColumnMapping> cols) {
        super(
            new Sticky<>(
                new Sorted<>(
                    Comparator.comparing(x -> x.order().intValue()),
                    new Mapped<Column>(
                        ColumnOfDbdColumnMapping::new,
                        cols
                    )
                )
            )
        );
    }

    /**
     * Instantiates a new Columns of table.
     * @param table The {@link DbObject} of {@link DbdTableMapping} to be encapsulated.
     */
    public ColumnsOfTable(final DbObject<DbdTableMapping> table) {
        this(
            new DbdColumnsMappingsOfTable(
                table
            )
        );
    }

    /**
     * Instantiates a new Columns of table.
     * @param mapping The {@link DbdTableMapping} to be encapsulated.
     */
    public ColumnsOfTable(final DbdTableMapping mapping) {
        this(
            new DbdColumnsMappingsOfTable(
                mapping
            )
        );
    }

}
