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

import org.cactoos.iterable.Mapped;
import org.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import org.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import org.fusionsoft.database.connection.ConnectionOfDbdServerMapping;
import org.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;
import org.fusionsoft.database.snapshot.objects.filtered.ObjectsWithType;
import org.fusionsoft.database.snapshot.objects.ofdbms.ObjectsFromServer;
import org.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * The tests for {@link RowsDataEntriesOfConnection}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
class RowsDataEntriesOfConnectionTest {

    /**
     * Just show.
     */
    @Test
    @Disabled
    @SuppressWarnings("PMD.SystemPrintln")
    public void show() {
        final ConnectionOfDbdServerMapping connection = new ConnectionOfDbdServerMapping(
            new DbdServerMappingWithCredentials(
                new UrlOfPgGitLabDatabaseV11("dvdrental"),
                new CredsOfPgTestDatabase()
            )
        );
        final Mapped<RowsDataEntriesOfConnection> datas = new Mapped<>(
            x -> new RowsDataEntriesOfConnection(connection, x),
            new ObjectsWithType<>(
                new ObjectTypeTable(),
                new ObjectsFromServer(connection)
            )
        );
        for (final RowsDataEntriesOfConnection data : datas) {
            System.out.println(new YamlMappingOfEntries(data).toString());
        }
    }

}
