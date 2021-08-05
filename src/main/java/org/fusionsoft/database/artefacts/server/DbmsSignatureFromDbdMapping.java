package org.fusionsoft.database.artefacts.server;

import org.fusionsoft.database.artefacts.DbmsName;
import org.fusionsoft.database.artefacts.DbmsSignature;
import org.fusionsoft.database.artefacts.connection.ConnFromDbdMapping;
import org.fusionsoft.database.artefacts.yaml.YamlDBDMapping;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DbmsSignatureFromDbdMapping implements DbmsSignature {
    private final DbmsSignature dbmsSignature;

    public DbmsSignatureFromDbdMapping(YamlDBDMapping yamlDbdMapping, CharSequence serverName) {
        this.dbmsSignature = new DbmsSignatureFromConnection(
            new ConnFromDbdMapping(
                yamlDbdMapping,
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
