package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;
import java.util.Set;

public interface IUColumnDBD {
    String name() throws IOException;
    String iuColumn() throws IOException;
    String dbName() throws IOException;
    String type() throws IOException;
    boolean nullable() throws IOException;
    String description() throws IOException;
    String dbLocalIdMethod() throws IOException;
    String iuJsonColumn() throws IOException;
    Set<String> iuIncludeProps() throws IOException;
    String dbType() throws IOException;
}
