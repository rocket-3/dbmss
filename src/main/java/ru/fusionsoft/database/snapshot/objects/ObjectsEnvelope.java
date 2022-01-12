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
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Objects;

/**
 * The {@link Objects} implementation
 *  that can be constructed of {@link Iterable} of {@link DbObject}s.
 * @param <T> The subtype of {@link YamlNode} parameter.
 * @since 0.1
 */
public class ObjectsEnvelope<T extends YamlNode>
    extends IterableEnvelope<DbObject<T>> implements Objects<T> {

    /**
     * Ctor.
     * @param iterable The wrapped iterable
     */
    public ObjectsEnvelope(final Iterable<DbObject<T>> iterable) {
        super(iterable);
    }

}
