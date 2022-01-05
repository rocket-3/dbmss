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

package org.fusionsoft.database.snapshot.objects;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Mapped;
import org.fusionsoft.database.snapshot.DbObject;

/**
 * The type of {@link ObjectsEnvelope} that joins iterables of {@link DbObject}s.
 * @since 0.1
 */
public class DefaultObjectsJoined extends ObjectsEnvelope<YamlNode> {

    /**
     * Ctor.
     * @param iterable The wrapped iterable of iterables of {@link DbObject}
     */
    public DefaultObjectsJoined(
        final Iterable<Iterable<? extends DbObject<? extends YamlNode>>> iterable
    ) {
        super(
            new Joined<>(
                new Mapped<>(
                    DefaultObjects::new,
                    iterable
                )
            )
        );
    }

    /**
     * Instantiates a new Default objects joined.
     * @param iterables The iterables array to be consumed.
     */
    @SafeVarargs
    public DefaultObjectsJoined(
        final Iterable<? extends DbObject<? extends YamlNode>>... iterables
    ) {
        this(new IterableOf<>(iterables));
    }

    /**
     * Instantiates a new Default objects joined.
     * @param object The {@link DbObject} to be prepended.
     * @param iterables The iterable of {@link DbObject}s to be encapsulated.
     */
    @SafeVarargs
    public DefaultObjectsJoined(
        final DbObject<?> object,
        final Iterable<? extends DbObject<? extends YamlNode>>... iterables
    ) {
        this(
            new Joined<>(
                new IterableOf<>(object),
                new IterableOf<>(iterables)
            )
        );
    }

}
