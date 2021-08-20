package org.fusionsoft.lib.collection;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Set;
import org.cactoos.map.MapEnvelope;

public class MapHasKeys<X, Y> extends MapEnvelope<X, Y> {
    /**
     * @param map  The original map to be tested before each operation on it.
     * @param keys The keys the map should contain.
     * @throws RuntimeException is thrown at the moment of usage, if any of `keys` is not found in `map`
     * @implNote also wrapped by StrictMap
     * @see StrictMap
     */
    public MapHasKeys(Set<X> keys, Map<X, Y> map) {
        super(
            new StrictMap<>(
                new ValidatedMap<>(
                    m -> keys.forEach(k -> {
                        if (! map.containsKey(k)) {
                            throw new RuntimeException(MessageFormat.format(
                                "No value found for key {0}",
                                k.toString()
                            ));
                        }
                    }),
                    map
                )
            )
        );
    }
}
