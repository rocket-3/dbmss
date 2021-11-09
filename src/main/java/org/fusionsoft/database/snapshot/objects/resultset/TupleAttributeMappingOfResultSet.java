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
package org.fusionsoft.database.snapshot.objects.resultset;

import java.sql.ResultSet;
import org.fusionsoft.database.mapping.dbd.DbdTupleAttributeMapping;
import org.fusionsoft.database.mapping.entries.ScalarEntryOfResultSet;
import org.fusionsoft.database.mapping.fields.DbdTupleAttributeField;
import org.fusionsoft.database.snapshot.query.Query;
import org.fusionsoft.lib.yaml.MappingWithoutNullScalars;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

public class TupleAttributeMappingOfResultSet extends DbdTupleAttributeMapping {

    public TupleAttributeMappingOfResultSet(
        final ResultSet rset,
        final Query<DbdTupleAttributeField> query
    ) {
        super(
            new MappingWithoutNullScalars(
                new YamlMappingOfEntries(
                    new ScalarEntryOfResultSet(DbdTupleAttributeField.NAME, query, rset),
                    new ScalarEntryOfResultSet(DbdTupleAttributeField.TYPE, query, rset),
                    new ScalarEntryOfResultSet(DbdTupleAttributeField.ORDER, query, rset)
                )
            )
        );
    }

}
