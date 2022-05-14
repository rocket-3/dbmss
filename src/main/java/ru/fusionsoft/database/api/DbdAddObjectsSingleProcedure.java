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

import org.cactoos.Text;
import ru.fusionsoft.database.dbdreadable.DbdReadableMerged;
import ru.fusionsoft.database.dbdreadable.DbdReadableOfDirectory;
import ru.fusionsoft.database.mapping.dbd.built.DbdSchemasRootMappingNode;
import ru.fusionsoft.database.mapping.dbd.ofobjects.DbdSchemasMappingValueOfObjects;
import ru.fusionsoft.database.snapshot.objects.dependencies.ObjectsOfDbdAddSingle;
import ru.fusionsoft.database.snapshot.writable.DbdYamlWritable;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.runnable.WriteToDirectoryRunnable;

/**
 * This thing takes DBD document from the directory specified, takes objects of selected
 *  server name, then filters them by name and type regexes, dependencies of the previous
 *  added then all objects, dependning on the whole set.
 * @since 0.1
 * @checkstyle ParameterNumberCheck (100 lines)
 */
public class DbdAddObjectsSingleProcedure extends WriteToDirectoryRunnable {

    /**
     * Instantiates a new Dbd add objects single procedure.
     * @param server The server to find in DBD.
     * @param rgxtype The type name regex.
     * @param rgxname The object name regex.
     * @param directory The {@link Directory} to search from.
     */
    public DbdAddObjectsSingleProcedure(
        final Text server,
        final Text rgxtype,
        final Text rgxname,
        final Directory directory
    ) {
        super(
            new DbdYamlWritable(
                new DbdReadableMerged(
                    new DbdReadableOfDirectory(directory),
                    new DbdSchemasRootMappingNode(
                        new DbdSchemasMappingValueOfObjects(
                            new ObjectsOfDbdAddSingle(
                                new DbdReadableOfDirectory(directory),
                                server,
                                rgxtype,
                                rgxname
                            )
                        )
                    )
                )
            ),
            directory
        );
    }

}
