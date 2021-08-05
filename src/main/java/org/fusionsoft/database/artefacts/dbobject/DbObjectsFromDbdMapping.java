package org.fusionsoft.database.artefacts.dbobject;

import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.list.ListOf;
import org.fusionsoft.database.artefacts.DbObject;
import org.fusionsoft.database.artefacts.DbmsSignature;
import org.fusionsoft.database.description.dbd.DBD;

public class DbObjectsFromDbdMapping extends CollectionEnvelope<DbObject> {
    
    public DbObjectsFromDbdMapping(DBD dbd, DbmsSignature dbmsSignature) {
        super(new ListOf<DbObject>());
    }
}
