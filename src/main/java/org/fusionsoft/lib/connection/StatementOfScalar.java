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
 *
 */
package org.fusionsoft.lib.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;

public class StatementOfScalar implements Statement {

    private final Unchecked<Statement> scalar;

    public StatementOfScalar(final Scalar<Statement> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public ResultSet executeQuery(final String sql) throws SQLException {
        return this.scalar.value().executeQuery(sql);
    }

    @Override
    public int executeUpdate(final String sql) throws SQLException {
        return this.scalar.value().executeUpdate(sql);
    }

    @Override
    public void close() throws SQLException {
        this.scalar.value().close();
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return this.scalar.value().getMaxFieldSize();
    }

    @Override
    public void setMaxFieldSize(final int max) throws SQLException {
        this.scalar.value().setMaxFieldSize(max);
    }

    @Override
    public int getMaxRows() throws SQLException {
        return this.scalar.value().getMaxRows();
    }

    @Override
    public void setMaxRows(final int max) throws SQLException {
        this.scalar.value().setMaxRows(max);
    }

    @Override
    public void setEscapeProcessing(final boolean enable) throws SQLException {
        this.scalar.value().setEscapeProcessing(enable);
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return this.scalar.value().getQueryTimeout();
    }

    @Override
    public void setQueryTimeout(final int seconds) throws SQLException {
        this.scalar.value().setQueryTimeout(seconds);
    }

    @Override
    public void cancel() throws SQLException {
        this.scalar.value().cancel();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return this.scalar.value().getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        this.scalar.value().clearWarnings();
    }

    @Override
    public void setCursorName(final String name) throws SQLException {
        this.scalar.value().setCursorName(name);
    }

    @Override
    public boolean execute(final String sql) throws SQLException {
        return this.scalar.value().execute(sql);
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return this.scalar.value().getResultSet();
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return this.scalar.value().getUpdateCount();
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return this.scalar.value().getMoreResults();
    }

    @Override
    public void setFetchDirection(final int direction) throws SQLException {
        this.scalar.value().setFetchDirection(direction);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return this.scalar.value().getFetchDirection();
    }

    @Override
    public void setFetchSize(final int rows) throws SQLException {
        this.scalar.value().setFetchSize(rows);
    }

    @Override
    public int getFetchSize() throws SQLException {
        return this.scalar.value().getFetchSize();
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return this.scalar.value().getResultSetConcurrency();
    }

    @Override
    public int getResultSetType() throws SQLException {
        return this.scalar.value().getResultSetType();
    }

    @Override
    public void addBatch(final String sql) throws SQLException {
        this.scalar.value().addBatch(sql);
    }

    @Override
    public void clearBatch() throws SQLException {
        this.scalar.value().clearBatch();
    }

    @Override
    public int[] executeBatch() throws SQLException {
        return this.scalar.value().executeBatch();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.scalar.value().getConnection();
    }

    @Override
    public boolean getMoreResults(final int current) throws SQLException {
        return this.scalar.value().getMoreResults();
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return this.scalar.value().getGeneratedKeys();
    }

    @Override
    public int executeUpdate(final String sql, final int autoGeneratedKeys) throws SQLException {
        return this.scalar.value().executeUpdate(sql, autoGeneratedKeys);
    }

    @Override
    public int executeUpdate(final String sql, final int[] columnIndexes) throws SQLException {
        return this.scalar.value().executeUpdate(sql, columnIndexes);
    }

    @Override
    public int executeUpdate(final String sql, final String[] columnNames) throws SQLException {
        return this.scalar.value().executeUpdate(sql, columnNames);
    }

    @Override
    public boolean execute(final String sql, final int autoGeneratedKeys) throws SQLException {
        return this.scalar.value().execute(sql, autoGeneratedKeys);
    }

    @Override
    public boolean execute(final String sql, final int[] columnIndexes) throws SQLException {
        return this.scalar.value().execute(sql, columnIndexes);
    }

    @Override
    public boolean execute(final String sql, final String[] columnNames) throws SQLException {
        return this.scalar.value().execute(sql, columnNames);
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return this.scalar.value().getResultSetHoldability();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return this.scalar.value().isClosed();
    }

    @Override
    public void setPoolable(final boolean poolable) throws SQLException {
        this.scalar.value().setPoolable(poolable);
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return this.scalar.value().isPoolable();
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        this.scalar.value().closeOnCompletion();
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return this.scalar.value().isCloseOnCompletion();
    }

    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        return this.scalar.value().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return this.scalar.value().isWrapperFor(iface);
    }

}
