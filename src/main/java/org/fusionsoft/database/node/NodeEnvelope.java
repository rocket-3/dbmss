package org.fusionsoft.database.node;

import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;
import org.fusionsoft.database.Node;

public class NodeEnvelope implements Node {
    private final Unchecked<Node> scalar;

    public NodeEnvelope(Scalar<Node> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public String name() {
        return scalar.value().name();
    }

    @Override
    public Iterable<Node> nodes() {
        return scalar.value().nodes();
    }

    @Override
    public String asString() throws Exception {
        return scalar.value().asString();
    }
}
