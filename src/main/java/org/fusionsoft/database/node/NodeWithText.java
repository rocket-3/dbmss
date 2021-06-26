package org.fusionsoft.database.node;

import java.util.Collections;
import org.fusionsoft.database.Node;

public class NodeWithText implements Node {
    private final CharSequence name;
    private final CharSequence value;

    public NodeWithText(CharSequence name, CharSequence value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String name() {
        return String.valueOf(this.name);
    }

    @Override
    public String asString() {
        return String.valueOf(this.value);
    }

    @Override
    public Iterable<Node> nodes() {
        return Collections.emptySet();
    }
}
