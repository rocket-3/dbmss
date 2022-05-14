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
import ru.fusionsoft.database.snapshot.writable.DbdRepoWritableUpdatingFromDbdServer;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.runnable.WriteToDirectoryRunnable;

/**
 * The procedure to update DBD document and files from server.
 * @since 0.1
 * @todo #40:30min Clean existing data files before merge
 */
public class DbdMergeFromServerProcedure extends WriteToDirectoryRunnable {

    /**
     * Instantiates a new Dbd merge procedure.
     * @param writable The {@link DbdRepoWritableUpdatingFromDbdServer} to be encapsulated.
     * @param directory The {@link Directory} to be encapsulated.
     */
    private DbdMergeFromServerProcedure(
        final DbdRepoWritableUpdatingFromDbdServer writable,
        final Directory directory
    ) {
        super(writable, directory);
    }

    /**
     * Instantiates a new Dbd merge procedure.
     * @param server The {@link Text} of server name from 'servers' node to be encapsulated.
     * @param directory The {@link Directory} to be encapsulated.
     */
    public DbdMergeFromServerProcedure(final Text server, final Directory directory) {
        this(
            new DbdRepoWritableUpdatingFromDbdServer(
                new DbdReadableOfDirectory(directory),
                server
            ),
            directory
        );
    }

}
