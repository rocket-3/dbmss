package org.fusionsoft.database.description.infouniverse.fromyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.scalar.ScalarOf;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.description.infouniverse.IUServerDBD;
import org.fusionsoft.database.description.infouniverse.IUServerDBDEnvelope;
import org.fusionsoft.database.description.infouniverse.SimpleIUServerDBD;

public class YamlIUServerDBD extends IUServerDBDEnvelope {
    private YamlIUServerDBD(StrictYamlMapping mapping, Text key) {
        super(
            new ScalarOf<IUServerDBD>(
                () -> new SimpleIUServerDBD(
                    key.asString(),
                    mapping.string("dbType"),
                    mapping.string("url"),
                    mapping.string("user"),
                    mapping.string("password"),
                    mapping.string("description"),
                    mapping.string("variables")
                )
            )
        );
    }
    
    public YamlIUServerDBD(YamlMapping containerMapping, YamlNode keyNode) {
        this(
            new StrictYamlMapping(containerMapping.yamlMapping(keyNode)),
            new TextOfScalar( () -> keyNode.asScalar().value() )
        );
    }
}
