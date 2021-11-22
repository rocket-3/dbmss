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
package org.fusionsoft.database.snapshot.objects.ofdbd;

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.iterable.Joined;
import org.fusionsoft.database.snapshot.objects.ObjectsEnvelope;
import org.fusionsoft.database.snapshot.objects.signature.ObjectName;
import org.fusionsoft.lib.yaml.artefacts.IterableOfClassFromYamlNode;

/**
 * The db table objects of DBD/schemas/#schema/tables/#table node.
 * @since 0.1
 */
public class ObjectsOfDbdTablesMapping extends ObjectsEnvelope<YamlMapping> {

    /**
     * Instantiates a new Objects of mapping.
     * @param mapping The YamlMapping to be encapsulated.
     * @param schema The Text to be encapsulated.
     */
    public ObjectsOfDbdTablesMapping(final YamlMapping mapping, final ObjectName schema) {
        super(
            new Joined<>(
                new IterableOfClassFromYamlNode<>(
                    (map, node) -> new ObjectsOfDbdTableMapping(map, node, schema),
                    mapping
                )
            )
        );
    }

}
