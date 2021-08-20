package org.fusionsoft.database.stringproperty;

import org.cactoos.map.MapEnvelope;
import org.cactoos.map.MapOf;
import org.fusionsoft.database.StringProperty;

public class MapOfProps extends MapEnvelope<String, StringProperty> {
    /**
     * Ctor.
     *
     * @param iterable The iterable of StringProperty.
     */
    public MapOfProps(Iterable<StringProperty> iterable) {
        super(
            new MapOf<>(
                sp -> sp.signature().key(),
                sp -> sp,
                iterable
            )
        );
    }
}
