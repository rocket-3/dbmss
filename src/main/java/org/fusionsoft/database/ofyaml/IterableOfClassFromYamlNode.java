package org.fusionsoft.database.ofyaml;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import com.amihaiemil.eoyaml.YamlSequence;
import org.cactoos.BiFunc;
import org.cactoos.Func;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterable.Skipped;

public class IterableOfClassFromYamlNode<Y> extends IterableEnvelope<Y> {
    public IterableOfClassFromYamlNode(
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
    public IterableOfClassFromYamlNode(
        Func<YamlMapping, Y> mappingYFunc, 
        YamlSequence yamlSequence
    ) {
        super(
            new Mapped<>(
                mappingYFunc,
                new Mapped<YamlMapping>(
                    YamlNode::asMapping,
                    yamlSequence.values()
                )
            ) 
        );
    }
}
