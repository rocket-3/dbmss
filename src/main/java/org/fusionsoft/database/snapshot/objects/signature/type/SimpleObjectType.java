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
package org.fusionsoft.database.snapshot.objects.signature.type;

import com.amihaiemil.eoyaml.YamlNode;
import java.util.Objects;
import org.cactoos.Func;
import org.cactoos.func.UncheckedFunc;
import org.fusionsoft.database.snapshot.objects.signature.ObjectType;

/**
 * The type of that can be constructed of.
 * @param <T> The subtype of {@link YamlNode} representation parameter.
 * @since 0.1
 */
public class SimpleObjectType<T extends YamlNode> implements ObjectType<T> {

    /**
     * The {@link UncheckedFunc} of {@link YamlNode}, {@link T} encapsulated.
     */
    private final UncheckedFunc<? super YamlNode, T> ctor;

    /**
     * The String encapsulated.
     */
    private final String text;

    /**
     * Instantiates a new Simple object type.
     * @param ctor The {@link Func} of {@link YamlNode} {@link T} to be encapsulated.
     * @param text The {@link String} representation to be encapsulated.
     */
    public SimpleObjectType(final Func<? super YamlNode, T> ctor, final String text) {
        this.ctor = new UncheckedFunc<>(ctor);
        this.text = text;
    }

    @Override
    public final T node(final YamlNode node) {
        return this.ctor.apply(node);
    }

    @Override
    public final String asString() {
        return this.text;
    }

    @Override
    public final boolean equalsTo(final ObjectType<?> other) {
        return this.asString().equals(other.asString());
    }

    @Override
    public final boolean equals(final Object other) {
        boolean equal = false;
        if (this == other) {
            equal = true;
        } else if (other instanceof ObjectType) {
            equal = this.equalsTo((SimpleObjectType<?>) other);
        }
        return equal;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.text);
    }

}
