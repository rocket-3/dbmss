package org.fusionsoft.database.dbobject.signature;

import org.cactoos.Text;
import org.fusionsoft.database.DbObjectType;
import org.fusionsoft.database.DbmsSignature;

public class TableDbObjectSignature extends SimpleDbObjectSignature {
    public TableDbObjectSignature(Text name, Text parentName, DbmsSignature dbmsSignature) {
        super(name, parentName, DbObjectType.Table, dbmsSignature);
    }
}
