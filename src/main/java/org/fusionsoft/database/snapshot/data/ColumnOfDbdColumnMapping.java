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

import org.cactoos.scalar.NumberOf;
import org.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import org.fusionsoft.database.mapping.fields.DbdColumnFields;
import org.fusionsoft.lib.yaml.artefacts.TextOfMappingValue;

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
                new TextOfMappingValue(
                    column,
                    DbdColumnFields.DBNAME
                ),
                new NumberOf(
                    new TextOfMappingValue(
                        column,
                        DbdColumnFields.ORDER
                    )
                ),
                new ValueFormatOfIuType(
                    new TextOfMappingValue(
                        column,
                        DbdColumnFields.IUTYPE
                    )
                )
            )
        );
    }

}
