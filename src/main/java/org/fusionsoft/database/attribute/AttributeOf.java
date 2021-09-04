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
package org.fusionsoft.database.attribute;

import org.cactoos.Text;
import org.cactoos.text.TextOfScalar;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.Attribute;
import org.fusionsoft.database.AttributeSignature;
import org.fusionsoft.database.AttributeType;
import org.fusionsoft.database.attribute.signature.SimpleAttributeSignature;
import org.fusionsoft.database.attribute.type.TypeBoolean;
import org.fusionsoft.database.attribute.type.TypeFloat;
import org.fusionsoft.database.attribute.type.TypeInteger;

/**
 * The way to instantiate {@link Attribute} from different sources.
 * @since 0.1
 */
public class AttributeOf implements Attribute {

    /**
     * The AttributeSignature encapsulated.
     */
    private final AttributeSignature sig;

    /**
     * The UncheckedText encapsulated.
     */
    private final UncheckedText text;

    /**
     * Instantiates a new Attribute of {@link Text}.
     * @param sig The AttributeSignature to be encapsulated.
     * @param text The Text to be encapsulated.
     */
    private AttributeOf(
        final AttributeSignature sig,
        final Text text
    ) {
        this.sig = sig;
        this.text = new UncheckedText(text);
    }

    /**
     * Instantiates a new Attribute of.
     * @param key The String to be used for signature' key.
     * @param type The AttributeType to be for signature' type.
     * @param value The Text to be encapsulated.
     */
    public AttributeOf(
        final String key,
        final AttributeType type,
        final Text value
    ) {
        this(new SimpleAttributeSignature(key, type), value);
    }

    /**
     * Instantiates a new Attribute of.
     * @param key The String to be used for signature's key.
     * @param integer The Integer to be used for value.
     */
    public AttributeOf(final String key, final Integer integer) {
        this(
            key,
            new TypeInteger(),
            new TextOfScalar(() -> String.valueOf(integer))
        );
    }

    /**
     * Instantiates a new Attribute of.
     * @param key The String to be encapsulated.
     * @param integer The Long to be used for value.
     */
    public AttributeOf(final String key, final Long integer) {
        this(
            key,
            new TypeInteger(),
            new TextOfScalar(() -> String.valueOf(integer))
        );
    }

    /**
     * Instantiates a new Attribute of.
     * @param key The String to be encapsulated.
     * @param floating The Float to be used for value.
     */
    public AttributeOf(final String key, final Float floating) {
        this(
            key,
            new TypeFloat(),
            new TextOfScalar(() -> String.valueOf(floating))
        );
    }

    /**
     * Instantiates a new Attribute of.
     * @param key The String to be encapsulated.
     * @param floating The Double to be used for value.
     */
    public AttributeOf(final String key, final Double floating) {
        this(
            key,
            new TypeFloat(),
            new TextOfScalar(() -> String.valueOf(floating))
        );
    }

    /**
     * Instantiates a new Attribute of.
     * @param key The String to be encapsulated.
     * @param bool The Boolean to be used for value.
     */
    public AttributeOf(final String key, final Boolean bool) {
        this(
            key,
            new TypeBoolean(),
            new TextOfScalar(() -> String.valueOf(bool))
        );
    }

    /**
     * Instantiates a new Attribute of.
     * @param key The String to be encapsulated.
     * @param chars The CharSequence to be used for value.
     */
    public AttributeOf(final String key, final CharSequence chars) {
        this(
            key,
            new TypeBoolean(),
            new TextOfScalar(() -> String.valueOf(chars))
        );
    }

    @Override
    public final AttributeSignature signature() {
        return this.sig;
    }

    @Override
    public final String value() {
        return this.text.asString();
    }

}
