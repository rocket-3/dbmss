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

public interface StringProperty extends Text {

    StringPropertySignature signature();

    @Override
    String asString();

    //    abstract class Envelope implements StringProperty {
    //        private final StringProperty stringProperty;
    //
    //        public Envelope(StringProperty stringProperty) {
    //            this.stringProperty = stringProperty;
    //        }
    //
    //        @Override
    //        public StringPropertySignature signature() {
    //            return this.stringProperty.signature();
    //        }
    //
    //        @Override
    //        public final String asString() throws Exception {
    //            return stringProperty.asString();
    //        }
    //    }
    //    
    //    final class OfScalar implements StringProperty {
    //        private final Unchecked<StringProperty> scalar;
    //        
    //        public OfScalar(Scalar<StringProperty> scalar) {
    //            this.scalar = new Unchecked<>(scalar);
    //        }
    //
    //        @Override
    //        public StringPropertySignature signature() {
    //            return this.scalar.value().signature();
    //        }
    //
    //        @Override
    //        public String asString() throws Exception {
    //            return this.scalar.value().asString();
    //        }
    //    }

    final class Of implements StringProperty {

        private final StringPropertySignature signature;

        private final UncheckedText text;

        private Of(final StringPropertySignature signature, final Text text) {
            this.signature = signature;
            this.text = new UncheckedText(text);
        }

        private Of(final Text key, final StringPropertyType type, final Text text) {
            this(
                new SimpleStringPropertySignature(
                    key,
                    type
                ),
                text
            );
        }

        public Of(final Text key, final CharSequence text) {
            this(
                key,
                StringPropertyType.Text,
                new TextOfScalar(() -> text)
            );
        }

        public Of(final CharSequence key, final CharSequence text) {
            this(new TextOf(key), text);
        }

        public Of(final Text key, final boolean bool) {
            this(
                key,
                StringPropertyType.Boolean,
                new TextOfScalar(() -> String.valueOf(bool))
            );
        }

        public Of(final CharSequence key, final boolean bool) {
            this(new TextOf(key), bool);
        }

        public Of(final Text key, final float number) {
            this(
                key,
                StringPropertyType.Float,
                new TextOfScalar(() -> String.valueOf(number))
            );
        }

        public Of(final CharSequence key, final float number) {
            this(new TextOf(key), number);
        }

        public Of(final Text key, final double number) {
            this(
                key,
                StringPropertyType.Float,
                new TextOfScalar(() -> String.valueOf(number))
            );
        }

        public Of(final CharSequence key, final double number) {
            this(new TextOf(key), number);
        }

        public Of(final Text key, final int number) {
            this(
                key,
                StringPropertyType.Integer,
                new TextOfScalar(() -> String.valueOf(number))
            );
        }

        public Of(final CharSequence key, final int number) {
            this(new TextOf(key), number);
        }

        public Of(final Text key, final long number) {
            this(
                key,
                StringPropertyType.Integer,
                new TextOfScalar(() -> String.valueOf(number))
            );
        }

        public Of(final CharSequence key, final long number) {
            this(new TextOf(key), number);
        }

        @Override
        public StringPropertySignature signature() {
            return this.signature;
        }

        @Override
        public final String asString() {
            return text.asString();
        }

    }

    //    final class Boolean extends Envelope {
    //        public Boolean(Text key, Text text) {
    //            super(
    //                new Of(key, StringPropertyType.Boolean, text)
    //            );
    //        }
    //    }
    //    
    //    final class Integer extends Envelope {
    //        public Integer(Text key, Text text) {
    //            super(
    //                new Of(key, StringPropertyType.Integer, text)
    //            );
    //        }
    //    }    
    //    
    //    final class Textual extends Envelope {
    //        public Textual(Text key, Text text) {
    //            super(
    //                new Of(key, StringPropertyType.Text, text)
    //            );
    //        }
    //    }
    //    
    //    final class Float extends Envelope {
    //        public Float(Text key, Text text) {
    //            super(
    //                new Of(key, StringPropertyType.Float, text)
    //            );
    //        }
    //    }
}
