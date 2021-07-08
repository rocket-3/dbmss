package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;
import java.util.Set;

public interface IUConstraintDBD {
    String key() throws IOException;
    Set<String> dbColumn() throws IOException;
    String dbConstraintType() throws IOException;
    Set<String> dbFKColumn() throws IOException;
    String dbRefSchema() throws IOException;
    String dbRefTable() throws IOException;
    Set<String> dbRefColumn() throws IOException;
    String dbRefUpdate() throws IOException;
    String dbRefDelete() throws IOException;
}
