package org.fusionsoft.database.description.dbd.ofyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.description.dbd.SimpleSchema;
import org.fusionsoft.lib.yaml.artefacts.IterableOfClassFromYamlNode;

public class SchemaOf extends SimpleSchema {
    private SchemaOf(StrictYamlMapping mapping, Text key) throws Exception {
        super(
            key.asString(),
            mapping.string("owner"),
            new IterableOfClassFromYamlNode<>(
                TableOf::new,
                mapping.yamlMapping("tables")
            )
        );
    }

    public SchemaOf(YamlMapping containerMapping, YamlNode keyNode) throws Exception {
        this(
            new StrictYamlMapping(containerMapping.yamlMapping(keyNode)),
            new TextOfScalar(() -> keyNode.asScalar().value())
        );
    }
}
