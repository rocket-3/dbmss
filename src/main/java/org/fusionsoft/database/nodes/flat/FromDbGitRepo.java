package org.fusionsoft.database.nodes.flat;

import java.nio.file.Path;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.fusionsoft.database.FlatNode;

public class FromDbGitRepo extends IterableEnvelope<FlatNode> {

    public FromDbGitRepo(Path pathOfDbGitRepo) {
        super(
            new IterableOf<>(
                
            )
        );
    }

}
