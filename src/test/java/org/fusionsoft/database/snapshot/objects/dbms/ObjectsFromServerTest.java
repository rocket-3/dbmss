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

import com.amihaiemil.eoyaml.YamlMapping;
import org.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import org.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import org.fusionsoft.database.mapping.dbd.DbdSchemasMappingOfObjects;
import org.fusionsoft.database.mapping.dbd.DbdServerMappingWithCredentials;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.objects.StickyObjects;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasSize;

/**
 * The test for {@link ObjectsFromServer}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
class ObjectsFromServerTest {

    /**
     * The default database name encapsulated.
     */
    private final String database;

    /**
     * Instantiates a new Objects from server test.
     */
    ObjectsFromServerTest() {
        this.database = "pagilla";
    }

    /**
     * Works.
     */
    @Test
    public void works() {
        final int size = 119;
        new Assertion<>(
            "Has expected size",
            new ObjectsFromServer(
                new DbdServerMappingWithCredentials(
                    new UrlOfPgGitLabDatabaseV11(this.database),
                    new CredsOfPgTestDatabase()
                )
            ),
            new HasSize(size)
        ).affirm();
    }

    /**
     * The Dbd created can be rendered.
     */
    @Test
    @Disabled
    @SuppressWarnings("PMD")
    public void createsCorrectDbd() {
        System.out.println(
            new DbdSchemasMappingOfObjects(
                new StickyObjects(
                    new ObjectsFromServer(
                        new DbdServerMappingWithCredentials(
                            new UrlOfPgGitLabDatabaseV11(this.database),
                            new CredsOfPgTestDatabase()
                        )
                    )
                )
            ).toString()
        );
    }

    /**
     * Show me.
     */
    @Test
    @Disabled
    @SuppressWarnings("PMD")
    public void showMe() {
        for (final DbObject<? extends YamlMapping> object : new ObjectsFromServer(
            new DbdServerMappingWithCredentials(
                new UrlOfPgGitLabDatabaseV11(this.database),
                new CredsOfPgTestDatabase()
            )
        )) {
            System.out.println(object.toString());
        }
    }

}
