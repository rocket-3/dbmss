package org.fusionsoft.database.server;

import java.sql.Connection;
import org.fusionsoft.database.DbmsSignature;
import org.fusionsoft.database.Server;
import org.fusionsoft.database.connection.ConnFromDbdMapping;
import org.fusionsoft.database.yaml.DBDYamlInput;

public class ServerFromDbdMapping implements Server {
    private final Connection connection;
    private final DbmsSignature dbmsSignature;
    private final CharSequence serverName;

    public ServerFromDbdMapping(DBDYamlInput dbdYamlInput, CharSequence serverName) {
        this.connection = new ConnFromDbdMapping(dbdYamlInput, serverName);
        this.dbmsSignature = new DbmsSignatureFromDbdMapping(dbdYamlInput, serverName);
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
