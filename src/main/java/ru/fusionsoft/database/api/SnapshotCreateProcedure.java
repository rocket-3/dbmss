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
import ru.fusionsoft.database.DbdFile;
import ru.fusionsoft.database.Folder;
import ru.fusionsoft.database.WriteToFolderProcedure;
import ru.fusionsoft.database.dbdfile.DbdFileOfFolder;
import ru.fusionsoft.database.snapshot.CreatingSnapshotFolder;
import ru.fusionsoft.database.snapshot.writable.SnapshotOfDbdFiles;
import ru.fusionsoft.lib.time.Utc;
import ru.fusionsoft.lib.time.UtcOfFirstAccess;

/**
 * The procedure to create database snapshot by guidance of {@link DbdFile}.
 * @since 0.1
 * @checkstyle ParameterNumberCheck (100 lines)
 */
public class SnapshotCreateProcedure extends WriteToFolderProcedure {

    /**
     * Instantiates a new Snapshot create procedure.
     * @param writable The Writable to be encapsulated.
     * @param folder The Folder to be encapsulated.
     */
    public SnapshotCreateProcedure(final SnapshotOfDbdFiles writable, final Folder folder) {
        super(writable, folder);
    }

    /**
     * Instantiates a new Snapshot create procedure.
     * @param time The {@link Utc} to be encapsulated.
     * @param dbd The {@link DbdFile} to be encapsulated.
     * @param server The {@link Text} to be encapsulated.
     * @param alldata Should it take operational data tables.
     */
    private SnapshotCreateProcedure(
        final Utc time,
        final DbdFile dbd,
        final Text server,
        final Boolean alldata
    ) {
        this(
            new SnapshotOfDbdFiles(time, dbd, server, alldata),
            new CreatingSnapshotFolder(time)
        );
    }

    /**
     * Instantiates a new Snapshot create procedure.
     * @param server The {@link Text} to be encapsulated.
     * @param folder The {@link Folder} to be encapsulated.
     * @param alldata Should it take operational data tables.
     */
    public SnapshotCreateProcedure(
        final Text server,
        final Folder folder,
        final Boolean alldata
    ) {
        this(
            new UtcOfFirstAccess(),
            new DbdFileOfFolder(folder),
            server,
            alldata
        );
    }

}
