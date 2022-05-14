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
package ru.fusionsoft.database.snapshot.objects.dependencies;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Sticky;
import org.cactoos.scalar.Ternary;
import org.cactoos.set.SetOf;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ObjectsOfScalar;

/**
 * The Objects filtered by being dependencies on objects from another set,
 *  recursive chained version.
 * @param <T> The type parameter.
 * @since 0.1
 */
public class ObjectsOfOneAreDependenciesOfAnotherRecursive<T extends YamlNode>
    extends ObjectsOfScalar<T> {

    /**
     * Instantiates a new Objects of one are dependencies of another recursive.
     * @param ones The objects to take from.
     * @param anothers The objects that could depend on first ones.
     * @param <Y> The type of YamlNode parameter
     */
    public <Y extends YamlNode> ObjectsOfOneAreDependenciesOfAnotherRecursive(
        final Iterable<DbObject<T>> ones,
        final Iterable<DbObject<Y>> anothers
    ) {
        super(
            () -> {
                final Iterable<DbObject<T>> current = new Sticky<>(
                    new ObjectsOfOneAreDependenciesOfAnother<>(
                        ones,
                        anothers
                    )
                );
                return new Ternary<>(
                    () -> new SetOf<>(current).isEmpty(),
                    () -> current,
                    () -> new SetOf<>(
                        new Joined<DbObject<T>>(
                            current,
                            new Sticky<>(
                                new ObjectsOfOneAreDependenciesOfAnotherRecursive<T>(
                                    ones,
                                    current
                                )
                            )
                        )
                    )
                ).value();
            }
        );
    }

}
