package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;

public interface IUSchemaDBD {
    String key() throws IOException;
    String owner() throws IOException;
    Iterable<IUTableDBD> tableDescriptions() throws IOException;
}
