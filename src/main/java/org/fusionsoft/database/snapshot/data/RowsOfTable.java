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
import org.cactoos.iterable.IterableOf;
import org.cactoos.list.ListEnvelope;
import org.cactoos.list.ListOf;
import org.cactoos.scalar.Sticky;
import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.query.DataQuery;
import org.fusionsoft.lib.connection.ResultSetOfScalar;
import org.fusionsoft.lib.connection.StatementOfScalar;

public class RowsOfTable extends ListEnvelope<Row> {

    private RowsOfTable(
        final Statement stmt,
        final ResultSet rset,
        final Scalar<Iterator<? extends Row>> iterator
    ) {
        super(new ListOf<>(new IterableOf<>(iterator)));
        new UncheckedFunc<Void, Void>(
            x -> {
                rset.close();
                stmt.close();
                return null;
            }
        ).apply(null);
    }

    private RowsOfTable(
        final Statement stmt,
        final ResultSet rset,
        final Iterable<Column> cols,
        final Number rows
    ) {
        this(
            stmt,
            rset,
            () -> new Iterator<Row>() {
                final Long all = rows.longValue();

                Long row = 0L;

                @Override
                public boolean hasNext() {
                    return row < all;
                }

                @Override
                public Row next() {
                    return new UncheckedFunc<ResultSet, Row>(
                        previous -> {
                            previous.next();
                            row++;
                            return new RowOfResultSet(row, previous, cols);
                        }
                    ).apply(rset);
                }
            }
        );
    }

    private RowsOfTable(
        final DataQuery query,
        final Statement stmt,
        final Iterable<Column> cols,
        final Number rows
    ) {
        this(
            stmt,
            new ResultSetOfScalar(
                new Sticky<>(
                    () -> stmt.executeQuery(query.asString())
                )
            ),
            cols,
            rows
        );
    }

    public RowsOfTable(
        final Connection connection,
        final DbObject<DbdTableMapping> table
    ) {
        this(
            new DataQuery(table),
            new StatementOfScalar(
                new Sticky<>(
                    () -> {
                        final Statement stmt = connection.createStatement();
                        stmt.setFetchSize(5000);
                        return stmt;
                    }
                )
            ),
            new ColumnsOfTable(table),
            new RowsCount(table, connection)
        );
    }

}
