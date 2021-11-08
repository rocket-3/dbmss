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
 * @param <T>  The type parameter.
 * @since 0.1
 */
public class ListOfConnection<T> extends ListOfResultSet<T> {

    /**
     * Instantiates a new List of connection.
     * @param func The {@link BiFunc}({@link ResultSet}, {@link Query} of {@link F})
     *  -> returns {@link T} to be used.
     * @param connection The {@link Connection} to be encapsulated.
     * @param query The Query of {@link F} to be encapsulated.
     * @param <F> The subtype of {@link Text} for {@link Query} outcomes.
     */
    public <F extends Text> ListOfConnection(
        final BiFunc<ResultSet, Query<F>, T> func,
        final Connection connection,
        final Query<F> query
    ) {
        super(
            rset -> func.apply(rset, query),
            query,
            connection
        );
    }

}
