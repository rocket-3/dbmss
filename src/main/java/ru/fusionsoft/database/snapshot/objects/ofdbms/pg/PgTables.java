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
package ru.fusionsoft.database.snapshot.objects.ofdbms.pg;

import java.sql.Connection;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.snapshot.dbms.DbmsVersionOfConnection;
import ru.fusionsoft.database.snapshot.objects.ObjectsOfScalar;
import ru.fusionsoft.database.snapshot.objects.ofresultset.TableOfResultSet;
import ru.fusionsoft.database.snapshot.query.pg.PgColumnsQuery;
import ru.fusionsoft.database.snapshot.query.pg.PgTablesQuery;
import ru.fusionsoft.lib.collection.ListOfResultSet;

/**
 * The type of {@link ObjectsOfScalar} that can be constructed
 *  of {@link Connection} to Postgres DBMS.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class PgTables extends ObjectsOfScalar<DbdTableMapping> {

    /**
     * Instantiates a new Postgres tables.
     * @param connection The Connection to be encapsulated.
     */
    public PgTables(final Connection connection) {
        super(
            () -> new ListOfResultSet<>(
                (rs, c) -> new TableOfResultSet(
                    rs, c,
                    new PgTablesQuery(new DbmsVersionOfConnection(c)),
                    PgColumnsQuery::new
                ),
                new PgTablesQuery(new DbmsVersionOfConnection(connection)),
                connection
            )
        );
    }

}
