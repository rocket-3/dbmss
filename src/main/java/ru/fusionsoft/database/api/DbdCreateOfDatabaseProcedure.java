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
import ru.fusionsoft.database.mapping.dbd.DbdServerMapping;
import ru.fusionsoft.database.snapshot.writable.DbdRepoWritableCreatingOfDatabaseUrl;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.runnable.WriteToDirectoryRunnable;

/**
 * The procedure to create new DBD files from database,
 *  see {@link DbdRepoWritableCreatingOfDatabaseUrl}.
 * @since 0.1
 * @checkstyle ParameterNumberCheck (100 lines)
 */
public class DbdCreateOfDatabaseProcedure extends WriteToDirectoryRunnable {

    /**
     * Instantiates a new Snapshot create procedure.
     * @param writable The Writable to be encapsulated.
     * @param directory The Folder to be encapsulated.
     */
    public DbdCreateOfDatabaseProcedure(
        final DbdRepoWritableCreatingOfDatabaseUrl writable,
        final Directory directory
    ) {
        super(writable, directory);
    }

    /**
     * Instantiates a new Snapshot create procedure.
     * @param server The {@link DbdServerMapping} to be encapsulated.
     * @param directory The {@link Directory} to be encapsulated.
     */
    public DbdCreateOfDatabaseProcedure(final DbdServerMapping server, final Directory directory) {
        this(
            new DbdRepoWritableCreatingOfDatabaseUrl(
                server
            ),
            directory
        );
    }

    /**
     * Instantiates a new Snapshot create procedure.
     * @param url The JDBC url {@link Text} to be encapsulated.
     * @param username The {@link Text} to be encapsulated.
     * @param password The {@link Text} to be encapsulated.
     * @param directory The {@link Directory} to be encapsulated.
     */
    public DbdCreateOfDatabaseProcedure(
        final Text url,
        final Text username,
        final Text password,
        final Directory directory
    ) {
        this(
            new DbdRepoWritableCreatingOfDatabaseUrl(
                url, username, password
            ),
            directory
        );
    }

}
