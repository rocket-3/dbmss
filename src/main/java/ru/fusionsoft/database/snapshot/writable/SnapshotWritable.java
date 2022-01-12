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

import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.Ternary;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.DbdFile;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdInfoMappingOfDbdFile;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdServerEntryOfDbdFile;
import ru.fusionsoft.database.snapshot.AstronomicalTime;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfServerFromDbd;
import ru.fusionsoft.database.writable.EmptyWritable;
import ru.fusionsoft.database.writable.JoinedWritable;

/**
 * The procedure to create database snapshot by guidance of {@link DbdFile}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class SnapshotWritable extends JoinedWritable {

    /**
     * Instantiates a new Snapshot create procedure.
     * @param time The AstronomicalTime to be encapsulated.
     * @param dbd The DbdFile to be encapsulated.
     * @param server The Text of server name to be encapsulated.
     * @param alldata The Boolean of to fetch all tables data or not to be encapsulated.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public SnapshotWritable(
        final AstronomicalTime time,
        final DbdFile dbd,
        final Text server,
        final Boolean alldata
    ) {
        super(
            new IterableOf<>(
                new SnapshotInfoDocument(
                    time, alldata
                ),
                new SnapshotDbdDocument(
                    new DbdServerEntryOfDbdFile(dbd, server),
                    new DbdInfoMappingOfDbdFile(dbd),
                    new ObjectsOfServerFromDbd(dbd, server)
                ),
                new Unchecked<>(
                    new Ternary<>(
                        alldata,
                        new OperationalTableDataFiles(dbd, server),
                        new EmptyWritable()
                    )
                ).value()
            )
        );
    }

}
