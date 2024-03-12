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

import org.cactoos.Func;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import ru.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import ru.fusionsoft.database.connection.ConnectionOfDbdServerMapping;
import ru.fusionsoft.database.dbdreadable.DbdReadableBuiltWithObjectsOfServer;
import ru.fusionsoft.database.mapping.dbd.DbdServerMapping;
import ru.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;
import ru.fusionsoft.database.migration.diff.SimpleTemporalDiff;
import ru.fusionsoft.database.migration.scenarios.main.MigrationOfDbdDiff;

/**
 * Tests for {@link MigrationOfDbdDiff}.
 * @since 0.1
 */
@SuppressWarnings("PMD")
class MigrationOfDbdDiffTest {

    /**
     * Show.
     * @checkstyle IllegalCatchCheck (100 lines)
     */
    @Test
    @Disabled
    public void show() {
        try {
            final Func<String, DbdServerMapping> server = name ->
                new DbdServerMappingWithCredentials(
                    new UrlOfPgGitLabDatabaseV11(name),
                    new CredsOfPgTestDatabase()
                );
            final String target = "DB2";
            final String source = "DB1";
            System.out.println(
                new MigrationOfDbdDiff(
                    new SimpleTemporalDiff<>(
                        new DbdReadableBuiltWithObjectsOfServer(server.apply(target)),
                        new DbdReadableBuiltWithObjectsOfServer(server.apply(source))
                    ),
                    new ConnectionOfDbdServerMapping(server.apply(target))
                ).sql()
            );
        } catch (final Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
