package org.fusionsoft.database.artefacts.migration;

import java.util.Collection;
import org.fusionsoft.database.artefacts.DbObject;
import org.fusionsoft.database.artefacts.dbobject.DbObjectsFromDbdMapping;
import org.fusionsoft.database.artefacts.dbobject.DbObjectsFromServer;
import org.fusionsoft.database.artefacts.yaml.YamlDBDMapping;
import org.fusionsoft.database.artefacts.Migration;
import org.fusionsoft.database.artefacts.RestoreParams;
import org.fusionsoft.database.artefacts.Server;
import org.fusionsoft.database.artefacts.server.ServerFromDbdMapping;
import org.fusionsoft.database.description.dbd.ofyaml.YamlIUDBD;

public class DBDToServerMigration implements Migration {
    private final Migration migration;

    private DBDToServerMigration(Migration migration){
        this.migration = migration;
    }
    
    private DBDToServerMigration(Server server, Collection<DbObject> persistentDBOs, Collection<DbObject> targetDBOs, RestoreParams restoreParams){
        this(
            new DboCollectionMigration(
                server,
                persistentDBOs,
                targetDBOs,
                restoreParams
            )
        );
    }
    
    //there we can insert our stick on dbd by server validation
    /*public*/private DBDToServerMigration(YamlDBDMapping yamlDbdMapping, Server server, RestoreParams restoreParams) {
        this(
            server,
            new DbObjectsFromServer(server),
            new DbObjectsFromDbdMapping(
                new YamlIUDBD(yamlDbdMapping),
                server.dbmsSignature()
            ),
            restoreParams
        );
    }

    public DBDToServerMigration(YamlDBDMapping yamlDbdMapping, CharSequence serverName, RestoreParams restoreParams) {
        this(
            yamlDbdMapping,
            new ServerFromDbdMapping(yamlDbdMapping, serverName),
            restoreParams
        );
    }
    
//    public DBDToServerMigration(YamlDBDMapping yamlDbdMapping, CharSequence serverName, RestoreParams restoreParams) {
//        this.server = new ServerFromDbdMapping(yamlDbdMapping, serverName);
//        this.migration = new DboCollectionMigration(
//            server, 
//            new DbObjectsFromServer(server),
//            new DbObjectsFromDbdMapping(
//                new YamlIUDBD(yamlDbdMapping), 
//                server.dbmsSignature()
//            ),
//            restoreParams
//        );
//    }
    @Override
    public boolean validate() throws Exception {
        return this.migration.validate();
    }

    @Override
    public void perform() throws Exception {
        this.migration.perform();
    }
    
}
