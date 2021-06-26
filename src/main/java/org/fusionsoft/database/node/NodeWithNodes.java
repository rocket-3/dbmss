package org.fusionsoft.database.node;

import org.cactoos.iterable.IterableOf;
import org.cactoos.text.Joined;
import org.cactoos.text.TextOf;
import org.fusionsoft.database.Node;

public class NodeWithNodes implements Node {
    private final CharSequence name;
    private final Iterable<Node> nodes;

    public NodeWithNodes(CharSequence name, Iterable<Node> nodes) {
        this.name = name;
        this.nodes = nodes;
    }
    public NodeWithNodes(CharSequence name, Node... nodes) {
        this(name, new IterableOf<>(nodes));
    }

    @Override
    public String name() {
        return String.valueOf(this.name);
    }

    @Override
    public String asString() throws Exception {
        return new Joined(new TextOf("\n"), this.nodes).asString();
    }

    @Override
    public Iterable<Node> nodes() {
        return this.nodes;
    }
}
