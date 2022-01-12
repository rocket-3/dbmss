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
package ru.fusionsoft.database.snapshot.objects.ofdbms.pg;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import ru.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import ru.fusionsoft.database.connection.ConnectionOfDbdServerMapping;
import ru.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;

/**
 * The tests for {@link PgConstraints}.
 * @since 0.1
 */
class PgConstraintsTest {

    /**
     * Show me.
     */
    @Test
    @Disabled
    @SuppressWarnings("PMD.SystemPrintln")
    public void showMe() {
        new PgConstraints(
            new ConnectionOfDbdServerMapping(
                new DbdServerMappingWithCredentials(
                    new UrlOfPgGitLabDatabaseV11("pagilla"),
                    new CredsOfPgTestDatabase()
                )
            )
        ).forEach(
            x -> {
                System.out.println(x.toString());
            }
        );
    }

}
