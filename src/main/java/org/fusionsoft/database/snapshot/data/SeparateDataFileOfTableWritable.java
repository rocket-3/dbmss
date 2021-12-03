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

import java.sql.Connection;
import java.text.MessageFormat;
import java.util.Iterator;
import org.cactoos.Proc;
import org.cactoos.io.WriterTo;
import org.cactoos.scalar.Unchecked;
import org.fusionsoft.database.Folder;
import org.fusionsoft.database.Writable;
import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.mapping.fields.DbdTableFields;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.lib.collection.IterableAutoCloseable;

public class SeparateDataFileOfTableWritable implements Writable {

    private final DbObject<DbdTableMapping> table;

    private final Connection connection;

    public SeparateDataFileOfTableWritable(
        final Connection connection,
        final DbObject<DbdTableMapping> table
    ) {
        this.table = table;
        this.connection = connection;
    }

    @Override
    public void writeTo(final Folder folder) {
        new Unchecked<>(
            () -> {
                final String spaces = "   ";
                final String sequence = "- ";
                final String linefeed = "\n";
                final WriterTo file = new WriterTo(
                    folder.path().resolve(
                        new SeparateDataFileName(table).asString()
                    )
                );
                final Proc<DbdTableFields> print = label -> file.write(
                    MessageFormat.format(
                        "{0}:\n", label.asString()
                    )
                );
                print.exec(DbdTableFields.COLUMNS);
                for (final Column column : new ColumnsOfTable(table)) {
                    file.write(spaces + sequence + column.name().asString() + linefeed);
                }
                final Iterator<String> iterator = new SeparateDataFileLinesOfTable(
                    connection, table
                ).iterator();
                print.exec(DbdTableFields.DATA);
                try (
                    IterableAutoCloseable<String> lines = new SeparateDataFileLinesOfTable(
                        connection, table
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

