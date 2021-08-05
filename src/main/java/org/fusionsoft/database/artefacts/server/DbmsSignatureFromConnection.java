package org.fusionsoft.database.artefacts.server;

import java.sql.Connection;
import org.fusionsoft.database.artefacts.DbmsName;
import org.fusionsoft.database.artefacts.DbmsSignature;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DbmsSignatureFromConnection implements DbmsSignature {
    private final Connection connection;

    public DbmsSignatureFromConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public DbmsName dbmsName() {
        throw new NotImplementedException();
    }

    @Override
    public String dbmsVersion() {
        throw new NotImplementedException();
    }
}
