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
import org.fusionsoft.database.StringPropertyType;
import org.fusionsoft.database.stringproperty.signature.SimpleStringPropertySignature;

public class TableProps extends CollectionEnvelope<StringProperty> {

    public TableProps(final Collection<StringProperty> collection) {
        super(
            new PropsHasKeys(
                new SetOf<>(
                    new SimpleStringPropertySignature(
                        "param1",
                        StringPropertyType.Text
                    ),
                    new SimpleStringPropertySignature(
                        "param2",
                        StringPropertyType.Integer
                    )
                ),
                collection
            )
        );
    }

    public TableProps(final String param1, final Integer param2) {
        this(
            new SetOf<>(
                new StringProperty.Of("param1", param1),
                new StringProperty.Of("param2", param2)
            )
        );
    }

}
