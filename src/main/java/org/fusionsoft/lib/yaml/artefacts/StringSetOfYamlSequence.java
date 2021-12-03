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
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.set.SetEnvelope;
import org.cactoos.set.SetOf;

/**
 * The type Set of that can be constructed of different Yaml artifacts.
 * @see IterableOfYamlSequence
 * @since 0.1
 */
public class StringSetOfYamlSequence extends SetEnvelope<String> {

    /**
     * Instantiates a new String set of {@link Scalar}.
     * @param sequence The Scalar to be encapsulated.
     */
    public StringSetOfYamlSequence(final YamlNode sequence) {
        super(
            new SetOf<>(
                new IterableOfYamlSequence<>(sequence, x -> x.asScalar().value())
            )
        );
    }

}