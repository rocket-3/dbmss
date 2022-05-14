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
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.Sticky;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasValues;
import ru.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import ru.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import ru.fusionsoft.database.dbdreadable.DbdReadableOfDirectory;
import ru.fusionsoft.database.mapping.dbd.DbdServerEntry;
import ru.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.ObjectSignature;
import ru.fusionsoft.database.snapshot.objects.ObjectsOfScalar;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfDbdReadable;
import ru.fusionsoft.database.snapshot.objects.signature.SimpleObjectSignature;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeSchema;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.path.DirectoryOfScalar;
import ru.fusionsoft.lib.path.UncheckedTempFolder;

/**
 * The test for {@link DbdAddObjectsSingleProcedure}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 */
@SuppressWarnings("PMD")
class DbdAddObjectsSingleProcedureTest {

    /**
     * Adds expected objects to document.
     */
    @Test
    public void addsExpectedObjectsToDocument() {
        final Text server = () -> "pagilla";
        final Directory directory = new DirectoryOfScalar(new Sticky<>(new UncheckedTempFolder()));
        new Assertion<>(
            "Should contain added object",
            new Mapped<ObjectSignature>(
                DbObject::signature,
                new ObjectsOfScalar<>(
                    () -> {
                        for (final Runnable runnable : new IterableOf<Runnable>(
                            new DbdInitProcedure(directory),
                            new DbdAddServerProcedure(
                                new DbdServerEntry(
                                    server,
                                    new DbdServerMappingWithCredentials(
                                        new UrlOfPgGitLabDatabaseV11(server),
                                        new CredsOfPgTestDatabase()
                                    )
                                ),
                                directory
                            ),
                            new DbdAddObjectsSingleProcedure(
                                server,
                                () -> "table|schema",
                                () -> ".*rental|public",
                                directory
                            )
                        )) {
                            runnable.run();
                        }
                        return new ObjectsOfDbdReadable(
                            new DbdReadableOfDirectory(directory)
                        );
                    }
                )
            ),
            new HasValues<ObjectSignature>(
                new SimpleObjectSignature(
                    new SimpleObjectName("public", "rental"),
                    new ObjectTypeTable()
                ),
                new SimpleObjectSignature(
                    new SimpleObjectName("public"),
                    new ObjectTypeSchema()
                )
            )
        ).affirm();
    }

}
