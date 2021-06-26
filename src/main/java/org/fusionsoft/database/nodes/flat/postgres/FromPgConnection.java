package org.fusionsoft.database.nodes.flat.postgres;

import java.sql.Connection;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Joined;
import org.fusionsoft.database.FlatNode;

public class FromPgConnection extends IterableEnvelope<FlatNode> {

    public FromPgConnection(Connection connection) {
        super(
            new Joined<FlatNode>(
                new PgSchemas(connection),
                new PgTables(connection)
            )
        );
    }


}
