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
package ru.fusionsoft.database.migration.common;

import org.cactoos.text.TextOf;
import org.junit.jupiter.api.Test;
import ru.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import ru.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import ru.fusionsoft.database.dbdreadable.DbdReadableBuiltWithObjectsOfServer;
import ru.fusionsoft.database.dbdreadable.DbdReadableWithServerEntry;
import ru.fusionsoft.database.diff.ObjectsDiffOfDbdReadableServer;
import ru.fusionsoft.database.mapping.dbd.DbdServerEntry;
import ru.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;
import ru.fusionsoft.database.snapshot.dbms.PostgresDbms;

class DbdMigrationTest {

    @Test
    public void show() {
        final String target = "dvdrental";
        System.out.println(new DbdMigration(
            new ObjectsDiffOfDbdReadableServer(
                new DbdReadableWithServerEntry(
                    new DbdReadableBuiltWithObjectsOfServer(
                        new DbdServerMappingWithCredentials(
                            new UrlOfPgGitLabDatabaseV11("pagilla"),
                            new CredsOfPgTestDatabase()
                        )
                    ),
                    new DbdServerEntry(
                        new TextOf(target),
                        new DbdServerMappingWithCredentials(
                            new UrlOfPgGitLabDatabaseV11(target),
                            new CredsOfPgTestDatabase()
                        )
                    )
                ),
                new TextOf(target)
            ),
            new PostgresDbms()
        ).sql());
    }

}
