package org.fusionsoft.database.description.infouniverse.fromyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.description.infouniverse.SimpleIUTableDBD;

public class YamlIUTableDBD extends SimpleIUTableDBD {
    private YamlIUTableDBD(StrictYamlMapping mapping, Text key) throws Exception {
        super(
            key.asString(),
            new Mapped<>(
                k -> new YamlIUColumnDBD(k.asMapping()),
                mapping.yamlSequence("columns").values()
            ),
            new Mapped<>(
                k -> new YamlIUConstraintDBD(mapping.yamlMapping("constraints"), k),
                mapping.yamlMapping("constraints").keys()
            ),
            new Mapped<>(
                k -> new YamlIUIndexDBD(mapping.yamlMapping("indexes"), k),
                mapping.yamlMapping("indexes").keys()
            )
        );
    }

    public YamlIUTableDBD(YamlMapping containerMapping, YamlNode keyNode) throws Exception {
        this(
            new StrictYamlMapping(containerMapping.yamlMapping(keyNode)),
            new TextOfScalar(() -> keyNode.asScalar().value())
        );
    }
}
