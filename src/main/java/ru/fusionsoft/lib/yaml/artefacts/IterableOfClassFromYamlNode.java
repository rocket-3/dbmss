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

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import com.amihaiemil.eoyaml.YamlSequence;
import org.cactoos.BiFunc;
import org.cactoos.Func;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Mapped;

/**
 * The type of {@link Iterable} that can be constructed of YamlMapping.
 * @param <Y> The type of iterated parameter.
 * @since 0.1
 */
public class IterableOfClassFromYamlNode<Y> extends IterableEnvelope<Y> {

    /**
     * Instantiates a new Iterable of class from yaml node.
     * @param constructor The BiFunc that has a YamlMapping and YamlNode per key
     *  and  returns an element of an Iterable, to be used for extracting.
     * @param mapping The YamlMapping to be used for extracting.
     */
    public IterableOfClassFromYamlNode(
        final BiFunc<YamlMapping, YamlNode, Y> constructor,
        final YamlMapping mapping
    ) {
        super(
            new Mapped<>(
                key -> constructor.apply(mapping, key),
                mapping.keys()
            )
        );
    }

    /**
     * Instantiates a new Iterable of class of YamlSequence.
     * @param constructor The Func of YamlMapping to be used to create Y inst.
     * @param sequence The YamlSequence of YamlMapping to be used.
     */
    public IterableOfClassFromYamlNode(
        final Func<YamlMapping, Y> constructor,
        final YamlSequence sequence
    ) {
        super(
            new Mapped<>(
                constructor,
                new Mapped<YamlMapping>(
                    YamlNode::asMapping,
                    sequence.values()
                )
            )
        );
    }

}
