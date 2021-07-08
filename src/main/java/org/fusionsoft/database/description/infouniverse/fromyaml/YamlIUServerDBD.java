package org.fusionsoft.database.description.infouniverse.fromyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.description.infouniverse.SimpleIUServerDBD;

public class YamlIUServerDBD extends SimpleIUServerDBD {
    private YamlIUServerDBD(StrictYamlMapping mapping, Text key) throws Exception {
        super(
            key.asString(),
            mapping.string("dbType"),
            mapping.string("url"),
            mapping.string("user"),
            mapping.string("password"),
            mapping.string("description"),
            mapping.string("variables")
        );
    }
    
    public YamlIUServerDBD(YamlMapping containerMapping, YamlNode keyNode) throws Exception {
        this(
            new StrictYamlMapping(containerMapping.yamlMapping(keyNode)),
            new TextOfScalar( () -> keyNode.asScalar().value() )
        );
    }
}
