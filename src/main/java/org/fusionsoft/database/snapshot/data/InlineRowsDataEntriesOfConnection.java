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

import com.amihaiemil.eoyaml.YamlNode;
import java.sql.Connection;
import java.util.Map;
import org.cactoos.Text;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListOf;
import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.snapshot.DbObject;

/**
 * The {@link Iterable} of {@link InlineRowsDataMappingEntryOfRow},
 *  can be constructed of {@link Connection} and {@link DbObject} of {@link DbdTableMapping}.
 * @since 0.1
 */
public class InlineRowsDataEntriesOfConnection extends IterableEnvelope<Map.Entry<Text, YamlNode>> {

    /**
     * Instantiates a new Inline rows data entries of connection.
     * @param connection The {@link Connection} to be encapsulated.
     * @param table The {@link DbObject} of {@link DbdTableMapping} to be encapsulated.
     */
    public InlineRowsDataEntriesOfConnection(
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
     * Instantiates a new Inline rows data entries of connection.
     * @param connection The {@link Connection} to be encapsulated.
     * @param table The {@link DbObject} of {@link DbdTableMapping} to be encapsulated.
     * @param columns The {@link Iterable} of {@link Column} to be encapsulated.
     */
    public InlineRowsDataEntriesOfConnection(
        final Connection connection,
        final DbObject<DbdTableMapping> table,
        final Iterable<Column> columns
    ) {
        super(
            new IterableOf<>(
                () -> new ListOf<>(
                    new Mapped<Map.Entry<Text, YamlNode>>(
                        row -> new InlineRowsDataMappingEntryOfRow(row, columns),
                        new RowsOfTable(
                            connection,
                            table
                        )
                    )
                ).iterator()
            )
        );
    }

}
