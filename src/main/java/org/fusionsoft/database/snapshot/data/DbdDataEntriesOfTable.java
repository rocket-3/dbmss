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

import com.amihaiemil.eoyaml.YamlNode;
import java.sql.Connection;
import java.util.Map;
import org.cactoos.Text;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterable.Sticky;
import org.cactoos.list.ListOf;
import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.snapshot.DbObject;

public class DbdDataEntriesOfTable extends IterableEnvelope<Map.Entry<Text, YamlNode>> {

    public DbdDataEntriesOfTable(
        final Connection connection,
        final DbObject<DbdTableMapping> table
    ) {
        this(
            connection,
            table,
            new Sticky<>(
                new ColumnsOfTable(table)
            )
        );
    }

    public DbdDataEntriesOfTable(
        final Connection connection,
        final DbObject<DbdTableMapping> table,
        final Iterable<Column> columns
    ) {
        super(
            new IterableOf<>(
                () -> new ListOf<>(
                    new Mapped<Map.Entry<Text, YamlNode>>(
                        x -> new DbdDataEntryOfRow(x, columns),
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
