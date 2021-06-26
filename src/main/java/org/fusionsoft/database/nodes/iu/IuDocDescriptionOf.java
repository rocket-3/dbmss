package org.fusionsoft.database.nodes.iu;

import java.util.Map;
import org.cactoos.map.MapOf;
import org.fusionsoft.database.Node;

public class IuDocDescriptionOf implements IuDocDescription {
    private final Map<String, Node> nodes;

    public IuDocDescriptionOf(Iterable<Node> nodes) {
        this.nodes = new MapOf<>(Node::name, node -> node, nodes);
    }

    @Override
    public String type() throws Exception {
        return nodes.get("type").asString();
    }

    @Override
    public String title() {
        return null;
    }

    @Override
    public String version() {
        return null;
    }

    @Override
    public String summary() {
        return null;
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String termsOfService() {
        return null;
    }

    @Override
    public String contact() {
        return null;
    }

    @Override
    public String license() {
        return null;
    }

    @Override
    public IuDatabaseDescription databaseDescription() {
        return null;
    }
}
