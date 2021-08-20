package org.fusionsoft.database.stringproperty;

import java.util.Collection;
import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.set.SetOf;
import org.fusionsoft.database.StringProperty;
import org.fusionsoft.database.StringPropertyType;
import org.fusionsoft.database.stringproperty.signature.SimpleStringPropertySignature;

public class TableProps extends CollectionEnvelope<StringProperty> {
    public TableProps(Collection<StringProperty> collection){
        super(
            new PropsHasKeys(
                new SetOf<>(
                    new SimpleStringPropertySignature("param1", StringPropertyType.Text),
                    new SimpleStringPropertySignature("param2", StringPropertyType.Integer)
                ),
                collection
            )
        );
    }
    
    public TableProps(String param1, Integer param2) {
        this(
            new SetOf<>(
                new StringProperty.Of("param1", param1),
                new StringProperty.Of("param2", param2)
            )
        );
    }
}
