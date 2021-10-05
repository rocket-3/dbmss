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
package org.fusionsoft.database;

import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.iterable.Filtered;

/**
 * Element of given enum extends Text from given Text instance.
 * @param <T> The class of enum type parameter.
 * @param <E> The enum value type parameter.
 * @since 0.1
 */
public class TextEnumValueOf<T extends Class<? extends E>, E extends Enum<E> & Text>
    implements Scalar<E> {

    /**
     * The T encapsulated.
     */
    private final T enumeration;

    /**
     * The Text encapsulated.
     */
    private final Text text;

    /**
     * Instantiates a new Text enum value of.
     * @param enumeration The T to be encapsulated.
     * @param text The Text to be encapsulated.
     */
    public TextEnumValueOf(final T enumeration, final Text text) {
        this.enumeration = enumeration;
        this.text = text;
    }

    @Override
    public final E value() {
        return new Filtered<E>(
            x -> x.asString().equals(this.text.asString()),
            this.enumeration.getEnumConstants()
        ).iterator().next();
    }

}
