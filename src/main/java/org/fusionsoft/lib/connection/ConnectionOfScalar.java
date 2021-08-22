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

public class ConnectionOfScalar implements Connection {

    private final Unchecked<Connection> scalar;

    public ConnectionOfScalar(final Scalar<Connection> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public Statement createStatement() throws SQLException {
        return this.scalar.value().createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(final String sql) throws SQLException {
        return this.scalar.value().prepareStatement(sql);
    }

    @Override
    public CallableStatement prepareCall(final String sql) throws SQLException {
        return this.scalar.value().prepareCall(sql);
    }

    @Override
    public String nativeSQL(final String sql) throws SQLException {
        return this.scalar.value().nativeSQL(sql);
    }

    @Override
    public void setAutoCommit(final boolean autoCommit) throws SQLException {
        this.scalar.value().setAutoCommit(autoCommit);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return this.scalar.value().getAutoCommit();
    }

    @Override
    public void commit() throws SQLException {
        this.scalar.value().commit();
    }

    @Override
    public void rollback() throws SQLException {
        this.scalar.value().rollback();
    }

    @Override
    public void close() throws SQLException {
        this.scalar.value().close();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return this.scalar.value().isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return this.scalar.value().getMetaData();
    }

    @Override
    public void setReadOnly(final boolean readOnly) throws SQLException {
        this.scalar.value().setReadOnly(readOnly);
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return this.scalar.value().isReadOnly();
    }

    @Override
    public void setCatalog(final String catalog) throws SQLException {
        this.scalar.value().setCatalog(catalog);
    }

    @Override
    public String getCatalog() throws SQLException {
        return this.scalar.value().getCatalog();
    }

    @Override
    public void setTransactionIsolation(final int level) throws SQLException {
        this.scalar.value().setTransactionIsolation(level);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return this.scalar.value().getTransactionIsolation();
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
    public Statement createStatement(final int resultSetType, final int resultSetConcurrency) throws SQLException {
        return this.scalar.value().createStatement(
            resultSetType,
            resultSetConcurrency
        );
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final int resultSetType, final int resultSetConcurrency) throws SQLException {
        return this.scalar.value().prepareStatement(
            sql,
            resultSetType,
            resultSetConcurrency
        );
    }

    @Override
    public CallableStatement prepareCall(final String sql, final int resultSetType, final int resultSetConcurrency) throws SQLException {
        return this.scalar.value().prepareCall(
            sql,
            resultSetType,
            resultSetConcurrency
        );
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return this.scalar.value().getTypeMap();
    }

    @Override
    public void setTypeMap(final Map<String, Class<?>> map) throws SQLException {
        this.scalar.value().setTypeMap(map);
    }

    @Override
    public void setHoldability(final int holdability) throws SQLException {
        this.scalar.value().setHoldability(holdability);
    }

    @Override
    public int getHoldability() throws SQLException {
        return this.scalar.value().getHoldability();
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return this.scalar.value().setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(final String name) throws SQLException {
        return this.scalar.value().setSavepoint(name);
    }

    @Override
    public void rollback(final Savepoint savepoint) throws SQLException {
        this.scalar.value().rollback();
    }

    @Override
    public void releaseSavepoint(final Savepoint savepoint) throws SQLException {
        this.scalar.value().releaseSavepoint(savepoint);
    }

    @Override
    public Statement createStatement(final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability) throws SQLException {
        return this.scalar.value().createStatement(
            resultSetType,
            resultSetConcurrency,
            resultSetHoldability
        );
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability) throws SQLException {
        return this.scalar.value().prepareStatement(
            sql,
            resultSetType,
            resultSetConcurrency,
            resultSetHoldability
        );
    }

    @Override
    public CallableStatement prepareCall(final String sql, final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability) throws SQLException {
        return this.scalar.value().prepareCall(
            sql,
            resultSetType,
            resultSetConcurrency,
            resultSetHoldability
        );
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final int autoGeneratedKeys) throws SQLException {
        return this.scalar.value().prepareStatement(sql, autoGeneratedKeys);
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final int[] columnIndexes) throws SQLException {
        return this.scalar.value().prepareStatement(sql, columnIndexes);
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final String[] columnNames) throws SQLException {
        return this.scalar.value().prepareStatement(sql, columnNames);
    }

    @Override
    public Clob createClob() throws SQLException {
        return this.scalar.value().createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        return this.scalar.value().createBlob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return this.scalar.value().createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return this.scalar.value().createSQLXML();
    }

    @Override
    public boolean isValid(final int timeout) throws SQLException {
        return this.scalar.value().isValid(timeout);
    }

    @Override
    public void setClientInfo(final String name, final String value) throws SQLClientInfoException {
        this.scalar.value().setClientInfo(name, value);
    }

    @Override
    public void setClientInfo(final Properties properties) throws SQLClientInfoException {
        this.scalar.value().setClientInfo(properties);
    }

    @Override
    public String getClientInfo(final String name) throws SQLException {
        return this.scalar.value().getClientInfo(name);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return this.scalar.value().getClientInfo();
    }

    @Override
    public Array createArrayOf(final String typeName, final Object[] elements) throws SQLException {
        return this.scalar.value().createArrayOf(typeName, elements);
    }

    @Override
    public Struct createStruct(final String typeName, final Object[] attributes) throws SQLException {
        return this.scalar.value().createStruct(typeName, attributes);
    }

    @Override
    public void setSchema(final String schema) throws SQLException {
        this.scalar.value().setSchema(schema);
    }

    @Override
    public String getSchema() throws SQLException {
        return this.scalar.value().getSchema();
    }

    @Override
    public void abort(final Executor executor) throws SQLException {
        this.scalar.value().abort(executor);
    }

    @Override
    public void setNetworkTimeout(final Executor executor, final int milliseconds) throws SQLException {
        this.scalar.value().setNetworkTimeout(executor, milliseconds);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return this.scalar.value().getNetworkTimeout();
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
