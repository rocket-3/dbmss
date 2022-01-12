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
import ru.fusionsoft.database.mapping.dbd.built.DbdDataMappingOfValue;
import ru.fusionsoft.database.snapshot.DbObject;

/**
 * The type of {@link DbdDataMappingOfValue} with relative separate data file url.
 * @since 0.1
 */
public class LinkDataMappingOfTable extends DbdDataMappingOfValue {

    /**
     * Instantiates a new Inline link data mapping of table.
     * @param table The {@link DbObject} {@link DbdTableMapping} to be encapsulated.
     */
    public LinkDataMappingOfTable(
        final DbObject<DbdTableMapping> table
    ) {
        super(
            new SeparateDataFilePathRelativeToDbd(
                new SeparateDataFileName(table)
            )
        );
    }

}
