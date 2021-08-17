package org.fusionsoft.lib.yaml.artefacts;

import com.amihaiemil.eoyaml.Node;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.ScalarOf;

public class KeysFromYamlNode extends IterableEnvelope<String> {
    
    public KeysFromYamlNode(YamlNode yn) {
        this(yn, "");
    }
    
    public KeysFromYamlNode(YamlNode yn, String path) {
        super(
            new IterableOf<>(
                new ScalarOf<>(
                    () -> {
                        if (yn.type().equals(Node.MAPPING)) {
                            return new Joined<String>(
                                new Mapped<>(
                                    x -> path + x.asScalar().value(),
                                    yn.asMapping().keys()
                                ),
                                new Joined<>(
                                    new Mapped<>(
                                        x -> new KeysFromYamlNode(
                                            yn.asMapping().value(x), 
                                            path + x.asScalar().value() + "/"
                                        ),
                                        yn.asMapping().keys()
                                    )
                                )
                            ).iterator();
                        } else {
                            return new IterableOf<String>().iterator();
                        }
                    }
                )
            )
        );
    }
    
    
}
