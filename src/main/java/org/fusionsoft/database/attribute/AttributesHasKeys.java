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
import java.util.Set;
import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.ScalarOf;
import org.cactoos.set.SetOf;
import org.fusionsoft.database.Attribute;
import org.fusionsoft.database.AttributeSignature;
import org.fusionsoft.lib.collection.MapHasKeys;

/**
 * The decorator of {@link Attribute} collection.
 * and signatures it must contain.
 * @see AttributeSignature
 * @since 0.1
 */
public class AttributesHasKeys extends CollectionEnvelope<Attribute> {

    /**
     * Instantiates a new AttributesHasKeys.
     * @param keys The Set of StringPropertySignature must present in props.
     * @param props The Collection of StringProperty to be encapsulated
     *  and tested at runtime.
     */
    public AttributesHasKeys(
        final Set<AttributeSignature> keys,
        final Collection<Attribute> props
    ) {
        super(
            new SetOf<>(
                new IterableOf<>(
                    new ScalarOf<>(
                        x -> x.values().iterator(),
                        new MapHasKeys<>(
                            keys,
                            new AttributesMap(props)
                        )
                    )
                )
            )
        );
    }

}
