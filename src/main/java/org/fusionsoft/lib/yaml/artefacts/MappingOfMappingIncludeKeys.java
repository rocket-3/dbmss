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
package org.fusionsoft.lib.yaml.artefacts;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlMappingBuilder;
import java.util.Set;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.Sticky;
import org.cactoos.set.SetOf;
import org.fusionsoft.lib.yaml.YamlMappingOfScalar;

/**
 * The type of YamlMapping that can be constructed of another
 *  with some keys picked from it.
 * @since 0.1
 */
public class MappingOfMappingIncludeKeys extends YamlMappingOfScalar {

    /**
     * Instantiates a new Yaml mapping envelope.
     * @param mapping The YamlMapping to be encapsulated.
     * @param includes The Iterable of Text to be encapsulated.
     */
    public MappingOfMappingIncludeKeys(
        final YamlMapping mapping,
        final Text... includes
    ) {
        this(
            mapping,
            new IterableOf<Text>(includes)
        );
    }

    /**
     * Instantiates a new Yaml mapping envelope.
     * @param mapping The YamlMapping to be encapsulated.
     * @param includes The Iterable of Text to be encapsulated.
     */
    public MappingOfMappingIncludeKeys(
        final YamlMapping mapping,
        final Iterable<Text> includes
    ) {
        this(
            mapping,
            new SetOf<String>(new Mapped<>(Text::asString, includes))
        );
    }

    /**
     * Instantiates a new Yaml mapping envelope.
     * @param mapping The YamlMapping to be encapsulated.
     * @param includes The Iterable of Text to be encapsulated.
     */
    public MappingOfMappingIncludeKeys(
        final YamlMapping mapping,
        final Set<String> includes
    ) {
        super(
            new Sticky<>(
                () -> {
                    YamlMappingBuilder yml = Yaml.createYamlMappingBuilder();
                    final Set<String> keys = new KeysFromYamlNode(mapping);
                    for (final String key : includes) {
                        if (keys.contains(key)) {
                            yml = yml.add(key, mapping.value(key));
                        }
                    }
                    return yml.build();
                }
            )
        );
    }

}
