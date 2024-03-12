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

package ru.fusionsoft.database.api;

import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.mapping.config.MigrationConfigMapping;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.migration.scenarios.main.MigrationOfDbdDiff;
import ru.fusionsoft.database.migration.writable.MigrationSqlOf;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.runnable.RunnableOfScalar;
import ru.fusionsoft.lib.runnable.WriteToDirectoryRunnable;

/**
 * Runnable to create migration sql between two DBD files.
 * @since 0.1
 */
public class MigrateDbdToDbdProcedure extends RunnableOfScalar {

    /**
     * Instantiates a new Migrate dbd to dbd procedure.
     * @param files The {@link TemporalDiff} of {@link DbdReadable} to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     * @param config The {@link MigrationConfigMapping} to be encapsulated.
     * @param directory The {@link Directory} to be encapsulated.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public MigrateDbdToDbdProcedure(
        final TemporalDiff<DbdReadable> files,
        final Dbms dbms,
        final MigrationConfigMapping config,
        final Directory directory
    ) {
        super(
            () -> {
                return new WriteToDirectoryRunnable(
                    new MigrationSqlOf(
                        new MigrationOfDbdDiff(
                            files,
                            dbms,
                            config
                        )
                    ),
                    directory
                );
            }
        );
    }

}
