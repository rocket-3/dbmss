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
import org.cactoos.iterable.Sticky;
import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.dbdreadable.DbdReadableMerged;
import ru.fusionsoft.database.mapping.dbd.entry.DbdSchemasEntry;
import ru.fusionsoft.database.mapping.dbd.ofobjects.DbdSchemasMappingValueOfObjects;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfServerFromDbd;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsWithInlineLinkDataAdded;
import ru.fusionsoft.lib.path.writable.JoinedWritable;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The DBD repository of existing one, being merged with data, coming from specific server of
 *  current DBD file, with configuration data only.
 * @since 0.1
 * @todo #40:30min Add deleting previous data files Writable in primary ctor.
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class DbdRepoWritableUpdatingFromDbdServer extends JoinedWritable {

    /**
     * Instantiates a new Merged dbd files.
     * @param dbdfile The {@link DbdReadable} to be encapsulated.
     * @param server The {@link Text} to be encapsulated.
     * @param objects The found objects being encapsulated.
     * @param <Y> The type of YamlNode parameter.
     */
    private <Y extends YamlNode> DbdRepoWritableUpdatingFromDbdServer(
        final DbdReadable dbdfile,
        final Text server,
        final Iterable<DbObject<Y>> objects
    ) {
        super(
            new DbdYamlWritable(
                new DbdReadableMerged(
                    dbdfile,
                    new YamlMappingOfEntries(
                        new DbdSchemasEntry(
                            new DbdSchemasMappingValueOfObjects(objects)
                        )
                    )
                )
            ),
            new ConfigurationDataFiles(objects, dbdfile, server)
        );
    }

    /**
     * Instantiates a new Merged dbd files.
     * @param dbdfile The {@link DbdReadable} to be encapsulated.
     * @param server The {@link Text} to be encapsulated.
     */
    public DbdRepoWritableUpdatingFromDbdServer(final DbdReadable dbdfile, final Text server) {
        this(
            dbdfile,
            server,
            new Sticky<>(
                new ObjectsWithInlineLinkDataAdded(
                    new ObjectsOfServerFromDbd(
                        dbdfile,
                        server
                    )
                )
            )
        );
    }

}
