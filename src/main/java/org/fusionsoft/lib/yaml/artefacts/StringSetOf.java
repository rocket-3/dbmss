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
package org.fusionsoft.lib.yaml.artefacts;

import com.amihaiemil.eoyaml.Scalar;
import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.set.SetEnvelope;
import org.cactoos.set.SetOf;

/**
 * The type Set of that can be constructed of different Yaml artifacts.
 * @see StringIterableOf
 * @since 0.1
 */
public class StringSetOf extends SetEnvelope<String> {

    /**
     * Instantiates a new String set of {@link YamlMapping}.
     * @param mapping The YamlMapping to be encapsulated.
     * @param key The String key to be encapsulated.
     */
    public StringSetOf(final YamlMapping mapping, final String key) {
        super(
            new SetOf<>(
                new StringIterableOf(mapping, key)
            )
        );
    }

    /**
     * Instantiates a new String set of {@link Scalar}.
     * @param scalar The Scalar to be encapsulated.
     */
    public StringSetOf(final Scalar scalar) {
        super(
            new SetOf<String>(
                new StringIterableOf(scalar)
            )
        );
    }

}
