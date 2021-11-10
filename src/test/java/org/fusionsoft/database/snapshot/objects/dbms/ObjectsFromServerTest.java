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
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapOf;
import org.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import org.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import org.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;
import org.fusionsoft.database.mapping.dbd.ofobjects.DbdSchemasMappingOfObjects;
import org.fusionsoft.database.mapping.fields.DbdSchemaFields;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.objects.StickyObjects;
import org.fusionsoft.lib.yaml.EntriesOfYamlMapping;
import org.fusionsoft.lib.yaml.YamlMappingOfPath;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasSize;
import org.llorllale.cactoos.matchers.HasValues;

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
     * Works with pagilla.
     */
    @Test
    public void worksWithPagilla() {
        final int size = 170;
        new Assertion<>(
            "Has expected object list size from 'pagilla' database",
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
     * Works with dvdrental.
     */
    @Test
    public void worksWithDvdRental() {
        final int size = 132;
        new Assertion<>(
            "Has expected object list size from 'dvdrental' database",
            new ObjectsFromServer(
                new DbdServerMappingWithCredentials(
                    new UrlOfPgGitLabDatabaseV11("dvdrental"),
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
    public void showDbd() {
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
     * The Dbd created can be rendered.
     */
    @Test
    public void canBeRendered() {
        new DbdSchemasMappingOfObjects(
            new StickyObjects(
                new ObjectsFromServer(
                    new DbdServerMappingWithCredentials(
                        new UrlOfPgGitLabDatabaseV11(this.database),
                        new CredsOfPgTestDatabase()
                    )
                )
            )
        ).toString();
    }

    /**
     * The Dbd created has expected nodes.
     */
    @Test
    @SuppressWarnings("PMD")
    public void createsCorrectDbd() {
        new Assertion<>(
            "Has tables and sequences second-level nodes",
            new Mapped<String>(
                Text::asString,
                new MapOf<>(
                    new EntriesOfYamlMapping(
                        new YamlMappingOfPath(
                            new DbdSchemasMappingOfObjects(
                                new StickyObjects(
                                    new ObjectsFromServer(
                                        new DbdServerMappingWithCredentials(
                                            new UrlOfPgGitLabDatabaseV11(this.database),
                                            new CredsOfPgTestDatabase()
                                        )
                                    )
                                )
                            ),
                            "public"
                        )
                    )
                ).keySet()
            ),
            new HasValues<>(
                new Mapped<String>(
                    Text::asString,
                    new IterableOf<Text>(DbdSchemaFields.values())
                )
            )
        ).affirm();
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
