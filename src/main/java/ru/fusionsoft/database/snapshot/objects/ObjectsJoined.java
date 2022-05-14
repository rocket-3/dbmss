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
package ru.fusionsoft.database.snapshot.objects;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Mapped;
import ru.fusionsoft.database.snapshot.DbObject;

/**
 * The objects iterable being constructed of other iterables, downcasted type to {@link YamlNode}.
 * @since 0.1
 */
public class ObjectsJoined extends IterableEnvelope<DbObject<YamlNode>> {

    /**
     * Instantiates a new Objects joined.
     * @param iterables The iterable of objects iterables to be encapsulated.
     */
    public ObjectsJoined(
        final Iterable<Iterable<? extends DbObject<? extends YamlNode>>> iterables
    ) {
        super(
            new Joined<>(
                new Mapped<>(
                    iterable -> new Mapped<DbObject<YamlNode>>(
                        object -> new DbObjectCasted<>(node -> node, object),
                        iterable
                    ),
                    iterables
                )
            )
        );
    }

    /**
     * Instantiates a new Objects joined.
     * @param iterables The array of objects iterables to be encapsulated.
     */
    @SafeVarargs
    public ObjectsJoined(final Iterable<? extends DbObject<? extends YamlNode>>... iterables) {
        this(new IterableOf<>(iterables));
    }

}
