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

/**
 * The Connection can be instantiated from other connection.
 * Can be used to create Connection from composition by subtyping.
 * @since 0.1
 * @checkstyle ParameterNameCheck (400 lines)
 * @checkstyle LineLengthCheck (400 lines)
 * @checkstyle ParameterNumberCheck (400 lines)
 */
@SuppressWarnings({"PMD", "SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING"})
public class ConnectionEnvelope implements Connection {

    /**
     * The original connection to be wrapped.
     */
    private final Connection connection;

    /**
     * Instantiates a new Connection envelope.
     * @param connection The connection to be wrapped.
     */
    public ConnectionEnvelope(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public final Statement createStatement() throws SQLException {
        return this.connection.createStatement();
    }

    @Override
    public final PreparedStatement prepareStatement(final String sql) throws SQLException {
        return this.connection.prepareStatement(sql);
    }

    @Override
    public final CallableStatement prepareCall(final String sql) throws SQLException {
        return this.connection.prepareCall(sql);
    }

    @Override
    public final String nativeSQL(final String sql) throws SQLException {
        return this.connection.nativeSQL(sql);
    }

    @Override
    public final void setAutoCommit(final boolean autoCommit) throws SQLException {
        this.connection.setAutoCommit(autoCommit);
    }

    @Override
    public final boolean getAutoCommit() throws SQLException {
        return this.connection.getAutoCommit();
    }

    @Override
    public final void commit() throws SQLException {
        this.connection.commit();
    }

    @Override
    public final void rollback() throws SQLException {
        this.connection.rollback();
    }

    @Override
    public final void close() throws SQLException {
        this.connection.close();
    }

    @Override
    public final boolean isClosed() throws SQLException {
        return this.connection.isClosed();
    }

    @Override
    public final DatabaseMetaData getMetaData() throws SQLException {
        return this.connection.getMetaData();
    }

    @Override
    public final void setReadOnly(final boolean readOnly) throws SQLException {
        this.connection.setReadOnly(readOnly);
    }

    @Override
    public final boolean isReadOnly() throws SQLException {
        return this.connection.isReadOnly();
    }

    @Override
    public final void setCatalog(final String catalog) throws SQLException {
        this.connection.setCatalog(catalog);
    }

    @Override
    public final String getCatalog() throws SQLException {
        return this.connection.getCatalog();
    }

    @Override
    public final void setTransactionIsolation(final int level) throws SQLException {
        this.connection.setTransactionIsolation(level);
    }

    @Override
    public final int getTransactionIsolation() throws SQLException {
        return this.connection.getTransactionIsolation();
    }

    @Override
    public final SQLWarning getWarnings() throws SQLException {
        return this.connection.getWarnings();
    }

    @Override
    public final void clearWarnings() throws SQLException {
        this.connection.clearWarnings();
    }

    @Override
    public final Statement createStatement(final int resultSetType, final int resultSetConcurrency) throws SQLException {
        return this.connection.createStatement(
            resultSetType,
            resultSetConcurrency
        );
    }

    @Override
    public final PreparedStatement prepareStatement(final String sql, final int resultSetType, final int resultSetConcurrency) throws SQLException {
        return this.connection.prepareStatement(
            sql,
            resultSetType,
            resultSetConcurrency
        );
    }

    @Override
    public final CallableStatement prepareCall(final String sql, final int resultSetType, final int resultSetConcurrency) throws SQLException {
        return this.connection.prepareCall(
            sql,
            resultSetType,
            resultSetConcurrency
        );
    }

    @Override
    public final Map<String, Class<?>> getTypeMap() throws SQLException {
        return this.connection.getTypeMap();
    }

    @Override
    public final void setTypeMap(final Map<String, Class<?>> map) throws SQLException {
        this.connection.setTypeMap(map);
    }

    @Override
    public final void setHoldability(final int holdability) throws SQLException {
        this.connection.setHoldability(holdability);
    }

    @Override
    public final int getHoldability() throws SQLException {
        return this.connection.getHoldability();
    }

    @Override
    public final Savepoint setSavepoint() throws SQLException {
        return this.connection.setSavepoint();
    }

    @Override
    public final Savepoint setSavepoint(final String name) throws SQLException {
        return this.connection.setSavepoint(name);
    }

    @Override
    public final void rollback(final Savepoint savepoint) throws SQLException {
        this.connection.rollback();
    }

    @Override
    public final void releaseSavepoint(final Savepoint savepoint) throws SQLException {
        this.connection.releaseSavepoint(savepoint);
    }

    @Override
    public final Statement createStatement(final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability) throws SQLException {
        return this.connection.createStatement(
            resultSetType,
            resultSetConcurrency,
            resultSetHoldability
        );
    }

    @Override
    public final PreparedStatement prepareStatement(final String sql, final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability) throws SQLException {
        return this.connection.prepareStatement(
            sql,
            resultSetType,
            resultSetConcurrency,
            resultSetHoldability
        );
    }

    @Override
    public final CallableStatement prepareCall(final String sql, final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability) throws SQLException {
        return this.connection.prepareCall(
            sql,
            resultSetType,
            resultSetConcurrency,
            resultSetHoldability
        );
    }

    @Override
    public final PreparedStatement prepareStatement(final String sql, final int autoGeneratedKeys) throws SQLException {
        return this.connection.prepareStatement(sql, autoGeneratedKeys);
    }

    @Override
    public final PreparedStatement prepareStatement(final String sql, final int[] columnIndexes) throws SQLException {
        return this.connection.prepareStatement(sql, columnIndexes);
    }

    @Override
    public final PreparedStatement prepareStatement(final String sql, final String[] columnNames) throws SQLException {
        return this.connection.prepareStatement(sql, columnNames);
    }

    @Override
    public final Clob createClob() throws SQLException {
        return this.connection.createClob();
    }

    @Override
    public final Blob createBlob() throws SQLException {
        return this.connection.createBlob();
    }

    @Override
    public final NClob createNClob() throws SQLException {
        return this.connection.createNClob();
    }

    @Override
    public final SQLXML createSQLXML() throws SQLException {
        return this.connection.createSQLXML();
    }

    @Override
    public final boolean isValid(final int timeout) throws SQLException {
        return this.connection.isValid(timeout);
    }

    @Override
    public final void setClientInfo(final String name, final String value) throws SQLClientInfoException {
        this.connection.setClientInfo(name, value);
    }

    @Override
    public final void setClientInfo(final Properties properties) throws SQLClientInfoException {
        this.connection.setClientInfo(properties);
    }

    @Override
    public final String getClientInfo(final String name) throws SQLException {
        return this.connection.getClientInfo(name);
    }

    @Override
    public final Properties getClientInfo() throws SQLException {
        return this.connection.getClientInfo();
    }

    @Override
    public final Array createArrayOf(final String typeName, final Object[] elements) throws SQLException {
        return this.connection.createArrayOf(typeName, elements);
    }

    @Override
    public final Struct createStruct(final String typeName, final Object[] attributes) throws SQLException {
        return this.connection.createStruct(typeName, attributes);
    }

    @Override
    public final void setSchema(final String schema) throws SQLException {
        this.connection.setSchema(schema);
    }

    @Override
    public final String getSchema() throws SQLException {
        return this.connection.getSchema();
    }

    @Override
    public final void abort(final Executor executor) throws SQLException {
        this.connection.abort(executor);
    }

    @Override
    public final void setNetworkTimeout(final Executor executor, final int milliseconds) throws SQLException {
        this.connection.setNetworkTimeout(executor, milliseconds);
    }

    @Override
    public final int getNetworkTimeout() throws SQLException {
        return this.connection.getNetworkTimeout();
    }

    @Override
    public final <T> T unwrap(final Class<T> iface) throws SQLException {
        return this.connection.unwrap(iface);
    }

    @Override
    public final boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return this.connection.isWrapperFor(iface);
    }

}
