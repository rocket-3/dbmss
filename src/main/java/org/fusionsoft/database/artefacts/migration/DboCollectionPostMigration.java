package org.fusionsoft.database.artefacts.migration;

import java.util.Collection;
import org.fusionsoft.database.artefacts.DbObject;
import org.fusionsoft.database.artefacts.Migration;
import org.fusionsoft.database.artefacts.Server;

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
