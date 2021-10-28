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
package org.fusionsoft.database.snapshot.objects.dbms.postgres;

import java.sql.ResultSet;
import org.fusionsoft.database.snapshot.objects.dbms.IndexOfResultSet;
import org.fusionsoft.database.snapshot.query.PgIndexesQuery;

/**
 * The {@link IndexOfResultSet} with {@link PgIndexesQuery}.
 * @since 0.1
 */
public class PgIndexOfResultSet extends IndexOfResultSet {

    /**
     * Instantiates a new Postgres IndexOfResultSet.
     * @param rset The ResultSet to be encapsulated.
     */
    public PgIndexOfResultSet(final ResultSet rset) {
        super(rset, new PgIndexesQuery());
    }

}
