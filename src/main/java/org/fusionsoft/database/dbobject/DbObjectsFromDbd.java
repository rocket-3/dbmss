package org.fusionsoft.database.dbobject;

import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.list.ListOf;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DbmsSignature;
import org.fusionsoft.database.description.dbd.DBD;

public class DbObjectsFromDbd extends CollectionEnvelope<DbObject> {
    
    public DbObjectsFromDbd(DBD dbd, DbmsSignature dbmsSignature) {
        super(new ListOf<DbObject>());
    }
}
