package org.fusionsoft.database.artefacts.migration;

import org.fusionsoft.database.artefacts.Migration;

public class DboCollectionNoConstraintsMigration implements Migration {
    @Override
    public boolean validate() throws Exception {
        return false;
    }

    @Override
    public void perform() throws Exception {

    }
}
