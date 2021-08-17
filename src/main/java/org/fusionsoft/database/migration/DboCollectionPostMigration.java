package org.fusionsoft.database.migration;

import java.util.Collection;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.Migration;
import org.fusionsoft.database.Server;

public class DboCollectionPostMigration implements Migration {
    private final Collection<DbObject> targetDbObjects;
    private final Server server;

    public DboCollectionPostMigration(Collection<DbObject> targetDbObjects, Server server) {
        this.targetDbObjects = targetDbObjects;
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
