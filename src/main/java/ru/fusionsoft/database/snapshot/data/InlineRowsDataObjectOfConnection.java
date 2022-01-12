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
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.snapshot.DbObject;

/**
 * The {@link DataObjectOfMappingAndTable} with {@link InlineRowsDataMappingOfConnection},
 *  can be constructed of {@link Connection} and parent {@link DbObject} of {@link DbdTableMapping}.
 * @since 0.1
 */
public class InlineRowsDataObjectOfConnection extends DataObjectOfMappingAndTable {

    /**
     * Instantiates a new simple db object.
     * @param connection The {@link Connection} to be encapsulated.
     * @param table The {@link DbObject} of {@link DbdTableMapping} to be encapsulated.
     */
    public InlineRowsDataObjectOfConnection(
        final Connection connection,
        final DbObject<DbdTableMapping> table
    ) {
        super(
            new InlineRowsDataMappingOfConnection(
                connection,
                table
            ),
            table
        );
    }

}
