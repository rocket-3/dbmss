package org.fusionsoft.database.dbms.signature;

import org.fusionsoft.database.DbmsName;
import org.fusionsoft.database.DbmsVersion;

public class PgDbmsSignature extends SimpleDbmsSignature {
    public PgDbmsSignature(DbmsVersion dbmsVersion) {
        super(DbmsName.Postgres, dbmsVersion);
    }
}
