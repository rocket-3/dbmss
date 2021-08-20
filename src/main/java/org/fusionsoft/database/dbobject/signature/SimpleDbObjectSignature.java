package org.fusionsoft.database.dbobject.signature;

import org.cactoos.Text;
import org.fusionsoft.database.DbObjectSignature;
import org.fusionsoft.database.DbObjectType;
import org.fusionsoft.database.DbmsSignature;

public class SimpleDbObjectSignature implements DbObjectSignature {
    private final Text name;
    private final Text parentName;
    private final DbObjectType dbObjectType;
    private final DbmsSignature dbmsSignature;

    public SimpleDbObjectSignature(Text name, Text parentName, DbObjectType dbObjectType, DbmsSignature dbmsSignature) {
        this.name = name;
        this.parentName = parentName;
        this.dbObjectType = dbObjectType;
        this.dbmsSignature = dbmsSignature;
    }

    @Override
    public final String name() {
        return this.name.toString();
    }

    @Override
    public final String parentName() {
        return this.parentName.toString();
    }

    @Override
    public final DbObjectType type() {
        return this.dbObjectType;
    }

    @Override
    public final DbmsSignature dbmsSignature() {
        return this.dbmsSignature;
    }
}
