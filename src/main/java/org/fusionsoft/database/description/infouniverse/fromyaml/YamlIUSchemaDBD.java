package org.fusionsoft.database.description.infouniverse.fromyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.ScalarOf;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.description.infouniverse.IUSchemaDBDEnvelope;
import org.fusionsoft.database.description.infouniverse.SimpleIUSchemaDBD;

public class YamlIUSchemaDBD extends IUSchemaDBDEnvelope {
    private YamlIUSchemaDBD(StrictYamlMapping mapping, Text key) {
        super(
            new ScalarOf<>(
                () -> new SimpleIUSchemaDBD(
                    key.asString(),
                    mapping.string("owner"),
                    new Mapped<>(
                        k -> new YamlIUTableDBD(mapping.yamlMapping("tables"), k),
                        mapping.yamlMapping("tables").keys()
                    )
                )
            )
        );
    }

    public YamlIUSchemaDBD(YamlMapping containerMapping, YamlNode keyNode) {
        this(
            new StrictYamlMapping(containerMapping.yamlMapping(keyNode)),
            new TextOfScalar(() -> keyNode.asScalar().value())
        );
    }
}
