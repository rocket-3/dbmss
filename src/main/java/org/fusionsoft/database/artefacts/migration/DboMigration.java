package org.fusionsoft.database.artefacts.migration;

import org.fusionsoft.database.artefacts.DbObject;
import org.fusionsoft.database.artefacts.DiffPair;
import org.fusionsoft.database.artefacts.Migration;


//generates dbms/object type specific migration
public class DboMigration implements Migration {
    private final DiffPair<DbObject> diffPair;

    public DboMigration(DiffPair<DbObject> diffPair) {
        this.diffPair = diffPair;
    }

    @Override
    public boolean validate() throws Exception {
        return false;
    }

    @Override
    public void perform() throws Exception {

    }
}
