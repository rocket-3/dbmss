package org.fusionsoft.database.description.dbd.ofyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import org.fusionsoft.database.description.dbd.SimpleDBD;
import org.fusionsoft.database.yaml.DBDYamlInput;
import org.fusionsoft.lib.yaml.YamlMappingOf;
import org.fusionsoft.lib.yaml.artefacts.IterableOfClassFromYamlNode;

public class DbdOf extends SimpleDBD {
    
    public DbdOf(YamlMapping mapping) {
        super(
            new IterableOfClassFromYamlNode<>(
                ServerOf::new, 
                new StrictYamlMapping(
                    mapping.yamlMapping("servers")
                )
            ),
            new IterableOfClassFromYamlNode<>(
                SchemaOf::new, 
                new StrictYamlMapping(
                    mapping.yamlMapping("schemas")
                )
            )
        );
    }
    
    public DbdOf(DBDYamlInput yamlInput) {
        this(
            new StrictYamlMapping(
                new YamlMappingOf(
                    yamlInput
                )
            )
        );
    }

//    public DbdOf(String string) {
//        this(
//            new DBDYamlInput(
//                new YamlInputOf(string)
//            )
//        );
//    }
    
}
