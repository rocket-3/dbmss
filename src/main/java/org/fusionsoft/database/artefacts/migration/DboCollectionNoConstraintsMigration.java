package org.fusionsoft.database.artefacts.migration;

import java.util.Collection;
import org.fusionsoft.database.artefacts.DbObject;
import org.fusionsoft.database.artefacts.DiffPair;
import org.fusionsoft.database.artefacts.Migration;
import org.fusionsoft.database.artefacts.RestoreParams;
import org.fusionsoft.database.artefacts.Server;

public class DboCollectionNoConstraintsMigration implements Migration {
    private final Collection<DiffPair<DbObject>> diffDBOs;
    private final RestoreParams restoreParams;
    private final Server server;

    public DboCollectionNoConstraintsMigration(Collection<DiffPair<DbObject>> diffDBOs, RestoreParams restoreParams, Server server) {
        this.diffDBOs = diffDBOs;
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
