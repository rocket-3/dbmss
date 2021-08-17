package org.fusionsoft.database.migration;

import java.util.Collection;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.Migration;
import org.fusionsoft.database.Server;

//want to convert to PersistentDbObjectsWithoutConstraints extends Collection<DbObject>
public class DboCollectionPreMigration implements Migration {
    private final Collection<DbObject> persistentDbObjects;
    private final Server server;

    public DboCollectionPreMigration(Collection<DbObject> persistentDbObjects, Server server) {
        this.persistentDbObjects = persistentDbObjects;
        this.server = server;
    }

    @Override
    public boolean validate() throws Exception {
        return true;
    }

    @Override
    public void perform() throws Exception {
        
    }
}
