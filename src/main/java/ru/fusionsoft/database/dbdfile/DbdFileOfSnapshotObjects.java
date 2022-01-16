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

import ru.fusionsoft.database.DbdFile;
import ru.fusionsoft.database.mapping.dbd.DbdInfoMapping;
import ru.fusionsoft.database.mapping.dbd.DbdServerEntry;
import ru.fusionsoft.database.mapping.dbd.ofobjects.DbdRootMappingOfSnapshotObjects;
import ru.fusionsoft.database.snapshot.Objects;

/**
 * The {@link DbdFile} can be constructed of
 *  'server', 'info' and objects descriptions.
 * @since 0.1
 */
public class DbdFileOfSnapshotObjects extends DbdFileYamlOfRootMapping {

    /**
     * Instantiates a new Dbd file of snapshot objects.
     * @param server The DbdServerEntry to be encapsulated.
     * @param info The DbdInfoMapping to be encapsulated.
     * @param objects The Objects to be encapsulated.
     */
    public DbdFileOfSnapshotObjects(
        final DbdServerEntry server,
        final DbdInfoMapping info,
        final Objects<?> objects
    ) {
        super(new DbdRootMappingOfSnapshotObjects(server, info, objects));
    }

}