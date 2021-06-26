package org.fusionsoft.database.nodes;

import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;

public class NodeYamlConstructor extends Constructor {
    public NodeYamlConstructor() {
    }

    @Override
    protected Construct getConstructor(org.yaml.snakeyaml.nodes.Node node) {
        
        return new Construct() {
            @Override
            public Object construct(Node node) {
                throw new RuntimeException("parsing of YAML is not supported");
            }
            
            @Override
            public void construct2ndStep(Node node, Object object) {
                throw new RuntimeException("recursive nodes are not supported");
            }
        };
        
    }
}
