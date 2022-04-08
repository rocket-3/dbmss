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
import org.cactoos.Scalar;
import org.cactoos.iterable.IterableOf;
import ru.fusionsoft.database.snapshot.DbObject;

/**
 * The Objects of scalar.
 * @param <T> The subtype of {@link YamlNode} parameter.
 * @since 0.1
 */
public class ObjectsOfScalar<T extends YamlNode> extends ObjectsEnvelope<T> {

    /**
     * Ctor.
     * @param scalar The Scalar of Iterable of DbObject to be encapsulated.
     */
    public ObjectsOfScalar(final Scalar<Iterable<DbObject<T>>> scalar) {
        super(
            new IterableOf<>(
                () -> scalar.value().iterator()
            )
        );
    }

}
