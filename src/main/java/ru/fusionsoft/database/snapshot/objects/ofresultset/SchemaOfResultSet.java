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
package ru.fusionsoft.database.snapshot.objects.ofresultset;

import java.sql.ResultSet;
import ru.fusionsoft.database.mapping.dbd.DbdSchemaMapping;
import ru.fusionsoft.database.mapping.entries.ScalarEntryOfResultSet;
import ru.fusionsoft.database.mapping.fields.DbdSchemaFields;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObjectOfEntries;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectNameOfResultSet;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeSchema;
import ru.fusionsoft.database.snapshot.query.Query;

/**
 * The {@link SimpleDbObjectOfEntries} of {@link DbdSchemaMapping}
 *  that can be constructed of {@link Query} and {@link ResultSet}.
 * @since 0.1
 */
public class SchemaOfResultSet extends SimpleDbObjectOfEntries<DbdSchemaMapping> {

    /**
     * Instantiates a new Schema of result set.
     * @param rset The {@link ResultSet} to be encapsulated.
     * @param query The {@link Query} of {@link DbdSchemaFields} to be encapsulated.
     */
    public SchemaOfResultSet(final ResultSet rset, final Query<DbdSchemaFields> query) {
        super(
            new ObjectTypeSchema(),
            new SimpleObjectNameOfResultSet(
                rset, query,
                DbdSchemaFields.SCHEMA
            ),
            new ScalarEntryOfResultSet(
                DbdSchemaFields.OWNER,
                rset, query
            )
        );
    }

}
