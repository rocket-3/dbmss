package org.fusionsoft.database.description.dbd;

import java.io.IOException;

public interface Index {
    String key() throws IOException;
    String dbColumn() throws IOException;
    boolean dbUnique() throws IOException;
    String indexType() throws IOException;
}
