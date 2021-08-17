package org.fusionsoft.database.condition;

import org.fusionsoft.database.Condition;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DbmsSignature;

public class DboHasDbmsSignature implements Condition {
    private final DbmsSignature dbmsSignature;
    private final DbObject dbObject;

    public DboHasDbmsSignature(DbmsSignature dbmsSignature, DbObject dbObject) {
        this.dbmsSignature = dbmsSignature;
        this.dbObject = dbObject;
    }

    @Override
    public Boolean value() throws Exception {
        return dbObject.signature().dbmsSignature().equals(dbmsSignature);
    }
}
