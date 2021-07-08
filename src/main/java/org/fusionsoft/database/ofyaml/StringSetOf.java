package org.fusionsoft.database.ofyaml;

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.set.SetEnvelope;
import org.cactoos.set.SetOf;

public class StringSetOf extends SetEnvelope<String> {
    
    public StringSetOf(YamlMapping yamlMapping, String key) {
        super(
            new SetOf<>(
                new StringIterableOf(yamlMapping, key)
            )
        );
    }
}
