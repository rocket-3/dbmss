package org.fusionsoft.database.stringproperty;

import org.cactoos.map.MapEnvelope;
import org.cactoos.map.MapOf;
import org.fusionsoft.database.StringProperty;
import org.fusionsoft.database.StringPropertySignature;

public class SignatureMapOfProps extends MapEnvelope<StringPropertySignature, StringProperty> {
    /**
     * Ctor.
     *
     * @param iterable The iterable of StringProperty.
     */
    public SignatureMapOfProps(Iterable<StringProperty> iterable) {
        super(
            new MapOf<>(
                StringProperty::signature,
                sp -> sp,
                iterable
            )
        );
    }
}
