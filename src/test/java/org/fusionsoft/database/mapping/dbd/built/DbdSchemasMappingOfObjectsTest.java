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
package org.fusionsoft.database.mapping.dbd.built;

import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import org.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import org.fusionsoft.database.mapping.MappingOfExampleYaml;
import org.fusionsoft.database.mapping.dbd.DbdRootMapping;
import org.fusionsoft.database.mapping.dbd.ofobjects.DbdSchemasMappingOfObjects;
import org.fusionsoft.database.snapshot.objects.StickyObjects;
import org.fusionsoft.database.snapshot.objects.dbd.ObjectsOfDbdRootMapping;
import org.fusionsoft.database.snapshot.objects.dbms.ObjectsFromServer;
import org.fusionsoft.lib.yaml.EntriesOfYamlMapping;
import org.fusionsoft.lib.yaml.YamlMappingOfPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasValues;

/**
 * The tests for {@link DbdSchemasMappingOfObjects}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (255 lines)
 */
class DbdSchemasMappingOfObjectsTest {

    /**
     * Creates mapping identical to input.
     */
    @Test
    public void createsMappingIdenticalToInput() {
        final MappingOfExampleYaml yaml = new MappingOfExampleYaml();
        Assertions.assertEquals(
            new YamlMappingOfPath(yaml, "schemas").toString(),
            new DbdSchemasMappingOfObjects(
                new ObjectsOfDbdRootMapping(
                    new DbdRootMapping(
                        yaml
                    )
                )
            ).toString()
        );
    }

    /**
     * Has all possible entries from pagilla.
     */
    @Test
    void hasEntriesFromPagilla() {
        new Assertion<>(
            "should have tables, sequences, views nodes",
            new Mapped<String>(
                entry -> entry.getKey().asString(),
                new EntriesOfYamlMapping(
                    new YamlMappingOfPath(
                        new DbdSchemasMappingOfObjects(
                            new StickyObjects(
                                new ObjectsFromServer(
                                    new DbdServerMappingWithCredentials(
                                        new UrlOfPgGitLabDatabaseV11("pagilla"),
                                        new CredsOfPgTestDatabase()
                                    )
                                )
                            )
                        ),
                        "public"
                    )
                )
            ),
            new HasValues<>(
                new IterableOf<String>("tables", "sequences", "views")
            )
        ).affirm();
    }

}
