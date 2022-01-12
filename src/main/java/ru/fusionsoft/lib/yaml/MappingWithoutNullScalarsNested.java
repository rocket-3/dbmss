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
package ru.fusionsoft.lib.yaml;

import com.amihaiemil.eoyaml.Node;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;

/**
 * The type of {@link MappingWithoutNullScalars} that can go in depth when filters nodes.
 * @since 0.1
 */
public class MappingWithoutNullScalarsNested extends MappingWithoutNullScalars {

    /**
     * Instantiates a new Mapping without null scalars nested.
     * @param mapping The YamlMapping to be encapsulated.
     */
    public MappingWithoutNullScalarsNested(final YamlMapping mapping) {
        super(
            new YamlMappingOfEntries(
                new Mapped<Map.Entry<? extends Text, ? extends YamlNode>>(
                    entry -> {
                        final YamlNode value = entry.getValue();
                        if (value.type().equals(Node.MAPPING)) {
                            entry = new MapEntry<>(
                                entry.getKey(),
                                new MappingWithoutNullScalarsNested(
                                    value.asMapping()
                                )
                            );
                        }
                        return entry;
                    },
                    new EntriesOfYamlMapping(mapping)
                )
            )
        );
    }

}
