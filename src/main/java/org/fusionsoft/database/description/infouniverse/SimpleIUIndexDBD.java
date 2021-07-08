package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;

public class SimpleIUIndexDBD implements IUIndexDBD {
    private final String key;
    private final String dbColumn;
    private final boolean dbUnique;
    private final String indexType;

    public SimpleIUIndexDBD(String key, String dbColumn, boolean dbUnique, String indexType) {
        this.key = key;
        this.dbColumn = dbColumn;
        this.dbUnique = dbUnique;
        this.indexType = indexType;
    }

    @Override
    public String key() {
        return this.key;
    }

    @Override
    public String dbColumn() {
        return this.dbColumn;
    }

    @Override
    public boolean dbUnique() throws IOException {
        return this.dbUnique;
    }

    @Override
    public String indexType() {
        return this.indexType;
    }
}
