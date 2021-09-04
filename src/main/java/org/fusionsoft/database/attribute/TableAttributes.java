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
package org.fusionsoft.database.attribute;

import java.util.Collection;
import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.set.SetOf;
import org.fusionsoft.database.Attribute;
import org.fusionsoft.database.attribute.signature.SimpleAttributeSignature;
import org.fusionsoft.database.attribute.type.TypeInteger;
import org.fusionsoft.database.attribute.type.TypeText;

/**
 * Attributes of table object in some DBMS.
 * @since 0.1
 */
public class TableAttributes extends CollectionEnvelope<Attribute> {

    /**
     * Instantiates a new TableAttributes.
     * @param collection The collection of {@link Attribute} to be encapsulated.
     */
    public TableAttributes(final Collection<Attribute> collection) {
        super(
            new AttributesHasKeys(
                new SetOf<>(
                    new SimpleAttributeSignature(
                        "param1",
                        new TypeText()
                    ),
                    new SimpleAttributeSignature(
                        "param2",
                        new TypeInteger()
                    )
                ),
                collection
            )
        );
    }

    /**
     * Instantiates a new TableAttributes.
     * @param string The String to be encapsulated.
     * @param number The Integer to be encapsulated.
     */
    public TableAttributes(final String string, final Integer number) {
        this(
            new SetOf<>(
                new AttributeOf("string", string),
                new AttributeOf("number", number)
            )
        );
    }

}
