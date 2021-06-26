package org.fusionsoft.database.nodes.repo;

import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.fusionsoft.database.FlatNode;
import org.fusionsoft.database.Node;

public class RepoFileNodesOf extends IterableEnvelope<Node> {
    
    public RepoFileNodesOf(Iterable<FlatNode> flatNodes) {
        super(new IterableOf<>());
    }
}
