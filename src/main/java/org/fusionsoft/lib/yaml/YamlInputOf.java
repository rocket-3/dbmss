package org.fusionsoft.lib.yaml;

import com.amihaiemil.eoyaml.Yaml;
import java.io.InputStream;
import java.nio.file.Path;
import org.cactoos.Text;
import org.cactoos.io.InputStreamOf;

public class YamlInputOf extends YamlInputEnvelope {
    public YamlInputOf(InputStream inputStream) {
        super(Yaml.createYamlInput(inputStream));
    }
    
    public YamlInputOf(Path pathToFile) {
        this(
            new InputStreamOf(pathToFile)
        );
    }    
    
    public YamlInputOf(CharSequence chars) {
        this(
            new InputStreamOf(chars)
        );
    }
    
    public YamlInputOf(Text text) {
        this(
            new InputStreamOf(text)
        );
    }
}
