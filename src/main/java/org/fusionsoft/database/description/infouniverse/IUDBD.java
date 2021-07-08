package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;

public interface IUDBD {
    Iterable<IUServerDBD> serverDescriptions() throws IOException;
    Iterable<IUSchemaDBD> schemaDescriptions() throws IOException;
}
