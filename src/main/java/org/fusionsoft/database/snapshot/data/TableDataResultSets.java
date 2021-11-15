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
package org.fusionsoft.database.snapshot.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import org.cactoos.Scalar;
import org.cactoos.func.UncheckedFunc;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.ScalarOf;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;
import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.query.DataQuery;

public class TableDataResultSets extends IterableEnvelope<ResultSet> implements AutoCloseable {

    private final Sticky<Statement> stmt;

    private final Sticky<ResultSet> rset;

    public TableDataResultSets(
        final Sticky<Statement> stmt,
        final Sticky<ResultSet> rset,
        final Scalar<Iterator<? extends ResultSet>> iterator
    ) {
        super(new IterableOf<>(iterator));
        this.stmt = stmt;
        this.rset = rset;
    }

    public TableDataResultSets(
        final Sticky<Statement> stmt,
        final Sticky<ResultSet> rset,
        final Number rows
    ) {
        this(
            stmt,
            rset,
            new ScalarOf<>(() -> {
                final Unchecked<Long> all = new Unchecked<>(new Sticky<>(rows::longValue));
                final Unchecked<ResultSet> data = new Unchecked<>(rset);
                final Long[] row = {0L};
                return new Iterator<ResultSet>() {
                    @Override
                    public boolean hasNext() {
                        return row[0] <= all.value();
                    }

                    @Override
                    public ResultSet next() {
                        return new UncheckedFunc<ResultSet, ResultSet>(
                            previous -> {
                                previous.next();
                                row[0]++;
                                return previous;
                            }
                        ).apply(data.value());
                    }
                };
            })
        );
    }

    public TableDataResultSets(
        final DataQuery query,
        final Scalar<Statement> stmt,
        final Number rows
    ) {
        this(
            new Sticky<Statement>(stmt),
            new Sticky<ResultSet>(() -> stmt.value().executeQuery(query.asString())),
            rows
        );
    }

    public TableDataResultSets(
        final Connection connection,
        final DbObject<DbdTableMapping> table
    ) {
        this(
            new DataQuery(table),
            () -> {
                final Statement stmt = connection.createStatement();
                stmt.setFetchSize(5000);
                return stmt;
            },
            new RowsCount(table, connection)
        );
    }

    @Override
    public void close() throws Exception {
        this.rset.value().close();
        this.stmt.value().close();
    }

}
