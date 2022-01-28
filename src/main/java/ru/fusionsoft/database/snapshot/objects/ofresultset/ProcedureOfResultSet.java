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
import org.cactoos.iterable.IterableOf;
import ru.fusionsoft.database.mapping.dbd.DbdProcedureMapping;
import ru.fusionsoft.database.mapping.entries.MultilineSqlScalarEntry;
import ru.fusionsoft.database.mapping.entries.ScalarEntry;
import ru.fusionsoft.database.mapping.fields.DbdProcedureFields;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObjectOfEntries;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeProcedure;
import ru.fusionsoft.database.snapshot.query.Query;
import ru.fusionsoft.lib.text.TextOfResultSet;

/**
 * The type of {@link SimpleDbObjectOfEntries} of {@link DbdProcedureMapping}, can
 *  be constructed of {@link ResultSet} and {@link Query} of {@link DbdProcedureFields}.
 * @since 0.1
 */
public class ProcedureOfResultSet extends SimpleDbObjectOfEntries<DbdProcedureMapping> {

    /**
     * Instantiates a new Procedure of result set.
     * @param rset The ResultSet to be encapsulated.
     * @param query The Query of DbdProcedureFields to be encapsulated.
     */
    public ProcedureOfResultSet(final ResultSet rset, final Query<DbdProcedureFields> query) {
        super(
            new ObjectTypeProcedure(),
            new IterableOf<>(
                new TextOfResultSet(DbdProcedureFields.SCHEMA, rset, query),
                new TextOfResultSet(DbdProcedureFields.PROCEDURE, rset, query)
            ),
            new IterableOf<>(
                new ScalarEntry(
                    DbdProcedureFields.OWNER,
                    new TextOfResultSet(DbdProcedureFields.OWNER, rset, query)
                ),
                new ScalarEntry(
                    DbdProcedureFields.ARGUMENTS,
                    new TextOfResultSet(DbdProcedureFields.ARGUMENTS, rset, query)
                ),
                new MultilineSqlScalarEntry(
                    DbdProcedureFields.DDL,
                    new TextOfResultSet(DbdProcedureFields.DDL, rset, query)
                )
            )
        );
    }

}
