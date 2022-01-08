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
package org.fusionsoft.lib.connection;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;

/**
 * The Connection can be instantiated of scalar.
 * @since 0.1
 * @checkstyle ParameterNameCheck (400 lines)
 * @checkstyle LineLengthCheck (400 lines)
 * @checkstyle ParameterNumberCheck (400 lines)
 */
@SuppressWarnings("PMD")
public class ConnectionOfScalar implements Connection {

    /**
     * The scalar, returning value of to delegate everything to.
     */
    private final Unchecked<Connection> scalar;

    /**
     * Instantiates a new Connection of scalar.
     * @param scalar The scalar
     */
    public ConnectionOfScalar(final Scalar<Connection> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public final Statement createStatement() throws SQLException {
        return this.scalar.value().createStatement();
    }

    @Override
    public final PreparedStatement prepareStatement(final String sql) throws SQLException {
        return this.scalar.value().prepareStatement(sql);
    }

    @Override
    public final CallableStatement prepareCall(final String sql) throws SQLException {
        return this.scalar.value().prepareCall(sql);
    }

    @Override
    public final String nativeSQL(final String sql) throws SQLException {
        return this.scalar.value().nativeSQL(sql);
    }

    @Override
    public final void setAutoCommit(final boolean autoCommit) throws SQLException {
        this.scalar.value().setAutoCommit(autoCommit);
    }

    @Override
    public final boolean getAutoCommit() throws SQLException {
        return this.scalar.value().getAutoCommit();
    }

    @Override
    public final void commit() throws SQLException {
        this.scalar.value().commit();
    }

    @Override
    public final void rollback() throws SQLException {
        this.scalar.value().rollback();
    }

    @Override
    public final void close() throws SQLException {
        this.scalar.value().close();
    }

    @Override
    public final boolean isClosed() throws SQLException {
        return this.scalar.value().isClosed();
    }

    @Override
    public final DatabaseMetaData getMetaData() throws SQLException {
        return this.scalar.value().getMetaData();
    }

    @Override
    public final void setReadOnly(final boolean readOnly) throws SQLException {
        this.scalar.value().setReadOnly(readOnly);
    }

    @Override
    public final boolean isReadOnly() throws SQLException {
        return this.scalar.value().isReadOnly();
    }

    @Override
    public final void setCatalog(final String catalog) throws SQLException {
        this.scalar.value().setCatalog(catalog);
    }

    @Override
    public final String getCatalog() throws SQLException {
        return this.scalar.value().getCatalog();
    }

    @Override
    public final void setTransactionIsolation(final int level) throws SQLException {
        this.scalar.value().setTransactionIsolation(level);
    }

    @Override
    public final int getTransactionIsolation() throws SQLException {
        return this.scalar.value().getTransactionIsolation();
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
    public final Statement createStatement(final int resultSetType, final int resultSetConcurrency) throws SQLException {
        return this.scalar.value().createStatement(
            resultSetType,
            resultSetConcurrency
        );
    }

    @Override
    public final PreparedStatement prepareStatement(final String sql, final int resultSetType, final int resultSetConcurrency) throws SQLException {
        return this.scalar.value().prepareStatement(
            sql,
            resultSetType,
            resultSetConcurrency
        );
    }

    @Override
    public final CallableStatement prepareCall(final String sql, final int resultSetType, final int resultSetConcurrency) throws SQLException {
        return this.scalar.value().prepareCall(
            sql,
            resultSetType,
            resultSetConcurrency
        );
    }

    @Override
    public final Map<String, Class<?>> getTypeMap() throws SQLException {
        return this.scalar.value().getTypeMap();
    }

    @Override
    public final void setTypeMap(final Map<String, Class<?>> map) throws SQLException {
        this.scalar.value().setTypeMap(map);
    }

    @Override
    public final void setHoldability(final int holdability) throws SQLException {
        this.scalar.value().setHoldability(holdability);
    }

    @Override
    public final int getHoldability() throws SQLException {
        return this.scalar.value().getHoldability();
    }

    @Override
    public final Savepoint setSavepoint() throws SQLException {
        return this.scalar.value().setSavepoint();
    }

    @Override
    public final Savepoint setSavepoint(final String name) throws SQLException {
        return this.scalar.value().setSavepoint(name);
    }

    @Override
    public final void rollback(final Savepoint savepoint) throws SQLException {
        this.scalar.value().rollback();
    }

    @Override
    public final void releaseSavepoint(final Savepoint savepoint) throws SQLException {
        this.scalar.value().releaseSavepoint(savepoint);
    }

    @Override
    public final Statement createStatement(final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability) throws SQLException {
        return this.scalar.value().createStatement(
            resultSetType,
            resultSetConcurrency,
            resultSetHoldability
        );
    }

    @Override
    public final PreparedStatement prepareStatement(final String sql, final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability) throws SQLException {
        return this.scalar.value().prepareStatement(
            sql,
            resultSetType,
            resultSetConcurrency,
            resultSetHoldability
        );
    }

    @Override
    public final CallableStatement prepareCall(final String sql, final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability) throws SQLException {
        return this.scalar.value().prepareCall(
            sql,
            resultSetType,
            resultSetConcurrency,
            resultSetHoldability
        );
    }

    @Override
    public final PreparedStatement prepareStatement(final String sql, final int autoGeneratedKeys) throws SQLException {
        return this.scalar.value().prepareStatement(sql, autoGeneratedKeys);
    }

    @Override
    public final PreparedStatement prepareStatement(final String sql, final int[] columnIndexes) throws SQLException {
        return this.scalar.value().prepareStatement(sql, columnIndexes);
    }

    @Override
    public final PreparedStatement prepareStatement(final String sql, final String[] columnNames) throws SQLException {
        return this.scalar.value().prepareStatement(sql, columnNames);
    }

    @Override
    public final Clob createClob() throws SQLException {
        return this.scalar.value().createClob();
    }

    @Override
    public final Blob createBlob() throws SQLException {
        return this.scalar.value().createBlob();
    }

    @Override
    public final NClob createNClob() throws SQLException {
        return this.scalar.value().createNClob();
    }

    @Override
    public final SQLXML createSQLXML() throws SQLException {
        return this.scalar.value().createSQLXML();
    }

    @Override
    public final boolean isValid(final int timeout) throws SQLException {
        return this.scalar.value().isValid(timeout);
    }

    @Override
    public final void setClientInfo(final String name, final String value) throws SQLClientInfoException {
        this.scalar.value().setClientInfo(name, value);
    }

    @Override
    public final void setClientInfo(final Properties properties) throws SQLClientInfoException {
        this.scalar.value().setClientInfo(properties);
    }

    @Override
    public final String getClientInfo(final String name) throws SQLException {
        return this.scalar.value().getClientInfo(name);
    }

    @Override
    public final Properties getClientInfo() throws SQLException {
        return this.scalar.value().getClientInfo();
    }

    @Override
    public final Array createArrayOf(final String typeName, final Object[] elements) throws SQLException {
        return this.scalar.value().createArrayOf(typeName, elements);
    }

    @Override
    public final Struct createStruct(final String typeName, final Object[] attributes) throws SQLException {
        return this.scalar.value().createStruct(typeName, attributes);
    }

    @Override
    public final void setSchema(final String schema) throws SQLException {
        this.scalar.value().setSchema(schema);
    }

    @Override
    public final String getSchema() throws SQLException {
        return this.scalar.value().getSchema();
    }

    @Override
    public final void abort(final Executor executor) throws SQLException {
        this.scalar.value().abort(executor);
    }

    @Override
    public final void setNetworkTimeout(final Executor executor, final int milliseconds) throws SQLException {
        this.scalar.value().setNetworkTimeout(executor, milliseconds);
    }

    @Override
    public final int getNetworkTimeout() throws SQLException {
        return this.scalar.value().getNetworkTimeout();
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
