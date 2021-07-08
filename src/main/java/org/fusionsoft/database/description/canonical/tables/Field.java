package org.fusionsoft.database.description.canonical.tables;

import org.fusionsoft.database.description.canonical.Object;

public interface Field extends Object {
    String generalTypeName();
    String sqlTypeName();
    boolean nullable();
    int order();
}
