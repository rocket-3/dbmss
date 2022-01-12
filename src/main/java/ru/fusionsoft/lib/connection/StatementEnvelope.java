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
package ru.fusionsoft.lib.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

/**
 * The Statement from other statement wrapped.
 * Used to construct Statement from composition by subtyping.
 * @since 0.1
 * @checkstyle ParameterNameCheck (400 lines)
 * @checkstyle LineLengthCheck (400 lines)
 */
@SuppressWarnings("PMD")
public class StatementEnvelope implements Statement {

    /**
     * The original statement to delegate everything to.
     */
    private final Statement statement;

    /**
     * Instantiates a new Statement from other.
     * @param statement The original statement to be wrapped.
     */
    public StatementEnvelope(final Statement statement) {
        this.statement = statement;
    }

    @Override
    public final ResultSet executeQuery(final String sql) throws SQLException {
        return this.statement.executeQuery(sql);
    }

    @Override
    public final int executeUpdate(final String sql) throws SQLException {
        return this.statement.executeUpdate(sql);
    }

    @Override
    public final void close() throws SQLException {
        this.statement.close();
    }

    @Override
    public final int getMaxFieldSize() throws SQLException {
        return this.statement.getMaxFieldSize();
    }

    @Override
    public final void setMaxFieldSize(final int max) throws SQLException {
        this.statement.setMaxFieldSize(max);
    }

    @Override
    public final int getMaxRows() throws SQLException {
        return this.statement.getMaxRows();
    }

    @Override
    public final void setMaxRows(final int max) throws SQLException {
        this.statement.setMaxRows(max);
    }

    @Override
    public final void setEscapeProcessing(final boolean enable) throws SQLException {
        this.statement.setEscapeProcessing(enable);
    }

    @Override
    public final int getQueryTimeout() throws SQLException {
        return this.statement.getQueryTimeout();
    }

    @Override
    public final void setQueryTimeout(final int seconds) throws SQLException {
        this.statement.setQueryTimeout(seconds);
    }

    @Override
    public final void cancel() throws SQLException {
        this.statement.cancel();
    }

    @Override
    public final SQLWarning getWarnings() throws SQLException {
        return this.statement.getWarnings();
    }

    @Override
    public final void clearWarnings() throws SQLException {
        this.statement.clearWarnings();
    }

    @Override
    public final void setCursorName(final String name) throws SQLException {
        this.statement.setCursorName(name);
    }

    @Override
    public final boolean execute(final String sql) throws SQLException {
        return this.statement.execute(sql);
    }

    @Override
    public final ResultSet getResultSet() throws SQLException {
        return this.statement.getResultSet();
    }

    @Override
    public final int getUpdateCount() throws SQLException {
        return this.statement.getUpdateCount();
    }

    @Override
    public final boolean getMoreResults() throws SQLException {
        return this.statement.getMoreResults();
    }

    @Override
    public final void setFetchDirection(final int direction) throws SQLException {
        this.statement.setFetchDirection(direction);
    }

    @Override
    public final int getFetchDirection() throws SQLException {
        return this.statement.getFetchDirection();
    }

    @Override
    public final void setFetchSize(final int rows) throws SQLException {
        this.statement.setFetchSize(rows);
    }

    @Override
    public final int getFetchSize() throws SQLException {
        return this.statement.getFetchSize();
    }

    @Override
    public final int getResultSetConcurrency() throws SQLException {
        return this.statement.getResultSetConcurrency();
    }

    @Override
    public final int getResultSetType() throws SQLException {
        return this.statement.getResultSetType();
    }

    @Override
    public final void addBatch(final String sql) throws SQLException {
        this.statement.addBatch(sql);
    }

    @Override
    public final void clearBatch() throws SQLException {
        this.statement.clearBatch();
    }

    @Override
    public final int[] executeBatch() throws SQLException {
        return this.statement.executeBatch();
    }

    @Override
    public final Connection getConnection() throws SQLException {
        return this.statement.getConnection();
    }

    @Override
    public final boolean getMoreResults(final int current) throws SQLException {
        return this.statement.getMoreResults();
    }

    @Override
    public final ResultSet getGeneratedKeys() throws SQLException {
        return this.statement.getGeneratedKeys();
    }

    @Override
    public final int executeUpdate(final String sql, final int autoGeneratedKeys) throws SQLException {
        return this.statement.executeUpdate(sql, autoGeneratedKeys);
    }

    @Override
    public final int executeUpdate(final String sql, final int[] columnIndexes) throws SQLException {
        return this.statement.executeUpdate(sql, columnIndexes);
    }

    @Override
    public final int executeUpdate(final String sql, final String[] columnNames) throws SQLException {
        return this.statement.executeUpdate(sql, columnNames);
    }

    @Override
    public final boolean execute(final String sql, final int autoGeneratedKeys) throws SQLException {
        return this.statement.execute(sql, autoGeneratedKeys);
    }

    @Override
    public final boolean execute(final String sql, final int[] columnIndexes) throws SQLException {
        return this.statement.execute(sql, columnIndexes);
    }

    @Override
    public final boolean execute(final String sql, final String[] columnNames) throws SQLException {
        return this.statement.execute(sql, columnNames);
    }

    @Override
    public final int getResultSetHoldability() throws SQLException {
        return this.statement.getResultSetHoldability();
    }

    @Override
    public final boolean isClosed() throws SQLException {
        return this.statement.isClosed();
    }

    @Override
    public final void setPoolable(final boolean poolable) throws SQLException {
        this.statement.setPoolable(poolable);
    }

    @Override
    public final boolean isPoolable() throws SQLException {
        return this.statement.isPoolable();
    }

    @Override
    public final void closeOnCompletion() throws SQLException {
        this.statement.closeOnCompletion();
    }

    @Override
    public final boolean isCloseOnCompletion() throws SQLException {
        return this.statement.isCloseOnCompletion();
    }

    @Override
    public final <T> T unwrap(final Class<T> iface) throws SQLException {
        return this.statement.unwrap(iface);
    }

    @Override
    public final boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return this.statement.isWrapperFor(iface);
    }

}
