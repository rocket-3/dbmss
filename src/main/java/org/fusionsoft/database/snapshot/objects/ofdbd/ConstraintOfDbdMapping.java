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
import org.fusionsoft.database.mapping.dbd.DbdConstraintMapping;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objects.signature.ObjectName;
import org.fusionsoft.database.snapshot.objects.signature.SimpleObjectSignature;
import org.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import org.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeConstraint;
import org.fusionsoft.lib.yaml.YamlMappingOfPath;
import org.fusionsoft.lib.yaml.artefacts.TextOfScalarNode;

/**
 * The db constraint objects of
 *  DBD/schemas/#schema/tables/#table/constraints/#constraint node.
 * @since 0.1
 */
public class ConstraintOfDbdMapping extends SimpleDbObject<DbdConstraintMapping> {

    /**
     * Instantiates a new Objects of dbd constraint mapping.
     * @param map The YamlMapping to be encapsulated.
     * @param key The YamlNode to be encapsulated.
     * @param table The {@link ObjectName} of table name be encapsulated.
     */
    public ConstraintOfDbdMapping(
        final YamlMapping map,
        final YamlNode key,
        final ObjectName table
    ) {
        this(
            new DbdConstraintMapping(new YamlMappingOfPath(map, key)),
            new SimpleObjectName(table, new TextOfScalarNode(key))
        );
    }

    /**
     * Instantiates a new Objects of dbd constraint mapping.
     * @param map The YamlMapping to be encapsulated.
     * @param constraint The {@link ObjectName} of constraint to be encapsulated.
     */
    public ConstraintOfDbdMapping(
        final DbdConstraintMapping map,
        final ObjectName constraint
    ) {
        super(
            map,
            new SimpleObjectSignature(
                constraint,
                new ObjectTypeConstraint()
            )
        );
    }

}
