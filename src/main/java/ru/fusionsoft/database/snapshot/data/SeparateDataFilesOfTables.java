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

import com.amihaiemil.eoyaml.YamlNode;
import java.sql.Connection;
import org.cactoos.iterable.Mapped;
import ru.fusionsoft.database.connection.ConnectionOfDbdServerMapping;
import ru.fusionsoft.database.mapping.dbd.DbdServerMapping;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithTypeCasted;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.lib.path.writable.JoinedWritable;

/**
 * The {@link JoinedWritable} of {@link SeparateDataFilesOfTables}, can be constructed from
 *  {@link Connection} and {@link DbObject}s of {@link DbdTableMapping}.
 * @since 0.1
 * @todo #40:30min exclude declarative partition tables from list
 */
public class SeparateDataFilesOfTables extends JoinedWritable {

    /**
     * Instantiates a new Separate data files of tables writable.
     * @param connection The {@link Connection} to be encapsulated.
     * @param tables The {@link Iterable} of {@link DbObject}s of {@link DbdTableMapping}
     *  to be encapsulated.
     */
    public SeparateDataFilesOfTables(
        final Connection connection,
        final Iterable<DbObject<DbdTableMapping>> tables
    ) {
        super(
            new Mapped<>(
                table -> new SeparateDataFileOfTableWritable(connection, table),
                tables
            )
        );
    }

    /**
     * Instantiates a new Separate data files of tables writable.
     * @param server The {@link DbdServerMapping} to be encapsulated.
     * @param objects The {@link Iterable} of {@link DbObject}s to be encapsulated.
     * @param <Y> The type of YamlNode parameter.
     */
    public <Y extends YamlNode> SeparateDataFilesOfTables(
        final DbdServerMapping server,
        final Iterable<DbObject<Y>> objects
    ) {
        this(
            new ConnectionOfDbdServerMapping(server),
            new ObjectsWithTypeCasted<>(
                new ObjectTypeTable(),
                objects
            )
        );
    }

}
