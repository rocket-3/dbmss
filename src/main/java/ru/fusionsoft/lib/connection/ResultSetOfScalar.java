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

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;

/**
 * {@link ResultSet} implementation of {@link Scalar}.
 * @since 0.1
 * @checkstyle ParameterNameCheck (4000 lines)
 * @checkstyle LineLengthCheck (4000 lines)
 * @checkstyle ParameterNumberCheck (4000 lines)
 * @checkstyle JavadocStyleCheck (4000 lines)
 * @checkstyle MethodCountCheck (4000 lines)
 * @checkstyle MissingDeprecatedCheck (4000 lines)
 */
@SuppressWarnings("PMD")
public class ResultSetOfScalar implements ResultSet {

    /**
     * The {@link Unchecked} {@link Scalar} of {@link ResultSet} encapsulated.
     */
    private final Unchecked<ResultSet> scalar;

    /**
     * Instantiates a new Result set of scalar.
     * @param scalar The {@link Scalar} of {@link ResultSet} to be encapsulated.
     */
    public ResultSetOfScalar(final Scalar<ResultSet> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public final boolean next() throws SQLException {
        return this.scalar.value().next();
    }

    @Override
    public final void close() throws SQLException {
        this.scalar.value().close();
    }

    @Override
    public final boolean wasNull() throws SQLException {
        return this.scalar.value().wasNull();
    }

    @Override
    public final String getString(final int columnIndex) throws SQLException {
        return this.scalar.value().getString(columnIndex);
    }

    @Override
    public final boolean getBoolean(final int columnIndex) throws SQLException {
        return this.scalar.value().getBoolean(columnIndex);
    }

    @Override
    public final byte getByte(final int columnIndex) throws SQLException {
        return this.scalar.value().getByte(columnIndex);
    }

    @Override
    public final short getShort(final int columnIndex) throws SQLException {
        return this.scalar.value().getShort(columnIndex);
    }

    @Override
    public final int getInt(final int columnIndex) throws SQLException {
        return this.scalar.value().getInt(columnIndex);
    }

    @Override
    public final long getLong(final int columnIndex) throws SQLException {
        return this.scalar.value().getLong(columnIndex);
    }

    @Override
    public final float getFloat(final int columnIndex) throws SQLException {
        return this.scalar.value().getFloat(columnIndex);
    }

    @Override
    public final double getDouble(final int columnIndex) throws SQLException {
        return this.scalar.value().getDouble(columnIndex);
    }

    @Override
    @Deprecated
    public final BigDecimal getBigDecimal(final int columnIndex, final int scale) throws SQLException {
        return this.scalar.value().getBigDecimal(columnIndex, scale);
    }

    @Override
    public final byte[] getBytes(final int columnIndex) throws SQLException {
        return this.scalar.value().getBytes(columnIndex);
    }

    @Override
    public final Date getDate(final int columnIndex) throws SQLException {
        return this.scalar.value().getDate(columnIndex);
    }

    @Override
    public final Time getTime(final int columnIndex) throws SQLException {
        return this.scalar.value().getTime(columnIndex);
    }

    @Override
    public final Timestamp getTimestamp(final int columnIndex) throws SQLException {
        return this.scalar.value().getTimestamp(columnIndex);
    }

    @Override
    public final InputStream getAsciiStream(final int columnIndex) throws SQLException {
        return this.scalar.value().getAsciiStream(columnIndex);
    }

    @Override
    @Deprecated
    public final InputStream getUnicodeStream(final int columnIndex) throws SQLException {
        return this.scalar.value().getUnicodeStream(columnIndex);
    }

    @Override
    public final InputStream getBinaryStream(final int columnIndex) throws SQLException {
        return this.scalar.value().getBinaryStream(columnIndex);
    }

    @Override
    public final String getString(final String columnLabel) throws SQLException {
        return this.scalar.value().getString(columnLabel);
    }

    @Override
    public final boolean getBoolean(final String columnLabel) throws SQLException {
        return this.scalar.value().getBoolean(columnLabel);
    }

    @Override
    public final byte getByte(final String columnLabel) throws SQLException {
        return this.scalar.value().getByte(columnLabel);
    }

    @Override
    public final short getShort(final String columnLabel) throws SQLException {
        return this.scalar.value().getShort(columnLabel);
    }

    @Override
    public final int getInt(final String columnLabel) throws SQLException {
        return this.scalar.value().getInt(columnLabel);
    }

    @Override
    public final long getLong(final String columnLabel) throws SQLException {
        return this.scalar.value().getLong(columnLabel);
    }

    @Override
    public final float getFloat(final String columnLabel) throws SQLException {
        return this.scalar.value().getFloat(columnLabel);
    }

    @Override
    public final double getDouble(final String columnLabel) throws SQLException {
        return this.scalar.value().getDouble(columnLabel);
    }

    @Override
    @Deprecated
    public final BigDecimal getBigDecimal(final String columnLabel, final int scale) throws SQLException {
        return this.scalar.value().getBigDecimal(columnLabel, scale);
    }

    @Override
    public final byte[] getBytes(final String columnLabel) throws SQLException {
        return this.scalar.value().getBytes(columnLabel);
    }

    @Override
    public final Date getDate(final String columnLabel) throws SQLException {
        return this.scalar.value().getDate(columnLabel);
    }

    @Override
    public final Time getTime(final String columnLabel) throws SQLException {
        return this.scalar.value().getTime(columnLabel);
    }

    @Override
    public final Timestamp getTimestamp(final String columnLabel) throws SQLException {
        return this.scalar.value().getTimestamp(columnLabel);
    }

    @Override
    public final InputStream getAsciiStream(final String columnLabel) throws SQLException {
        return this.scalar.value().getAsciiStream(columnLabel);
    }

    @Override
    @Deprecated
    public final InputStream getUnicodeStream(final String columnLabel) throws SQLException {
        return this.scalar.value().getUnicodeStream(columnLabel);
    }

    @Override
    public final InputStream getBinaryStream(final String columnLabel) throws SQLException {
        return this.scalar.value().getBinaryStream(columnLabel);
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
    public final String getCursorName() throws SQLException {
        return this.scalar.value().getCursorName();
    }

    @Override
    public final ResultSetMetaData getMetaData() throws SQLException {
        return this.scalar.value().getMetaData();
    }

    @Override
    public final Object getObject(final int columnIndex) throws SQLException {
        return this.scalar.value().getObject(columnIndex);
    }

    @Override
    public final Object getObject(final String columnLabel) throws SQLException {
        return this.scalar.value().getObject(columnLabel);
    }

    @Override
    public final int findColumn(final String columnLabel) throws SQLException {
        return this.scalar.value().findColumn(columnLabel);
    }

    @Override
    public final Reader getCharacterStream(final int columnIndex) throws SQLException {
        return this.scalar.value().getCharacterStream(columnIndex);
    }

    @Override
    public final Reader getCharacterStream(final String columnLabel) throws SQLException {
        return this.scalar.value().getCharacterStream(columnLabel);
    }

    @Override
    public final BigDecimal getBigDecimal(final int columnIndex) throws SQLException {
        return this.scalar.value().getBigDecimal(columnIndex);
    }

    @Override
    public final BigDecimal getBigDecimal(final String columnLabel) throws SQLException {
        return this.scalar.value().getBigDecimal(columnLabel);
    }

    @Override
    public final boolean isBeforeFirst() throws SQLException {
        return this.scalar.value().isBeforeFirst();
    }

    @Override
    public final boolean isAfterLast() throws SQLException {
        return this.scalar.value().isAfterLast();
    }

    @Override
    public final boolean isFirst() throws SQLException {
        return this.scalar.value().isFirst();
    }

    @Override
    public final boolean isLast() throws SQLException {
        return this.scalar.value().isLast();
    }

    @Override
    public final void beforeFirst() throws SQLException {
        this.scalar.value().beforeFirst();
    }

    @Override
    public final void afterLast() throws SQLException {
        this.scalar.value().afterLast();
    }

    @Override
    public final boolean first() throws SQLException {
        return this.scalar.value().first();
    }

    @Override
    public final boolean last() throws SQLException {
        return this.scalar.value().last();
    }

    @Override
    public final int getRow() throws SQLException {
        return this.scalar.value().getRow();
    }

    @Override
    public final boolean absolute(final int row) throws SQLException {
        return this.scalar.value().absolute(row);
    }

    @Override
    public final boolean relative(final int rows) throws SQLException {
        return this.scalar.value().relative(rows);
    }

    @Override
    public final boolean previous() throws SQLException {
        return this.scalar.value().previous();
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
    public final int getType() throws SQLException {
        return this.scalar.value().getType();
    }

    @Override
    public final int getConcurrency() throws SQLException {
        return this.scalar.value().getConcurrency();
    }

    @Override
    public final boolean rowUpdated() throws SQLException {
        return this.scalar.value().rowUpdated();
    }

    @Override
    public final boolean rowInserted() throws SQLException {
        return this.scalar.value().rowInserted();
    }

    @Override
    public final boolean rowDeleted() throws SQLException {
        return this.scalar.value().rowDeleted();
    }

    @Override
    public final void updateNull(final int columnIndex) throws SQLException {
        this.scalar.value().updateNull(columnIndex);
    }

    @Override
    public final void updateBoolean(final int columnIndex, final boolean x) throws SQLException {
        this.scalar.value().updateBoolean(columnIndex, x);
    }

    @Override
    public final void updateByte(final int columnIndex, final byte x) throws SQLException {
        this.scalar.value().updateByte(columnIndex, x);
    }

    @Override
    public final void updateShort(final int columnIndex, final short x) throws SQLException {
        this.scalar.value().updateShort(columnIndex, x);
    }

    @Override
    public final void updateInt(final int columnIndex, final int x) throws SQLException {
        this.scalar.value().updateInt(columnIndex, x);
    }

    @Override
    public final void updateLong(final int columnIndex, final long x) throws SQLException {
        this.scalar.value().updateLong(columnIndex, x);
    }

    @Override
    public final void updateFloat(final int columnIndex, final float x) throws SQLException {
        this.scalar.value().updateFloat(columnIndex, x);
    }

    @Override
    public final void updateDouble(final int columnIndex, final double x) throws SQLException {
        this.scalar.value().updateDouble(columnIndex, x);
    }

    @Override
    public final void updateBigDecimal(final int columnIndex, final BigDecimal x) throws SQLException {
        this.scalar.value().updateBigDecimal(columnIndex, x);
    }

    @Override
    public final void updateString(final int columnIndex, final String x) throws SQLException {
        this.scalar.value().updateString(columnIndex, x);
    }

    @Override
    public final void updateBytes(final int columnIndex, final byte[] x) throws SQLException {
        this.scalar.value().updateBytes(columnIndex, x);
    }

    @Override
    public final void updateDate(final int columnIndex, final Date x) throws SQLException {
        this.scalar.value().updateDate(columnIndex, x);
    }

    @Override
    public final void updateTime(final int columnIndex, final Time x) throws SQLException {
        this.scalar.value().updateTime(columnIndex, x);
    }

    @Override
    public final void updateTimestamp(final int columnIndex, final Timestamp x) throws SQLException {
        this.scalar.value().updateTimestamp(columnIndex, x);
    }

    @Override
    public final void updateAsciiStream(final int columnIndex, final InputStream x, final int length) throws SQLException {
        this.scalar.value().updateAsciiStream(columnIndex, x, length);
    }

    @Override
    public final void updateBinaryStream(final int columnIndex, final InputStream x, final int length) throws SQLException {
        this.scalar.value().updateBinaryStream(columnIndex, x, length);
    }

    @Override
    public final void updateCharacterStream(final int columnIndex, final Reader x, final int length) throws SQLException {
        this.scalar.value().updateCharacterStream(columnIndex, x, length);
    }

    @Override
    public final void updateObject(final int columnIndex, final Object x, final int scaleOrLength) throws SQLException {
        this.scalar.value().updateObject(columnIndex, x, scaleOrLength);
    }

    @Override
    public final void updateObject(final int columnIndex, final Object x) throws SQLException {
        this.scalar.value().updateObject(columnIndex, x);
    }

    @Override
    public final void updateNull(final String columnLabel) throws SQLException {
        this.scalar.value().updateNull(columnLabel);
    }

    @Override
    public final void updateBoolean(final String columnLabel, final boolean x) throws SQLException {
        this.scalar.value().updateBoolean(columnLabel, x);
    }

    @Override
    public final void updateByte(final String columnLabel, final byte x) throws SQLException {
        this.scalar.value().updateByte(columnLabel, x);
    }

    @Override
    public final void updateShort(final String columnLabel, final short x) throws SQLException {
        this.scalar.value().updateShort(columnLabel, x);
    }

    @Override
    public final void updateInt(final String columnLabel, final int x) throws SQLException {
        this.scalar.value().updateInt(columnLabel, x);
    }

    @Override
    public final void updateLong(final String columnLabel, final long x) throws SQLException {
        this.scalar.value().updateLong(columnLabel, x);
    }

    @Override
    public final void updateFloat(final String columnLabel, final float x) throws SQLException {
        this.scalar.value().updateFloat(columnLabel, x);
    }

    @Override
    public final void updateDouble(final String columnLabel, final double x) throws SQLException {
        this.scalar.value().updateDouble(columnLabel, x);
    }

    @Override
    public final void updateBigDecimal(final String columnLabel, final BigDecimal x) throws SQLException {
        this.scalar.value().updateBigDecimal(columnLabel, x);
    }

    @Override
    public final void updateString(final String columnLabel, final String x) throws SQLException {
        this.scalar.value().updateString(columnLabel, x);
    }

    @Override
    public final void updateBytes(final String columnLabel, final byte[] x) throws SQLException {
        this.scalar.value().updateBytes(columnLabel, x);
    }

    @Override
    public final void updateDate(final String columnLabel, final Date x) throws SQLException {
        this.scalar.value().updateDate(columnLabel, x);
    }

    @Override
    public final void updateTime(final String columnLabel, final Time x) throws SQLException {
        this.scalar.value().updateTime(columnLabel, x);
    }

    @Override
    public final void updateTimestamp(final String columnLabel, final Timestamp x) throws SQLException {
        this.scalar.value().updateTimestamp(columnLabel, x);
    }

    @Override
    public final void updateAsciiStream(final String columnLabel, final InputStream x, final int length) throws SQLException {
        this.scalar.value().updateAsciiStream(columnLabel, x, length);
    }

    @Override
    public final void updateBinaryStream(final String columnLabel, final InputStream x, final int length) throws SQLException {
        this.scalar.value().updateBinaryStream(columnLabel, x, length);
    }

    @Override
    public final void updateCharacterStream(final String columnLabel, final Reader reader, final int length) throws SQLException {
        this.scalar.value().updateCharacterStream(columnLabel, reader, length);
    }

    @Override
    public final void updateObject(final String columnLabel, final Object x, final int scaleOrLength) throws SQLException {
        this.scalar.value().updateObject(columnLabel, x, scaleOrLength);
    }

    @Override
    public final void updateObject(final String columnLabel, final Object x) throws SQLException {
        this.scalar.value().updateObject(columnLabel, x);
    }

    @Override
    public final void insertRow() throws SQLException {
        this.scalar.value().insertRow();
    }

    @Override
    public final void updateRow() throws SQLException {
        this.scalar.value().updateRow();
    }

    @Override
    public final void deleteRow() throws SQLException {
        this.scalar.value().deleteRow();
    }

    @Override
    public final void refreshRow() throws SQLException {
        this.scalar.value().refreshRow();
    }

    @Override
    public final void cancelRowUpdates() throws SQLException {
        this.scalar.value().cancelRowUpdates();
    }

    @Override
    public final void moveToInsertRow() throws SQLException {
        this.scalar.value().moveToInsertRow();
    }

    @Override
    public final void moveToCurrentRow() throws SQLException {
        this.scalar.value().moveToCurrentRow();
    }

    @Override
    public final Statement getStatement() throws SQLException {
        return this.scalar.value().getStatement();
    }

    @Override
    public final Object getObject(final int columnIndex, final Map<String, Class<?>> map) throws SQLException {
        return this.scalar.value().getObject(columnIndex, map);
    }

    @Override
    public final Ref getRef(final int columnIndex) throws SQLException {
        return this.scalar.value().getRef(columnIndex);
    }

    @Override
    public final Blob getBlob(final int columnIndex) throws SQLException {
        return this.scalar.value().getBlob(columnIndex);
    }

    @Override
    public final Clob getClob(final int columnIndex) throws SQLException {
        return this.scalar.value().getClob(columnIndex);
    }

    @Override
    public final Array getArray(final int columnIndex) throws SQLException {
        return this.scalar.value().getArray(columnIndex);
    }

    @Override
    public final Object getObject(final String columnLabel, final Map<String, Class<?>> map) throws SQLException {
        return this.scalar.value().getObject(columnLabel, map);
    }

    @Override
    public final Ref getRef(final String columnLabel) throws SQLException {
        return this.scalar.value().getRef(columnLabel);
    }

    @Override
    public final Blob getBlob(final String columnLabel) throws SQLException {
        return this.scalar.value().getBlob(columnLabel);
    }

    @Override
    public final Clob getClob(final String columnLabel) throws SQLException {
        return this.scalar.value().getClob(columnLabel);
    }

    @Override
    public final Array getArray(final String columnLabel) throws SQLException {
        return this.scalar.value().getArray(columnLabel);
    }

    @Override
    public final Date getDate(final int columnIndex, final Calendar cal) throws SQLException {
        return this.scalar.value().getDate(columnIndex, cal);
    }

    @Override
    public final Date getDate(final String columnLabel, final Calendar cal) throws SQLException {
        return this.scalar.value().getDate(columnLabel, cal);
    }

    @Override
    public final Time getTime(final int columnIndex, final Calendar cal) throws SQLException {
        return this.scalar.value().getTime(columnIndex, cal);
    }

    @Override
    public final Time getTime(final String columnLabel, final Calendar cal) throws SQLException {
        return this.scalar.value().getTime(columnLabel, cal);
    }

    @Override
    public final Timestamp getTimestamp(final int columnIndex, final Calendar cal) throws SQLException {
        return this.scalar.value().getTimestamp(columnIndex, cal);
    }

    @Override
    public final Timestamp getTimestamp(final String columnLabel, final Calendar cal) throws SQLException {
        return this.scalar.value().getTimestamp(columnLabel, cal);
    }

    @Override
    public final URL getURL(final int columnIndex) throws SQLException {
        return this.scalar.value().getURL(columnIndex);
    }

    @Override
    public final URL getURL(final String columnLabel) throws SQLException {
        return this.scalar.value().getURL(columnLabel);
    }

    @Override
    public final void updateRef(final int columnIndex, final Ref x) throws SQLException {
        this.scalar.value().updateRef(columnIndex, x);
    }

    @Override
    public final void updateRef(final String columnLabel, final Ref x) throws SQLException {
        this.scalar.value().updateRef(columnLabel, x);
    }

    @Override
    public final void updateBlob(final int columnIndex, final Blob x) throws SQLException {
        this.scalar.value().updateBlob(columnIndex, x);
    }

    @Override
    public final void updateBlob(final String columnLabel, final Blob x) throws SQLException {
        this.scalar.value().updateBlob(columnLabel, x);
    }

    @Override
    public final void updateClob(final int columnIndex, final Clob x) throws SQLException {
        this.scalar.value().updateClob(columnIndex, x);
    }

    @Override
    public final void updateClob(final String columnLabel, final Clob x) throws SQLException {
        this.scalar.value().updateClob(columnLabel, x);
    }

    @Override
    public final void updateArray(final int columnIndex, final Array x) throws SQLException {
        this.scalar.value().updateArray(columnIndex, x);
    }

    @Override
    public final void updateArray(final String columnLabel, final Array x) throws SQLException {
        this.scalar.value().updateArray(columnLabel, x);
    }

    @Override
    public final RowId getRowId(final int columnIndex) throws SQLException {
        return this.scalar.value().getRowId(columnIndex);
    }

    @Override
    public final RowId getRowId(final String columnLabel) throws SQLException {
        return this.scalar.value().getRowId(columnLabel);
    }

    @Override
    public final void updateRowId(final int columnIndex, final RowId x) throws SQLException {
        this.scalar.value().updateRowId(columnIndex, x);
    }

    @Override
    public final void updateRowId(final String columnLabel, final RowId x) throws SQLException {
        this.scalar.value().updateRowId(columnLabel, x);
    }

    @Override
    public final int getHoldability() throws SQLException {
        return this.scalar.value().getHoldability();
    }

    @Override
    public final boolean isClosed() throws SQLException {
        return this.scalar.value().isClosed();
    }

    @Override
    public final void updateNString(final int columnIndex, final String nString) throws SQLException {
        this.scalar.value().updateNString(columnIndex, nString);
    }

    @Override
    public final void updateNString(final String columnLabel, final String nString) throws SQLException {
        this.scalar.value().updateNString(columnLabel, nString);
    }

    @Override
    public final void updateNClob(final int columnIndex, final NClob nClob) throws SQLException {
        this.scalar.value().updateNClob(columnIndex, nClob);
    }

    @Override
    public final void updateNClob(final String columnLabel, final NClob nClob) throws SQLException {
        this.scalar.value().updateNClob(columnLabel, nClob);
    }

    @Override
    public final NClob getNClob(final int columnIndex) throws SQLException {
        return this.scalar.value().getNClob(columnIndex);
    }

    @Override
    public final NClob getNClob(final String columnLabel) throws SQLException {
        return this.scalar.value().getNClob(columnLabel);
    }

    @Override
    public final SQLXML getSQLXML(final int columnIndex) throws SQLException {
        return this.scalar.value().getSQLXML(columnIndex);
    }

    @Override
    public final SQLXML getSQLXML(final String columnLabel) throws SQLException {
        return this.scalar.value().getSQLXML(columnLabel);
    }

    @Override
    public final void updateSQLXML(final int columnIndex, final SQLXML xmlObject) throws SQLException {
        this.scalar.value().updateSQLXML(columnIndex, xmlObject);
    }

    @Override
    public final void updateSQLXML(final String columnLabel, final SQLXML xmlObject) throws SQLException {
        this.scalar.value().updateSQLXML(columnLabel, xmlObject);
    }

    @Override
    public final String getNString(final int columnIndex) throws SQLException {
        return this.scalar.value().getNString(columnIndex);
    }

    @Override
    public final String getNString(final String columnLabel) throws SQLException {
        return this.scalar.value().getNString(columnLabel);
    }

    @Override
    public final Reader getNCharacterStream(final int columnIndex) throws SQLException {
        return this.scalar.value().getNCharacterStream(columnIndex);
    }

    @Override
    public final Reader getNCharacterStream(final String columnLabel) throws SQLException {
        return this.scalar.value().getNCharacterStream(columnLabel);
    }

    @Override
    public final void updateNCharacterStream(final int columnIndex, final Reader x, final long length) throws SQLException {
        this.scalar.value().updateNCharacterStream(columnIndex, x, length);
    }

    @Override
    public final void updateNCharacterStream(final String columnLabel, final Reader reader, final long length) throws SQLException {
        this.scalar.value().updateNCharacterStream(columnLabel, reader, length);
    }

    @Override
    public final void updateAsciiStream(final int columnIndex, final InputStream x, final long length) throws SQLException {
        this.scalar.value().updateAsciiStream(columnIndex, x, length);
    }

    @Override
    public final void updateBinaryStream(final int columnIndex, final InputStream x, final long length) throws SQLException {
        this.scalar.value().updateBinaryStream(columnIndex, x, length);
    }

    @Override
    public final void updateCharacterStream(final int columnIndex, final Reader x, final long length) throws SQLException {
        this.scalar.value().updateCharacterStream(columnIndex, x, length);
    }

    @Override
    public final void updateAsciiStream(final String columnLabel, final InputStream x, final long length) throws SQLException {
        this.scalar.value().updateAsciiStream(columnLabel, x, length);
    }

    @Override
    public final void updateBinaryStream(final String columnLabel, final InputStream x, final long length) throws SQLException {
        this.scalar.value().updateBinaryStream(columnLabel, x, length);
    }

    @Override
    public final void updateCharacterStream(final String columnLabel, final Reader reader, final long length) throws SQLException {
        this.scalar.value().updateCharacterStream(columnLabel, reader, length);
    }

    @Override
    public final void updateBlob(final int columnIndex, final InputStream inputStream, final long length) throws SQLException {
        this.scalar.value().updateBlob(columnIndex, inputStream, length);
    }

    @Override
    public final void updateBlob(final String columnLabel, final InputStream inputStream, final long length) throws SQLException {
        this.scalar.value().updateBlob(columnLabel, inputStream, length);
    }

    @Override
    public final void updateClob(final int columnIndex, final Reader reader, final long length) throws SQLException {
        this.scalar.value().updateClob(columnIndex, reader, length);
    }

    @Override
    public final void updateClob(final String columnLabel, final Reader reader, final long length) throws SQLException {
        this.scalar.value().updateClob(columnLabel, reader, length);
    }

    @Override
    public final void updateNClob(final int columnIndex, final Reader reader, final long length) throws SQLException {
        this.scalar.value().updateNClob(columnIndex, reader, length);
    }

    @Override
    public final void updateNClob(final String columnLabel, final Reader reader, final long length) throws SQLException {
        this.scalar.value().updateNClob(columnLabel, reader, length);
    }

    @Override
    public final void updateNCharacterStream(final int columnIndex, final Reader x) throws SQLException {
        this.scalar.value().updateNCharacterStream(columnIndex, x);
    }

    @Override
    public final void updateNCharacterStream(final String columnLabel, final Reader reader) throws SQLException {
        this.scalar.value().updateNCharacterStream(columnLabel, reader);
    }

    @Override
    public final void updateAsciiStream(final int columnIndex, final InputStream x) throws SQLException {
        this.scalar.value().updateAsciiStream(columnIndex, x);
    }

    @Override
    public final void updateBinaryStream(final int columnIndex, final InputStream x) throws SQLException {
        this.scalar.value().updateBinaryStream(columnIndex, x);
    }

    @Override
    public final void updateCharacterStream(final int columnIndex, final Reader x) throws SQLException {
        this.scalar.value().updateCharacterStream(columnIndex, x);
    }

    @Override
    public final void updateAsciiStream(final String columnLabel, final InputStream x) throws SQLException {
        this.scalar.value().updateAsciiStream(columnLabel, x);
    }

    @Override
    public final void updateBinaryStream(final String columnLabel, final InputStream x) throws SQLException {
        this.scalar.value().updateBinaryStream(columnLabel, x);
    }

    @Override
    public final void updateCharacterStream(final String columnLabel, final Reader reader) throws SQLException {
        this.scalar.value().updateCharacterStream(columnLabel, reader);
    }

    @Override
    public final void updateBlob(final int columnIndex, final InputStream inputStream) throws SQLException {
        this.scalar.value().updateBlob(columnIndex, inputStream);
    }

    @Override
    public final void updateBlob(final String columnLabel, final InputStream inputStream) throws SQLException {
        this.scalar.value().updateBlob(columnLabel, inputStream);
    }

    @Override
    public final void updateClob(final int columnIndex, final Reader reader) throws SQLException {
        this.scalar.value().updateClob(columnIndex, reader);
    }

    @Override
    public final void updateClob(final String columnLabel, final Reader reader) throws SQLException {
        this.scalar.value().updateClob(columnLabel, reader);
    }

    @Override
    public final void updateNClob(final int columnIndex, final Reader reader) throws SQLException {
        this.scalar.value().updateNClob(columnIndex, reader);
    }

    @Override
    public final void updateNClob(final String columnLabel, final Reader reader) throws SQLException {
        this.scalar.value().updateNClob(columnLabel, reader);
    }

    @Override
    public final <T> T getObject(final int columnIndex, final Class<T> type) throws SQLException {
        return this.scalar.value().getObject(columnIndex, type);
    }

    @Override
    public final <T> T getObject(final String columnLabel, final Class<T> type) throws SQLException {
        return this.scalar.value().getObject(columnLabel, type);
    }

    @Override
    public final void updateObject(final int columnIndex, final Object x, final SQLType targetSqlType, final int scaleOrLength) throws SQLException {
        this.scalar.value().updateObject(
            columnIndex,
            x,
            targetSqlType,
            scaleOrLength
        );
    }

    @Override
    public final void updateObject(final String columnLabel, final Object x, final SQLType targetSqlType, final int scaleOrLength) throws SQLException {
        this.scalar.value().updateObject(
            columnLabel,
            x,
            targetSqlType,
            scaleOrLength
        );
    }

    @Override
    public final void updateObject(final int columnIndex, final Object x, final SQLType targetSqlType) throws SQLException {
        this.scalar.value().updateObject(columnIndex, x, targetSqlType);
    }

    @Override
    public final void updateObject(final String columnLabel, final Object x, final SQLType targetSqlType) throws SQLException {
        this.scalar.value().updateObject(columnLabel, x, targetSqlType);
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
