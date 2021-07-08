package org.fusionsoft.database.ofyaml;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.BiFunc;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Mapped;

public class IterableOfClassByMappingKeysFrom<Y> extends IterableEnvelope<Y> {
    public IterableOfClassByMappingKeysFrom(
        BiFunc<YamlMapping, YamlNode, Y> mapAndItsKeyBiFunc, 
        YamlMapping yamlMapping
    ) {
        super(
            new Mapped<>(
                key -> mapAndItsKeyBiFunc.apply(yamlMapping, key),
                yamlMapping.keys()
            ) 
        );
    }
}
