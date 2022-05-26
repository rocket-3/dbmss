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
import com.amihaiemil.eoyaml.YamlNode;
import ru.fusionsoft.database.mapping.dbd.DbdConstraintMapping;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObject;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.SimpleObjectSignature;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeConstraint;
import ru.fusionsoft.lib.yaml.YamlMappingOfPath;
import ru.fusionsoft.lib.yaml.artefacts.TextOfYamlScalarNode;

/**
 * The db constraint objects of
 *  DBD/schemas/#schema/tables/#table/constraints/#constraint node.
 * @since 0.1
 */
public class ObjectOfDbdConstraintMapping extends SimpleDbObject<DbdConstraintMapping> {

    /**
     * Instantiates a new Objects of dbd constraint mapping.
     * @param map The YamlMapping to be encapsulated.
     * @param key The YamlNode to be encapsulated.
     * @param table The {@link ObjectName} of table name be encapsulated.
     */
    public ObjectOfDbdConstraintMapping(
        final YamlMapping map,
        final YamlNode key,
        final ObjectName table
    ) {
        this(
            new DbdConstraintMapping(new YamlMappingOfPath(map, key)),
            new SimpleObjectName(table, new TextOfYamlScalarNode(key))
        );
    }

    /**
     * Instantiates a new Objects of dbd constraint mapping.
     * @param map The YamlMapping to be encapsulated.
     * @param constraint The {@link ObjectName} of constraint to be encapsulated.
     */
    public ObjectOfDbdConstraintMapping(
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
