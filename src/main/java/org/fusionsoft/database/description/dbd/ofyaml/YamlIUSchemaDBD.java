package org.fusionsoft.database.description.dbd.ofyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.description.dbd.SimpleIUSchemaDBD;
import org.fusionsoft.database.ofyaml.IterableOfClassFromYamlNode;

public class YamlIUSchemaDBD extends SimpleIUSchemaDBD {
    private YamlIUSchemaDBD(StrictYamlMapping mapping, Text key) throws Exception {
        super(
            key.asString(),
            mapping.string("owner"),
            new IterableOfClassFromYamlNode<>(
                YamlIUTableDBD::new,
                mapping.yamlMapping("tables")
            )
        );
    }

    public YamlIUSchemaDBD(YamlMapping containerMapping, YamlNode keyNode) throws Exception {
        this(
            new StrictYamlMapping(containerMapping.yamlMapping(keyNode)),
            new TextOfScalar(() -> keyNode.asScalar().value())
        );
    }
}
