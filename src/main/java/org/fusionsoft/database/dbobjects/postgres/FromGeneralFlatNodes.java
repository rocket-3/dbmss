package org.fusionsoft.database.dbobjects.postgres;

import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.ScalarOf;
import org.fusionsoft.database.DbObjects;
import org.fusionsoft.database.FlatNode;

public class FromGeneralFlatNodes extends DbObjects {
    public FromGeneralFlatNodes(Iterable<FlatNode> nodes) {
        super(
            new IterableOf<>(
                new ScalarOf<>(() -> {
                    /*if(...)*/
                    return new FromPgTypeFlatNodes(nodes).iterator();
                })
            )
        );
    }
}
