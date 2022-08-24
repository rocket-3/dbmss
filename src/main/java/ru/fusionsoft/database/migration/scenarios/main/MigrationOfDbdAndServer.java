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
package ru.fusionsoft.database.migration.scenarios.main;

import java.sql.Connection;
import org.cactoos.Text;
import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.connection.ConnectionOfDbdServerMapping;
import ru.fusionsoft.database.mapping.config.MigrationConfigMapping;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdServerMappingOfDbdReadable;
import ru.fusionsoft.database.migration.diff.ObjectsDiff;
import ru.fusionsoft.database.migration.diff.ObjectsDiffOfDbdReadableServer;
import ru.fusionsoft.database.migration.typecasts.mapping.TypecastsMappingOfConnectionAndObjectsDiff;
import ru.fusionsoft.database.snapshot.dbms.DbmsOfConnection;

/**
 * Sql migration that is constructed of objects that is fetched from
 *  {@link DbdReadable} and its server.
 * @since 0.1
 */
public class MigrationOfDbdAndServer extends MigrationOfObjectsDiff {

    /**
     * Instantiates a new Migration of dbd and server.
     * @param diff The {@link ObjectsDiff} to be encapsulated.
     * @param connection The {@link Connection} to be encapsulated.
     */
    private MigrationOfDbdAndServer(
        final ObjectsDiff diff,
        final Connection connection
    ) {
        super(
            diff,
            new DbmsOfConnection(connection),
            new MigrationConfigMapping(
                new TypecastsMappingOfConnectionAndObjectsDiff(
                    connection,
                    diff
                )
            )
        );
    }

    /**
     * Instantiates a new Migration of dbd and server.
     * @param dbd The {@link DbdReadable} to be encapsulated.
     * @param server The {@link Text} to be encapsulated.
     */
    public MigrationOfDbdAndServer(
        final DbdReadable dbd,
        final Text server
    ) {
        this(
            new ObjectsDiffOfDbdReadableServer(
                dbd,
                server
            ),
            new ConnectionOfDbdServerMapping(
                new DbdServerMappingOfDbdReadable(
                    dbd,
                    server
                )
            )
        );
    }

}
