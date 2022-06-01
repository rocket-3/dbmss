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
import ru.fusionsoft.database.mapping.dbd.DbdIndexMapping;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObject;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.SimpleObjectSignature;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeIndex;
import ru.fusionsoft.lib.yaml.YamlMappingOfPath;
import ru.fusionsoft.lib.yaml.artefacts.TextOfYamlScalarNode;

/**
 * The db index object of DBD/schemas/#schema/tables/#table/indexes/#index node.
 * @since 0.1
 */
public class ObjectOfDbdIndexMapping extends SimpleDbObject<DbdIndexMapping> {

    /**
     * Instantiates a new Object of dbd index mapping.
     * @param root The YamlMapping to be encapsulated.
     * @param key The YamlNode to be encapsulated.
     * @param table The Text to be encapsulated.
     */
    public ObjectOfDbdIndexMapping(
        final YamlMapping root,
        final YamlNode key,
        final ObjectName table
    ) {
        this(
            new DbdIndexMapping(new YamlMappingOfPath(root, key)),
            new SimpleObjectName(table, new TextOfYamlScalarNode(key))
        );
    }

    /**
     * Instantiates a new Object of dbd index mapping.
     * @param mapping The YamlMapping to be encapsulated.
     * @param name The Text to be encapsulated.
     */
    public ObjectOfDbdIndexMapping(
        final DbdIndexMapping mapping,
        final ObjectName name
    ) {
        super(
            mapping,
            new SimpleObjectSignature(
                new SimpleObjectName(name),
                new ObjectTypeIndex()
            )
        );
    }

}
