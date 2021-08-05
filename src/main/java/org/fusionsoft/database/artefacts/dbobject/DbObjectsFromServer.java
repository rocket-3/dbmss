package org.fusionsoft.database.artefacts.dbobject;

import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.set.SetOf;
import org.fusionsoft.database.artefacts.DbObject;
import org.fusionsoft.database.artefacts.Server;

public class DbObjectsFromServer extends CollectionEnvelope<DbObject> {
    public DbObjectsFromServer(Server server) {
        super(new SetOf<DbObject>());
    }
}
