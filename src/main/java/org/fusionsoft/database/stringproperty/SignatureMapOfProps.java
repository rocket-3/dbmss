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

import org.cactoos.map.MapEnvelope;
import org.cactoos.map.MapOf;
import org.fusionsoft.database.StringProperty;
import org.fusionsoft.database.StringPropertySignature;

/**
 * The type of Map that can be constructed of iterable {@link StringProperty},
 * keyed by {@link StringPropertySignature}.
 * @since 0.1
 */
public class SignatureMapOfProps extends MapEnvelope<StringPropertySignature, StringProperty> {

    /**
     * Ctor.
     * @param iterable The iterable of StringProperty.
     */
    public SignatureMapOfProps(final Iterable<StringProperty> iterable) {
        super(
            new MapOf<>(
                StringProperty::signature,
                sp -> sp,
                iterable
            )
        );
    }

}
