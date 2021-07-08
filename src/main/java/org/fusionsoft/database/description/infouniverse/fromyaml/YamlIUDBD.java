package org.fusionsoft.database.description.infouniverse.fromyaml;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import java.io.IOException;
import org.fusionsoft.database.description.infouniverse.SimpleIUDBD;
import org.fusionsoft.database.ofyaml.IterableOfClassByMappingKeysFrom;

public class YamlIUDBD extends SimpleIUDBD {
    
    public YamlIUDBD(YamlMapping mapping) {
        super(
            new IterableOfClassByMappingKeysFrom<>(
                YamlIUServerDBD::new, 
                mapping.yamlMapping("servers")
            ),
            new IterableOfClassByMappingKeysFrom<>(
                YamlIUSchemaDBD::new, 
                mapping.yamlMapping("schemas")
            )
        );
    }

    public YamlIUDBD(String string) throws IOException {
        this(Yaml.createYamlInput(string).readYamlMapping());
    }
    
}
