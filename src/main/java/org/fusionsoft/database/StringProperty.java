package org.fusionsoft.database;

import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.Unchecked;
import org.cactoos.text.TextOf;
import org.cactoos.text.TextOfScalar;

public interface StringProperty extends Text {
    StringPropertyType type();
    
    abstract class Envelope implements StringProperty {
        private final StringProperty stringProperty;

        public Envelope(StringProperty stringProperty) {
            this.stringProperty = stringProperty;
        }

        @Override
        public final StringPropertyType type() {
            return stringProperty.type();
        }

        @Override
        public final String asString() throws Exception {
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
    
    final class Of implements StringProperty{
        private final StringPropertyType stringPropertyType;
        private final Text text;

        private Of(Text text, StringPropertyType stringPropertyType) {
            this.stringPropertyType = stringPropertyType;
            this.text = text;
        }
        
        public Of(boolean bool){
            this(new TextOfScalar(()->String.valueOf(bool)), StringPropertyType.Boolean);
        }
        
        public Of(float number){
            this(new TextOfScalar(()->String.valueOf(number)), StringPropertyType.Float);
        }      
        
        public Of(double number){
            this(new TextOfScalar(()->String.valueOf(number)), StringPropertyType.Float);
        }
        
        public Of(int number){
            this(new TextOfScalar(()->String.valueOf(number)), StringPropertyType.Integer);
        }
        
        public Of(long number){
            this(new TextOfScalar(()->String.valueOf(number)), StringPropertyType.Integer);
        }

        @Override
        public final StringPropertyType type() {
            return stringPropertyType;
        }

        @Override
        public final String asString() throws Exception {
            return text.asString();
        }
    }
    
    final class Boolean extends Envelope {
        public Boolean(Text text) {
            super(
                new Of(text, StringPropertyType.Boolean)
            );
        }
    }
    
    final class Integer extends Envelope {
        public Integer(Text text) {
            super(
                new Of(text, StringPropertyType.Integer)
            );
        }
    }    
    
    final class Textual extends Envelope {
        public Textual(Text text) {
            super(
                new Of(text, StringPropertyType.Text)
            );
        }
    }
    
    final class Float extends Envelope {
        public Float(Text text) {
            super(
                new Of(text, StringPropertyType.Float)
            );
        }
    }
}
