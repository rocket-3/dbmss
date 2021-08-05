package org.fusionsoft.database.artefacts.migration;

import org.fusionsoft.database.artefacts.dbobject.DbObjectsFromDbdMapping;
import org.fusionsoft.database.artefacts.dbobject.DbObjectsFromServer;
import org.fusionsoft.database.artefacts.yaml.YamlDBDMapping;
import org.fusionsoft.database.artefacts.Migration;
import org.fusionsoft.database.artefacts.RestoreParams;
import org.fusionsoft.database.artefacts.Server;
import org.fusionsoft.database.artefacts.server.ServerFromDbdMapping;
import org.fusionsoft.database.description.dbd.ofyaml.YamlIUDBD;

public class DBDToServerMigration implements Migration {
    private final Server server;
    private final Migration migration;

    public DBDToServerMigration(YamlDBDMapping yamlDbdMapping, CharSequence serverName, RestoreParams restoreParams) {
        this.server = new ServerFromDbdMapping(yamlDbdMapping, serverName);
        this.migration = new DboCollectionMigration(
            server, 
            new DbObjectsFromServer(server),
            new DbObjectsFromDbdMapping(
                new YamlIUDBD(yamlDbdMapping), 
                server.dbmsSignature()
            ),
            restoreParams
        );
    }
    @Override
    public boolean validate() throws Exception {
        return this.migration.validate();
    }

    @Override
    public void perform() throws Exception {
        this.migration.perform();
    }
    
}
