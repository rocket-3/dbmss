package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;

public class SimpleIUSchemaDBD implements IUSchemaDBD {
    private final String key;
    private final String owner;
    private final Iterable<IUTableDBD> tableDBDs;

    public SimpleIUSchemaDBD(String key, String owner, Iterable<IUTableDBD> tableDBDs) {
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
    public final Iterable<IUTableDBD> tableDescriptions() throws IOException {
        return this.tableDBDs;
    }
}
