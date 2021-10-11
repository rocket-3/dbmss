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
package org.fusionsoft.database.snapshot.objects;

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.Text;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.lib.yaml.artefacts.IterableOfClassFromYamlNode;

/**
 * The db index objects of DBD/schemas/#schema/tables/#table/indexes mapping.
 * @since 0.1
 */
public class ObjectsOfDbdIndexesMapping extends ObjectsEnvelope {

    /**
     * Instantiates a new Objects of dbd indexes mapping.
     * @param indexes The YamlMapping to be encapsulated.
     * @param table The Text to be encapsulated.
     */
    public ObjectsOfDbdIndexesMapping(
        final YamlMapping indexes,
        final Text table
    ) {
        super(
            new IterableOfClassFromYamlNode<DbObject<? extends YamlMapping>>(
                (map, node) -> new ObjectOfDbdIndexMapping(map, node, table),
                indexes
            )
        );
    }

}
