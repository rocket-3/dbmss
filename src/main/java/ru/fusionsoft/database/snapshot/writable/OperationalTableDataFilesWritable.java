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

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdServerMappingOfDbdReadable;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.data.SeparateDataFilesOfTables;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsAreTablesInDbdFile;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfServerFromDbd;

/**
 * The type of {@link SeparateDataFilesOfTables}
 *  of all given {@link DbdReadable}'s tables.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class OperationalTableDataFilesWritable extends SeparateDataFilesOfTables {

    /**
     * Instantiates a new Operational data files.
     * @param objects The {@link Iterable} of {@link DbObject}s to be encapsulated.
     * @param dbdfile The {@link DbdReadable} to be encapsulated.
     * @param server The {@link Text} of server to be encapsulated.
     * @param <Y> The type of YamlNode parameter.
     */
    public <Y extends YamlNode> OperationalTableDataFilesWritable(
        final Iterable<DbObject<Y>> objects,
        final DbdReadable dbdfile,
        final Text server
    ) {
        super(
            new DbdServerMappingOfDbdReadable(
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
     * @param dbdfile The {@link DbdReadable} to be encapsulated.
     * @param server The {@link Text} of server to be encapsulated.
     */
    public OperationalTableDataFilesWritable(
        final DbdReadable dbdfile,
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
