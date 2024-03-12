/*
 * Copyright (C) 2018-2022 FusionSoft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.
 *
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ru.fusionsoft.database.migration.diff;

import java.util.Map;
import org.cactoos.Func;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapOf;

/**
 * The {@link TemporalDiff} of iterables, with intersection and changedBy extensions.
 * @param <T> The subtype of iterable parameter.
 * @since 0.1
 */
public class SmartIterableTemporalDiff<T> implements TemporalDiff<Iterable<T>> {

    /**
     * The TemporalDiff of iterables encapsulated.
     */
    private final TemporalDiff<? extends Iterable<T>> diff;

    /**
     * The Func to get key encapsulated.
     */
    private final Func<T, Integer> key;

    /**
     * Instantiates a new Smart iterable temporal diff.
     * @param key The key extraction func to be encapsulated.
     * @param current The current iterable to be encapsulated.
     * @param next The next iterable to be encapsulated.
     * @param <K> The type of key parameter.
     */
    public <K> SmartIterableTemporalDiff(
        final Func<T, K> key,
        final Iterable<T> current,
        final Iterable<T> next
    ) {
        this(key, new SimpleTemporalDiff<>(current, next));
    }

    /**
     * Instantiates a new Smart iterable temporal diff.
     * @param key The key extraction func to be encapsulated.
     * @param diff The {@link TemporalDiff} of iterables to be encapsulated.
     * @param <K> The type of key parameter.
     */
    public <K> SmartIterableTemporalDiff(
        final Func<T, K> key,
        final TemporalDiff<? extends Iterable<T>> diff
    ) {
        this.key = obj -> key.apply(obj).hashCode();
        this.diff = diff;
    }

    @Override
    public final Iterable<T> current() {
        return this.diff.current();
    }

    @Override
    public final Iterable<T> next() {
        return this.diff.next();
    }

    /**
     * Added iterable.
     * @return The iterable.
     */
    public final Iterable<T> added() {
        final Map<Integer, T> currents = new MapOf<>(
            this.key,
            entry -> entry,
            this.diff.current()
        );
        return new Filtered<>(
            added -> !currents.containsKey(this.key.apply(added)),
            this.diff.next()
        );
    }

    /**
     * Deleted iterable.
     * @return The iterable.
     */
    public final Iterable<T> deleted() {
        final Map<Integer, T> nexts = new MapOf<>(
            this.key,
            entry -> entry,
            this.diff.next()
        );
        return new Filtered<>(
            deleted -> !nexts.containsKey(this.key.apply(deleted)),
            this.diff.current()
        );
    }

    /**
     * Intersection iterable.
     * @return The iterable.
     */
    public final Iterable<TemporalDiff<T>> intersection() {
        final Map<Integer, T> nexts = new MapOf<>(
            this.key,
            entry -> entry,
            this.diff.next()
        );
        return new Mapped<TemporalDiff<T>>(
            cur -> new SimpleTemporalDiff<>(
                cur,
                nexts.get(this.key.apply(cur))
            ),
            new Filtered<>(
                current -> nexts.containsKey(this.key.apply(current)),
                this.diff.current()
            )
        );
    }

    /**
     * Changed by iterable.
     * @param hashcode The hashcode extraction func.
     * @return The iterable.
     */
    public final Iterable<TemporalDiff<T>> changedBy(final Func<T, Integer> hashcode) {
        return new Filtered<>(
            difference -> hashcode.apply(difference.next()).intValue()
            != hashcode.apply(difference.current()).intValue(),
            this.intersection()
        );
    }

}
