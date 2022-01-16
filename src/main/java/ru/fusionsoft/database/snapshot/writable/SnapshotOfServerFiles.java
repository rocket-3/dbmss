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
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.mapping.dbd.DbdServerEntry;
import ru.fusionsoft.database.mapping.dbd.DbdServerMapping;
import ru.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;
import ru.fusionsoft.database.mapping.dbd.built.SimpleDbdInfoMapping;
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.SnapshotCatalogName;
import ru.fusionsoft.database.snapshot.SnapshotDefaultDescription;
import ru.fusionsoft.database.snapshot.data.SeparateDataFilesOfTables;
import ru.fusionsoft.database.snapshot.objects.StickyObjects;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsFromServer;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsWithInlineLinkDataAdded;
import ru.fusionsoft.lib.time.UTC;
import ru.fusionsoft.lib.time.UTCOfFirstAccess;

/**
 * The {@link SnapshotFiles} of whole new DBD of some database's url and credentials,
 *  always with all data, being marked as configuration data.
 * @since 0.1
 * @todo #40:30min Design filtered version of this.
 */
public class SnapshotOfServerFiles extends SnapshotFiles {

    /**
     * Instantiates a new Created from server dbd file writable.
     * @param time The {@link UTC} to be encapsulated.
     * @param server The {@link DbdServerMapping} to be encapsulated.
     * @param objects The {@link Objects} to be encapsulated.
     */
    private SnapshotOfServerFiles(
        final UTC time,
        final DbdServerMapping server,
        final Objects<?> objects
    ) {
        super(
            new SnapshotInfoDocument(
                time,
                Boolean.TRUE
            ),
            new SnapshotDbdDocument(
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
            ),
            new SeparateDataFilesOfTables(
                server,
                objects
            )
        );
    }

    /**
     * Instantiates a new Created from server dbd file writable.
     * @param time The {@link UTC} to be encapsulated.
     * @param server The {@link DbdServerMapping} to be encapsulated.
     */
    public SnapshotOfServerFiles(
        final UTC time,
        final DbdServerMapping server
    ) {
        this(
            time,
            server,
            new StickyObjects<>(
                new ObjectsWithInlineLinkDataAdded(
                    new ObjectsFromServer(server)
                )
            )
        );
    }

    /**
     * Instantiates a new Created from server dbd file writable.
     * @param server The {@link DbdServerMapping} to be encapsulated.
     */
    public SnapshotOfServerFiles(final DbdServerMapping server) {
        this(
            new UTCOfFirstAccess(),
            server
        );
    }

    /**
     * Instantiates a new Created from server dbd file writable.
     * @param time The {@link UTC} to be encapsulated.
     * @param url The {@link Text} to be encapsulated.
     * @param username The {@link Text} to be encapsulated.
     * @param password The {@link Text} to be encapsulated.
     */
    public SnapshotOfServerFiles(
        final UTC time,
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
    public SnapshotOfServerFiles(
        final Text url,
        final Text username,
        final Text password
    ) {
        this(
            new UTCOfFirstAccess(),
            new DbdServerMappingWithCredentials(
                url,
                username,
                password
            )
        );
    }

}
