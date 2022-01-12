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
import ru.fusionsoft.database.mapping.dbd.DbdTupleMapping;
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.objects.ObjectsOfScalar;
import ru.fusionsoft.database.snapshot.objects.ofresultset.TupleOfResultSet;
import ru.fusionsoft.database.snapshot.query.pg.PgTupleAttributesQuery;
import ru.fusionsoft.database.snapshot.query.pg.PgTuplesQuery;
import ru.fusionsoft.lib.collection.ListOfResultSet;

/**
 * The type of {@link Objects} that can be constructed of connection to Postgres DBMS.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class PgTuples extends ObjectsOfScalar<DbdTupleMapping> {

    /**
     * Instantiates a new Postgres tuples (UDT's) .
     * @param connection The Connection to be encapsulated.
     */
    public PgTuples(final Connection connection) {
        super(
            () -> new ListOfResultSet<>(
                rset -> new TupleOfResultSet(
                    rset,
                    connection,
                    new PgTuplesQuery(),
                    PgTupleAttributesQuery::new
                ),
                new PgTuplesQuery(),
                connection
            )
        );
    }

}
