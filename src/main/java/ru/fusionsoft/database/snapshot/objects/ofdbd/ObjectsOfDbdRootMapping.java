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
package ru.fusionsoft.database.snapshot.objects.ofdbd;

import ru.fusionsoft.database.mapping.dbd.DbdRootMapping;
import ru.fusionsoft.database.snapshot.objects.ObjectsJoined;
import ru.fusionsoft.lib.yaml.YamlMappingOfPath;

/**
 * The type of Objects that can be constructed of DbdRootMapping.
 * @since 0.1
 */
public class ObjectsOfDbdRootMapping extends ObjectsJoined {

    /**
     * Instantiates a new Objects of dbd root mapping.
     * @param mapping The DbdRootMapping to be encapsulated.
     */
    public ObjectsOfDbdRootMapping(final DbdRootMapping mapping) {
        super(
            new ObjectsOfDbdSchemasMapping(
                new YamlMappingOfPath(mapping, "schemas")
            )
        );
    }

}
