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
package ru.fusionsoft.database.snapshot.objects.sorted;

import com.amihaiemil.eoyaml.YamlNode;
import java.util.Comparator;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.Grouped;
import org.cactoos.set.Sorted;
import ru.fusionsoft.database.migration.order.OrderOfObjectType;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectType;
import ru.fusionsoft.lib.collection.IterableOfScalarSticky;

/**
 * Objects grouped by {@link ObjectType} and sorted by {@link OrderOfObjectType} first,
 *  {@link ObjectsSortedByDependencies} next.
 * @since 0.1
 */
public class ObjectsSortedByTypeAndDependencies extends IterableOfScalarSticky<DbObject<YamlNode>> {

    /**
     * Instantiates a new Objects sorted by type and dependencies.
     * @param objects The iterable {@link DbObject}s to sort.
     */
    public ObjectsSortedByTypeAndDependencies(final Iterable<DbObject<YamlNode>> objects) {
        super(
            () -> {
                return new Joined<DbObject<YamlNode>>(
                    new Mapped<>(
                        list -> new ObjectsSortedByDependencies(list),
                        new Mapped<>(
                            group -> group.getValue(),
                            new Sorted<>(
                                Comparator.comparing(
                                    entry -> new OrderOfObjectType().apply(entry.getKey())
                                ),
                                new Grouped<>(
                                    objects,
                                    object -> object.signature().type(),
                                    object -> object
                                ).entrySet()
                            )
                        )
                    )
                );
            }
        );
    }

}
