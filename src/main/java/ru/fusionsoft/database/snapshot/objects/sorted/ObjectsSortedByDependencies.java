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
import org.cactoos.iterable.Sorted;
import org.cactoos.list.ListOf;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.dependencies.ObjectsDependenciesMap;
import ru.fusionsoft.lib.collection.IterableOfScalarSticky;

/**
 * The independent object is the 'biggest', so close to tail.
 * @since 0.1
 */
public class ObjectsSortedByDependencies extends IterableOfScalarSticky<DbObject<YamlNode>> {

    /**
     * Instantiates a new Objects sorted by dependencies.
     * @param objects The iterable {@link DbObject}s to sort.
     */
    public ObjectsSortedByDependencies(final Iterable<DbObject<YamlNode>> objects) {
        super(
            () -> {
                final Iterable<DbObject<YamlNode>> sorted = new Sorted<>(
                    new ObjectsComparingByType().thenComparing(
                        new ObjectsComparingByDependencies(
                            new ObjectsDependenciesMap(
                                objects
                            )
                        )
                    ),
                    new ListOf<DbObject<YamlNode>>(objects)
                );
                return sorted;
            }
        );
    }

}
