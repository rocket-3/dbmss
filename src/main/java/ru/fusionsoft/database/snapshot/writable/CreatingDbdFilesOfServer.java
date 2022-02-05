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
import ru.fusionsoft.database.mapping.dbd.DbdServerMapping;
import ru.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.data.SeparateDataFilesOfTables;
import ru.fusionsoft.database.snapshot.objects.StickyObjects;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsOfServer;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsWithInlineLinkDataAdded;
import ru.fusionsoft.lib.path.writable.JoinedWritable;
import ru.fusionsoft.lib.time.Utc;
import ru.fusionsoft.lib.time.UtcOfFirstAccess;

/**
 * The {@link SnapshotFiles} of whole new DBD of some database's url and credentials,
 *  always with all data, being marked as configuration data.
 * @since 0.1
 * @todo #40:30min Design filtered version of this.
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class CreatingDbdFilesOfServer extends JoinedWritable {

    /**
     * Instantiates a new Created from server dbd file writable.
     * @param time The {@link Utc} to be encapsulated.
     * @param server The {@link DbdServerMapping} to be encapsulated.
     * @param objects The {@link Objects} to be encapsulated.
     */
    private CreatingDbdFilesOfServer(
        final Utc time,
        final DbdServerMapping server,
        final Objects<?> objects
    ) {
        super(
            new CreatingDbdDocumentOfServer(
                server,
                time,
                objects
            ),
            new SeparateDataFilesOfTables(
                server,
                objects
            )
        );
    }

    /**
     * Instantiates a new Created from server dbd file writable.
     * @param time The {@link Utc} to be encapsulated.
     * @param server The {@link DbdServerMapping} to be encapsulated.
     */
    public CreatingDbdFilesOfServer(
        final Utc time,
        final DbdServerMapping server
    ) {
        this(
            time,
            server,
            new StickyObjects<>(
                new ObjectsWithInlineLinkDataAdded(
                    new ObjectsOfServer(server)
                )
            )
        );
    }

    /**
     * Instantiates a new Created from server dbd file writable.
     * @param server The {@link DbdServerMapping} to be encapsulated.
     */
    public CreatingDbdFilesOfServer(final DbdServerMapping server) {
        this(
            new UtcOfFirstAccess(),
            server
        );
    }

    /**
     * Instantiates a new Created from server dbd file writable.
     * @param time The {@link Utc} to be encapsulated.
     * @param url The {@link Text} to be encapsulated.
     * @param username The {@link Text} to be encapsulated.
     * @param password The {@link Text} to be encapsulated.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public CreatingDbdFilesOfServer(
        final Utc time,
        final Text url,
        final Text username,
        final Text password
    ) {
        this(
            time,
            new DbdServerMappingWithCredentials(
                url,
                username,
                password
            )
        );
    }

    /**
     * Instantiates a new Created from server dbd file writable.
     * @param url The {@link Text} to be encapsulated.
     * @param username The {@link Text} to be encapsulated.
     * @param password The {@link Text} to be encapsulated.
     */
    public CreatingDbdFilesOfServer(
        final Text url,
        final Text username,
        final Text password
    ) {
        this(
            new UtcOfFirstAccess(),
            new DbdServerMappingWithCredentials(
                url,
                username,
                password
            )
        );
    }

}
