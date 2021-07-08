package org.fusionsoft.database.description.canonical;

import org.fusionsoft.database.description.canonical.containers.Package;
import org.fusionsoft.database.description.canonical.containers.Schema;
import org.fusionsoft.database.description.canonical.containers.Tablespace;

public interface Containers {
    Iterable<Schema> schemas();
    Iterable<Package> packages();
    Iterable<Tablespace> tablespaces();
}
