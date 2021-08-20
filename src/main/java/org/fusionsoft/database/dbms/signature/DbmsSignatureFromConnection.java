package org.fusionsoft.database.dbms.signature;

import java.sql.Connection;
import org.fusionsoft.database.DbmsName;
import org.fusionsoft.database.DbmsSignature;
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
