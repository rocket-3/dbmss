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
import ru.fusionsoft.database.dbdfile.DbdFileMerged;
import ru.fusionsoft.database.dbdfile.DbdFileOfDirectory;
import ru.fusionsoft.database.mapping.dbd.ofobjects.DbdSchemasMappingOfObjects;
import ru.fusionsoft.database.snapshot.objects.ObjectsOfScalar;
import ru.fusionsoft.database.snapshot.objects.dependencies.ObjectsUpdatedWithTheirRelationsOfServerAndDbd;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithTypeAndNameMatchesRegexp;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfDbdFile;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfServerFromDbd;
import ru.fusionsoft.database.snapshot.writable.DbdDocument;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.runnable.WriteToDirectoryRunnable;

public class DbdAddObjectsProcedure extends WriteToDirectoryRunnable {

    /**
     * Instantiates a new Write to folder procedure.
     * @param directory The {@link Directory} to be encapsulated.
     */
    public DbdAddObjectsProcedure(
        final Text server,
        final Text type,
        final Text name,
        final Directory directory
    ) {
        super(
            new DbdDocument(
                new DbdFileMerged(
                    new DbdFileOfDirectory(directory),
                    new DbdSchemasMappingOfObjects(
                        new ObjectsOfScalar<>(
                            () -> {
                                final DbdFileOfDirectory file = new DbdFileOfDirectory(directory);
                                final ObjectsOfServerFromDbd ofserver = new ObjectsOfServerFromDbd(
                                    file,
                                    server
                                );
                                return new ObjectsUpdatedWithTheirRelationsOfServerAndDbd<>(
                                    new ObjectsWithTypeAndNameMatchesRegexp<>(
                                        type,
                                        name,
                                        ofserver
                                    ),
                                    ofserver,
                                    new ObjectsOfDbdFile(file)
                                );
                            })
                    )
                )
            ),
            directory
        );
    }

}
