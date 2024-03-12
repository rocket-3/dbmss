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

import org.cactoos.Text;
import ru.fusionsoft.database.dbdreadable.DbdReadableOfDirectory;
import ru.fusionsoft.database.migration.scenarios.main.MigrationOfDbdAndServer;
import ru.fusionsoft.database.migration.writable.MigrationSqlOf;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.runnable.RunnableOfScalar;
import ru.fusionsoft.lib.runnable.WriteToDirectoryRunnable;

/**
 * Runnable to create migration sql of server and directory to find DBD at.
 * @since 0.1
 */
public class MigrateDbdAndServerProcedure extends RunnableOfScalar {

    /**
     * Instantiates a new Migrate dbd and server procedure.
     * @param server The {@link Text} to be encapsulated.
     * @param directory The {@link Directory} to be encapsulated.
     */
    public MigrateDbdAndServerProcedure(final Text server, final Directory directory) {
        super(
            () -> {
                return new WriteToDirectoryRunnable(
                    new MigrationSqlOf(
                        new MigrationOfDbdAndServer(
                            new DbdReadableOfDirectory(directory),
                            server
                        )
                    ),
                    directory
                );
            }
        );
    }

}
