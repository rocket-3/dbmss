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

import java.sql.Connection;
import org.cactoos.iterable.Mapped;
import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.writable.JoinedWritable;

/**
 * The {@link JoinedWritable} of {@link SeparateDataFilesOfTablesWritable}, can be constructed from
 *  {@link Connection} and {@link Objects} of {@link DbdTableMapping}.
 * @since 0.1
 */
public class SeparateDataFilesOfTablesWritable extends JoinedWritable {

    /**
     * Instantiates a new Separate data files of tables writable.
     * @param connection The {@link Connection} to be encapsulated.
     * @param tables The {@link Objects} of {@link DbdTableMapping} to be encapsulated.
     */
    public SeparateDataFilesOfTablesWritable(
        final Connection connection,
        final Objects<DbdTableMapping> tables
    ) {
        super(
            new Mapped<>(
                table -> new SeparateDataFileOfTableWritable(connection, table),
                tables
            )
        );
    }

}