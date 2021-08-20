package org.fusionsoft.database.dbobject;

import java.util.Collection;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DbObjectSignature;
import org.fusionsoft.database.StringProperty;

public class DbObjectEnvelope implements DbObject {
    private final DbObject origin;

    public DbObjectEnvelope(DbObject origin) {
        this.origin = origin;
    }

    @Override
    public DbObjectSignature signature() {
        return this.origin.signature();
    }

    @Override
    public Collection<StringProperty> props() {
        return this.origin.props();
    }
}
