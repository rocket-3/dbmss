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
package ru.fusionsoft.database.migration.diff;

import org.cactoos.Text;
import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdServerMappingOfDbdReadable;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfDbdReadable;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsOfServer;

/**
 * The {@link ObjectsDiff} of {@link DbdReadable} and objects taken from its given server.
 * @since 0.1
 */
public class ObjectsDiffOfDbdReadableServer extends ObjectsDiff {

    /**
     * Instantiates a new Objects diff of dbd readable server.
     * @param dbd The {@link DbdReadable} to be encapsulated.
     * @param server The server name to be encapsulated.
     */
    public ObjectsDiffOfDbdReadableServer(final DbdReadable dbd, final Text server) {
        super(
            new ObjectsOfServer(
                new DbdServerMappingOfDbdReadable(dbd, server)
            ),
            new ObjectsOfDbdReadable(dbd)
        );
    }

}
