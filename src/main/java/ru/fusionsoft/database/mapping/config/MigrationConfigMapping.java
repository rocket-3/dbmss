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
import org.cactoos.Text;
import org.cactoos.map.MapEntry;
import ru.fusionsoft.lib.yaml.YamlMappingEnvelope;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The mapping representing migration config, i.e. typecasts and maybe other options.
 * @since 0.1
 */
public class MigrationConfigMapping extends YamlMappingEnvelope {

    /**
     * Instantiates a new MigrationConfigMapping.
     * @param mapping The YamlMapping to be encapsulated.
     */
    public MigrationConfigMapping(final YamlMapping mapping) {
        super(mapping);
    }

    /**
     * Instantiates a new Migration config mapping.
     * @param typecasts The {@link TypecastsMapping} to be encapsulated.
     */
    public MigrationConfigMapping(final TypecastsMapping typecasts) {
        this(
            new YamlMappingOfEntries(
                new MapEntry<Text, YamlNode>(
                    MigrationConfigFields.DBMS_TYPES_CASTS,
                    typecasts
                )
            )
        );
    }

}
