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
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectNameOfValues;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectSignature;
import org.fusionsoft.lib.yaml.YamlMappingOfScalar;
import org.fusionsoft.lib.yaml.artefacts.TextOfScalarNode;

/**
 * The db constraint objects of
 *  DBD/schemas/#schema/tables/#table/constraints/#constraint node.
 * @since 0.1
 */
public class ObjectOfDbdConstraintMapping extends SimpleDbObject<YamlMapping> {

    /**
     * Instantiates a new Objects of dbd constraint mapping.
     * @param map The YamlMapping to be encapsulated.
     * @param key The YamlNode to be encapsulated.
     * @param table The Text of table name be encapsulated.
     */
    public ObjectOfDbdConstraintMapping(
        final YamlMapping map,
        final YamlNode key,
        final Text table
    ) {
        this(
            new YamlMappingOfScalar(() -> map.value(key).asMapping()),
            new SimpleObjectNameOfValues(table, new TextOfScalarNode(key))
        );
    }

    /**
     * Instantiates a new Objects of dbd constraint mapping.
     * @param map The YamlMapping to be encapsulated.
     * @param name The Text of constraint name to be encapsulated.
     */
    public ObjectOfDbdConstraintMapping(
        final YamlMapping map,
        final Text name
    ) {
        super(
            map,
            new SimpleObjectSignature(
                new SimpleObjectNameOfValues(name),
                ObjectType.CONSTRAINT
            )
        );
    }

}
