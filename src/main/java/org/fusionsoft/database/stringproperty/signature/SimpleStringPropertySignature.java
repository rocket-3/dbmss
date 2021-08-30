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
package org.fusionsoft.database.stringproperty.signature;

import java.text.MessageFormat;
import java.util.Objects;
import org.cactoos.Text;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.StringPropertySignature;
import org.fusionsoft.database.StringPropertyType;

/**
 * The signature of StringProperty entity
 * that can be constructed of key and type.
 * @since 0.1
 */
@SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
public class SimpleStringPropertySignature implements StringPropertySignature {

    /**
     * The StringPropertyType encapsulated.
     */
    private final StringPropertyType type;

    /**
     * The UncheckedText encapsulated.
     */
    private final UncheckedText key;

    /**
     * Instantiates a new Simple string property signature.
     * @param key The Text to be encapsulated.
     * @param type The StringPropertyType to be encapsulated.
     */
    public SimpleStringPropertySignature(
        final Text key,
        final StringPropertyType type
    ) {
        this.type = type;
        this.key = new UncheckedText(key);
    }

    /**
     * Instantiates a new Simple string property signature.
     * @param key The CharSequence to be encapsulated.
     * @param type The StringPropertyType to be encapsulated.
     */
    public SimpleStringPropertySignature(
        final CharSequence key,
        final StringPropertyType type
    ) {
        this(new TextOf(key), type);
    }

    @Override
    public final StringPropertyType type() {
        return this.type;
    }

    @Override
    public final String name() {
        return this.key.asString();
    }

    @Override
    public final String toString() {
        return MessageFormat.format(
            "{0} : {1}",
            this.key,
            new UncheckedText(this.type).asString()
        );
    }

    @Override
    public final boolean equals(final Object object) {
        return object instanceof SimpleStringPropertySignature
            && this.toString().equals(
            ((SimpleStringPropertySignature) object).toString()
        );
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.toString());
    }

}
