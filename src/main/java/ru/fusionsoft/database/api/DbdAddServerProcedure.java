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
package ru.fusionsoft.database.api;

import ru.fusionsoft.database.dbdreadable.DbdReadableMerged;
import ru.fusionsoft.database.dbdreadable.DbdReadableOfDirectory;
import ru.fusionsoft.database.mapping.dbd.DbdServerEntry;
import ru.fusionsoft.database.mapping.dbd.DbdServersMappingOfEntries;
import ru.fusionsoft.database.mapping.dbd.entry.DbdServersEntry;
import ru.fusionsoft.database.snapshot.writable.DbdYamlWritable;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.runnable.WriteToDirectoryRunnable;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The procedure to add server to DBD/servers mapping.
 * @since 0.1
 */
public class DbdAddServerProcedure extends WriteToDirectoryRunnable {

    /**
     * Instantiates a new Dbd add server procedure.
     * @param server The {@link DbdServerEntry} to be encapsulated.
     * @param directory The {@link Directory} to be encapsulated.
     */
    public DbdAddServerProcedure(final DbdServerEntry server, final Directory directory) {
        super(
            new DbdYamlWritable(
                new DbdReadableMerged(
                    new DbdReadableOfDirectory(directory),
                    new YamlMappingOfEntries(
                        new DbdServersEntry(
                            new DbdServersMappingOfEntries(
                                server
                            )
                        )
                    )
                )
            ),
            directory
        );
    }

}
