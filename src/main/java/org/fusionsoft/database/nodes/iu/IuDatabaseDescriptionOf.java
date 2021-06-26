package org.fusionsoft.database.nodes.iu;

import org.fusionsoft.database.Node;

public class IuDatabaseDescriptionOf implements IuDatabaseDescription {
    private final Iterable<Node> nodes;

    public IuDatabaseDescriptionOf(Iterable<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public Iterable<IuServerOfDatabaseDescription> serverDescriptions() {
        return null;
    }

    @Override
    public Iterable<IuSchemaOfDatabaseDescription> schemaDescriptions() {
        return null;
    }
}
