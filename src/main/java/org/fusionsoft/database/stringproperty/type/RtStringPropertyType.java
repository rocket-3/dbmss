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
package org.fusionsoft.database.stringproperty.type;

import org.cactoos.Text;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.StringPropertyType;

/**
 * The naive implementation of StringPropertyType.
 * @since 0.1
 */
public abstract class RtStringPropertyType implements StringPropertyType {

    /**
     * The UncheckedText encapsulated.
     */
    private final UncheckedText text;

    /**
     * Instantiates a new Rt string property type.
     * @param text The Text to be encapsulated.
     */
    private RtStringPropertyType(final Text text) {
        this.text = new UncheckedText(text);
    }

    /**
     * Instantiates a new Rt string property type.
     * @param text The CharSequence to be encapsulated.
     */
    public RtStringPropertyType(final CharSequence text) {
        this(new TextOf(text));
    }

    @Override
    public final String asString() {
        return this.text.asString();
    }

}
