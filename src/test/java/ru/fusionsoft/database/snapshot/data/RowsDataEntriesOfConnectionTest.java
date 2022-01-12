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

import org.cactoos.iterable.Mapped;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import ru.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import ru.fusionsoft.database.connection.ConnectionOfDbdServerMapping;
import ru.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithType;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsFromServer;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The tests for {@link InlineRowsDataEntriesOfConnection}.
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
        final Mapped<InlineRowsDataEntriesOfConnection> datas = new Mapped<>(
            x -> new InlineRowsDataEntriesOfConnection(connection, x),
            new ObjectsWithType<>(
                new ObjectTypeTable(),
                new ObjectsFromServer(connection)
            )
        );
        for (final InlineRowsDataEntriesOfConnection data : datas) {
            System.out.println(new YamlMappingOfEntries(data).toString());
        }
    }

}
