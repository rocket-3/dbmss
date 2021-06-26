package org.fusionsoft.database;

public interface DbObject {
    String name();
    String type();
    String createSql() throws Exception;
    String dropSql() throws Exception;
}
