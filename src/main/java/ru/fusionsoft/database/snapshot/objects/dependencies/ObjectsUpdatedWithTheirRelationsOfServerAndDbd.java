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
import org.cactoos.set.SetOf;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ObjectsOfScalar;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsMentionedInObjects;

/**
 * This takes dependencies of 'selected' from server, then all which depends on previous from DBD.
 * @param <T> The type of {@link YamlNode} parameter.
 * @since 0.1
 */
public class ObjectsUpdatedWithTheirRelationsOfServerAndDbd<T extends YamlNode>
    extends ObjectsOfScalar<T> {

    /**
     * Instantiates a new Objects updated with their relations of server and dbd.
     * @param selected The target objects set.
     * @param server The objects to take dependencies from.
     * @param dbd The objects, which dependency makes them attached too.
     * @param <Y> The type of YamlNode parameter.
     * @checkstyle DiamondOperatorCheck (100 lines)
     */
    public <Y extends YamlNode> ObjectsUpdatedWithTheirRelationsOfServerAndDbd(
        final Iterable<DbObject<T>> selected,
        final Iterable<DbObject<T>> server,
        final Iterable<DbObject<Y>> dbd
    ) {
        super(
            () -> {
                final Iterable<DbObject<T>> adding = new Joined<DbObject<T>>(
                    selected,
                    new ObjectsOfOneAreDependenciesOfAnotherRecursive<>(
                        server,
                        selected
                    )
                );
                final ObjectsMentionedInObjects<T> referrers = new ObjectsMentionedInObjects<>(
                    new ObjectsOfOneDependingOnAnotherRecursive<>(
                        dbd,
                        adding
                    ),
                    server
                );
                return new SetOf<>(
                    new Joined<>(
                        adding,
                        referrers,
                        new ObjectsOfOneAreDependenciesOfAnotherRecursive<>(
                            server,
                            referrers
                        )
                    )
                );
            }
        );
    }

}
