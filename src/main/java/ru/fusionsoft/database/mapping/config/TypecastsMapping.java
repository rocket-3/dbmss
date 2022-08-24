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
package ru.fusionsoft.database.mapping.config;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.lib.yaml.YamlMappingEnvelope;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The casts of some DBMS type mapping, part of {@link MigrationConfigMapping},
 *  (example) postgres: type1: type2: implicit: true.
 * @since 0.1
 */
public class TypecastsMapping extends YamlMappingEnvelope {

    /**
     * Instantiates a new Typecasts mapping.
     * @param mapping The {@link YamlMapping} to be encapsulated.
     */
    private TypecastsMapping(final YamlMapping mapping) {
        super(mapping);
    }

    /**
     * Instantiates a new Typecasts mapping.
     * @param entries The entries of {@link TypecastsOfDbmsMapping}.
     */
    public TypecastsMapping(final Iterable<Map.Entry<Dbms, TypecastsOfDbmsMapping>> entries) {
        this(
            new YamlMappingOfEntries(
                new Mapped<Map.Entry<? extends Text, ? extends YamlNode>>(
                    entry -> new MapEntry<>(
                        entry.getKey().dbd(),
                        entry.getValue()
                    ),
                    entries
                )
            )
        );
    }

}
