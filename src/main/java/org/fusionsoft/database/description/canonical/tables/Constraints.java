package org.fusionsoft.database.description.canonical.tables;

import org.fusionsoft.database.description.canonical.constraints.CheckConstraint;
import org.fusionsoft.database.description.canonical.constraints.FKConstraint;
import org.fusionsoft.database.description.canonical.constraints.PKConstraint;
import org.fusionsoft.database.description.canonical.constraints.UniqueConstraint;

public interface Constraints {
    Iterable<PKConstraint> primaryKeys();
    Iterable<FKConstraint> foreignKeys();
    Iterable<UniqueConstraint> uniqueConstraints();
    Iterable<CheckConstraint> checkConstraints();
}
