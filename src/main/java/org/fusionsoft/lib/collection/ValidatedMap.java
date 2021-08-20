package org.fusionsoft.lib.collection;

import java.util.Iterator;
import java.util.Map;
import org.cactoos.Proc;
import org.cactoos.iterable.IterableOf;
import org.cactoos.map.MapEnvelope;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.ScalarOf;

public class ValidatedMap<X, Y> extends MapEnvelope<X, Y> {
    /**
     * @param validation The procedure to apply to map before returning `map`
     * @param map        The wrapped map
     */
    public ValidatedMap(Proc<Map<X, Y>> validation, Map<X, Y> map) {
        super(
            new MapOf<>(
                new IterableOf<>(
                    new ScalarOf<Iterator<? extends Entry<? extends X, ? extends Y>>>(
                        () -> {
                            validation.exec(map);
                            return map.entrySet().iterator();
                        }
                    )
                )
            )
        );
    }
}
