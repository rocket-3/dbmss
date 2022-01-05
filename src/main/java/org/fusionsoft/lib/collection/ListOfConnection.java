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
package org.fusionsoft.lib.collection;

import java.sql.Connection;
import java.sql.ResultSet;
import org.cactoos.BiFunc;
import org.cactoos.Text;
import org.fusionsoft.database.snapshot.query.Query;

/**
 * The type of {@link java.util.List} with some {@link T} type instances
 *  from {@link Connection}, {@link Query}
 *  and {@link BiFunc} of {@link ResultSet} returns {@link T}.
 * @param <T> The outcome type parameter.
 * @since 0.1
 */
public class ListOfConnection<T> extends ListOfResultSet<T> {

    /**
     * Instantiates a new List of connection.
     * @param connection The {@link Connection} to be encapsulated.
     * @param query The Query of {@link F} to be encapsulated.
     * @param func The unwrap {@link BiFunc} to be used.
     */
    public <F extends Text> ListOfConnection(
        final Connection connection,
        final Query<F> query,
        final BiFunc<ResultSet, Query<F>, T> func
    ) {
        super(
            rset -> func.apply(rset, query),
            query,
            connection
        );
    }

}
