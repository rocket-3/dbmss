package org.fusionsoft.database.migration;

import java.util.Collection;
import org.cactoos.list.ListOf;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DiffPair;
import org.fusionsoft.database.Migration;
import org.fusionsoft.database.RestoreParams;
import org.fusionsoft.database.Server;
import org.fusionsoft.database.diffpair.DbObjectDiffPairs;

public class DboCollectionNoConstraintsMigration implements Migration {
    private final Collection<DiffPair<DbObject>> diffDBOs;
    private final RestoreParams restoreParams;
    private final Server server;

    public DboCollectionNoConstraintsMigration(
        Collection<DbObject> persistentDBOs,
        Collection<DbObject> targetDBOs, 
        RestoreParams restoreParams, 
        Server server
    ) {
        this.diffDBOs = new ListOf<>( 
            new DbObjectDiffPairs(persistentDBOs, targetDBOs)
        );
        this.restoreParams = restoreParams;
        this.server = server;
    }

    @Override
    public boolean validate() throws Exception {
        return false;
    }

    @Override
    public void perform() throws Exception {

    }
}
