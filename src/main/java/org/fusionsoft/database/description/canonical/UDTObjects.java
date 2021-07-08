package org.fusionsoft.database.description.canonical;

import org.fusionsoft.database.description.canonical.udt.Domain;
import org.fusionsoft.database.description.canonical.udt.Enum;
import org.fusionsoft.database.description.canonical.udt.Tuple;

public interface UDTObjects {
    Iterable<Domain> domains();
    Iterable<Tuple> tuples();
    Iterable<Enum> enums();
}
