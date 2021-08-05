package org.fusionsoft.database.artefacts.yaml;

import com.amihaiemil.eoyaml.BaseYamlMapping;
import com.amihaiemil.eoyaml.Comment;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Set;
import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;

public class YamlMappingOfScalar extends BaseYamlMapping {
    private final Unchecked<YamlMapping> scalar;

    public YamlMappingOfScalar(Scalar<YamlMapping> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public Set<YamlNode> keys() {
        return scalar.value().keys();
    }

    @Override
    public YamlNode value(YamlNode key) {
        return scalar.value().value(key);
    }

    @Override
    public Comment comment() {
        return scalar.value().comment();
    }
}
