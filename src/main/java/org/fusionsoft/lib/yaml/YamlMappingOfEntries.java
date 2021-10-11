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
package org.fusionsoft.lib.yaml;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMappingBuilder;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.map.MapEntry;

/**
 * The type of YamlMapping that can be constructed of map entries.
 * @since 0.1
 */
public class YamlMappingOfEntries extends YamlMappingOfScalar {

    /**
     * Instantiates a new Yaml mapping of scalar.
     * @param entries The keys and YamlNodes associated to put into the mapping.
     */
    public YamlMappingOfEntries(final Iterable<MapEntry<Text, YamlNode>> entries) {
        super(
            () -> {
                YamlMappingBuilder yaml = Yaml.createYamlMappingBuilder();
                for (final MapEntry<Text, YamlNode> entry : entries) {
                    yaml = yaml.add(
                        entry.getKey().asString(),
                        entry.getValue()
                    );
                }
                return yaml.build();
            }
        );
    }

    /**
     * Instantiates a new Yaml mapping of scalar.
     * @param entries The keys and YamlNodes associated to put into the mapping.
     */
    @SafeVarargs
    public YamlMappingOfEntries(final MapEntry<Text, YamlNode>... entries) {
        this(new IterableOf<>(entries));
    }

}
