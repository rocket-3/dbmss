package org.fusionsoft.database.nodes;

import org.cactoos.Text;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.Node;
import org.yaml.snakeyaml.Yaml;

public class FromYaml extends IterableEnvelope<Node> {

    public FromYaml(Text text) {
        super(
            new IterableOf<Node>(
                ()->{
                    return new Yaml(
                        new NodeYamlConstructor()
                    )
                    .loadAs(
                        new UncheckedText(text).asString(),
                        Node.class
                    )
                    .nodes()
                    .iterator();
                }
            )
        );
    }

}
