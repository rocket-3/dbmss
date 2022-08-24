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
import java.util.Map;
import org.cactoos.Text;
import ru.fusionsoft.lib.yaml.YamlMappingEnvelope;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The type to types casts of some DBMS mapping, (example) type1: type2: implicit: true.
 * @since 0.1
 */
public class TypecastsOfDbmsMapping extends YamlMappingEnvelope {

    /**
     * Instantiates a new TypecastsMapping.
     * @param mapping The YamlMapping to be encapsulated.
     */
    private TypecastsOfDbmsMapping(final YamlMapping mapping) {
        super(mapping);
    }

    /**
     * Instantiates a new Typecasts of dbms mapping.
     * @param entries The entries of {@link TypecastsOfTypeMapping}.
     */
    public TypecastsOfDbmsMapping(final Iterable<Map.Entry<Text, TypecastsOfTypeMapping>> entries) {
        this(
            new YamlMappingOfEntries(
                entries
            )
        );
    }

}
