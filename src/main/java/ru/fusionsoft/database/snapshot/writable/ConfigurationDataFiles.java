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
import org.cactoos.iterable.Joined;
import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdServerMappingOfDbdReadable;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.data.SeparateDataFilesOfTables;
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
     * @param objects The {@link Iterable} of {@link DbObject}s to be encapsulated.
     * @param dbdfile The {@link DbdReadable} to be encapsulated.
     * @param server The {@link Text} to be encapsulated.
     * @param <Y> The type of YamlNode parameter.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    private <Y extends YamlNode> ConfigurationDataFiles(
        final Iterable<? extends DbObject<DbdTableMapping>> datatables,
        final Iterable<? extends DbObject<Y>> objects,
        final DbdReadable dbdfile,
        final Text server
    ) {
        super(
            new DbdServerMappingOfDbdReadable(dbdfile, server),
            new Joined<DbObject<DbdTableMapping>>(
                datatables,
                new ObjectsArePartitionsOf<>(objects, datatables)
            )
        );
    }

    /**
     * Instantiates a new Configuration data files.
     * @param objects The {@link Iterable} of {@link DbObject}s to be filtered.
     * @param dbdfile The {@link DbdReadable} to filter by.
     * @param server The {@link Text} to be encapsulated.
     * @param <Y> The type of YamlNode parameter.
     */
    public <Y extends YamlNode> ConfigurationDataFiles(
        final Iterable<DbObject<Y>> objects,
        final DbdReadable dbdfile,
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
     * @param dbdfile The {@link DbdReadable} to be encapsulated.
     * @param server The {@link Text} to be encapsulated.
     */
    public ConfigurationDataFiles(
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
