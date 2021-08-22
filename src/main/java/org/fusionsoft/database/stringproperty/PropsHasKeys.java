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
 *
 */
package org.fusionsoft.database.stringproperty;

import java.util.Collection;
import java.util.Set;
import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.ScalarOf;
import org.cactoos.set.SetOf;
import org.fusionsoft.database.StringProperty;
import org.fusionsoft.database.StringPropertySignature;
import org.fusionsoft.lib.collection.MapHasKeys;

public class PropsHasKeys extends CollectionEnvelope<StringProperty> {

    public PropsHasKeys(final Set<StringPropertySignature> keys, final Collection<StringProperty> props) {
        super(
            new SetOf<>(
                new IterableOf<>(
                    new ScalarOf<>(
                        x -> x.values().iterator(),
                        new MapHasKeys<>(
                            keys,
                            new SignatureMapOfProps(props)
                        )
                    )
                )
            )
        );
    }

}
