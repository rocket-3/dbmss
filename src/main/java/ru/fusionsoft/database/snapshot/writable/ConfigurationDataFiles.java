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
import ru.fusionsoft.database.snapshot.objects.DefaultObjectsJoined;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsArePartitionsOf;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithTableDataInDbdFile;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfServerFromDbd;

/**
 * The {@link SeparateDataFilesOfTables} for given objects, which are mentioned in specific
 *  DBD document as tables with 'data' node specified.
 * @since 0.1
 */
public class ConfigurationDataFiles extends SeparateDataFilesOfTables {

    /**
     * Instantiates a new Configuration data files.
     * @param datatables The {@link ObjectsWithTableDataInDbdFile} to be encapsulated.
     * @param objects The {@link Objects} to be encapsulated.
     * @param dbdfile The {@link DbdFile} to be encapsulated.
     * @param server The {@link Text} to be encapsulated.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    private ConfigurationDataFiles(
        final ObjectsWithTableDataInDbdFile datatables,
        final Objects<?> objects,
        final DbdFile dbdfile,
        final Text server
    ) {
        super(
            new DbdServerMappingOfDbdFile(dbdfile, server),
            new DefaultObjectsJoined(
                datatables,
                new ObjectsArePartitionsOf<>(objects, datatables)
            )
        );
    }

    /**
     * Instantiates a new Configuration data files.
     * @param objects The {@link Objects} to be filtered.
     * @param dbdfile The {@link DbdFile} to filter by.
     * @param server The {@link Text} to be encapsulated.
     */
    public ConfigurationDataFiles(
        final Objects<?> objects,
        final DbdFile dbdfile,
        final Text server
    ) {
        this(
            new ObjectsWithTableDataInDbdFile(objects, dbdfile),
            objects,
            dbdfile,
            server
        );
    }

    /**
     * Instantiates a new Configuration data files.
     * @param dbdfile The {@link DbdFile} to be encapsulated.
     * @param server The {@link Text} to be encapsulated.
     */
    public ConfigurationDataFiles(
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
