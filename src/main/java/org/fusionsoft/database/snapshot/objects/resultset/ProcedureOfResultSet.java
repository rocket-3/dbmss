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
import org.cactoos.iterable.IterableOf;
import org.fusionsoft.database.mapping.dbd.DbdProcedureMapping;
import org.fusionsoft.database.mapping.entries.MultilineScalarEntry;
import org.fusionsoft.database.mapping.entries.ScalarEntry;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.query.Query;
import org.fusionsoft.database.snapshot.query.pg.DbdProcedureFields;
import org.fusionsoft.lib.text.TextOfResultSet;

public class ProcedureOfResultSet extends ObjectOfEntries<DbdProcedureMapping> {

    public ProcedureOfResultSet(final ResultSet rset, final Query<DbdProcedureFields> query) {
        super(
            ObjectType.PROCEDURE,
            DbdProcedureMapping::new,
            new IterableOf<>(
                new TextOfResultSet(DbdProcedureFields.SCHEMA, rset),
                new TextOfResultSet(DbdProcedureFields.PROCEDURE, rset)
            ),
            new IterableOf<>(
                new ScalarEntry(
                    DbdProcedureFields.OWNER,
                    new TextOfResultSet(DbdProcedureFields.OWNER, rset)
                ),
                new ScalarEntry(
                    DbdProcedureFields.ARGUMENTS,
                    new TextOfResultSet(DbdProcedureFields.ARGUMENTS, rset)
                ),
                new MultilineScalarEntry(
                    DbdProcedureFields.DDL,
                    new TextOfResultSet(DbdProcedureFields.DDL, rset)
                )
            )
        );
    }

}
