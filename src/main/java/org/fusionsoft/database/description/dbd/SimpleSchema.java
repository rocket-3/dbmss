package org.fusionsoft.database.description.dbd;

import java.io.IOException;

public class SimpleSchema implements Schema {
    private final String key;
    private final String owner;
    private final Iterable<Table> tableDBDs;

    public SimpleSchema(String key, String owner, Iterable<Table> tableDBDs) {
        this.key = key;
        this.owner = owner;
        this.tableDBDs = tableDBDs;
    }

    @Override
    public final String key() throws IOException {
        return this.key;
    }

    @Override
    public final String owner() throws IOException {
        return this.owner;
    }

    @Override
    public final Iterable<Table> tableDescriptions() throws IOException {
        return this.tableDBDs;
    }
}
