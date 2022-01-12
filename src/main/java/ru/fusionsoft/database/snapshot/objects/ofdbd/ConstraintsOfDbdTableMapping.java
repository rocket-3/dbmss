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

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.Text;
import ru.fusionsoft.database.mapping.dbd.DbdConstraintMapping;
import ru.fusionsoft.database.snapshot.objects.ObjectsEnvelope;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.lib.yaml.YamlMappingOfPath;
import ru.fusionsoft.lib.yaml.YamlMappingOrEmptyWhenNoValueNotFound;
import ru.fusionsoft.lib.yaml.artefacts.IterableOfClassFromYamlNode;

/**
 * The db constraint objects
 *  of DBD/schemas/#schema/tables/#table/constraints mapping.
 * @since 0.1
 */
public class ConstraintsOfDbdTableMapping extends ObjectsEnvelope<DbdConstraintMapping> {

    /**
     * Instantiates a new Objects of dbd constraints mapping.
     * @param mapping The {@link YamlMapping} to be encapsulated.
     * @param key The key's {@link Text} to be encapsulated.
     * @param table The {@link ObjectName} to be encapsulated.
     */
    public ConstraintsOfDbdTableMapping(
        final YamlMapping mapping,
        final Text key,
        final ObjectName table
    ) {
        this(
            new YamlMappingOrEmptyWhenNoValueNotFound(
                new YamlMappingOfPath(mapping, key)
            ),
            table
        );
    }

    /**
     * Instantiates a new Objects of dbd constraints mapping.
     * @param constraints The {@link YamlMapping} to be encapsulated.
     * @param table The {@link ObjectName} to be encapsulated.
     */
    public ConstraintsOfDbdTableMapping(
        final YamlMapping constraints,
        final ObjectName table
    ) {
        super(
            new IterableOfClassFromYamlNode<>(
                (map, node) -> new ConstraintOfDbdMapping(map, node, table),
                constraints
            )
        );
    }

}
