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
package ru.fusionsoft.database.snapshot.objects.dependencies;

import org.junit.jupiter.api.Test;
import ru.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import ru.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import ru.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;
import ru.fusionsoft.database.mapping.dbd.ofobjects.DbdSchemasMappingOfObjects;
import ru.fusionsoft.database.snapshot.objects.StickyObjects;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsOfServer;

class ObjectsWithCrossDependenciesAddedTest {

    @Test
    public void showDbd() {
        System.out.println(
            new DbdSchemasMappingOfObjects(
                new ObjectsWithCrossDependenciesAdded(
                    new StickyObjects<>(
                        new ObjectsOfServer(
                            new DbdServerMappingWithCredentials(
                                new UrlOfPgGitLabDatabaseV11("dvdrental"),
                                new CredsOfPgTestDatabase()
                            )
                        )
                    )
                )
            ).toString()
        );
    }

}
