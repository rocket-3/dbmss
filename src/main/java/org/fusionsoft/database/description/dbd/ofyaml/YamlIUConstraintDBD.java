package org.fusionsoft.database.description.dbd.ofyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.text.TextOfScalar;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.description.dbd.SimpleIUConstraintDBD;
import org.fusionsoft.database.ofyaml.MaybeEmptyTextOf;
import org.fusionsoft.database.ofyaml.StringSetOf;

public class YamlIUConstraintDBD extends SimpleIUConstraintDBD {
    private YamlIUConstraintDBD(StrictYamlMapping mapping, Text key) throws Exception {
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

    public YamlIUConstraintDBD(YamlMapping containerMapping, YamlNode keyNode) throws Exception {
        this(
            new StrictYamlMapping(containerMapping.yamlMapping(keyNode)),
            new TextOfScalar(() -> keyNode.asScalar().value())
        );
    }
}
