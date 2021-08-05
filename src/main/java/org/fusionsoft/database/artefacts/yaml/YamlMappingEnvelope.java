package org.fusionsoft.database.artefacts.yaml;

import com.amihaiemil.eoyaml.BaseYamlMapping;
import com.amihaiemil.eoyaml.Comment;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Set;

public class YamlMappingEnvelope extends BaseYamlMapping {
    private final YamlMapping yamlMapping;

    public YamlMappingEnvelope(YamlMapping yamlMapping) {
        this.yamlMapping = yamlMapping;
    }

    @Override
    public Set<YamlNode> keys() {
        return yamlMapping.keys();
    }

    @Override
    public YamlNode value(YamlNode key) {
        return yamlMapping.value(key);
    }

    @Override
    public Comment comment() {
        return yamlMapping.comment();
    }
}
