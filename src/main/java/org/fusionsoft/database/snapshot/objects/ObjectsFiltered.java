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
import org.cactoos.Func;
import org.cactoos.iterable.Filtered;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;

/**
 * The type of DbObjects that was filtered by some predicate.
 * @param <T> The subtype of {@link YamlNode} parameter.
 * @since 0.1
 */
public class ObjectsFiltered<T extends YamlNode> extends ObjectsEnvelope<T> {

    /**
     * Ctor.
     * @param origin The wrapped DbObjects
     * @param predicate The predicate to be used.
     */
    public ObjectsFiltered(
        final Objects<T> origin,
        final Func<DbObject<?>, Boolean> predicate
    ) {
        super(
            new Filtered<>(
                predicate,
                origin
            )
        );
    }

    /**
     * Ctor.
     * @param predicate The predicate to be used.
     * @param origin The wrapped DbObjects
     */
    public ObjectsFiltered(
        final Func<DbObject<?>, Boolean> predicate,
        final Objects<T> origin
    ) {
        this(origin, predicate);
    }

}
