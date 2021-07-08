package org.fusionsoft.database;

public interface DbObject {
    String fullName();
    String createSql() throws Exception;
    String dropSql() throws Exception;
}
