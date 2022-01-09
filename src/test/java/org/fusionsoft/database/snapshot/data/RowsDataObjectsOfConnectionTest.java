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
package org.fusionsoft.database.snapshot.data;

import org.cactoos.scalar.LengthOf;
import org.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import org.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import org.fusionsoft.database.connection.ConnectionOfDbdServerMapping;
import org.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;
import org.fusionsoft.database.mapping.fields.DbdTableFields;
import org.fusionsoft.database.snapshot.objects.ObjectsFiltered;
import org.fusionsoft.database.snapshot.objects.filtered.ObjectsWithType;
import org.fusionsoft.database.snapshot.objects.ofdbms.ObjectsFromServer;
import org.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import org.fusionsoft.lib.yaml.EntriesOfYamlMapping;
import org.fusionsoft.lib.yaml.YamlMappingOfPath;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasValuesMatching;

/**
 * The tests for {@link InlineRowsDataObjectsOfConnection}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class RowsDataObjectsOfConnectionTest {

    /**
     * Creates expected rows.
     */
    @Test
    public void createsExpectedRows() {
        final ConnectionOfDbdServerMapping connection = new ConnectionOfDbdServerMapping(
            new DbdServerMappingWithCredentials(
                new UrlOfPgGitLabDatabaseV11("pagilla"),
                new CredsOfPgTestDatabase()
            )
        );
        final InlineRowsDataObjectsOfConnection data = new InlineRowsDataObjectsOfConnection(
            connection,
            new ObjectsFiltered<>(
                x -> !x.signature().name().first().asString().contains("million"),
                new ObjectsWithType<>(
                    new ObjectTypeTable(),
                    new ObjectsFromServer(connection)
                )
            )
        );
        new Assertion<>(
            "Should render non empty data rows for each table",
            data,
            new HasValuesMatching<>(
                obj -> new LengthOf(
                    new EntriesOfYamlMapping(
                        new YamlMappingOfPath(
                            obj.asYaml(),
                            DbdTableFields.DATA
                        )
                    )
                ).value() > 1
            )
        ).affirm();
    }

}

