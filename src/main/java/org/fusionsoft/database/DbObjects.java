package org.fusionsoft.database;

import java.util.Iterator;
import org.cactoos.Scalar;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;

public abstract class DbObjects extends IterableEnvelope<DbObject> {

    public DbObjects(Scalar<Iterator<? extends DbObject>> scalar) {
        super(
            new IterableOf<DbObject>(
                scalar
            )
        );
    }
    
    public DbObjects(Iterable<DbObject> dbObjectsIterable) {
        super(
            dbObjectsIterable
        );
    }
}
