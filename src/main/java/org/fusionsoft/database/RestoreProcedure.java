package org.fusionsoft.database;

import java.nio.file.Path;
import org.fusionsoft.database.is.PersistentISFromYamlDBDInput;
import org.fusionsoft.lib.functional.RunnableWithException;

//root thing, actually very close to CMDRestore
public class RestoreProcedure implements RunnableWithException {
    private final IS<?> instanceState;

    public RestoreProcedure(Path pathToDbd, CharSequence serverName, RestoreParams restoreParams) {
        this.instanceState = new PersistentISFromYamlDBDInput(
            pathToDbd,
            serverName,
            restoreParams
        );
        
    }
    
    //how to split execution and configuration? 
    //all now for configuration and we have only simple run
    
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
