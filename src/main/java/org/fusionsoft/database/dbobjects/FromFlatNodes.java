package org.fusionsoft.database.dbobjects;

import org.cactoos.iterable.IterableOf;
import org.fusionsoft.database.FlatNode;
import org.fusionsoft.database.DbObjects;

public class FromFlatNodes extends DbObjects {
    public FromFlatNodes(Iterable<FlatNode> nodes) {
        super(
            new IterableOf<>()
        );
    }
}
