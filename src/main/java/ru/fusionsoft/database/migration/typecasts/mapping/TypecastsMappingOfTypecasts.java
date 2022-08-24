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
package ru.fusionsoft.database.migration.typecasts.mapping;

import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.Grouped;
import org.cactoos.map.MapEntry;
import ru.fusionsoft.database.mapping.config.TypecastDetailsFields;
import ru.fusionsoft.database.mapping.config.TypecastDetailsMapping;
import ru.fusionsoft.database.mapping.config.TypecastsMapping;
import ru.fusionsoft.database.mapping.config.TypecastsOfDbmsMapping;
import ru.fusionsoft.database.mapping.config.TypecastsOfTypeMapping;
import ru.fusionsoft.database.migration.typecasts.Typecast;
import ru.fusionsoft.database.snapshot.Dbms;

/**
 * The main way to create {@link TypecastsMapping} of Iterable of {@link Typecast}s.
 * @since 0.1
 */
public class TypecastsMappingOfTypecasts extends TypecastsMapping {

    /**
     * Instantiates a new Typecasts mapping of typecasts.
     * @param dbms The {@link Dbms} to be encapsulated.
     * @param typecasts The Iterable of {@link Typecast}s to be encapsulated.
     */
    public TypecastsMappingOfTypecasts(final Dbms dbms, final Iterable<Typecast> typecasts) {
        super(
            new IterableOf<>(
                new MapEntry<>(
                    dbms,
                    new TypecastsOfDbmsMapping(
                        new Mapped<>(
                            group -> new MapEntry<>(
                                group.getKey(),
                                new TypecastsOfTypeMapping(
                                    new Mapped<>(
                                        value -> new MapEntry<>(
                                            value.next(),
                                            new TypecastDetailsMapping(
                                                new MapEntry<>(
                                                    TypecastDetailsFields.IMPLICIT,
                                                    value::implicit
                                                )
                                            )
                                        ),
                                        group.getValue()
                                    )
                                )
                            ),
                            new Grouped<>(
                                typecasts,
                                typecast -> typecast.current(),
                                typecast -> typecast
                            ).entrySet()
                        )
                    )
                )
            )
        );
    }

}
