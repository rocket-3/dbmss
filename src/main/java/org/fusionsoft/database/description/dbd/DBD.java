package org.fusionsoft.database.description.dbd;

import java.io.IOException;

public interface DBD {
    Iterable<Server> serverDescriptions() throws IOException;
    Iterable<Schema> schemaDescriptions() throws IOException;
}
