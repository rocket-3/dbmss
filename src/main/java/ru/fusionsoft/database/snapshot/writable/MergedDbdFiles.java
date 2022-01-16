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
import ru.fusionsoft.database.mapping.MappingOfRepresentative;
import ru.fusionsoft.database.mapping.dbd.DbdRootMapping;
import ru.fusionsoft.database.mapping.dbd.built.DbdRootMappingBuilt;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdInfoMappingOfDbdFile;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdServersMappingOfDbdFile;
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfServerFromDbd;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsWithInlineLinkDataAdded;
import ru.fusionsoft.database.writable.JoinedWritable;
import ru.fusionsoft.lib.yaml.MappingMerged;

/**
 * The DBD repository of existing one, being merged with data, coming from specific server of
 *  current DBD file, with configuration data only.
 * @since 0.1
 */
public class MergedDbdFiles extends JoinedWritable {

    /**
     * Instantiates a new Merged dbd files.
     * @param dbdfile The {@link DbdFile} to be encapsulated.
     * @param server The {@link Text} to be encapsulated.
     * @param objects The {@link Objects} to be encapsulated.
     */
    private MergedDbdFiles(final DbdFile dbdfile, final Text server, final Objects<?> objects) {
        super(
            new DbdDocument(
                new DbdRootMapping(
                    new MappingMerged(
                        new MappingOfRepresentative(dbdfile),
                        new DbdRootMappingBuilt(
                            new DbdServersMappingOfDbdFile(dbdfile),
                            new DbdInfoMappingOfDbdFile(dbdfile),
                            objects
                        )
                    )
                )
            ),
            new ConfigurationDataFiles(objects, dbdfile, server)
        );
    }

    /**
     * Instantiates a new Merged dbd files.
     * @param dbdfile The {@link DbdFile} to be encapsulated.
     * @param server The {@link Text} to be encapsulated.
     */
    public MergedDbdFiles(final DbdFile dbdfile, final Text server) {
        this(
            dbdfile,
            server,
            new ObjectsWithInlineLinkDataAdded(
                new ObjectsOfServerFromDbd(
                    dbdfile,
                    server
                )
            )
        );
    }

}
