package org.fusionsoft.database.artefacts.migration;

import java.util.Collection;
import org.fusionsoft.database.artefacts.DbObject;
import org.fusionsoft.database.artefacts.Migration;
import org.fusionsoft.database.artefacts.Server;

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
