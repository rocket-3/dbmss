package org.fusionsoft.database.migration;

import java.util.Collection;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.dbobject.DbObjectsFromDbd;
import org.fusionsoft.database.dbobject.DbObjectsFromServer;
import org.fusionsoft.database.yaml.DBDYamlInput;
import org.fusionsoft.database.Migration;
import org.fusionsoft.database.RestoreParams;
import org.fusionsoft.database.Server;
import org.fusionsoft.database.server.ServerFromDbdMapping;
import org.fusionsoft.database.description.dbd.ofyaml.DbdOf;

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
    /*public*/private DBDToServerMigration(DBDYamlInput dbdYamlInput, Server server, RestoreParams restoreParams) {
        this(
            server,
            new DbObjectsFromServer(server),
            new DbObjectsFromDbd(
                new DbdOf(dbdYamlInput),
                server.dbmsSignature()
            ),
            restoreParams
        );
    }

    public DBDToServerMigration(DBDYamlInput dbdYamlInput, CharSequence serverName, RestoreParams restoreParams) {
        this(
            dbdYamlInput,
            new ServerFromDbdMapping(dbdYamlInput, serverName),
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
