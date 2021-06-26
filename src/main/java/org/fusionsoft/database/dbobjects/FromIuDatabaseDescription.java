package org.fusionsoft.database.dbobjects;

import org.fusionsoft.database.nodes.flat.FlatNodesOf;
import org.fusionsoft.database.nodes.iu.IuDatabaseDescription;

public class FromIuDatabaseDescription extends FromFlatNodes {
    public FromIuDatabaseDescription(IuDatabaseDescription iuDatabaseDescription) {
        super(
            new FlatNodesOf(
                iuDatabaseDescription
            )
        );
    }
}
