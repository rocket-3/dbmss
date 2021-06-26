package org.fusionsoft.database;

import org.cactoos.Text;

public interface Node extends Text {
    String name();
    Iterable<Node> nodes();
}
