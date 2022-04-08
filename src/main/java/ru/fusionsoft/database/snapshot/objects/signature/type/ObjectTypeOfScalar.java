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
package ru.fusionsoft.database.snapshot.objects.signature.type;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Scalar;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectType;

/**
 * The {@link ObjectType}, can be constructed of {@link Scalar}.
 * @param <T> The type parameter.
 * @since 0.1
 */
public class ObjectTypeOfScalar<T extends YamlNode> implements ObjectType<T> {

    /**
     * The Scalar encapsulated.
     */
    private final Unchecked<ObjectType<? extends T>> scalar;

    /**
     * Instantiates a new Object type of scalar.
     * @param scalar The Scalar to be encapsulated.
     */
    public ObjectTypeOfScalar(final Scalar<ObjectType<? extends T>> scalar) {
        this.scalar = new Unchecked<>(new Sticky<>(scalar));
    }

    @Override
    public final T node(final YamlNode mapping) {
        return this.scalar.value().node(mapping);
    }

    @Override
    public final String asString() {
        return this.scalar.value().asString();
    }

    @Override
    public final boolean equalsTo(final ObjectType<?> other) {
        return this.scalar.value().equalsTo(other);
    }

}
