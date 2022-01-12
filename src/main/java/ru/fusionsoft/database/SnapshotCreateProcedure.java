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
package ru.fusionsoft.database;

import org.cactoos.Text;
import ru.fusionsoft.database.snapshot.AstronomicalTime;
import ru.fusionsoft.database.snapshot.CreatingSnapshotFolder;
import ru.fusionsoft.database.writable.SnapshotWritable;

/**
 * The procedure to create database snapshot by guidance of {@link DbdFile}.
 * @since 0.1
 * @checkstyle ParameterNumberCheck (100 lines)
 */
public class SnapshotCreateProcedure implements Runnable {

    /**
     * The Writable encapsulated.
     */
    private final Writable writable;

    /**
     * The Folder encapsulated.
     */
    private final Folder folder;

    /**
     * Instantiates a new Snapshot create procedure.
     * @param writable The Writable to be encapsulated.
     * @param folder The Folder to be encapsulated.
     */
    private SnapshotCreateProcedure(final Writable writable, final Folder folder) {
        this.writable = writable;
        this.folder = folder;
    }

    /**
     * Instantiates a new Snapshot create procedure.
     * @param time The AstronomicalTime to be encapsulated.
     * @param dbd The DbdFile to be encapsulated.
     * @param server The Text of server name to be encapsulated.
     * @param alldata The Boolean of to fetch all tables data or not to be encapsulated.
     */
    public SnapshotCreateProcedure(
        final AstronomicalTime time,
        final DbdFile dbd,
        final Text server,
        final Boolean alldata
    ) {
        this(
            new SnapshotWritable(time, dbd, server, alldata),
            new CreatingSnapshotFolder(time)
        );
    }

    /**
     * Instantiates a new Snapshot create procedure.
     * @param file The DbdFile to be encapsulated.
     * @param database The String to be encapsulated.
     * @param alldata The Boolean of to fetch all tables data or not to be encapsulated.
     */
    public SnapshotCreateProcedure(
        final DbdFile file,
        final Text database,
        final Boolean alldata
    ) {
        this(
            new AstronomicalTime(),
            file,
            database,
            alldata
        );
    }

    @Override
    public final void run() {
        this.writable.writeTo(this.folder);
    }

}
