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
package org.fusionsoft.database.snapshot.objects.dbms;

import org.fusionsoft.database.ci.UrlOfPgTestDatabaseV11;
import org.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import org.fusionsoft.database.mapping.dbd.DbdServerMappingWithCredentials;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasSize;

/**
 * The test for {@link ObjectsFromServer}.
 * @since 0.1
 */
class ObjectsFromServerTest {

    /**
     * Works.
     */
    @Test
    public void works() {
        final int size = 1;
        new Assertion<>(
            "Has expected size",
            new ObjectsFromServer(
                new DbdServerMappingWithCredentials(
                    new UrlOfPgTestDatabaseV11(),
                    new CredsOfPgTestDatabase()
                )
            ),
            new HasSize(size)
        );
    }

}
