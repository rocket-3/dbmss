package org.fusionsoft.database.description.canonical;

import org.fusionsoft.database.description.canonical.tables.Constraints;
import org.fusionsoft.database.description.canonical.tables.Index;
import org.fusionsoft.database.description.canonical.tables.Sequence;
import org.fusionsoft.database.description.canonical.tables.Table;

public interface TableObjects {
    Iterable<Table> tables();
    Iterable<Constraints> constraints();
    Iterable<Sequence> sequences();
    Iterable<Index> indexes();
}
