package org.fusionsoft.database.description.dbd;

import java.util.Set;

public class SimpleConstraint implements Constraint {
    private final String key;
    private final Set<String> dbColumn;
    private final String dbConstraintType;
    private final String dbRefSchema;
    private final String dbRefTable;
    private final Set<String> dbRefColumn;
    private final Set<String> dbFKColumn;
    private final String dbRefUpdate;
    private final String dbRefDelete;

    public SimpleConstraint(String key, Set<String> dbColumn, String dbConstraintType, String dbRefSchema, String dbRefTable, Set<String> dbRefColumn, Set<String> dbFKColumn, String dbRefUpdate, String dbRefDelete) {
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
    public final String key() {
        return this.key;
    }

    @Override
    public final Set<String> dbColumn() {
        return this.dbColumn;
    }

    @Override
    public final String dbConstraintType() {
        return this.dbConstraintType;
    }

    @Override
    public final Set<String> dbFKColumn() {
        return this.dbFKColumn;
    }

    @Override
    public final String dbRefSchema() {
        return this.dbRefSchema;
    }

    @Override
    public final String dbRefTable() {
        return this.dbRefTable;
    }

    @Override
    public final Set<String> dbRefColumn() {
        return dbRefColumn;
    }

    @Override
    public final String dbRefUpdate() {
        return this.dbRefUpdate;
    }

    @Override
    public final String dbRefDelete() {
        return this.dbRefDelete;
    }
}
