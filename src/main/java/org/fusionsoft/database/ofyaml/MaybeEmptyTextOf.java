package org.fusionsoft.database.ofyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlNodeNotFoundException;
import org.cactoos.scalar.ScalarOf;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOf;

public class MaybeEmptyTextOf extends TextEnvelope {
    
    public MaybeEmptyTextOf(StrictYamlMapping yamlMapping, String key) {
        super(
            new TextOf(
                new ScalarOf<CharSequence>(
                    () -> {
                        try {
                            return yamlMapping.value(key).asScalar().value();
                        } catch (YamlNodeNotFoundException ynfe) {  
                            return "";
                        }
                    }
                )
            )
        );
    }
}
