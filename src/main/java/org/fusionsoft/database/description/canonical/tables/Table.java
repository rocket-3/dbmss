package org.fusionsoft.database.description.canonical.tables;

import org.fusionsoft.database.description.canonical.OwnedObject;

public interface Table extends OwnedObject {
    Iterable<Field> fields();    
}
