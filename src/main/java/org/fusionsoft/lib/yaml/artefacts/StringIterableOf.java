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
package org.fusionsoft.lib.yaml.artefacts;

import com.amihaiemil.eoyaml.Scalar;
import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNodeNotFoundException;
import org.cactoos.Fallback;
import org.cactoos.Text;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterator.IteratorOf;
import org.cactoos.scalar.ScalarWithFallback;
import org.cactoos.text.Split;
import org.cactoos.text.Sub;
import org.cactoos.text.TextOf;

public class StringIterableOf extends IterableEnvelope<String> {

    private StringIterableOf(final StrictYamlMapping yamlMapping, final String key) {
        super(
            new Mapped<>(
                x -> x.asScalar().value(),
                new IterableOf<>(
                    new ScalarWithFallback<>(
                        () -> yamlMapping.value(key)
                            .asSequence()
                            .iterator(),
                        new Fallback.From<>(
                            YamlNodeNotFoundException.class,
                            (ynnfe) -> new IteratorOf<>()
                        )
                    )
                )
            )
        );
    }

    public StringIterableOf(final YamlMapping yamlMapping, final String key) {
        this(new StrictYamlMapping(yamlMapping), key);
    }

    public StringIterableOf(final Scalar yamlScalar) {
        super(
            new Mapped<>(
                Text::asString,
                new Split(
                    new Sub(
                        new TextOf(yamlScalar.value()),
                        1,
                        (str) -> str.length() - 2
                    ),
                    ",[ ]?"
                )
            )
        );
    }

}
