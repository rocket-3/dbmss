package org.fusionsoft.database.description.dbd.ofyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.description.dbd.SimpleIndex;
import org.fusionsoft.lib.yaml.artefacts.MaybeEmptyTextOf;

public class IndexOf extends SimpleIndex {
    private IndexOf(StrictYamlMapping mapping, Text key) throws Exception {
        super(
            key.asString(),
            mapping.string("dbColumn"),
            Boolean.parseBoolean(mapping.string("dbUnique")),
            new MaybeEmptyTextOf(mapping, "pgIndexType").asString()
        );
    }

    public IndexOf(YamlMapping containerMapping, YamlNode keyNode) throws Exception {
        this(
            new StrictYamlMapping(containerMapping.yamlMapping(keyNode)),
            new TextOfScalar(() -> keyNode.asScalar().value())
        );
    }
}
