package org.fusionsoft.database.artefacts.diff;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import org.cactoos.Scalar;
import org.fusionsoft.database.artefacts.DbObject;
import org.fusionsoft.database.artefacts.DiffPair;

public class DboDiffPairsIteratorScalar implements Scalar<Iterator<? extends DiffPair<DbObject>>> {
    private final Collection<DbObject> persistentDbObjects;
    private final Collection<DbObject> targetDbObjects;

    public DboDiffPairsIteratorScalar(Collection<DbObject> persistentDbObjects, Collection<DbObject> targetDbObjects) {
        this.persistentDbObjects = persistentDbObjects;
        this.targetDbObjects = targetDbObjects;
    }

    @Override
    public Iterator<DiffPair<DbObject>> value() throws Exception {
        return Collections.emptyIterator();
    }
}
