package org.fusionsoft.database.description.infouniverse.fromyaml;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import java.io.IOException;
import org.cactoos.scalar.ScalarOf;
import org.fusionsoft.database.description.infouniverse.IUDBDEnvelope;
import org.fusionsoft.database.description.infouniverse.SimpleIUDBD;
import org.fusionsoft.database.ofyaml.IterableOfClassByMappingKeysFrom;

public class YamlIUDBD extends IUDBDEnvelope {
    
    public YamlIUDBD(YamlMapping mapping) {
        super(
            new ScalarOf<>(
                () -> new SimpleIUDBD(
                    new IterableOfClassByMappingKeysFrom<>(
                        YamlIUServerDBD::new, 
                        mapping.yamlMapping("servers")
                    ),
                    new IterableOfClassByMappingKeysFrom<>(
                        YamlIUSchemaDBD::new, 
                        mapping.yamlMapping("schemas")
                    )
                )
            )
        );
    }

    public YamlIUDBD(String string) throws IOException {
        this(Yaml.createYamlInput(string).readYamlMapping());
    }
    
}
