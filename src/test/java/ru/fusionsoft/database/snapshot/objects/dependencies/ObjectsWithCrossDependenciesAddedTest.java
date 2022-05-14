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

import org.cactoos.iterable.Sticky;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import ru.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import ru.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;
import ru.fusionsoft.database.mapping.dbd.ofobjects.DbdSchemasMappingValueOfObjects;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsOfServer;

/**
 * The test for {@link ObjectsWithCrossDependenciesAdded}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
@SuppressWarnings("PMD")
class ObjectsWithCrossDependenciesAddedTest {

    /**
     * Show dbd.
     */
    @Test
    @Disabled
    public void showDbd() {
        System.out.println(
            new DbdSchemasMappingValueOfObjects(
                new ObjectsWithCrossDependenciesAdded(
                    new Sticky<>(
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
