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
package org.fusionsoft.database.snapshot.objects.ofdbd;

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.Text;
import org.fusionsoft.database.mapping.dbd.DbdIndexMapping;
import org.fusionsoft.database.snapshot.objects.ObjectsEnvelope;
import org.fusionsoft.database.snapshot.objects.signature.ObjectName;
import org.fusionsoft.lib.yaml.YamlMappingOfPath;
import org.fusionsoft.lib.yaml.YamlMappingOrEmptyWhenNoValueNotFound;
import org.fusionsoft.lib.yaml.artefacts.IterableOfClassFromYamlNode;

/**
 * The db index objects of DBD/schemas/#schema/tables/#table/indexes mapping.
 * @since 0.1
 */
public class ObjectsOfDbdIndexesMapping extends ObjectsEnvelope<DbdIndexMapping> {

    /**
     * Instantiates a new Objects of dbd indexes mapping.
     * @param mapping The root's {@link YamlMapping} to be encapsulated.
     * @param key The key's {@link Text} to be encapsulated.
     * @param table The table's {@link ObjectName} to be encapsulated.
     */
    public ObjectsOfDbdIndexesMapping(
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
     * Instantiates a new Objects of dbd indexes mapping.
     * @param indexes The YamlMapping to be encapsulated.
     * @param table The Text to be encapsulated.
     */
    public ObjectsOfDbdIndexesMapping(
        final YamlMapping indexes,
        final ObjectName table
    ) {
        super(
            new IterableOfClassFromYamlNode<>(
                (map, node) -> new IndexOfDbdMapping(map, node, table),
                indexes
            )
        );
    }

}
