package org.fusionsoft.database.dbobjects.postgres;

import org.cactoos.iterable.IterableOf;
import org.fusionsoft.database.DbObjects;
import org.fusionsoft.database.FlatNode;

public class FromPgTypeFlatNodes extends DbObjects {
    public FromPgTypeFlatNodes(Iterable<FlatNode> nodes) {
        super(
            new IterableOf<>()
        );
    }
}
