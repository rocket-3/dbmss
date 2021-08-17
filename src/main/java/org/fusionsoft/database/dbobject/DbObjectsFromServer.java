package org.fusionsoft.database.dbobject;

import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.set.SetOf;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.Server;

public class DbObjectsFromServer extends CollectionEnvelope<DbObject> {
    public DbObjectsFromServer(Server server) {
        super(new SetOf<DbObject>());
    }
}
