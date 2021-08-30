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

import org.cactoos.Text;
import org.cactoos.text.TextOf;
import org.cactoos.text.TextOfScalar;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.stringproperty.signature.SimpleStringPropertySignature;
import org.fusionsoft.database.stringproperty.type.Bool;
import org.fusionsoft.database.stringproperty.type.Chars;
import org.fusionsoft.database.stringproperty.type.Float;
import org.fusionsoft.database.stringproperty.type.Int;

/**
 * The interface StringProperty representing textual property of some domain
 *  entity.
 * @since 0.1
 */
@SuppressWarnings("PMD.IntegerInstantiation")
public interface StringProperty extends Text {

    /**
     * Signature string property signature.
     * @return The string property signature.
     */
    StringPropertySignature signature();

    @Override
    String asString();

    /**
     * The type of that can be constructed of.
     * @since 0.1
     */
    final class Of implements StringProperty {

        /**
         * The StringPropertySignature encapsulated.
         */
        private final StringPropertySignature signature;

        /**
         * The UncheckedText encapsulated.
         */
        private final UncheckedText text;

        /**
         * Instantiates a new StringProperty.
         * @param signature The StringPropertySignature to be encapsulated.
         * @param text The Text to be encapsulated.
         */
        private Of(final StringPropertySignature signature, final Text text) {
            this.signature = signature;
            this.text = new UncheckedText(text);
        }

        /**
         * Instantiates a new StringProperty.
         * @param key The Text to be encapsulated.
         * @param type The StringPropertyType to be encapsulated.
         * @param text The Text to be encapsulated.
         */
        private Of(final Text key, final StringPropertyType type, final Text text) {
            this(
                new SimpleStringPropertySignature(
                    key,
                    type
                ),
                text
            );
        }

        /**
         * Instantiates a new StringProperty.
         * @param key The Text to be encapsulated.
         * @param text The CharSequence to be encapsulated.
         */
        public Of(final Text key, final CharSequence text) {
            this(
                key,
                new Chars(),
                new TextOfScalar(() -> text)
            );
        }

        /**
         * Instantiates a new StringProperty.
         * @param key The CharSequence to be encapsulated.
         * @param text The CharSequence to be encapsulated.
         */
        public Of(final CharSequence key, final CharSequence text) {
            this(new TextOf(key), text);
        }

        /**
         * Instantiates a new StringProperty.
         * @param key The Text to be encapsulated.
         * @param bool The boolean to be encapsulated.
         */
        public Of(final Text key, final boolean bool) {
            this(
                key,
                new Bool(),
                new TextOfScalar(() -> String.valueOf(bool))
            );
        }

        /**
         * Instantiates a new StringProperty.
         * @param key The CharSequence to be encapsulated.
         * @param bool The boolean to be encapsulated.
         */
        public Of(final CharSequence key, final boolean bool) {
            this(new TextOf(key), bool);
        }

        /**
         * Instantiates a new StringProperty.
         * @param key The Text to be encapsulated.
         * @param number The float to be encapsulated.
         */
        public Of(final Text key, final float number) {
            this(
                key,
                new Float(),
                new TextOfScalar(() -> String.valueOf(number))
            );
        }

        /**
         * Instantiates a new StringProperty.
         * @param key The CharSequence to be encapsulated.
         * @param number The float to be encapsulated.
         */
        public Of(final CharSequence key, final float number) {
            this(new TextOf(key), number);
        }

        /**
         * Instantiates a new StringProperty.
         * @param key The Text to be encapsulated.
         * @param number The double to be encapsulated.
         */
        public Of(final Text key, final double number) {
            this(
                key,
                new Float(),
                new TextOfScalar(() -> String.valueOf(number))
            );
        }

        /**
         * Instantiates a new StringProperty.
         * @param key The CharSequence to be encapsulated.
         * @param number The double to be encapsulated.
         */
        public Of(final CharSequence key, final double number) {
            this(new TextOf(key), number);
        }

        /**
         * Instantiates a new StringProperty.
         * @param key The Text to be encapsulated.
         * @param number The int to be encapsulated.
         */
        public Of(final Text key, final int number) {
            this(
                key,
                new Int(),
                new TextOfScalar(() -> String.valueOf(number))
            );
        }

        /**
         * Instantiates a new StringProperty.
         * @param key The CharSequence to be encapsulated.
         * @param number The int to be encapsulated.
         */
        public Of(final CharSequence key, final int number) {
            this(new TextOf(key), number);
        }

        /**
         * Instantiates a new StringProperty.
         * @param key The Text to be encapsulated.
         * @param number The long to be encapsulated.
         */
        public Of(final Text key, final long number) {
            this(
                key,
                new Int(),
                new TextOfScalar(() -> String.valueOf(number))
            );
        }

        /**
         * Instantiates a new StringProperty.
         * @param key The CharSequence to be encapsulated.
         * @param number The long to be encapsulated.
         */
        public Of(final CharSequence key, final long number) {
            this(new TextOf(key), number);
        }

        @Override
        public StringPropertySignature signature() {
            return this.signature;
        }

        @Override
        public String asString() {
            return this.text.asString();
        }

    }

}
