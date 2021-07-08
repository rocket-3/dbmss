package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;

public interface IUIndexDBD {
    String key() throws IOException;
    String dbColumn() throws IOException;
    boolean dbUnique() throws IOException;
    String indexType() throws IOException;
}
