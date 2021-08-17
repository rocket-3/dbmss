package org.fusionsoft.database.description.dbd.ofyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.io.IOException;
import org.fusionsoft.database.description.dbd.SimpleTable;
import org.fusionsoft.lib.yaml.artefacts.IterableOfClassFromYamlNode;

public class TableOf extends SimpleTable {
    private TableOf(StrictYamlMapping mapping, String name) {
        super(
            name,
            new IterableOfClassFromYamlNode<>(ColumnOf::new, mapping.yamlSequence("columns")),
            new IterableOfClassFromYamlNode<>(ConstraintOf::new, mapping.yamlMapping("constraints")),
            new IterableOfClassFromYamlNode<>(IndexOf::new, mapping.yamlMapping("indexes"))
        );
    }

    public TableOf(YamlMapping containerMapping, YamlNode keyNode) {
        this(
            new StrictYamlMapping(containerMapping.yamlMapping(keyNode)),
            keyNode.asScalar().value()
        );
    }

    public TableOf(String name, String text) throws IOException {
        this(
            new StrictYamlMapping(
                Yaml.createYamlInput(text)
                .readYamlMapping()
            ),
            name
        );
    }
}
