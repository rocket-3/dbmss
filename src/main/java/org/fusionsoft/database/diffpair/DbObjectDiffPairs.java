package org.fusionsoft.database.diffpair;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import org.cactoos.Scalar;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterator.IteratorEnvelope;
import org.cactoos.iterator.IteratorOf;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DiffPair;

public class DbObjectDiffPairs extends IterableEnvelope<DiffPair<DbObject>> {

    public DbObjectDiffPairs(Collection<DbObject> persistentDbObjects, Collection<DbObject> targetDbObjects) {
        super(
            new IterableOf<>(
                IteratorOf<DiffPair<DbObject>>::new
            )
        );
    }

}
