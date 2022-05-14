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

import org.cactoos.iterable.IterableOf;
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.mapping.dbd.DbdServersMappingOfEntries;
import ru.fusionsoft.database.mapping.dbd.built.DbdRootMappingBuilt;
import ru.fusionsoft.database.mapping.dbd.built.SimpleDbdInfoMapping;
import ru.fusionsoft.database.snapshot.writable.DbdYamlWritable;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.runnable.WriteToDirectoryRunnable;
import ru.fusionsoft.lib.text.JsonEmptyText;

/**
 * This creates a new empty DBD document in specified directory.
 * @since 0.1
 */
public class DbdInitProcedure extends WriteToDirectoryRunnable {

    /**
     * Instantiates a new Snapshot create procedure.
     * @param directory The Folder to be encapsulated.
     */
    public DbdInitProcedure(final Directory directory) {
        super(
            new DbdYamlWritable(
                new DbdRootMappingBuilt(
                    new DbdServersMappingOfEntries(),
                    new SimpleDbdInfoMapping(
                        new TextOf("A scratch new dbd document, just created"),
                        new JsonEmptyText(),
                        new TextOf("1.0")
                    ),
                    new IterableOf<>()
                )
            ),
            directory
        );
    }

}
