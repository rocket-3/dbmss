package org.fusionsoft.database.description.dbd.ofyaml;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import java.io.IOException;
import org.fusionsoft.database.description.dbd.SimpleIUDBD;
import org.fusionsoft.database.ofyaml.IterableOfClassFromYamlNode;

public class YamlIUDBD extends SimpleIUDBD {
    
    public YamlIUDBD(YamlMapping mapping) {
        super(
            new IterableOfClassFromYamlNode<>(
                YamlIUServerDBD::new, 
                mapping.yamlMapping("servers")
            ),
            new IterableOfClassFromYamlNode<>(
                YamlIUSchemaDBD::new, 
                mapping.yamlMapping("schemas")
            )
        );
    }

    public YamlIUDBD(String string) throws IOException {
        this(Yaml.createYamlInput(string).readYamlMapping());
    }
    
}
