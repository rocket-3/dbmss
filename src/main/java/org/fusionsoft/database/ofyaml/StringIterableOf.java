package org.fusionsoft.database.ofyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNodeNotFoundException;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterator.IteratorOf;
import org.cactoos.scalar.ScalarOf;

public class StringIterableOf extends IterableEnvelope<String> {
    
    private StringIterableOf(StrictYamlMapping yamlMapping, String key) {
        super(
            new Mapped<>(
                x -> x.asScalar().value(),
                new IterableOf<>(
                    new ScalarOf<>(
                        () -> {
                            try {
                                return yamlMapping.value(key)
                                .asSequence()
                                .iterator();
                            } catch (YamlNodeNotFoundException ynnfe) {
                                return new IteratorOf<>();
                            }
                        }
                    )
                )
            )
        );
    }
    
    public StringIterableOf(YamlMapping yamlMapping, String key) {
        this(new StrictYamlMapping(yamlMapping), key);
    }
}
