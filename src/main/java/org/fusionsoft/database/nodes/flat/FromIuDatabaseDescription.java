package org.fusionsoft.database.nodes.flat;

import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.fusionsoft.database.FlatNode;
import org.fusionsoft.database.nodes.iu.IuDatabaseDescription;

public class FromIuDatabaseDescription extends IterableEnvelope<FlatNode> {
    public FromIuDatabaseDescription(IuDatabaseDescription iuDatabaseDescription) {
        super(new IterableOf<>());
    }
}
