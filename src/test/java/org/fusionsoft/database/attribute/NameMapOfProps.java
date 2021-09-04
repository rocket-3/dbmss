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

import org.cactoos.map.MapEnvelope;
import org.cactoos.map.MapOf;
import org.fusionsoft.database.Attribute;
import org.fusionsoft.database.AttributeSignature;

/**
 * The type of Map, keyed by
 * {@link AttributeSignature}
 * that can be constructed of iterable of {@link Attribute}.
 * @since 0.1
 */
public class NameMapOfProps extends MapEnvelope<String, Attribute> {

    /**
     * Ctor.
     * @param iterable The iterable of StringProperty.
     */
    public NameMapOfProps(final Iterable<Attribute> iterable) {
        super(
            new MapOf<>(
                sp -> sp.signature().name(),
                sp -> sp,
                iterable
            )
        );
    }

}
