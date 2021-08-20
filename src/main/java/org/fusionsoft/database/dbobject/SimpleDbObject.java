package org.fusionsoft.database.dbobject;

import java.util.Collection;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DbObjectSignature;
import org.fusionsoft.database.StringProperty;

public class SimpleDbObject implements DbObject {
    private final DbObjectSignature dbObjectSignature;
    private final Collection<StringProperty> properties;

    public SimpleDbObject(DbObjectSignature dbObjectSignature, Collection<StringProperty> properties) {
        this.dbObjectSignature = dbObjectSignature;
        this.properties = properties;
    }

    @Override
    public final DbObjectSignature signature() {
        return this.dbObjectSignature;
    }

    @Override
    public final Collection<StringProperty> props() {
        return this.properties;
    }
}
