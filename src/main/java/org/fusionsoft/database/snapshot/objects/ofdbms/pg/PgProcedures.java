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
package org.fusionsoft.database.snapshot.objects.ofdbms.pg;

import java.sql.Connection;
import org.fusionsoft.database.mapping.dbd.DbdProcedureMapping;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.dbms.DbmsVersionOfConnection;
import org.fusionsoft.database.snapshot.objects.ObjectsOfScalar;
import org.fusionsoft.database.snapshot.objects.ofresultset.ProcedureOfResultSet;
import org.fusionsoft.database.snapshot.query.Query;
import org.fusionsoft.database.snapshot.query.pg.DbdProcedureFields;
import org.fusionsoft.database.snapshot.query.pg.PgProceduresQuery;
import org.fusionsoft.lib.collection.ListOfConnection;

/**
 * The type of procedures {@link Objects} that can be constructed of connection to Postgres DBMS.
 * @since 0.1
 */
public class PgProcedures extends ObjectsOfScalar<DbdProcedureMapping> {

    /**
     * Instantiates a new Postgres procedures.
     * @param connection The Connection to be encapsulated.
     */
    public PgProcedures(final Connection connection) {
        this(
            connection,
            new PgProceduresQuery(
                new DbmsVersionOfConnection(connection)
            )
        );
    }

    /**
     * Instantiates a new Postgres procedures.
     * @param connection The Connection to be encapsulated.
     * @param query The Query of DbdProcedureFields to be encapsulated.
     */
    public PgProcedures(final Connection connection, final Query<DbdProcedureFields> query) {
        super(
            () -> new ListOfConnection<>(
                connection,
                query,
                ProcedureOfResultSet::new
            )
        );
    }

}
