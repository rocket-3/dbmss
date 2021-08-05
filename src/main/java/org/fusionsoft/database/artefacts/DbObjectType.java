package org.fusionsoft.database.artefacts;

import org.cactoos.Text;

public interface DbObjectType extends Text {
    DbObjectType Table = () -> "table";
    DbObjectType Absent = () -> "absent";
}
