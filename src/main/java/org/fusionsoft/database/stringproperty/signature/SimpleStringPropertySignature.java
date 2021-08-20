package org.fusionsoft.database.stringproperty.signature;

import java.text.MessageFormat;
import java.util.Objects;
import org.cactoos.Text;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.StringPropertySignature;
import org.fusionsoft.database.StringPropertyType;

public class SimpleStringPropertySignature implements StringPropertySignature {
    private final StringPropertyType type;
    private final UncheckedText key;

    public SimpleStringPropertySignature(Text key, StringPropertyType type ) {
        this.type = type;
        this.key = new UncheckedText(key);
    }
    
    public SimpleStringPropertySignature(CharSequence key, StringPropertyType type ) {
        this(
            new TextOf(key), 
            type
        );
    }

    @Override
    public StringPropertyType type() {
        return this.type;
    }

    @Override
    public String key() {
        return this.key.asString();
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} : {1}", this.key, new UncheckedText(this.type).asString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (! ( o instanceof SimpleStringPropertySignature )) {
            return false;
        }
        final SimpleStringPropertySignature that = (SimpleStringPropertySignature) o;
        return that.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }
}
