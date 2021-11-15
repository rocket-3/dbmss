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
 * The iterable of Text -> Mapping pairs unwrapped of
 *  all scope and top-level (mapped){@link Objects} by function, creating mapping
 *  of specific kind.
 * @param <M> The type of YamlMapping parameter.
 * @since 0.1
 */
public class UnwrapEntriesOfObjects<M extends YamlNode>
    extends IterableEnvelope<MapEntry<Text, M>> {

    /**
     * Ctor.
     * @param scope The scope objects to take in unwrapping
     * @param mapped The objects to map
     * @param unwrapping The Func to unwrap objects  in current object context as mapping
     */
    public UnwrapEntriesOfObjects(
        final Objects scope,
        final Objects mapped,
        final BiFunc<Objects, DbObject<?>, ? extends M> unwrapping
    ) {
        super(
            new Mapped<>(
                object -> new MapEntry<>(
                    object.signature().name().first(),
                    unwrapping.apply(scope, object)
                ),
                mapped
            )
        );
    }

}
