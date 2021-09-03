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

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNodeNotFoundException;
import org.cactoos.Fallback;
import org.cactoos.scalar.ScalarOf;
import org.cactoos.scalar.ScalarWithFallback;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOf;

/**
 * The type of Text that can be constructed of value from
 *  {@link YamlMapping} and its key, which may be absent and empty value is
 *   returned in that case.
 * @since 0.1
 */
public class MaybeEmptyTextOf extends TextEnvelope {

    /**
     * Instantiates a new Maybe empty text of.
     * @param mapping The StrictYamlMapping to be encapsulated.
     * @param key The String to be encapsulated.
     */
    public MaybeEmptyTextOf(
        final StrictYamlMapping mapping,
        final String key
    ) {
        super(
            new TextOf(
                new ScalarWithFallback<>(
                    new ScalarOf<>(
                        () -> mapping.value(key).asScalar().value()
                    ),
                    new Fallback.From<>(
                        YamlNodeNotFoundException.class,
                        e -> ""
                    )
                )
            )
        );
    }

}