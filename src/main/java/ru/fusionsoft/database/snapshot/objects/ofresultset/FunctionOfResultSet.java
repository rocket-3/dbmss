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
import ru.fusionsoft.database.mapping.dbd.DbdFunctionMapping;
import ru.fusionsoft.database.mapping.entries.MultilineSqlScalarEntry;
import ru.fusionsoft.database.mapping.entries.ScalarEntry;
import ru.fusionsoft.database.mapping.fields.DbdFunctionFields;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObject;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObjectOfEntries;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectNameOfResultSet;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeFunction;
import ru.fusionsoft.database.snapshot.query.Query;
import ru.fusionsoft.lib.text.TextOfResultSet;

/**
 * The type of {@link SimpleDbObject} of {@link DbdFunctionMapping} can be
 *  constructed of {@link ResultSet} and {@link Query} of {@link DbdFunctionFields}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class FunctionOfResultSet extends SimpleDbObjectOfEntries<DbdFunctionMapping> {

    /**
     * Instantiates a new Function of result set.
     * @param rset The ResultSet to be encapsulated.
     * @param query The Query of DbdFunctionFields to be encapsulated.
     */
    public FunctionOfResultSet(final ResultSet rset, final Query<DbdFunctionFields> query) {
        super(
            new ObjectTypeFunction(),
            new SimpleObjectNameOfResultSet(
                rset,
                query,
                DbdFunctionFields.SCHEMA,
                DbdFunctionFields.FUNCTION
            ),
            new ScalarEntry(
                DbdFunctionFields.OWNER,
                new TextOfResultSet(
                    query.outcomeFor(DbdFunctionFields.OWNER),
                    rset
                )
            ),
            new ScalarEntry(
                DbdFunctionFields.ARGUMENTS,
                new TextOfResultSet(
                    query.outcomeFor(DbdFunctionFields.ARGUMENTS),
                    rset
                )
            ),
            new ScalarEntry(
                DbdFunctionFields.AGGREGATE,
                new TextOfResultSet(
                    query.outcomeFor(DbdFunctionFields.AGGREGATE),
                    rset
                )
            ),
            new MultilineSqlScalarEntry(
                DbdFunctionFields.DDL,
                new TextOfResultSet(
                    query.outcomeFor(DbdFunctionFields.DDL),
                    rset
                )
            )
        );
    }

}
