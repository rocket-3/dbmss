package org.fusionsoft.database.stringproperty;

import org.cactoos.set.SetOf;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.StringProperty;
import org.fusionsoft.database.StringPropertyType;
import org.fusionsoft.database.stringproperty.signature.SimpleStringPropertySignature;
import org.junit.jupiter.api.Test;

class PropsHasKeysTest {
    @Test
    public void creates(){
        new PropsHasKeys(
            new SetOf<>(
                new SimpleStringPropertySignature("param1", StringPropertyType.Text),
                new SimpleStringPropertySignature("param2", StringPropertyType.Integer)
            ),
            new SetOf<>(
                new StringProperty.Of("param1", "abc"),
                new StringProperty.Of("param2", 2)
            )
        ).forEach(
            x -> {
                System.out.println(new UncheckedText(x).asString());
            }   
        );
    }

}
