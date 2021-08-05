package org.fusionsoft.database.artefacts.condition;

import org.fusionsoft.database.artefacts.Condition;
import org.fusionsoft.database.artefacts.DbObject;
import org.fusionsoft.database.artefacts.DbmsSignature;

public class CndDbObjectHaveSameDBMSSignature implements Condition {
    private final DbmsSignature dbmsSignature;
    private final DbObject dbObject;

    public CndDbObjectHaveSameDBMSSignature(DbmsSignature dbmsSignature, DbObject dbObject) {
        this.dbmsSignature = dbmsSignature;
        this.dbObject = dbObject;
    }

    @Override
    public Boolean value() throws Exception {
        return dbObject.signature().dbmsSignature().equals(dbmsSignature);
    }
}
