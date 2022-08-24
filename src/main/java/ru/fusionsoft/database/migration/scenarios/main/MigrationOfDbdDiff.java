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
import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.mapping.config.MigrationConfigMapping;
import ru.fusionsoft.database.migration.diff.ObjectsDiff;
import ru.fusionsoft.database.migration.diff.ObjectsDiffOfDbdReadables;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.migration.typecasts.mapping.TypecastsMappingOfConnectionAndObjectsDiff;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.dbms.DbmsOfConnection;

/**
 * The sql migration of comparison between two DBDs, it also needs migration config data,
 *  which contains typecasts config and other things.
 * @since 0.1
 */
public class MigrationOfDbdDiff extends MigrationOfObjectsDiff {

    /**
     * Instantiates a new Migration of dbd diff.
     * @param diff The {@link ObjectsDiff} to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     * @param config The {@link MigrationConfigMapping} to be encapsulated.
     */
    private MigrationOfDbdDiff(
        final ObjectsDiff diff,
        final Dbms dbms,
        final MigrationConfigMapping config
    ) {
        super(
            diff,
            dbms,
            config
        );
    }

    /**
     * Instantiates a new Migration of dbd diff.
     * @param diff The {@link ObjectsDiff} to be encapsulated.
     * @param connection The {@link Connection} to be encapsulated.
     */
    private MigrationOfDbdDiff(
        final ObjectsDiff diff,
        final Connection connection
    ) {
        this(
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
     * Instantiates a new Migration of dbd diff.
     * @param files The {@link TemporalDiff} of {@link DbdReadable}.
     * @param dbms The {@link Dbms} to be encapsulated.
     * @param config The {@link MigrationConfigMapping} to be encapsulated.
     */
    public MigrationOfDbdDiff(
        final TemporalDiff<DbdReadable> files,
        final Dbms dbms,
        final MigrationConfigMapping config
    ) {
        this(
            new ObjectsDiffOfDbdReadables(files),
            dbms,
            config
        );
    }

    /**
     * Instantiates a new Migration of dbd diff.
     * @param files The {@link TemporalDiff} of {@link DbdReadable}.
     * @param connection The {@link Connection} to be encapsulated.
     */
    public MigrationOfDbdDiff(
        final TemporalDiff<DbdReadable> files,
        final Connection connection
    ) {
        this(
            new ObjectsDiffOfDbdReadables(files),
            connection
        );
    }

}
