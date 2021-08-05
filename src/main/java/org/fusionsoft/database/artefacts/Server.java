package org.fusionsoft.database.artefacts;

import java.sql.Connection;

public interface Server {
    Connection connection();
    String name();
    DbmsSignature dbmsSignature();
}
