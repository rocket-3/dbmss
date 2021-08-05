package org.fusionsoft.database.description.dbd;

import java.io.IOException;

public interface Schema {
    String key() throws IOException;
    String owner() throws IOException;
    Iterable<Table> tableDescriptions() throws IOException;
}
