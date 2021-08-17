package org.fusionsoft.database.description.dbd.ofyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.text.TextOfScalar;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.description.dbd.SimpleConstraint;
import org.fusionsoft.lib.yaml.artefacts.MaybeEmptyTextOf;
import org.fusionsoft.lib.yaml.artefacts.StringSetOf;

public class ConstraintOf extends SimpleConstraint {
    private ConstraintOf(StrictYamlMapping mapping, Text key) throws Exception {
        super(
            new UncheckedText(key).asString(),
            new StringSetOf(mapping, "dbColumn"),
            mapping.string("dbConstraintType"),
            new MaybeEmptyTextOf(mapping, "dbRefSchema").asString(),
            new MaybeEmptyTextOf(mapping, "dbRefTable").asString(),
            new StringSetOf(mapping, "dbRefColumn"),
            new StringSetOf(mapping, "dbFKColumn"),
            new MaybeEmptyTextOf(mapping, "dbRefUpdate").asString(),
            new MaybeEmptyTextOf(mapping, "dbRefDelete").asString()
        );
    }

    public ConstraintOf(YamlMapping containerMapping, YamlNode keyNode) throws Exception {
        this(
            new StrictYamlMapping(containerMapping.yamlMapping(keyNode)),
            new TextOfScalar(() -> keyNode.asScalar().value())
        );
    }
}
