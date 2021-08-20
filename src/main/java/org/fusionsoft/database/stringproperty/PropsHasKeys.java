package org.fusionsoft.database.stringproperty;

import java.util.Collection;
import java.util.Set;
import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.ScalarOf;
import org.cactoos.set.SetOf;
import org.fusionsoft.database.StringProperty;
import org.fusionsoft.database.StringPropertySignature;
import org.fusionsoft.lib.collection.MapHasKeys;

public class PropsHasKeys extends CollectionEnvelope<StringProperty> {
    public PropsHasKeys(Set<StringPropertySignature> keys, Collection<StringProperty> props) {
        super(
            new SetOf<>(
                new IterableOf<>(
                    new ScalarOf<>(
                        x -> x.values().iterator(),
                        new MapHasKeys<>(
                            keys, 
                            new SignatureMapOfProps(props)
                        )
                    )
                )
            )
        );
    }
}
