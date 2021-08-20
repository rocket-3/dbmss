package org.fusionsoft.database.dbms.signature;

import org.fusionsoft.database.DbmsName;
import org.fusionsoft.database.DbmsSignature;
import org.fusionsoft.database.DbmsVersion;

public class SimpleDbmsSignature implements DbmsSignature{
    private final DbmsName dbmsName;
    private final DbmsVersion dbmsVersion;

    public SimpleDbmsSignature(DbmsName dbmsName, DbmsVersion dbmsVersion) {
        this.dbmsName = dbmsName;
        this.dbmsVersion = dbmsVersion;
    }

    @Override
    public DbmsName dbmsName() {
        return this.dbmsName;
    }

    @Override
    public String dbmsVersion() {
        return this.dbmsVersion.toString();
    }
}
