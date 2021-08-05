package org.fusionsoft.database.artefacts;

import java.io.IOException;
import java.nio.file.Path;
import org.fusionsoft.database.artefacts.is.PersistentISFromDBD;
import org.fusionsoft.database.artefacts.migration.DBDToServerMigration;
import org.fusionsoft.database.artefacts.yaml.YamlDBDMapping;
import org.fusionsoft.database.artefacts.yaml.YamlMappingFromPath;

//root thing, actually very close to CMDRestore
public class RestoreProcedure implements RunnableWithException {
    private final IS<YamlDBDMapping> instanceState;

    public RestoreProcedure(Path pathToDbd, CharSequence serverName, RestoreParams restoreParams) throws IOException {
        this.instanceState = new PersistentISFromDBD(
            new DBDToServerMigration(
                new YamlDBDMapping(new YamlMappingFromPath(pathToDbd)),
                serverName,     //this two can be moved 
                restoreParams   //to `execution part`
            )
        );
        
    }


    //how to split execution and configuration? 
    //all now for configuration and we have only simple run
    //
    
    @Override
    public void run() throws Exception {
        this.instanceState.restore().perform();
        //0. get server from path
        //1. get `server DBMS type and version`
        //2. get DBD with needed DBMS t/v from path (or fail) / TIS
        //3. get DBD with same DBMS t/v from connection, obtained from HEADER / PIS
        //4. create migration PIS -> TIS (PIS must become TIS, that way)
    }
}
