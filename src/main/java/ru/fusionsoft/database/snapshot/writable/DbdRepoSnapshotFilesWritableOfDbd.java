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
package ru.fusionsoft.database.snapshot.writable;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.iterable.Sticky;
import org.cactoos.scalar.Ternary;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdInfoMappingOfDbdReadable;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdServerEntryOfDbdFile;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfDbdReadableServer;
import ru.fusionsoft.lib.time.Utc;
import ru.fusionsoft.lib.time.UtcOfFirstAccess;

/**
 * The procedure to create database snapshot by guidance of {@link DbdReadable}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class DbdRepoSnapshotFilesWritableOfDbd extends DbdRepoSnapshotFilesWritable {

    /**
     * Instantiates a new Snapshot create procedure.
     * @param dbd The {@link DbdReadable} to be encapsulated.
     * @param server The Text of server name to be encapsulated.
     * @param alldata The Boolean of to fetch all tables data or not to be encapsulated.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public DbdRepoSnapshotFilesWritableOfDbd(
        final DbdReadable dbd,
        final Text server,
        final Boolean alldata
    ) {
        this(
            new UtcOfFirstAccess(),
            dbd,
            server,
            alldata
        );
    }

    /**
     * Instantiates a new Snapshot create procedure.
     * @param time The {@link Utc} to be encapsulated.
     * @param dbd The {@link DbdReadable} to be encapsulated.
     * @param server The Text of server name to be encapsulated.
     * @param alldata The Boolean of to fetch all tables data or not to be encapsulated.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public DbdRepoSnapshotFilesWritableOfDbd(
        final Utc time,
        final DbdReadable dbd,
        final Text server,
        final Boolean alldata
    ) {
        this(
            time,
            dbd,
            server,
            new Sticky<>(new ObjectsOfDbdReadableServer(dbd, server)),
            alldata
        );
    }

    /**
     * Instantiates a new Snapshot create procedure.
     * @param time The {@link Utc} of creation to be encapsulated.
     * @param dbd The {@link DbdReadable} to be encapsulated.
     * @param server The Text of server name to be encapsulated.
     * @param objects The objects to pus inside to be encapsulated.
     * @param alldata The Boolean of to fetch all tables data or not to be encapsulated.
     * @param <Y> The type of YamlNode parameter.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    private <Y extends YamlNode> DbdRepoSnapshotFilesWritableOfDbd(
        final Utc time,
        final DbdReadable dbd,
        final Text server,
        final Iterable<DbObject<Y>> objects,
        final Boolean alldata
    ) {
        super(
            new SnapshotInfoDocument(
                time, alldata
            ),
            new DbdYamlWritableTakingServerSnaphot(
                new DbdServerEntryOfDbdFile(dbd, server),
                new DbdInfoMappingOfDbdReadable(dbd),
                objects
            ),
            new Unchecked<>(
                new Ternary<>(
                    alldata,
                    new OperationalTableDataFilesWritable(objects, dbd, server),
                    new ConfigurationDataFiles(objects, dbd, server)
                )
            ).value()
        );
    }

}
