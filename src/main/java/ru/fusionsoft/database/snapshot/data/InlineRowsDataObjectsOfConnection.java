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

import java.sql.Connection;
import org.cactoos.iterable.Mapped;
import ru.fusionsoft.database.mapping.dbd.DbdDataMapping;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.objects.ObjectsEnvelope;

/**
 * The {@link Objects} of {@link DbdDataMapping} with '"key": ["value1", "value2"]' format,
 *  can be constructed of {@link Connection} and {@link Objects} of {@link DbdTableMapping}.
 * @since 0.1
 */
public class InlineRowsDataObjectsOfConnection extends ObjectsEnvelope<DbdDataMapping> {

    /**
     * Instantiates a new Inline rows data objects of connection.
     * @param connection The {@link Connection} to be encapsulated.
     * @param tables The {@link Objects} of {@link DbdTableMapping} to be encapsulated.
     */
    public InlineRowsDataObjectsOfConnection(
        final Connection connection,
        final Objects<DbdTableMapping> tables
    ) {
        super(
            new Mapped<>(
                table -> new InlineRowsDataObjectOfConnection(connection, table),
                tables
            )
        );
    }

}
