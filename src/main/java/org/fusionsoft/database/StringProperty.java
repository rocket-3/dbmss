package org.fusionsoft.database;

import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.Unchecked;
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
    
    final class Of implements StringProperty{
        private final StringPropertySignature signature;
        private final UncheckedText text;

        private Of(StringPropertySignature signature, Text text) {
            this.signature = signature;
            this.text = new UncheckedText(text);
        }
        
        private Of(Text key, StringPropertyType type, Text text) {
            this(
                new SimpleStringPropertySignature(
                    key,
                    type
                ),
                text
            );
        }
        
        public Of(Text key, CharSequence text){
            this(
                key,
                StringPropertyType.Text,
                new TextOfScalar(() -> text)
            );
        }

        public Of(CharSequence key, CharSequence text) {
            this(new TextOf(key), text);
        }
        
        public Of(Text key, boolean bool){
            this(
                key,
                StringPropertyType.Boolean,
                new TextOfScalar(()->String.valueOf(bool))
            );
        }
        
        public Of(CharSequence key, boolean bool){
            this(new TextOf(key), bool);
        }
        
        public Of(Text key, float number){
            this(
                key,
                StringPropertyType.Float, 
                new TextOfScalar(()->String.valueOf(number))
            );
        }

        public Of(CharSequence key, float number) {
            this(new TextOf(key), number);
        }
        
        public Of(Text key, double number ){
            this(
                key,
                StringPropertyType.Float, 
                new TextOfScalar(()->String.valueOf(number))
            );
        }

        public Of(CharSequence key, double number) {
            this(new TextOf(key), number);
        }
        
        public Of(Text key, int number){
            this(
                key,
                StringPropertyType.Integer, 
                new TextOfScalar(()->String.valueOf(number))
            );
        }

        public Of(CharSequence key, int number) {
            this(new TextOf(key), number);
        }
        
        public Of(Text key, long number){
            this(
                key,
                StringPropertyType.Integer,
                new TextOfScalar(()->String.valueOf(number))
            );
        }

        public Of(CharSequence key, long number) {
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
