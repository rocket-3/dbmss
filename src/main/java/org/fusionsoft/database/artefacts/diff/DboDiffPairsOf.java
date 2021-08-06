package org.fusionsoft.database.artefacts.diff;

import java.util.Collection;
import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.list.ListOf;
import org.fusionsoft.database.artefacts.DbObject;
import org.fusionsoft.database.artefacts.DiffPair;

public class DboDiffPairsOf extends CollectionEnvelope<DiffPair<DbObject>> {
    private final Collection<DbObject> persistentDbObjects;
    private final Collection<DbObject> targetDbObjects;
    public DboDiffPairsOf(Collection<DbObject> persistentDbObjects, Collection<DbObject> targetDbObjects) {
        super(
            new ListOf<>(
                new IterableOf<>(
                    new DboDiffPairsIteratorScalar(
                        persistentDbObjects,
                        targetDbObjects
                    )
                )
            )
        );
        this.persistentDbObjects = persistentDbObjects;
        this.targetDbObjects = targetDbObjects;
    }
}