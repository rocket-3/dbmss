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
package ru.fusionsoft.database.snapshot.objects.ofdbms;

import com.amihaiemil.eoyaml.YamlMapping;
import java.io.File;
import java.util.Comparator;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterable.Sorted;
import org.cactoos.map.MapOf;
import org.cactoos.text.TextOf;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasSize;
import org.llorllale.cactoos.matchers.HasValues;
import ru.fusionsoft.database.Folder;
import ru.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import ru.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import ru.fusionsoft.database.connection.ConnectionOfDbdServerMapping;
import ru.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;
import ru.fusionsoft.database.mapping.dbd.ofobjects.DbdSchemasMappingOfObjects;
import ru.fusionsoft.database.mapping.fields.DbdSchemaFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ObjectsFiltered;
import ru.fusionsoft.database.snapshot.objects.StickyObjects;
import ru.fusionsoft.database.writable.YamlDocument;
import ru.fusionsoft.lib.yaml.EntriesOfYamlMapping;
import ru.fusionsoft.lib.yaml.YamlMappingOfPath;

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
        final int size = 174;
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
        final int size = 133;
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
    public void canBeRendered() {
        new DbdSchemasMappingOfObjects(
            new StickyObjects<>(
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
                                new StickyObjects<>(
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
                    new IterableOf<Text>(
                        DbdSchemaFields.VIEWS,
                        DbdSchemaFields.SEQUENCES,
                        DbdSchemaFields.TABLES,
                        DbdSchemaFields.DOMAINS,
                        DbdSchemaFields.FUNCTIONS,
                        DbdSchemaFields.PROCEDURES
                    )
                )
            )
        ).affirm();
    }

    /**
     * Show me individual object's data.
     */
    @Test
    @Disabled
    @SuppressWarnings("PMD")
    public void showIndividual() {
        for (final DbObject<? extends YamlMapping> object : new ObjectsFromServer(
            new DbdServerMappingWithCredentials(
                new UrlOfPgGitLabDatabaseV11(this.database),
                new CredsOfPgTestDatabase()
            )
        )) {
            System.out.println(object.toString());
        }
    }

    /**
     * Show me the names only.
     */
    @Test
    @Disabled
    @SuppressWarnings("PMD")
    public void showNames() {
        for (final DbObject<? extends YamlMapping> object : new Sorted<>(
            Comparator.comparing(x -> x.signature().name().asString()),
            new ObjectsFromServer(
                new DbdServerMappingWithCredentials(
                    new UrlOfPgGitLabDatabaseV11(this.database),
                    new CredsOfPgTestDatabase()
                )
            )
        )) {
            System.out.println(object.signature().asString());
        }
    }

    /**
     * The Dbd created can be rendered.
     */
    @Test
    @Disabled
    @SuppressWarnings("PMD")
    public void showDbd() {
        final Folder test = () -> new File(
            "src/test/java/org/fusionsoft/database/snapshot/objects/ofdbms"
        ).toPath();
        final ConnectionOfDbdServerMapping connection = new ConnectionOfDbdServerMapping(
            new DbdServerMappingWithCredentials(
                new UrlOfPgGitLabDatabaseV11(this.database),
                new CredsOfPgTestDatabase()
            )
        );
        new YamlDocument(
            new DbdSchemasMappingOfObjects(
                new StickyObjects<>(
                    new ObjectsFiltered<>(
                        x -> !x.signature().name().parent().first().asString().contains("million"),
                        new ObjectsWithInlineLinkDataAdded(
                            new ObjectsFromServer(
                                connection
                            )
                        )
                    )
                )
            ),
            new TextOf("pagillaWithData.yaml")
        ).writeTo(test);
    }

}
