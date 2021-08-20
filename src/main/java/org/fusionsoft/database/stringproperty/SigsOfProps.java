package org.fusionsoft.database.stringproperty;

import java.util.Collection;
import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.iterable.Mapped;
import org.cactoos.set.SetOf;
import org.fusionsoft.database.StringProperty;
import org.fusionsoft.database.StringPropertySignature;

public class SigsOfProps extends CollectionEnvelope<StringPropertySignature> {
    /**
     * Ctor.
     *
     * @param col The wrapped collection
     */
    public SigsOfProps(Collection<StringProperty> col) {
        super(
            new SetOf<>(
                new Mapped<>(
                    StringProperty::signature,
                    col
                )
            )
        );
    }
}
