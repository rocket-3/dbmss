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
package ru.fusionsoft.database.migration.order;

import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.MappedWithIndex;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapEnvelope;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.And;
import org.cactoos.scalar.Ternary;
import org.cactoos.set.SetOf;
import ru.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import ru.fusionsoft.database.snapshot.objects.sorted.DbdColumnMappingsSortedByOrder;
import ru.fusionsoft.database.text.DbdColumnIdentity;

/**
 * The Map of {@link DbdColumnIdentity} to Integer, merging columns like DBMS does.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines).
 */
public class OrdersOfColumnsMergedLikeDbms extends MapEnvelope<DbdColumnIdentity, Integer> {

    /**
     * Instantiates a new Orders of columns safe merged.
     * @param original The original cols data.
     * @param deleted The data of deleted cols.
     * @param added The data of added cols.
     */
    public OrdersOfColumnsMergedLikeDbms(
        final Iterable<DbdColumnMapping> original,
        final Iterable<DbdColumnMapping> deleted,
        final Iterable<DbdColumnMapping> added
    ) {
        super(
            new MapOf<>(
                new MappedWithIndex<>(
                    (DbdColumnMapping mapping, Integer idx) -> new MapEntry<>(
                        new DbdColumnIdentity(mapping),
                        idx + 1
                    ),
                    new Joined<DbdColumnMapping>(
                        new Filtered<>(
                            origin -> new Ternary<>(
                                () -> new SetOf<>(deleted).isEmpty(),
                                () -> true,
                                new And(
                                    (DbdColumnMapping exclude) -> {
                                        return !new DbdColumnIdentity(origin).equals(
                                            new DbdColumnIdentity(exclude)
                                        );
                                    },
                                    deleted
                                )
                            ).value(),
                            new DbdColumnMappingsSortedByOrder(original)
                        ),
                        new DbdColumnMappingsSortedByOrder(added)
                    )
                )
            )
        );
    }

}
