package org.fusionsoft.lib.yaml.artefacts;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlNodeNotFoundException;
import org.cactoos.Fallback;
import org.cactoos.scalar.ScalarOf;
import org.cactoos.scalar.ScalarWithFallback;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOf;

public class MaybeEmptyTextOf extends TextEnvelope {
    
    public MaybeEmptyTextOf(StrictYamlMapping yamlMapping, String key) {
        super(
            new TextOf(
                new ScalarWithFallback<>(
                    new ScalarOf<>(
                        () -> yamlMapping.value(key).asScalar().value()
                    ),
                    new Fallback.From<>(
                        YamlNodeNotFoundException.class, 
                        (e) -> ""
                    )
                )
            )
        );
    }
}
