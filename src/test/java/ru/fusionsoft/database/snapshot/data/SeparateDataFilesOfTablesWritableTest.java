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
package ru.fusionsoft.database.snapshot.data;

import java.sql.Connection;
import java.sql.SQLException;
import org.cactoos.scalar.NumberOf;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsNumber;
import ru.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import ru.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import ru.fusionsoft.database.connection.ConnectionOfDbdServerMapping;
import ru.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithTypeCasted;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsOfServer;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.path.UncheckedTempFolder;

/**
 * The test for {@link SeparateDataFilesOfTables}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
class SeparateDataFilesOfTablesWritableTest {

    /**
     * Writes big files fast.
     */
    @Test
    public void writesBigFilesFast() throws SQLException {
        final Directory directory = new UncheckedTempFolder();
        try (
            Connection connection = new ConnectionOfDbdServerMapping(
                new DbdServerMappingWithCredentials(
                    new UrlOfPgGitLabDatabaseV11("pagilla"),
                    new CredsOfPgTestDatabase()
                )
            )
        ) {
            new SeparateDataFilesOfTables(
                connection,
                new ObjectsWithTypeCasted<>(
                    new ObjectTypeTable(),
                    new ObjectsOfServer(
                        connection
                    )
                )
            ).writeTo(directory);
            new Assertion<>(
                "the biggest data file has expected size",
                directory.value().resolve("public.million.data.yaml").toFile().length(),
                new IsNumber(new NumberOf("236778131"))
            ).affirm();
        }
    }

}
