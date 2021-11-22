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
package org.fusionsoft.database.snapshot.objects.ofdbms.pg;

import java.sql.Connection;
import org.fusionsoft.database.mapping.dbd.DbdFunctionMapping;
import org.fusionsoft.database.mapping.fields.DbdFunctionFields;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.dbms.DbmsVersionOfConnection;
import org.fusionsoft.database.snapshot.objects.ObjectsOfScalar;
import org.fusionsoft.database.snapshot.objects.ofresultset.FunctionOfResultSet;
import org.fusionsoft.database.snapshot.query.Query;
import org.fusionsoft.database.snapshot.query.pg.PgFunctionsQuery;
import org.fusionsoft.lib.collection.ListOfConnection;

/**
 * The type of functions {@link Objects} that can be constructed of connection to Postgres DBMS.
 * @since 0.1
 * @checkstyle StringLiteralsConcatenationCheck (100 lines)
 */
public class PgFunctions extends ObjectsOfScalar<DbdFunctionMapping> {

    /**
     * Instantiates a new Postgres functions.
     * @param connection The Connection to be encapsulated.
     */
    public PgFunctions(final Connection connection) {
        this(
            connection,
            new PgFunctionsQuery(
                new DbmsVersionOfConnection(connection)
            )
        );
    }

    /**
     * Instantiates a new Postgres functions.
     * @param connection The Connection to be encapsulated.
     * @param query The Query of DbdFunctionFields to be encapsulated.
     */
    public PgFunctions(final Connection connection, final Query<DbdFunctionFields> query) {
        super(
            () -> new ListOfConnection<>(
                connection,
                query,
                FunctionOfResultSet::new
            )
        );
    }

}
