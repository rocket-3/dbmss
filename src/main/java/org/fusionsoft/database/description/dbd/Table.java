package org.fusionsoft.database.description.dbd;

import java.io.IOException;

public interface Table extends DbmsSpecific {
    String key() throws IOException;
    Iterable<Column> columns() throws IOException;
    Iterable<Constraint> constraints() throws IOException;
    Iterable<Index> indexes() throws IOException;
}
