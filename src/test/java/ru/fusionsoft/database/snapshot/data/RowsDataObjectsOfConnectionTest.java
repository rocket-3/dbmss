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
package ru.fusionsoft.database.snapshot.data;

import org.cactoos.scalar.LengthOf;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasValuesMatching;
import ru.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import ru.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import ru.fusionsoft.database.connection.ConnectionOfDbdServerMapping;
import ru.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;
import ru.fusionsoft.database.mapping.fields.DbdTableFields;
import ru.fusionsoft.database.snapshot.objects.ObjectsFiltered;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithTypeCasted;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsOfServer;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.lib.yaml.EntriesOfYamlMapping;
import ru.fusionsoft.lib.yaml.YamlMappingOfPath;

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
                new ObjectsWithTypeCasted<>(
                    new ObjectTypeTable(),
                    new ObjectsOfServer(connection)
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

