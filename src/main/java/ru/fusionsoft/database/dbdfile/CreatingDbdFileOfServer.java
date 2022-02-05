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
package ru.fusionsoft.database.dbdfile;

import org.cactoos.text.TextOf;
import ru.fusionsoft.database.mapping.dbd.DbdServerEntry;
import ru.fusionsoft.database.mapping.dbd.DbdServerMapping;
import ru.fusionsoft.database.mapping.dbd.built.SimpleDbdInfoMapping;
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.SnapshotCatalogName;
import ru.fusionsoft.database.snapshot.SnapshotDefaultDescription;
import ru.fusionsoft.database.snapshot.objects.StickyObjects;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsOfServer;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsWithInlineLinkDataAdded;
import ru.fusionsoft.lib.time.Utc;
import ru.fusionsoft.lib.time.UtcOfFirstAccess;

/**
 * The {@link ru.fusionsoft.database.DbdFile} that is created from database,
 *  which {@link DbdServerMapping} points at, with default info mapping and server name.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class CreatingDbdFileOfServer extends DbdFileOfServerSnapshot {

    /**
     * Instantiates a new Dbd file of snapshot objects.
     * @param server The DbdServerEntry to be encapsulated.
     * @param time The {@link Utc} to be encapsulated.
     * @param objects The Objects to be encapsulated.
     */
    public CreatingDbdFileOfServer(
        final DbdServerMapping server, final Utc time, final Objects<?> objects
    ) {
        super(
            new DbdServerEntry(
                new TextOf("origin"),
                server
            ),
            new SimpleDbdInfoMapping(
                new SnapshotCatalogName(time),
                new SnapshotDefaultDescription(server),
                new TextOf("1.0")
            ),
            objects
        );
    }

    /**
     * Instantiates a new Dbd file of server snapshot, and objects are taken from database.
     * @param server The {@link DbdServerMapping} to be encapsulated.
     * @param time The {@link Utc} to be encapsulated.
     */
    public CreatingDbdFileOfServer(
        final DbdServerMapping server,
        final Utc time
    ) {
        this(
            server,
            time,
            new StickyObjects<>(
                new ObjectsWithInlineLinkDataAdded(
                    new ObjectsOfServer(
                        server
                    )
                )
            )
        );
    }

    /**
     * Instantiates a new Dbd file of server snapshot, and objects are taken from database.
     * @param server The {@link DbdServerMapping} to be encapsulated.
     */
    public CreatingDbdFileOfServer(
        final DbdServerMapping server
    ) {
        this(
            server,
            new UtcOfFirstAccess()
        );
    }

}