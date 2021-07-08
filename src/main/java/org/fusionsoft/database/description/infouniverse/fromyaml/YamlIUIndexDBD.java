package org.fusionsoft.database.description.infouniverse.fromyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.scalar.ScalarOf;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.description.infouniverse.IUIndexDBD;
import org.fusionsoft.database.description.infouniverse.IUIndexDBDEnvelope;
import org.fusionsoft.database.description.infouniverse.SimpleIUIndexDBD;
import org.fusionsoft.database.ofyaml.MaybeEmptyTextOf;

public class YamlIUIndexDBD extends IUIndexDBDEnvelope {
    private YamlIUIndexDBD(StrictYamlMapping mapping, Text key) {
        super(
            new ScalarOf<IUIndexDBD>(
                () -> new SimpleIUIndexDBD(
                    key.asString(),
                    mapping.string("dbColumn"),
                    Boolean.parseBoolean(mapping.string("dbUnique")),
                    new MaybeEmptyTextOf(mapping, "pgIndexType").asString()
                )
            )
        );
    }

    public YamlIUIndexDBD(YamlMapping containerMapping, YamlNode keyNode) {
        this(
            new StrictYamlMapping(containerMapping.yamlMapping(keyNode)),
            new TextOfScalar(() -> keyNode.asScalar().value())
        );
    }
}
