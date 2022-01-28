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
import ru.fusionsoft.database.Folder;
import ru.fusionsoft.database.WriteToFolderProcedure;
import ru.fusionsoft.database.mapping.dbd.DbdServerMapping;
import ru.fusionsoft.database.snapshot.writable.SnapshotFilesOfServerDefault;

/**
 * The procedure to create new DBD files from database, see {@link SnapshotFilesOfServerDefault}.
 * @since 0.1
 * @checkstyle ParameterNumberCheck (100 lines)
 */
public class DbdCreateProcedure extends WriteToFolderProcedure {

    /**
     * Instantiates a new Snapshot create procedure.
     * @param writable The Writable to be encapsulated.
     * @param folder The Folder to be encapsulated.
     */
    public DbdCreateProcedure(final SnapshotFilesOfServerDefault writable, final Folder folder) {
        super(writable, folder);
    }

    /**
     * Instantiates a new Snapshot create procedure.
     * @param server The {@link DbdServerMapping} to be encapsulated.
     * @param folder The {@link Folder} to be encapsulated.
     */
    public DbdCreateProcedure(final DbdServerMapping server, final Folder folder) {
        this(
            new SnapshotFilesOfServerDefault(
                server
            ),
            folder
        );
    }

    /**
     * Instantiates a new Snapshot create procedure.
     * @param url The JDBC url {@link Text} to be encapsulated.
     * @param username The {@link Text} to be encapsulated.
     * @param password The {@link Text} to be encapsulated.
     * @param folder The {@link Folder} to be encapsulated.
     */
    public DbdCreateProcedure(
        final Text url,
        final Text username,
        final Text password,
        final Folder folder
    ) {
        this(
            new SnapshotFilesOfServerDefault(
                url, username, password
            ),
            folder
        );
    }

}
