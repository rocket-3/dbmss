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

import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.snapshot.DbObject;

/**
 * The {@link DataObjectOfMappingAndTable} of {@link LinkDataMappingOfTable},
 *  can be created from parent {@link DbObject} of {@link DbdTableMapping}.
 * @since 0.1
 */
public class LinkDataObjectOfTable extends DataObjectOfMappingAndTable {

    /**
     * Instantiates a new Inline link data object of table.
     * @param table The {@link DbObject} of {@link DbdTableMapping} to be encapsulated.
     */
    public LinkDataObjectOfTable(final DbObject<DbdTableMapping> table) {
        super(
            new LinkDataMappingOfTable(table),
            table
        );
    }

}
