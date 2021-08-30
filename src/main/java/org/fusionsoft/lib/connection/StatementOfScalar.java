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
package org.fusionsoft.lib.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;

/**
 * The Statement can be instantiated from Scalar.
 * @see Scalar
 * @since 0.1
 * @checkstyle ParameterNameCheck (400 lines)
 * @checkstyle LineLengthCheck (400 lines)
 */
@SuppressWarnings("PMD")
public class StatementOfScalar implements Statement {

    /**
     * The scalar to delegate everything to.
     */
    private final Unchecked<Statement> scalar;

    /**
     * Instantiates a new Statement of scalar.
     * @param scalar The scalar to delegate everything to.
     */
    public StatementOfScalar(final Scalar<Statement> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public final ResultSet executeQuery(final String sql) throws SQLException {
        return this.scalar.value().executeQuery(sql);
    }

    @Override
    public final int executeUpdate(final String sql) throws SQLException {
        return this.scalar.value().executeUpdate(sql);
    }

    @Override
    public final void close() throws SQLException {
        this.scalar.value().close();
    }

    @Override
    public final int getMaxFieldSize() throws SQLException {
        return this.scalar.value().getMaxFieldSize();
    }

    @Override
    public final void setMaxFieldSize(final int max) throws SQLException {
        this.scalar.value().setMaxFieldSize(max);
    }

    @Override
    public final int getMaxRows() throws SQLException {
        return this.scalar.value().getMaxRows();
    }

    @Override
    public final void setMaxRows(final int max) throws SQLException {
        this.scalar.value().setMaxRows(max);
    }

    @Override
    public final void setEscapeProcessing(final boolean enable) throws SQLException {
        this.scalar.value().setEscapeProcessing(enable);
    }

    @Override
    public final int getQueryTimeout() throws SQLException {
        return this.scalar.value().getQueryTimeout();
    }

    @Override
    public final void setQueryTimeout(final int seconds) throws SQLException {
        this.scalar.value().setQueryTimeout(seconds);
    }

    @Override
    public final void cancel() throws SQLException {
        this.scalar.value().cancel();
    }

    @Override
    public final SQLWarning getWarnings() throws SQLException {
        return this.scalar.value().getWarnings();
    }

    @Override
    public final void clearWarnings() throws SQLException {
        this.scalar.value().clearWarnings();
    }

    @Override
    public final void setCursorName(final String name) throws SQLException {
        this.scalar.value().setCursorName(name);
    }

    @Override
    public final boolean execute(final String sql) throws SQLException {
        return this.scalar.value().execute(sql);
    }

    @Override
    public final ResultSet getResultSet() throws SQLException {
        return this.scalar.value().getResultSet();
    }

    @Override
    public final int getUpdateCount() throws SQLException {
        return this.scalar.value().getUpdateCount();
    }

    @Override
    public final boolean getMoreResults() throws SQLException {
        return this.scalar.value().getMoreResults();
    }

    @Override
    public final void setFetchDirection(final int direction) throws SQLException {
        this.scalar.value().setFetchDirection(direction);
    }

    @Override
    public final int getFetchDirection() throws SQLException {
        return this.scalar.value().getFetchDirection();
    }

    @Override
    public final void setFetchSize(final int rows) throws SQLException {
        this.scalar.value().setFetchSize(rows);
    }

    @Override
    public final int getFetchSize() throws SQLException {
        return this.scalar.value().getFetchSize();
    }

    @Override
    public final int getResultSetConcurrency() throws SQLException {
        return this.scalar.value().getResultSetConcurrency();
    }

    @Override
    public final int getResultSetType() throws SQLException {
        return this.scalar.value().getResultSetType();
    }

    @Override
    public final void addBatch(final String sql) throws SQLException {
        this.scalar.value().addBatch(sql);
    }

    @Override
    public final void clearBatch() throws SQLException {
        this.scalar.value().clearBatch();
    }

    @Override
    public final int[] executeBatch() throws SQLException {
        return this.scalar.value().executeBatch();
    }

    @Override
    public final Connection getConnection() throws SQLException {
        return this.scalar.value().getConnection();
    }

    @Override
    public final boolean getMoreResults(final int current) throws SQLException {
        return this.scalar.value().getMoreResults();
    }

    @Override
    public final ResultSet getGeneratedKeys() throws SQLException {
        return this.scalar.value().getGeneratedKeys();
    }

    @Override
    public final int executeUpdate(final String sql, final int autoGeneratedKeys) throws SQLException {
        return this.scalar.value().executeUpdate(sql, autoGeneratedKeys);
    }

    @Override
    public final int executeUpdate(final String sql, final int[] columnIndexes) throws SQLException {
        return this.scalar.value().executeUpdate(sql, columnIndexes);
    }

    @Override
    public final int executeUpdate(final String sql, final String[] columnNames) throws SQLException {
        return this.scalar.value().executeUpdate(sql, columnNames);
    }

    @Override
    public final boolean execute(final String sql, final int autoGeneratedKeys) throws SQLException {
        return this.scalar.value().execute(sql, autoGeneratedKeys);
    }

    @Override
    public final boolean execute(final String sql, final int[] columnIndexes) throws SQLException {
        return this.scalar.value().execute(sql, columnIndexes);
    }

    @Override
    public final boolean execute(final String sql, final String[] columnNames) throws SQLException {
        return this.scalar.value().execute(sql, columnNames);
    }

    @Override
    public final int getResultSetHoldability() throws SQLException {
        return this.scalar.value().getResultSetHoldability();
    }

    @Override
    public final boolean isClosed() throws SQLException {
        return this.scalar.value().isClosed();
    }

    @Override
    public final void setPoolable(final boolean poolable) throws SQLException {
        this.scalar.value().setPoolable(poolable);
    }

    @Override
    public final boolean isPoolable() throws SQLException {
        return this.scalar.value().isPoolable();
    }

    @Override
    public final void closeOnCompletion() throws SQLException {
        this.scalar.value().closeOnCompletion();
    }

    @Override
    public final boolean isCloseOnCompletion() throws SQLException {
        return this.scalar.value().isCloseOnCompletion();
    }

    @Override
    public final <T> T unwrap(final Class<T> iface) throws SQLException {
        return this.scalar.value().unwrap(iface);
    }

    @Override
    public final boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return this.scalar.value().isWrapperFor(iface);
    }

}
