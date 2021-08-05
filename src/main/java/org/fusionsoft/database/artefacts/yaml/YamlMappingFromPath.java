package org.fusionsoft.database.artefacts.yaml;

import com.amihaiemil.eoyaml.Yaml;
import java.io.IOException;
import java.nio.file.Path;

public class YamlMappingFromPath extends YamlMappingEnvelope {
    public YamlMappingFromPath(Path pathToYamlMapping) throws IOException {
        super(Yaml.createYamlInput(pathToYamlMapping.toFile()).readYamlMapping());
    }
}
