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
package ru.fusionsoft.database.diff;

import org.cactoos.Text;
import org.cactoos.iterable.Sticky;
import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdServerMappingOfDbdReadable;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfDbdReadable;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsOfServer;

public class ObjectsDiffOfDbdReadableServer extends ObjectsDiff {

    public ObjectsDiffOfDbdReadableServer(final DbdReadable dbd, final Text server) {
        super(
            new Sticky<>(
                new ObjectsOfDbdReadable(dbd)
            ),
            new Sticky<>(
                new ObjectsOfServer(
                    new DbdServerMappingOfDbdReadable(dbd, server)
                )
            )
        );
    }

}
