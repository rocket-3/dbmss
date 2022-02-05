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

import ru.fusionsoft.database.dbdfile.CreatingDbdFileOfServer;
import ru.fusionsoft.database.mapping.dbd.DbdServerMapping;
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.objects.StickyObjects;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsOfServer;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsWithInlineLinkDataAdded;
import ru.fusionsoft.lib.time.Utc;
import ru.fusionsoft.lib.time.UtcOfFirstAccess;

/**
 * The {@link DbdDocument} that is created from database,
 *  which {@link DbdServerMapping} points at, with default info mapping and server name.
 * @since 0.1
 */
public class CreatingDbdDocumentOfServer extends DbdDocument {

    /**
     * Instantiates a new Dbd yaml of objects.
     * @param server The {@link DbdServerMapping} to be encapsulated.
     * @param time The {@link Utc} to be encapsulated.
     * @param objects The {@link Objects} to be encapsulated.
     */
    public CreatingDbdDocumentOfServer(
        final DbdServerMapping server,
        final Utc time,
        final Objects<?> objects
    ) {
        super(
            new CreatingDbdFileOfServer(server, time, objects)
        );
    }

    /**
     * Instantiates a new Dbd yaml of objects.
     * @param server The {@link DbdServerMapping} to be encapsulated.
     * @param time The {@link Utc} to be encapsulated.
     */
    public CreatingDbdDocumentOfServer(
        final DbdServerMapping server,
        final Utc time
    ) {
        this(
            server,
            time,
            new StickyObjects<>(
                new ObjectsWithInlineLinkDataAdded(
                    new ObjectsOfServer(server)
                )
            )
        );
    }

    /**
     * Instantiates a new Dbd yaml of objects.
     * @param server The {@link DbdServerMapping} to be encapsulated.
     */
    public CreatingDbdDocumentOfServer(
        final DbdServerMapping server
    ) {
        this(
            server,
            new UtcOfFirstAccess()
        );
    }

}
