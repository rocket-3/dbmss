package org.fusionsoft.database.nodes.flat;

import java.sql.Connection;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.ScalarOf;
import org.fusionsoft.database.FlatNode;
import org.fusionsoft.database.nodes.flat.postgres.FromPgConnection;

public class FromAnyConnection extends IterableEnvelope<FlatNode> {

    public FromAnyConnection(Connection connection) {
        super(
            new IterableOf<>(
                new ScalarOf<>(
                    () -> {
                        final String productName = connection.getMetaData().getDatabaseProductName();
                        switch (productName) {
                            case "PostgreSQL": return new FromPgConnection(connection).iterator();
                            default: throw new Exception("Unknown db type: " + productName);
                        }
                    }
                )
            )
        );
    }

}
