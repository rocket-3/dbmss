package org.fusionsoft.database.description.dbd.ofyaml;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlInput;
import com.amihaiemil.eoyaml.YamlMapping;
import java.io.IOException;
import org.fusionsoft.database.description.dbd.SimpleDBD;
import org.fusionsoft.database.yaml.DBDYamlInput;
import org.fusionsoft.lib.yaml.YamlInputOf;
import org.fusionsoft.lib.yaml.YamlMappingOfScalar;
import org.fusionsoft.lib.yaml.artefacts.IterableOfClassFromYamlNode;

public class DbdOf extends SimpleDBD {
    
    public DbdOf(YamlMapping mapping) {
        super(
            new IterableOfClassFromYamlNode<>(
                ServerOf::new, 
                mapping.yamlMapping("servers")
            ),
            new IterableOfClassFromYamlNode<>(
                SchemaOf::new, 
                mapping.yamlMapping("schemas")
            )
        );
    }
    
    public DbdOf(DBDYamlInput yamlInput) {
        this(
            new YamlMappingOfScalar(
                yamlInput::readYamlMapping
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
