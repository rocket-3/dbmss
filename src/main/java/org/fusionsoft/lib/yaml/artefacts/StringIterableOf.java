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
import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import com.amihaiemil.eoyaml.YamlNodeNotFoundException;
import java.util.Iterator;
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

/**
 * The set of String, can be constructed of different Yaml artifacts.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class StringIterableOf extends IterableEnvelope<String> {

    /**
     * Instantiates a new String iterable of.
     * @param mapping The StrictYamlMapping to be encapsulated.
     * @param key The String to be encapsulated.
     */
    private StringIterableOf(
        final StrictYamlMapping mapping,
        final String key
    ) {
        super(
            new Mapped<>(
                x -> x.asScalar().value(),
                new IterableOf<YamlNode>(
                    new ScalarWithFallback<Iterator<? extends YamlNode>>(
                        () -> mapping
                            .value(key)
                            .asSequence()
                            .iterator(),
                        new Fallback.From<>(
                            YamlNodeNotFoundException.class,
                            exception -> new IteratorOf<YamlNode>()
                        )
                    )
                )
            )
        );
    }

    /**
     * Instantiates a new String iterable of {@link YamlMapping} by key.
     * @param mapping The YamlMapping to be encapsulated.
     * @param key The String key to be encapsulated.
     */
    public StringIterableOf(
        final YamlMapping mapping,
        final String key
    ) {
        this(new StrictYamlMapping(mapping), key);
    }

    /**
     * Instantiates a new String iterable of Yaml {@link Scalar}.
     * @param scalar The yaml Scalar to be encapsulated.
     */
    public StringIterableOf(final Scalar scalar) {
        super(
            new Mapped<>(
                Text::asString,
                new Split(
                    new Sub(
                        new TextOf(scalar.value()),
                        1,
                        str -> str.length() - 1
                    ),
                    ",[ ]?"
                )
            )
        );
    }

}
