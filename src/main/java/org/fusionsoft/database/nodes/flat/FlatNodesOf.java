package org.fusionsoft.database.nodes.flat;

import java.nio.file.Path;
import java.sql.Connection;
import org.cactoos.iterable.IterableEnvelope;
import org.fusionsoft.database.FlatNode;
import org.fusionsoft.database.nodes.iu.IuDatabaseDescription;

public class FlatNodesOf extends IterableEnvelope<FlatNode> {
    public FlatNodesOf(IuDatabaseDescription iuDatabaseDescription) {
        super(
            new FromIuDatabaseDescription(iuDatabaseDescription)
        );
    }
    public FlatNodesOf(Connection connection) {
        super(
            new FromAnyConnection(connection)
        );
    }
    public FlatNodesOf(Path pathOfDbGitRepo) {
        super(
            new FromDbGitRepo(pathOfDbGitRepo)
        );
    }
}
