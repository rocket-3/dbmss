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
package org.fusionsoft.database.snapshot.data;

import org.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import org.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import org.fusionsoft.database.connection.ConnectionOfDbdServerMapping;
import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;
import org.fusionsoft.database.mapping.fields.DbdTableFields;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.objects.ObjectsFiltered;
import org.fusionsoft.database.snapshot.objects.filtered.ObjectsWithType;
import org.fusionsoft.database.snapshot.objects.ofdbms.ObjectsFromServer;
import org.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import org.fusionsoft.lib.collection.Single;
import org.fusionsoft.lib.yaml.artefacts.TextOfMappingValue;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasString;

class InlineLinkDataObjectOfTableTest {

    @Test
    public void rendersLink() {
        final DbObject<DbdTableMapping> table = new Single<>(
            new ObjectsFiltered<>(
                x -> x.signature().name().first().asString().contains("million"),
                new ObjectsWithType<>(
                    new ObjectTypeTable(),
                    new ObjectsFromServer(
                        new ConnectionOfDbdServerMapping(
                            new DbdServerMappingWithCredentials(
                                new UrlOfPgGitLabDatabaseV11("pagilla"),
                                new CredsOfPgTestDatabase()
                            )
                        )
                    )
                )
            )
        ).value();
        new Assertion<>(
            "Should render non empty data rows for each table",
            new TextOfMappingValue(
                new InlineLinkDataObjectOfTable(table).asYaml(),
                DbdTableFields.DATA
            ),
            new HasString(
                new SeparateDataFilePathRelativeToDbd(
                    new SeparateDataFileName(table)
                )
            )
        ).affirm();
    }

}
