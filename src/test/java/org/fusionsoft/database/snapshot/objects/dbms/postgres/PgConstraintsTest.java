/*
 * Copyright (C) 2018-2021 FusionSoft
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
package org.fusionsoft.database.snapshot.objects.dbms.postgres;

import org.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import org.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import org.fusionsoft.database.connection.ConnectionOfDbdServerMapping;
import org.fusionsoft.database.mapping.dbd.DbdServerMappingWithCredentials;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class PgConstraintsTest {

    @Test
    @Disabled
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