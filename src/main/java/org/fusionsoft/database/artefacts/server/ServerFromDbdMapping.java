package org.fusionsoft.database.artefacts.server;

import java.sql.Connection;
import org.fusionsoft.database.artefacts.DbmsSignature;
import org.fusionsoft.database.artefacts.Server;
import org.fusionsoft.database.artefacts.connection.ConnFromDbdMapping;
import org.fusionsoft.database.artefacts.server.DbmsSignatureFromDbdMapping;
import org.fusionsoft.database.artefacts.yaml.YamlDBDMapping;

public class ServerFromDbdMapping implements Server {
    private final Connection connection;
    private final DbmsSignature dbmsSignature;
    private final CharSequence serverName;

    public ServerFromDbdMapping(YamlDBDMapping yamlDbdMapping, CharSequence serverName) {
        this.connection = new ConnFromDbdMapping(yamlDbdMapping, serverName);
        this.dbmsSignature = new DbmsSignatureFromDbdMapping(yamlDbdMapping, serverName);
        this.serverName = serverName;
    }

    @Override
    public Connection connection() {
        return this.connection;
    }

    @Override
    public String name() {
        return String.valueOf(serverName);
    }

    @Override
    public DbmsSignature dbmsSignature() {
        return dbmsSignature;
    }
}
