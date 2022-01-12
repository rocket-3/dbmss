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
package ru.fusionsoft.database.snapshot.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import org.cactoos.Scalar;
import org.cactoos.func.UncheckedFunc;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.Sticky;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.query.DataQuery;
import ru.fusionsoft.lib.collection.IterableAutoCloseable;
import ru.fusionsoft.lib.connection.ResultSetOfScalar;
import ru.fusionsoft.lib.connection.StatementOfScalar;

/**
 * The {@link IterableAutoCloseable} of {@link Row}, can be constructed
 *  of {@link Connection} and {@link DbObject} of {@link DbdTableMapping}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 */
public class RowsOfTable extends IterableAutoCloseable<Row> {

    /**
     * Instantiates a new Rows of table.
     * @param stmt The {@link Statement} to be encapsulated.
     * @param rset The {@link ResultSet} to be encapsulated.
     * @param iterator The {@link Scalar} of {@link Iterator} of {@link Row} to be encapsulated.
     */
    @SuppressWarnings("PMD.UnusedFormalParameter")
    private RowsOfTable(
        final Statement stmt,
        final ResultSet rset,
        final Scalar<Iterator<? extends Row>> iterator
    ) {
        super(
            new IterableOf<Row>(
                new Sticky<>(
                    iterator
                )
            ),
            stmt
        );
    }

    /**
     * Instantiates a new Rows of table.
     * @param stmt The {@link Statement} to be encapsulated.
     * @param rset The {@link ResultSet} to be encapsulated.
     * @param cols The {@link Iterable} of {@link Column} to be encapsulated.
     * @param rows The {@link Number} to be encapsulated.
     * @checkstyle ParameterNumberCheck (200 lines)
     */
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
                private final Long all = rows.longValue();
                private Long row = 0L;

                @Override
                public boolean hasNext() {
                    return this.row < this.all;
                }

                @Override
                public Row next() {
                    return new UncheckedFunc<ResultSet, Row>(
                        previous -> {
                            previous.next();
                            this.row = this.row + 1;
                            return new RowOfResultSet(this.row, previous, cols);
                        }
                    ).apply(rset);
                }
            }
        );
    }

    /**
     * Instantiates a new Rows of table.
     * @param query The {@link DataQuery} to be encapsulated.
     * @param stmt The {@link Statement} to be encapsulated.
     * @param cols The {@link Iterable} of {@link Column} to be encapsulated.
     * @param rows The {@link Number} of rows count to be encapsulated.
     * @checkstyle ParameterNumberCheck (200 lines)
     */
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

    /**
     * Instantiates a new Rows of table.
     * @param connection The {@link Connection} to be encapsulated.
     * @param table The {@link DbObject} of {@link DbdTableMapping} to be encapsulated.
     * @checkstyle MagicNumberCheck (100 lines)
     */
    public RowsOfTable(
        final Connection connection,
        final DbObject<DbdTableMapping> table
    ) {
        this(
            new DataQuery(table),
            new StatementOfScalar(
                new Sticky<>(
                    () -> {
                        connection.setAutoCommit(false);
                        final Statement stmt = connection.createStatement(
                            ResultSet.TYPE_FORWARD_ONLY,
                            ResultSet.CONCUR_READ_ONLY
                        );
                        stmt.setFetchDirection(ResultSet.FETCH_FORWARD);
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
