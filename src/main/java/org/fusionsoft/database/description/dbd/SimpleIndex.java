package org.fusionsoft.database.description.dbd;

import java.io.IOException;

public class SimpleIndex implements Index {
    private final String key;
    private final String dbColumn;
    private final boolean dbUnique;
    private final String indexType;

    public SimpleIndex(String key, String dbColumn, boolean dbUnique, String indexType) {
        this.key = key;
        this.dbColumn = dbColumn;
        this.dbUnique = dbUnique;
        this.indexType = indexType;
    }

    @Override
    public final String key() {
        return this.key;
    }

    @Override
    public final String dbColumn() {
        return this.dbColumn;
    }

    @Override
    public final boolean dbUnique() throws IOException {
        return this.dbUnique;
    }

    @Override
    public final String indexType() {
        return this.indexType;
    }
}
