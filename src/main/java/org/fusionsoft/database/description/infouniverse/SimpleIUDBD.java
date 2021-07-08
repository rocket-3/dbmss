package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;

public class SimpleIUDBD implements IUDBD {
    private final Iterable<IUServerDBD> serverDBDs;
    private final Iterable<IUSchemaDBD> schemaDBDs;

    public SimpleIUDBD(Iterable<IUServerDBD> serverDBDs, Iterable<IUSchemaDBD> schemaDBDs) {
        this.serverDBDs = serverDBDs;
        this.schemaDBDs = schemaDBDs;
    }

    @Override
    public Iterable<IUServerDBD> serverDescriptions() throws IOException {
        return this.serverDBDs;
    }

    @Override
    public Iterable<IUSchemaDBD> schemaDescriptions() throws IOException {
        return this.schemaDBDs;
    }
}
