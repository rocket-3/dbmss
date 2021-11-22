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
import org.fusionsoft.database.mapping.fields.DbdTableFields;
import org.fusionsoft.database.snapshot.objects.DefaultObjectsJoined;
import org.fusionsoft.database.snapshot.objects.signature.ObjectName;
import org.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import org.fusionsoft.lib.yaml.YamlMappingOfPath;
import org.fusionsoft.lib.yaml.artefacts.TextOfScalarNode;

/**
 * The type of db table Objects of DBD/schemas/#schema/tables/#table node.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class ObjectsOfDbdTableMapping extends DefaultObjectsJoined {

    /**
     * Instantiates a new Objects of dbd table mapping.
     * @param parent The YamlMapping to be encapsulated.
     * @param key The YamlNode to be encapsulated.
     * @param schema The Text to be encapsulated.
     */
    public ObjectsOfDbdTableMapping(
        final YamlMapping parent,
        final YamlNode key,
        final ObjectName schema
    ) {
        this(
            new YamlMappingOfPath(
                parent,
                key
            ),
            new SimpleObjectName(
                schema,
                new TextOfScalarNode(key)
            )
        );
    }

    /**
     * Instantiates a new Objects of dbd schema mapping.
     * @param mapping The YamlMapping to be encapsulated.
     * @param table The fully qualified {@link ObjectName} of table to be encapsulated.
     */
    public ObjectsOfDbdTableMapping(
        final YamlMapping mapping,
        final ObjectName table
    ) {
        super(
            new TableOfDbdMapping(mapping, table),
            new ObjectsOfDbdIndexesMapping(
                mapping,
                DbdTableFields.INDEXES,
                table
            ),
            new ObjectsOfDbdConstraintsMapping(
                mapping,
                DbdTableFields.CONSTRAINTS,
                table
            ),
            new ObjectsOfDbdTriggersMapping(
                mapping,
                DbdTableFields.TRIGGERS,
                table
            ),
            new ObjectsOfDbdDataMapping(
                mapping,
                DbdTableFields.DATA,
                table
            )
        );
    }

}
