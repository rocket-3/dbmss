package org.fusionsoft.database.description.infouniverse.ofyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.io.IOException;
import org.fusionsoft.database.description.infouniverse.SimpleIUTableDBD;
import org.fusionsoft.database.ofyaml.IterableOfClassFromYamlNode;

public class YamlIUTableDBD extends SimpleIUTableDBD {
    private YamlIUTableDBD(StrictYamlMapping mapping, String name) {
        super(
            name,
            new IterableOfClassFromYamlNode<>(YamlIUColumnDBD::new, mapping.yamlSequence("columns")),
            new IterableOfClassFromYamlNode<>(YamlIUConstraintDBD::new, mapping.yamlMapping("constraints")),
            new IterableOfClassFromYamlNode<>(YamlIUIndexDBD::new, mapping.yamlMapping("indexes"))
        );
    }

    public YamlIUTableDBD(YamlMapping containerMapping, YamlNode keyNode) {
        this(
            new StrictYamlMapping(containerMapping.yamlMapping(keyNode)),
            keyNode.asScalar().value()
        );
    }

    public YamlIUTableDBD(String name, String text) throws IOException {
        this(
            new StrictYamlMapping(
                Yaml.createYamlInput(text)
                .readYamlMapping()
            ),
            name
        );
    }
}
