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
import org.cactoos.Func;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapOf;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.ObjectSignature;
import ru.fusionsoft.lib.collection.IterableOfScalarSticky;

/**
 * The {@link ru.fusionsoft.database.migration.diff.ObjectsDiff}
 *  sorted by {@link ObjectsSortedByDependencies}.
 * @since 0.1
 */
public class ObjectsDiffSortedByDependencies
    extends IterableOfScalarSticky<TemporalDiff<DbObject<YamlNode>>> {

    /**
     * Instantiates a new Objects diff sorted by dependencies.
     * @param which The func to select current or next objects of {@link TemporalDiff}s.
     * @param original The iterable of {@link TemporalDiff}s
     *  of {@link DbObject}s to be encapsulated.
     */
    public ObjectsDiffSortedByDependencies(
        final Func<TemporalDiff<DbObject<YamlNode>>, DbObject<YamlNode>> which,
        final Iterable<TemporalDiff<DbObject<YamlNode>>> original
    ) {
        super(
            () -> {
                final MapOf<ObjectSignature, TemporalDiff<DbObject<YamlNode>>> map = new MapOf<>(
                    item -> item.current().signature(),
                    item -> item,
                    original
                );
                return new Mapped<>(
                    item -> map.get(item.signature()),
                    new ObjectsSortedByDependencies(
                        new Mapped<>(
                            which,
                            original
                        )
                    )
                );
            }
        );
    }

}
