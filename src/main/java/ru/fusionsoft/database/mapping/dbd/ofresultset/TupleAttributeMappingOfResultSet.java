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
package ru.fusionsoft.database.mapping.dbd.ofresultset;

import java.sql.ResultSet;
import ru.fusionsoft.database.mapping.dbd.DbdTupleAttributeMapping;
import ru.fusionsoft.database.mapping.entries.ScalarEntryOfResultSet;
import ru.fusionsoft.database.mapping.fields.DbdTupleAttributeField;
import ru.fusionsoft.database.snapshot.query.Query;
import ru.fusionsoft.lib.yaml.MappingWithoutNullScalars;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The type of {@link DbdTupleAttributeMapping} that can be constructed
 *  of {@link ResultSet} and {@link Query} of {@link DbdTupleAttributeField}.
 * @since 0.1
 */
public class TupleAttributeMappingOfResultSet extends DbdTupleAttributeMapping {

    /**
     * Instantiates a new Tuple attribute mapping of result set.
     * @param rset The {@link ResultSet} to be used eagerly (immediately).
     * @param query The {@link Query} of {@link DbdTupleAttributeField} to be encapsulated.
     */
    public TupleAttributeMappingOfResultSet(
        final ResultSet rset,
        final Query<DbdTupleAttributeField> query
    ) {
        super(
            new MappingWithoutNullScalars(
                new YamlMappingOfEntries(
                    new ScalarEntryOfResultSet(DbdTupleAttributeField.ATTRIBUTE, query, rset),
                    new ScalarEntryOfResultSet(DbdTupleAttributeField.TYPE, query, rset),
                    new ScalarEntryOfResultSet(DbdTupleAttributeField.IUTYPE, query, rset),
                    new ScalarEntryOfResultSet(DbdTupleAttributeField.ORDER, query, rset)
                )
            )
        );
    }

}
