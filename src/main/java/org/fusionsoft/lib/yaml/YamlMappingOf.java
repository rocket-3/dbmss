package org.fusionsoft.lib.yaml;

import com.amihaiemil.eoyaml.YamlInput;
import org.cactoos.scalar.Sticky;

public class YamlMappingOf extends YamlMappingEnvelope {
    public YamlMappingOf(YamlInput yamlInput) {
        super(
            new YamlMappingOfScalar(
                new Sticky<>(
                    yamlInput::readYamlMapping
                )
            )
        );
    }
}
