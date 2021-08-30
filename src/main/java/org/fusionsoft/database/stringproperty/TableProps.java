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
package org.fusionsoft.database.stringproperty;

import java.util.Collection;
import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.set.SetOf;
import org.fusionsoft.database.StringProperty;
import org.fusionsoft.database.stringproperty.signature.SimpleStringPropertySignature;
import org.fusionsoft.database.stringproperty.type.Chars;
import org.fusionsoft.database.stringproperty.type.Int;

/**
 * The type of that can be constructed of.
 * @since 0.1
 */
public class TableProps extends CollectionEnvelope<StringProperty> {

    /**
     * Instantiates a new Table props.
     * @param collection The Collection of StringProperty to be encapsulated.
     */
    public TableProps(final Collection<StringProperty> collection) {
        super(
            new PropsHasKeys(
                new SetOf<>(
                    new SimpleStringPropertySignature(
                        "param1",
                        new Chars()
                    ),
                    new SimpleStringPropertySignature(
                        "param2",
                        new Int()
                    )
                ),
                collection
            )
        );
    }

    /**
     * Instantiates a new Table props.
     * @param string The String to be encapsulated.
     * @param number The Integer to be encapsulated.
     * @checkstyle ParameterNameCheck (100 lines)
     */
    public TableProps(final String string, final Integer number) {
        this(
            new SetOf<>(
                new StringProperty.Of("string", string),
                new StringProperty.Of("number", number)
            )
        );
    }

}
