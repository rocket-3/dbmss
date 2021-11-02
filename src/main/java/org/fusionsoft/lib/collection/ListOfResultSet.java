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
import java.util.ArrayList;
import org.cactoos.BiFunc;
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.iterable.IterableOf;
import org.cactoos.list.ListEnvelope;
import org.cactoos.list.ListOf;
import org.fusionsoft.database.snapshot.query.Query;

/**
 * The type of {@link java.util.List} of some type
 *  that can be constructed of {@link ResultSet} and transform function.
 * @param <T> The type parameter.
 * @since 0.1
 */
public class ListOfResultSet<T> extends ListEnvelope<T> {

    /**
     * Ctor.
     * @param func The Func of ResultSet ret T to be encapsulated.
     * @param rset The Scalar of ResultSet to be encapsulated.
     */
    public ListOfResultSet(final Func<ResultSet, T> func, final Scalar<ResultSet> rset) {
        super(
            new ListOf<T>(
                new IterableOf<T>(
                    () -> {
                        final ArrayList<T> list = new ArrayList<>(1);
                        try (ResultSet resultSet = rset.value()) {
                            while (resultSet.next()) {
                                list.add(func.apply(resultSet));
                            }
                        }
                        return list.iterator();
                    }
                )
            )
        );
    }

    /**
     * Ctor.
     * @param func The BiFunc of ResultSet, Connection -> T to be encapsulated.
     * @param rset The Scalar of ResultSet to be encapsulated.
     * @param connection The Connection to be encapsulated.
     */
    public ListOfResultSet(
        final BiFunc<ResultSet, Connection, T> func,
        final Scalar<ResultSet> rset,
        final Connection connection
    ) {
        this(
            rs -> func.apply(rs, connection),
            rset
        );
    }

    /**
     * Ctor.
     * @param func The Func of ResultSet -> T to be encapsulated.
     * @param query The Query to be encapsulated.
     * @param connection The Connection to be encapsulated.
     */
    public ListOfResultSet(
        final Func<ResultSet, T> func,
        final Query<?> query,
        final Connection connection
    ) {
        this(
            func,
            () -> connection.createStatement().executeQuery(
                query.asString()
            )
        );
    }

    /**
     * Ctor.
     * @param func The BiFunc of ResultSet, Connection -> T to be encapsulated.
     * @param query The Query to be encapsulated.
     * @param connection The Connection to be encapsulated.
     */
    public ListOfResultSet(
        final BiFunc<ResultSet, Connection, T> func,
        final Query<?> query,
        final Connection connection
    ) {
        this(
            func,
            () -> connection.createStatement().executeQuery(
                query.asString()
            ),
            connection
        );
    }

}
