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
import org.cactoos.iterable.Mapped;
import org.cactoos.set.SetOf;
import org.fusionsoft.lib.yaml.YamlMappingOfScalar;

/**
 * The YamlMapping constructed from other but w/0 some keys ignored.
 * @since 0.1
 */
public class MappingFromMappingIgnoreKeys extends YamlMappingOfScalar {

    /**
     * Instantiates a new Mapping from mapping ignore keys.
     * @param mapping The YamlMapping to be encapsulated.
     * @param ignore The Iterable of Text to be encapsulated.
     */
    public MappingFromMappingIgnoreKeys(
        final YamlMapping mapping,
        final Iterable<Text> ignore
    ) {
        this(
            mapping,
            new SetOf<String>(new Mapped<String>(Text::asString, ignore))
        );
    }

    /**
     * Instantiates a new Mapping from mapping ignore keys.
     * @param mapping The YamlMapping to be encapsulated.
     * @param ignores The Set of String to be encapsulated.
     */
    public MappingFromMappingIgnoreKeys(
        final YamlMapping mapping,
        final Set<String> ignores
    ) {
        super(
            () -> {
                YamlMappingBuilder yml = Yaml.createYamlMappingBuilder();
                for (final String key : new KeysFromYamlNode(mapping)) {
                    if (!ignores.contains(key)) {
                        yml = yml.add(key, mapping.value(key));
                    }
                }
                return yml.build();
            }
        );
    }

}
