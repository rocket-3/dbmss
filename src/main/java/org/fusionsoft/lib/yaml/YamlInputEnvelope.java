package org.fusionsoft.lib.yaml;

import com.amihaiemil.eoyaml.Scalar;
import com.amihaiemil.eoyaml.YamlInput;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlSequence;
import com.amihaiemil.eoyaml.YamlStream;
import java.io.IOException;

public class YamlInputEnvelope implements YamlInput {
    private final YamlInput yamlInput;

    public YamlInputEnvelope(YamlInput yamlInput) {
        this.yamlInput = yamlInput;
    }

    @Override
    public YamlMapping readYamlMapping() throws IOException {
        return this.yamlInput.readYamlMapping();
    }

    @Override
    public YamlSequence readYamlSequence() throws IOException {
        return this.yamlInput.readYamlSequence();
    }

    @Override
    public YamlStream readYamlStream() throws IOException {
        return this.yamlInput.readYamlStream();
    }

    @Override
    public Scalar readPlainScalar() throws IOException {
        return this.yamlInput.readPlainScalar();
    }

    @Override
    public Scalar readFoldedBlockScalar() throws IOException {
        return this.yamlInput.readFoldedBlockScalar();
    }

    @Override
    public Scalar readLiteralBlockScalar() throws IOException {
        return this.yamlInput.readLiteralBlockScalar();
    }
    
    
}
