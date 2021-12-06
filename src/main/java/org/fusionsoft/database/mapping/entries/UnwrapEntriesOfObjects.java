/*
 * Copyright (C) 2018-2021 FusionSoft
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
package org.fusionsoft.database.mapping.entries;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.BiFunc;
import org.cactoos.Text;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;

/**
 * The iterable of Text -> {@link M} entries of origin and scope {@link Objects}.
 * @param <M> The type of YamlMapping parameter.
 * @since 0.1
 */
public class UnwrapEntriesOfObjects<M extends YamlNode>
    extends IterableEnvelope<MapEntry<Text, M>> {

    /**
     * Ctor.
     * @param scope The scope objects to take in ctor
     * @param origin The objects to map
     * @param ctor The Func to construct {@link M} of origin {@link Objects}
     *  and {@link Objects} to take in scope.
     */
    public UnwrapEntriesOfObjects(
        final Objects<?> scope,
        final Objects<M> origin,
        final BiFunc<Objects<?>, DbObject<M>, ? extends M> ctor
    ) {
        super(
            new Mapped<>(
                object -> new MapEntry<>(
                    object.signature().name().first(),
                    ctor.apply(scope, object)
                ),
                origin
            )
        );
    }

}
