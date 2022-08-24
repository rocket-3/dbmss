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
package ru.fusionsoft.database.migration.typecasts;

import java.util.Map;
import org.cactoos.BiFunc;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.Ternary;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.lib.collection.IterableOfScalarSticky;

/**
 * Merging given {@link Typecast}s with given type names diffs, if theres no entry in 'casts', then
 *  {@link Typecast} is created with implicit set to 'false'.
 * @since 0.1
 */
public class TypecastsOfTypeDiffsAndConfig extends IterableOfScalarSticky<Typecast> {

    /**
     * Instantiates a new Typecasts of type diffs and config.
     * @param diffs The Iterable {@link TemporalDiff} of type names to be encapsulated.
     * @param casts The Iterable of {@link Typecast} to be encapsulated.
     */
    public TypecastsOfTypeDiffsAndConfig(
        final Iterable<TemporalDiff<Text>> diffs,
        final Iterable<Typecast> casts
    ) {
        super(
            () -> {
                final BiFunc<Text, Text, Integer> key = (first, second) -> {
                    return first.asString().concat(second.asString()).hashCode();
                };
                final Map<Integer, Typecast> typecasts = new MapOf<>(
                    typecast -> key.apply(typecast.current(), typecast.next()),
                    typecast -> typecast,
                    casts
                );
                return new Mapped<>(
                    diff -> new SimpleTypecast(
                        diff.current(),
                        diff.next(),
                        new Ternary<>(
                            () -> typecasts.containsKey(key.apply(diff.current(), diff.next())),
                            () -> typecasts.get(key.apply(diff.current(), diff.next())).implicit(),
                            () -> false
                        ).value()
                    ),
                    diffs
                );
            }
        );
    }

}
