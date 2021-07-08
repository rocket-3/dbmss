package org.fusionsoft.database.description.infouniverse.fromyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Collections;
import org.cactoos.Text;
import org.cactoos.text.TextOfScalar;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.description.infouniverse.SimpleIUConstraintDBD;
import org.fusionsoft.database.ofyaml.StringSetOf;

public class YamlIUConstraintDBD extends SimpleIUConstraintDBD {
    private YamlIUConstraintDBD(StrictYamlMapping mapping, Text key, boolean isFK) {
        super(
            new UncheckedText(key).asString(),
            new StringSetOf(mapping, "dbColumn"),
            mapping.string("dbConstraintType"),
            isFK ? mapping.string("dbRefSchema") : "",
            isFK ? mapping.string("dbRefTable") : "",
            isFK ? new StringSetOf(mapping, "dbRefColumn") : Collections.emptySet(),
            isFK ? new StringSetOf(mapping, "dbFKColumn") : Collections.emptySet(),
            isFK ? mapping.string("dbRefUpdate") : "",
            isFK ? mapping.string("dbRefDelete") : ""
        );
    }
    private YamlIUConstraintDBD(StrictYamlMapping mapping, Text key) {
        this(mapping, key, mapping.string("dbConstraintType").equals("FK"));
    }


    public YamlIUConstraintDBD(YamlMapping containerMapping, YamlNode keyNode) {
        this(
            new StrictYamlMapping(containerMapping.yamlMapping(keyNode)),
            new TextOfScalar(() -> keyNode.asScalar().value())
        );
    }
}
