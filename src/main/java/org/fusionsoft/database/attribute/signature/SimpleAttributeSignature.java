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
package org.fusionsoft.database.attribute.signature;

import org.cactoos.Text;
import org.cactoos.text.TextOfScalar;
import org.cactoos.text.TextOfString;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.AttributeSignature;
import org.fusionsoft.database.AttributeType;

/**
 * The signature of StringProperty entity,
 *  that can be constructed of key and type.
 * @since 0.1
 */
@SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
public class SimpleAttributeSignature implements AttributeSignature {

    /**
     * The UncheckedText encapsulated.
     */
    private final UncheckedText key;

    /**
     * The StringPropertyType encapsulated.
     */
    private final UncheckedText type;

    /**
     * Instantiates a new SimpleAttributeSignature.
     * @param key The Text to be encapsulated.
     * @param type The Text to be encapsulated.
     */
    private SimpleAttributeSignature(final Text key, final Text type) {
        this.key = new UncheckedText(key);
        this.type = new UncheckedText(type);
    }

    /**
     * Instantiates a new SimpleAttributeSignature.
     * @param name The String to be used for key.
     * @param type The AttributeType to be used for type.
     */
    public SimpleAttributeSignature(
        final String name,
        final AttributeType type
    ) {
        this(
            new TextOfString(name),
            new TextOfScalar(type::name)
        );
    }

    @Override
    public final String name() {
        return this.key.asString();
    }

    @Override
    public final String type() {
        return this.type.asString();
    }

}
