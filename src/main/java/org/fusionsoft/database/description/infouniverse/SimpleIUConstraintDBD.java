package org.fusionsoft.database.description.infouniverse;

import java.util.Set;

public class SimpleIUConstraintDBD implements IUConstraintDBD {
    private final String key;
    private final Set<String> dbColumn;
    private final String dbConstraintType;
    private final String dbRefSchema;
    private final String dbRefTable;
    private final Set<String> dbRefColumn;
    private final Set<String> dbFKColumn;
    private final String dbRefUpdate;
    private final String dbRefDelete;

    public SimpleIUConstraintDBD(String key, Set<String> dbColumn, String dbConstraintType, String dbRefSchema, String dbRefTable, Set<String> dbRefColumn, Set<String> dbFKColumn, String dbRefUpdate, String dbRefDelete) {
        this.key = key;
        this.dbColumn = dbColumn;
        this.dbConstraintType = dbConstraintType;
        this.dbRefSchema = dbRefSchema;
        this.dbRefTable = dbRefTable;
        this.dbRefColumn = dbRefColumn;
        this.dbFKColumn = dbFKColumn;
        this.dbRefUpdate = dbRefUpdate;
        this.dbRefDelete = dbRefDelete;
    }

    @Override
    public String key() {
        return this.key;
    }

    @Override
    public Set<String> dbColumn() {
        return this.dbColumn;
    }

    @Override
    public String dbConstraintType() {
        return this.dbConstraintType;
    }

    @Override
    public Set<String> dbFKColumn() {
        return this.dbFKColumn;
    }

    @Override
    public String dbRefSchema() {
        return this.dbRefSchema;
    }

    @Override
    public String dbRefTable() {
        return this.dbRefTable;
    }

    @Override
    public Set<String> dbRefColumn() {
        return dbRefColumn;
    }

    @Override
    public String dbRefUpdate() {
        return this.dbRefUpdate;
    }

    @Override
    public String dbRefDelete() {
        return this.dbRefDelete;
    }
}
