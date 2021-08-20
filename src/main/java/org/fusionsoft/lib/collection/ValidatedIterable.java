package org.fusionsoft.lib.collection;

import org.cactoos.Proc;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.ScalarOf;

public class ValidatedIterable<X> extends IterableEnvelope<X> {
    /**
     * Ctor.
     *
     * @param col The wrapped collection
     */
    public ValidatedIterable(Proc<Iterable<X>> validation, Iterable<X> col) {
        super(
            new IterableOf<X>(
                new ScalarOf<>(
                    () -> {
                        validation.exec(col);
                        return col.iterator();
                    }
                )
            )
        );
    }
}
