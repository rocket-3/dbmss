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

import org.cactoos.scalar.NumberOf;
import ru.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import ru.fusionsoft.database.mapping.fields.DbdColumnFields;
import ru.fusionsoft.lib.yaml.artefacts.TextOfYamlMappingKeyValue;

/**
 * The type of {@link Column} can be constructed of {@link DbdColumnMapping}.
 * @since 0.1
 */
public class ColumnOfDbdColumnMapping extends ColumnOfScalar {

    /**
     * Instantiates a new Column of dbd column mapping.
     * @param column The DbdColumnMapping to be encapsulated.
     */
    public ColumnOfDbdColumnMapping(final DbdColumnMapping column) {
        super(
            () -> new ColumnSimple(
                new TextOfYamlMappingKeyValue(
                    column,
                    DbdColumnFields.DBNAME
                ),
                new NumberOf(
                    new TextOfYamlMappingKeyValue(
                        column,
                        DbdColumnFields.ORDER
                    )
                ),
                new ValueFormatOfIuType(
                    new TextOfYamlMappingKeyValue(
                        column,
                        DbdColumnFields.IUTYPE
                    )
                )
            )
        );
    }

}
