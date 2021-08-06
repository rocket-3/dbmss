package org.fusionsoft.database.artefacts;

import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.Unchecked;

public interface StringProperty extends Text {
    StringPropertyType type();
    
    final class Envelope implements StringProperty {
        private final StringProperty stringProperty;

        public Envelope(StringProperty stringProperty) {
            this.stringProperty = stringProperty;
        }

        @Override
        public StringPropertyType type() {
            return stringProperty.type();
        }

        @Override
        public String asString() throws Exception {
            return stringProperty.asString();
        }
    }
    
    final class OfScalar implements StringProperty {
        private final Unchecked<StringProperty> scalar;
        
        public OfScalar(Scalar<StringProperty> scalar) {
            this.scalar = new Unchecked<>(scalar);
        }

        @Override
        public StringPropertyType type() {
            return scalar.value().type();
        }

        @Override
        public String asString() throws Exception {
            return scalar.value().asString();
        }
    }
    
    abstract class OfText implements StringProperty{
        private final StringPropertyType stringPropertyType;
        private final CharSequence text;

        protected OfText(StringPropertyType stringPropertyType, CharSequence text) {
            this.stringPropertyType = stringPropertyType;
            this.text = text;
        }

        @Override
        public StringPropertyType type() {
            return stringPropertyType;
        }

        @Override
        public String asString() throws Exception {
            return String.valueOf(text);
        }
    }
    
    final class Boolean extends OfText {
        protected Boolean(CharSequence text) {
            super(StringPropertyType.Boolean, text);
        }
    }
    
    final class Integer extends OfText {
        protected Integer(CharSequence text) {
            super(StringPropertyType.Integer, text);
        }
    }    
    
    final class Text extends OfText {
        protected Text(CharSequence text) {
            super(StringPropertyType.Text, text);
        }
    }
    
    final class Float extends OfText {
        protected Float(CharSequence text) {
            super(StringPropertyType.Float, text);
        }
    }
}
