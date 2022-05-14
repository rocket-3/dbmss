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

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Func;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Mapped;
import ru.fusionsoft.database.snapshot.DbObject;

/**
 * The type of {@link Iterable} of {@link DbObject}s,
 *  with yaml representation casted to some {@link YamlMapping}.
 * @param <T> The type of {@link YamlNode} parameter.
 * @since 0.1
 */
public class ObjectsCasted<T extends YamlNode> extends IterableEnvelope<DbObject<T>> {

    /**
     * Ctor.
     * @param cast The cast {@link Func} of {@link YamlNode}, {@link T} to be encapsulated.
     * @param iterable The wrapped iterable
     */
    public ObjectsCasted(
        final Func<YamlNode, T> cast,
        final Iterable<? extends DbObject<?>> iterable
    ) {
        super(
            new Mapped<>(
                object -> new DbObjectCasted<>(cast, object),
                iterable
            )
        );
    }

}
