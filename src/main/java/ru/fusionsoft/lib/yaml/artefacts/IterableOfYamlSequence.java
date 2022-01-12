/*
 * Copyright (C) 2018-2022 FusionSoft
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
package ru.fusionsoft.lib.yaml.artefacts;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Func;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;

/**
 * The set of String, can be constructed of different Yaml artifacts.
 * @param <T> The type parameter.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class IterableOfYamlSequence<T> extends IterableEnvelope<T> {

    /**
     * Instantiates a new Iterable of yaml sequence.
     * @param node The {@link YamlNode} to be encapsulated.
     * @param extraction The {@link Func} to extract {@link T} from each value to be encapsulated.
     */
    public IterableOfYamlSequence(
        final YamlNode node,
        final Func<YamlNode, T> extraction
    ) {
        super(
            new IterableOf<>(
                () -> new org.cactoos.iterator.Mapped<T>(
                    extraction,
                    node.asSequence().values().iterator()
                )
            )
        );
    }

}
