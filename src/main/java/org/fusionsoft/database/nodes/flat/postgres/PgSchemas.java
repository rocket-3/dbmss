package org.fusionsoft.database.nodes.flat.postgres;

import java.sql.Connection;
import java.sql.Statement;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterator.IteratorOf;
import org.cactoos.scalar.ScalarOf;
import org.fusionsoft.database.FlatNode;

public class PgSchemas extends IterableEnvelope<FlatNode> {
    public PgSchemas(Connection connection) {
        super(
            new IterableOf<>(
                new ScalarOf<>(
                    () -> {
                        try (Statement stmt = connection.createStatement()) {
                            stmt.executeQuery("");
                        }
                        return new IteratorOf<>();
                    }
                )
            )
        );
    }
}
