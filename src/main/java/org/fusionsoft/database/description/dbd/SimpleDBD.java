package org.fusionsoft.database.description.dbd;

import java.io.IOException;

public class SimpleDBD implements DBD {
    private final Iterable<Server> serverDBDs;
    private final Iterable<Schema> schemaDBDs;

    public SimpleDBD(Iterable<Server> serverDBDs, Iterable<Schema> schemaDBDs) {
        this.serverDBDs = serverDBDs;
        this.schemaDBDs = schemaDBDs;
    }

    @Override
    public final Iterable<Server> serverDescriptions() throws IOException {
        return this.serverDBDs;
    }

    @Override
    public final Iterable<Schema> schemaDescriptions() throws IOException {
        return this.schemaDBDs;
    }
}
