package org.fusionsoft.database.dbms.signature;

import org.fusionsoft.database.DbmsName;
import org.fusionsoft.database.DbmsSignature;
import org.fusionsoft.database.connection.ConnFromDbdMapping;
import org.fusionsoft.database.yaml.DBDYamlInput;

public class DbmsSignatureFromDbdMapping implements DbmsSignature {
    private final DbmsSignature dbmsSignature;

    public DbmsSignatureFromDbdMapping(DBDYamlInput dbdYamlInput, CharSequence serverName) {
        this.dbmsSignature = new DbmsSignatureFromConnection(
            new ConnFromDbdMapping(
                dbdYamlInput,
                serverName
            )
        );
    }

    @Override
    public DbmsName dbmsName() {
        return dbmsSignature.dbmsName();
    }

    @Override
    public String dbmsVersion() {
        return dbmsSignature.dbmsVersion();
    }
}
