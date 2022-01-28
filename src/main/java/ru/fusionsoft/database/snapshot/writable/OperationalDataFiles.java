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
import ru.fusionsoft.database.DbdFile;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdServerMappingOfDbdFile;
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.data.SeparateDataFilesOfTables;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsAreTablesInDbdFile;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfServerFromDbd;

/**
 * The type of {@link SeparateDataFilesOfTables}
 *  of all given {@link DbdFile}'s tables.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class OperationalDataFiles extends SeparateDataFilesOfTables {

    /**
     * Instantiates a new Operational data files.
     * @param objects The {@link Objects} to be encapsulated.
     * @param dbdfile The {@link DbdFile} to be encapsulated.
     * @param server The {@link Text} of server to be encapsulated.
     */
    public OperationalDataFiles(
        final Objects<?> objects,
        final DbdFile dbdfile,
        final Text server
    ) {
        super(
            new DbdServerMappingOfDbdFile(
                dbdfile,
                server
            ),
            new ObjectsAreTablesInDbdFile(
                objects,
                dbdfile
            )
        );
    }

    /**
     * Instantiates a new Operational data files.
     * @param dbdfile The {@link DbdFile} to be encapsulated.
     * @param server The {@link Text} of server to be encapsulated.
     */
    public OperationalDataFiles(
        final DbdFile dbdfile,
        final Text server
    ) {
        this(
            new ObjectsOfServerFromDbd(
                dbdfile,
                server
            ),
            dbdfile,
            server
        );
    }

}
