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
import java.text.MessageFormat;
import org.cactoos.Proc;
import org.cactoos.io.WriterTo;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.mapping.fields.DbdTableFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.lib.collection.IterableAutoCloseable;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.path.Writable;

/**
 * The {@link Writable} of separate table date,
 *  can be constructed of {@link Connection} and {@link DbObject} of {@link DbdTableMapping}.
 * @since 0.1
 */
public class SeparateDataFileOfTableWritable implements Writable {

    /**
     * The {@link DbObject} of {@link DbdTableMapping} encapsulated.
     */
    private final DbObject<DbdTableMapping> table;

    /**
     * The Connection encapsulated.
     */
    private final Connection connection;

    /**
     * Instantiates a new Separate data file of table writable.
     * @param connection The {@link Connection} to be encapsulated.
     * @param table The {@link DbObject} of {@link DbdTableMapping} to be encapsulated.
     */
    public SeparateDataFileOfTableWritable(
        final Connection connection,
        final DbObject<DbdTableMapping> table
    ) {
        this.table = table;
        this.connection = connection;
    }

    @Override
    public final void writeTo(final Directory directory) {
        new Unchecked<>(
            () -> {
                final String spaces = "   ";
                final String sequence = "- ";
                final String linefeed = "\n";
                final WriterTo file = new WriterTo(
                    directory.value().resolve(
                        new SeparateDataFileName(this.table).asString()
                    )
                );
                final Proc<DbdTableFields> entroduce = label -> file.write(
                    MessageFormat.format(
                        "{0}:\n", label.asString()
                    )
                );
                entroduce.exec(DbdTableFields.COLUMNS);
                for (final Column column : new ColumnsOfTable(this.table)) {
                    file.write(spaces + sequence + column.name().asString() + linefeed);
                }
                entroduce.exec(DbdTableFields.DATA);
                try (
                    IterableAutoCloseable<String> lines = new SeparateDataFileLinesOfTable(
                        this.connection,
                        this.table
                    )
                ) {
                    for (final String line : lines) {
                        file.write(spaces + line + linefeed);
                    }
                }
                file.flush();
                return true;
            }
        ).value();
    }

}

