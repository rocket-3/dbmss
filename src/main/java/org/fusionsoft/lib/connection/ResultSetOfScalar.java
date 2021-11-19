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

public class ResultSetOfScalar implements ResultSet {

    private final Unchecked<ResultSet> scalar;

    public ResultSetOfScalar(final Scalar<ResultSet> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public boolean next() throws SQLException {
        return scalar.value().next();
    }

    @Override
    public void close() throws SQLException {
        scalar.value().close();
    }

    @Override
    public boolean wasNull() throws SQLException {
        return scalar.value().wasNull();
    }

    @Override
    public String getString(final int columnIndex) throws SQLException {
        return scalar.value().getString(columnIndex);
    }

    @Override
    public boolean getBoolean(final int columnIndex) throws SQLException {
        return scalar.value().getBoolean(columnIndex);
    }

    @Override
    public byte getByte(final int columnIndex) throws SQLException {
        return scalar.value().getByte(columnIndex);
    }

    @Override
    public short getShort(final int columnIndex) throws SQLException {
        return scalar.value().getShort(columnIndex);
    }

    @Override
    public int getInt(final int columnIndex) throws SQLException {
        return scalar.value().getInt(columnIndex);
    }

    @Override
    public long getLong(final int columnIndex) throws SQLException {
        return scalar.value().getLong(columnIndex);
    }

    @Override
    public float getFloat(final int columnIndex) throws SQLException {
        return scalar.value().getFloat(columnIndex);
    }

    @Override
    public double getDouble(final int columnIndex) throws SQLException {
        return scalar.value().getDouble(columnIndex);
    }

    @Override
    @Deprecated
    public BigDecimal getBigDecimal(final int columnIndex, final int scale) throws SQLException {
        return scalar.value().getBigDecimal(columnIndex, scale);
    }

    @Override
    public byte[] getBytes(final int columnIndex) throws SQLException {
        return scalar.value().getBytes(columnIndex);
    }

    @Override
    public Date getDate(final int columnIndex) throws SQLException {
        return scalar.value().getDate(columnIndex);
    }

    @Override
    public Time getTime(final int columnIndex) throws SQLException {
        return scalar.value().getTime(columnIndex);
    }

    @Override
    public Timestamp getTimestamp(final int columnIndex) throws SQLException {
        return scalar.value().getTimestamp(columnIndex);
    }

    @Override
    public InputStream getAsciiStream(final int columnIndex) throws SQLException {
        return scalar.value().getAsciiStream(columnIndex);
    }

    @Override
    @Deprecated
    public InputStream getUnicodeStream(final int columnIndex) throws SQLException {
        return scalar.value().getUnicodeStream(columnIndex);
    }

    @Override
    public InputStream getBinaryStream(final int columnIndex) throws SQLException {
        return scalar.value().getBinaryStream(columnIndex);
    }

    @Override
    public String getString(final String columnLabel) throws SQLException {
        return scalar.value().getString(columnLabel);
    }

    @Override
    public boolean getBoolean(final String columnLabel) throws SQLException {
        return scalar.value().getBoolean(columnLabel);
    }

    @Override
    public byte getByte(final String columnLabel) throws SQLException {
        return scalar.value().getByte(columnLabel);
    }

    @Override
    public short getShort(final String columnLabel) throws SQLException {
        return scalar.value().getShort(columnLabel);
    }

    @Override
    public int getInt(final String columnLabel) throws SQLException {
        return scalar.value().getInt(columnLabel);
    }

    @Override
    public long getLong(final String columnLabel) throws SQLException {
        return scalar.value().getLong(columnLabel);
    }

    @Override
    public float getFloat(final String columnLabel) throws SQLException {
        return scalar.value().getFloat(columnLabel);
    }

    @Override
    public double getDouble(final String columnLabel) throws SQLException {
        return scalar.value().getDouble(columnLabel);
    }

    @Override
    @Deprecated
    public BigDecimal getBigDecimal(final String columnLabel, final int scale) throws SQLException {
        return scalar.value().getBigDecimal(columnLabel, scale);
    }

    @Override
    public byte[] getBytes(final String columnLabel) throws SQLException {
        return scalar.value().getBytes(columnLabel);
    }

    @Override
    public Date getDate(final String columnLabel) throws SQLException {
        return scalar.value().getDate(columnLabel);
    }

    @Override
    public Time getTime(final String columnLabel) throws SQLException {
        return scalar.value().getTime(columnLabel);
    }

    @Override
    public Timestamp getTimestamp(final String columnLabel) throws SQLException {
        return scalar.value().getTimestamp(columnLabel);
    }

    @Override
    public InputStream getAsciiStream(final String columnLabel) throws SQLException {
        return scalar.value().getAsciiStream(columnLabel);
    }

    @Override
    @Deprecated
    public InputStream getUnicodeStream(final String columnLabel) throws SQLException {
        return scalar.value().getUnicodeStream(columnLabel);
    }

    @Override
    public InputStream getBinaryStream(final String columnLabel) throws SQLException {
        return scalar.value().getBinaryStream(columnLabel);
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return scalar.value().getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        scalar.value().clearWarnings();
    }

    @Override
    public String getCursorName() throws SQLException {
        return scalar.value().getCursorName();
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return scalar.value().getMetaData();
    }

    @Override
    public Object getObject(final int columnIndex) throws SQLException {
        return scalar.value().getObject(columnIndex);
    }

    @Override
    public Object getObject(final String columnLabel) throws SQLException {
        return scalar.value().getObject(columnLabel);
    }

    @Override
    public int findColumn(final String columnLabel) throws SQLException {
        return scalar.value().findColumn(columnLabel);
    }

    @Override
    public Reader getCharacterStream(final int columnIndex) throws SQLException {
        return scalar.value().getCharacterStream(columnIndex);
    }

    @Override
    public Reader getCharacterStream(final String columnLabel) throws SQLException {
        return scalar.value().getCharacterStream(columnLabel);
    }

    @Override
    public BigDecimal getBigDecimal(final int columnIndex) throws SQLException {
        return scalar.value().getBigDecimal(columnIndex);
    }

    @Override
    public BigDecimal getBigDecimal(final String columnLabel) throws SQLException {
        return scalar.value().getBigDecimal(columnLabel);
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        return scalar.value().isBeforeFirst();
    }

    @Override
    public boolean isAfterLast() throws SQLException {
        return scalar.value().isAfterLast();
    }

    @Override
    public boolean isFirst() throws SQLException {
        return scalar.value().isFirst();
    }

    @Override
    public boolean isLast() throws SQLException {
        return scalar.value().isLast();
    }

    @Override
    public void beforeFirst() throws SQLException {
        scalar.value().beforeFirst();
    }

    @Override
    public void afterLast() throws SQLException {
        scalar.value().afterLast();
    }

    @Override
    public boolean first() throws SQLException {
        return scalar.value().first();
    }

    @Override
    public boolean last() throws SQLException {
        return scalar.value().last();
    }

    @Override
    public int getRow() throws SQLException {
        return scalar.value().getRow();
    }

    @Override
    public boolean absolute(final int row) throws SQLException {
        return scalar.value().absolute(row);
    }

    @Override
    public boolean relative(final int rows) throws SQLException {
        return scalar.value().relative(rows);
    }

    @Override
    public boolean previous() throws SQLException {
        return scalar.value().previous();
    }

    @Override
    public void setFetchDirection(final int direction) throws SQLException {
        scalar.value().setFetchDirection(direction);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return scalar.value().getFetchDirection();
    }

    @Override
    public void setFetchSize(final int rows) throws SQLException {
        scalar.value().setFetchSize(rows);
    }

    @Override
    public int getFetchSize() throws SQLException {
        return scalar.value().getFetchSize();
    }

    @Override
    public int getType() throws SQLException {
        return scalar.value().getType();
    }

    @Override
    public int getConcurrency() throws SQLException {
        return scalar.value().getConcurrency();
    }

    @Override
    public boolean rowUpdated() throws SQLException {
        return scalar.value().rowUpdated();
    }

    @Override
    public boolean rowInserted() throws SQLException {
        return scalar.value().rowInserted();
    }

    @Override
    public boolean rowDeleted() throws SQLException {
        return scalar.value().rowDeleted();
    }

    @Override
    public void updateNull(final int columnIndex) throws SQLException {
        scalar.value().updateNull(columnIndex);
    }

    @Override
    public void updateBoolean(final int columnIndex, final boolean x) throws SQLException {
        scalar.value().updateBoolean(columnIndex, x);
    }

    @Override
    public void updateByte(final int columnIndex, final byte x) throws SQLException {
        scalar.value().updateByte(columnIndex, x);
    }

    @Override
    public void updateShort(final int columnIndex, final short x) throws SQLException {
        scalar.value().updateShort(columnIndex, x);
    }

    @Override
    public void updateInt(final int columnIndex, final int x) throws SQLException {
        scalar.value().updateInt(columnIndex, x);
    }

    @Override
    public void updateLong(final int columnIndex, final long x) throws SQLException {
        scalar.value().updateLong(columnIndex, x);
    }

    @Override
    public void updateFloat(final int columnIndex, final float x) throws SQLException {
        scalar.value().updateFloat(columnIndex, x);
    }

    @Override
    public void updateDouble(final int columnIndex, final double x) throws SQLException {
        scalar.value().updateDouble(columnIndex, x);
    }

    @Override
    public void updateBigDecimal(final int columnIndex, final BigDecimal x) throws SQLException {
        scalar.value().updateBigDecimal(columnIndex, x);
    }

    @Override
    public void updateString(final int columnIndex, final String x) throws SQLException {
        scalar.value().updateString(columnIndex, x);
    }

    @Override
    public void updateBytes(final int columnIndex, final byte[] x) throws SQLException {
        scalar.value().updateBytes(columnIndex, x);
    }

    @Override
    public void updateDate(final int columnIndex, final Date x) throws SQLException {
        scalar.value().updateDate(columnIndex, x);
    }

    @Override
    public void updateTime(final int columnIndex, final Time x) throws SQLException {
        scalar.value().updateTime(columnIndex, x);
    }

    @Override
    public void updateTimestamp(final int columnIndex, final Timestamp x) throws SQLException {
        scalar.value().updateTimestamp(columnIndex, x);
    }

    @Override
    public void updateAsciiStream(final int columnIndex, final InputStream x, final int length) throws SQLException {
        scalar.value().updateAsciiStream(columnIndex, x, length);
    }

    @Override
    public void updateBinaryStream(final int columnIndex, final InputStream x, final int length) throws SQLException {
        scalar.value().updateBinaryStream(columnIndex, x, length);
    }

    @Override
    public void updateCharacterStream(final int columnIndex, final Reader x, final int length) throws SQLException {
        scalar.value().updateCharacterStream(columnIndex, x, length);
    }

    @Override
    public void updateObject(final int columnIndex, final Object x, final int scaleOrLength) throws SQLException {
        scalar.value().updateObject(columnIndex, x, scaleOrLength);
    }

    @Override
    public void updateObject(final int columnIndex, final Object x) throws SQLException {
        scalar.value().updateObject(columnIndex, x);
    }

    @Override
    public void updateNull(final String columnLabel) throws SQLException {
        scalar.value().updateNull(columnLabel);
    }

    @Override
    public void updateBoolean(final String columnLabel, final boolean x) throws SQLException {
        scalar.value().updateBoolean(columnLabel, x);
    }

    @Override
    public void updateByte(final String columnLabel, final byte x) throws SQLException {
        scalar.value().updateByte(columnLabel, x);
    }

    @Override
    public void updateShort(final String columnLabel, final short x) throws SQLException {
        scalar.value().updateShort(columnLabel, x);
    }

    @Override
    public void updateInt(final String columnLabel, final int x) throws SQLException {
        scalar.value().updateInt(columnLabel, x);
    }

    @Override
    public void updateLong(final String columnLabel, final long x) throws SQLException {
        scalar.value().updateLong(columnLabel, x);
    }

    @Override
    public void updateFloat(final String columnLabel, final float x) throws SQLException {
        scalar.value().updateFloat(columnLabel, x);
    }

    @Override
    public void updateDouble(final String columnLabel, final double x) throws SQLException {
        scalar.value().updateDouble(columnLabel, x);
    }

    @Override
    public void updateBigDecimal(final String columnLabel, final BigDecimal x) throws SQLException {
        scalar.value().updateBigDecimal(columnLabel, x);
    }

    @Override
    public void updateString(final String columnLabel, final String x) throws SQLException {
        scalar.value().updateString(columnLabel, x);
    }

    @Override
    public void updateBytes(final String columnLabel, final byte[] x) throws SQLException {
        scalar.value().updateBytes(columnLabel, x);
    }

    @Override
    public void updateDate(final String columnLabel, final Date x) throws SQLException {
        scalar.value().updateDate(columnLabel, x);
    }

    @Override
    public void updateTime(final String columnLabel, final Time x) throws SQLException {
        scalar.value().updateTime(columnLabel, x);
    }

    @Override
    public void updateTimestamp(final String columnLabel, final Timestamp x) throws SQLException {
        scalar.value().updateTimestamp(columnLabel, x);
    }

    @Override
    public void updateAsciiStream(final String columnLabel, final InputStream x, final int length) throws SQLException {
        scalar.value().updateAsciiStream(columnLabel, x, length);
    }

    @Override
    public void updateBinaryStream(final String columnLabel, final InputStream x, final int length) throws SQLException {
        scalar.value().updateBinaryStream(columnLabel, x, length);
    }

    @Override
    public void updateCharacterStream(final String columnLabel, final Reader reader, final int length) throws SQLException {
        scalar.value().updateCharacterStream(columnLabel, reader, length);
    }

    @Override
    public void updateObject(final String columnLabel, final Object x, final int scaleOrLength) throws SQLException {
        scalar.value().updateObject(columnLabel, x, scaleOrLength);
    }

    @Override
    public void updateObject(final String columnLabel, final Object x) throws SQLException {
        scalar.value().updateObject(columnLabel, x);
    }

    @Override
    public void insertRow() throws SQLException {
        scalar.value().insertRow();
    }

    @Override
    public void updateRow() throws SQLException {
        scalar.value().updateRow();
    }

    @Override
    public void deleteRow() throws SQLException {
        scalar.value().deleteRow();
    }

    @Override
    public void refreshRow() throws SQLException {
        scalar.value().refreshRow();
    }

    @Override
    public void cancelRowUpdates() throws SQLException {
        scalar.value().cancelRowUpdates();
    }

    @Override
    public void moveToInsertRow() throws SQLException {
        scalar.value().moveToInsertRow();
    }

    @Override
    public void moveToCurrentRow() throws SQLException {
        scalar.value().moveToCurrentRow();
    }

    @Override
    public Statement getStatement() throws SQLException {
        return scalar.value().getStatement();
    }

    @Override
    public Object getObject(final int columnIndex, final Map<String, Class<?>> map) throws SQLException {
        return scalar.value().getObject(columnIndex, map);
    }

    @Override
    public Ref getRef(final int columnIndex) throws SQLException {
        return scalar.value().getRef(columnIndex);
    }

    @Override
    public Blob getBlob(final int columnIndex) throws SQLException {
        return scalar.value().getBlob(columnIndex);
    }

    @Override
    public Clob getClob(final int columnIndex) throws SQLException {
        return scalar.value().getClob(columnIndex);
    }

    @Override
    public Array getArray(final int columnIndex) throws SQLException {
        return scalar.value().getArray(columnIndex);
    }

    @Override
    public Object getObject(final String columnLabel, final Map<String, Class<?>> map) throws SQLException {
        return scalar.value().getObject(columnLabel, map);
    }

    @Override
    public Ref getRef(final String columnLabel) throws SQLException {
        return scalar.value().getRef(columnLabel);
    }

    @Override
    public Blob getBlob(final String columnLabel) throws SQLException {
        return scalar.value().getBlob(columnLabel);
    }

    @Override
    public Clob getClob(final String columnLabel) throws SQLException {
        return scalar.value().getClob(columnLabel);
    }

    @Override
    public Array getArray(final String columnLabel) throws SQLException {
        return scalar.value().getArray(columnLabel);
    }

    @Override
    public Date getDate(final int columnIndex, final Calendar cal) throws SQLException {
        return scalar.value().getDate(columnIndex, cal);
    }

    @Override
    public Date getDate(final String columnLabel, final Calendar cal) throws SQLException {
        return scalar.value().getDate(columnLabel, cal);
    }

    @Override
    public Time getTime(final int columnIndex, final Calendar cal) throws SQLException {
        return scalar.value().getTime(columnIndex, cal);
    }

    @Override
    public Time getTime(final String columnLabel, final Calendar cal) throws SQLException {
        return scalar.value().getTime(columnLabel, cal);
    }

    @Override
    public Timestamp getTimestamp(final int columnIndex, final Calendar cal) throws SQLException {
        return scalar.value().getTimestamp(columnIndex, cal);
    }

    @Override
    public Timestamp getTimestamp(final String columnLabel, final Calendar cal) throws SQLException {
        return scalar.value().getTimestamp(columnLabel, cal);
    }

    @Override
    public URL getURL(final int columnIndex) throws SQLException {
        return scalar.value().getURL(columnIndex);
    }

    @Override
    public URL getURL(final String columnLabel) throws SQLException {
        return scalar.value().getURL(columnLabel);
    }

    @Override
    public void updateRef(final int columnIndex, final Ref x) throws SQLException {
        scalar.value().updateRef(columnIndex, x);
    }

    @Override
    public void updateRef(final String columnLabel, final Ref x) throws SQLException {
        scalar.value().updateRef(columnLabel, x);
    }

    @Override
    public void updateBlob(final int columnIndex, final Blob x) throws SQLException {
        scalar.value().updateBlob(columnIndex, x);
    }

    @Override
    public void updateBlob(final String columnLabel, final Blob x) throws SQLException {
        scalar.value().updateBlob(columnLabel, x);
    }

    @Override
    public void updateClob(final int columnIndex, final Clob x) throws SQLException {
        scalar.value().updateClob(columnIndex, x);
    }

    @Override
    public void updateClob(final String columnLabel, final Clob x) throws SQLException {
        scalar.value().updateClob(columnLabel, x);
    }

    @Override
    public void updateArray(final int columnIndex, final Array x) throws SQLException {
        scalar.value().updateArray(columnIndex, x);
    }

    @Override
    public void updateArray(final String columnLabel, final Array x) throws SQLException {
        scalar.value().updateArray(columnLabel, x);
    }

    @Override
    public RowId getRowId(final int columnIndex) throws SQLException {
        return scalar.value().getRowId(columnIndex);
    }

    @Override
    public RowId getRowId(final String columnLabel) throws SQLException {
        return scalar.value().getRowId(columnLabel);
    }

    @Override
    public void updateRowId(final int columnIndex, final RowId x) throws SQLException {
        scalar.value().updateRowId(columnIndex, x);
    }

    @Override
    public void updateRowId(final String columnLabel, final RowId x) throws SQLException {
        scalar.value().updateRowId(columnLabel, x);
    }

    @Override
    public int getHoldability() throws SQLException {
        return scalar.value().getHoldability();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return scalar.value().isClosed();
    }

    @Override
    public void updateNString(final int columnIndex, final String nString) throws SQLException {
        scalar.value().updateNString(columnIndex, nString);
    }

    @Override
    public void updateNString(final String columnLabel, final String nString) throws SQLException {
        scalar.value().updateNString(columnLabel, nString);
    }

    @Override
    public void updateNClob(final int columnIndex, final NClob nClob) throws SQLException {
        scalar.value().updateNClob(columnIndex, nClob);
    }

    @Override
    public void updateNClob(final String columnLabel, final NClob nClob) throws SQLException {
        scalar.value().updateNClob(columnLabel, nClob);
    }

    @Override
    public NClob getNClob(final int columnIndex) throws SQLException {
        return scalar.value().getNClob(columnIndex);
    }

    @Override
    public NClob getNClob(final String columnLabel) throws SQLException {
        return scalar.value().getNClob(columnLabel);
    }

    @Override
    public SQLXML getSQLXML(final int columnIndex) throws SQLException {
        return scalar.value().getSQLXML(columnIndex);
    }

    @Override
    public SQLXML getSQLXML(final String columnLabel) throws SQLException {
        return scalar.value().getSQLXML(columnLabel);
    }

    @Override
    public void updateSQLXML(final int columnIndex, final SQLXML xmlObject) throws SQLException {
        scalar.value().updateSQLXML(columnIndex, xmlObject);
    }

    @Override
    public void updateSQLXML(final String columnLabel, final SQLXML xmlObject) throws SQLException {
        scalar.value().updateSQLXML(columnLabel, xmlObject);
    }

    @Override
    public String getNString(final int columnIndex) throws SQLException {
        return scalar.value().getNString(columnIndex);
    }

    @Override
    public String getNString(final String columnLabel) throws SQLException {
        return scalar.value().getNString(columnLabel);
    }

    @Override
    public Reader getNCharacterStream(final int columnIndex) throws SQLException {
        return scalar.value().getNCharacterStream(columnIndex);
    }

    @Override
    public Reader getNCharacterStream(final String columnLabel) throws SQLException {
        return scalar.value().getNCharacterStream(columnLabel);
    }

    @Override
    public void updateNCharacterStream(final int columnIndex, final Reader x, final long length) throws SQLException {
        scalar.value().updateNCharacterStream(columnIndex, x, length);
    }

    @Override
    public void updateNCharacterStream(final String columnLabel, final Reader reader, final long length) throws SQLException {
        scalar.value().updateNCharacterStream(columnLabel, reader, length);
    }

    @Override
    public void updateAsciiStream(final int columnIndex, final InputStream x, final long length) throws SQLException {
        scalar.value().updateAsciiStream(columnIndex, x, length);
    }

    @Override
    public void updateBinaryStream(final int columnIndex, final InputStream x, final long length) throws SQLException {
        scalar.value().updateBinaryStream(columnIndex, x, length);
    }

    @Override
    public void updateCharacterStream(final int columnIndex, final Reader x, final long length) throws SQLException {
        scalar.value().updateCharacterStream(columnIndex, x, length);
    }

    @Override
    public void updateAsciiStream(final String columnLabel, final InputStream x, final long length) throws SQLException {
        scalar.value().updateAsciiStream(columnLabel, x, length);
    }

    @Override
    public void updateBinaryStream(final String columnLabel, final InputStream x, final long length) throws SQLException {
        scalar.value().updateBinaryStream(columnLabel, x, length);
    }

    @Override
    public void updateCharacterStream(final String columnLabel, final Reader reader, final long length) throws SQLException {
        scalar.value().updateCharacterStream(columnLabel, reader, length);
    }

    @Override
    public void updateBlob(final int columnIndex, final InputStream inputStream, final long length) throws SQLException {
        scalar.value().updateBlob(columnIndex, inputStream, length);
    }

    @Override
    public void updateBlob(final String columnLabel, final InputStream inputStream, final long length) throws SQLException {
        scalar.value().updateBlob(columnLabel, inputStream, length);
    }

    @Override
    public void updateClob(final int columnIndex, final Reader reader, final long length) throws SQLException {
        scalar.value().updateClob(columnIndex, reader, length);
    }

    @Override
    public void updateClob(final String columnLabel, final Reader reader, final long length) throws SQLException {
        scalar.value().updateClob(columnLabel, reader, length);
    }

    @Override
    public void updateNClob(final int columnIndex, final Reader reader, final long length) throws SQLException {
        scalar.value().updateNClob(columnIndex, reader, length);
    }

    @Override
    public void updateNClob(final String columnLabel, final Reader reader, final long length) throws SQLException {
        scalar.value().updateNClob(columnLabel, reader, length);
    }

    @Override
    public void updateNCharacterStream(final int columnIndex, final Reader x) throws SQLException {
        scalar.value().updateNCharacterStream(columnIndex, x);
    }

    @Override
    public void updateNCharacterStream(final String columnLabel, final Reader reader) throws SQLException {
        scalar.value().updateNCharacterStream(columnLabel, reader);
    }

    @Override
    public void updateAsciiStream(final int columnIndex, final InputStream x) throws SQLException {
        scalar.value().updateAsciiStream(columnIndex, x);
    }

    @Override
    public void updateBinaryStream(final int columnIndex, final InputStream x) throws SQLException {
        scalar.value().updateBinaryStream(columnIndex, x);
    }

    @Override
    public void updateCharacterStream(final int columnIndex, final Reader x) throws SQLException {
        scalar.value().updateCharacterStream(columnIndex, x);
    }

    @Override
    public void updateAsciiStream(final String columnLabel, final InputStream x) throws SQLException {
        scalar.value().updateAsciiStream(columnLabel, x);
    }

    @Override
    public void updateBinaryStream(final String columnLabel, final InputStream x) throws SQLException {
        scalar.value().updateBinaryStream(columnLabel, x);
    }

    @Override
    public void updateCharacterStream(final String columnLabel, final Reader reader) throws SQLException {
        scalar.value().updateCharacterStream(columnLabel, reader);
    }

    @Override
    public void updateBlob(final int columnIndex, final InputStream inputStream) throws SQLException {
        scalar.value().updateBlob(columnIndex, inputStream);
    }

    @Override
    public void updateBlob(final String columnLabel, final InputStream inputStream) throws SQLException {
        scalar.value().updateBlob(columnLabel, inputStream);
    }

    @Override
    public void updateClob(final int columnIndex, final Reader reader) throws SQLException {
        scalar.value().updateClob(columnIndex, reader);
    }

    @Override
    public void updateClob(final String columnLabel, final Reader reader) throws SQLException {
        scalar.value().updateClob(columnLabel, reader);
    }

    @Override
    public void updateNClob(final int columnIndex, final Reader reader) throws SQLException {
        scalar.value().updateNClob(columnIndex, reader);
    }

    @Override
    public void updateNClob(final String columnLabel, final Reader reader) throws SQLException {
        scalar.value().updateNClob(columnLabel, reader);
    }

    @Override
    public <T> T getObject(final int columnIndex, final Class<T> type) throws SQLException {
        return scalar.value().getObject(columnIndex, type);
    }

    @Override
    public <T> T getObject(final String columnLabel, final Class<T> type) throws SQLException {
        return scalar.value().getObject(columnLabel, type);
    }

    @Override
    public void updateObject(final int columnIndex, final Object x, final SQLType targetSqlType, final int scaleOrLength) throws SQLException {
        scalar.value().updateObject(
            columnIndex,
            x,
            targetSqlType,
            scaleOrLength
        );
    }

    @Override
    public void updateObject(final String columnLabel, final Object x, final SQLType targetSqlType, final int scaleOrLength) throws SQLException {
        scalar.value().updateObject(
            columnLabel,
            x,
            targetSqlType,
            scaleOrLength
        );
    }

    @Override
    public void updateObject(final int columnIndex, final Object x, final SQLType targetSqlType) throws SQLException {
        scalar.value().updateObject(columnIndex, x, targetSqlType);
    }

    @Override
    public void updateObject(final String columnLabel, final Object x, final SQLType targetSqlType) throws SQLException {
        scalar.value().updateObject(columnLabel, x, targetSqlType);
    }

    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        return scalar.value().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return scalar.value().isWrapperFor(iface);
    }

}
