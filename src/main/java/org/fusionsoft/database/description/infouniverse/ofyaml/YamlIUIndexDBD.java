package org.fusionsoft.database.description.infouniverse.ofyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.description.infouniverse.SimpleIUIndexDBD;
import org.fusionsoft.database.ofyaml.MaybeEmptyTextOf;

public class YamlIUIndexDBD extends SimpleIUIndexDBD {
    private YamlIUIndexDBD(StrictYamlMapping mapping, Text key) throws Exception {
        super(
            key.asString(),
            mapping.string("dbColumn"),
            Boolean.parseBoolean(mapping.string("dbUnique")),
            new MaybeEmptyTextOf(mapping, "pgIndexType").asString()
        );
    }

    public YamlIUIndexDBD(YamlMapping containerMapping, YamlNode keyNode) throws Exception {
        this(
            new StrictYamlMapping(containerMapping.yamlMapping(keyNode)),
            new TextOfScalar(() -> keyNode.asScalar().value())
        );
    }
}
