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
import org.cactoos.Func;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableEnvelope;
import ru.fusionsoft.database.snapshot.DbObject;

/**
 * The objects being filtered by some predicate.
 * @param <Y> The type of YamlNode parameter.
 * @since 0.1
 */
public class ObjectsFiltered<Y extends YamlNode> extends IterableEnvelope<DbObject<Y>> {

    /**
     * Ctor.
     * @param predicate The predicate to be encapsulated.
     * @param iterable The original objects iterable.
     */
    public ObjectsFiltered(
        final Func<DbObject<Y>, Boolean> predicate,
        final Iterable<? extends DbObject<Y>> iterable
    ) {
        super(new Filtered<DbObject<Y>>(predicate, iterable));
    }

    /**
     * Ctor.
     * @param iterable The original objects iterable.
     * @param predicate The predicate to be encapsulated.
     */
    public ObjectsFiltered(
        final Iterable<DbObject<Y>> iterable,
        final Func<DbObject<Y>, Boolean> predicate
    ) {
        this(predicate, iterable);
    }

}
